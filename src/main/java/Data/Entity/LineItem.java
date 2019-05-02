/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data.Entity;

/**
 *
 * @author sinanjasar
 */
public class LineItem {
    
    private Material material = null;
    private Roof roof = null;
    private final int qty;
    private final int price;
    private final String description;

    public LineItem(Material material, int qty, String description, int price)
    {
        this.material = material;
        this.qty = qty;
        this.description = description;
        this.price = material.getPrice() * qty;
    }

    public LineItem(Roof roof, int qty, String description, int price) {
        this.roof = roof;
        this.qty = qty;
        this.price = price;
        this.description = description;
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
    
    public String getDescription()
    {
        return description;
    }

    public Roof getRoof() {
        return roof;
    }
    
    @Override
    public String toString() {
        return "LineItem{" + "material=" + material + "qty=" + qty + "description=" + description + "price=" + price + '}';
    }
    
}
