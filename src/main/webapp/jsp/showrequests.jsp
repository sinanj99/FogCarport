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
                        <%for (Request r : requests) {%>
                        <a href="FrontController?command=createOffer&requestID=<%=r.getReq_id()%>">
                            <%="Lavet af: " + r.getAddress().getFirstname() + " " + r.getAddress().getLastname() + " - Tidspunkt for oprettelse: " + r.getDatePlaced()%>
                        </a>
                        <hr style="width: 100%;">
                        <% }%>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <jsp:include page='/include/sitefoot.jsp'></jsp:include>