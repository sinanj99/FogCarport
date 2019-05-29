/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data.Entity;

/**
 * Mirrors shed table in database,
 * is used in data mappers where it is fetched/inserted
 * and is used in presentation layer in connection with requests.
 * @author Obaydah Mohamad
 */
public class Shed {
    private int shedId;
    private int width;
    private int length;

    public Shed(int width, int length) {
        this.width = width;
        this.length = length;
    }
    
    public Shed(int shedId, int width, int length) {
        this.shedId = shedId;
        this.width = width;
        this.length = length;
    }
    
    public int getShedId(){
        return shedId;
    }

    public int getWidth() {
        return width;
    }

    public int getLength() {
        return length;
    }

    @Override
    public String toString() {
        return "Shed{" + "width=" + width + ", length=" + length + '}';
    }
    
    
}
