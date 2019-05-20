/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data.Mappers;

import Data.Entity.Carport;
import Data.Database.DBConnector;
import Data.Entity.Request;
import Data.Entity.Roof;
import Data.Entity.Shed;
import Data.Entity.ShippingAddress;
import Presentation.Exceptions.NoSuchCarportException;
import Presentation.Exceptions.NoSuchRequestException;
import Presentation.Exceptions.NoSuchRoofException;
import Presentation.Exceptions.NoSuchShedException;
import Presentation.Exceptions.SystemErrorException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;

/**
 *
 * @author Obaydah Mohamad
 */
class RequestMapper extends IRequestMapper {

    private static RequestMapper instance = null;
    private final DBConnector con = new DBConnector();
    private Connection conn;

    @Override
    public void setDataSource(DataSource ds) {
        con.setDataSource(ds);
        conn = con.getConnection();
    }

    public synchronized static RequestMapper getInstance() {
        if (instance == null) {
            instance = new RequestMapper();
        }
        return instance;
    }

    @Override
    public Request getRequest(int id) throws SystemErrorException, NoSuchRequestException, NoSuchShedException {
        Request r = null;
        int req_id = 0;
        int user_id = 0;
        String datePlaced = "";
        int price = 0;
        ShippingAddress address;

        try {
            String query = "SELECT * FROM requests WHERE request_id = ?;";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                req_id = rs.getInt("request_id");
                user_id = rs.getInt("user_id");
                address = getRequestShippingAddress(id);
                Carport cp = getRequestCarport(id);
                datePlaced = rs.getString("dateplaced");

                r = new Request(req_id, user_id, datePlaced, cp, address);
            } else {
                throw new NoSuchRequestException(id);
            }
        } catch (SQLException e) {
            throw new SystemErrorException(e.getMessage());
        }

