
<%@page import="Data.User"%>
<%@page import="Data.Roof"%>
<%@page import="java.util.List"%>
<jsp:include page='/include/sitehead.jsp'></jsp:include>
<jsp:include page='/include/sitemenu.jsp'></jsp:include>
<%List<Roof> roofs = (List<Roof>) request.getAttribute("roofs");
    User user = (User) session.getAttribute("user");
    request.setAttribute("inclined", "false");
    String firstname = user.getInfo().getFirstname(); %>

<body onload="spaceForShed(); wantAdress();" class="background2">
    <div class="container">
        <form class="standarddiv pl-0 pr-0 d-flex flex-column justify-content-center">
            <h3>Tilpas din carport</h3>
            <div class="row">
                <div class="col-sm-12 d-flex flex-column align-items-center">
                    <p class="p-0" style="width: 65%;">Carportens bredde</p>
                    <select name="cwidth" required class="inputbig" id="carportWidth" onchange="spaceForShed(); widthSubtract30();">
                        <option value="n/a">Vælg</option>
                        <%
                            for (int i = 240; i <= 750; i += 30) {
                        %>
                        <option value="<%=i%>" ><%=i%> cm</option>
                        <%}%>
                    </select>
                </div>
            </div>
            <div class="row">
                <div class="col-sm-12 d-flex flex-column align-items-center">
                    <p class="p-0" style="width: 65%;">Carportens længde</p>
                    <select required name="clength" class="inputbig" id="carportLength" onchange="spaceForShed(); lengthSubtract30();">
                        <option value="n/a">Vælg</option>
                        <%
                            for (int i = 240; i <= 750; i += 30) {
                        %>
                        <option value="<%=i%>" ><%=i%> cm</option>
                        <%}%>
                    </select>
                </div>
            </div>
            <div class="row">
                <div class="col-sm-12 d-flex flex-column align-items-center">
                    <p class="p-0" style="width: 65%;">Tagtype</p>
                    <select required name="rchoice" class="inputbig">
                        <option value="n/a">Vælg</option>
                        <% for (Roof r : roofs) {%>
                        <option value="<%= r.getName()%>"><%= r.getName()%></option>
                        <% } %>
                    </select>
                </div>
            </div>
            <div class="row">
                <div class="col-sm-12 d-flex flex-column align-items-center">
                    <p class="p-0" style="width: 65%;"><label id="shedLabel">Redskabsrum?</label></p>
                    <select required name="schoice" onchange="wantShed()" id="shedChoice" class="inputbig">
                        <option value="n/a">Vælg</option>
                        <option id="yes" value="1">Ja</option>
                        <option value="2">Nej</option>
                    </select>
                </div>
            </div>
            <div class="row">
                <div class="col-sm-12 d-flex flex-column align-items-center">
                    <p class="p-0" style="width: 65%;" ><label id="shedWidthLabel" style="margin-bottom: 0px;" class="d-none">Redskabsrummets bredde</label></p>
                    <select required name="swidth" id="shedWidth" class="inputbig d-none" class="d-none">
                        <option value="n/a">Vælg</option>
                        <%
                            for (int i = 210; i <= 720; i += 30) {
                        %>
                        <option value="<%=i%>" id="widthOption<%=i%>" > <%=i%> cm</option>
                        <% }%>
                    </select>
                </div>
            </div>
            <div class="row">
                <div class="col-sm-12 d-flex flex-column align-items-center">
                    <p class="p-0" style="width: 65%;" ><label id="shedLengthLabel" style="margin-bottom: 0px;" class="d-none">Redskabsrummets Længde</label></p>
                    <select required name="slength" id="shedLength" class="inputbig d-none" class="d-none">
                        <option value="n/a">Vælg</option>
                        <%
                            for (int i = 150; i <= 510; i += 30) {
                        %>
                        <option value="<%=i%>" id="lengthOption<%=i%>" > <%=i%> cm</option>
                        <% }%>
                    </select>
                </div>
            </div>
            <h3>Kontaktinformationer</h3>
            <div class="row">
                <div class="col-sm-12 d-flex flex-column align-items-center">
                    <p class="p-0" style="width: 65%;">Brug gemte oplysninger?</p>
                    <select required onchange="wantAdress();" id="adressChoice" class="inputbig">
                        <option id="yes2" value="1">Ja</option>
                        <option value="2">Nej</option>
                    </select>
                </div>
            </div>
            <div id="adress">
                <div class="row">
                    <div class="col-sm-6 p-0 col-sm-6-l">
                        <div class="textsmall">Fornavn</div>
                    </div>
                    <div class="col-sm-6 p-0 col-sm-6-r">
                        <div class="textsmallr">Efternavn</div>
                    </div>
                    <div class="col-sm-6 p-0 col-sm-6-l">
                        <input name="fname" id="fname" class="inputsmalll" type="text" value="<%=firstname%>" placeholder="Fornavn..." required>
                    </div>
                    <div class="col-sm-6 p-0 col-sm-6-r d-sm-none">
                        <div class="textsmall">Efternavn</div>
                    </div>
                    <div class="col-sm-6 p-0 col-sm-6-r">
                        <input id="lname" name="lname" class="inputsmallr" type="text" value="<%=user.getInfo().getLastname()%>" placeholder="Efternavn..." required>
                    </div>
                </div>  
                <div class="row">
                    <div class="col-sm-12 d-flex flex-column align-items-center">
                        <p class="p-0" style="width: 65%;">Adresse</p>
                        <input class="inputbig" name="address" id="adresss" type="text" value="<%=user.getInfo().getAddress()%>" placeholder="Adresse..." required>
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
                        <input class="inputsmalll" id="zip" name="zip" type="text" value="<%=user.getInfo().getZipcode()%>" placeholder="Postnummer..." required>
                    </div>
                    <div class="col-sm-6 p-0 col-sm-6-r d-sm-none">
                        <div class="textsmall">By</div>
                    </div>
                    <div class="col-sm-6 p-0 col-sm-6-r">
                        <input class="inputsmallr" id="city" name="city" type="text" value="<%=user.getInfo().getCity()%>" placeholder="By..." required>
                    </div>
                </div>
                <div class="row">
                    <div class="col-sm-12 d-flex flex-column align-items-center">
                        <p class="p-0" style="width: 65%;">Email</p>
                        <input class="inputbig" id="email" name="email" type="text" value="<%=user.getEmail()%>" placeholder="Email..." required>
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
        function wantAdress() {
            var shedChoice = document.getElementById("adressChoice").value;
            var adress = document.getElementById("adress");
            if (shedChoice == 1) {
                document.getElementById("fname").disabled = true;
                document.getElementById("lname").disabled = true;
                document.getElementById("adresss").disabled = true;
                document.getElementById("zip").disabled = true;
                document.getElementById("city").disabled = true;
                document.getElementById("email").disabled = true;
            } else if (shedChoice == 2) {
                document.getElementById("yes2").disabled = true;
                document.getElementById("fname").disabled = false;
                document.getElementById("fname").value = "";
                document.getElementById("lname").disabled = false;
                document.getElementById("lname").value = "";
                document.getElementById("adresss").disabled = false;
                document.getElementById("adresss").value = "";
                document.getElementById("zip").disabled = false;
                document.getElementById("zip").value = "";
                document.getElementById("city").disabled = false;
                document.getElementById("city").value = "";
                document.getElementById("email").disabled = false;
                document.getElementById("email").value = "";
            }
        }
        function spaceForShed() {

            if (document.getElementById("carportLength").value == "n/a"
                    || document.getElementById("carportWidth").value == "n/a")
                    //explanation:
                            //you shouldn't be able to choose a toolshed, if a length or width is not specified. 
                            {
                                document.getElementById("shedChoice").classList.add("d-none");
                                document.getElementById("shedLabel").classList.add("d-none");

                            } else if (document.getElementById("carportLength").value < 390)
                            // explanation:
                                    // minimum length for toolshed is 150cm
                                            // minimum length for full carport is 240cm (excluding toolshed) for there to be space for a car. 
                                                    // therefore the carport must be at least 390 long (240+150) for there to be space for a tool shed. 
                                                    {
                                                        document.getElementById("shedLabel").classList.add("d-none");
                                                        document.getElementById("shedChoice").classList.add("d-none");

                                                    } else
                                            {
                                                document.getElementById("shedLabel").classList.remove("d-none");
                                                document.getElementById("shedChoice").classList.remove("d-none");
                                            }
                                        }
                                function wantShed() {

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
                                    } else if (shedChoice == "n/a") {
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
                                    var shedWidth = document.getElementById("shedWidth");
                                    shedWidth.selectedIndex = 0;
                                    var chosenWidth = document.getElementById("carportWidth").value;
                                    chosenWidth = chosenWidth - 0; // doesnt work if you do not do this. 
                                    for (i = chosenWidth; i <= 720; i += 30)
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
                                    var shedLength = document.getElementById("shedLength");
                                    shedLength.selectedIndex = 0;

                                    var chosenLength = document.getElementById("carportLength").value;
                                    var maxLength = chosenLength - 210; // 210 and not 240, as the first index is removed. 
                                    for (i = maxLength; i <= 750; i += 30)
                                    {
                                        document.getElementById("lengthOption" + i).disabled = true;
                                    }

                                }
    </script>