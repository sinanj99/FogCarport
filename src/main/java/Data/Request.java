/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

/**
 *
 * @author Obaydah Mohamad
 */
public class Request {
    private int user_id;
    private boolean inclined;
    private int width;
    private int length;
    private Roof roofType;
    private boolean shed;
    private int shedWidth;
    private int shedLength;

    public Request(int user_id, boolean isInclined, int width, int length, Roof roofType, boolean shed, int shedWidth, int shedLength) {
        this.user_id = user_id;
        this.inclined = isInclined;
        this.width = width;
        this.length = length;
        this.roofType = roofType;
        this.shed = shed;
        this.shedWidth = shedWidth;
        this.shedLength = shedLength;
    }

    public boolean isInclined() {
        return inclined;
    }

    public int getWidth() {
        return width;
    }

    public int getLength() {
        return length;
    }

    public Roof getRoofType() {
        return roofType;
    }

    public boolean isShed() {
        return shed;
    }

    public int getShedWidth() {
        return shedWidth;
    }

    public int getShedLength() {
        return shedLength;
    }

    @Override
    public String toString() {
        return "Request{" + "user_id=" + user_id + ", inclined=" + inclined + ", width=" + width + ", length=" + length + ", roofType=" + roofType + ", shed=" + shed + ", shedWidth=" + shedWidth + ", shedLength=" + shedLength + '}';
    }
    
    
}
