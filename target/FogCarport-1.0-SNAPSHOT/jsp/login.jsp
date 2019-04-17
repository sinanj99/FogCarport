<jsp:include page='/include/sitehead.jsp'></jsp:include>
    <body class="background1">
    <jsp:include page='/include/sitemenu.jsp'></jsp:include>
        <div class="container d-flex flex-column justify-content-center">
            <form class="newform">
                <h1>Kontooplysninger</h1>
                <div class="row">
                    <div class="col-sm-12 d-flex justify-content-center">
                        <input style="width: 50%;" class="inputbig" type="text" placeholder="Email..." required>
                    </div>
                </div>
                <div class="row">
                    <div class="col-sm-12">
                        <input style="width: 50%;" class="inputbig" type="password" placeholder="Adgangskode..." required>
                    </div>
                </div>
                <div class="col-sm-12 d-flex justify-content-center">
                    <input class="btn nicebtn" type="submit" value="Log ind">
                </div>
            </form>
            <form class="newform2" action="register.jsp">
                <p style="color: #0f0c28">Ikke allerede medlem?</p>
                <div class="row">
                    <div class="col-sm-12 d-flex justify-content-center">
                        <input class="btn notmemberbtn" type="submit" value="Registrer">
                    </div>
                </div>
            </form>
        </div>

    <jsp:include page='/include/sitefoot.jsp'></jsp:include>