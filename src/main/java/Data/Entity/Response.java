/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data.Entity;

/**
 *
 * @author Obaydah Mohamad
 * 
 * CREATE TABLE responses (
	request_id INT NOT NULL UNIQUE,
        seller_id INT NOT NULL,
	dateplaced DATETIME NOT NULL,
	sell_price INT NOT NULL,
        status INT(1) DEFAULT 0,
	CONSTRAINT responses_ibfk_1 FOREIGN KEY (request_id) REFERENCES requests(request_id)
);
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
