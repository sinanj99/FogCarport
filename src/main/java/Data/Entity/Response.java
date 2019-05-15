/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data.Entity;

/**
 *
 * @author Obaydah Mohamad
 */
public class Response {
    private int responseId;
    private int requestId;
    private int userId;
    private int empId;
    private int carportId;
    private int shedId;
    private String datePlaced;
    private int productionPrice;
    private int sellPrice;

    public Response(int requestId, int userId, int empId, int carportId, int shedId, String datePlaced, int productionPrice, int sellPrice) {
        this.requestId = requestId;
        this.userId = userId;
        this.empId = empId;
        this.carportId = carportId;
        this.shedId = shedId;
        this.datePlaced = datePlaced;
        this.productionPrice = productionPrice;
        this.sellPrice = sellPrice;
    }
    
    public Response(int responseId, int requestId, int userId, int empId, int carportId, int shedId, String datePlaced, int productionPrice, int sellPrice) {
        this.responseId = responseId;
        this.requestId = requestId;
        this.userId = userId;
        this.empId = empId;
        this.carportId = carportId;
        this.shedId = shedId;
        this.datePlaced = datePlaced;
        this.productionPrice = productionPrice;
        this.sellPrice = sellPrice;
    }

    public int getRequestId() {
        return requestId;
    }

    public int getUserId() {
        return userId;
    }

    public int getEmpId() {
        return empId;
    }

    public String getDatePlaced() {
        return datePlaced;
    }

    public int getProductionPrice() {
        return productionPrice;
    }

    public int getSellPrice() {
        return sellPrice;
    }

    public int getResponseId() {
        return responseId;
    }

    public int getCarportId() {
        return carportId;
    }

    public int getShedId() {
        return shedId;
    }

    @Override
    public String toString() {
        return "Response{" + "responseId=" + responseId + ", requestId=" + requestId + ", userId=" + userId + ", empId=" + empId + ", carportId=" + carportId + ", shedId=" + shedId + ", datePlaced=" + datePlaced + ", productionPrice=" + productionPrice + ", sellPrice=" + sellPrice + '}';
    }
    
    
}
