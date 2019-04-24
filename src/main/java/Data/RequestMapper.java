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
class RequestMapper extends IRequestMapper{
    
    private static RequestMapper instance = null;
    Connection con = DBConnector.getConnection();

    public synchronized static RequestMapper getInstance() {
        if (instance == null) {
            instance = new RequestMapper();
        }
        return instance;
    }
    
    public static void main(String[] args) throws NoSuchCarportException, NoSuchRequestException {
        
        //IRequestMapper.instance().insertRequest(1, 101, 20000, false, 400, 600, true, 100, 200);
        //IRequestMapper.instance().insertRequestCarport(5, 109, false, 420, 620, false);
        //IRequestMapper.instance().insertRequestShed(4, 25, 26);
        //System.out.println(IRequestMapper.instance().getRequestCarport(7));
        System.out.println(IRequestMapper.instance().getRequest(7));
    }
    
    
    @Override
    public Request getRequest(int id) throws NoSuchRequestException {
        Request r = null;
        int user_id = 0;
        String date = "";
        int price = 0;
        try{
            String query = "SELECT * FROM requests WHERE request_id = ?;";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            
            if(rs.next()){
                Carport cp = getRequestCarport(rs.getInt("request_id"));
                Shed shed = getRequestShed(rs.getInt("request_id"));
                user_id = rs.getInt("user_id");
                price = rs.getInt("price");
                date = rs.getString("dateplaced");
                r = new Request(user_id, cp.isInclined(), cp.getWidth(), cp.getLength(), getRoof(cp.getRoof_id()), cp.isShed(), shed.getWidth(), shed.getLength());
            }
        }catch(SQLException | NoSuchCarportException | NoSuchShedException | NoSuchRoofException e){
            throw new NoSuchRequestException();
        }
        
        return r;
    }
    
    @Override
    public Carport getRequestCarport(int request_id) throws NoSuchCarportException{
        Carport cp = null;
        int roof_id = 0;
        boolean inclined = false;
        int width = 0;
        int length = 0;
        boolean shed = false;
        
        try{
            String query = "SELECT * FROM carports WHERE request_id = ?";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setInt(1, request_id);
            ResultSet rs = pstmt.executeQuery();
            
            if(rs.next()){
                roof_id = rs.getInt("roof_id");
                width = rs.getInt("width");
                length = rs.getInt("length");
                if(rs.getInt("inclined") == 1) inclined = true;
                if(rs.getInt("shed") == 1) shed = true;
                try {
                    cp = new Carport(roof_id, inclined, width, length, shed, getRequestShed(rs.getInt("request_id")));
                } catch (NoSuchShedException e) {
                    
                }
            }
        }catch(SQLException e){
            e.printStackTrace();
            throw new NoSuchCarportException();
        }
        
        return cp;
    }

    @Override
    public Shed getRequestShed(int request_id) throws NoSuchShedException{
        int width = 0;
        int length = 0;
        try{
            String query = "SELECT * FROM sheds WHERE request_id = ?;";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setInt(1, request_id);
            ResultSet rs = pstmt.executeQuery();
            
            if(rs.next()){
                width = rs.getInt("width");
                length = rs.getInt("length");
            }
        }catch(SQLException e){
            throw new NoSuchShedException();
        }
        
        return new Shed(width, length);
    }
    
    @Override
    public void insertRequest(int user_id, int roof_id, int price, boolean inclined, int width, int length, boolean shed, int shedWidth, int shedLength) {
        LocalDateTime date = LocalDateTime.now();
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        try{
            String query = "INSERT INTO `requests` (user_id, dateplaced, price) VALUES (?,?,?);";
            PreparedStatement pstmt = con.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, user_id);
            pstmt.setString(2, date.format(dateFormat));
            pstmt.setInt(3, price);
            pstmt.executeUpdate();
            ResultSet rs = pstmt.getGeneratedKeys();
            if(rs.next()){
                insertRequestCarport(rs.getInt(1), roof_id, inclined, width, length, shed);
                insertRequestShed(rs.getInt(1), shedWidth, shedLength); 
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    
    @Override
    public void insertRequestCarport(int request_id, int roof_id, boolean inclined, int width, int length, boolean shed) {
        int inclined_ = 0;
        int hasShed = 0;
        if(inclined) inclined_ = 1;
        if(shed) hasShed = 1;
        try{
            String query = "INSERT INTO `carports` (request_id, roof_id, inclined, width, length, shed) VALUES (?,?,?,?,?,?);";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setInt(1, request_id);
            pstmt.setInt(2, roof_id);
            pstmt.setInt(3, inclined_);
            pstmt.setInt(4, width);
            pstmt.setInt(5, length);
            pstmt.setInt(6, hasShed);
            pstmt.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void insertRequestShed(int request_id, int shedWidth, int shedLength) {
        try{
            String query = "INSERT INTO `sheds` (request_id, width, length) VALUES (?,?,?);";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setInt(1, request_id);
            pstmt.setInt(2, shedWidth);
            pstmt.setInt(3, shedLength);
            pstmt.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    
    @Override
    public Roof getRoof(int id) throws NoSuchRoofException {
        int roof_id = 0;
        String name = "";
        int price = 0;
        boolean inclined = false;
        
        try{
            String query = "SELECT * FROM rooftype WHERE roof_id = ?;";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            
            if(rs.next()){
                roof_id = rs.getInt("roof_id");
                name = rs.getString("name");
                price = rs.getInt("price");
                if(rs.getInt("inclined") == 1) inclined = true;
            }
        }catch(SQLException e){
            throw new NoSuchRoofException();
        }
        
        return new Roof(roof_id, name, price, inclined);
    }

    @Override
    public List<Roof> getRoofs() throws NoSuchRoofException {
        List<Roof> roofs = new ArrayList<Roof>();
        int roof_id = 0;
        String name = "";
        int price = 0;
        boolean inclined = false;
        
        try{
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM rooftype");
            
            while(rs.next()){
                roof_id = rs.getInt("roof_id");
                name = rs.getString("name");
                price = rs.getInt("price");
                if(rs.getInt("inclined") == 1) inclined = true;
                roofs.add(new Roof(roof_id, name, price, inclined));
            }
        }catch(SQLException e){
            e.printStackTrace();
            throw new NoSuchRoofException();
        }
        
        return roofs;
    }

    @Override
    public void updateRoofPrice(int roof_id, int price) throws NoSuchRoofException {
        try{
            String query = "UPDATE `rooftype` SET price = ? WHERE roof_id = ?;";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setInt(2, roof_id);
            pstmt.setInt(1, price);
            pstmt.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
            throw new NoSuchRoofException();
        }
    }

    @Override
    public void insertRoof(String name, int price, boolean inclined) {
        int inclined_ = 0;
        if(inclined) inclined_ = 1;
        
        try{
            String query = "INSERT INTO rooftype (name, price, inclined) VALUES (?, ?, ?);";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1, name);
            pstmt.setInt(2, price);
            pstmt.setInt(3, inclined_);
            pstmt.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
}
