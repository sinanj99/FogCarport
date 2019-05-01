/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var removeCartItemButton = document.getElementsByClassName("btn-danger");
console.log(removeCartItemButton);

for(var i = 0; i < removeCartItemButton.length; i++)
{
    var button = removeCartItemButton[i];
    button.addEventListener('click', function(event)
    {
        var buttonClicked = event.target;
        buttonClicked.parentElement.parentElement.remove();
        updateCartTotal();
        console.log("clicked");
    })
}

function updateCartTotal()
{
    var cartItemContainer = document.getElementsByClassName("cart-items")[0];
    var cartRows = cartItemContainer.getElementsByClassName("cart-row");
    
    for(var i = 1; i < cartRows.length; i++)
    {
        var cartRow = cartRows[i];
        var priceElement = cartRow.getElementsByClassName("cart-price")[0];
        var quantityElement = cartRow.getElementsByClassName("cart-quantity-input")[0];
        console.log(priceElement, quantityElement);
        
        var price = parseFloat(priceElement.innerText);
        var quantity = quantityElement.value;
        
        console.log(price);
        console.log(quantity);
        console.log(price * quantity);
        
    }
}
