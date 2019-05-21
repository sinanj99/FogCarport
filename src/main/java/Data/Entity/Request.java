/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data.Entity;

/**
 * Mirrors requests table in database.
 * Is used in data mappers where a request is inserted/fetched. Is also used in presentation layer
 * where a request is created based on user-input or a request is fetched and displayed in jsp-file.
 * @author Obaydah Mohamad
 */
public class Request {
    private int req_id;
    private int user_id;
    private String datePlaced;
    private Carport carport;
    private ShippingAddress address;
    
    // for when a request is being created with specified input.
    public Request(ShippingAddress address, int user_id, String datePlaced, Carport carport) {
        this.address = address;
        this.user_id = user_id;
        this.carport = carport;
        this.datePlaced = datePlaced;
    }

    // for when a request is received from the database. 
    public Request(int req_id, int user_id, String datePlaced, Carport carport, ShippingAddress address) {
        this.req_id = req_id;
        this.user_id = user_id;
        this.datePlaced = datePlaced;
        this.carport = carport;
        this.address = address;
    }

    public int getReq_id() {
        return req_id;
    }
    
    

    public int getUser_id() {
        return user_id;
    }

    public Carport getCarport() {
        return carport;
    }

    public String getDatePlaced() {
        return datePlaced;
    }

    public ShippingAddress getAddress() {
        return address;
    }


    @Override
    public String toString() {
        return "Request{" + "user_id=" + user_id + ", datePlaced=" + datePlaced + ", carport=" + carport + '}';
    }
    
    
}
