/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

/**
 *
 * @author Obaydah Mohamad
 */
public class Roof {
    private int roof_id;
    private String name;
    private int price;
    private boolean inclined;

    public Roof(int roof_id, String name, int price, boolean inclined) {
        this.roof_id = roof_id;
        this.name = name;
        this.price = price;
        this.inclined = inclined;
    }

    public int getRoof_id() {
        return roof_id;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public boolean isInclined() {
        return inclined;
    }
    
    
    @Override
    public String toString() {
        return "Roof{" + "roof_id=" + roof_id + ", name=" + name + ", price=" + price + ", inclined=" + inclined + '}';
    }
    
    
    
}
