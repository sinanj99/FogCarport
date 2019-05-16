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
public class Carport {
    private int carportId;
    private Roof roof;
    private int inclination;
    private int width;
    private int length;
    private Shed shed_;

    public Carport(Roof roof, int inclination, int width, int length, Shed shed_) {
        this.roof = roof;
        this.inclination = inclination;
        this.width = width;
        this.length = length;
        this.shed_ = shed_;
    }
    
    public Carport(int carportId, Roof roof, int inclination, int width, int length, Shed shed_) {
        this.carportId = carportId;
        this.roof = roof;
        this.inclination = inclination;
        this.width = width;
        this.length = length;
        this.shed_ = shed_;
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

    public Shed getShed_() {
        return shed_;
    }

    public Roof getRoof() {
        return roof;
    }
    
    @Override
    public String toString() {
        return "Carport{" + "roof=" + roof + ", inclination=" + inclination + ", width=" + width + ", length=" + length + ", shed_=" + shed_ + '}';
    } 
    
}
