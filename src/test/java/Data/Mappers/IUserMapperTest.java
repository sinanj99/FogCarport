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
import Logic.Exceptions.UserNotFoundException;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.SQLException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author sinanjasar
 */
public class IUserMapperTest {

    private static UserMapper userMapper;

    private static String sqlStatements = "";

    public IUserMapperTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        System.out.println("MYSQL Tests...");

        BufferedReader sqlScript;
        try {
            //indlæser scriptet
            sqlScript = new BufferedReader(new InputStreamReader(new FileInputStream("CarportTest.sql"), "UTF-8"));

            String sqlStatement;

            //henter alle sql statements fra scriptet
            while ((sqlStatement = sqlScript.readLine()) != null) {
                sqlStatements += sqlStatement;
            }
            System.out.println(sqlStatements);
            //henter DBCOnnector, ændrer URL til testDB
            DBConnector con = new DBConnector();
            DBConnector.getTestConnection();

            //udfører alle statements (genstarter db-data)
            con.preparedStatement(sqlStatements).executeUpdate();

        } catch (SQLException | IOException e) {
            System.out.println(e.getMessage());
        }

        userMapper = new UserMapper();
        userMapper.getTestConnection();

    }

    @Test
    public void testInsertUser() throws Exception {
        System.out.println("insertUser");
        User user = new User(new PersonalInfo("Sinan", "Jasar", "Lillemosevej 27", 2800, "Kgs. Lyngby", "m"), "sinanjasar@live.dk", "adgangskode");
        IUserMapper instance = IUserMapper.instance();
        instance.insertUser(user);
        User expResult = instance.getUser("sinanjasar@live.dk");
        assertEquals(expResult, user);
    }

    @Test
    public void testGetUser_String() throws Exception {
        System.out.println("getUser");
        String email = "test@test.dk";
        IUserMapper instance = IUserMapper.instance();
        User user = instance.getUser(email);
        String expResult = "1 Peter Petersen test";
        String result = String.valueOf(user.getId()) + " "
                + user.getInfo().getFirstname() + " " + user.getInfo().getLastname()
                + " " + user.getPassword();
        assertEquals(expResult, result);
    }

    @Test
    public void testGetUser_int() throws Exception {
        System.out.println("getUser");
        int id = 0;
        User expResult = null;
        User result = IUserMapper.instance().getUser(id);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

}
