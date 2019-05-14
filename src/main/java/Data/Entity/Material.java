/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data.Entity;

/**
 * Mirrors material-table in DB
 * 
 * @author sinanjasar
 */
public class Material {
    private int id;
    private String name;
    private int length;
    private String unit;
    private int price;
    private int stock;

    public Material(int materialId, String name, int length, String unit, int price, int stock) {
        this.id = materialId;
        this.name = name;
        this.length = length;
        this.unit = unit;
        this.price = price;
        this.stock = stock;
    }

    public Material(int id, String name, String unit, int price, int stock) {
        this.id = id;
        this.name = name;
        this.unit = unit;
        this.price = price;
        this.stock = stock;
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

    public int getStock() {
        return stock;
    }
    
    @Override
    public String toString() {
        return "Material{" + "id=" + id + ", name=" + name + ", length=" + length + ", unit=" + unit  + ", price=" + price + '}';
    }
    
    
}