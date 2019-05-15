<jsp:include page='/include/sitehead.jsp'></jsp:include>
    <body class="background2">
    <jsp:include page='/include/sitemenu.jsp'></jsp:include>
        <div class="container d-flex flex-column justify-content-center">
            <div class="standarddiv"> 
                <div class="row">
                    <form class="d-flex flex-column align-items-center" onsubmit="return checkPrice()">
                        <span id="errortxt" class="d-none" style="color: red">Indtast venligst et positivt heltal mellem 1 og 4 cifre.</span>
                        <input id="price" name="price" placeholder="Indtast pris..." type="text" required>
                        <input id="id" name="id" placeholdeR="Indtast id..." type="text" required>
                        <input type="submit" value="Opdater pris">
                        <input type="hidden" name="command" value="change_price">
                    </form>
                </div>
            </div>
        </div>
        <script>
            function checkPrice() {
                var price = document.getElementById("price");
                var errortxt = document.getElementById("errortxt");
                var regex = /[1-9{1}]/g;
                console.log(price.value);
                var found = regex.test(price.value);
                if (!found) {
                    console.log(found)
                    errortxt.classList.remove("d-none");
                    price.style.border = "2px solid red";
                }
            }
        </script>        
    <jsp:include page='/include/sitefoot.jsp'></jsp:include>