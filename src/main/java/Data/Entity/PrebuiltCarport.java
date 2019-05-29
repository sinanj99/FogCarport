/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data.Entity;

/**
 * Mirrors prebuilt_carport table in database,
 * is instantiated and used in PrebuiltCarportCommand, where a list of prebuilt carport 
 * is set on the request attribute and later accessed in jsp-file.
 * @author Kasper Jeppesen
 */
public class PrebuiltCarport
{
    private int id;
    private String imgPath;
    private int carportWidth;
    private int carportLength;
    private Shed shed;
    private int price;

    public PrebuiltCarport(int id, String imgPpath, int carportWidth, int carportLength, Shed shed, int price)
    {
        this.id = id;
        this.imgPath = imgPpath;
        this.carportWidth = carportWidth;
        this.carportLength = carportLength;
        this.shed = shed;
        this.price = price;
    }

    public int getId()
    {
        return id;
    }

    public String getImgPpath()
    {
        return imgPath;
    }

    public int getCarportWidth()
    {
        return carportWidth;
    }

    public int getCarportLength()
    {
        return carportLength;
    }

    public int getPrice()
    {
        return price;
    }

    public Shed getShed() {
        return shed;
    }
    
    @Override
    public String toString() {
        return "PrebuiltCarport{" + "id=" + id + ", imgPath=" + imgPath + ", carportWidth=" + carportWidth + ", carportLength=" + carportLength + ", shed=" + shed + ", price=" + price + '}';
    }

    
    
    
}
