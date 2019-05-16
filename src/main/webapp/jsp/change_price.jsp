<%@page import="Data.Entity.Roof"%>
<%@page import="java.util.List"%>
<%@page import="Data.Entity.Material"%>
<jsp:include page='/include/sitehead.jsp'></jsp:include>
    <body class="background2">
    <jsp:include page='/include/sitemenu.jsp'></jsp:include>
    <%
        String priceError = (String) request.getAttribute("priceError");
        String idError = (String) request.getAttribute("idError");
        List<Material> materials = (List<Material>) request.getAttribute("materials");
        List<Roof> roofs = (List<Roof>) request.getAttribute("roofs");
    %>
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <form class="col-12 standarddiv" onsubmit="return checkPrice()" method="post" action="/project/FrontController">
                    <h1>Opdater pris</h1>
                    <div class="col-12 d-flex flex-column align-items-center">
                        <div class="d-flex flex-column" style="width: 60%;">
                            <span id="errortxt" class="d-none" style="color: red">Indtast venligst et positivt heltal mellem 1 og 4 cifre.</span>
                            <p class="p-0">Vælg varetype</p>
                            <br><span> <input type="radio" name="where" value="length"required> Materiale (med længde)</span>
                            <span> <input type="radio" name="where" value="nolength"required> Materiale (uden længde)</span>
                            <span> <input type="radio" name="where" value="roof" required> Tag</span><br>
                        </div>
                    </div>
                    <div class="col-12 d-flex flex-column align-items-center">
                        <div class="d-flex flex-column" style="width: 60%;">
                            Varens id
                            <%if (idError == null) { %>
                            <input style="width:100%" class="ml-0 inputbig" id="id" name="id" placeholder="Indtast id..." type="number" max="100" required>
                            <% } else {%>
                            <input style="width:100%; border: 1px solid red" class="ml-0 inputbig" id="id" name="id" placeholder="<%=idError%>" type="number" max="100" required>
                            <% } %>
                        </div>
                    </div>
                    <div class="col-12 d-flex flex-column align-items-center">
                        <div class="d-flex flex-column" style="width: 60%;">
                            Ny pris
                            <%if (priceError == null) { %>
                            <input style="width:100%" class="ml-0 inputbig" id="price" name="price" placeholder="Indtast pris..." type="number" required>
                            <% } else {%>
                            <input style="width:100%; border: 1px solid red" class="ml-0 inputbig" id="price" name="price" placeholder="<%=priceError%>" type="number" required>
                            <% } %>
                        </div>
                        <input style="width: 30%" class="notmemberbtn"type="submit" value="Opdater pris">
                        <input type="hidden" name="command" value="change_price">
                        <input type="hidden" name="manual" value="true">
                    </div>
                </form>
            </div>
        </div>
        <div class="row">
            <div class="col-md-6">
                <div class="standarddiv p-0 mt-0">
                    <form class="d-flex flex-column" onsubmit="return checkPrice()" method="post" action="/project/FrontController">
                        <h3>Materialer (med længde)</h3>
                        <div style="max-height: 300px; overflow: auto; display:inline-block;" class="col-12">
                            <table class="table table-bordered table-striped">
                                <thead>
                                    <tr>
                                        <td>ID</td>
                                        <td>Navn</td>
                                        <td>Pris</td>
                                        <td>Vælg</td>
                                    </tr>
                                </thead>
                                <tbody>
                                    <%for (Material m : materials) {%>
                                    <%if (m.getLength() > 0) {%>
                                    <tr>
                                        <td><%=m.getId()%></td>
                                        <td><%=m.getName()%></td>
                                        <td><%=m.getPrice()%> (<%=m.getLength()%>cm)</td>
                                        <td><input type="radio" name="id" value="<%=m.getId()%>" required></td>
                                    </tr>
                                    <% } %>
                                    <% } %>
                                </tbody>
                            </table>
                        </div>
                        <div class="col-12 d-flex align-items-center flex-column">
                            <br>
                            <div style="width: 60%">
                                Indtast den nye pris
                            </div>
                            <div style="width: 60%">
                                <%if (priceError == null) { %>
                                <input type="text" class="w-100 ml-0 inputbig" name="price" placeholder="Indtast ny pris...">
                                <% } else {%>
                                <input style="border: 1px solid red" type="number" class="w-100 ml-0 inputbig" name="price" placeholder="<%=priceError%>">
                                <% } %>
                            </div>

                            <input type="submit" class="notmemberbtn" style="width: 60%;" value="Opdater pris">
                            <input type="hidden" name="command" value="change_price">
                            <input type="hidden" name="where" value="length">
                        </div>
                    </form>
                </div>
            </div>
            <div class="col-md-6">
                <div class="standarddiv p-0 mt-0">
                    <form class="d-flex flex-column" onsubmit="return checkPrice()" method="post" action="/project/FrontController">
                        <h3>Materialer (uden længde)</h3>
                        <div style="max-height: 300px; overflow: auto; display:inline-block;" class="col-12">
                            <table class="table table-bordered table-striped">
                                <thead>
                                    <tr>
                                        <td>ID</td>
                                        <td>Navn</td>
                                        <td>Pris</td>
                                        <td>Vælg</td>
                                    </tr>
                                </thead>
                                <tbody>
                                    <%for (Material m : materials) {%>
                                    <%if (m.getLength() == 0) {%>
                                    <tr>
                                        <td><%=m.getId()%></td>
                                        <td><%=m.getName()%></td>
                                        <td><%=m.getPrice()%></td>
                                        <td><input type="radio" name="id" value="<%=m.getId()%>" required></td>
                                    </tr>
                                    <% } %>
                                    <% } %>
                                </tbody>
                            </table>
                        </div>
                        <div class="col-12 d-flex align-items-center flex-column">
                            <br>
                            <div style="width: 60%">
                                Indtast den nye pris
                            </div>
                            <div style="width: 60%">
                                <%if (priceError == null) { %>
                                <input type="text" class="w-100 ml-0 inputbig" name="price" placeholder="Indtast ny pris...">
                                <% } else {%>
                                <input style="border: 1px solid red" type="number" class="w-100 ml-0 inputbig" name="price" placeholder="<%=priceError%>">
                                <% } %>
                            </div>

                            <input type="submit" class="notmemberbtn" style="width: 60%;" value="Opdater pris">
                            <input type="hidden" name="command" value="change_price">
                            <input type="hidden" name="where" value="nolength">
                        </div>
                    </form>
                </div>
            </div>

        </div>
        <div class="row">
            <div class="col-md-12">
                <div class="standarddiv p-0 mt-0">
                    <form class="d-flex flex-column" onsubmit="return checkPrice()" method="post" action="/project/FrontController">
                        <h3>Tagplader/sten</h3>
                        <div style="max-height: 300px; overflow: auto; display:inline-block;" class="col-12">
                            <table class="table table-bordered table-striped">
                                <thead>
                                    <tr>
                                        <td>ID</td>
                                        <td>Navn</td>
                                        <td>Type</td>
                                        <td>Pris</td>
                                        <td>Vælg</td>
                                    </tr>
                                </thead>
                                <tbody>
                                    <%for (Roof r : roofs) {%>
                                    <tr>
                                        <td><%=r.getRoof_id()%></td>
                                        <td><%=r.getName()%></td>
                                        <%if (r.isInclined()) {%>
                                        <td>Med hældning</td>
                                        <% } else { %>
                                        <td>Uden hældning</td>
                                        <% }%>
                                        <td><%=r.getPrice()%> (<%=r.getLength()%>cm)</td>
                                        <td><input type="radio" name="id" value="<%=r.getRoof_id()%>" required></td>
                                    </tr>
                                    <% }%>
                                </tbody>
                            </table>
                        </div>
                        <div class="col-12 d-flex align-items-center flex-column">
                            <br>
                            <div style="width: 60%">
                                Indtast den nye pris
                            </div>
                            <div style="width: 60%">
                                <%if (priceError == null) { %>
                                <input style="width:100%" class="ml-0 inputbig" id="price" name="price" placeholder="Indtast pris..." type="number" required>
                                <% } else {%>
                                <input style="width:100%; border: 1px solid red" class="ml-0 inputbig" id="price" name="price" placeholder="<%=priceError%>" type="number" required>
                                <% }%>
                            </div>

                            <input type="submit" class="notmemberbtn" style="width: 30%;" value="Opdater pris">
                            <input type="hidden" name="command" value="change_price">
                            <input type="hidden" name="where" value="roof">
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
    <script>
        function checkPrice() {
            var price = document.getElementById("price");
            var errortxt = document.getElementById("errortxt");
            var regex = /[1-9{1}]/g;
            console.log(price.value);
            var found = regex.test(price.value);
            if (!found) {
                console.log(found)
                errortxt.classList.remove("d-none");
                price.style.border = "2px solid red";
            }
        }
    </script>        
    <jsp:include page='/include/sitefoot.jsp'></jsp:include>