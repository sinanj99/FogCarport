<%@page import="Data.Entity.Request"%>
<%@page import="java.util.List"%>
<jsp:include page='/include/sitehead.jsp'></jsp:include>
<jsp:include page='/include/sitemenu.jsp'></jsp:include>

<% List<Request> requests = (List) request.getAttribute("requests");%>
<body class="background2">
    <div class="container d-flex flex-column justify-content-center">
        <div class="standarddiv">
            <div class="row">
                <div class="col-12 d-flex flex-column justify-content-center align-items-center">
                    <div class="list-group">
                        <hr>
                        <h1>Forespørgsler: </h1>
                        <hr style="width:100%;">
                        <%for (Request r : requests) {%>
                        <a href="FrontController?command=createOffer&requestID=<%=r.getReq_id()%>">
                            <b>Lavet af: </b> <%=r.getAddress().getFirstname() + " " + r.getAddress().getLastname()%> <b>Tidspunkt for oprettelse: </b> <%=r.getDatePlaced()%>
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
            </div>
        </div>
    </div>
    <jsp:include page='/include/sitefoot.jsp'></jsp:include>