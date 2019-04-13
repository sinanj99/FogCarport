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
    
    private String name;
    private int length;
    private String unit;
    private String description;
    private int quantity;

    public LineItem(String materialName, int length, String unit, String description, int quantity)
    {
        this.name = materialName;
        this.length = length;
        this.unit = unit;
        this.description = description;
        this.quantity = quantity;
    }

    public String getName()
    {
        return name;
    }

    public int getLength()
    {
        return length;
    }

    public String getUnit()
    {
        return unit;
    }

    public String getDescription()
    {
        return description;
    }

    public int getQuantity()
    {
        return quantity;
    }
}
