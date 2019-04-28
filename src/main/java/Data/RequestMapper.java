/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import Logic.NoSuchCarportException;
import Logic.NoSuchMaterialException;
import Logic.NoSuchRequestException;
import Logic.NoSuchRoofException;
import Logic.NoSuchShedException;
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
    public Request getRequest(int id) throws NoSuchRequestException {
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

                r = new Request(info, user_id, datePlaced, dateAccepted, price, cp);
            }
        } catch (SQLException | NoSuchCarportException e) {
            throw new NoSuchRequestException();
        }

        return r;
    }

    @Override
    public Carport getRequestCarport(int request_id) throws NoSuchCarportException {
        Carport cp = null;
        int roof_id = 0;
        boolean inclined = false;
        int width = 0;
        int length = 0;
        boolean shed = false;

        try {
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
                try {
                    cp = new Carport(getRoof(roof_id), inclined, width, length, shed, getRequestShed(rs.getInt("request_id")));
                } catch (NoSuchShedException e) {

                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new NoSuchCarportException();
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
        try {
            String query = "INSERT INTO `requests` (user_id, dateplaced) VALUES (?,?);";
            PreparedStatement pstmt = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, req.getUser_id());
            pstmt.setString(2, req.getDatePlaced());
            pstmt.executeUpdate();
            ResultSet rs = pstmt.getGeneratedKeys();

            insertRequestCarport(rs.getInt(1), roof.getRoof_id(),
                    cp.isInclined(), cp.getWidth(), cp.getLength(), cp.isShed());
            
            if(cp.isShed()) insertRequestShed(rs.getInt(1), shed.getWidth(), shed.getLength());
            
            insertRequestRoof();
            
            insertRequestInfo();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void insertRequestCarport(int request_id, int roof_id, boolean inclined, int width, int length, boolean shed) {
        int inclined_ = 0;
        int hasShed = 0;
        if (inclined) {
            inclined_ = 1;
        }
        if (shed) {
            hasShed = 1;
        }
        try {
            String query = "INSERT INTO `carports` (request_id, roof_id, inclined, width, length, shed) VALUES (?,?,?,?,?,?);";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setInt(1, request_id);
            pstmt.setInt(2, roof_id);
            pstmt.setInt(3, inclined_);
            pstmt.setInt(4, width);
            pstmt.setInt(5, length);
            pstmt.setInt(6, hasShed);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void insertRequestShed(int request_id, int shedWidth, int shedLength) {
        try {
            String query = "INSERT INTO `sheds` (request_id, width, length) VALUES (?,?,?);";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setInt(1, request_id);
            pstmt.setInt(2, shedWidth);
            pstmt.setInt(3, shedLength);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
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
            throw new NoSuchRoofException();
        }

        return new Roof(roof_id, name, price, inclined);
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
            e.printStackTrace();
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
            e.printStackTrace();
            throw new NoSuchRoofException();
        }
    }

    @Override
    public void insertRoof(String name, int price, boolean inclined) {
        int inclined_ = 0;
        if (inclined) {
            inclined_ = 1;
        }

        try {
            String query = "INSERT INTO rooftype (name, price, inclined) VALUES (?, ?, ?);";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1, name);
            pstmt.setInt(2, price);
            pstmt.setInt(3, inclined_);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void insertDimensions(int id, int length) {
        try {
            String query = "INSERT INTO rooflength (roof_id, roof_length) VALUES (?, ?);";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setInt(1, id);
            pstmt.setInt(2, length);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private PersonalInfo getRequestInfo(int aInt) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void insertRequestRoof() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void insertRequestInfo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
