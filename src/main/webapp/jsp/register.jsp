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
                        <div class="textsmall">Adgangskode (min. 8 bogstaver inkl. 1 tal)</div>
                    </div>
                    <div class="col-sm-6 p-0 col-sm-6-r">
                        <div class="textsmallr">Adgangskode igen</div>
                    </div>
                    <div class="col-sm-6 p-0 col-sm-6-l">
                        <input class="inputsmalll check" id="pword" type="password" name="pword" placeholder="Adgangskode..." required onkeyup="check(this)">
                    </div>
                    <div class="col-sm-6 p-0 col-sm-6-r">
                        <input class="inputsmallr check" id="pword2" type="password" name="pword2" placeholder="Adgangskode igen..." required onkeyup="check(this)">
                    </div>
                </div> 
                <div class="row">
                    <div class="col-sm-12 d-flex flex-column align-items-center">
                        <p class="p-0" style="width: 65%; color: white">Køn</p>
                        <select class="inputbig check" id="gender" name="gender" required onchange="check(this)">
                            <option value="n/a">Vælg køn</option>
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
        <script src="/project/include/javascript_validation.js" async></script>
    <jsp:include page='/include/sitefoot.jsp'></jsp:include>