/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data.Entity;

/**
 * mirrors prebuilt_carport table in database
 * @author Kasper Jeppesen
 */
public class PrebuiltCarport
{
    private int id;
    private String imgPpath;
    private int carportWidth;
    private int carportLength;
    private boolean shed;
    private int shedWidth;
    private int shedLength;
    private int price;

    public PrebuiltCarport(int id, String imgPpath, int carportWidth, int carportLength, boolean shed, int shedWidth, int shedLength)
    {
        this.id = id;
        this.imgPpath = imgPpath;
        this.carportWidth = carportWidth;
        this.carportLength = carportLength;
        this.shed = shed;
        this.shedWidth = shedWidth;
        this.shedLength = shedLength;
    }

    public int getId()
    {
        return id;
    }

    public String getImgPpath()
    {
        return imgPpath;
    }

    public int getCarportWidth()
    {
        return carportWidth;
    }

    public int getCarportLength()
    {
        return carportLength;
    }

    public boolean isShed()
    {
        return shed;
    }

    public int getShedLength()
    {
        return shedLength;
    }

    public int getPrice()
    {
        return price;
    }
    
    
}
