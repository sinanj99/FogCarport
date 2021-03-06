/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data.Entity;

/**
 * This class represents a line item in the bill of materials,
 * is used in the logic layer
 * where an instance is returned and later fetched and added to a list of lineitems (BOM).
 * @author sinanjasar
 */
public class LineItem {
    private Type type;
    private Material material;
    private Roof roof;
    private final int qty;
    private final int price;
    private final String description;

    public LineItem(Material material, int qty, String description, int price, Type type) {
        this.material = material;
        this.qty = qty;
        this.description = description;
        this.price = material.getPrice() * qty;
        this.type = type;
    }

    public LineItem(Roof roof, int qty, String description, int price) {
        this.roof = roof;
        this.qty = qty;
        this.price = price;
        this.description = description;
        this.type = Type.ROOF;
    }

    public Material getMaterial() {
        return material;
    }

    public int getQty() {
        return qty;
    }

    public int getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public Roof getRoof() {
        return roof;
    }

    public Type getType() {
        return type;
    }

    @Override
    public String toString() {
        return "LineItem{" + "material=" + material + "qty=" + qty + "description=" + description + "price=" + price + '}';
    }

}
