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
    private static final DBConnector connector = new DBConnector();
    private Connection con = DBConnector.getConnection();
    
    @Override
    public void getTestConnection() {
        con = DBConnector.getTestConnection();
    }
    
    public static UserMapper getInstance() {
        if (instance == null) {
            instance = new UserMapper();
        }
        return instance;
    }

    @Override
    public void insertUser(User user) throws DuplicateException {
        try {
            con.setAutoCommit(false);
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
            con.commit();

        } catch (SQLException e) {
            try {
                con.rollback();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
            if (e.getMessage().toLowerCase().startsWith("duplicate entry")) {
                throw new DuplicateException("Email optaget!");
            } else {
                System.out.println("User could not be inserted: " + e.getMessage());
            }
        }
    }

    /**
     * Used when user logs in with specified email.
     *
     * @param email
     * @return
     * @throws UserNotFoundException
     */
    @Override
    public User getUser(String email) throws UserNotFoundException {
        int user_id = 0, zip = 0, seller = 0;
        String password = "", fname = "", lname = "", address = "", city = "", gender = "";
        boolean seller_ = false;
        try {
            String sql = "SELECT * FROM `users` INNER JOIN `users_personalinfo` USING(user_id) WHERE email = ?;";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, email);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                user_id = rs.getInt("user_id");
                seller = rs.getInt("seller");
                password = rs.getString("password");
                fname = rs.getString("firstname");
                lname = rs.getString("lastname");
                address = rs.getString("address");
                zip = rs.getInt("zipcode");
                city = rs.getString("city");
                gender = rs.getString("gender");
            } else {
                throw new UserNotFoundException("Bruger findes ikke!");
            }
        } catch (SQLException e) {
            System.out.println("An error occured fetching user from DB: " + e.getMessage());
        }
        if (seller == 1) {
            seller_ = true;
        }
        return new User(new PersonalInfo(fname, lname, address, zip, city, gender), user_id, seller_, email, password);
    }

    /**
     *
     * @param id
     * @return
     * @throws UserNotFoundException
     */
    @Override
    public User getUser(int id) throws UserNotFoundException {

        int zip = 0;
        String email_ = "";
        String password = "";
        String fname = "";
        String lname = "";
        String adress = "";
        String city = "";
        String gender = "";
        int seller = 0;
        boolean seller_ = false;
        try {
            String sql = "SELECT * FROM `users` INNER JOIN `users_personalinfo` USING(user_id) WHERE user_id = ?;";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                seller = rs.getInt("seller");
                email_ = rs.getString("email");
                password = rs.getString("password");
                fname = rs.getString("firstname");
                lname = rs.getString("lastname");
                adress = rs.getString("address");
                zip = rs.getInt("zipcode");
                city = rs.getString("city");
                gender = rs.getString("gender");
            } else {
                throw new UserNotFoundException("Bruger findes ikke!");
            }
        } catch (SQLException e) {
            System.out.println("An error occured trying to fetch data from user table: " + e.getMessage());
        }
        if (seller == 1) {
            seller_ = true;
        }
        return new User(new PersonalInfo(fname, lname, adress, zip, city, gender), id, seller_, email_, password);
    }

    public static void main(String[] args) throws UserNotFoundException {

        try {
            System.out.println(IUserMapper.instance().getUser("test@test.dk").getInfo());
            System.out.println(IUserMapper.instance().getUser(2).getInfo().getFirstname());
        } catch (UserNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
    }

}
