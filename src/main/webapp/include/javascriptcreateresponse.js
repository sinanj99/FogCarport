var sellPrice = document.getElementById("sellprice").value;
updatePriceBtn();

function updatePriceBtn() {
    if (document.getElementById("sellprice").value === sellPrice) {
        document.getElementById("sellpricebtn").disabled = true;
    } else {
        document.getElementById("sellpricebtn").disabled = false;
    }
}


