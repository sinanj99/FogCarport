/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic.Logic;

import Data.Entity.Carport;
import Data.Entity.Shed;

/**
 * Draws SVGs for flat roof carport
 * @author Kasper Jeppesen
 */
class DrawSVGFlatroof {
 
    /**
     * Draws SVG for flat roof carport from the top
     * @param c the carport
     * @return a string representation of svg
     */
    public String drawFlat(Carport c) {
        
        String drawingForFlatRoof = "<svg height='80%' width='80%' viewbox='0 0 " + 900 + " " + 900 + "' >";
        
        String drawPost = drawPost(c);
        String drawStrap = drawStrap(c);
        String drawRafters = drawRafter(c);
        String drawShedClothing = drawShedClothing(c);
        String drawPerforatedBands = drawPerforatedBands(c);
        String drawDimension = drawDimenssions(c);
        
        drawingForFlatRoof += drawPost + drawStrap + drawRafters + drawShedClothing + drawPerforatedBands + drawDimension;        
        
        drawingForFlatRoof += "</svg>";
        return drawingForFlatRoof;
    }
    
    /**
     * 
     * @param c
     * @return a string for placement of post (stolper)
     */
    private String drawPost(Carport c)
    {
        String drawing = "";
        // get the dimension of the carport
        int length = c.getLength();
        int width = c.getWidth();
        Shed shed = c.getShed();
        int slength = 0;
        int swidth = 0;
        
        if(shed!=null) {
        slength = shed.getLength();
        swidth = shed.getWidth();
        }

        BOMFundament f = new BOMFundament();
        float spaceBetweenRafterVAR = f.spaceBetweenRafter(length, 60);

        float startingPointFirstRafterX = 50;
        float startingPointFirstRafterY = 50;

        //set the starting cordinates ( + spaceBetweenRafter ) because first post should appear around the second rafter
        float xCordinate = startingPointFirstRafterX + spaceBetweenRafterVAR;
        float yCordinate = startingPointFirstRafterY;

        int quantityOfStolper = f.calculateQuantityOfPost(c);
        
        //Set the placement of post
        for (int i = 0; i < quantityOfStolper; i++) {
            
            //If the carport contain a shed
            if (shed != null && shed.getLength() != 0) {
                //Top left stolpe
                if (i == 0) {
                    //yCordinate changed to make place it under remmen
                    yCordinate -= 3.6f;
                }
                //Top right stolpe
                if (i == 1) {
                    //xCordinate changed to place it at the second last spær
                    xCordinate = startingPointFirstRafterX + length - spaceBetweenRafterVAR - 9.7f;
                }
                //Botton right stolpe
                if (i == 2) {
                    //yCordinate changed to place it at the at the other rem 
                    yCordinate = startingPointFirstRafterY + width - 36;
                }
                //Bottom left stolpe
                if (i == 3) {
                    //xCordinato changed to place it at the same xCordinate at the top left stolpe
                    xCordinate = startingPointFirstRafterX + spaceBetweenRafterVAR;
                }
                //The bottom front stolpe of the shed
                if (i == 4) {
                    //xCprdinate changed to place it the length of the shed away from the bottom right stolpe
                    xCordinate = (startingPointFirstRafterX + length - spaceBetweenRafterVAR) - slength;
                }
                //The top front stolpe of the shed
                if (i == 5) {
                    //Changed yCordinate to place it the width of the shed away
                    yCordinate -= swidth - 2.4;
                }
                //the front middle stolpe of the shed
                if (i == 6) {
                    //yCordinate changed to place it between the front bottom and top stolpe of the shed
                    yCordinate = ((startingPointFirstRafterY + width - 36) + yCordinate) / 2;
                }
                //the back middle stolpe of the shed
                if (i == 7) {
                    //xCordinate changed to place it at the same xCordinate as the rigth top/bottom stolpe
                    xCordinate = startingPointFirstRafterX + length - spaceBetweenRafterVAR - 9.7f;
                }
                //Stolpe between the top left stolpe and top front stolpe of the shed
                if (i == 8) {
                    
                    //function as top right corner stolpe for the shed
                    if(quantityOfStolper == 9)
                    {
                        //yCordiante changed so it is placed at the botton left corner stolpe
                        yCordinate = startingPointFirstRafterY + width - 36;
                        //yCordinate changed so is placed shedwidth away
                        yCordinate -= swidth - 2.4;

                    }
                    else
                    {
                        //yCordinate changed to place it at same yCordinate at top left stolpe
                        yCordinate = startingPointFirstRafterX - 3.6f;
                        //xCordinate changed to place it in the center of the top left stolpe and top front stolpe of the shed
                        xCordinate = (xCordinate - slength + startingPointFirstRafterX + spaceBetweenRafterVAR) / 2;
                    }
                    
                }
                //stolpe between the bottom left stolpe and the bottom front stolpe of the shed
                if (i == 9) {
                    //yCordinate changed to place it at the same yCordinate as the front bottom stolpe
                    yCordinate = startingPointFirstRafterY + width - 36;
                }
                //stolpe for the top right corner of the shed, if the top right corner stolpe of the carport cant be used.
                if(i == 10)
                {
                    //yCordiante changed so it is placed at the carportwidth
                    yCordinate -= swidth - 2.4;
                    //xCordinate changed so it is placed at the same xCordinate at the top right corner stolpe for the carport
                    xCordinate = startingPointFirstRafterX + length - spaceBetweenRafterVAR - 9.7f;
                }
            } 
            
            //If the carport contains no shed
            if(slength == 0) {
                //Top left stolpe
                if (i == 0) {
                    //yCordinate changed to make place it under remmen
                    yCordinate -= 3.6f;
                }
                //Top right stolpe
                if (i == 1) {
                    //xCordinate changed to place it at the second last spær
                    xCordinate = startingPointFirstRafterX + length - spaceBetweenRafterVAR - 9.7f;
                }
                //Botton right stolpe
                if (i == 2) {
                    //yCordinate changed to place it at the at the other rem 
                    yCordinate = startingPointFirstRafterY + width - 36;
                }
                //Bottom left stolpe
                if (i == 3) {
                    //xCordinato changed to place it at the same xCordinate at the top left stolpe
                    xCordinate = startingPointFirstRafterX + spaceBetweenRafterVAR;
                }
                // one of middle bottom stolpe
                if (i == 4) {
                    if (quantityOfStolper == 8) {
                        //xCordinate changed to place it so that this middle stolpe and the other middle stolpe is as far from other stolpe as possible
                        //Take the position of the bottem right stolpe and add the position of bottom left, then divide by 1.5f
                        xCordinate = (startingPointFirstRafterX + length - spaceBetweenRafterVAR - 9.7f + startingPointFirstRafterX + spaceBetweenRafterVAR) / 1.5f;
                    } else {
                        //xCordinate changed to place it in the center of bottom left and bottom right stolpe
                        //Take the position of the bottem right stolpe and add the position of bottom left, then divide by 2
                        xCordinate = (startingPointFirstRafterX + length - spaceBetweenRafterVAR - 9.7f + startingPointFirstRafterX + spaceBetweenRafterVAR) / 2;
                    }

                }
                //one of the middle top stolpe
                if (i == 5) {
                    //yCordinate changed to place it at the same yCordination as the other top stolper
                    yCordinate = startingPointFirstRafterY - 3.6f;
                }
                if (i == 6) {
                    xCordinate = (startingPointFirstRafterX + length - spaceBetweenRafterVAR - 9.7f + startingPointFirstRafterX + spaceBetweenRafterVAR) / 2.5f;
                }
                if (i == 7) {
                    yCordinate = startingPointFirstRafterY + width - 36;
                }
            }
            
            drawing += "<rect class=*stolper* x='" + xCordinate + "' y='" + yCordinate + "' height='9.7' width='9.7' fill='none' stroke='black' stroke-width='3px'/>";
        }    
            return drawing;
    }
    
