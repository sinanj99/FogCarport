/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data.Mappers;

import Data.Database.DBConnector;
import Data.Entity.PersonalInfo;
import Data.Entity.User;
import Logic.Exceptions.DuplicateException;
import Logic.Exceptions.NoSuchMaterialException;
import Logic.Exceptions.UserNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sinanjasar
 */
public class UserMapper extends IUserMapper {

    private static UserMapper instance;
    static Connection con = DBConnector.getConnection();

    public static UserMapper getInstance() {
        if (instance == null) {
            instance = new UserMapper();
        }
        return instance;
    }

    @Override
    public void insertUser(User user) throws DuplicateException, SQLException {
        try {
            String sql = "INSERT INTO `users`(email, password) "
                    + "VALUES(?, ?);";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, user.getEmail());
            pstmt.setString(2, user.getPassword());
            pstmt.executeUpdate();

            sql = "INSERT INTO `users_personalinfo`(user_id, firstname, lastname, address, zipcode, city, gender) "
                    + "values ((SELECT user_id FROM `users` WHERE email = '" + user.getEmail() + "' ORDER BY user_id DESC LIMIT 1),?,?,?,?,?,?);";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, user.getInfo().getFirstname());
            pstmt.setString(2, user.getInfo().getLastname());
            pstmt.setString(3, user.getInfo().getAddress());
            pstmt.setInt(4, user.getInfo().getZipcode());
            pstmt.setString(5, user.getInfo().getCity());
            pstmt.setString(6, user.getInfo().getGender());
            pstmt.executeUpdate();

        } catch (SQLException e) {
            if (e.getMessage().toLowerCase().startsWith("duplicate entry")) {
                throw new DuplicateException("Email optaget!");
            } else {
                throw new SQLException();
            }
        }
    }

    @Override
    public User getUser(String email) throws SQLException, UserNotFoundException {
        int user_id, zip = 0;
        String email_ = "";
        String password = "";
        String fname = ""; 
        String lname = "";
        String adress ="";
        String city = "";
        String gender = "";
        
        String sql = "SELECT * FROM `users` WHERE email = ?;";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setString(1, email);
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            user_id = rs.getInt("user_id");
            email_ = rs.getString("email");
            password = rs.getString("password");
        } else {
            throw new UserNotFoundException("Bruger findes ikke!");
        }
        sql = "SELECT * FROM `users_personalinfo` WHERE user_id = (SELECT user_id FROM `users` WHERE email = '" + email + "' ORDER BY user_id DESC LIMIT 1);";
        
        pstmt = con.prepareStatement(sql);
        
        rs = pstmt.executeQuery();
        
        if(rs.next()) {
            fname = rs.getString("firstname");
            lname = rs.getString("lastname");
            adress = rs.getString("address");
            zip = rs.getInt("zipcode");
            city = rs.getString("city");
            gender = rs.getString("gender");
        }
        return new User(new PersonalInfo(fname, lname, adress, zip, city, gender), user_id, email_, password);
    }
    
    /**
     *
     * @param id
     * @return
     * @throws SQLException
     * @throws UserNotFoundException
     */
    @Override
    public User getUser(int id) throws SQLException, UserNotFoundException {
        
        int user_id, zip = 0;
        String email_ = "";
        String password = ""; 
        String fname = ""; 
        String lname = "";
        String adress ="";
        String city = "";
        String gender = "";
        
        String sql = "SELECT * FROM `users` WHERE user_id = ?;";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setInt(1, id);
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            user_id = rs.getInt("user_id");
            email_ = rs.getString("email");
            password = rs.getString("password");
        } else {
            throw new UserNotFoundException("Bruger findes ikke!");
        }
        
        sql = "SELECT * FROM `users_personalinfo` WHERE user_id = " + id + ";";
        
        pstmt = con.prepareStatement(sql);
        rs = pstmt.executeQuery();
        if(rs.next()) {
            fname = rs.getString("firstname");
            lname = rs.getString("lastname");
            adress = rs.getString("address");
            zip = rs.getInt("zipcode");
            city = rs.getString("city");
            gender = rs.getString("gender");
        }
        return new User(new PersonalInfo(fname, lname, adress, zip, city, gender), user_id, email_, password);
    }

    public static void main(String[] args) throws UserNotFoundException, SQLException {

        try {
            System.out.println(IUserMapper.instance().getUser("test@test.dk").getInfo());
            System.out.println(IUserMapper.instance().getUser(2).getInfo().getFirstname());
        } catch (UserNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
    }

}
