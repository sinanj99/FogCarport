/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data.Entity;

/**
 * Mirrors response table in database.
 * Is used in data mappers where a response is inserted/fetched. Is also used in presentation layer
 * where a response is created based on user-input or a response is fetched and displayed in jsp-file.
 * @author Obaydah Mohamad
 */
public class Response {
    private Request request;
    private int sellerId;
    private String datePlaced;
    private int sellPrice;
    private int status;
    
    public Response(Request request, int sellerId, String datePlaced, int sellPrice) {
        this.request = request;
        this.sellerId = sellerId;
        this.datePlaced = datePlaced;
        this.sellPrice = sellPrice;
    }

    public Request getRequest() {
        return request;
    }

    public int getSellerId() {
        return sellerId;
    }

    public String getDatePlaced() {
        return datePlaced;
    }

    public int getSellPrice() {
        return sellPrice;
    }

    public int getStatus() {
        return status;
    }
    
    
    
}
