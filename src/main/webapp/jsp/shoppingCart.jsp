<%-- 
    Document   : shoppingCart
    Created on : 30-04-2019, 17:21:57
    Author     : Kasper Jeppesen
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="/project/include/javascriptForShop.js" async></script>
        
        <title>JSP Page</title>
        
        <style>
            .cart-header
            {
                font-weight: bold;
                font-size: 1.2em;
                color: #333;
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
            .cart-item
            {
                width: 45%;
            }
            .cart-price
            {
                width: 20%;
                font-size: 1.2em;
                color: #333;
            }
            .cart-quantity
            {
                width: 35%;
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
    <body>
        <h1>Hello World!</h1>
        <section>
            <h2>CART</h2>
            <div class="cart-items">
                <div class="cart-row">
                    <span class="cart-item cart-header cart-column">Item</span>
                    <span class="cart-price cart-header cart-column">Price</span>
                    <span class="cart-quantity cart-header cart-column">Quantity</span>
                </div>

                <div class="cart-row">
                    <div class="cart-item cart-column">
                        <img class="cart-item-image" src="/project/images/abc.png" width="100" height="100">
                        <span class="cart-item-title">Carport abc</span>
                    </div>

                    <span class="cart-price cart-column">17.798 </span>

                    <div class="cart-quantity cart-column">
                        <input class="cart-quantity-input" type="number" value="1">
                        <button class="btn btn-danger " role="button">REMOVE</button>
                    </div>
                </div>

                <div class="cart-row">
                    <div class="cart-item cart-column">
                        <img class="cart-item-image" src="/project/images/abc.png" width="100" height="100">
                        <span class="cart-item-title">Carport abc</span>
                    </div>

                    <span class="cart-price cart-column">19.798 </span>

                    <div class="cart-quantity cart-column">
                        <input class="cart-quantity-input" type="number" value="1">
                        <button class="btn btn-danger " role="button">REMOVE</button>
                    </div>
                </div>
         </div>
            
            <div class="cart-total">
                <strong class="cart-total-title" >TOTAL</strong>
                <span class="cart-total-price" >20.00</span>
            </div>
            
            <button class="btn btn-primary btn-purchase" role="button">PURCHASE</button>
            <br>
            <a href="prebuiltCarport.jsp">link to prebuilt</a>
            
        </section>
    </body>
    
    
    
</html>
