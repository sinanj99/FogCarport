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
    private Roof roof;
    private boolean inclined;
    private int width;
    private int length;
    private boolean shed;
    private Shed shed_;

    public Carport(Roof roof, boolean inclined, int width, int length, boolean shed, Shed shed_) {
        this.roof = roof;
        this.inclined = inclined;
        this.width = width;
        this.length = length;
        this.shed = shed;
        this.shed_ = shed_;
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
        return "Carport{" + "roof=" + roof + ", inclined=" + inclined + ", width=" + width + ", length=" + length + ", shed=" + shed + ", shed_=" + shed_ + '}';
    }
    
    
    
}
