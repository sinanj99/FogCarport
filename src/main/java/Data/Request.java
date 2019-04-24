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
    private boolean inclined;
    private int width;
    private int length;
    private Roof roofType;
    private boolean shed;
    private int shedWidth;
    private int shedLength;

    public Request(boolean isInclined, int width, int length, Roof roofType, boolean shed, int shedWidth, int shedLength) {
        this.inclined = isInclined;
        this.width = width;
        this.length = length;
        this.roofType = roofType;
        this.shed = shed;
        this.shedWidth = shedWidth;
        this.shedLength = shedLength;
    }
    
    
}
