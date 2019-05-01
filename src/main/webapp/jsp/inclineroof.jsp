<%@page import="Data.Entity.User"%>
<%@page import="Data.Entity.Roof"%>
<%@page import="java.util.List"%>
<jsp:include page='/include/sitehead.jsp'></jsp:include>
<jsp:include page='/include/sitemenu.jsp'></jsp:include>
<%List<Roof> roofs = (List<Roof>) request.getAttribute("roofs");
User user = (User) session.getAttribute("user");

if(user == null) response.sendRedirect("login.jsp");

    int a;
    int b; %>
<body class="background2">
    <div class="container">
        <form class="standarddiv pl-0 pr-0 d-flex flex-column justify-content-center">
            <h3>Tilpas din carport</h3>
            <div class="row">
                <div class="col-12 d-flex flex-column align-items-center">
                    <p class="p-0" style="width: 65%;">Carportens bredde</p>
                    <select class="inputbig" id="carportWidth" onchange="widthSubtract30()">
                        <option value="n/a">Vælg</option>
                        <% b = 210;
                            for (int i = 0; i < 18; i += 1) {
                                b += 30;%>
                        <option value="<%=i + 1%>"><%=b%> cm</option>
                        <%}%>
                    </select>
                </div>
            </div>
            <div class="row">
                <div class="col-12 d-flex flex-column align-items-center">
                    <p class="p-0" style="width: 65%;">Carportens længde</p>
                    <select class="inputbig" id="carportLength" onchange="lengthSubtract30()">
                        <option value="n/a">Vælg</option>
                        <% b = 210;
                            for (int i = 0; i < 18; i += 1) {
                                b += 30;%>
                        <option value="<%=i + 1%>"><%=b%> cm</option>
                        <%}%>
                    </select>
                </div>
            </div>
            <div class="row">
                <div class="col-12 d-flex flex-column align-items-center">
                    <p class="p-0" style="width: 65%;">Tagtype</p>
                    <select class="inputbig">
                        <% if(roofs != null){
                            for (Roof r : roofs)  {%>
                        <option value="<%=r.getRoof_id()%>"><%=r.getName()%></option>
                        <%}}%>
                    </select>
                </div>
            </div>
            <div class="row">
                <div class="col-12 d-flex flex-column align-items-center">
                    <p class="p-0" style="width: 65%;">Redskabsrum?</p>
                    <select onchange="wantShed()" id="shedChoice" class="inputbig">
                        <option value="n/a">Vælg</option>
                        <option value="1">Ja</option>
                        <option value="2">Nej</option>
                    </select>
                </div>
            </div>
            <div class="row">
                <div class="col-12 d-flex flex-column align-items-center">
                    <p class="p-0" style="width: 65%;" ><label id="shedWidthLabel" style="margin-bottom: 0px;" class="d-none">Redskabsrummets bredde</label></p>
                    <select id="shedWidth" class="inputbig d-none" class="d-none">
                        <option value="n/a">Vælg</option>
                        <% a = 180;
                            for (int i = 0; i < 18; i += 1) {
                                a += 30;%>
                        <option id="widthOption<%=i%>"> <%= a%> cm </option>
                        <% }%>
                    </select>
                </div>
            </div>
            <div class="row">
                <div class="col-12 d-flex flex-column align-items-center">
                    <p class="p-0" style="width: 65%;" ><label id="shedLengthLabel" style="margin-bottom: 0px;" class="d-none">Redskabsrummets Længde</label></p>
                    <select id="shedLength" class="inputbig d-none" class="d-none">
                        <option value="n/a">Vælg</option>
                        <% a = 180;
                            for (int i = 0; i < 18; i += 1) {
                                a += 30;%>
                        <option id="lengthOption<%=i%>"> <%= a%> cm </option>
                        <% }%>
                    </select>
                </div>
            </div>
            <h3>Kontaktinformationer</h3>
           
            <div id="adress">
                <div class="row">
                    <div class="col-sm-6 p-0 col-sm-6-l">
                        <div class="textsmall">Fornavn</div>
                    </div>
                    <div class="col-sm-6 p-0 col-sm-6-r">
                        <div class="textsmallr">Efternavn</div>
                    </div>
                    <div class="col-sm-6 p-0 col-sm-6-l">
                        <input name="fname" onfocus="this.value=''" id="fname" class="inputsmalll" type="text" value="" placeholder="Fornavn..." required>
                    </div>
                    <div class="col-sm-6 p-0 col-sm-6-r d-sm-none">
                        <div class="textsmall">Efternavn</div>
                    </div>
                    <div class="col-sm-6 p-0 col-sm-6-r">
                        <input id="lname" name="lname" onfocus="this.value=''" class="inputsmallr" type="text" value="" placeholder="Efternavn..." required>
                    </div>
                </div>  
                <div class="row">
                    <div class="col-sm-12 d-flex flex-column align-items-center">
                        <p class="p-0" style="width: 65%;">Adresse</p>
                        <input class="inputbig" name="address" onfocus="this.value=''" id="adresss" type="text" value="" placeholder="Adresse..." required>
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
                        <input class="inputsmalll" id="zip" name="zip" onfocus="this.value=''" type="text" value="" placeholder="Postnummer..." required>
                    </div>
                    <div class="col-sm-6 p-0 col-sm-6-r d-sm-none">
                        <div class="textsmall">By</div>
                    </div>
                    <div class="col-sm-6 p-0 col-sm-6-r">
                        <input class="inputsmallr" id="city" name="city" onfocus="this.value=''" type="text" value="" placeholder="By..." required>
                    </div>
                </div>
                <div class="row">
                    <div class="col-sm-12 d-flex flex-column align-items-center">
                        <p class="p-0" style="width: 65%;">Email</p>
                        <input class="inputbig" id="email" name="email" onfocus="this.value=''" type="text" value="" placeholder="Email..." required>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-sm-12 d-flex justify-content-center">
                    <input type="submit" class="btn notmemberbtn" value="Send forespørgsel">
                    <input type="hidden" name="command" value="request">
                </div>
            </div>   
        </form>
    </div>

    <script type="text/javascript">
        /*function wantShed() {
            var shedChoice = document.getElementById("shedChoice").value;
            var shedWidth = document.getElementById("shedWidth");
            var shedLength = document.getElementById("shedLength");
            if (shedChoice == 1) {
                shedWidth.classList.remove("d-none");
                shedLength.classList.remove("d-none");
                document.getElementById("shedWidthLabel").classList.remove("d-none");
                document.getElementById("shedLengthLabel").classList.remove("d-none");
            } else if (shedChoice == 2) {
                shedWidth.classList.add("d-none");
                shedLength.classList.add("d-none");
                document.getElementById("shedWidthLabel").classList.add("d-none");
                document.getElementById("shedLengthLabel").classList.add("d-none");
            }
        }
        function widthSubtract30()
        {

            var shedLengthOptions = document.querySelectorAll("#shedWidth option");
            shedLengthOptions.forEach(shedOption => {
                shedOption.disabled = false;
            });

            var chosenWidth = document.getElementById("carportWidth").value;
            for (i = chosenWidth; i <= 18; i++)
            {
                document.getElementById("widthOption" + i).disabled = true;
            }

        }
        function lengthSubtract30()
        {

            var shedLengthOptions = document.querySelectorAll("#shedLength option");
            shedLengthOptions.forEach(shedOption => {
                shedOption.disabled = false;
            });

            var chosenLength = document.getElementById("carportLength").value;
            for (i = chosenLength; i <= 18; i++)
            {
                document.getElementById("lengthOption" + i).disabled = true;
            }

        }
    </script>