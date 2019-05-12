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
    private String imgPath;
    private int carportWidth;
    private int carportLength;
    private boolean shed;
    private int shedWidth;
    private int shedLength;
    private int price;

    public PrebuiltCarport(int id, String imgPpath, int carportWidth, int carportLength, boolean shed, int shedWidth, int shedLength, int price)
    {
        this.id = id;
        this.imgPath = imgPpath;
        this.carportWidth = carportWidth;
        this.carportLength = carportLength;
        this.shed = shed;
        this.shedWidth = shedWidth;
        this.shedLength = shedLength;
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
    
     @Override
    public String toString() {
        return "PrebuiltCarport{" + "id=" + id + ", imgPath=" + imgPath + ", carportWidth=" + carportWidth + ", carportLength=" + carportLength
                + ", shed=" + shed + ", shedWidth=" + shedWidth + ", shedLength=" + shedLength + ", price=" + price + '}';
    }
    
}
