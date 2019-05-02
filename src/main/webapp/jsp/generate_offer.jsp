<%@page import="Data.Entity.LineItem"%>
<%@page import="Data.Entity.BOM"%>
<%BOM bom = (BOM) request.getParameter("BOM"); %>
<jsp:include page='/include/sitehead.jsp'></jsp:include>
    <body class="background1">
    <jsp:include page='/include/sitemenu.jsp'></jsp:include>
        <div class="container d-flex flex-column justify-content-center">
            <div class="standarddiv">
                <div class="row">
                    <div class="col-12 d-flex flex-column justify-content-center align-items-center">
                        <div class="list-group">
                        <%for (LineItem l : bom.getLineitems()) {%>
                        <a href="#" class="list-group-item">
                            <%= l.getQty() %>
                        </a>
                        <% }%>
                    </div>
                </div>
            </div>
        </div>
    </div>