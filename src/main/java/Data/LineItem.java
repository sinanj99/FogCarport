/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

/**
 *
 * @author sinanjasar
 */
public class LineItem {
    
    private final Material material;
    private final int qty;
    private final int price;
    

    public LineItem(Material material, int qty, int price)
    {
        this.material = material;
        this.qty = qty;
        this.price = material.getPrice() * qty;
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
    
}
