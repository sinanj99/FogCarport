/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

/**
 * Mirrors material-table in DB
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