/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data.Mappers;

import DB.DataSourceMysqlTest;
import Data.Database.DBConnector;
import Presentation.Exceptions.NoSuchMaterialException;
import Presentation.Exceptions.NoSuchRoofException;
import Presentation.Exceptions.SystemErrorException;
import Presentation.Exceptions.InvalidInputException;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Map;
import javax.sql.DataSource;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author sinanjasar
 */
public class IMaterialMapperTest {
    
    private static String sqlStatements = "";
    private static IMaterialMapper mapper;
    private static final DataSource ds = new DataSourceMysqlTest().getDataSource();
            
    public IMaterialMapperTest() {
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
            con.setDataSource(ds);
            con.getConnection();
            con.preparedStatement(sqlStatements).executeUpdate();

        } catch (SQLException | IOException e) {
            System.out.println(e.getMessage());
        }
        //materialMapper.instance returns the connection, which is already established (singleton)
        mapper = IMaterialMapper.instance();
        mapper.setDataSource(ds);
    }

    @Test
    public void testUpdateStockWithLength() throws SystemErrorException, IllegalArgumentException, NoSuchMaterialException  {
        System.out.println("updateStockWithLength");
        int id = 1;
        int length = 270;
        int qty = 10;
        
        mapper.updateStockWithLength(id, length, qty);
        
        int expResult = 990;
        int result = mapper.getWoodMaterial(id,length).getStock();
        assertEquals(expResult, result); 
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testUpdateStockWithLengthNegative() throws SystemErrorException, IllegalArgumentException, NoSuchMaterialException {
        System.out.println("updateStockWithLength");
        int id = 1;
        int length = 270;
        int qty = -10;
        
        mapper.updateStockWithLength(id, length, qty);

    }
    @Test(expected = NoSuchMaterialException.class)
    public void testUpdateStockWithLengthFail() throws SystemErrorException, IllegalArgumentException, NoSuchMaterialException {
        System.out.println("updateStockWithLength");
        int id = 2;
        int length = 270;
        int qty = 10;
        
        mapper.updateStockWithLength(id, length, qty);
    }

    @Test
    public void testUpdateStockNoLength() throws SystemErrorException, NoSuchMaterialException {
        System.out.println("updateStockNoLength");
        int id = 1;
        int qty = 10;
        
        mapper.updateStockNoLength(id, qty);
        
        int expResult = 990;
        int result = mapper.getFitting(id).getStock();
        
        assertEquals(expResult, result);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testUpdateStockNoLengthNegative() throws SystemErrorException, NoSuchMaterialException, NoSuchMaterialException {
        System.out.println("updateStockNoLength");
        int id = 1;
        int qty = -10;
        
        mapper.updateStockNoLength(id, qty);
    }
    @Test(expected = NoSuchMaterialException.class)
    public void testUpdateStockNoLengthFail() throws SystemErrorException, NoSuchMaterialException {
        System.out.println("updateStockNoLength");
        int id = 2;
        int qty = 10;
        
        mapper.updateStockNoLength(id, qty);
    }
    @Test
    public void testUpdatePriceWithLength() throws SystemErrorException, NoSuchMaterialException, InvalidInputException {
        System.out.println("updatePriceWithLength");
        int price = 990;
        int id = 1;
        
        
        mapper.updatePriceWithLength(price, id);
        
        Iterator<Map.Entry<Integer, Integer>> it = mapper.getMaterialLengthPrices(id).entrySet().iterator();
        
        //increments with 100 for each length
        
        int expResult = 990;
        int result = it.next().getValue();
        assertEquals(expResult, result);
        
        expResult = 1090;
        result = it.next().getValue();
        assertEquals(expResult, result);
    }
    @Test(expected = NoSuchMaterialException.class)
    public void testUpdatePriceWithLengthFail1() throws SystemErrorException, InvalidInputException, NoSuchMaterialException {
        System.out.println("updatePriceWithLengthFail1");
        mapper.updatePriceWithLength(321412, 10);
    }
    @Test
    public void testUpdatePriceNoLength() throws SystemErrorException, NoSuchMaterialException {
        System.out.println("updatePriceNoLength");
        int price = 990;
        int id = 1;
        
        mapper.updatePriceFittings(price, id);
        
        int expResult = 990;
        int result = mapper.getFitting(id).getPrice();

        assertEquals(expResult, result);
    }
    @Test
    public void testUpdatePriceRoof() throws SystemErrorException, NoSuchRoofException, InvalidInputException {
        System.out.println("updatePriceRoof");
        int price = 990;
        int id = 1;
        
        
        mapper.updatePriceRoof(price, id);
        
        Iterator<Map.Entry<Integer, Integer>> it = mapper.getRoofLengthPrices(id).entrySet().iterator();
        
        //increments with 100 for each length
        
        int expResult = 990;
        int result = it.next().getValue();
        assertEquals(expResult, result);
        
        expResult = 1090;
        result = it.next().getValue();
        assertEquals(expResult, result);
    }
}