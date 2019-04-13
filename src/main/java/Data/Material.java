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
public class Material {
    
    private int id;
    private String materialName;
    private int length;
    private String unit;
    private String description;
    private int price;

    public Material(int id, String materialName, int length, String unit, String description, int price)
    {
        this.id = id;
        this.materialName = materialName;
        this.length = length;
        this.unit = unit;
        this.description = description;
        this.price = price;
    }

    public int getId()
    {
        return id;
    }

    public String getMaterialName()
    {
        return materialName;
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

    public int getPrice()
    {
        return price;
    }
    
}
