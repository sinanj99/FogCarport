<%@page import="Data.Entity.Request"%>
<%@page import="java.util.List"%>
<jsp:include page='/include/sitehead.jsp'></jsp:include>
<jsp:include page='/include/sitemenu.jsp'></jsp:include>

<% List<Request> requests = (List) request.getAttribute("requests");%>
<body class="background2">
    <div class="container d-flex flex-column justify-content-center">
        <div class="standarddiv">
            <div class="row">
                <div class="col-md-12">
                    <h1>Forespørgelser</h1>
                    <table class="table showorders-table">
                        <thead class="showorders-thead">
                            <tr>
                                <th class="showorders-th" scope="col">Ordrenr.:</th>
                                <th class="showorders-th" scope="col">Kunde</th>
                                <th class="showorders-th" scope="col">Dato</th>
                                <th class="showorders-th" scope="col">Type</th>
                                <th class="showorders-th" scope="col"></th>
                            </tr>
                        </thead>
                        <tbody>
                            <% for (Request r : requests) {%>
                            <tr class="showorders-tr">
                                <td><%=r.getReq_id()%></td>
                                <td><%=r.getAddress().getFirstname() + " " + r.getAddress().getLastname()%></td>
                                <td><%=r.getDatePlaced()%></td>
                                <td>
                                <%
                                    if (r.getCarport().getInclination() != 0 && r.getCarport().getShed_().getLength() == 0) {
                                        out.println("Carport med rejsning");
                                    } else if (r.getCarport().getInclination() != 0 && r.getCarport().getShed_().getLength() != 0) {
                                        out.println("Carport med rejsning");
                                    } else if (r.getCarport().getInclination() != 0 && r.getCarport().getShed_().getLength() != 0) {
                                        out.println("Carport med rejsning & skur");
                                    } else if (r.getCarport().getInclination() == 0 && r.getCarport().getShed_().getLength() != 0) {
                                        out.println("Carport uden rejsning, men med skur");
                                    }else{
                                        out.println("Carport uden rejsning");
                                    }
                                %>
                                </td>
                                <td>
                                    <a class="btn btn-primary" href="FrontController?command=createOffer&requestID=<%=r.getReq_id()%>" role="button" style="border-radius: 2px; font-weight: 600; font-size: .8rem; padding: 0px 5px;">SE ORDRE</a>
                                </td>
                            </tr>
                            <%}%>
                        </tbody>
                    </table>

                </div>
            </div>
        </div>
    </div>
    <jsp:include page='/include/sitefoot.jsp'></jsp:include>

        <!--
        
        <div class="col-12 d-flex flex-column justify-content-center align-items-center">
                            <div class="list-group">
                                <hr>
                                <h1>Forespørgsler: </h1>
                                <hr style="width:100%;">
    <%--for (Request r : requests) {%>
    <a href="FrontController?command=createOffer&requestID=<%=r.getReq_id()%>">
        <b>Ordrenummer: </b><%=r.getReq_id()%><br>
        <b>Kunde: </b> <%=r.getAddress().getFirstname() + " " + r.getAddress().getLastname()%><br>
        <b>Tidspunkt for oprettelse: </b> <%=r.getDatePlaced()%>
        <%if (r.getCarport().getInclination() != 0 && r.getCarport().getShed_().getLength() == 0) {%>
        <br><b>Type:</b> Carport med rejsning
        <% } else if (r.getCarport().getInclination() != 0 && r.getCarport().getShed_().getLength() != 0) {%>
        <br><b>Type:</b> Carport med rejsning & skur 
        <% } else if (r.getCarport().getInclination() == 0 && r.getCarport().getShed_().getLength() != 0) {%>
        <br><b>Type:</b> Carport med rejsning & skur  
        <% } else { %>
        <br><b>Type:</b> Carport uden rejsning  
        <% }%>
        
    </a>
    <hr style="width: 100%;">
    <% }%>
</div>
</div>
    --%>
    -->
