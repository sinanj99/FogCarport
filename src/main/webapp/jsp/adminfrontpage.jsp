<%-- 
    Document   : adminfrontpage
    Created on : 22-05-2019, 00:13:59
    Author     : Obaydah Mohamad
--%>
<%@page import="Data.Entity.User"%>
<%
    User user = (User) session.getAttribute("user");
    if (user == null || user != null && !user.isAdmin()) {
        response.sendRedirect("login.jsp");
    }
%>
<jsp:include page='/include/sitehead.jsp'></jsp:include>
    <body class="background2">
    <jsp:include page='/include/sitemenu.jsp'></jsp:include>
    <div class="container">
        <div class="standarddiv">
            <div class="row d-flex justify-content-center">
                <div class="col-12 d-flex flex-column align-items-center justify-content-center">
                    <h1>Velkommen <i class="fas fa-smile" style="color: yellow;"></i></h1>
                    
                </div>
            </div>
        </div>
    </div>
<jsp:include page='/include/sitefoot.jsp'></jsp:include>
