<%@page import="Data.Entity.User"%>
<%
    String pwordError = (String) request.getAttribute("passwordError");
    String unameError = (String) request.getAttribute("emailError");
    
    User user = (User) session.getAttribute("user");
    if(user != null) {
        request.getRequestDispatcher("/FrontController?command=frontpageredirect").forward(request, response);
    }
%>
<jsp:include page='/include/sitehead.jsp'></jsp:include>
    <body class="background1">
    <jsp:include page='/include/sitemenu.jsp'></jsp:include>
        <div class="container d-flex flex-column justify-content-center">
            <form class="newform" method="post" action="/project/FrontController?">
                <h1>Kontooplysninger</h1>
                <div class="row">
                    <div class="col-sm-12 d-flex flex-column align-items-center">
                        <p class="p-0" style="width: 50%; color: white">Email</p>
                    <%if (unameError == null) {%>
                    <input style="width: 50%;" class="inputbig" name="email" type="text" placeholder="Email..." required>
                    <% } else { %>
                    <input style="width: 50%;" class="error inputbig" name="email" type="text" placeholder="<%=unameError%>" required>
                    <% } %>
                </div>
            </div>
            <div class="row">
                <div class="col-sm-12 d-flex flex-column align-items-center">
                    <p class="p-0" style="width: 50%; color: white">Adgangskode</p>
                    <% if (pwordError == null) { %>
                    <input style="width: 50%;" class="inputbig" name="pword" type="password" placeholder="Adgangskode..." required>
                    <% } else { %>
                    <input style="width: 50%;" class="error inputbig" name="pword" type="password" placeholder="<%=pwordError%>" required>
                    <% }%>
                </div>
            </div>
            <div class="col-sm-12 d-flex justify-content-center">
                <input class="btn nicebtn" type="submit" value="Log ind">
                <input type="hidden" name="command" value="login">
            </div>
        </form>
        <form class="newform2" action="register.jsp">
            <p style="color: #0f0c28; text-align: center;">Ikke allerede medlem?</p>
            <div class="row">
                <div class="col-sm-12 d-flex justify-content-center">
                    <input class="btn notmemberbtn" type="submit" value="Registrer">
                </div>
            </div>
        </form>
    </div>
    <jsp:include page='/include/sitefoot.jsp'></jsp:include>