    /**
     * 
     * @param c
     * @return string for placement of strap (remmen)
     */
    private String drawStrap(Carport c)
    {
        String drawing = "";
        
        int length = c.getLength();
        int width = c.getWidth();
        
        float startingPointFirstRafterX = 50;
        float startingPointFirstSpærY = 50;
        
        drawing += "<rect x='"+startingPointFirstRafterX+"' y='"+startingPointFirstSpærY+"' height='4.5' width='"+length+"' fill='none' stroke='black' stroke-width='3px' />";
        drawing += "<rect x='"+startingPointFirstRafterX+"' y='"+(startingPointFirstSpærY + width - 34)+"' height='4.5' width='"+length+"' fill='none' stroke='black' stroke-width='3px' />";
        
        return drawing;
    }
    
    /**
     * 
     * @param c
     * @return string for placement of rafter (spær)
     */
    private String drawRafter(Carport c)
    {
        String drawing = "";
        int length = c.getLength();
        int width = c.getWidth();
        
        float startingPointFirstRafterX = 50;
        float startingPointFirstRafterY = 50;

        BOMFundament f = new BOMFundament();
        
        int quantityOfSpærPlusTheBackSpær = f.calculateQuantityOfRafterIncludedBackRafter(length, 60);

        float frontSpærPlacementX = startingPointFirstRafterX;
        float frontSpærPlacementY = startingPointFirstRafterY - 15;
        
        
        for (int i = 0; i < quantityOfSpærPlusTheBackSpær; i++) {
            drawing += "<rect x='" + frontSpærPlacementX + "' y='" + frontSpærPlacementY + "' height='" + width + "' width='10' fill='none' stroke='black' stroke-width='3px'/>";
            frontSpærPlacementX += f.spaceBetweenRafter(length, 60) + 3f;
        }
        return drawing;
    }
    
