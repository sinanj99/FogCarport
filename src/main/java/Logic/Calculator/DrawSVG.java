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

    public String drawTopInclineShed(Carport c) {
        String drawingTop = "";
        InclineRoofCarportBOM ib = new InclineRoofCarportBOM();
        FlatRoofCarportBOM fb = new FlatRoofCarportBOM();
        int carportLength = c.getLength();
        int carportWidth = c.getWidth();
        int shedLength = 0;
        int amountOfRafters = fb.calculateQuantityOfSpærIncludedBackSpær(carportLength, 90);

        //rafters
        drawingTop += "<svg height='60%' width='60%' viewbox='0 0 " + (carportLength + 60) + " " + carportWidth + "' >";
        drawingTop += "<rect x='0' y='50%' width='" + (carportLength + 60) + "' height='5' fill='black' stroke='black' stroke-width='5'/>";
        drawingTop += "<rect x='33' y='0' height='" + (carportWidth) + "' width='15' fill='none' stroke='black' stroke-width='3'/>";

        int position = 0;
        System.out.println(ib.spaceBetweenRafters(carportLength, shedLength));
        for (int i = 0; i < amountOfRafters; i++) {
            position += fb.spaceBetweenSpær(fb.calculateQuantityOFSpærExcluedBackSpær(carportLength, 90), carportLength, 90);
            drawingTop += "<rect x='" + (position + 30) + "' y='0' width='15' height='100%' fill='none' stroke='black' stroke-width='3'/>";
        }
        //laths
        position = 0;
        drawingTop += "<rect x='0' y='" + (position + 30) + "' width='" + (carportLength + 60) + "' height='5' fill='none' stroke='black' stroke-width='3'/>";
        System.out.println("SPACELENGTH = " + ib.spaceLength(carportWidth, c.getInclination()));
        for (int i = 0; i < ib.amountOfLaths(carportWidth, c.getInclination()) / 2 - 1; i++) {

            position += ib.spaceLength(carportWidth, c.getInclination());

            drawingTop += "<rect x='0' y='" + (position + 30) + "' width='" + (carportLength + 60) + "' height='5' fill='none' stroke='black' stroke-width='3'/>";
        }
        drawingTop += "<rect x='0' y='" + (carportWidth - 30) + "' width='" + (carportLength + 60) + "' height='5' fill='none' stroke='black' stroke-width='3'/>";
        position = carportWidth;
        for (int i = ib.amountOfLaths(carportWidth, c.getInclination()) / 2 - 1; i > 0; i--) {

            position -= ib.spaceLength(carportWidth, c.getInclination());

            drawingTop += "<rect x='0' y='" + (position - 30) + "' width='100%' height='5' fill='none' stroke='black' stroke-width='3'/>";
        }

        drawingTop += "</svg>";
        return drawingTop;

    }

    public String drawTopInclineNoShed(Carport c) {
        String drawingTop = "";
        InclineRoofCarportBOM ib = new InclineRoofCarportBOM();
        FlatRoofCarportBOM fb = new FlatRoofCarportBOM();
        int carportLength = c.getLength();
        int carportWidth = c.getWidth();
        int shedLength = 0;
        int amountOfRafters = ib.amountOfRafters(carportLength);

        //rafters
        drawingTop += "<svg height='900' width='800' viewbox='0 0 " + (carportLength + 60) + " " + carportWidth + "' >";
        drawingTop += "<rect x='0' y='50%' width='100%' height='5' fill='black' stroke='black' stroke-width='5'/>";
        drawingTop += "<rect x='33' y='0' height='100%' width='15' fill='none' stroke='black' stroke-width='3'/>";
        int position = 0;
        System.out.println(ib.spaceBetweenRafters(carportLength, shedLength));
        for (int i = 0; i < amountOfRafters; i++) {
            position += ib.spaceBetweenRafters(carportLength, shedLength);
            drawingTop += "<rect x='" + (position + 30) + "' y='0' width='15' height='100%' fill='none' stroke='black' stroke-width='3'/>";
        }
        //laths
        position = 0;
        drawingTop += "<rect x='0' y='" + (position + 30) + "' width='100%' height='5' fill='none' stroke='black' stroke-width='3'/>";
        System.out.println("SPACELENGTH = " + ib.spaceLength(carportWidth - 60, c.getInclination()));
        for (int i = 0; i < ib.amountOfLaths(carportWidth - 60, c.getInclination()) / 2 - 1; i++) {

            position += ib.spaceLength(carportWidth, c.getInclination());

            drawingTop += "<rect x='0' y='" + (position + 30) + "' width='100%' height='5' fill='none' stroke='black' stroke-width='3'/>";
        }
        drawingTop += "<rect x='0' y='" + (carportWidth - 30) + "' width='100%' height='5' fill='none' stroke='black' stroke-width='3'/>";
        position = carportWidth;
        for (int i = ib.amountOfLaths(carportWidth - 60, c.getInclination()) / 2 - 1; i > 0; i--) {

            position -= ib.spaceLength(carportWidth, c.getInclination());

            drawingTop += "<rect x='0' y='" + (position - 30) + "' width='100%' height='5' fill='none' stroke='black' stroke-width='3'/>";
        }

        drawingTop += "</svg>";
        return drawingTop;

    }

    public String drawPerforatedBand(Carport c) {
        String drawingTop = "";
        InclineRoofCarportBOM ib = new InclineRoofCarportBOM();
        FlatRoofCarportBOM fb = new FlatRoofCarportBOM();
        int carportLength = c.getLength();
        int carportWidth = c.getWidth();
        int shedLength = 0;
        int amountOfRafters = fb.calculateQuantityOfSpærIncludedBackSpær(carportLength, 90);

        //rafters
        drawingTop += "<svg height='60%' width='60%' viewbox='0 0 " + (carportLength + 60) + " " + carportWidth + "' >";
        drawingTop += "<rect x='0' y='50%' width='" + (carportLength + 60) + "' height='5' fill='black' stroke='black' stroke-width='5'/>";
        drawingTop += "<rect x='33' y='0' height='" + (carportWidth) + "' width='15' fill='none' stroke='black' stroke-width='3'/>";

        int position = 0;
        System.out.println(ib.spaceBetweenRafters(carportLength, shedLength));
        for (int i = 0; i < amountOfRafters; i++) {
            position += fb.spaceBetweenSpær(fb.calculateQuantityOFSpærExcluedBackSpær(carportLength, 90), carportLength, 90);
            drawingTop += "<rect x='" + (position + 30) + "' y='0' width='15' height='100%' fill='none' stroke='black' stroke-width='3'/>";
        }
        //laths
        position = 0;
        drawingTop += "<rect x='0' y='" + (position + 30) + "' width='" + (carportLength + 60) + "' height='5' fill='none' stroke='black' stroke-width='3'/>";
        System.out.println("SPACELENGTH = " + ib.spaceLength(carportWidth, c.getInclination()));
        for (int i = 0; i < ib.amountOfLaths(carportWidth, c.getInclination()) / 2 - 1; i++) {

            position += ib.spaceLength(carportWidth, c.getInclination());

            drawingTop += "<rect x='0' y='" + (position + 30) + "' width='" + (carportLength + 60) + "' height='5' fill='none' stroke='black' stroke-width='3'/>";
        }
        drawingTop += "<rect x='0' y='" + (carportWidth - 30) + "' width='" + (carportLength + 60) + "' height='5' fill='none' stroke='black' stroke-width='3'/>";
        position = carportWidth;
        for (int i = ib.amountOfLaths(carportWidth, c.getInclination()) / 2 - 1; i > 0; i--) {

            position -= ib.spaceLength(carportWidth, c.getInclination());

            drawingTop += "<rect x='0' y='" + (position - 30) + "' width='100%' height='5' fill='none' stroke='black' stroke-width='3'/>";
        }

        drawingTop += "</svg>";
        return drawingTop;
    }

    public String drawFlat(Carport c) {
        String drawing = "";
        int length = c.getLength();
        int width = c.getWidth();
        Shed shed = c.getShed_();
        int slength = shed.getLength();
        FlatRoofCarportBOM f = new FlatRoofCarportBOM();
        drawing += "<svg height='80%' width='80%' viewbox='0 0 " + (length + 60) + " " + width + "' >";

        float spaceBetweenSpærVAR = f.spaceBetweenSpær(f.calculateQuantityOFSpærExcluedBackSpær(length, 60), length, 60);

        float startingPointFirstSpærX = 50;
        float startingPointFirstSpærY = 50;

        float xCordinate = startingPointFirstSpærX + spaceBetweenSpærVAR;
        float yCordinate = startingPointFirstSpærY;

        int quantityOfStolper = f.calculateQuantityOfStolper(length, shed, slength);

        for (int i = 0; i < quantityOfStolper; i++) {
            drawing += "<rect x='" + xCordinate + "' y='" + yCordinate + "' height='9.7' width='9.7' fill='none' stroke='black' stroke-width='3px'/>";
            if (i == 0) {
                xCordinate = startingPointFirstSpærX + length - spaceBetweenSpærVAR;
            }
            if (i == 1) {
                yCordinate = startingPointFirstSpærY + width - 30;
            }
            if (i == 2) {
                xCordinate = startingPointFirstSpærX + spaceBetweenSpærVAR;
            }
            if (i == 3) {
                xCordinate = (startingPointFirstSpærX + length - spaceBetweenSpærVAR) - slength;
            }
            if (i == 4) {
                yCordinate = startingPointFirstSpærY;
            }
            if (i == 5) {
                yCordinate = yCordinate + (width - 30) / 2;
            }
            if (i == 6) {
                xCordinate = startingPointFirstSpærX + length - spaceBetweenSpærVAR;
            }
        }

        drawing += "</svg>";
        return drawing;
    }

}
