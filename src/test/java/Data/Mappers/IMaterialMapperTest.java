///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package Data.Mappers;
//
//import Data.Entity.Material;
//import Logic.Exceptions.NoSuchMaterialException;
//import java.util.List;
//import org.junit.After;
//import org.junit.AfterClass;
//import org.junit.Before;
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
//    public IMaterialMapperTest() {
//    }
//    
//    @BeforeClass
//    public static void setUpClass() {
//    }
//    
//    @AfterClass
//    public static void tearDownClass() {
//    }
//    
//    @Before
//    public void setUp() {
//    }
//    
//    @After
//    public void tearDown() {
//    }
//
//    @Test
//    public void testInstance() {
//        System.out.println("instance");
//        IMaterialMapper expResult = null;
//        IMaterialMapper result = IMaterialMapper.instance();
//        assertEquals(expResult, result);
//        fail("The test case is a prototype.");
//    }
//
//    @Test
//    public void testGetMaterial_int() throws Exception {
//        System.out.println("getMaterial");
//        int id = 0;
//        IMaterialMapper instance = new IMaterialMapperImpl();
//        String expResult = "";
//        String result = instance.getMaterial(id);
//        assertEquals(expResult, result);
//        fail("The test case is a prototype.");
//    }
//
//    @Test
//    public void testUpdateStockWithLength() {
//        System.out.println("updateStockWithLength");
//        int id = 0;
//        IMaterialMapper instance = new IMaterialMapperImpl();
//        instance.updateStockWithLength(id);
//        fail("The test case is a prototype.");
//    }
//
//    @Test
//    public void testUpdateStockNoLength() {
//        System.out.println("updateStockNoLength");
//        int id = 0;
//        IMaterialMapper instance = new IMaterialMapperImpl();
//        instance.updateStockNoLength(id);
//        fail("The test case is a prototype.");
//    }
//
//    @Test
//    public void testGetMaterialWithLength() {
//        System.out.println("getMaterialWithLength");
//        int id = 0;
//        int length = 0;
//        IMaterialMapper instance = new IMaterialMapperImpl();
//        Material expResult = null;
//        Material result = instance.getMaterialWithLength(id, length);
//        assertEquals(expResult, result);
//        fail("The test case is a prototype.");
//    }
//
//    @Test
//    public void testGetMaterialNoLength() {
//        System.out.println("getMaterialNoLength");
//        int id = 0;
//        IMaterialMapper instance = new IMaterialMapperImpl();
//        Material expResult = null;
//        Material result = instance.getMaterialNoLength(id);
//        assertEquals(expResult, result);
//        fail("The test case is a prototype.");
//    }
//
//    @Test
//    public void testInsertMaterialDim() {
//        System.out.println("insertMaterialDim");
//        int id = 0;
//        int length = 0;
//        int price = 0;
//        int stock = 0;
//        IMaterialMapper instance = new IMaterialMapperImpl();
//        instance.insertMaterialDim(id, length, price, stock);
//        fail("The test case is a prototype.");
//    }
//
//    @Test
//    public void testGetMaterial_String_int() throws Exception {
//        System.out.println("getMaterial");
//        String name = "";
//        int length = 0;
//        IMaterialMapper instance = new IMaterialMapperImpl();
//        Material expResult = null;
//        Material result = instance.getMaterial(name, length);
//        assertEquals(expResult, result);
//        fail("The test case is a prototype.");
//    }
//
//    @Test
//    public void testGetMaterial_() throws Exception {
//        System.out.println("getMaterial_");
//        String name = "";
//        IMaterialMapper instance = new IMaterialMapperImpl();
//        Material expResult = null;
//        Material result = instance.getMaterial_(name);
//        assertEquals(expResult, result);
//        fail("The test case is a prototype.");
//    }
//
//    @Test
//    public void testGetMaterials() {
//        System.out.println("getMaterials");
//        IMaterialMapper instance = new IMaterialMapperImpl();
//        List<Material> expResult = null;
//        List<Material> result = instance.getMaterials();
//        assertEquals(expResult, result);
//        fail("The test case is a prototype.");
//    }
//
//    @Test
//    public void testUpdatePrice() throws Exception {
//        System.out.println("updatePrice");
//        int price = 0;
//        int id = 0;
//        IMaterialMapper instance = new IMaterialMapperImpl();
//        instance.updatePrice(price, id);
//        fail("The test case is a prototype.");
//    }
//
//    @Test
//    public void testInsertMaterial() {
//        System.out.println("insertMaterial");
//        String name = "";
//        int length = 0;
//        String unit = "";
//        String description = "";
//        int price = 0;
//        IMaterialMapper instance = new IMaterialMapperImpl();
//        instance.insertMaterial(name, length, unit, description, price);
//        fail("The test case is a prototype.");
//    }
//
//    public class IMaterialMapperImpl extends IMaterialMapper {
//
//        public String getMaterial(int id) throws NoSuchMaterialException {
//            return "";
//        }
//
//        public void updateStockWithLength(int id) {
//        }
//
//        public void updateStockNoLength(int id) {
//        }
//
//        public Material getMaterialWithLength(int id, int length) {
//            return null;
//        }
//
//        public Material getMaterialNoLength(int id) {
//            return null;
//        }
//
//        public void insertMaterialDim(int id, int length, int price, int stock) {
//        }
//
//        public Material getMaterial(String name, int length) throws NoSuchMaterialException {
//            return null;
//        }
//
//        public Material getMaterial_(String name) throws NoSuchMaterialException {
//            return null;
//        }
//
//        public List<Material> getMaterials() {
//            return null;
//        }
//
//        public void updatePrice(int price, int id) throws NoSuchMaterialException {
//        }
//
//        public void insertMaterial(String name, int length, String unit, String description, int price) {
//        }
//    }
//    
//}
