/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data.Mappers;

import Data.Entity.Material;
import Logic.Exceptions.NoSuchMaterialException;
import Logic.Exceptions.SystemErrorException;
import java.util.List;

/**
 *
 * @author sinanjasar
 */
public abstract class IMaterialMapper {
    //teknika 3
    public static IMaterialMapper instance() {
        return MaterialMapper.getInstance();
    }
    
    public abstract String getMaterial(int id) throws NoSuchMaterialException;
    
    public abstract void updateStockWithLength(int id, int length, int qty)throws SystemErrorException, IllegalArgumentException, NoSuchMaterialException;
    
    public abstract void updateStockNoLength(int id, int qty)throws SystemErrorException;
    
    public abstract Material getMaterialWithLength(int id, int length) throws NoSuchMaterialException, SystemErrorException;
    
    public abstract Material getMaterialNoLength(int id)throws SystemErrorException;
    
    public abstract void insertMaterialDim(int id, int length, int price, int stock)throws SystemErrorException;
    
    public abstract Material getMaterial(String name, int length) throws NoSuchMaterialException;
    
    public abstract Material getMaterial_(String name) throws NoSuchMaterialException;
    
    public abstract List<Material> getMaterials();
    
    /**
     * Returns a list of prices of all available lengths of a material with the specified id.
     * @param id of the material type
     * @return list of materials.
     */
    public abstract List<Integer> getMaterialLengthPrices(int id) throws SystemErrorException;
    
    /**
     * Updates price of all lengths of a material specified by id
     * @param price the new price of the material (shortest length)
     * @param id the id of the desired material
     * @throws SystemErrorException 
     */
    
    public abstract void updatePriceWithLength(int price, int id, int length) throws SystemErrorException;
    
    /**
     * Updates price of a no-length material specified by id. 
     * @param price the new price of the material
     * @param id the id of the material
     * @throws SystemErrorException 
     */
    public abstract void updatePriceNoLength(int price, int id) throws SystemErrorException;
    
    /**
     * Updates price of all length of a specific roof specified by id.
     * @param price new price of the roof
     * @param id
     * @throws SystemErrorException 
     */
    public abstract void updatePriceRoof(int price, int id) throws SystemErrorException;

    public abstract void insertMaterial(String name, int length, String unit, String description, int price);
}
