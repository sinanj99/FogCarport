/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data.Mappers;

import Data.Controller.DataFacade;
import Data.Database.DBConnector;
import Data.Database.DataSourceMysql;
import Data.Entity.Response;
import Presentation.Exceptions.NoSuchResponseException;
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
class ResponseMapper extends IResponseMapper{

    private static ResponseMapper instance = null;
    private final DBConnector con = new DBConnector();
    
    private Connection conn;

    @Override
    public void setDataSource(DataSource ds) {
        con.setDataSource(ds);
        conn = con.getConnection();
    }
    
    public synchronized static ResponseMapper getInstance() {
        if (instance == null) {
            instance = new ResponseMapper();
        }
        return instance;
    }
    
    
    @Override
    public List<Response> getResponses(int userId) throws SystemErrorException{
        List<Response> responses = new ArrayList<Response>();
        
        int requestId = 0;
        int sellerId = 0;
        int sellPrice = 0;
        int status = 0;
        String datePlaced = "";
        /*
        
        SELECT Orders.OrderID, Customers.CustomerName, Orders.OrderDate
        FROM Orders
        INNER JOIN Customers
        ON Orders.CustomerID=Customers.CustomerID;
        */
        try{
            String query = "SELECT responses.request_id, seller_id, responses.dateplaced, sell_price, status "
                        + "FROM responses LEFT JOIN requests ON responses.request_id = requests.request_id "
                        + "WHERE requests.user_Id = ? "
                        + "ORDER BY dateplaced DESC";
            PreparedStatement p = conn.prepareStatement(query);
            p.setInt(1, userId);
            ResultSet rs = p.executeQuery();
            
            while(rs.next()){
                requestId = rs.getInt("request_id");
                sellerId = rs.getInt("seller_id");
                sellPrice = rs.getInt("sell_price");
                datePlaced = rs.getString("dateplaced");
                status = rs.getInt("status");
                responses.add(new Response(DataFacade.getInstance().getRequest(requestId), sellerId, datePlaced, sellPrice));
            }
            
        }catch(SQLException e){
            throw new SystemErrorException(e.getMessage());
        }
        
        return responses;
    }
    
    @Override
    public Response getResponse(int requestId) throws NoSuchResponseException, SystemErrorException{
        Response r = null;
        int sellerId = 0;
        int sellPrice = 0;
        int status = 0;
        String datePlaced = "";
        
        try {
            String query = "SELECT * FROM responses WHERE request_id = ?;";
            PreparedStatement p = conn.prepareStatement(query);
            p.setInt(1, requestId);
            ResultSet rs = p.executeQuery();
            if(rs.next()){
                
                requestId = rs.getInt("request_id");
                sellerId = rs.getInt("seller_id");
                sellPrice = rs.getInt("sell_price");
                datePlaced = rs.getString("dateplaced");
                status = rs.getInt("status");
                
                r = new Response(DataFacade.getInstance().getRequest(requestId), sellerId, datePlaced, sellPrice);
            }
        }catch(SQLException e){
            throw new SystemErrorException(e.getMessage());
        }

        
        
        return r;
    }
    
    public void insertResponse(Response res) throws SystemErrorException{
       try {
            String query = "INSERT INTO `responses` (request_id, seller_id, dateplaced, sell_price) "
                        + "VALUES (?,?,?,?);";
            PreparedStatement p = conn.prepareStatement(query);
            p.setInt(1, res.getRequest().getRequestId());
            p.setInt(2, res.getSellerId());
            p.setString(3, res.getDatePlaced());
            p.setInt(4, res.getSellPrice());
            p.executeUpdate();
        } catch (SQLException e) {
            throw new SystemErrorException(e.getMessage());
        }
    }
    
    @Override
    public void deleteResponse(int requestId) throws NoSuchResponseException, SystemErrorException{
        String query = "DELETE FROM responses WHERE request_id = ?";
        
        try{
            PreparedStatement p = conn.prepareStatement(query);
            p.setInt(1, requestId);
            int deleted = p.executeUpdate();
            if(deleted == 0) throw new NoSuchResponseException();
        }catch(SQLException e){
            throw new SystemErrorException(e.getMessage());
        }
    }

}