    /**
     * 
     * @param c
     * @return string for placement of shed clothing (skur beklædning)
     */
    private String drawShedClothing(Carport c)
    {
        String drawing = "";
        int length = c.getLength();
        int width = c.getWidth();
        Shed shed = c.getShed();
        int slength = 0;
        int swidth = 0;
        
        if(shed!=null) {
        slength = shed.getLength();
        swidth = shed.getWidth();
        }
        
        float startingPointFirstRafterX = 50;
        float startingPointFirstRafterY = 50;

        BOMFundament f = new BOMFundament();
        
        float spaceBetweenRafterVAR = f.spaceBetweenRafter(length, 60);
        
        if(shed != null)
        {
            drawing += "<line x1='"+(startingPointFirstRafterX + length - spaceBetweenRafterVAR)+"' y1='"+(startingPointFirstRafterY + width - 36 + 10)+
                "' x2='"+(startingPointFirstRafterX + length - spaceBetweenRafterVAR)+"' y2='"+(startingPointFirstRafterY + width - 36 - swidth)+
                "' stroke='darkblue' stroke-width='3px' fill='none'  />";
            drawing += "<line x1='"+(startingPointFirstRafterX + length - spaceBetweenRafterVAR)+"' y1='"+(startingPointFirstRafterY + width - 36 - swidth)+
                    "' x2='"+((startingPointFirstRafterX + length - spaceBetweenRafterVAR) - slength)+"' y2='"+(startingPointFirstRafterY + width - 36 - swidth)+
                    "' stroke='darkblue' stroke-width='3px' fill='none' />";
            drawing += "<line x1='"+((startingPointFirstRafterX + length - spaceBetweenRafterVAR) - slength)+"' y1='"+(startingPointFirstRafterY + width - 36 - swidth)+
                    "' x2='"+((startingPointFirstRafterX + length - spaceBetweenRafterVAR) - slength)+"' y2='"+(startingPointFirstRafterY + width - 36 + 10)+
                    "' stroke='darkblue' stroke-width='3px' fill='none' />";
            drawing += "<line x1='"+((startingPointFirstRafterX + length - spaceBetweenRafterVAR) - slength)+"' y1='"+(startingPointFirstRafterY + width - 36 + 10)+
                    "' x2='"+(startingPointFirstRafterX + length - spaceBetweenRafterVAR)+"' y2='"+(startingPointFirstRafterY + width - 36 + 10)+
                    "' stroke='darkblue' stroke-width='3px' fill='none'  />";
        }
        
        return drawing;
    }
    
