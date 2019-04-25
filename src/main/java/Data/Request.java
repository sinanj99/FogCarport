/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import java.time.LocalDateTime;

/**
 *
 * @author Obaydah Mohamad
 */
public class Request {
    private int user_id;
    private String datePlaced;
    private String dateAccepted;
    private int price;
    private Carport carport;

    public Request(int user_id, String datePlaced, String dateAccepted, int price, Carport carport) {
        this.user_id = user_id;
        this.carport = carport;
        this.dateAccepted = dateAccepted;
        this.datePlaced = datePlaced;
        this.price = price;
    }

    public int getUser_id() {
        return user_id;
    }

    public Carport getCarport() {
        return carport;
    }

    @Override
    public String toString() {
        return "Request{" + "user_id=" + user_id + ", datePlaced=" + datePlaced + ", dateAccepted=" + dateAccepted + ", price=" + price + ", carport=" + carport + '}';
    }
    
    
}
