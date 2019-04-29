/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data.Mappers;

import Data.Entity.Carport;
import Data.Entity.Request;
import Data.Entity.Roof;
import Data.Entity.Shed;


import Logic.Exceptions.NoSuchCarportException;
import Logic.Exceptions.NoSuchMaterialException;
import Logic.Exceptions.NoSuchRequestException;
import Logic.Exceptions.NoSuchRoofException;
import Logic.Exceptions.NoSuchShedException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Obaydah Mohamad
 */
public abstract class IRequestMapper {
    public static IRequestMapper instance() {
        return RequestMapper.getInstance();
    }
    
    public abstract Request getRequest(int id) throws NoSuchRequestException, NoSuchRoofException, NoSuchShedException;
    public abstract Carport getRequestCarport(int request_id) throws NoSuchRoofException, SQLException, NoSuchShedException;
    public abstract Shed getRequestShed(int request_id) throws NoSuchShedException;
    public abstract void insertRequest(Request req);
    public abstract void insertDimensions(int id, int length, int price);
    public abstract int getDimensionID(int roof_id, int length);
    public abstract int getDimensionPrice(int roof_id, int length);
    public abstract Roof getRoof(String name) throws NoSuchRoofException;
    public abstract Roof getRoof(int id) throws NoSuchRoofException;
    public abstract List<Roof> getRoofs(int rooftype) throws NoSuchRoofException;
    public abstract void updateRoofPrice(int roof_id, int price) throws NoSuchRoofException;
}