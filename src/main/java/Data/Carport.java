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
public class Carport {
    private int roof_id;
    private boolean inclined;
    private int width;
    private int length;
    private boolean shed;
    private Shed shed_;

    public Carport(int roof_id, boolean inclined, int width, int length, boolean shed, Shed shed_) {
        this.roof_id = roof_id;
        this.inclined = inclined;
        this.width = width;
        this.length = length;
        this.shed = shed;
        this.shed_ = shed_;
    }

    public int getRoof_id() {
        return roof_id;
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

    public boolean isShed() {
        return shed;
    }

    public Shed getShed_() {
        return shed_;
    }
    
    @Override
    public String toString() {
        return "Carport{" + "roof_id=" + roof_id + ", inclined=" + inclined + ", width=" + width + ", length=" + length + ", shed=" + shed + ", shed_=" + shed_ + '}';
    }
    
    
}
