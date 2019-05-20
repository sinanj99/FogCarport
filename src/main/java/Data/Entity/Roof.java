/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data.Entity;

/**
 * Mirrors roof-tables in database.
 * Is used in datamappers where it is fetched / inserted. Is also used in presentation layer
 * where the different rooftypes are displayed in jsp's, and roofs are instantiated in connection
 * with requests.
 * @author Obaydah Mohamad
 */
public class Roof {
    private int roof_id;
    private String name;
    private boolean inclined;
    private int price;
    private int length;

    public Roof(int roof_id, String name, boolean inclined) {
        this.roof_id = roof_id;
        this.name = name;
        this.inclined = inclined;
    }

    public Roof(int roof_id, String name, int price, boolean inclined, int length) {
        this.roof_id = roof_id;
        this.name = name;
        this.price = price;
        this.inclined = inclined;
        this.length = length;
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

    public int getLength() {
        return length;
    }
    
    
    @Override
    public String toString() {
        return "Roof{" + "roof_id=" + roof_id + ", name=" + name +  ", inclined=" + inclined + '}';
    }
    
    
    
}
