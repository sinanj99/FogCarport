/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic.Calculator;

import Data.Entity.Carport;
import Data.Entity.Request;
import Data.Entity.Shed;

/**
 *
 * @author sinanjasar
 */
public class DrawSVG {

    
    public String drawTop(Carport c) {
        String drawingTop = "";
        int carportLength = c.getLength();
        int carportWidth = c.getWidth();
        
        
        drawingTop += "<svg height='20%' width='40%' viewbox='0 0 " + carportLength + " " + carportWidth + "' style='border: 1px solid black;'>";
        drawingTop += "<rect x='0' y='50%' width='100%' height='10' fill='none' stroke='black' stroke-width='3'/>";
        
        drawingTop += "</svg>";
        return drawingTop;
        
        
    }
}
