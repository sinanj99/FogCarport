/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import Logic.DuplicateException;
import Logic.NoSuchMaterialException;
import Logic.UserNotFoundException;
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
    public void insertClient(Client client) throws DuplicateException, SQLException {
        try {
            String sql = "INSERT INTO `users`(email, password) "
                    + "VALUES(?, ?);";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, client.getEmail());
            pstmt.setString(2, client.getPassword());
            pstmt.executeUpdate();

            sql = "INSERT INTO `users_personalinfo`(user_id, firstname, lastname, address, zipcode, city, gender) "
                    + "values ((SELECT user_id FROM `users` WHERE email = '" + client.getEmail() + "' ORDER BY user_id DESC LIMIT 1),?,?,?,?,?,?);";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, client.getInfo().getFirstname());
            pstmt.setString(2, client.getInfo().getLastname());
            pstmt.setString(3, client.getInfo().getAddress());
            pstmt.setInt(4, client.getInfo().getZipcode());
            pstmt.setString(5, client.getInfo().getCity());
            pstmt.setBoolean(6, client.getInfo().isGender());
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
        int user_id = 0;
        String email_ = "";
        String password = "";

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
        return new User(user_id, email, password);
    }

    @Override
    public User getUser(int id) throws SQLException, UserNotFoundException {
        int user_id = 0;
        String email_ = "";
        String password = "";
        int price = 0;
            String sql = "SELECT * FROM `users` WHERE user_id = ?;";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                user_id = rs.getInt("user_id");
                email_ = rs.getString("email");
                password = rs.getString("password");
            }
            System.out.println(new User(user_id, email_, password));
            if (new User(user_id, email_, password) == null) {
            throw new UserNotFoundException("Bruger findes ikke!");
        }

        return new User(user_id, email_, password);
    }

    public static void main(String[] args) throws UserNotFoundException, SQLException {

        try {
            System.out.println(IUserMapper.instance().getUser("john@gmail.com"));
            System.out.println(IUserMapper.instance().getUser(2));
        } catch (UserNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
    }

}
