<jsp:include page='/include/sitehead.jsp'></jsp:include>
<jsp:include page='/include/sitemenu.jsp'></jsp:include>
    <body class="background2">
        <div class="container d-flex flex-column justify-content-center">
            <div class="standarddiv">
                <div class="row">
                    <div class="col-12 d-flex flex-column justify-content-center align-items-center">
                        <h1>QUICK-BYG TILBUD</h1>
                        <br>
                        <p> Med et specialudviklet computerprogram kan vi lynhurtigt beregne prisen og udskrive en skitsetegning p� en carport inden for vores standardprogram, der tilpasses dine specikke �nsker.<br><br>
                            Tilbud og skitsetegning fremsendes med post hurtigst muligt.<br>
                            Ved bestilling medf�lger standard byggevejledning.<br><br>
                            Rekvir�r tilbud - start med at v�lge type:</p>
                    </div>
                </div>
                <hr>
                <div class="row">
                    <div class="col-sm-6 d-flex flex-column align-items-center"><b>Fladt tag</b>
                        <form class="d-flex flex-column align-items-center" method="get" action="FrontController">
                        <input type="submit" value="v�lg"> <img src="/project/images/fladt.jpg" style="width: 60%; height: auto; margin-top: 20px;">
                        <input type="hidden" name="command" value="flatroof">
                        </form>
                    </div>

                    <div class="col-sm-6 d-flex flex-column align-items-center"><b>Tag med rejsning</b>
                        <form class="d-flex flex-column align-items-center" method="get" action="FrontController">
                        <input type="submit" value="v�lg"> <img src="/project/images/fladt.jpg" style="width: 60%; height: auto; margin-top: 20px;">
                        <input type="hidden" name="command" value="inclineroof">
                        </form>
                    </div>
                </div>
                <hr>
                <div class="row">
                    <div class="col-12 d-flex flex-column justify-content-center align-items-center">
                        <p>Ellers er du naturligvis velkommen til at kigge p� vores udvalg af pre-byggede carporte:</p>
                        <input class="btn nicebtn" type="submit" value="Se pre-byggede" style="color: black;">
                    </div>
                </div>
            </div>
        </div>
    <jsp:include page='/include/sitefoot.jsp'></jsp:include>