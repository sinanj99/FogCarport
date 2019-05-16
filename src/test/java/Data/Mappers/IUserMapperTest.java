/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data.Mappers;

import DB.DataSourceMysqlTest;
import Data.Database.DBConnector;
import Data.Entity.PersonalInfo;
import Data.Entity.User;
import Presentation.Exceptions.DuplicateException;
import Presentation.Exceptions.SystemErrorException;
import Presentation.Exceptions.UserNotFoundException;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import javax.sql.DataSource;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author sinanjasar
 */
public class IUserMapperTest {

    private static IUserMapper mapper;
    private static String sqlStatements = "";
    private static final DataSource ds = new DataSourceMysqlTest().getDataSource();

    public IUserMapperTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        System.out.println("MYSQL Tests...");

        BufferedReader sqlScript;
        try {
            //indlæser scriptet
            sqlScript = new BufferedReader(new InputStreamReader(new FileInputStream("CarportTest.sql"), "UTF-8"));
            System.out.println("test");

            String sqlStatement;
            //henter alle sql statements fra scriptet
            while ((sqlStatement = sqlScript.readLine()) != null) {
                sqlStatements += sqlStatement + "\n";
            }
            System.out.println(sqlStatements);

            //henter DBCOnnector, ændrer URL til testDB
            DBConnector con = new DBConnector();
            //udfører alle statements (genstarter db-data)
            con.setDataSource(new DataSourceMysqlTest().getDataSource());
            con.getConnection();
            con.preparedStatement(sqlStatements).executeUpdate();

        } catch (SQLException | IOException e) {
            System.out.println(e.getMessage());
        }

        mapper = IUserMapper.instance();
        mapper.setDataSource(ds);
    }

    @Test
    public void testInsertUser() throws SystemErrorException, DuplicateException, UserNotFoundException {
        System.out.println("insertUser");
        User user = new User(new PersonalInfo("Sinan", "Jasar", "Lillemosevej 27", 2800, "Kgs. Lyngby", "m"), 3, false, false, "sinanjasar@live.dk", "adgangskode");
        IUserMapper instance = IUserMapper.instance();
        instance.insertUser(user);
        User result = instance.getUser("sinanjasar@live.dk");
        assertEquals(result.getId(), user.getId());
        assertEquals(result.getInfo().getFirstname(), user.getInfo().getFirstname());
        assertEquals(result.getInfo().getLastname(), user.getInfo().getLastname());
        assertEquals(result.getPassword(), user.getPassword());
    }

    @Test(expected = DuplicateException.class)
    public void testInsertUserFail() throws Exception {
        System.out.println("insertUserFail");
        User user = new User(new PersonalInfo("Peter", "Petersen", "Lillemosevej 27", 2800, "Kgs. Lyngby", "m"), 2, false, false, "test@test.dk", "adgangskode");
        mapper.insertUser(user);

    }

    @Test
    public void testGetUser_String() throws SystemErrorException, UserNotFoundException {
        System.out.println("getUser");
        String email = "test@test.dk";

        User user = mapper.getUser(email);
        String expResult = "1 Peter Petersen test";
        String result = String.valueOf(user.getId()) + " "
                + user.getInfo().getFirstname() + " " + user.getInfo().getLastname()
                + " " + user.getPassword();
        assertEquals(expResult, result);
    }

    @Test(expected = UserNotFoundException.class)
    public void testGetUser_StringFail() throws Exception {
        System.out.println("getUserFail");
        String email = "terlix@test.dk";

        mapper.getUser(email);
    }

    @Test
    public void testGetUser_int() throws SystemErrorException, UserNotFoundException {
        System.out.println("getUser");
        int id = 1;
        User user = mapper.getUser(id);
        String expResult = "1 Peter Petersen test";
        String result = String.valueOf(user.getId()) + " "
                + user.getInfo().getFirstname() + " " + user.getInfo().getLastname()
                + " " + user.getPassword();
        assertEquals(expResult, result);
    }

    @Test(expected = UserNotFoundException.class)
    public void testGetUser_intFail() throws Exception {
        System.out.println("getUserFail");
        int id = 21424124;
        mapper.getUser(id);
    }

}
