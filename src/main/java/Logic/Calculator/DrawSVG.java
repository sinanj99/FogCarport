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

    /**
     * Draws top of carport
     *
     * @param c
     * @param ic
     * @return a string representation of the html code needed to create svg
     * element
     */
    public String drawTopInclineWithShed(Carport c, InclineRoofCarportBOM ic) {
        int inclination = c.getInclination();
        int width = c.getWidth();
        int half = width / 2;
        Shed shed = c.getShed_();
        int shedLength = shed.getLength();
        // amount of laths for 1 of the sides
        int amountOneSide = ic.amountOfLaths(width, inclination) / 2;
        System.out.println("AMOUNT ONE SIDE : " + amountOneSide);
        // amount of spaces for 1 of the sides
        int lengthOfSpace = ic.spaceLength(width, inclination);
        System.out.println("spacelength");
        System.out.println("HALF: " + half);
        System.out.println("LENGTH OF SPACE: " + lengthOfSpace);
        String drawingTop = "";

        int carportWidth = c.getWidth() + 50; // + 20 since we need space for text
        int carportLength = c.getLength() + 50;
        int spaceBetweenRafters = ic.spaceBetweenRafters(carportLength, shed, shedLength);

        drawingTop += "<svg height='50%' width='50%' viewbox='0 0 " + (carportLength +35)+ " " + carportWidth + "'>";

        //full carport
        drawingTop += "<rect height='100%' width='100%' fill='none' stroke='none'/>";

        //middle lath
        drawingTop += "<rect x='3' y='50%' width='"+(carportLength+25)+"' fill='none' height='10' stroke='black' stroke-width='5'/>";
        int position = 32;
        //first rafter 
        drawingTop += "<rect x='30' y='2' height='" + (carportWidth - 3) + "' width='10' fill='none' stroke='black' stroke-width='5'/>";
        for (int i = 0; i < ic.amountOfRaftersNoShed(carportLength, shedLength)-1; i++) {
            position += spaceBetweenRafters;
            drawingTop += "<rect x='" + position + "' y='2' height='" + (carportWidth - 3) + "' width='10' fill='none' stroke='black' stroke-width='3'/>";
        }
        //last rafter
        drawingTop += "<rect x='" + (carportLength - 13) + "' y='2' height='" + (carportWidth - 3) + "' width='10' fill='none' stroke='black' stroke-width='5'/>";

        //shed start
        drawingTop += "<rect x='" + (carportLength - shedLength) + "' y='2' height='" + (carportWidth - 3) + "' width='10' fill='none' stroke='black' stroke-width='5'/>";
        //mid shed
        drawingTop += "<rect x='" + (carportLength - shedLength + shedLength / 2-5) + "' y='2' height='" + (carportWidth - 3) + "' width='10' fill='none' stroke='black' stroke-width='3'/>";

        drawingTop += "</svg>";

//        //full carport
//        drawingTop += "<rect x='0' y ='0' width='" + carportLength + "' height='" + carportWidth + "' fill='none' stroke='black' stroke-width='2' />";
//        //top lath 
//        int position = 0;
//        for (int i = 0; i < amountOneSide; i++) {
//            if (i == 0) {
//                position = 35;
//            } else if (i == amountOneSide - 1) {
//                position = half - 30;
//            } else {
//                position += lengthOfSpace;
//            }
//            drawingTop += "<rect x='0' y ='" + position + "' width='" + carportLength + "' height='" + 3 + "' fill='none' stroke='black'/>";
//        }
//        position = 0;
//        drawingTop += "<rect x='0' y ='" + half + "' width='" + carportLength + "' height='" + 2 + "' fill='black'/>";
//
//        for (int i = 0; i < amountOneSide; i++) {
//            if (i == 0) {
//                position = half + 30;
//            } else if (i == amountOneSide ) {
//                position = width - 35;
//            } else {
//                position += lengthOfSpace;
//            }
//            drawingTop += "<rect x='0' y ='" + position + "' width='" + carportLength + "' height='" + 3 + "' fill='none' stroke='black'/>";
//        }
//        drawingTop += "</svg>";
        return drawingTop;
    }
}
