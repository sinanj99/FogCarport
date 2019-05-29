/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic.Logic;

import Data.Entity.Carport;
import Data.Entity.Shed;

/**
 * Draws SVGs for incline roof carports
 * @author sinanjasar
 */
public class DrawSVGIncline {
    /**
     * Draws the top of a carport with inclined roof
     * @param c the carport
     * @return A string representation of svg drawing 
     * of the top of a carport with inclined roof
     */
    protected String drawTopIncline(Carport c) {
        
        String drawingForInclineRoof = "<svg height='80%' width='80%' viewbox='0 0 " + 900 + " " + 900 + "' >";
        
        String drawPost = drawPost(c);
        String drawStrap = drawStrap(c);
        String drawRafter = drawRafter(c);
        String drawLaths = drawLaths(c);
        String drawShedClothing = drawShedClothing(c);
        String drawDimensions = drawDimensions(c);
        
        drawingForInclineRoof += drawStrap + drawRafter + drawLaths + drawPost +  drawShedClothing + drawDimensions;
      
        drawingForInclineRoof += "</svg>";
        return drawingForInclineRoof;
    }
    /**
     * Draws the side of a carport with inclined roof
     * @param c the carport
     * @return a string representation of svg drawing of the side of a carport
     * with inclined roof
     */
    protected String drawFrontIncline(Carport c) {
        String drawing = "";
        
        int width = c.getWidth();
        
        double inclination = Math.toRadians(c.getInclination());
        double hypotenuse = (width / 2) / Math.cos(inclination);
        double roofHeight = Math.sin(inclination) * hypotenuse;
        roofHeight *= 2;
        
        System.out.println("ROOFHEIGHT : " + roofHeight);

        drawing += "<svg height='100%' width='100%' viewbox='0 -600 " + 800 + " " + 800 + "' >";
        drawing += "<rect x='0' y='" + roofHeight + "' height='20' width='" + width + "' fill='lightgray' stroke='black' stroke-width='3'/>";
        drawing += "<text x='" + (width / 2 - 50) + "' y='" + (roofHeight + 50) + "' fill='black'>Bredde: " + width  + " cm</text>";
        drawing += "<text transform='rotate(-90)' x='" + (-roofHeight / 2 - 50) + "' y='" + (width / 2 - 10) + "' fill='black'>Højde: " + (int) roofHeight + " cm</text>";
        drawing += "<text x='" + (width - 80) + "' y='" + (roofHeight - 50) + "' fill='black'>Hældning: " + (int) c.getInclination() + "°</text>";
        drawing += "<line x1='" + width / 2 + "' x2='" + width / 2 + "' y1='" + roofHeight + "' y2='" + 0 + "' stroke-dasharray='5,5' stroke='black' stroke-width='3'/>";
        drawing += "<line x1='" + width + "' x2='" + width / 2 + "' y1='" + roofHeight + "' y2='" + 0 + "' stroke='black' stroke-width='3'/>";
        drawing += "<line x1='" + 0 + "' x2='" + width / 2 + "' y1='" + roofHeight + "' y2='" + 0 + "' stroke='black' stroke-width='3'/>";
        drawing += "</svg>";
        return drawing;
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

        int hypotenuse = hypotenuseCalc(c);
        
        BOMFundament f = new BOMFundament();
        float spaceBetweenRafterVAR = f.spaceBetweenRafter(length, 60);

        float startingPointFirstRafterX = 50;
        float startingPointFirstRafterY = 50;

        //set the starting cordinates ( + spaceBetweenRafter ) because first post should appear around the second rafter
        float xCordinate = startingPointFirstRafterX + spaceBetweenRafterVAR;
        float yCordinate = startingPointFirstRafterY;

        int quantityOfPost = f.calculateQuantityOfPost(c);
    
        for (int i = 0; i < quantityOfPost; i++) 
        {
            //if the carport contains a shed 
            if (shed != null ) 
            {

                //Top left pole
                if (i == 0) 
                {
                    //yCordinate changed to make place it under remmen
                    xCordinate = startingPointFirstRafterX + spaceBetweenRafterVAR / 2;
                    yCordinate -= 3.6f;
                }
                //Top right pole
                if (i == 1) 
                {
                    //xCordinate changed to place it at the second last spær
                    xCordinate = startingPointFirstRafterX + length - 9.7f;
                }
                //Botton right pole
                if (i == 2) 
                {
                    //yCordinate changed to place it at the at the other rem 
                    yCordinate = startingPointFirstRafterY + (int) hypotenuse * 2 - 93;
                }
                //Bottom left pole
                if (i == 3) 
                {
                    //xCordinato changed to place it at the same xCordinate at the top left pole
                    xCordinate = startingPointFirstRafterX + spaceBetweenRafterVAR / 2;
                }
                //The bottom front pole of the shed
                if (i == 4) 
                {
                    //xCprdinate changed to place it the length of the shed away from the bottom right pole
                    //yCordinate = (int)hypotenuse*2-(((int)hypotenuse*2-swidth)/2);
                    xCordinate = (startingPointFirstRafterX + length) - slength;
                }
                //The top front pole of the shed
                if (i == 5) 
                {
                    if(swidth > (int) hypotenuse * 2 - 93)
                    {
                        yCordinate -= (int) hypotenuse * 2 - 93;
                    }
                    else
                    {
                        //Changed yCordinate to place it the width of the shed away
                        yCordinate -= swidth - 2.4;
                    }
                    
                }
                //the front middle pole of the shed
                if (i == 6) 
                {
                    //yCordinate changed to place it between the front bottom and top pole of the shed
                    yCordinate = ((startingPointFirstRafterY + (int) hypotenuse * 2 - 93) + yCordinate) / 2;
                    //((int)hypotenuse);
                }
                //the back middle pole of the shed
                if (i == 7) 
                {
                    //xCordinate changed to place it at the same xCordinate as the rigth top/bottom pole
                    xCordinate = startingPointFirstRafterX + length - 9.7f;
                }
                //Stolpe between the top left pole and top front pole of the shed
                if (i == 8) 
                {

                    //function as top right corner pole for the shed
                    if (quantityOfPost == 9) 
                    {
                        //yCordiante changed so it is placed at the botton right corner pole
                        yCordinate = startingPointFirstRafterY + (int) hypotenuse * 2 - 93;
                        //yCordinate changed so is placed shedwidth away
                        yCordinate -= swidth - 2.4;

                    } 
                    else 
                    {
                        //yCordinate changed to place it at same yCordinate at top left pole
                        yCordinate = startingPointFirstRafterX - 3.6f;
                        //xCordinate changed to place it in the center of the top left pole and top front pole of the shed
                        xCordinate = (xCordinate - slength + startingPointFirstRafterX + spaceBetweenRafterVAR) / 2;
                    }

                }
                //pole between the bottom left pole and the bottom front pole of the shed
                if (i == 9) 
                {
                    //yCordinate changed to place it at the same yCordinate as the front bottom pole
                    yCordinate = startingPointFirstRafterY + (int) hypotenuse * 2 - 93;
                    
                }
                //pole for the top right corner of the shed, if the top right corner pole of the carport cant be used.
                if (i == 10) 
                {
                    //yCordiante changed so it is placed at the carportwidth
                    yCordinate -= swidth - 2.4;
                    //xCordinate changed so it is placed at the same xCordinate at the top right corner pole for the carport
                    xCordinate = startingPointFirstRafterX + length - 9.7f;
                }
            }
            
            
            //if there is no shed 
            if (shed == null) 
            {
                //Top left pole
                if (i == 0) 
                {
                    //yCordinate changed to make place it under remmen
                    xCordinate = startingPointFirstRafterX + spaceBetweenRafterVAR / 2;
                    yCordinate -= 3.6f;
                }
                //Top right pole
                if (i == 1) 
                {
                    //xCordinate changed to place it at the second last spær
                    xCordinate = startingPointFirstRafterX + length - 9.7f;
                }
                //Botton right pole
                if (i == 2) 
                {
                    //yCordinate changed to place it at the at the other rem 
                    yCordinate = startingPointFirstRafterY + (int) hypotenuse * 2 - 93;
                }
                //Bottom left pole
                if (i == 3) 
                {
                    //xCordinato changed to place it at the same xCordinate at the top left pole
                    xCordinate = startingPointFirstRafterX + spaceBetweenRafterVAR / 2;
                }
                // one of middle bottom pole
                if (i == 4) 
                {
                    if (quantityOfPost == 8) 
                    {
                        //xCordinate changed to place it so that this middle pole and the other middle pole is as far from other pole as possible
                        //Take the position of the bottem right pole and add the position of bottom left, then divide by 1.5f
                        xCordinate = (startingPointFirstRafterX + length - spaceBetweenRafterVAR - 9.7f + startingPointFirstRafterX + spaceBetweenRafterVAR) / 1.5f;
                    } 
                    else 
                    {
                        //xCordinate changed to place it in the center of bottom left and bottom right pole
                        //Take the position of the bottem right pole and add the position of bottom left, then divide by 2
                        xCordinate = (startingPointFirstRafterX + length - spaceBetweenRafterVAR - 9.7f + startingPointFirstRafterX + spaceBetweenRafterVAR) / 2;
                    }

                }
                //one of the middle top pole
                if (i == 5) 
                {
                    //yCordinate changed to place it at the same yCordination as the other top poler
                    yCordinate = startingPointFirstRafterY - 3.6f;
                }
                if (i == 6) 
                {
                    xCordinate = (startingPointFirstRafterX + length - spaceBetweenRafterVAR - 9.7f + startingPointFirstRafterX + spaceBetweenRafterVAR) / 2.5f;
                }
                if (i == 7) 
                {
                    yCordinate = startingPointFirstRafterY + (int) hypotenuse * 2 - 93;
                }
            }
            drawing += "<rect class='stolper' x='" + xCordinate + "' y='" + yCordinate + "' height='9.7' width='9.7' fill='none' stroke='black' stroke-width='3px'/>";
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
        
        int hypotenuse = hypotenuseCalc(c);
        
        float startingPointFirstRafterX = 50;
        float startingPointFirstRafterY = 50;
        
        drawing += "<rect class='remmen' x='" + startingPointFirstRafterX + "' y='" + startingPointFirstRafterY + "' height='4.5' width='" + length + "' fill='none' stroke='black' stroke-width='3px' />";
        drawing += "<rect class='remmen' x='" + startingPointFirstRafterX + "' y='" + (startingPointFirstRafterY + hypotenuse * 2 - 90) + "' height='4.5' width='" + length + "' fill='none' stroke='black' stroke-width='3px' />";
        
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
        
        int hypotenuse = hypotenuseCalc(c);
        
        float startingPointFirstRafterX = 50;
        float startingPointFirstRafterY = 50;

        BOMFundament f = new BOMFundament();
        
        int quantityOfRaftersPlusTheBackRafter = f.calculateQuantityOfRafterIncludedBackRafter(length, 90);

        //set the starting point for rafter ( - 15 ) because ) because rafter have 15 cm space from post on sides
        float frontSpærPlacementX = startingPointFirstRafterX;
        float frontSpærPlacementY = startingPointFirstRafterY - 15;
        
        for (int i = 0; i < quantityOfRaftersPlusTheBackRafter; i++) 
        {
            drawing += "<rect x='" + frontSpærPlacementX + "' y='" + frontSpærPlacementY + "' height='" + (hypotenuse * 2 - 60) + "' width='10' fill='none' stroke='black' stroke-width='3px'/>";
            frontSpærPlacementX += f.spaceBetweenRafter(length, 90) + 3f;
        }
        
        return drawing;
    }
    
    /**
     * 
     * @param c
     * @return string for placement of laths (lægter)
     */
    private String drawLaths(Carport c)
    {
        String drawing = "";
        
        int length = c.getLength();
        
        int hypotenuse = hypotenuseCalc(c);
        
        
        float startingPointFirstRafterY = 50;
        
        BOMFundament f = new BOMFundament();
        BOMRoofPackage r = new BOMRoofPackage();
        
        int quantityOfLathsPlusBackLath = r.amountOfLaths(c) - 1;

        float frontLathPlacementX = 30;
        float frontLathPlacementY = startingPointFirstRafterY - 15;

        for (int i = 0; i < quantityOfLathsPlusBackLath; i++) {
            drawing += "<rect x='" + frontLathPlacementX + "' y='" + frontLathPlacementY + "' height='" + 10 + "' width='" + (length + 40) + "' fill='lightgrey' stroke='black' stroke-width='3px'/>";
            frontLathPlacementY += f.spaceBetweenRafter(hypotenuse, 40) + 3f;
        }
        //middle lath
        drawing += "<rect x='" + frontLathPlacementX + "' y='" + (frontLathPlacementY - 25) + "' height='" + 10 + "' width='" + (length + 40) + "' fill='grey' stroke='black' stroke-width='3px'/>";
        frontLathPlacementY += f.spaceBetweenRafter(hypotenuse, 40) + (3f - 50);
        
        for (int i = 0; i < quantityOfLathsPlusBackLath; i++) {
            drawing += "<rect x='" + frontLathPlacementX + "' y='" + frontLathPlacementY + "' height='" + 10 + "' width='" + (length + 40) + "' fill='lightgrey' stroke='black' stroke-width='3px'/>";
            frontLathPlacementY += f.spaceBetweenRafter(hypotenuse, 40) + 3f;
        }
        
        return drawing;
    }
    
    /**
     * 
     * @param c
     * @return string for placement of shed clothing
     */
    private String drawShedClothing(Carport c)
    {
        //If shed is null, no reason to do the rest of the code
        if(c.getShed() == null)
        {
            return "";
        }
        
        String drawing = "";
        
        int length = c.getLength();
        Shed shed = c.getShed();
        int slength = shed.getLength();
        int swidth = shed.getWidth();
        
        int hypotenuse = hypotenuseCalc(c);
        
        float startingPointFirstRafterX = 50;
        float startingPointFirstRafterY = 50;
        
        //from front bottom to back bottom post
        drawing += "<line x1='" + (length - slength + 47) + "' y1='" + (startingPointFirstRafterY + (int) hypotenuse * 2 - 81) +
                "' x2='" + (startingPointFirstRafterX + length + 3) + "' y2='" + (startingPointFirstRafterY + (int) hypotenuse * 2 - 80) + 
                "' style='stroke:red;stroke-width:2'/>";
        //From front bottom to front top post 
        drawing += "<line x1='" + (length - slength + 47) + "' y1='" + (startingPointFirstRafterY + (int) hypotenuse * 2 - 81) +
                "' x2='" + (length - slength + 47) + "' y2='" + (startingPointFirstRafterY + (int) hypotenuse * 2 - 93 - swidth) + 
                "' style='stroke:red;stroke-width:2'/>";
        //from front top to back top post
        drawing += "<line x1='" + (length - slength + 47) + "' y1='" + (startingPointFirstRafterY + (int) hypotenuse * 2 - 93 - swidth) + 
                "' x2='" + (startingPointFirstRafterX + length + 3) + "' y2='" + (startingPointFirstRafterY + (int) hypotenuse * 2 - 93 - swidth) +
                "' style='stroke:red;stroke-width:2'/>";
        //from back top to back bottom post
        drawing += "<line x1='" + (startingPointFirstRafterX + length + 3) + "' y1='" + (startingPointFirstRafterY + (int) hypotenuse * 2 - 81) + 
                "' x2='" + (startingPointFirstRafterX + length + 3) + "' y2='" + (startingPointFirstRafterY + (int) hypotenuse * 2 - 93 - swidth) +
                "' style='stroke:red;stroke-width:2'/>";
        
        return drawing;
    }
    
    /**
     * 
     * @param c
     * @return string for dimensions of drawing
     */
    private String drawDimensions(Carport c)
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
        
        int hypotenuse = hypotenuseCalc(c);
        
        float startingPointFirstRafterY = 50;
        
        BOMFundament f = new BOMFundament();

        
        //Line for spaces between lath
        drawing += "<line x1='20' y1='45' x2='20' y2='" + (startingPointFirstRafterY + 25) + "' style='stroke:black;stroke-width:2'/>";
        drawing += "<text x='-80' transform='rotate(-90)' y='15' fill='black'>" + (int) f.spaceBetweenRafter(length, 40) + " cm</text>";


        //line for widh of carport
        drawing += "<line x1='25' y1='35' x2='25' y2='" + (hypotenuse * 2 - 17) + "' style='stroke:black;stroke-width:2'/>";
        drawing += "<text x='" + (-hypotenuse * 2 + 23) + "' transform='rotate(-90)' y='20' fill='black'>" + width + " cm</text>";

        //Line for length of carport
        drawing += "<line x1='25' y1='30' x2='" + (length + 70) + "' y2='30' style='stroke:black;stroke-width:2'/>";
        drawing += "<text x='25' y='25' fill='black'>" + length + " cm</text>";
        
        //line for space between rafter
        drawing += "<line x1='" + (length - 23) + "' y1='25' x2='" + (length + 40) + "' y2='25' style='stroke:black;stroke-width:2'/>";
        drawing += "<text x='" + (length - 23) + "' y='20' fill='black'>" + (int) f.spaceBetweenRafter(length, 60) + " cm</text>";
        
        if (shed != null) 
        {
            //line for shed width
            drawing += "<line x1='" + (length+50 - slength) + "' y1='" + ((int) hypotenuse * 2-10) + 
                    "' x2='" + (length+50) + "' y2='" + ((int) hypotenuse * 2-10) + "' style='stroke:red;stroke-width:2'/>";
            drawing += "<text x='" + (length + 50 - slength) + "' y='" + ((int) hypotenuse * 2) + "' fill='red'>" + c.getShed().getLength() +
                    " cm</text>";
            //line for shed length
            drawing += "<line x1='" + (length+75) + "' y1='" + ((int) hypotenuse * 2-40) + "' x2='" + (length+75) + "' y2='" 
                    + (((int) hypotenuse * 2-40) - swidth) + "' style='stroke:red;stroke-width:2'/>";
            drawing += "<text transform='rotate(90)' x='" + ((int) hypotenuse * 2 - 80) + "' y='" + (-length - 75) + "' fill='red'>" + c.getShed().getWidth()
                    + " cm</text>";
        }
        
        return drawing;
    }
    
    /**
     * 
     * @param c
     * @return int containing the hypotenuse
     */
    private int hypotenuseCalc(Carport c)
    {
        int width =c.getWidth();
        
        double inclination = c.getInclination();
        int triangleWidth = width / 2; // width of each triangle.
        inclination = Math.toRadians(inclination); //Math.cos expects radians
        double hypotenuse_ = triangleWidth / (double) Math.cos(inclination);
        int hypotenuse = (int) hypotenuse_;
        
        return hypotenuse;
    }

}
