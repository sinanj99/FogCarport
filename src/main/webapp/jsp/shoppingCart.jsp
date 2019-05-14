<%-- 
    Document   : shoppingCart
    Created on : 30-04-2019, 17:21:57
    Author     : Kasper Jeppesen
--%>

<%@page import="Data.Entity.PrebuiltCarport"%>
<%@page import="Data.Entity.ShoppingCart"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page='/include/sitehead.jsp'></jsp:include>
<jsp:include page='/include/sitemenu.jsp'></jsp:include>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="/project/include/javascriptForShop.js" async></script>
        
        <title>Indkøbskurv</title>
        
        <style>
            .cart-header
            {
                font-weight: bold;
                font-size: 1.2em;
                color: #333;
                margin-top: 100px;
            }
            .cart-column
            {
                display: flex;
                align-items: center;
                border-bottom: 1px solid black;
                margin-right: 1.5em;
                padding-bottom: 10px;
                margin-top: 10px;
            }
            .cart-row
            {
                display: flex;
            }
            .cart-image
            {
                width: 15%;
            }
            .cart-carportLength
            {
                width: 15%;
            }
            .cart-carportWidth
            {
                width: 15%;
            }
            .cart-ShedDimension
            {
                width: 20%
            }
            .cart-price
            {
                width: 15%;
                font-size: 1.2em;
                color: #333;
            }
            .cart-quantity
            {
                width: 15%;
            }
            .cart-item-title
            {
                color: #333;
                margin-left: .5em;
                font-size: 1.2em;
            }
            .cart-item-image
            {
                width: 75px;
                height: auto;
                border-radius: 10px;
            }
            .btn-danger
            {
                color: white;
                background-color: #EB5757;
                border: none;
                border-radius: .3em;
                font-weight: bold;
            }
            .btn-danger:hover
            {
                background-color: #cc4c4c;
            }
            .cart-quantity-input
            {
                height: 34px;
                width: 50px;
                border-radius: 5px;
                border: 1px solid #56CCF2;
                background-color: #eee;
                color: #333;
                padding: 0;
                text-align: center;
                font-size: 1.2em;
                margin-right: 25px;
            }
            .cart-row:last-child
            {
                border-bottom: 1px solid black;
            }
            .cart-row:last-child .cart-column
            {
                border: none;
            }
            .cart-total
            {
                text-align: end;
                margin-top: 10px;
                margin-right: 10px;
            }
            .cart-total-title
            {
                font-weight: bold;
                font-size: 1.5em;
                color: black;
                margin-right: 20px;
            }
            .cart-total-price
            {
                color: #333;
                font-size: 1.1em;
            }
            .btn-purchase
            {
                display: block;
                margin: 40px auto 80px auto;
                font-size: 1.7em;
                background-color: blue;
            }
            .btn
            {
                text-align: center;
                vertical-align: middle;
                padding: .67em;
                cursor: pointer;
            }
            .btn-primary
            {
                color: white;
                background-color: #56CCF2;
                border: none;
                border-radius: .3em;
                font-weight: bold;
            }
            .btn-primary:hover
            {
                background-color: #2D9CDB
            }
        </style>
        
    </head>
    <body class="background2">
        <%
            if(request.getSession().getAttribute("shoppingcart") == null)
            {
        %>
                <h4>Indkøbskurven er tom</h4>
        <%
            }
            if(request.getSession().getAttribute("shoppingcart") != null)
            {
                ShoppingCart cart = (ShoppingCart) request.getSession().getAttribute("shoppingcart");
                int fullPrice = 0;
                System.out.println(cart.getPrebuiltCarports());
                System.out.println("hi");
        %>
        
        <section>
            <h2>CART</h2>
            <div class="cart-items">
                <div class="cart-row">
                    <span class="cart-image cart-header cart-column">Billede</span>
                    <span class="cart-carportLength cart-header cart-column">Carpotens længde</span>
                    <span class="cart-carportWidth cart-header cart-column">Carportens bredde</span>
                    <span class="cart-ShedDimension cart-header cart-column">Mål på skur (B x L)</span>
                    <span class="cart-price cart-header cart-column">Pris</span>
                    <span class="cart-quantity cart-header cart-column">Mængde</span>
                </div>
                
                <%
                    for(PrebuiltCarport p : cart.getPrebuiltCarports())
                    {
                %>
                        <div class="cart-row">
                            <div class="cart-image cart-column">
                                <img class="cart-item-image" src=<%= p.getImgPpath()  %> width="100" height="100">
                            </div>

                            <div class="cart-carportLength cart-column" >
                                <span class=" cart-item-title"  > <%= p.getCarportLength() %> </span>
                            </div>

                            <div class="cart-carportWidth cart-column" >
                                <span class="cart-item-title"  > <%= p.getCarportWidth() %> </span>
                            </div>

                            <div class="cart-ShedDimension cart-column" >
                                <%
                                    if(p.isShed() == true)
                                    {
                                %>
                                <span class="cart-item-title"> <%= p.getShedWidth()%> x <%= p.getShedLength() %>    </span>
                                <% 
                                    }
                                    else
                                    {   
                                %>
                                <span class="cart-item-title"  > Skur medfølger ikke </span>
                                <% 
                                    }
                                %>
                            </div>

                            <span class="cart-price cart-column"> <%= p.getPrice() %> kr.  </span>

                            <div class="cart-quantity cart-column">
                                <input class="cart-quantity-input" type="number" value="1">
                                <button class="btn btn-danger " role="button">Fjern carport</button>
                            </div>
                            
                        </div>
                <%
                    }    
                %>
            </div>
            
            <% 
                for(PrebuiltCarport p : cart.getPrebuiltCarports())
                {
                    fullPrice += p.getPrice();
                }
            %>
            
            <div class="cart-total">
                <strong class="cart-total-title" >TOTAL</strong>
                <span class="cart-total-price" style="color: blue" > <%= fullPrice %> .-  </span>
            </div>

            <button class="btn btn-primary btn-purchase" role="button">Betal for varene</button>
            <br>

       </section>
         
       <%
           }
       %>
           
    </body>
    
</html>
