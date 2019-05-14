///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package Data.Mappers;
//
//import DB.DataSourceMysqlTest;
//import Data.Database.DBConnector;
//import Logic.Exceptions.NoSuchMaterialException;
//import Logic.Exceptions.SystemErrorException;
//import java.io.BufferedReader;
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.sql.SQLException;
//import javax.sql.DataSource;
//import org.junit.BeforeClass;
//import org.junit.Test;
//import static org.junit.Assert.*;
//
///**
// *
// * @author sinanjasar
// */
//public class IMaterialMapperTest {
//    
//    private static String sqlStatements = "";
//    private static IMaterialMapper mapper;
//    private static DataSource ds = new DataSourceMysqlTest().getDataSource();
//            
//    public IMaterialMapperTest() {
//    }
//
//    @BeforeClass
//    public static void setUpClass() {
//        System.out.println("MYSQL Tests...");
//
//        BufferedReader sqlScript;
//        try {
//            //indlæser scriptet
//            sqlScript = new BufferedReader(new InputStreamReader(new FileInputStream("CarportTest.sql"), "UTF-8"));
//            System.out.println("test");
//
//            String sqlStatement;
//            //henter alle sql statements fra scriptet
//            while ((sqlStatement = sqlScript.readLine()) != null) {
//                sqlStatements += sqlStatement + "\n";
//            }
//            System.out.println(sqlStatements);
//
//            //henter DBCOnnector, ændrer URL til testDB
//            DBConnector con = new DBConnector();
//            //udfører alle statements (genstarter db-data)
//            con.setDataSource(ds);
//            con.getConnection();
//            con.preparedStatement(sqlStatements).executeUpdate();
//
//        } catch (SQLException | IOException e) {
//            System.out.println(e.getMessage());
//        }
//        //materialMapper.instance returns the connection, which is already established (singleton)
//        mapper = IMaterialMapper.instance();
//        mapper.setDataSource(ds);
//        
//    }
//
//    @Test
//    public void testUpdateStockWithLength() throws SystemErrorException  {
//        System.out.println("updateStockWithLength");
//        int id = 1;
//        int length = 270;
//        int qty = 10;
//        
//        mapper.updateStockWithLength(id, length, qty);
//        
//        int expResult = 990;
//        int result = mapper.getMaterialWithLength(id,length).getStock();
//        assertEquals(expResult, result); 
//    }
//    
//    @Test(expected = IllegalArgumentException.class)
//    public void testUpdateStockWithLengthNegative() throws SystemErrorException {
//        System.out.println("updateStockWithLength");
//        int id = 1;
//        int length = 270;
//        int qty = -10;
//        
//        mapper.updateStockWithLength(id, length, qty);
//
//    }
//    @Test(expected = NoSuchMaterialException.class)
//    public void testUpdateStockWithLengthFail() throws SystemErrorException {
//        System.out.println("updateStockWithLength");
//        int id = 2;
//        int length = 270;
//        int qty = 10;
//        
//        mapper.updateStockWithLength(id, length, qty);
//    }
//
//    @Test
//    public void testUpdateStockNoLength() throws SystemErrorException {
//        System.out.println("updateStockNoLength");
//        int id = 1;
//        int qty = 10;
//        
//        mapper.updateStockNoLength(id, qty);
//        
//        int expResult = 990;
//        int result = mapper.getMaterialNoLength(id).getStock();
//        
//        assertEquals(expResult, result);
//    }
//    
//    @Test(expected = IllegalArgumentException.class)
//    public void testUpdateStockNoLengthNegative() throws SystemErrorException {
//        System.out.println("updateStockNoLength");
//        int id = 1;
//        int qty = -10;
//        
//        mapper.updateStockNoLength(id, qty);
//    }
//    @Test(expected = NoSuchMaterialException.class)
//    public void testUpdateStockNoLengthFail() throws SystemErrorException {
//        System.out.println("updateStockNoLength");
//        int id = 2;
//        int qty = 10;
//        
//        mapper.updateStockNoLength(id, qty);
//    }
//    @Test
//    public void testUpdatePriceWithLength() throws SystemErrorException {
//        System.out.println("updatePriceWithLength");
//        mapper.updatePriceWithLength(990, 1);
//    }
//}