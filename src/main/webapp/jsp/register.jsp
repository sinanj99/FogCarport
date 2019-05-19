<jsp:include page='/include/sitehead.jsp'></jsp:include>
    <body onload="checkAll()" class="background1">
    <jsp:include page='/include/sitemenu.jsp'></jsp:include>

        <div class="container d-flex flex-column justify-content-center">
            <form onkeyup="checkAll()" class="newform" method="post" action="/project/FrontController">
                <h1 class="text-align-start">Adresseoplysninger</h1>
                <div class="row">
                    <div class="col-sm-6 p-0 col-sm-6-l">
                        <div class="textsmall">Fornavn</div>
                    </div>
                    <div class="col-sm-6 p-0 col-sm-6-r">
                        <div class="textsmallr">Efternavn</div>
                    </div>
                    <div class="col-sm-6 p-0 col-sm-6-l">
                        <input class="inputsmalll check" id="fname" name="fname" type="text" placeholder="Fornavn..." required onkeyup="check(this);">
                    </div>
                    <div class="col-sm-6 p-0 col-sm-6-r d-sm-none">
                        <div class="textsmall">Efternavn</div>
                    </div>
                    <div class="col-sm-6 p-0 col-sm-6-r">
                        <input class="inputsmallr check" id="lname" name="lname" type="text" placeholder="Efternavn..." required onkeyup="check(this)">
                    </div>
                </div> 
                <div class="row">
                    <div class="col-sm-12 d-flex flex-column align-items-center">
                        <p class="p-0" style="width: 65%; color: white">Adresse</p>
                        <input class="inputbig check" id="address" name="adress" type="text" placeholder="Adresse..." required onkeyup="check(this)">
                    </div>
                </div>
                <div class="row">
                    <div class="col-sm-6 p-0 col-sm-6-l">
                        <div class="textsmall">Postnummer</div>
                    </div>
                    <div class="col-sm-6 p-0 col-sm-6-r">
                        <div class="textsmallr">By</div>
                    </div>
                    <div class="col-sm-6 p-0 col-sm-6-l">
                        <input class="inputsmalll check" id="zip" name="zip" type="text" placeholder="Postnummer..." required onkeyup="check(this)">
                    </div>
                    <div class="col-sm-6 p-0 col-sm-6-r d-sm-none">
                        <div class="textsmall">By</div>
                    </div>
                    <div class="col-sm-6 p-0 col-sm-6-r">
                        <input class="inputsmallr check" id="city" name="city" type="text" placeholder="By..." required onkeyup="check(this)">
                    </div>
                </div>    
                <h1>Kontooplysninger</h1>
                <div class="row">
                    <div class="col-sm-12 d-flex flex-column align-items-center">
                        <p class="p-0" style="width: 65%; color: white">Email</p>
                        <input class="inputbig check" id="email" name="email" type="text" placeholder="Email..." required onkeyup="check(this)">
                    </div>
                </div>
                <div class="row">
                    <div class="col-sm-6 p-0 col-sm-6-l">
                        <div class="textsmall">Adgangskode</div>
                    </div>
                    <div class="col-sm-6 p-0 col-sm-6-r">
                        <div class="textsmallr">Adgangskode igen</div>
                    </div>
                    <div class="col-sm-6 p-0 col-sm-6-l">
                        <input class="inputsmalll check" id="pword" type="password" name="pword" placeholder="Adgangskode igen..." required onkeyup="check(this)">
                    </div>
                    <div class="col-sm-6 p-0 col-sm-6-r d-sm-none">
                        <div class="textsmall">Adgangskode igen</div>
                    </div>
                    <div class="col-sm-6 p-0 col-sm-6-r">
                        <input class="inputsmallr check" id="pword2" type="password" name="pword2" placeholder="Adgangskode igen..." required onkeyup="check(this)">
                    </div>
                </div> 
                <div class="row">
                    <div class="col-sm-12 d-flex flex-column align-items-center">
                        <p class="p-0" style="width: 65%; color: white">K¯n</p>
                        <select class="inputbig check" id="gender" name="gender" required onchange="check(this)">
                            <option value="n/a">VÊlg k¯n</option>
                            <option value="m">Mand</option>
                            <option value="w">Kvinde</option>
                        </select>
                    </div>
                </div>
                <div class="col-sm-12 d-flex justify-content-center">
                    <input class="btn nicebtn" id="submit" type="submit" value="Registrer">
                    <input type="hidden" name="command" value="register">
                </div>
            </form>
            <form class="newform2" action="jsp/login.jsp">
                <p style="color: #0f0c28; text-align: center;">Allerede medlem?</p>
                <div class="row">
                    <div class="col-sm-12 d-flex justify-content-center">
                        <input class="btn notmemberbtn" type="submit" value="Log ind">
                    </div>
                </div>
            </form>
        </div>
        <script>
            function check(element) {
                var regex;
                var found = false;
                switch (element.id) {
                    case "fname":
                    case "lname":
                        regex = /^[a-zA-ZÂ\¯Ê‹÷¸ˆ][a-zA-Z\. Â\¯Ê‹÷¸ˆ-]{1,20}$/;
                        break;
                    case "address":
                        regex = /^[A-Za-z∆ÿ≈Ê¯Â‹÷¸ˆ 0-9'\.\-,#]{1,100}$/;
                        break;
                    case "zip":
                        regex = /^[0-9]{4}$/;
                        break;
                    case "city":
                        regex = /^[A-Za-z∆ÿ≈Ê¯Â‹÷ˆ¸ \.\-,]{1,20}$/;
                        break;
                    case "email":
                        var email = element.value;
                        if (email.indexOf("@") !== -1 && email.substring(0, email.indexOf("@")) !== ''
                                && email.substring(email.indexOf("@")).indexOf(".") !== -1 && !email.endsWith(".")) {
                            found = true;
                        }
                    case "pword":
                        regex = /^(?=.*[A-Za-z∆ÿ≈Ê¯Â‹÷ˆ¸])(?=.*\d)[A-Za-z∆ÿ≈Ê¯Â‹÷¸¸\d]{8,}$/;
                        break;
                    case "pword2":
                        var pword = document.getElementById("pword");
                        if (element.value.match(pword.value) && pword.value.length !== 0) {
                            found = true;
                        }
                    case "gender":
                        regex = /^[mw]{1}$/;
                        break;
                        
                }
                if (element.id !== "email" && element.id !== "pword2") {
                    found = regex.test(element.value);
                }
                
                if (element.value.length == 0 || element.value == "n/a") {
                    element.style.border = "none";
                    return false;
                } else if (found) {
                    element.style.border = "2px solid green";
                    return true;
                } else {
                    element.style.border = "2px solid red";
                    return false;
                }
            }

            function checkAll() {
                console.log("test");
                var condition = true;
                var inputs = document.getElementsByClassName("check");
                for (var i = 0; i < inputs.length; i++) {
                    console.log("test1");
                    if (check(inputs[i]) == false) {
                        console.log("check false!");
                        condition = false;
                    }
                }
                if (condition == false) {
                    document.getElementById("submit").disabled = true;
                } else {
                    document.getElementById("submit").disabled = false;
                }
            }
        </script>
    <jsp:include page='/include/sitefoot.jsp'></jsp:include>