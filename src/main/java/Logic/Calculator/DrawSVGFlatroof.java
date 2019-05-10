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
public class DrawSVGFlatroof {

    public String drawFlat(Carport c) {
        String drawing = "";
        int length = c.getLength();
        int width = c.getWidth();
        Shed shed = c.getShed_();
        int slength = 0;
        int swidth = 0;
        if(shed!=null) {
        slength = shed.getLength();
        swidth = shed.getWidth();
        }
        BOMFlatRoof f = new BOMFlatRoof();
        drawing += "<svg height='80%' width='80%' viewbox='0 0 " + 900 + " " + 900 + "' >";

        //STOLPER 
        float spaceBetweenSpærVAR = f.spaceBetweenSpær(f.calculateQuantityOFSpærExcluedBackSpær(length, 60), length, 60);

        float startingPointFirstSpærX = 50;
        float startingPointFirstSpærY = 50;

        float xCordinate = startingPointFirstSpærX + spaceBetweenSpærVAR;
        float yCordinate = startingPointFirstSpærY;

        int quantityOfStolper = f.calculateQuantityOfStolper(length,width, shed);
        

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
                    //Changed yCordinate to place it the width of the shed away
                    yCordinate -= swidth - 2.4;
                }
                //the front middle stolpe of the shed
                if (i == 6) {
                    //yCordinate changed to place it between the front bottom and top stolpe of the shed
                    yCordinate = (startingPointFirstSpærY + width - 36) + yCordinate / 2  ;
                }
                //the back middle stolpe of the shed
                if (i == 7) {
                    //xCordinate changed to place it at the same xCordinate as the rigth top/bottom stolpe
                    xCordinate = startingPointFirstSpærX + length - spaceBetweenSpærVAR - 9.7f;
                }
                //Stolpe between the top left stolpe and top front stolpe of the shed
                if (i == 8) {
                    
                    //function as top right corner stolpe for the shed
                    if(quantityOfStolper == 9)
                    {
                        //yCordiante changed so it is placed at the botton left corner stolpe
                        yCordinate = startingPointFirstSpærY + width - 36;
                        //yCordinate changed so is placed shedwidth away
                        yCordinate -= swidth - 2.4;

                    }
                    else
                    {
                        //yCordinate changed to place it at same yCordinate at top left stolpe
                        yCordinate = startingPointFirstSpærX - 3.6f;
                        //xCordinate changed to place it in the center of the top left stolpe and top front stolpe of the shed
                        xCordinate = (xCordinate - slength + startingPointFirstSpærX + spaceBetweenSpærVAR) / 2;
                    }
                    
                }
                //stolpe between the bottom left stolpe and the bottom front stolpe of the shed
                if (i == 9) {
                    //yCordinate changed to place it at the same yCordinate as the front bottom stolpe
                    yCordinate = startingPointFirstSpærY + width - 36;
                }
                //stolpe for the top right corner of the shed, if the top right corner stolpe of the carport cant be used.
                if(i == 10)
                {
                    //yCordiante changed so it is placed at the carportwidth
                    yCordinate -= swidth - 2.4;
                    //xCordinate changed so it is placed at the same xCordinate at the top right corner stolpe for the carport
                    xCordinate = startingPointFirstSpærX + length - spaceBetweenSpærVAR - 9.7f;
                }
            } if(slength == 0) {
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
                // one of middle bottom stolpe
                if (i == 4) {
                    if (quantityOfStolper == 8) {
                        //xCordinate changed to place it so that this middle stolpe and the other middle stolpe is as far from other stolpe as possible
                        //Take the position of the bottem right stolpe and add the position of bottom left, then divide by 1.5f
                        xCordinate = (startingPointFirstSpærX + length - spaceBetweenSpærVAR - 9.7f + startingPointFirstSpærX + spaceBetweenSpærVAR) / 1.5f;
                    } else {
                        //xCordinate changed to place it in the center of bottom left and bottom right stolpe
                        //Take the position of the bottem right stolpe and add the position of bottom left, then divide by 2
                        xCordinate = (startingPointFirstSpærX + length - spaceBetweenSpærVAR - 9.7f + startingPointFirstSpærX + spaceBetweenSpærVAR) / 2;
                    }

                }
                //one of the middle top stolpe
                if (i == 5) {
                    //yCordinate changed to place it at the same yCordination as the other top stolper
                    yCordinate = startingPointFirstSpærY - 3.6f;
                }
                if (i == 6) {
                    xCordinate = (startingPointFirstSpærX + length - spaceBetweenSpærVAR - 9.7f + startingPointFirstSpærX + spaceBetweenSpærVAR) / 2.5f;
                }
                if (i == 7) {
                    yCordinate = startingPointFirstSpærY + width - 36;
                }
            }
            
            drawing += "<rect class=*stolper* x='" + xCordinate + "' y='" + yCordinate + "' height='9.7' width='9.7' fill='none' stroke='black' stroke-width='3px'/>";
        }

        
        
        // REM
        
        drawing += "<rect x='"+startingPointFirstSpærX+"' y='"+startingPointFirstSpærY+"' height='4.5' width='"+length+"' fill='none' stroke='black' stroke-width='3px' />";
        drawing += "<rect x='"+startingPointFirstSpærX+"' y='"+(startingPointFirstSpærY + width - 34)+"' height='4.5' width='"+length+"' fill='none' stroke='black' stroke-width='3px' />";
        
        
        //SPÆR
        int quantityOfSpær = f.calculateQuantityOFSpærExcluedBackSpær(length, 60);
        int quantityOfSpærPlusTheBackSpær = f.calculateQuantityOfSpærIncludedBackSpær(length, 60);

        float frontSpærPlacementX = startingPointFirstSpærX;
        float frontSpærPlacementY = startingPointFirstSpærY - 15;
        
        
        for (int i = 0; i < quantityOfSpærPlusTheBackSpær; i++) {
            drawing += "<rect x='" + frontSpærPlacementX + "' y='" + frontSpærPlacementY + "' height='" + width + "' width='10' fill='none' stroke='black' stroke-width='3px'/>";
            frontSpærPlacementX += f.spaceBetweenSpær(f.calculateQuantityOFSpærExcluedBackSpær(length, 60), length, 60) + 3f;
        }
        
        drawing += "</svg>";
        return drawing;
    }
}
