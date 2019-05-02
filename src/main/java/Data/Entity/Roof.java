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
public class Roof {
    private int roof_id;
    private String name;
    private boolean inclined;
    private int price;

    public Roof(int roof_id, String name, boolean inclined) {
        this.roof_id = roof_id;
        this.name = name;
        this.inclined = inclined;
    }

    public Roof(int roof_id, String name, int price, boolean inclined) {
        this.roof_id = roof_id;
        this.name = name;
        this.price = price;
        this.inclined = inclined;
    }

    public int getPrice() {
        return price;
    }
     
    public int getRoof_id() {
        return roof_id;
    }

    public String getName() {
        return name;
    }


    public boolean isInclined() {
        return inclined;
    }
    
    
    @Override
    public String toString() {
        return "Roof{" + "roof_id=" + roof_id + ", name=" + name +  ", inclined=" + inclined + '}';
    }
    
    
    
}
