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
public class DrawSVGIncline {

    public String drawTopIncline(Carport c) {
        String drawing = "";
        int length = c.getLength();
        int width = c.getWidth();
        Shed shed = c.getShed_();
        int slength = 0;
        int swidth = 0;

        if (shed != null) {
            slength = shed.getLength();
            swidth = shed.getWidth();
        }

        double inclination = c.getInclination();
        int triangleWidth = width / 2; // width of each triangle.
        inclination = Math.toRadians(inclination); //Math.cos expects radians
        double hypotenuse = triangleWidth / Math.cos(inclination);
        BOMFlatRoof f = new BOMFlatRoof();
        BOMInclineRoof ic = new BOMInclineRoof();
        drawing += "<svg height='100%' width='100%' viewbox='-150 100 900 900' >";

        float spaceBetweenSpærVAR = f.spaceBetweenSpær(c, 90);

        float startingPointFirstSpærX = 50;
        float startingPointFirstSpærY = 50;

        float xCordinate = startingPointFirstSpærX + spaceBetweenSpærVAR;
        float yCordinate = startingPointFirstSpærY;

        int quantityOfStolper = f.calculateQuantityOfStolper(c);

        // REM
        drawing += "<rect class='remmen' x='" + startingPointFirstSpærX + "' y='" + startingPointFirstSpærY + "' height='4.5' width='" + length + "' fill='none' stroke='black' stroke-width='3px' />";
        drawing += "<rect class='remmen' x='" + startingPointFirstSpærX + "' y='" + (startingPointFirstSpærY + hypotenuse * 2 - 90) + "' height='4.5' width='" + length + "' fill='none' stroke='black' stroke-width='3px' />";

        //SPÆR
        int quantityOfRafters = f.calculateQuantityOFSpærExcluedBackSpær(c, 90);
        int quantityOfRaftersPlusTheBackRafter = f.calculateQuantityOfSpærIncludedBackSpær(c, 90);

        float frontSpærPlacementX = startingPointFirstSpærX;
        float frontSpærPlacementY = startingPointFirstSpærY - 15;

        for (int i = 0; i < quantityOfRaftersPlusTheBackRafter; i++) {
            drawing += "<rect x='" + frontSpærPlacementX + "' y='" + frontSpærPlacementY + "' height='" + (hypotenuse * 2 - 60) + "' width='10' fill='none' stroke='black' stroke-width='3px'/>";
            frontSpærPlacementX += f.spaceBetweenSpær(c, 60) + 3f;
        }
        //Lægter

        int quantityOfLathsPlusBackLath = f.calculateQuantityOfSpærIncludedBackSpær((int) hypotenuse, 40) - 1;

        float frontLathPlacementX = 30;
        float frontLathPlacementY = startingPointFirstSpærY - 15;

        for (int i = 0; i < quantityOfLathsPlusBackLath; i++) {
            drawing += "<rect x='" + frontLathPlacementX + "' y='" + frontLathPlacementY + "' height='" + 10 + "' width='" + (length + 40) + "' fill='lightgrey' stroke='black' stroke-width='3px'/>";
            frontLathPlacementY += f.spaceBetweenSpær(f.calculateQuantityOFSpærExcluedBackSpær((int) hypotenuse, 40), (int) hypotenuse, 40) + 3f;
        }
        //middle lath
        drawing += "<rect x='" + frontLathPlacementX + "' y='" + (frontLathPlacementY - 25) + "' height='" + 10 + "' width='" + (length + 40) + "' fill='grey' stroke='black' stroke-width='3px'/>";
        frontLathPlacementY += f.spaceBetweenSpær(f.calculateQuantityOFSpærExcluedBackSpær((int) hypotenuse, 40), (int) hypotenuse, 40) + (3f - 50);

        for (int i = 0; i < quantityOfLathsPlusBackLath; i++) {
            drawing += "<rect x='" + frontLathPlacementX + "' y='" + frontLathPlacementY + "' height='" + 10 + "' width='" + (length + 40) + "' fill='lightgrey' stroke='black' stroke-width='3px'/>";
            frontLathPlacementY += f.spaceBetweenSpær(f.calculateQuantityOFSpærExcluedBackSpær((int) hypotenuse, 40), (int) hypotenuse, 40) + 3f;
        }

        //STOLPER 
        for (int i = 0; i < quantityOfStolper; i++) {
            if (shed != null && shed.getLength() != 0) {
                //Top left stolpe
                if (i == 0) {
                    //yCordinate changed to make place it under remmen
                    xCordinate = startingPointFirstSpærX + spaceBetweenSpærVAR / 2;
                    yCordinate -= 3.6f;
                }
                //Top right stolpe
                if (i == 1) {
                    //xCordinate changed to place it at the second last spær
                    xCordinate = startingPointFirstSpærX + length - 9.7f;
                }
                //Botton right stolpe
                if (i == 2) {
                    //yCordinate changed to place it at the at the other rem 
                    yCordinate = startingPointFirstSpærY + (int) hypotenuse * 2 - 93;
                }
                //Bottom left stolpe
                if (i == 3) {
                    //xCordinato changed to place it at the same xCordinate at the top left stolpe
                    xCordinate = startingPointFirstSpærX + spaceBetweenSpærVAR / 2;
                }
                //The bottom front stolpe of the shed
                if (i == 4) {
                    //xCprdinate changed to place it the length of the shed away from the bottom right stolpe
                    //yCordinate = (int)hypotenuse*2-(((int)hypotenuse*2-swidth)/2);
                    xCordinate = (startingPointFirstSpærX + length) - slength;
                }
                //The top front stolpe of the shed
                if (i == 5) {
                    //Changed yCordinate to place it the width of the shed away
                    yCordinate -= swidth - 2.4;
                }
                //the front middle stolpe of the shed
                if (i == 6) {
                    //yCordinate changed to place it between the front bottom and top stolpe of the shed
                    yCordinate = ((startingPointFirstSpærY + (int) hypotenuse * 2 - 93) + yCordinate) / 2;
                    //((int)hypotenuse);
                }
                //the back middle stolpe of the shed
                if (i == 7) {
                    //xCordinate changed to place it at the same xCordinate as the rigth top/bottom stolpe
                    xCordinate = startingPointFirstSpærX + length - 9.7f;
                }
                //Stolpe between the top left stolpe and top front stolpe of the shed
                if (i == 8) {

                    //function as top right corner stolpe for the shed
                    if (quantityOfStolper == 9) {
                        //yCordiante changed so it is placed at the botton right corner stolpe
                        yCordinate = startingPointFirstSpærY + (int) hypotenuse * 2 - 93;
                        //yCordinate changed so is placed shedwidth away
                        yCordinate -= swidth - 2.4;

                    } else {
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
                if (i == 10) {
                    //yCordiante changed so it is placed at the carportwidth
                    yCordinate -= swidth - 2.4;
                    //xCordinate changed so it is placed at the same xCordinate at the top right corner stolpe for the carport
                    xCordinate = startingPointFirstSpærX + length - spaceBetweenSpærVAR - 9.7f;
                }
            }
            if (slength == 0) {
                //Top left stolpe
                if (i == 0) {
                    //yCordinate changed to make place it under remmen
                    xCordinate = startingPointFirstSpærX + spaceBetweenSpærVAR / 2;
                    yCordinate -= 3.6f;
                }
                //Top right stolpe
                if (i == 1) {
                    //xCordinate changed to place it at the second last spær
                    xCordinate = startingPointFirstSpærX + length - 9.7f;
                }
                //Botton right stolpe
                if (i == 2) {
                    //yCordinate changed to place it at the at the other rem 
                    yCordinate = startingPointFirstSpærY + (int) hypotenuse * 2 - 93;
                }
                //Bottom left stolpe
                if (i == 3) {
                    //xCordinato changed to place it at the same xCordinate at the top left stolpe
                    xCordinate = startingPointFirstSpærX + spaceBetweenSpærVAR / 2;
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
                    yCordinate = startingPointFirstSpærY + (int) hypotenuse * 2 - 93;
                }
            }
            drawing += "<rect class='stolper' x='" + xCordinate + "' y='" + yCordinate + "' height='9.7' width='9.7' fill='none' stroke='black' stroke-width='3px'/>";
        }
        //lengths
        drawing += "<line x1='20' y1='45' x2='20' y2='" + (startingPointFirstSpærY + 25) + "' style='stroke:black;stroke-width:2'/>";
        drawing += "<text x='-80' transform='rotate(-90)' y='15' fill='black'>" + (int) f.spaceBetweenSpær(f.calculateQuantityOFSpærExcluedBackSpær((int) hypotenuse, 40), (int) hypotenuse, 40) + " cm</text>";
        drawing += "<line x1='25' y1='35' x2='25' y2='" + (hypotenuse * 2 - 17) + "' style='stroke:black;stroke-width:2'/>";
        drawing += "<text x='" + (-hypotenuse * 2 + 23) + "' transform='rotate(-90)' y='20' fill='black'>" + width + " cm</text>";

        drawing += "<line x1='25' y1='30' x2='" + (length + 70) + "' y2='30' style='stroke:black;stroke-width:2'/>";
        drawing += "<text x='25' y='25' fill='black'>" + length + " cm</text>";
        drawing += "<line x1='" + (length - 23) + "' y1='25' x2='" + (length + 40) + "' y2='25' style='stroke:black;stroke-width:2'/>";
        drawing += "<text x='" + (length - 23) + "' y='20' fill='black'>" + (int) f.spaceBetweenSpær(f.calculateQuantityOFSpærExcluedBackSpær(length, 60), length, 60) + " cm</text>";
        if (c.getShed_() != null) {
            drawing += "<line x1='" + (length+50 - slength) + "' y1='" + ((int) hypotenuse * 2-10) + "' x2='" + (length+50) + "' y2='" + ((int) hypotenuse * 2-10) + "' style='stroke:red;stroke-width:2'/>";
            drawing += "<text x='" + (length+50 - slength) + "' y='"+((int) hypotenuse * 2+7)+"' fill='red'>" + c.getShed_().getLength() + " cm</text>";
            drawing += "<line x1='" + (length+75) + "' y1='" + ((int) hypotenuse * 2-40) + "' x2='" + (length+75) + "' y2='" + ((int) hypotenuse * 2-10-c.getWidth()) + "' style='stroke:red;stroke-width:2'/>";
            drawing += "<text transform='rotate(90)' x='" +(-(int) hypotenuse * 2-10-c.getWidth()) + "' y='"+(length+75)+"' fill='red'>" + c.getShed_().getLength() + " cm</text>";
        }
        //beklædning
        drawing += "<line x1='" + (length - slength + 47) + "' y1='" + (startingPointFirstSpærY + (int) hypotenuse * 2 - 93) + "' x2='" + (startingPointFirstSpærX + length) + "' y2='" + (startingPointFirstSpærY + (int) hypotenuse * 2 - 93) + "' style='stroke:red;stroke-width:2'/>";
        drawing += "<line x1='" + (length - slength + 47) + "' y1='" + (startingPointFirstSpærY + (int) hypotenuse * 2 - 93) + "' x2='" + (length - slength + 47) + "' y2='" + (startingPointFirstSpærY + (int) hypotenuse * 2 - 93 - swidth) + "' style='stroke:red;stroke-width:2'/>";
        drawing += "<line x1='" + (length - slength + 47) + "' y1='" + (startingPointFirstSpærY + (int) hypotenuse * 2 - 93 - swidth) + "' x2='" + (startingPointFirstSpærX + length) + "' y2='" + (startingPointFirstSpærY + (int) hypotenuse * 2 - 93 - swidth) + "' style='stroke:red;stroke-width:2'/>";
        drawing += "<line x1='" + (startingPointFirstSpærX + length) + "' y1='" + (startingPointFirstSpærY + (int) hypotenuse * 2 - 93) + "' x2='" + (startingPointFirstSpærX + length) + "' y2='" + (startingPointFirstSpærY + (int) hypotenuse * 2 - 93 - swidth) + "' style='stroke:red;stroke-width:2'/>";
        drawing += "</svg>";
        return drawing;
    }

    public String drawFrontIncline(Carport c) {
        String drawing = "";
        int length = c.getLength();
        int width = c.getWidth();
        Shed shed = c.getShed_();
        int slength, swidth = 0;
        if (shed != null) {
            slength = shed.getLength();
            swidth = shed.getWidth();
        }
        BOMFlatRoof f = new BOMFlatRoof();
        BOMInclineRoof ic = new BOMInclineRoof();
        double inclination = Math.toRadians(c.getInclination());
        double hypotenuse = (width / 2) / Math.cos(inclination);
        double roofHeight = Math.sin(inclination) * hypotenuse;
        roofHeight *= 2;
        width *= 2;
        System.out.println("ROOFHEIGHT : " + roofHeight);

        drawing += "<svg height='100%' width='100%' viewbox='-300 -600 " + 1300 + " " + 1300 + "' >";
        drawing += "<rect x='0' y='" + roofHeight + "' height='20' width='" + width + "' fill='lightgray' stroke='black' stroke-width='3'/>";
        drawing += "<text x='" + (width / 2 - 50) + "' y='" + (roofHeight + 50) + "' fill='black'>Bredde: " + width / 2 + " cm</text>";
        drawing += "<text transform='rotate(-90)' x='" + (-roofHeight / 2 - 50) + "' y='" + (width / 2 - 10) + "' fill='black'>Højde: " + (int) roofHeight + " cm</text>";
        drawing += "<text x='" + (width - 80) + "' y='" + (roofHeight - 50) + "' fill='black'>Hældning: " + (int) c.getInclination() + "°</text>";
        drawing += "<line x1='" + width / 2 + "' x2='" + width / 2 + "' y1='" + roofHeight + "' y2='" + 0 + "' stroke-dasharray='5,5' stroke='black' stroke-width='3'/>";
        drawing += "<line x1='" + width + "' x2='" + width / 2 + "' y1='" + roofHeight + "' y2='" + 0 + "' stroke='black' stroke-width='3'/>";
        drawing += "<line x1='" + 0 + "' x2='" + width / 2 + "' y1='" + roofHeight + "' y2='" + 0 + "' stroke='black' stroke-width='3'/>";
        drawing += "</svg>";
        return drawing;
    }

    public String drawSideIncline(Carport c) {
        String drawing = "";
        int height = 250;
        height *= 2;
        int length = c.getLength();
        int width = c.getWidth();
        Shed shed = c.getShed_();
        if (shed != null) {
            int slength = shed.getLength();
            int swidth = shed.getWidth();
        }

        BOMFlatRoof f = new BOMFlatRoof();
        BOMInclineRoof ic = new BOMInclineRoof();
        double inclination = Math.toRadians(c.getInclination());
        double hypotenuse = (width / 2) / Math.cos(inclination);
        double roofHeight = Math.sin(inclination) * hypotenuse;
        roofHeight *= 2;
        width *= 2;
        System.out.println("ROOFHEIGHT : " + roofHeight);

        drawing += "<svg height='80%' width='80%' viewbox='0 0 " + width + 20 + " " + length + 20 + "' >";
        drawing += "<rect x='0' y='0' height='" + (height + roofHeight) + "' width='" + width + "' fill='lightgray' stroke='black' stroke-width='3'/>";
        drawing += "<text x='" + (width / 2 - 50) + "' y='" + (roofHeight + 50) + "' fill='black'>Bredde: " + width + " cm</text>";
        drawing += "<text x='" + (width / 2 + 10) + "' y='" + (roofHeight / 2) + "' fill='black'>Højde: " + (int) roofHeight + " cm</text>";
        drawing += "<text x='" + (width - 80) + "' y='" + (roofHeight - 50) + "' fill='black'>Hældning: " + (int) c.getInclination() + " cm</text>";
        drawing += "<line x1='" + width / 2 + "' x2='" + width / 2 + "' y1='" + roofHeight + "' y2='" + 0 + "' stroke-dasharray='5,5' stroke='black' stroke-width='3'/>";
        drawing += "<line x1='" + width + "' x2='" + width / 2 + "' y1='" + roofHeight + "' y2='" + 0 + "' stroke='black' stroke-width='3'/>";
        drawing += "<line x1='" + 0 + "' x2='" + width / 2 + "' y1='" + roofHeight + "' y2='" + 0 + "' stroke='black' stroke-width='3'/>";
        drawing += "</svg>";
        return drawing;
    }

//    public String drawPerforatedBand(Carport c) {
//        String drawingTop = "";
//        InclineRoofCarportBOM ib = new InclineRoofCarportBOM();
//        FlatRoofCarportBOM fb = new FlatRoofCarportBOM();
//        int carportLength = c.getLength();
//        int carportWidth = c.getWidth();
//        double hypotenuse = Math.sqrt(((carportLength - c.getShed_().getLength()) * (carportLength - c.getShed_().getLength()))
//                + ((carportWidth - c.getShed_().getWidth()) * (carportWidth - c.getShed_().getWidth())));
//        double bandLengthPercent = ((100.0 / (carportLength * 1.0))) * hypotenuse;
//        if (c.getShed_().getLength() == 0) {
//            bandLengthPercent = 100.0;
//        }
//        //double hypotenuse = Math.sqrt(((carportLength - c.getShed_().getLength()) * (carportLength - c.getShed_().getLength())) 
//        //      + ((carportWidth - c.getShed_().getWidth()) * (carportWidth - c.getShed_().getWidth())));
//        //double bandLengthPercent = (((100.0 / (carportLength * 1.0))) * hypotenuse); //- ((100.0 / carportLength * 1.0) * 30) 
//        //if(c.getShed_().getLength() == 0) bandLengthPercent = 100.0;
//        int shedLength = 0;
//        int amountOfRafters = fb.calculateQuantityOfSpærIncludedBackSpær(carportLength, 90);
//
//        //rafters
//        drawingTop += "<svg height='500' width='400' viewbox='0 0 " + (carportLength + 60) + " " + carportWidth + "' >";
//        drawingTop += "<rect x='0' y='50%' width='" + (carportLength + 60) + "' height='5' fill='black' stroke='black' stroke-width='5'/>";
//        drawingTop += "<rect x='33' y='0' height='" + (carportWidth) + "' width='15' fill='none' stroke='black' stroke-width='3'/>";
//
//        int position = 0;
//        System.out.println(ib.spaceBetweenRafters(carportLength, shedLength));
//        for (int i = 0; i < amountOfRafters; i++) {
//            position += fb.spaceBetweenSpær(fb.calculateQuantityOFSpærExcluedBackSpær(carportLength, 90), carportLength, 90);
//            drawingTop += "<rect x='" + (position + 30) + "' y='0' width='15' height='100%' fill='none' stroke='black' stroke-width='3'/>";
//        }
//        //laths
//        position = 0;
//        drawingTop += "<rect x='0' y='" + (position + 30) + "' width='" + (carportLength + 60) + "' height='5' fill='none' stroke='black' stroke-width='3'/>";
//        System.out.println("SPACELENGTH = " + ib.spaceLength(carportWidth, c.getInclination()));
//        for (int i = 0; i < ib.amountOfLaths(carportWidth, c.getInclination()) / 2 - 1; i++) {
//
//            position += ib.spaceLength(carportWidth, c.getInclination());
//
//            drawingTop += "<rect x='0' y='" + (position + 30) + "' width='" + (carportLength + 60) + "' height='5' fill='none' stroke='black' stroke-width='3'/>";
//        }
//        drawingTop += "<rect x='0' y='" + (carportWidth - 30) + "' width='" + (carportLength + 60) + "' height='5' fill='none' stroke='black' stroke-width='3'/>";
//        position = carportWidth;
//        for (int i = ib.amountOfLaths(carportWidth, c.getInclination()) / 2 - 1; i > 0; i--) {
//
//            position -= ib.spaceLength(carportWidth, c.getInclination());
//
//            drawingTop += "<rect x='0' y='" + (position - 30) + "' width='100%' height='5' fill='none' stroke='black' stroke-width='3'/>";
//        }
//
//        int minusRafters = 2;
//        if (c.getShed_().getLength() > 0) {
//            minusRafters = 1;
//        }
//        double startPoint = 33 + ib.spaceBetweenRafters(carportLength, shedLength);
//        double endPoint = (33 + (ib.spaceBetweenRafters(carportLength, shedLength) * (amountOfRafters - minusRafters)));
//        drawingTop += "<line x1=\"" + startPoint + "\" y1=\"0\" x2=\"" + endPoint + "\" y2=\"100%\" style=\"stroke:blue; stroke-width:5\" />"
//                + "<line x1=\"" + startPoint + "\" y1=\"100%\" x2=\"" + endPoint + "\" y2=\"0\" style=\"stroke:blue; stroke-width:5\" />";
//        drawingTop += "</svg>";
//        return drawingTop;
//    }
}
