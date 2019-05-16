/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data.Mappers;

import Data.Database.DBConnector;
import Data.Entity.PersonalInfo;
import Data.Entity.User;
import Presentation.Exceptions.DuplicateException;
import Presentation.Exceptions.SystemErrorException;
import Presentation.Exceptions.UserNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;

/**
 *
 * @author sinanjasar
 */
class UserMapper extends IUserMapper {

    private static UserMapper instance;
    private final DBConnector con = new DBConnector();
    Connection conn;

    @Override
    public void setDataSource(DataSource ds)
    {
        con.setDataSource(ds);
        conn = con.getConnection();
    }
    
    public static UserMapper getInstance() {
        if (instance == null) {
            instance = new UserMapper();
        }
        return instance;
    }

    @Override
    public void insertUser(User user) throws DuplicateException, SystemErrorException {
        try {
            conn.setAutoCommit(false);
            String sql = "INSERT INTO `users`(email, password) "
                    + "VALUES(?, ?);";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, user.getEmail());
            pstmt.setString(2, user.getPassword());
            pstmt.executeUpdate();

            sql = "INSERT INTO `users_personalinfo`(user_id, firstname, lastname, address, zipcode, city, gender) "
                    + "values ((SELECT user_id FROM `users` WHERE email = '" + user.getEmail() + "' ORDER BY user_id DESC LIMIT 1),?,?,?,?,?,?);";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, user.getInfo().getFirstname());
            pstmt.setString(2, user.getInfo().getLastname());
            pstmt.setString(3, user.getInfo().getAddress());
            pstmt.setInt(4, user.getInfo().getZipcode());
            pstmt.setString(5, user.getInfo().getCity());
            pstmt.setString(6, user.getInfo().getGender());
            pstmt.executeUpdate();
            conn.commit();

        } catch (SQLException e) {
            try {
                conn.rollback();
            } catch (SQLException ex) {
                throw new SystemErrorException(ex.getMessage());
            }
            if (e.getMessage().toLowerCase().startsWith("duplicate entry")) {
                throw new DuplicateException("Email optaget!");
            } else {
                throw new SystemErrorException(e.getMessage());
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
    public User getUser(String email) throws UserNotFoundException, SystemErrorException {
        int user_id = 0, zip = 0, seller = 0, admin = 0;
        String password = "", fname = "", lname = "", address = "", city = "", gender = "";
        boolean seller_ = false, admin_ = false;
        try {
            String sql = "SELECT * FROM `users` LEFT JOIN `users_personalinfo` USING(user_id) WHERE email = ?;";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, email);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                user_id = rs.getInt("user_id");
                seller = rs.getInt("seller");
                admin = rs.getInt("admin");
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
            throw new SystemErrorException(e.getMessage());
        }
        if (seller == 1) {
            seller_ = true;
        } else if(admin == 1) {
            admin_ = true;
        }
        return new User(new PersonalInfo(fname, lname, address, zip, city, gender), user_id, seller_, admin_, email, password);
    }

    /**
     *
     * @param id
     * @return
     * @throws UserNotFoundException
     */
    @Override
    public User getUser(int id) throws UserNotFoundException, SystemErrorException {

        int zip = 0;
        String email_ = "";
        String password = "";
        String fname = "";
        String lname = "";
        String adress = "";
        String city = "";
        String gender = "";
        int seller = 0;
        int admin = 0;
        boolean seller_ = false;
        boolean admin_ = false;
        try {
            String sql = "SELECT * FROM `users` INNER JOIN `users_personalinfo` USING(user_id) WHERE user_id = ?;";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                seller = rs.getInt("seller");
                admin = rs.getInt("admin");
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
            throw new SystemErrorException(e.getMessage());
        }
        if (seller == 1) {
            seller_ = true;
        } else if(admin == 1){
            admin_ = true;
        }
        return new User(new PersonalInfo(fname, lname, adress, zip, city, gender), id, seller_, admin_, email_, password);
    }
}
