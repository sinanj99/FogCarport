/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data.Mappers;

import Data.Entity.Material;
import Logic.Exceptions.NoSuchMaterialException;
import Logic.Exceptions.NoSuchRoofException;
import Logic.Exceptions.SystemErrorException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import javax.sql.DataSource;

/**
 *
 * @author sinanjasar
 */
public abstract class IMaterialMapper {
    
    
    public static IMaterialMapper instance() {
        return MaterialMapper.getInstance();
    }
    
    public abstract void setDataSource(DataSource ds);
    
    public abstract String getMaterial(int id) throws NoSuchMaterialException;
    
    public abstract void updateStockWithLength(int id, int length, int qty)throws SystemErrorException, IllegalArgumentException, NoSuchMaterialException;
    
    public abstract void updateStockNoLength(int id, int qty)throws SystemErrorException, NoSuchMaterialException, IllegalArgumentException;
    
    public abstract Material getMaterialWithLength(int id, int length) throws NoSuchMaterialException, SystemErrorException;
    
    public abstract Material getMaterialNoLength(int id)throws SystemErrorException, NoSuchMaterialException;
    
    public abstract void insertMaterialDim(int id, int length, int price, int stock)throws SystemErrorException;
    
    public abstract Material getMaterial(String name, int length) throws NoSuchMaterialException;
    
    public abstract Material getMaterial_(String name) throws NoSuchMaterialException;
    
    public abstract List<Material> getMaterials();
    /**
     * Returns a linked hash map including all lengths and its prices.
     * @param id
     * @return linked hash map including all lengths and its prices.
     * @throws SystemErrorException 
     */
    public abstract LinkedHashMap<Integer, Integer> getMaterialLengthPrices(int id) throws SystemErrorException;
    
    /**
     * Updates price of all lengths of a material specified by id
     * @param price the new price of the material (shortest length)
     * @param id the id of the desired material
     * @throws SystemErrorException 
     * @throws Logic.Exceptions.NoSuchMaterialException 
     */
    
    public abstract void updatePriceWithLength(int price, int id) throws SystemErrorException, NoSuchMaterialException;
    
    /**
     * Updates price of a no-length material specified by id. 
     * @param price the new price of the material
     * @param id the id of the material
     * @throws SystemErrorException 
     * @throws Logic.Exceptions.NoSuchMaterialException 
     */
    public abstract void updatePriceNoLength(int price, int id) throws SystemErrorException, NoSuchMaterialException;
    
    /**
     * Updates price of all length of a specific roof specified by id.
     * @param price new price of the roof
     * @param id
     * @throws SystemErrorException 
     * @throws Logic.Exceptions.NoSuchRoofException 
     */
    public abstract void updatePriceRoof(int price, int id) throws SystemErrorException, NoSuchRoofException;

    public abstract LinkedHashMap<Integer, Integer> getRoofLengthPrices(int id) throws SystemErrorException;
    
    public abstract void insertMaterial(String name, int length, String unit, String description, int price);
}
