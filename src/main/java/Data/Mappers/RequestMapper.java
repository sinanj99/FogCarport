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
import Logic.Exceptions.NoSuchMaterialException;
import Logic.Exceptions.NoSuchRequestException;
import Logic.Exceptions.NoSuchRoofException;
import Logic.Exceptions.NoSuchShedException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

        //IRequestMapper.instance().insertRequest(1, 101, 20000, false, 400, 600, true, 100, 200);
        //IRequestMapper.instance().insertRequestCarport(5, 109, false, 420, 620, false);
        //IRequestMapper.instance().insertRequestShed(4, 25, 26);
        //System.out.println(IRequestMapper.instance().getRequestCarport(7));
        //System.out.println(IRequestMapper.instance().getRequest(7));
//        System.out.println("////////////////////FLADT TAG////////////////////");
//        for(Roof r : IRequestMapper.instance().getRoofs(0)){
//            System.out.println(r);
//        }
//        System.out.println("////////////////////INCLINED TAG////////////////////");
//        for(Roof r : IRequestMapper.instance().getRoofs(1)){
//            System.out.println(r);
//        }
//        System.out.println("////////////////////ALLE TAG////////////////////");
//        for(Roof r : IRequestMapper.instance().getRoofs(2)){
//            System.out.println(r);
//        }
        System.out.println(IRequestMapper.instance().getRoof("Betonstagsten - Rød"));

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
    public Carport getRequestCarport(int request_id) throws NoSuchRoofException, NoSuchShedException, SQLException {
        Carport cp = null;
        int roof_id = 0;
        boolean inclined = false;
        int width = 0;
        int length = 0;
        boolean shed = false;

        String query = "SELECT * FROM carports WHERE request_id = ?";
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

            cp = new Carport(getRoof(roof_id), inclined, width, length, shed, getRequestShed(request_id));
        }

        return cp;
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
                req_id = rs.getInt("request_id");
            }
            //req info
            insertRequestInfo(pstmt, query, rs, req, req_id);
            //carport roof
            insertRoof(pstmt, query, rs, roof);
            //roof dimensions of carport roof
            insertDimensions(cp.getRoof().getRoof_id(), cp.getLength());
            //the carport (carport_id is returned)
            int cp_id = insertRequestCarport(pstmt, query, rs,
            req_id, cp);
            
            //shed, if chosen
            if (cp.isShed()) {
                insertRequestShed(pstmt, query, rs, req_id, shed);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
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
            pstmt.setInt(1, request_id);
            pstmt.setInt(2, cp.getRoof().getRoof_id());
            pstmt.setInt(3, inclined_);
            pstmt.setInt(4, cp.getWidth());
            pstmt.setInt(5, cp.getLength());
            pstmt.setInt(6, hasShed);
            pstmt.executeUpdate();
            rs = pstmt.executeQuery();
            if (rs.next()) {
                id = rs.getInt("carport_id");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return id;
    }

    public void insertRequestShed(PreparedStatement pstmt, String query, ResultSet rs, 
            int request_id, Shed shed) {
        try {
            query = "INSERT INTO `sheds` (request_id, width, length) VALUES (?,?,?);";
            pstmt.setInt(1, request_id);
            pstmt.setInt(2, shed.getWidth());
            pstmt.setInt(3, shed.getLength());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
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
                price = rs.getInt("price");
                if (rs.getInt("inclined") == 1) {
                    inclined = true;
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new NoSuchRoofException();
        }

        return new Roof(roof_id, name, price, inclined);
    }

    @Override
    public Roof getRoof(int id) throws NoSuchRoofException {
        int roof_id = 0;
        String name_ = "";
        int price = 0;
        boolean inclined = false;

        try {
            String query = "SELECT * FROM rooftype WHERE roof_id = ?;";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                roof_id = rs.getInt("roof_id");
                name_ = rs.getString("name");
                price = rs.getInt("price");
                if (rs.getInt("inclined") == 1) {
                    inclined = true;
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new NoSuchRoofException();
        }

        return new Roof(roof_id, name_, price, inclined);
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
                price = rs.getInt("price");
                if (rs.getInt("inclined") == 1) {
                    inclined = true;
                }
                roofs.add(new Roof(roof_id, name, price, inclined));
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
            query = "INSERT INTO rooftype (name, price, inclined) VALUES (?, ?, ?);";
            pstmt.setString(1, roof.getName());
            pstmt.setInt(2, roof.getPrice());
            pstmt.setInt(3, inclined_);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    @Override
    public void insertDimensions(int id, int length) {
        try {
            String query = "INSERT INTO rooflength (roof_id, roof_length) VALUES (?, ?);";
            PreparedStatement pstmt = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, id);
            pstmt.setInt(2, length);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private PersonalInfo getRequestInfo(int aInt) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void insertRequestInfo(PreparedStatement pstmt, String query, ResultSet rs, Request req, int req_id) throws SQLException {

        query = "INSERT INTO users_personalinfo (request_id, user_id, firstname"
                + "lastname, address, zipcode, city, gender) VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
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
}
