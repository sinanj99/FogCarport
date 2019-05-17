<jsp:include page='/include/sitehead.jsp'></jsp:include>
    <body class="background1">
    <jsp:include page='/include/sitemenu.jsp'></jsp:include>

        <div class="container d-flex flex-column justify-content-center">
            <form class="newform" method="post" action="/project/FrontController">
                <h1 class="text-align-start">Adresseoplysninger</h1>
                <div class="row">
                    <div class="col-sm-6 p-0 col-sm-6-l">
                        <div class="textsmall">Fornavn</div>
                    </div>
                    <div class="col-sm-6 p-0 col-sm-6-r">
                        <div class="textsmallr">Efternavn</div>
                    </div>
                    <div class="col-sm-6 p-0 col-sm-6-l">
                        <input class="inputsmalll" id="fname" name="fname" type="text" placeholder="Fornavn..." required onchange="fnamechecker();">
                    </div>
                    <div class="col-sm-6 p-0 col-sm-6-r d-sm-none">
                        <div class="textsmall">Efternavn</div>
                    </div>
                    <div class="col-sm-6 p-0 col-sm-6-r">
                        <input class="inputsmallr" name="lname" type="text" placeholder="Efternavn..." required>
                    </div>
                </div> 
                <div class="row">
                    <div class="col-sm-12 d-flex flex-column align-items-center">
                        <p class="p-0" style="width: 65%; color: white">Adresse</p>
                        <input class="inputbig" name="adress" type="text" placeholder="Adresse..." required>
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
                        <input class="inputsmalll" name="zip" type="text" placeholder="Postnummer..." required>
                    </div>
                    <div class="col-sm-6 p-0 col-sm-6-r d-sm-none">
                        <div class="textsmall">By</div>
                    </div>
                    <div class="col-sm-6 p-0 col-sm-6-r">
                        <input class="inputsmallr" name="city" type="text" placeholder="By..." required>
                    </div>
                </div>    
                <h1>Kontooplysninger</h1>
                <div class="row">
                    <div class="col-sm-12 d-flex flex-column align-items-center">
                        <p class="p-0" style="width: 65%; color: white">Email</p>
                        <input class="inputbig" name="email" type="text" placeholder="Email..." required>
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
                        <input class="inputsmalll" type="password" name="pword" placeholder="Adgangskode igen..." required>
                    </div>
                    <div class="col-sm-6 p-0 col-sm-6-r d-sm-none">
                        <div class="textsmall">Adgangskode igen</div>
                    </div>
                    <div class="col-sm-6 p-0 col-sm-6-r">
                        <input class="inputsmallr" type="password" name="pword2" placeholder="Adgangskode igen..." required>
                    </div>
                </div> 
                <div class="row">
                    <div class="col-sm-12 d-flex flex-column align-items-center">
                        <p class="p-0" style="width: 65%; color: white">Køn</p>
                        <select class="inputbig" name="gender" required>
                            <option value="n/a">Vælg køn</option>
                            <option value="m">Mand</option>
                            <option value="w">Kvinde</option>
                        </select>
                    </div>
                </div>
                <div class="col-sm-12 d-flex justify-content-center">
                    <input class="btn nicebtn" type="submit" value="Registrer">
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
            function fnamechecker() {
                var firstname = document.getElementById("fname");
                var regex = /[A-Za-z{1,20}]/g;
                console.log(firstname.value);
                var found = regex.test(firstname.value);
                if (found) {
                    console.log(found);
                    firstname.style.border = "2px solid green";
                } else {
                    console.log(found)
                    firstname.style.border = "2px solid red";
                }
            }
        </script>
    <jsp:include page='/include/sitefoot.jsp'></jsp:include>