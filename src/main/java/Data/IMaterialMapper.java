/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import Logic.NoSuchMaterialException;
import java.util.List;

/**
 *
 * @author sinanjasar
 */
public abstract class IMaterialMapper {

    public static IMaterialMapper instance() {
        return MaterialMapper.getInstance();
    }
    public abstract Material getMaterial(String name, int length) throws NoSuchMaterialException;
    
    public abstract Material getMaterial_(String name) throws NoSuchMaterialException;
    
    public abstract List<Material> getMaterials();
    
    public abstract void updatePrice(int price, int id) throws NoSuchMaterialException;
    
    public abstract void insertMaterial(String name, int length, String unit, String description, int price);
}
