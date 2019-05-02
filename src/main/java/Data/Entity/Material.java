/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data.Entity;

/**
 * Mirrors material-table in DB
 * Vi burde have lavet Material som en superklasse, da det kun er træ- og tagplader,
 * der har en længde. Men fuck det der er for meget der skal ændres så. 
 * @author sinanjasar
 */
public class Material {
    private int id;
    private String name;
    private int length;
    private String unit;
    private int price;

    public Material(int materialId, String name, int length, String unit, int price) {
        this.id = materialId;
        this.name = name;
        this.length = length;
        this.unit = unit;
        this.price = price;
    }

    public Material(int id, String name, String unit, int price) {
        this.id = id;
        this.name = name;
        this.unit = unit;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getLength() {
        return length;
    }

    public String getUnit() {
        return unit;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
  
    @Override
    public String toString() {
        return "Material{" + "id=" + id + ", name=" + name + ", length=" + length + ", unit=" + unit  + ", price=" + price + '}';
    }
    
    
}