    /**
     * 
     * @param c
     * @return string for placement of perforated bands (hulbånd)
     */
    private String drawPerforatedBands(Carport c)
    {
         String drawing = "";
        int length = c.getLength();
        int width = c.getWidth();
        Shed shed = c.getShed();
        int slength = 0;
        int swidth = 0;
        
        if(shed!=null) {
        slength = shed.getLength();
        swidth = shed.getWidth();
        }

        BOMFundament f = new BOMFundament();
        
        float spaceBetweenRafterVAR = f.spaceBetweenRafter(length, 60);
        int quantityOfRafter = f.calculateQuantityOFRafterExcluedBackRafter(length, 60);
       
        int bandWidth = width + 20;
        float startPoint = 110;
        double endPoint = 110 + (spaceBetweenRafterVAR * (quantityOfRafter - 2)) + (4 * (quantityOfRafter - 2));
        if(shed != null) endPoint = (110 + (spaceBetweenRafterVAR * (quantityOfRafter - 2)) + (1 * (quantityOfRafter - 2))) - slength;
        //float endPoint = (spaceBetweenSpærVAR * (quantityOfSpær));// + (ib.spaceBetweenRafters(carportLength, shedLength) * (amountOfRafters - minusRafters)));
        drawing += "<line x1='"+startPoint+"' y1='50' x2='"+ endPoint +"' y2='"+bandWidth+"' style='stroke:silver; stroke-width:5; stroke-dasharray:10, 5;' />"
                + "<line x1='"+startPoint+"' y1='"+bandWidth+"' x2='"+ endPoint +"' y2='50' style='stroke:silver; stroke-width:5; stroke-dasharray:10, 5;' />";
        
        return drawing;
    }
            
    /**
     * 
     * @param c
     * @return string for dimension of drawing
     */
    private String drawDimenssions(Carport c)
    {
        String drawing = "";
        int length = c.getLength();
        int width = c.getWidth();
        Shed shed = c.getShed();
        int slength = 0;
        int swidth = 0;
        
        if(shed!=null) {
        slength = shed.getLength();
        swidth = shed.getWidth();
        }
        
        float startingPointFirstRafterX = 50;
        float startingPointFirstRafterY = 50;

        BOMFundament f = new BOMFundament();
        
        float spaceBetweenRafterVAR = f.spaceBetweenRafter(length, 60);
        
     drawing += "<line x1='"+20+"' y1='"+35+
            "' x2='"+20+"' y2='"+  (width + 35) +
            "' stroke='darkgrey' stroke-width='3px' fill='none'  />";
        drawing += "<text x='"+(-80)+"' transform='rotate(-90)' y='"+15+"' fill='black'>"
                +width+" cm</text>";

        drawing += "<line x1='"+50+"' y1='"+20+
            "' x2='"+(length+50)+"' y2='"+  20 +
            "' stroke='darkgrey' stroke-width='3px' fill='none'  />";
        drawing += "<text x='"+50+"' y='"+15+ "' fill='black'>"
                +length+" cm</text>";

        if(shed != null)
        {
             drawing += "<line x1='"+(length+70)+"' y1='"+(width+20)+
            "' x2='"+(length+70)+"' y2='"+  ((startingPointFirstRafterY + width - 36) - (swidth - 2.4)) +
            "' stroke='darkgrey' stroke-width='3px' fill='none'  />";
            drawing += "<text x='"+(-width-20)+"' transform='rotate(-90)' y='"+(length+90)+"' fill='black'>"
                    +swidth+" cm</text>";


             drawing += "<line x1='"+(startingPointFirstRafterX + length - spaceBetweenRafterVAR )+"' y1='"+(width+60)+
                "' x2='"+((startingPointFirstRafterX + length - spaceBetweenRafterVAR) - slength)+"' y2='"+ (width+60)  +
                "' stroke='darkgrey' stroke-width='3px' fill='none'  />";
             drawing += "<text x='"+((startingPointFirstRafterX + length - spaceBetweenRafterVAR) - slength)+"' y='"+(width+80)+ "' fill='black'>"
                    +slength+" cm</text>";
        }

        int startPointThis = 60;
        for(int i = 0; i < f.calculateQuantityOFRafterExcluedBackRafter(length, 60); i++)
        {
            drawing += "<line x1='"+startPointThis+"' y1='"+40+
            "' x2='"+(startPointThis + f.spaceBetweenRafter(length, 60) - 6)+"' y2='"+  40 +
            "' class='lineFOrSpace'  stroke='darkgrey' stroke-width='3px' fill='none'  />";

            drawing += "<text x='"+(startPointThis + 2)+"' y='"+35+ "' fill='black' font-size='12px'>"
                +f.spaceBetweenRafter(length, 60)+" cm</text>";

            startPointThis += f.spaceBetweenRafter(length, 60) + 3;
        }
        return drawing;    
    }
}
