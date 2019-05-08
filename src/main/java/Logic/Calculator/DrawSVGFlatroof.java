/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic.Calculator;

import Data.Entity.Carport;
import Data.Entity.Shed;

/**
 *
 * @author Kasper Jeppesen
 */
public class DrawSVGFlatroof
{
    public String drawFlat(Carport c) {
        String drawing = "";
        int length = c.getLength();
        int width = c.getWidth();
        Shed shed = c.getShed_();
        int slength = shed.getLength();
        FlatRoofCarportBOM f = new FlatRoofCarportBOM();
        drawing += "<svg height='80%' width='80%' viewbox='0 0 " + (length + 60) + " " + width + "' >";

        float spaceBetweenSpærVAR = f.spaceBetweenSpær(f.calculateQuantityOFSpærExcluedBackSpær(length), length);

        float startingPointFirstSpærX = 50;
        float startingPointFirstSpærY = 50;

        float xCordinate = startingPointFirstSpærX + spaceBetweenSpærVAR;
        float yCordinate = startingPointFirstSpærY;

        int quantityOfStolper = f.calculateQuantityOfStolper(length, shed, slength);
        
        for (int i = 0; i < quantityOfStolper; i++) {
            if (shed != null && shed.getLength() != 0) {
                //Top left stolpe
                if (i == 0) {
                    //yCordinate changed to make place it under remmen
                    yCordinate -= 3.6f;
                }
                //Top right stolpe
                if (i == 1) {
                    //xCordinate changed to place it at the second last spær
                    xCordinate = startingPointFirstSpærX + length - spaceBetweenSpærVAR - 9.7f;
                }
                //Botton right stolpe
                if (i == 2) {
                    //yCordinate changed to place it at the at the other rem 
                    yCordinate = startingPointFirstSpærY + width - 36;
                }
                //Bottom left stolpe
                if (i == 3) {
                    //xCordinato changed to place it at the same xCordinate at the top left stolpe
                    xCordinate = startingPointFirstSpærX + spaceBetweenSpærVAR;
                }
                //The bottom front stolpe of the shed
                if (i == 4) {
                    //xCprdinate changed to place it the length of the shed away from the bottom right stolpe
                    xCordinate = (startingPointFirstSpærX + length - spaceBetweenSpærVAR) - slength;
                }
                //The top front stolpe of the shed
                if (i == 5) {
                    //Changed yCordinate to place it at the same yCordinat as the top left/right stolpe
                    yCordinate = startingPointFirstSpærY - 3.6f;
                }
//the front middle stolpe of the shed
                if (i == 6) {
                    //yCordinate changed to place it between the front bottom and top stolpe of the shed
                    yCordinate = yCordinate + (width - 30) / 2;
                }
                //the back middle stolpe of the shed
                if (i == 7) {
                    //xCordinate changed to place it at the same xCordinate as the rigth top/bottom stolpe
                    xCordinate = startingPointFirstSpærX + length - spaceBetweenSpærVAR - 9.7f;
                }
                //Stolpe between the top left stolpe and top front stolpe of the shed
                if (i == 8) {
                    //yCordinate changed to place it at same yCordinate at top left stolpe
                    yCordinate = startingPointFirstSpærX - 3.6f;
                    //xCordinate changed to place it in the center of the top left stolpe and top front stolpe of the shed
                    xCordinate = (xCordinate - slength + startingPointFirstSpærX + spaceBetweenSpærVAR) / 2;
                }
                //stolpe between the bottom left stolpe and the bottom front stolpe of the shed
                if (i == 9) {
                    //yCordinate changed to place it at the same yCordinate as the front bottom stolpe
                    yCordinate = startingPointFirstSpærY + width - 36;
                }
                drawing += "<rect x='" + xCordinate + "' y='" + yCordinate + "' height='9.7' width='9.7' fill='none' stroke='black' stroke-width='3px'/>";
            }
        }

        drawing += "</svg>";
        return drawing;
    }
}
