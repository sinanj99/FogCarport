<%-- 
    Document   : shedsvg
    Created on : 06-05-2019, 09:48:37
    Author     : Kasper Jeppesen
--%>


<%@page import="Logic.Calculator.spærTestKasper"%>
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
            Carport c = new Carport(roof, 0, 420,750 , new Shed(210, 210));
            
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
        int shedLength = r.getCarport().getShed_().getLength();
        
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
            %>
            <rect class="stolper" x=<%= xCordinate %>  y=<%= yCordinate %> height="9.7" width="9.7" fill="none" stroke="black" stroke-width="3px" />
            <%      

                    if(i == 0)
                    {
                        xCordinate = startingPointFirstSpærX + carportLength - spaceBetweenSpærVAR;
                    }
                    if(i == 1)
                    {
                        yCordinate = startingPointFirstSpærY + carportWidth - 30;
                    }
                    if(i == 2)
                    {
                        xCordinate = startingPointFirstSpærX + spaceBetweenSpærVAR;
                    }
                    if(i == 3)
                    {
                        xCordinate = (startingPointFirstSpærX + carportLength - spaceBetweenSpærVAR) - shedLength;
                    }
                    if(i == 4)
                    {
                        yCordinate = startingPointFirstSpærY;
                    }
                    if(i == 5)
                    {
                        yCordinate = yCordinate + (carportWidth - 30) / 2;
                    }
                    if(i == 6)
                    {
                        xCordinate = startingPointFirstSpærX + carportLength - spaceBetweenSpærVAR;
                    }
                }
            %>
        </svg>

        <svg class="remmenene">
            
            <rect class="remmen" x=<%= startingPointFirstSpærX %> y=<%= startingPointFirstSpærY +2 %> height="4.5" width=<%=carportLength%> fill="none" stroke="black" stroke-width="3px"  />
            <rect class="remmen" x=<%= startingPointFirstSpærX %> y=<%= startingPointFirstSpærY + carportWidth - 30 + 2  %> height="4.5" width=<%=carportLength%> fill="none" stroke="black" stroke-width="3px"  />

        </svg>
        
        <svg class="spærerne">
            
            <%
                int quantityOfSpær = f.calculateQuantityOFSpærExcluedBackSpær(carportLength);
                int quantityOfSpærPlusTheBackSpær = f.calculateQuantityOfSpærIncludedBackSpær(carportLength);
                
                float frontSpærPlacementX =  startingPointFirstSpærX;
                float frontSpærPlacementY = startingPointFirstSpærY - 10;
                
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
