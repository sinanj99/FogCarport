/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data.Mappers;

import Data.Database.DBConnector;
import Data.Entity.Response;
import Presentation.Exceptions.NoSuchResponseException;
import Presentation.Exceptions.SystemErrorException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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
    protected void setDataSource(DataSource ds) {
        con.setDataSource(ds);
        conn = con.getConnection();
    }
    
    protected synchronized static ResponseMapper getInstance() {
        if (instance == null) {
            instance = new ResponseMapper();
        }
        return instance;
    }
    
    
    @Override
    protected List<Response> getResponses(int userId) throws SystemErrorException{
        List<Response> responses = new ArrayList<Response>();
        
        int requestId = 0;
        int sellerId = 0;
        int sellPrice = 0;
        int status = 0;
        String datePlaced = "";

        try{

            String query = "SELECT responses.request_id, seller_id, responses.dateplaced, sell_price, status "
                        + "FROM responses LEFT JOIN requests USING(request_id) "
                        + "WHERE user_id = ? "
                        + "ORDER BY responses.dateplaced DESC";
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
    protected Response getResponse(int requestId) throws NoSuchResponseException, SystemErrorException{
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
            }else{
                return null;
            }
        }catch(SQLException e){
            throw new SystemErrorException(e.getMessage());
        }

        
        
        return r;
    }
    
    @Override
    protected void insertResponse(Response res) throws SystemErrorException{
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
    protected void deleteResponse(int requestId) throws NoSuchResponseException, SystemErrorException{
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
