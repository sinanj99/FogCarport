/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import Logic.NoSuchCarportException;
import Logic.NoSuchMaterialException;
import Logic.NoSuchRequestException;
import Logic.NoSuchRoofException;
import Logic.NoSuchShedException;
import java.util.List;

/**
 *
 * @author Obaydah Mohamad
 */
public abstract class IRequestMapper {
    public static IRequestMapper instance() {
        return RequestMapper.getInstance();
    }
    
    public abstract Request getRequest(int id) throws NoSuchRequestException;
    public abstract Carport getRequestCarport(int request_id) throws NoSuchCarportException;
    public abstract Shed getRequestShed(int request_id) throws NoSuchShedException;
    public abstract void insertRequest(Request req);
    public abstract void insertRequestCarport(int request_id, int roof_id, boolean inclined, int width, int length, boolean shed);    
    public abstract void insertRequestShed(int request_id, int shedWidth, int shedLength);        
    public abstract Roof getRoof(String name) throws NoSuchRoofException;
    public abstract List<Roof> getRoofs(int rooftype) throws NoSuchRoofException;
    public abstract void updateRoofPrice(int roof_id, int price) throws NoSuchRoofException;
    public abstract void insertRoof(String name, int price, boolean inclined);
    public abstract void insertDimensions(int id, int length);
}
