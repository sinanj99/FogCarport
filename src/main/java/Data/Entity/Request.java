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
    private int requestId;
    private int userId;
    private String datePlaced;
    private Carport carport;
    private ShippingAddress address;
    
    // for when a request is being created with specified input.
    public Request(ShippingAddress address, int userId, String datePlaced, Carport carport) {
        this.address = address;
        this.userId = userId;
        this.carport = carport;
        this.datePlaced = datePlaced;
    }

    // for when a request is received from the database. 
    public Request(int requestId, int userId, String datePlaced, Carport carport, ShippingAddress address) {
        this.requestId = requestId;
        this.userId = userId;
        this.datePlaced = datePlaced;
        this.carport = carport;
        this.address = address;
    }

    public int getRequestId() {
        return requestId;
    }
    
    

    public int getUserId() {
        return userId;
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
        return "Request{" + "user_id=" + userId + ", datePlaced=" + datePlaced + ", carport=" + carport + '}';
    }
    
    
}
