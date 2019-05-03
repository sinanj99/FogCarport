<%@page import="Data.Entity.LineItem"%>
<%@page import="Data.Entity.BOM"%>
<%BOM bom = (BOM) request.getAttribute("bom"); %>
<jsp:include page='/include/sitehead.jsp'></jsp:include>
    <body class="background1">
    <jsp:include page='/include/sitemenu.jsp'></jsp:include>
        <div class="container d-flex flex-column justify-content-center">
            <div class="standarddiv">
                <div class="row">
                    <div class="col-12 d-flex flex-column justify-content-center align-items-center">
                        <table class="table table-striped">
                            <h1>Træ</h1>
                            <tr>
                                <th>Materiale</th>
                                <th>Længde</th>
                                <th>Antal</th>
                                <th>Enhed</th>
                                <th>Beskrivelse</th>
                            </tr>


                            <%for (LineItem l : bom.getLineitems()) {%>
                        <tr>
                            <td><%=l.getMaterial().getName()%></td>
                            <td><%=l.getMaterial().getLength()%></td>
                            <td><%=l.getQty()%></td>
                            <td><%=l.getMaterial().getUnit()%></td>
                            <td><%=l.getDescription()%></td>
                        </tr>
                        <% }%>
                    </table>
                    <table class="table table-striped">
                            <h1>Tagpakken</h1>
                            <tr>
                                <th>Materiale</th>
                                <th>Længde</th>
                                <th>Antal</th>
                                <th>Enhed</th>
                                <th>Beskrivelse</th>
                            </tr>


                            <%for (LineItem l : bom.getLineitems()) {%>
                        <tr>
                            <td><%=l.getMaterial().getName()%></td>
                            <td><%=l.getMaterial().getLength()%></td>
                            <td><%=l.getQty()%></td>
                            <td><%=l.getMaterial().getUnit()%></td>
                            <td><%=l.getDescription()%></td>
                        </tr>
                        <% }%>
                    </table>
                    <table class="table table-striped">
                            <h1>Beslag & Skruer</h1>
                            <tr>
                                <th>Materiale</th>
                                <th>Længde</th>
                                <th>Antal</th>
                                <th>Enhed</th>
                                <th>Beskrivelse</th>
                            </tr>


                            <%for (LineItem l : bom.getLineitems()) {%>
                        <tr>
                            <td><%=l.getMaterial().getName()%></td>
                            <td><%=l.getMaterial().getLength()%></td>
                            <td><%=l.getQty()%></td>
                            <td><%=l.getMaterial().getUnit()%></td>
                            <td><%=l.getDescription()%></td>
                        </tr>
                        <% }%>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>