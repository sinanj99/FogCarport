<%@page import="java.lang.String"%>
<jsp:include page='/include/sitehead.jsp'></jsp:include>
    <body class="background1">
    <jsp:include page='/include/sitemenu.jsp'></jsp:include>
    <%String message = (String) request.getAttribute("message"); %>
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <div class="standarddiv">
                    <div class="col-12">
                        <h1>Der er opst�et en fejl!</h1>
                    </div>
                    <div class="col-12 d-flex justify-content-center">
                        <h3>Vi arbejder p� sagen!</h3>
                    </div>
                </div>
            </div>

            <jsp:include page='/include/sitefoot.jsp'></jsp:include>
