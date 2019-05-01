/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data.Mappers;

import Data.Entity.Carport;
import Data.Database.DBConnector;
import Data.Entity.PersonalInfo;
import Data.Entity.Request;
import Data.Entity.Roof;
import Data.Entity.Shed;
import Logic.Exceptions.NoSuchCarportException;
import Logic.Exceptions.NoSuchRequestException;
import Logic.Exceptions.NoSuchRoofException;
import Logic.Exceptions.NoSuchShedException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Obaydah Mohamad
 */
class RequestMapper extends IRequestMapper {

    private static RequestMapper instance = null;
    Connection con = DBConnector.getConnection();

    public synchronized static RequestMapper getInstance() {
        if (instance == null) {
            instance = new RequestMapper();
        }
        return instance;
    }

    public static void main(String[] args) throws NoSuchCarportException, NoSuchRequestException, NoSuchRoofException {

        System.out.println(IRequestMapper.instance().getRoofs(0));

    }

    @Override
    public Request getRequest(int id) throws NoSuchRequestException, NoSuchRoofException, NoSuchShedException {
        Request r = null;
        int user_id = 0;
        String datePlaced = "";
        String dateAccepted = "";
        int price = 0;
        PersonalInfo info;
        try {
            String query = "SELECT * FROM requests WHERE request_id = ?;";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                info = getRequestInfo(rs.getInt("user_id"));
                Carport cp = getRequestCarport(rs.getInt("request_id"));
                user_id = rs.getInt("user_id");
                price = rs.getInt("price");
                datePlaced = rs.getString("dateplaced");
                dateAccepted = rs.getString("dateaccepted");

                r = new Request(info, user_id, datePlaced, cp);
            }
        } catch (SQLException e) {
            throw new NoSuchRequestException();
        }

