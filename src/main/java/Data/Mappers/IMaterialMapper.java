/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data.Mappers;

import Data.Entity.Material;
import Data.Entity.Roof;
import Presentation.Exceptions.NoSuchMaterialException;
import Presentation.Exceptions.NoSuchRoofException;
import Presentation.Exceptions.SystemErrorException;
import Presentation.Exceptions.InvalidInputException;
import java.util.LinkedHashMap;
import java.util.List;
import javax.sql.DataSource;

/**
 * Interface for MaterialMapper. Is public so that its javadoc can be published.
 * @author sinanjasar
 */
 public abstract class IMaterialMapper {
    
    
    protected static IMaterialMapper instance() {
        return MaterialMapper.getInstance();
    }
    /**
     * Sets datasource of mapper
     * @param ds the datasource
     */
    protected abstract void setDataSource(DataSource ds);

    /**
     * Substracts the qty parameter from material (with length) with specified
     * id.
     *
     * @param id of the material to be updated
     * @param length of the material to be updated
     * @param qty quantity to substract
     * @throws NoSuchMaterialException if there is no material with the
     * specified id.
     * @throws SystemErrorException if an sql-exception is thrown
     */
    protected abstract void updateStockWithLength(int id, int length, int qty)throws SystemErrorException, IllegalArgumentException, NoSuchMaterialException;
    /**
     * Substracts the qty parameter from material (without length) with
     * specified id.
     *
     * @param id of the material to be updated
     * @param qty quantity to substract
     * @throws NoSuchMaterialException if there is no material with the
     * specified id.
     * @throws SystemErrorException if an sql-exception is thrown.
     */
    protected abstract void updateStockFittings(int id, int qty)throws SystemErrorException, NoSuchMaterialException, IllegalArgumentException;
    
    /**
     * Fetches material with specified id from wood_materials and material_lengths tables in dB
     * @param id of the desired material
     * @param length of the desired material
     * @return a material with specified id
     * @throws NoSuchMaterialException if the material with specified id is not found
     * @throws SystemErrorException if an sql-exception is thrown
     */
    protected abstract Material getWoodMaterial(int id, int length) throws NoSuchMaterialException, SystemErrorException;
    /**
     * Fetches material with specified id from material_nolength table in dB
     * @param id of the desired material
     * @return a material with specified id
     * @throws SystemErrorException if an sql-exception is thrown
     * @throws NoSuchMaterialException if the material with specified id is not found
     */
    protected abstract Material getFitting(int id)throws SystemErrorException, NoSuchMaterialException;
    
    /**
     * Inserts material length, price and stock to table 'material_lengths' in database for a material with specified id.
     * Is used in DataInsertion.class where data is inserted to tables in a for loop.
     * @param id of the material
     * @param length length of the material
     * @param price price of the material
     * @param stock stock of the material
     * @throws SystemErrorException if an SQL-related exception occurs
     */
    protected abstract void insertMaterialDim(int id, int length, int price, int stock)throws SystemErrorException;
    /**
     * Fetches all materials from materials_with length and material_lengths tables in database.
     * @return a list of all materials in the database.
     * @throws SystemErrorException if an sql-exception occurs
     */
    protected abstract List<Material> getMaterials() throws SystemErrorException;
    /**
     * Returns a linked hash map including all lengths and their prices for a specified material
     * @param id of the material
     * @return linked hash map including all lengths and their prices.
     * @throws SystemErrorException if an sql-exception occurs 
     */
    protected abstract LinkedHashMap<Integer, Integer> getMaterialLengthPrices(int id) throws SystemErrorException, NoSuchMaterialException;
    
    /**
     * Updates price of all lengths of a material specified by id
     * @param id the id of the desired material
     * @throws SystemErrorException 
     * @throws NoSuchMaterialException if there is no material with given id 
     */
    
    protected abstract void updatePriceWithLength(LinkedHashMap<Integer, Integer> prices, int id) throws SystemErrorException, NoSuchMaterialException, InvalidInputException;
    
    /**
     * Updates price of a no-length material specified by id. 
     * @param price the new price of the material
     * @param id the id of the material
     * @throws SystemErrorException 
     * @throws Presentation.Exceptions.NoSuchMaterialException 
     */
    protected abstract void updatePriceFittings(int price, int id) throws SystemErrorException, NoSuchMaterialException;
    
    /**
     * Updates price of all lengths of a specific roof specified by id.
     * @param price new price of the roof
     * @param id the roof
     * @throws SystemErrorException 
     * @throws NoSuchRoofException 
     * @throws InvalidInputException 
     */
    protected abstract void updatePriceRoof(LinkedHashMap<Integer, Integer> prices, int id) throws SystemErrorException, NoSuchRoofException, InvalidInputException;
    /**
     * Fetches all lengths and the price for each length for a material specified by id.
     * @param id of the material whose lengths needs to be fetched
     * @return a hash map with length as key and its respective price as value
     * @throws SystemErrorException 
     */
    protected abstract LinkedHashMap<Integer, Integer> getRoofLengthPrices(int id) throws SystemErrorException;
    
    /**
     * Fetches all roofs with shortest length from dB.
     * @return a list of all roofs with shortest length; no id-duplicates.
     * @throws SystemErrorException if an sql-exception is thrown.
     */
    protected abstract List<Roof> getRoofs() throws SystemErrorException;
}
