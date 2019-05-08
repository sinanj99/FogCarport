/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic.Calculator;

import Data.Entity.Carport;

/**
 *
 * @author Kasper Jeppesen
 */
public class DrawSVGFlatroof
{
    public String drawFlatCarportNoShed(Carport c)
    {
        FlatRoofCarportBOM f = new FlatRoofCarportBOM();
        
        String draw = "";
        
        int carportLength = c.getLength();
        int carportWidth = c.getWidth();
        int shedLength = 10;
        
        float spaceBetweenSpær = f.spaceBetweenSpær(f.calculateQuantityOFSpærExcluedBackSpær(carportLength), carportLength);
        float startingPointX = 50;
        float startingPointY = 50;
        
        //Set the viewport
        draw += "<svg height='800px' width='800px' style='border: 3px solid red;'>";
        
        
        //Stolper
        float xCordinate = startingPointX + spaceBetweenSpær;
        float yCordinate = startingPointY;
        
        int quantityOfStolper = f.calculateQuantityOfStolper(carportLength, c.getShed_(), shedLength);
        
        for(int i = 0; i < quantityOfStolper; i++)
        {
            if(c.getShed_() == null)
            {
                //Top left stolpe
                if(i == 0)
                {
                    //yCordinate changed to make place it under remmen
                    yCordinate -= 3.6f;
                }
                //Top right stolpe
                if(i == 1)
                {
                    //xCordinate changed to place it at the second last spær
                    xCordinate = startingPointX + carportLength - spaceBetweenSpær - 9.7f;
                }
                 //Botton right stolpe
                if(i == 2)
                {
                    //yCordinate changed to place it at the at the other rem 
                    yCordinate = startingPointY + carportWidth - 36;
                }
                //Bottom left stolpe
                if(i == 3)
                {
                    //xCordinato changed to place it at the same xCordinate at the top left stolpe
                    xCordinate = startingPointX + spaceBetweenSpær;
                }
                // one of middle bottom stolpe
                if(i == 4)
                {
                    if(quantityOfStolper == 8)
                    {
                        //xCordinate changed to place it so that this middle stolpe and the other middle stolpe is as far from other stolpe as possible
                        //Take the position of the bottem right stolpe and add the position of bottom left, then divide by 1.5f
                        xCordinate = (startingPointX + carportLength - spaceBetweenSpær - 9.7f + startingPointX + spaceBetweenSpær) / 1.5f;
                    }
                    else
                    {
                        //xCordinate changed to place it in the center of bottom left and bottom right stolpe
                        //Take the position of the bottem right stolpe and add the position of bottom left, then divide by 2
                        xCordinate = (startingPointX + carportLength - spaceBetweenSpær - 9.7f + startingPointX + spaceBetweenSpær) / 2;
                    }

                }
                //one of the middle top stolpe
                if(i == 5)
                {
                    //yCordinate changed to place it at the same yCordination as the other top stolper
                    yCordinate = startingPointY - 3.6f;
                }
                if(i == 6)
                {
                    xCordinate = (startingPointX + carportLength - spaceBetweenSpær - 9.7f + startingPointX + spaceBetweenSpær) / 2.5f;
                }
                if(i == 7)
                {
                    yCordinate = startingPointY + carportWidth - 36;
                }
                
                draw += "<rect x='" + xCordinate + "' " + "y='" + yCordinate + "' height='9.7' width='9.7' fill='none' stroke='black' stroke-width='3px' /> ";
                        
            }
        }
        draw += "</svg>";
        return draw;
    }    
}
