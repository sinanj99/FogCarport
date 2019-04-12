<jsp:include page='sitehead.jsp'></jsp:include>
<jsp:include page='sitemenu.jsp'></jsp:include>
    <div class="container d-flex flex-column justify-content-center">
        <form class="newform">
            <h1>Kontooplysninger</h1>
            <div class="row">
                <div class="col-sm-12 d-flex justify-content-center">
                    <input class="inputbig" type="text" placeholder="Email" required>
                </div>
            </div>
            <div class="row">
                <div class="col-sm-12">
                    <input class="inputbig" type="password" placeholder="Adgangskode" required>
                </div>
            </div>
            <div class="col-sm-12 d-flex justify-content-center">
                <input class="btn" type="submit" value="Log ind">
            </div>
        </form>
        <form class="newform2" action="register.jsp">
            <p>Ikke allerede medlem?</p>
            <div class="row">
                <div class="col-sm-12 d-flex justify-content-center">
                    <input class="btn" type="submit" value="Registrer">
                </div>
            </div>
        </form>
    </div>

<jsp:include page='sitefoot.jsp'></jsp:include>