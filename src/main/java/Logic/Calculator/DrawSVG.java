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
        String drawing = "";
        InclineRoofCarportBOM ib = new InclineRoofCarportBOM();
        FlatRoofCarportBOM fb = new FlatRoofCarportBOM();
        int carportLength = c.getLength();
        int carportWidth = c.getWidth();
        int shedLength = 0;
        int amountOfRafters = ib.amountOfRafters(carportLength, shedLength);
        
        //rafters
        drawing += "<svg height='30%' width='30%' viewbox='0 0 " + (carportLength + 60)+ " " + carportWidth + "' >";
        drawing += "<rect x='0' y='50%' width='100%' height='5' fill='black' stroke='black' stroke-width='5'/>";
        drawing += "<rect x='33' y='0' height='100%' width='15' fill='none' stroke='black' stroke-width='3'/>";
        int position = 0;
        System.out.println(ib.spaceBetweenRafters(carportLength, shedLength));
        for (int i = 0; i < amountOfRafters; i++) {
            position += ib.spaceBetweenRafters(carportLength, shedLength);
            drawing += "<rect x='" + (position+30) +"' y='0' width='15' height='100%' fill='none' stroke='black' stroke-width='3'/>";
        }
        //laths
        position = 0;
        drawing += "<rect x='0' y='"+(position+30)+"' width='100%' height='5' fill='none' stroke='black' stroke-width='3'/>";
        System.out.println("SPACELENGTH = " + ib.spaceLength(carportWidth, c.getInclination()));
        for(int i = 0; i < ib.amountOfLaths(carportWidth, c.getInclination())/2-1; i++) {
            
            position+=ib.spaceLength(carportWidth, c.getInclination());
            
            drawing += "<rect x='0' y='"+(position+30)+"' width='100%' height='5' fill='none' stroke='black' stroke-width='3'/>";
        }
        drawing += "<rect x='0' y='"+(carportWidth-30)+"' width='100%' height='5' fill='none' stroke='black' stroke-width='3'/>";
        position = carportWidth;
        for(int i = ib.amountOfLaths(carportWidth, c.getInclination())/2-1; i > 0; i--) {
            
            position-=ib.spaceLength(carportWidth, c.getInclination());
            
            drawing += "<rect x='0' y='"+(position-30)+"' width='100%' height='5' fill='none' stroke='black' stroke-width='3'/>";
        }
        

        drawing += "</svg>";
        return drawing;

    }
    
        public String drawTopInclineNoShed(Carport c) {
        String drawing = "";
        InclineRoofCarportBOM ib = new InclineRoofCarportBOM();
        FlatRoofCarportBOM fb = new FlatRoofCarportBOM();
        int carportLength = c.getLength();
        int carportWidth = c.getWidth();
        int shedLength = 0;
        int amountOfRafters = ib.amountOfRafters(carportLength, shedLength);
        
        //rafters
        drawing += "<svg height='900' width='800' viewbox='0 0 " + (carportLength + 60)+ " " + carportWidth + "' >";
        drawing += "<rect x='0' y='50%' width='100%' height='5' fill='black' stroke='black' stroke-width='5'/>";
        drawing += "<rect x='33' y='0' height='100%' width='15' fill='none' stroke='black' stroke-width='3'/>";
        int position = 0;
        System.out.println(ib.spaceBetweenRafters(carportLength, shedLength));
        for (int i = 0; i < amountOfRafters; i++) {
            position += ib.spaceBetweenRafters(carportLength, shedLength);
            drawing += "<rect x='" + (position+30) +"' y='0' width='15' height='100%' fill='none' stroke='black' stroke-width='3'/>";
        }
        //laths
        position = 0;
        drawing += "<rect x='0' y='"+(position+30)+"' width='100%' height='5' fill='none' stroke='black' stroke-width='3'/>";
        System.out.println("SPACELENGTH = " + ib.spaceLength(carportWidth-60, c.getInclination()));
        for(int i = 0; i < ib.amountOfLaths(carportWidth-60, c.getInclination())/2-1; i++) {
            
            position+=ib.spaceLength(carportWidth, c.getInclination());
            
            drawing += "<rect x='0' y='"+(position+30)+"' width='100%' height='5' fill='none' stroke='black' stroke-width='3'/>";
        }
        drawing += "<rect x='0' y='"+(carportWidth-30)+"' width='100%' height='5' fill='none' stroke='black' stroke-width='3'/>";
        position = carportWidth;
        for(int i = ib.amountOfLaths(carportWidth-60, c.getInclination())/2-1; i > 0; i--) {
            
            position-=ib.spaceLength(carportWidth, c.getInclination());
            
            drawing += "<rect x='0' y='"+(position-30)+"' width='100%' height='5' fill='none' stroke='black' stroke-width='3'/>";
        }
        

        drawing += "</svg>";
        return drawing;

    }
        
    public String drawPerforatedBand(Carport c){
        String drawing = "";
        
        
        InclineRoofCarportBOM ib = new InclineRoofCarportBOM();
        FlatRoofCarportBOM fb = new FlatRoofCarportBOM();
        int carportLength = c.getLength();
        int carportWidth = c.getWidth();
        double hypotenuse = Math.sqrt(((carportLength - c.getShed_().getLength()) * (carportLength - c.getShed_().getLength())) 
                + ((carportWidth - c.getShed_().getWidth()) * (carportWidth - c.getShed_().getWidth())));

        int shedLength = 0;
        int amountOfRafters = ib.amountOfRafters(carportLength, shedLength);
        
        double bandLengthPercent = ((100.0 / (carportLength * 1.0))) * hypotenuse; 
        if(c.getShed_().getLength() == 0) bandLengthPercent = 100.0;
        
        //rafters
        drawing += "<svg height='900' width='800' viewbox='0 0 " + carportLength + " " + carportWidth + "' >";
        drawing += "<rect x='0' y='50%' width='100%' height='5' fill='black' stroke='black' stroke-width='5'/>";
        drawing += "<rect x='33' y='0' height='100%' width='15' fill='none' stroke='black' stroke-width='3'/>";
        int position = 0;
        System.out.println(ib.spaceBetweenRafters(carportLength, shedLength));
        for (int i = 0; i < amountOfRafters; i++) {
            position += ib.spaceBetweenRafters(carportLength, shedLength);
            drawing += "<rect x='" + (position+30) +"' y='0' width='15' height='100%' fill='none' stroke='black' stroke-width='3'/>";
        }
        //laths
        position = 0;
        drawing += "<rect x='0' y='"+(position+30)+"' width='100%' height='5' fill='none' stroke='black' stroke-width='3'/>";
        System.out.println("SPACELENGTH = " + ib.spaceLength(carportWidth-60, c.getInclination()));
        for(int i = 0; i < ib.amountOfLaths(carportWidth-60, c.getInclination())/2-1; i++) {
            
            position+=ib.spaceLength(carportWidth, c.getInclination());
            
            drawing += "<rect x='0' y='"+(position+30)+"' width='100%' height='5' fill='none' stroke='black' stroke-width='3'/>";
        }
        drawing += "<rect x='0' y='"+(carportWidth-30)+"' width='100%' height='5' fill='none' stroke='black' stroke-width='3'/>";
        position = carportWidth;
        for(int i = ib.amountOfLaths(carportWidth-60, c.getInclination())/2-1; i > 0; i--) {
            
            position-=ib.spaceLength(carportWidth, c.getInclination());
            
            drawing += "<rect x='0' y='"+(position-30)+"' width='100%' height='5' fill='none' stroke='black' stroke-width='3'/>";
        }
        
        //Hulbånd
        drawing += "<line x1=\"0\" y1=\"0\" x2=\""+ bandLengthPercent +"%\" y2=\"100%\" style=\"stroke:blue; stroke-width:5\" />"
                + "<line x1=\"0\" y1=\"100%\" x2=\""+ bandLengthPercent +"%\" y2=\"0\" style=\"stroke:blue; stroke-width:5\" />";
        drawing += "</svg>";
        
        
        
        return drawing;
    }
        
}
