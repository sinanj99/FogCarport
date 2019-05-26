<%@page import="Data.Entity.User"%>
<%
    User user = (User) session.getAttribute("user");
    if (user == null) {
        response.sendRedirect("jsp/login.jsp");
    } else if (user != null && !user.isSeller()) {
        request.getRequestDispatcher("/FrontController?command=frontpageredirect").forward(request, response);
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
                    <p>Du er logget på som en sælger</p>
                </div>
            </div>
        </div>
    </div>
<jsp:include page='/include/sitefoot.jsp'></jsp:include>