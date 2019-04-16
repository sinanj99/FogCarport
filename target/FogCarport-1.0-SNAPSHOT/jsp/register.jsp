<jsp:include page='/include/sitehead.jsp'></jsp:include>
    <body class="background1">
    <jsp:include page='/include/sitemenu.jsp'></jsp:include>
        <div class="container d-flex flex-column justify-content-center">
            <form class="newform">
                <h1 class="text-align-start">Adresseoplysninger</h1>
                <div class="row">
                    <div class="col-sm-6 p-0 col-sm-6-l">
                        <input class="inputsmalll" type="text" placeholder="Fornavn..." required>
                    </div>
                    <div class="col-sm-6 p-0 col-sm-6-r">
                        <input class="inputsmallr" type="text" placeholder="Efternavn..." required>
                    </div>
                </div>
                <div class="row">
                    <div class="col-sm-12 d-flex justify-content-center">
                        <input class="inputbig" type="text" placeholder="Adresse..." required>
                    </div>
                </div>
                <div class="row">
                    <div class="col-sm-6 p-0 col-sm-6-l">
                        <input class="inputsmalll" type="text" placeholder="Postnummer..." required>
                    </div>
                    <div class="col-sm-6 p-0 col-sm-6-r">
                        <input class="inputsmallr" type="text" placeholder="By..." required>
                    </div>
                </div>    
                <h1>Kontooplysninger</h1>
                <div class="row">
                    <div class="col-sm-12 d-flex justify-content-center">
                        <input class="inputbig" type="text" placeholder="Email..." required>
                    </div>
                </div>
                <div class="row">
                    <div class="col-sm-6 p-0 col-sm-6-l">
                        <input class="inputsmalll" type="password" placeholder="Adgangskode..." required>
                    </div>
                    <div class="col-sm-6 p-0 col-sm-6-r">
                        <input class="inputsmallr" type="password" placeholder="Adgangskode igen..." required>
                    </div>
                </div>
                <div class="row">
                    <div class="col-sm-12 d-flex justify-content-center">
                        <select class="inputbig" required>
                            <option value="n/a">Vælg køn</option>
                            <option value="man">Mand</option>
                            <option value="woman">Kvinde</option>
                        </select>
                    </div>
                </div>
                <div class="col-sm-12 d-flex justify-content-center">
                    <input class="btn" type="submit" value="Registrer">
                </div>
            </form>
            <form class="newform2" action="login.jsp">
                <p>Ikke allerede medlem?</p>
                <div class="row">
                    <div class="col-sm-12 d-flex justify-content-center">
                        <input class="btn" type="submit" value="Log ind">
                    </div>
                </div>
            </form>
        </div>

    <jsp:include page='/include/sitefoot.jsp'></jsp:include>