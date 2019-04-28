/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import java.time.LocalDateTime;

/**
 * Hvorfor har req price ? - sinan
 * @author Obaydah Mohamad
 */
public class Request {
    private int user_id;
    private String datePlaced;
    private Carport carport;
    PersonalInfo info;

    public Request(PersonalInfo info, int user_id, String datePlaced, Carport carport) {
        this.user_id = user_id;
        this.carport = carport;
        this.datePlaced = datePlaced;
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

    public PersonalInfo getInfo() {
        return info;
    }

    @Override
    public String toString() {
        return "Request{" + "user_id=" + user_id + ", datePlaced=" + datePlaced + ", carport=" + carport + '}';
    }
    
    
}
