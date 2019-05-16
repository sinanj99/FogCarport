/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data.Mappers;

import Data.Database.DBConnector;
import Data.Database.DataSourceMysql;
import Data.Entity.Response;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
    public Response getResponse(int requestId) {
        Response r = null;
        int responseId = 0;
        int reqId = 0;
        int userId = 0;
        int empId = 0;
        int carportId = 0;
        int shedId = 0;
        int productionPrice = 0;
        int sellPrice = 0;
        String datePlaced = "";
        int status = 0;
        
        try {
            String query = "SELECT * FROM responses WHERE request_id = ?;";
            PreparedStatement p = conn.prepareStatement(query);
            p.setInt(1, requestId);
            ResultSet rs = p.executeQuery();
            if(rs.next()){
                responseId = rs.getInt("response_id");
                reqId = rs.getInt("request_id");
                userId = rs.getInt("user_id");
                empId = rs.getInt("emp_id");
                carportId = rs.getInt("carport_id");
                shedId = rs.getInt("shed_id");
                productionPrice = rs.getInt("productionprice");
                sellPrice = rs.getInt("sellprice");
                datePlaced = rs.getString("dateplaced");
                status = rs.getInt("status");
                r = new Response(responseId, requestId, userId, empId, carportId, shedId, datePlaced, productionPrice, sellPrice, status);
            }
        }catch(SQLException e){
            System.out.println("There was an error fetching data from the response table: \n" + e.getMessage());
        }

        
        
        return r;
    }
    
    public void insertResponse(Response res) {
       try {

            String query = "INSERT INTO `responses` (request_id, user_id, emp_id, dateplaced, carport_id, shed_id, productionprice, sellprice, status) "
                        + "VALUES (?,?,?,?,?,?,?,?,?);";
            PreparedStatement p = conn.prepareStatement(query);
            p.setInt(1, res.getRequestId());
            p.setInt(2, res.getUserId());
            p.setInt(3, res.getEmpId());
            p.setString(4, res.getDatePlaced());
            p.setInt(5, res.getCarportId());
            if(res.getShedId() == 0){
                p.setNull(6, java.sql.Types.INTEGER);
            }else{
                p.setInt(6, res.getShedId());
            }
            p.setInt(7, res.getProductionPrice());
            p.setInt(8, res.getSellPrice());
            p.setInt(9, 1);
            p.executeUpdate();

        } catch (SQLException e) {
            System.out.println("RESPONSE TABLE: " + e.getMessage());
        }
    }
    
    
    @Override
    public List<Response> getResponses(int userId) {
        List<Response> responses = new ArrayList<Response>();
        
        int responseId = 0;
        int reqId = 0;
        int empId = 0;
        int carportId = 0;
        int shedId = 0;
        int productionPrice = 0;
        int sellPrice = 0;
        int status = 0;
        String datePlaced = "";
        
        try{
            String query = "SELECT * FROM responses WHERE user_id = ?";
            PreparedStatement p = conn.prepareStatement(query);
            p.setInt(1, userId);
            ResultSet rs = p.executeQuery();
            
            while(rs.next()){
                responseId = rs.getInt("response_id");
                reqId = rs.getInt("request_id");
                userId = rs.getInt("user_id");
                empId = rs.getInt("emp_id");
                carportId = rs.getInt("carport_id");
                shedId = rs.getInt("shed_id");
                productionPrice = rs.getInt("productionprice");
                sellPrice = rs.getInt("sellprice");
                datePlaced = rs.getString("dateplaced");
                status = rs.getInt("status");
                responses.add(new Response(responseId, reqId, userId, empId, carportId, shedId, datePlaced, productionPrice, sellPrice, status));
            }
            
        }catch(SQLException e){
            System.out.println("There was an error fetching data from the response table: \n" + e.getMessage());
        }
        
        return responses;
    }
    
    public static void main(String[] args) {
        IResponseMapper r = IResponseMapper.instance();
        r.setDataSource(new DataSourceMysql().getDataSource());
        for(Response rr : r.getResponses(1)){
            System.out.println(rr);
        }
    }

    
}