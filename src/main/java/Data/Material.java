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
    private int materialId;
    private String name;
    private int length;
    private String unit;
    private String desc;
    private int price;

    public Material(int materialId, String name, int length, String unit, String desc, int price) {
        this.materialId = materialId;
        this.name = name;
        this.length = length;
        this.unit = unit;
        this.desc = desc;
        this.price = price;
    }

    public int getMaterialId() {
        return materialId;
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

    public String getDesc() {
        return desc;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
    
    @Override
    public String toString() {
        return "Material{" + "materialId=" + materialId + ", name=" + name + ", length=" + length + ", unit=" + unit + ", desc=" + desc + ", price=" + price + '}';
    }
}