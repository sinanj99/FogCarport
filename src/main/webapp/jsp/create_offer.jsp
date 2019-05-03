<%@page import="Data.Entity.Request"%>
<%@page import="Data.Entity.Roof"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Data.Entity.Material"%>
<%@page import="java.util.List"%>
<%@page import="Data.Entity.Type"%>
<%@page import="Data.Entity.LineItem"%>
<%@page import="Data.Entity.BOM"%>
<%      int buyPrice = (int) request.getAttribute("buyPrice");
    int sellPrice = (int) request.getAttribute("sellPrice");
    Request r = (Request) request.getAttribute("request");
    List<LineItem> materialsLength = (List) request.getAttribute("materialLength");
    List<LineItem> rooftiles = (List) request.getAttribute("roofTiles");
    List<LineItem> roofmaterials = (List) request.getAttribute("roofMaterials");
    List<LineItem> materialsNoLength = (List) request.getAttribute("materialsNoLength");
    int fullPrice = 0;
%>

<jsp:include page='/include/sitehead.jsp'></jsp:include>
    <body class="background2">
    <jsp:include page='/include/sitemenu.jsp'></jsp:include>
        <div class="container d-flex flex-column justify-content-center">
            <div class="standarddiv pb-0">
                <div class="row">
                    <div class="col-12 d-flex flex-column justify-content-center align-items-center">

                    <h1 class="mb-0"><b>Stykliste</b></h1>

                    <table class="table table-striped mb-0">
                        <h3>Træ</h3>
                        <tr>
                            <th>Materiale</th>
                            <th>Længde</th>
                            <th>Antal</th>
                            <th>Enhed</th>
                            <th>Beskrivelse</th>
                        </tr>

                        <%for (LineItem l : materialsLength) {%>
                        <tr>
                            <td><%=l.getMaterial().getName()%></td>
                            <td><%=l.getMaterial().getLength()%></td>
                            <td><%=l.getQty()%></td>
                            <td><%=l.getMaterial().getUnit()%></td>
                            <td><%=l.getDescription()%></td>
                        </tr>
                        <% }%>
                    </table>


                    <table class="table table-striped mb-0">
                        <h3>Tagpakken</h3>
                        <tr>
                            <th>Materiale</th>
                            <th>Antal</th>
                            <th>Enhed</th>
                            <th>Beskrivelse</th>
                        </tr>

                        <%for (int i = 0; i < rooftiles.size(); i++) {%>
                        <tr>
                            <%if (i == 0) {%>
                            <td><%=rooftiles.get(i).getRoof().getName()%> - Tagsten</td>
                            <% } else if (i == 1) {%>
                            <td><%=rooftiles.get(i).getRoof().getName()%> - Rygsten</td>
                            <% } else {%>
                            <td><%=rooftiles.get(i).getRoof().getName()%></td>
                            <% }%>
                            <td><%=rooftiles.get(i).getQty()%></td>
                            <td>Stk.</td>
                            <td><%=rooftiles.get(i).getDescription()%></td>
                        </tr>
                        <% }%>
                        <%for (LineItem l : roofmaterials) {%>
                        <tr>
                            <td><%=l.getMaterial().getName()%></td>
                            <td><%=l.getQty()%></td>
                            <td><%=l.getMaterial().getUnit()%></td>
                            <td><%=l.getDescription()%></td>
                        </tr>
                        <% }%>
                    </table>

                    <table class="table table-striped mb-0">
                        <h3>Beslag & Skruer</h3>
                        <tr>
                            <th>Materiale</th>
                            <th>Antal</th>
                            <th>Enhed</th>
                            <th>Beskrivelse</th>
                        </tr>

                        <%for (LineItem l : materialsNoLength) {%>
                        <tr>
                            <td><%=l.getMaterial().getName()%></td>
                            <td><%=l.getQty()%></td>
                            <td><%=l.getMaterial().getUnit()%></td>
                            <td><%=l.getDescription()%></td>
                        </tr>
                        <% }%>
                    </table>
                    <h3>Indkøbspris: <%= buyPrice%>,-</h3>
                    <hr style="width: 100%;" class="m-0">
                    <h3>Foreslået salgspris: <u><%=sellPrice%>,-</u></h3>
                    <hr style="width: 100%;" class="m-0">
                    <h1><b>Carportens mål:</b></h1>

                        <div class="d-flex" style="padding-bottom: 40px;">
                            <div class="d-flex flex-column align-items-center">
                                <h3 class="">Carport:</h3>
                                <p>Bredde: <%=r.getCarport().getWidth()%></p>
                            <p>Længde: <%=r.getCarport().getLength()%></p>
                        </div>

                        <%if (r.getCarport().getShed_().getLength() != 0) {%>
                        <div class="d-flex flex-column align-items-center">
                            <h3 class="">Redskabsrum: </h3>
                            <p>Bredde: <%=r.getCarport().getShed_().getWidth()%></p>
                            <p>Længde: <%=r.getCarport().getShed_().getLength()%></p>
                        </div>

                        <% } %>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>