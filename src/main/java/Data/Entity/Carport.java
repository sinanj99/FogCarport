/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data.Entity;

/**
 * Mirrors carport table in database,
 * is used in data mappers where a carport is returned and forwarded to
 * the presentation layer where it is used in connection with request-handling.
 * 
 * @author Obaydah Mohamad
 */
public class Carport {
    private int carportId;
    private Roof roof;
    private int inclination;
    private int width;
    private int length;
    private Shed shed;

    public Carport(Roof roof, int inclination, int width, int length, Shed shed) {
        this.roof = roof;
        this.inclination = inclination;
        this.width = width;
        this.length = length;
        this.shed = shed;
    }
    
    public Carport(int carportId, Roof roof, int inclination, int width, int length, Shed shed) {
        this.carportId = carportId;
        this.roof = roof;
        this.inclination = inclination;
        this.width = width;
        this.length = length;
        this.shed = shed;
    }

    public int getCarportId(){
        return carportId;
    }
    
    public int getWidth() {
        return width;
    }

    public int getInclination() {
        return inclination;
    }
    public int getLength() {
        return length;
    }

    public Shed getShed() {
        return shed;
    }

    public Roof getRoof() {
        return roof;
    }
    
    @Override
    public String toString() {
        return "Carport{" + "roof=" + roof + ", inclination=" + inclination + ", width=" + width + ", length=" + length + ", shed=" + shed + '}';
    } 
    
}
