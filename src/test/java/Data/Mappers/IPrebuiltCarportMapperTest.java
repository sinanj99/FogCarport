/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data.Mappers;

import DB.DataSourceMysqlTest;
import Data.Database.DBConnector;
import Data.Entity.PrebuiltCarport;
import Presentation.Exceptions.SystemErrorException;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
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
public class IPrebuiltCarportMapperTest {
    
    private static IPrebuiltCarportMapper mapper;
    private static String sqlStatements = "";
    private static final DataSource ds = new DataSourceMysqlTest().getDataSource();
    
    public IPrebuiltCarportMapperTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        System.out.println("MYSQL Tests...");

        BufferedReader sqlScript;
        try {
            //indlæser scriptet
            sqlScript = new BufferedReader(new InputStreamReader(new FileInputStream("CarportTestScript.sql"), "UTF-8"));
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

        mapper = IPrebuiltCarportMapper.instance();
        mapper.setDataSource(ds);
    }
    
    @Test
    public void testGetAllPrebuiltCarports() throws SystemErrorException {
        System.out.println("getAllPrebuiltCarports");
        IPrebuiltCarportMapper instance = IPrebuiltCarportMapper.instance();
        List<PrebuiltCarport> list  = instance.getAllPrebuiltCarports();
        PrebuiltCarport p;
        int expResult = 11;
        int result = list.size();
        assertEquals(expResult, result);
        
        /*
        prebuilt_carport_id int(11) AI PK 
        img_path varchar(1000) 
        carport_width int(3) 
        carport_length int(3) 
        price
        ("/project/images/abc.png",450,750,true,420,180,17779),
        ("/project/images/carport2.png",360,540,false,0,0,13998),
        ("/project/images/carport3.png",360,720,true,330,220,21498),
        ("/project/images/carport4.png",390,750,true,240,330,23498),
        ("/project/images/carport5.png",300,480,false,0,0,3998),
        ("/project/images/carport6.png",600,750,true,210,510,31998),
        ("/project/images/carport7.png",600,540,true,270,240,13798),
        ("/project/images/carport8.png",600,480,false,0,0,10498),
        ("/project/images/carport9.jpg",630,540,true,570,150,15798),
        ("/project/images/carport10.jpg",420,480,false,0,0,5798),
        ("/project/images/carport10.jpg",420,480,false,0,0,5798);
        */
        p = list.get(10);
        assertEquals("/project/images/carport10.jpg", p.getImgPpath());
        assertEquals(420, p.getCarportWidth());
        assertEquals(480, p.getCarportLength());
        assertEquals(5798, p.getPrice());
        
        p = list.get(9);
        assertEquals("/project/images/carport10.jpg", p.getImgPpath());
        assertEquals(420, p.getCarportWidth());
        assertEquals(480, p.getCarportLength());
        assertEquals(5798, p.getPrice());
        
        p = list.get(8);
        assertEquals("/project/images/carport9.jpg", p.getImgPpath());
        assertEquals(630, p.getCarportWidth());
        assertEquals(540, p.getCarportLength());
        assertEquals(15798, p.getPrice());
        
        p = list.get(7);
        assertEquals("/project/images/carport8.png", p.getImgPpath());
        assertEquals(600, p.getCarportWidth());
        assertEquals(480, p.getCarportLength());
        assertEquals(10498, p.getPrice());
        
        p = list.get(6);
        assertEquals("/project/images/carport7.png", p.getImgPpath());
        assertEquals(600, p.getCarportWidth());
        assertEquals(540, p.getCarportLength());
        assertEquals(13798, p.getPrice());
        
        p = list.get(5);
        assertEquals("/project/images/carport6.png", p.getImgPpath());
        assertEquals(600, p.getCarportWidth());
        assertEquals(750, p.getCarportLength());
        assertEquals(31998, p.getPrice());
        
        p = list.get(4);
        assertEquals("/project/images/carport5.png", p.getImgPpath());
        assertEquals(300, p.getCarportWidth());
        assertEquals(480, p.getCarportLength());
        assertEquals(3998, p.getPrice());
        
        p = list.get(3);
        assertEquals("/project/images/carport4.png", p.getImgPpath());
        assertEquals(390, p.getCarportWidth());
        assertEquals(750, p.getCarportLength());
        assertEquals(23498, p.getPrice());
        
        p = list.get(2);
        assertEquals("/project/images/carport3.png", p.getImgPpath());
        assertEquals(360, p.getCarportWidth());
        assertEquals(720, p.getCarportLength());
        assertEquals(21498, p.getPrice());
        
        p = list.get(1);
        assertEquals("/project/images/carport2.png", p.getImgPpath());
        assertEquals(360, p.getCarportWidth());
        assertEquals(540, p.getCarportLength());
        assertEquals(13998, p.getPrice());
        
        p = list.get(0);
        assertEquals("/project/images/abc.png", p.getImgPpath());
        assertEquals(450, p.getCarportWidth());
        assertEquals(750, p.getCarportLength());
        assertEquals(17779, p.getPrice());
        
        
    }
}
