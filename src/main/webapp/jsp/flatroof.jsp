
<%@page import="Data.Entity.User"%>
<%@page import="Data.Entity.Roof"%>
<%@page import="java.util.List"%>
<jsp:include page='/include/sitehead.jsp'></jsp:include>
<jsp:include page='/include/sitemenu.jsp'></jsp:include>
<%
    if(request.getAttribute("access") == null)  response.sendRedirect("FrontController?command=flatroof");
    List<Roof> roofs = (List<Roof>) request.getAttribute("roofs");
    User user = (User) session.getAttribute("user");
    if(user == null) {
        response.sendRedirect("jsp/login.jsp");
    }else if(user != null && (user.isAdmin() || user.isSeller())){
        request.getRequestDispatcher("/FrontController?command=frontpageredirect").forward(request, response);
    }
    request.setAttribute("inclined", "false");
    String firstname = user.getInfo().getFirstname(); %>

<body onload="spaceForShed(); checkAll()" class="background2">
    <div class="container">
        <form onkeyup="checkAll()" class="standarddiv pl-0 pr-0 d-flex flex-column justify-content-center">
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
                    <select required name="rchoice" id="rchoice" class="inputbig" onchange="visualizeRoof();">
                        <option value="n/a">Vælg</option>
                        <% for (Roof r : roofs) {%>
                        <option value="<%=r.getRoof_id()%>" ><%=r.getName()%></option>

                        <% } %>
                    </select>

                    <% for (Roof r : roofs) {%>
                    <p name="pics" id="<%=r.getName() + "text"%>" class="p-0 d-none" style="width: 65%;">Udseende: </p>
                    <img name="pics" id="<%=r.getName()%>" class="pics d-none col-xs-12" style="margin-bottom: 10px; margin-top: 10px; border: 1px solid black; height:auto; width: 65%; max-width: 500px;" src="/project/images/<%=r.getName()%>.jpg" alt="<%=r.getName()%>" > 
                    <% } %>
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

            <div id="adress">
                <div class="row">
                    <div class="col-sm-6 p-0 col-sm-6-l">
                        <div class="textsmall">Fornavn</div>
                    </div>
                    <div class="col-sm-6 p-0 col-sm-6-r">
                        <div class="textsmallr">Efternavn</div>
                    </div>
                    <div class="col-sm-6 p-0 col-sm-6-l">
                        <input name="fname"  id="fname" class="inputsmalll check" type="text" value="<%=firstname%>" placeholder="Fornavn..." required onkeyup="check(this)">
                    </div>
                    <div class="col-sm-6 p-0 col-sm-6-r d-sm-none">
                        <div class="textsmall">Efternavn</div>
                    </div>
                    <div class="col-sm-6 p-0 col-sm-6-r">
                        <input id="lname" name="lname"  class="inputsmallr check" type="text" value="<%=user.getInfo().getLastname()%>" placeholder="Efternavn..." required onkeyup="check(this)">
                    </div>
                </div>  
                <div class="row">
                    <div class="col-sm-12 d-flex flex-column align-items-center">
                        <p class="p-0" style="width: 65%;">Adresse</p>
                        <input class="inputbig check" name="address"  id="address" type="text" value="<%=user.getInfo().getAddress()%>" placeholder="Adresse..." required onkeyup="check(this)">
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
                        <input class="inputsmalll check" id="zip" name="zip"  type="text" value="<%=user.getInfo().getZipcode()%>" placeholder="Postnummer..." required onkeyup="check(this)">
                    </div>
                    <div class="col-sm-6 p-0 col-sm-6-r d-sm-none">
                        <div class="textsmall">By</div>
                    </div>
                    <div class="col-sm-6 p-0 col-sm-6-r">
                        <input class="inputsmallr check" id="city" name="city"  type="text" value="<%=user.getInfo().getCity()%>" placeholder="By..." required onkeyup="check(this)">
                    </div>
                </div>
                <div class="row">
                    <div class="col-sm-12 d-flex flex-column align-items-center">
                        <p class="p-0" style="width: 65%;">Email</p>
                        <input class="inputbig check" id="email" name="email"  type="text" value="<%=user.getEmail()%>" placeholder="Email..." required onkeyup="check(this)">
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-sm-12 d-flex justify-content-center">
                    <input id="submit" type="submit" class="btn notmemberbtn" value="Send forespørgsel">
                    <input type="hidden" name="command" value="request">
                    <input type="hidden" name="inclined" value="false">
                </div>
            </div>
        </form>
    </div>

    <script type="text/javascript">

        function visualizeRoof() {

            var elements = document.getElementsByName("pics");
            for (var i = 0; i < elements.length; i++) {
                elements[i].classList.add("d-none");
            }
            var element = document.getElementById("rchoice");
            var text = element.options[element.selectedIndex].text;
            document.getElementById(text).classList.remove("d-none");
            document.getElementById(text + "text").classList.remove("d-none");
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

    <script src="/project/include/javascript_validation.js" async></script>