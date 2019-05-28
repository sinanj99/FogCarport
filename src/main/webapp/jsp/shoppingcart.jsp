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
<link rel="stylesheet" href="/project/cssprebuilt.css">

        <body class="background2">
        <%
            if (request.getSession().getAttribute("shoppingcart") == null) {
        %>
        <h4>Indkøbskurven er tom</h4>
        <%
            }
            if (request.getSession().getAttribute("shoppingcart") != null) {
                ShoppingCart cart = (ShoppingCart) request.getSession().getAttribute("shoppingcart");
                int fullPrice = 0;
        %>
        <div class="shopcart custom-container">
            <h2>Indkøbskurv</h2>
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
                    for (PrebuiltCarport p : cart.getPrebuiltCarports()) {
                %>
                <div class="cart-row">
                    <div class="cart-image cart-column">
                        <img class="cart-item-image" src=<%= p.getImgPpath()%> width="100" height="100">
                    </div>

                    <div class="cart-carportLength cart-column" >
                        <span class=" cart-item-title"  > <%= p.getCarportLength()%> </span>
                    </div>

                    <div class="cart-carportWidth cart-column" >
                        <span class="cart-item-title"  > <%= p.getCarportWidth()%> </span>
                    </div>

                    <div class="cart-ShedDimension cart-column" >
                        <%
                            if (p.getShed() != null) {
                        %>
                        <span class="cart-item-title"> <%= p.getShed().getWidth()%> x <%= p.getShed().getLength()%>    </span>
                        <%
                        } else {
                        %>
                        <span class="cart-item-title"  > Skur medfølger ikke </span>
                        <%
                            }
                        %>
                    </div>

                    <span class="cart-price cart-column"> <%= p.getPrice()%> kr.  </span>

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
                for (PrebuiltCarport p : cart.getPrebuiltCarports()) {
                    fullPrice += p.getPrice();
                }
            %>

            <div class="cart-total">
                <strong class="cart-total-title" >TOTAL</strong>
                <span class="cart-total-price" style="color: blue" > <%= fullPrice%> ,-  </span>
            </div>

            <button class="btn btn-primary btn-purchase" role="button">Betal for varene</button>
            <br>

        </div>

        <%
            }
        %>
        <script src="/project/include/javascriptforshop.js" async></script>
    </body>

</html>