        return r;
    }

    @Override
    public Carport getRequestCarport(int request_id) {
       
        Roof roof = null;
        Shed shed_ = null;
        int roof_id = 0;
        boolean inclined = false;
        int width = 0;
        int length = 0;
        boolean shed = false;
        
        String query = "SELECT * FROM carports WHERE request_id = ?;";
        try {
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setInt(1, request_id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                roof_id = rs.getInt("roof_id");
                width = rs.getInt("width");
                length = rs.getInt("length");
                if (rs.getInt("inclined") == 1) {
                    inclined = true;
                }
                if (rs.getInt("shed") == 1) {
                    shed = true;
                }
                System.out.println("big test");
            }
            System.out.println("test test test");
            roof = getRoof(roof_id);
            shed_ = getRequestShed(request_id);
        } catch (NoSuchRoofException | NoSuchShedException | SQLException e) {
            System.out.println("carport ex " + e.getMessage());
        }
        return new Carport(roof, inclined, width, length, shed, shed_);
    }

    @Override
    public Shed getRequestShed(int request_id) throws NoSuchShedException {
        int width = 0;
        int length = 0;
        try {
            String query = "SELECT * FROM sheds WHERE request_id = ?;";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setInt(1, request_id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                width = rs.getInt("width");
                length = rs.getInt("length");
            }
        } catch (SQLException e) {
            throw new NoSuchShedException();
        }

        return new Shed(width, length);
    }

    @Override
    public void insertRequest(Request req) {
        Carport cp = req.getCarport();
        Roof roof = cp.getRoof();
        Shed shed = cp.getShed_();
        int req_id = 0;

        try {

            String query = "INSERT INTO `requests` (user_id, dateplaced) VALUES (?,?);";
            PreparedStatement pstmt = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, req.getUser_id());
            pstmt.setString(2, req.getDatePlaced());
            pstmt.executeUpdate();

            ResultSet rs = pstmt.getGeneratedKeys();

            if (rs.next()) {
                req_id = rs.getInt(1);
                System.out.println("REQ ID  === " + req_id);
            }
            //req info
            insertRequestInfo(pstmt, query, rs, req, req_id);
            //the carport (carport_id is returned)
            int cp_id = insertRequestCarport(pstmt, query, rs,
                    req_id, cp);

            //shed, if chosen
            if (cp.isShed()) {
                insertRequestShed(pstmt, query, rs, cp_id, shed);
            }

        } catch (SQLException e) {
            System.out.println("REQUEST TABLE " + e.getMessage());
            e.printStackTrace();
        }
    }

    public int insertRequestCarport(PreparedStatement pstmt, String query, ResultSet rs,
            int request_id, Carport cp) {
        int inclined_ = 0;
        int hasShed = 0;
        int id = 0;
        if (cp.isInclined()) {
            inclined_ = 1;
        }
        if (cp.isShed()) {
            hasShed = 1;
        }
        try {
            query = "INSERT INTO `carports` (request_id, roof_id, inclined, width, length, shed) VALUES (?,?,?,?,?,?);";
            pstmt = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, request_id);
            pstmt.setInt(2, cp.getRoof().getRoof_id());
            pstmt.setInt(3, inclined_);
            pstmt.setInt(4, cp.getWidth());
            pstmt.setInt(5, cp.getLength());
            pstmt.setInt(6, hasShed);
            pstmt.executeUpdate();
            rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                id = rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println("INSERT REQ CARPORT " + e.getMessage());
        }
        return id;
    }

    public void insertRequestShed(PreparedStatement pstmt, String query, ResultSet rs,
            int cp_id, Shed shed) {
        try {
            query = "INSERT INTO `sheds` (carport_id, width, length) VALUES (?,?,?);";
            pstmt = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, cp_id);
            pstmt.setInt(2, shed.getWidth());
            pstmt.setInt(3, shed.getLength());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("INSERT REQ SHED: " + e.getMessage());
        }
    }

    @Override
    public Roof getRoof(String name) throws NoSuchRoofException {
        int roof_id = 0;
        String name_ = "";
        int price = 0;
        boolean inclined = false;

        try {
            String query = "SELECT * FROM rooftype WHERE name = ?;";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1, name);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                roof_id = rs.getInt("roof_id");
                name_ = rs.getString("name");
                if (rs.getInt("inclined") == 1) {
                    inclined = true;
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new NoSuchRoofException();
        }

        return new Roof(roof_id, name, inclined);
    }

    @Override
    public Roof getRoof(int id) throws NoSuchRoofException {
        int roof_id = 0;
        String name_ = "";
        int price = 0;
        boolean inclined = false;

        try {
            String query = "SELECT * FROM rooftype WHERE roof_id = ?";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                roof_id = rs.getInt("roof_id");
                name_ = rs.getString("name");
                if (rs.getInt("inclined") == 1) {
                    inclined = true;
                }
            }
        } catch (SQLException e) {
            System.out.println("ROOFEX " + e.getMessage());
            throw new NoSuchRoofException();
        }

        return new Roof(roof_id, name_, inclined);
    }

    @Override
    public List<Roof> getRoofs(int rooftype) throws NoSuchRoofException {
        List<Roof> roofs = new ArrayList<Roof>();
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
        }

        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                roof_id = rs.getInt("roof_id");
                name = rs.getString("name");
                if (rs.getInt("inclined") == 1) {
                    inclined = true;
                }
                roofs.add(new Roof(roof_id, name, inclined));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new NoSuchRoofException();
        }

        return roofs;
    }

    @Override
    public void updateRoofPrice(int roof_id, int price) throws NoSuchRoofException {
        try {
            String query = "UPDATE `rooftype` SET price = ? WHERE roof_id = ?;";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setInt(2, roof_id);
            pstmt.setInt(1, price);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new NoSuchRoofException();
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
    public void insertDimensions(int id, int length, int price) {
        try {
            String query = "INSERT INTO rooflength (roof_id, roof_length, price) VALUES (?, ?, ?);";
            PreparedStatement pstmt = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, id);
            pstmt.setInt(2, length);
            pstmt.setInt(3, price);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("INSERT DIMENSIONS: " + e.getMessage());
        }
    }

    private PersonalInfo getRequestInfo(int id) {
        int user_id = 0;
        String firstname = "";
        String lastname = "";
        String address = "";
        int zipcode = 0;
        String city = "";
        String gender = "";

        try {
            String query = "SELECT * FROM users_personalinfo WHERE user_id = ?;";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                firstname = rs.getString("firstname");
                lastname = rs.getString("lastname");
                address = rs.getString("address");
                zipcode = rs.getInt("zipcode");
                city = rs.getString("city");
                gender = rs.getString("gender");
            }
        } catch (SQLException e) {
            System.out.println("infoex " + e.getMessage());
        }

        return new PersonalInfo(firstname, lastname, address, zipcode, city, gender);
    }

    private void insertRequestInfo(PreparedStatement pstmt, String query, ResultSet rs, Request req, int req_id) throws SQLException {

        query = "INSERT INTO users_personalinfo (request_id, user_id, firstname, "
                + "lastname, address, zipcode, city, gender) VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
        pstmt = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        pstmt.setInt(1, req_id);
        pstmt.setInt(2, req.getUser_id());
        pstmt.setString(3, req.getInfo().getFirstname());
        pstmt.setString(4, req.getInfo().getLastname());
        pstmt.setString(5, req.getInfo().getAddress());
        pstmt.setInt(6, req.getInfo().getZipcode());
        pstmt.setString(7, req.getInfo().getCity());
        pstmt.setString(8, req.getInfo().getGender());
        pstmt.executeUpdate();
    }

    @Override
    public int getDimensionID(int roof_id, int length) {
        int rooflength_id = 0;
        try {
            String query = "SELECT * FROM rooflength WHERE roof_id = ? AND roof_length = ?";
            PreparedStatement pstmt = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, roof_id);
            pstmt.setInt(2, length);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                rooflength_id = rs.getInt("rooflength_id");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return rooflength_id;
    }

    @Override
    public int getDimensionPrice(int roof_id, int length) {
        int price = 0;
        try {
            String query = "SELECT * FROM rooflength WHERE roof_id = ? AND roof_length = ?";
            PreparedStatement pstmt = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, roof_id);
            pstmt.setInt(2, length);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                price = rs.getInt("rooflength_id");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return price;
    }

    @Override
    public List<Request> getRequests() {
        int user_id = 0;
        int req_id = 0;
        String datePlaced = "";
        PersonalInfo info = null;
        List<Request> requests = new ArrayList();
        Carport cp = null;

        try {
            String query = "SELECT * FROM requests;";
            Statement stmt = con.prepareStatement(query);
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                req_id = rs.getInt("request_id");
                user_id = rs.getInt("user_id");
                datePlaced = rs.getString("dateplaced");
                info = getRequestInfo(user_id);
                cp = getRequestCarport(req_id);
                requests.add(new Request(info, user_id, datePlaced, cp));
            }
        } catch (SQLException e) {
            System.out.println("REQUESTEX" + e.getMessage());
        }
        
        return requests;
    }

}