        return r;
    }

    /**
     *
     * Method is used in getRequest-method; fetches the needed info from the
     * carport table in dB.
     *
     * @param request_id
     * @return Info about a request's carport.
     */
    private Carport getRequestCarport(int request_id) throws NoSuchCarportException, SystemErrorException, NoSuchShedException {

        Roof roof = null;
        Shed shed_ = null;
        int roof_id = 0;
        int inclination = 0;
        int width = 0;
        int length = 0;
        int carport_id = 0;

        String query = "SELECT * FROM carports WHERE request_id = ?;";
        try {
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, request_id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                carport_id = rs.getInt("carport_id");
                roof_id = rs.getInt("roof_id");
                width = rs.getInt("width");
                length = rs.getInt("length");
                inclination = rs.getInt("inclination");
            } else {
                throw new NoSuchCarportException(request_id);
            }
            roof = getRoof(roof_id, width);
            shed_ = getRequestShed(request_id);
        } catch (SQLException e) {
            throw new SystemErrorException(e.getMessage());
        }
        return new Carport(carport_id, roof, inclination, width, length, shed_);
    }

    /**
     * Method is used in getRequestCarport-method - fetches the needed data from
     * shed-table.
     *
     * @param carport_id
     * @return
     */
    private Shed getRequestShed(int carport_id) throws SystemErrorException {
        int shedId = 0;
        int width = 0;
        int length = 0;
        Shed shed = null;
        try {
            String query = "SELECT * FROM sheds WHERE carport_id = ?;";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, carport_id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                shedId = rs.getInt("shed_id");
                width = rs.getInt("width");
                length = rs.getInt("length");
                shed = new Shed(shedId, width, length);
            } 
        } catch (SQLException e) {
            throw new SystemErrorException(e.getMessage());
        }
        return shed;
    }

    @Override
    public ShippingAddress getRequestShippingAddress(int id) throws SystemErrorException {
        String firstname = "";
        String lastname = "";
        String address = "";
        int zipcode = 0;
        String city = "";
        String gender = "";

        try {
            String query = "SELECT * FROM shipping_address WHERE request_id = ?;";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                firstname = rs.getString("firstname");
                lastname = rs.getString("lastname");
                address = rs.getString("address");
                zipcode = rs.getInt("zipcode");
                city = rs.getString("city");
            }
        } catch (SQLException e) {
            throw new SystemErrorException(e.getMessage());
        }

        return new ShippingAddress(firstname, lastname, address, zipcode, city);
    }

    @Override
    public void insertRequest(Request req) throws SystemErrorException {
        Carport cp = req.getCarport();
        Roof roof = cp.getRoof();
        Shed shed = cp.getShed_();
        int req_id = 0;

        try {
            conn.setAutoCommit(false);
            String query = "INSERT INTO `requests` (user_id, dateplaced) VALUES (?,?);";
            PreparedStatement pstmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, req.getUser_id());
            pstmt.setString(2, req.getDatePlaced());
            pstmt.executeUpdate();

            ResultSet rs = pstmt.getGeneratedKeys();

            if (rs.next()) {
                req_id = rs.getInt(1);
            }
            //req info
            insertRequestShippingAddress(pstmt, query, rs, req, req_id);
            //the carport (carport_id is returned)
            int cp_id = insertRequestCarport(pstmt, query, rs,
                    req_id, cp);

            //shed, if chosen
            if (cp.getShed_() != null) {
                insertRequestShed(pstmt, query, rs, cp_id, shed);
            }
            conn.commit();
        } catch (SQLException e) {
            try {
                conn.rollback();
            } catch (SQLException ex) {
                throw new SystemErrorException(ex.getMessage());
            }
            throw new SystemErrorException(e.getMessage());
        }
    }

    /**
     * Called in insert request method.
     *
     * @param pstmt
     * @param query
     * @param rs
     * @param request_id
     * @param cp
     * @return
     */
    private int insertRequestCarport(PreparedStatement pstmt, String query, ResultSet rs,
            int request_id, Carport cp) throws SystemErrorException {
        int id = 0;

        try {
            query = "INSERT INTO `carports` (request_id, roof_id, inclination, width, length) VALUES (?,?,?,?,?);";
            pstmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, request_id);
            pstmt.setInt(2, cp.getRoof().getRoof_id());
            pstmt.setInt(3, cp.getInclination());
            pstmt.setInt(4, cp.getWidth());
            pstmt.setInt(5, cp.getLength());
            pstmt.executeUpdate();
            rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                id = rs.getInt(1);
            }
        } catch (SQLException e) {
            throw new SystemErrorException(e.getMessage());
        }
        return id;
    }

    /**
     * Called in insert request method.
     *
     * @param pstmt
     * @param query
     * @param rs
     * @param cp_id
     * @param shed
     */
    private void insertRequestShed(PreparedStatement pstmt, String query, ResultSet rs,
            int cp_id, Shed shed) throws SystemErrorException {
        try {
            query = "INSERT INTO `sheds` (carport_id, width, length) VALUES (?,?,?);";
            pstmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, cp_id);
            pstmt.setInt(2, shed.getWidth());
            pstmt.setInt(3, shed.getLength());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new SystemErrorException(e.getMessage());
        }
    }

    @Override
    public Roof getRoof(int id) throws NoSuchRoofException, SystemErrorException {
        int roof_id = 0;
        String name_ = "";
        boolean inclined = false;

        try {
            String query = "SELECT * FROM rooftype WHERE roof_id = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                roof_id = rs.getInt("roof_id");
                name_ = rs.getString("name");
                if (rs.getInt("inclined") == 1) {
                    inclined = true;
                }
            } else {
                throw new NoSuchRoofException(roof_id);
            }
        } catch (SQLException e) {
            throw new SystemErrorException(e.getMessage());
        }

        return new Roof(roof_id, name_, inclined);
    }

    @Override
    public List<Roof> getRoofs() throws SystemErrorException{
        List<Roof> roofs = new ArrayList();
        int roof_id = 0;
        String name = "";
        int inclined_ = 0;
        boolean inclined = false;

        String query = "SELECT * FROM rooftype";

        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                roof_id = rs.getInt("roof_id");
                name = rs.getString("name");
                inclined_ = rs.getInt("inclined");
                if (inclined_ == 1) {
                    inclined = true;
                }

                roofs.add(new Roof(roof_id, name, inclined));
            }
        } catch (SQLException e) {
            throw new SystemErrorException(e.getMessage());
        }

        return roofs;
    }

    @Override
    public List<Roof> getRoofs(int rooftype) throws SystemErrorException {
        List<Roof> roofs = new ArrayList();
        int roof_id = 0;
        String name = "";
        int price = 0;
        boolean inclined = false;

        String query = "SELECT * FROM rooftype";
        if (rooftype == 0) {
            query = "SELECT * FROM rooftype WHERE inclined = 0";
        }
        if (rooftype == 1) {
            query = "SELECT * FROM rooftype WHERE inclined = 1";
            inclined = true;
        }

        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                roof_id = rs.getInt("roof_id");
                name = rs.getString("name");
                roofs.add(new Roof(roof_id, name, inclined));
            }
        } catch (SQLException e) {
            throw new SystemErrorException(e.getMessage());
        }

        return roofs;
    }

    @Override
    public void updateRoofPrice(int roof_id, int price) throws SystemErrorException {
        try {
            String query = "UPDATE `rooftype` SET price = ? WHERE roof_id = ?;";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(2, roof_id);
            pstmt.setInt(1, price);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new SystemErrorException(e.getMessage());
        }
    }

    public void insertRoof(PreparedStatement pstmt, String query, ResultSet rs, Roof roof) {
        int inclined_ = 0;
        if (roof.isInclined()) {
            inclined_ = 1;
        }

        try {
            query = "INSERT INTO rooftype (name, inclined) VALUES (?, ?, ?);";
            pstmt.setString(1, roof.getName());
            pstmt.setInt(3, inclined_);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void insertDimensions(int id, int length, int price) throws SystemErrorException{
        try {
            String query = "INSERT INTO rooflength (roof_id, roof_length, price) VALUES (?, ?, ?);";
            PreparedStatement pstmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, id);
            pstmt.setInt(2, length);
            pstmt.setInt(3, price);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new SystemErrorException(e.getMessage());
        }
    }

    /**
     * Called in insert request method.
     *
     * @param pstmt
     * @param query
     * @param rs
     * @param req
     * @param req_id
     */
    private void insertRequestShippingAddress(PreparedStatement pstmt, String query, ResultSet rs, Request req, int req_id) throws SystemErrorException {

        try {
            query = "INSERT INTO shipping_address (request_id, firstname, "
                    + "lastname, address, zipcode, city) VALUES (?, ?, ?, ?, ?, ?);";
            pstmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, req_id);
            pstmt.setString(2, req.getAddress().getFirstname());
            pstmt.setString(3, req.getAddress().getLastname());
            pstmt.setString(4, req.getAddress().getAddress());
            pstmt.setInt(5, req.getAddress().getZipcode());
            pstmt.setString(6, req.getAddress().getCity());
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            throw new SystemErrorException(ex.getMessage());
        }
    }

    @Override
    public int getDimensionPrice(int roof_id, int length) throws SystemErrorException{
        int price = 0;
        try {
            String query = "SELECT * FROM rooflength WHERE roof_id = ? AND roof_length = ?";
            PreparedStatement pstmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, roof_id);
            pstmt.setInt(2, length);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                price = rs.getInt("price");
            }
        } catch (SQLException ex) {
            throw new SystemErrorException(ex.getMessage());
        }
        return price;
    }

    @Override
    public List<Request> getRequests() throws SystemErrorException, NoSuchShedException{
        int user_id, req_id;
        String datePlaced;
        ShippingAddress address;
        List<Request> requests = new ArrayList();
        Carport cp;
        System.out.println("TEST TEST");
        try {
            String query = "SELECT * FROM requests ORDER BY `request_id` DESC;";
            Statement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                req_id = rs.getInt("request_id");
                user_id = rs.getInt("user_id");
                datePlaced = rs.getString("dateplaced");
                address = getRequestShippingAddress(req_id);
                cp = getRequestCarport(req_id);
                System.out.println("CARPORT = " + cp);
                requests.add(new Request(req_id, user_id, datePlaced, cp, address));
            }
            System.out.println(requests);
        } catch (SQLException e) {
            throw new SystemErrorException(e.getMessage());
        }

        return requests;
    }

    @Override
    public Roof getRoof(int id, int length) throws SystemErrorException {
        int roof_id = 0;
        String name_ = "";
        int price = 0;
        int inclined_ = 0;
        boolean inclined = false;

        try {

            String query = "SELECT * FROM rooftype WHERE roof_id = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                roof_id = rs.getInt("roof_id");
                name_ = rs.getString("name");
                inclined_ = rs.getInt("inclined");

                if (inclined_ == 1) {
                    inclined = true;
                }
            }

            query = "SELECT * FROM rooflength WHERE roof_id = ? AND roof_length = ?;";
            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, id);
            pstmt.setInt(2, length);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                price = rs.getInt("price");
            }
        } catch (SQLException e) {
            throw new SystemErrorException(e.getMessage());
        }

        return new Roof(roof_id, name_, price, inclined, length);
    }

}
