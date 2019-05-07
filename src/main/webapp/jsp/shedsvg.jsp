<%-- 
    Document   : shedsvg
    Created on : 06-05-2019, 09:48:37
    Author     : Kasper Jeppesen
--%>



<%@page import="Data.Entity.Shed"%>
<%@page import="Data.Entity.Roof"%>
<%@page import="Data.Entity.Carport"%>
<%@page import="Data.Entity.ShippingAddress"%>
<%@page import="Data.Entity.Request"%>
<%@page import="Logic.Calculator.FlatRoofCarportBOM"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        
        <style>
            
            .shedsvg
            {
                width: 900px;
                height: 800px;
                border: 3px solid red;
            }
            
        </style>
        
        <%
            ShippingAddress s = new ShippingAddress("bob", "johnson", "vejen", 3420, "opdigtet");
            Roof roof = new Roof(1, "taget", false);
            Carport c = new Carport(roof, 0, 530,750 , null);
            
            Request r = new Request(s, 2, "", c);
        %>
        
    </head>
    <body>
        
        
        
    <svg class="shedsvg">

    <%
        FlatRoofCarportBOM f = new FlatRoofCarportBOM();

        //below are variables, which stores frequently used values
        
        int carportLength = r.getCarport().getLength();
        int carportWidth = r.getCarport().getWidth();
        int shedLength = 10;
        
        //new Shed(210, 240)
        //r.getCarport().getShed_().getLength();
        
        float spaceBetweenSpærVAR = f.spaceBetweenSpær(f.calculateQuantityOFSpærExcluedBackSpær(carportLength), carportLength);

        float startingPointFirstSpærX = 50;
        float startingPointFirstSpærY = 50;
        
    %>

        <svg class="stolpePlacement">
            <%
                float xCordinate = startingPointFirstSpærX + spaceBetweenSpærVAR;
                float yCordinate = startingPointFirstSpærY;
                
                int quantityOfStolper = f.calculateQuantityOfStolper(carportLength, r.getCarport().getShed_(), shedLength);

                for(int i = 0; i < quantityOfStolper; i++)
                {
                    if(r.getCarport().getShed_() != null)
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
                            xCordinate = startingPointFirstSpærX + carportLength - spaceBetweenSpærVAR - 9.7f;
                        }
                        //Botton right stolpe
                        if(i == 2)
                        {
                            //yCordinate changed to place it at the at the other rem 
                            yCordinate = startingPointFirstSpærY + carportWidth - 36;
                        }
                        //Bottom left stolpe
                        if(i == 3)
                        {
                            //xCordinato changed to place it at the same xCordinate at the top left stolpe
                            xCordinate = startingPointFirstSpærX + spaceBetweenSpærVAR;
                        }
                        //The bottom front stolpe of the shed
                        if(i == 4)
                        {
                            //xCprdinate changed to place it the length of the shed away from the bottom right stolpe
                            xCordinate = (startingPointFirstSpærX + carportLength - spaceBetweenSpærVAR) - shedLength;
                        }
                        //The top front stolpe of the shed
                        if(i == 5)
                        {
                            //Changed yCordinate to place it at the same yCordinat as the top left/right stolpe
                            yCordinate = startingPointFirstSpærY - 3.6f;
                        }
                        //the front middle stolpe of the shed
                        if(i == 6)
                        {
                            //yCordinate changed to place it between the front bottom and top stolpe of the shed
                            yCordinate = yCordinate + (carportWidth - 30) / 2;
                        }
                        //the back middle stolpe of the shed
                        if(i == 7)
                        {
                            //xCordinate changed to place it at the same xCordinate as the rigth top/bottom stolpe
                            xCordinate = startingPointFirstSpærX + carportLength - spaceBetweenSpærVAR - 9.7f;
                        }
                        //Stolpe between the top left stolpe and top front stolpe of the shed
                        if(i == 8)
                        {
                            //yCordinate changed to place it at same yCordinate at top left stolpe
                            yCordinate = startingPointFirstSpærX - 3.6f;
                            //xCordinate changed to place it in the center of the top left stolpe and top front stolpe of the shed
                            xCordinate = (xCordinate - shedLength + startingPointFirstSpærX + spaceBetweenSpærVAR) / 2;
                        }
                        //stolpe between the bottom left stolpe and the bottom front stolpe of the shed
                        if(i == 9)
                        {
                            //yCordinate changed to place it at the same yCordinate as the front bottom stolpe
                            yCordinate = startingPointFirstSpærY + carportWidth - 36;
                        }
                    }
                    
                    if(r.getCarport().getShed_() == null)
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
                            xCordinate = startingPointFirstSpærX + carportLength - spaceBetweenSpærVAR - 9.7f;
                        }
                         //Botton right stolpe
                        if(i == 2)
                        {
                            //yCordinate changed to place it at the at the other rem 
                            yCordinate = startingPointFirstSpærY + carportWidth - 36;
                        }
                        //Bottom left stolpe
                        if(i == 3)
                        {
                            //xCordinato changed to place it at the same xCordinate at the top left stolpe
                            xCordinate = startingPointFirstSpærX + spaceBetweenSpærVAR;
                        }
                        // one of middle bottom stolpe
                        if(i == 4)
                        {
                            if(quantityOfStolper == 8)
                            {
                                //xCordinate changed to place it so that this middle stolpe and the other middle stolpe is as far from other stolpe as possible
                                //Take the position of the bottem right stolpe and add the position of bottom left, then divide by 1.5f
                                xCordinate = (startingPointFirstSpærX + carportLength - spaceBetweenSpærVAR - 9.7f + startingPointFirstSpærX + spaceBetweenSpærVAR) / 1.5f;
                            }
                            else
                            {
                                //xCordinate changed to place it in the center of bottom left and bottom right stolpe
                                //Take the position of the bottem right stolpe and add the position of bottom left, then divide by 2
                                xCordinate = (startingPointFirstSpærX + carportLength - spaceBetweenSpærVAR - 9.7f + startingPointFirstSpærX + spaceBetweenSpærVAR) / 2;
                            }
                            
                        }
                        //one of the middle top stolpe
                        if(i == 5)
                        {
                            //yCordinate changed to place it at the same yCordination as the other top stolper
                            yCordinate = startingPointFirstSpærY - 3.6f;
                        }
                        if(i == 6)
                        {
                            xCordinate = (startingPointFirstSpærX + carportLength - spaceBetweenSpærVAR - 9.7f + startingPointFirstSpærX + spaceBetweenSpærVAR) / 2.5f;
                        }
                        if(i == 7)
                        {
                            yCordinate = startingPointFirstSpærY + carportWidth - 36;
                        }
                    }
                    
                    
                    
            %>
            <rect class="stolper" x=<%= xCordinate %>  y=<%= yCordinate %> height="9.7" width="9.7" fill="none" stroke="black" stroke-width="3px" />
            <%      

                    
                }
            %>
        </svg>224

        <svg class="remmenene">
            
            <rect class="remmen" x=<%= startingPointFirstSpærX %> y=<%= startingPointFirstSpærY  %> height="4.5" width=<%=carportLength%> fill="none" stroke="black" stroke-width="3px"  />
            <rect class="remmen" x=<%= startingPointFirstSpærX %> y=<%= startingPointFirstSpærY + carportWidth - 34  %> height="4.5" width=<%=carportLength%> fill="none" stroke="black" stroke-width="3px"  />

        </svg>
        
        <svg class="spærerne">
            
            <%
                int quantityOfSpær = f.calculateQuantityOFSpærExcluedBackSpær(carportLength);
                int quantityOfSpærPlusTheBackSpær = f.calculateQuantityOfSpærIncludedBackSpær(carportLength);
                
                float frontSpærPlacementX =  startingPointFirstSpærX;
                float frontSpærPlacementY = startingPointFirstSpærY - 15;
                
                for(int i = 0; i < quantityOfSpærPlusTheBackSpær; i++)
                {
            %>
            <rect class="spær" x=<%= frontSpærPlacementX %> y=<%= frontSpærPlacementY  %> height=<%= carportWidth %> width=<%= 10 %> fill="none" stroke="black" stroke-width="3px" />
            <%
                
                frontSpærPlacementX += f.spaceBetweenSpær(f.calculateQuantityOFSpærExcluedBackSpær(carportLength), carportLength) + 3f;
                
                }
            %>
            
        </svg>

    </svg>

    <script>

        var rect = document.getElementsByTagName("rect");
        console.log(rect.length);

        for(i = 0; i < rect.length; i++)
        {
            var specRect = rect.item(i);
            console.log(specRect);
            //specRect.style.setProperty("height", 400);
            //specRect.setAttribute('x',400);

        }


    </script>


    </body>
</html>
