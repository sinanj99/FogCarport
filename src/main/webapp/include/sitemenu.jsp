<%@page import="Data.Entity.User"%>
<%
    User user = (User) session.getAttribute("user");
    String error = (String) request.getAttribute("error");
    String detail = (String) request.getAttribute("detail");
%>
<div id="nav">
    <a class="right d-md-none" href="#" onclick="displayNav()"><i class="fas fa-bars"></i></a>
        <% if (user == null) {%>
    <a class="left" href="/project/jsp/frontpage.jsp">FOG CARPORT</a>
    <a class="right d-none d-md-block" href="/project/jsp/register.jsp"> <i class="fas fa-user-plus"></i>    Opret bruger</a>
    <a class="right d-none d-md-block" href="/project/jsp/login.jsp"> <i class="fas fa-sign-in-alt"></i>    Log ind</a>
    <% } else if (user != null && user.isSeller()) { %>
    <a class="left" href="/project/jsp/sellerfrontpage.jsp">FORSIDE</a>
    <a class="right d-none d-md-block" href="/project/FrontController?command=logout"> <i class="fas fa-sign-out-alt"></i>    Log ud</a>
    <a class="right d-none d-md-block" href="/project/FrontController?command=showrequests"> <i class="fas fa-clipboard-list"></i>    Vis forespørgsler</a>
    <% } else if (user != null && user.isAdmin()) { %>
    <a class="left" href="/project/jsp/adminfrontpage.jsp">FORSIDE</a>
    <a class="right d-none d-md-block" href="/project/FrontController?command=logout"> <i class="fas fa-sign-out-alt"></i>    Log ud</a>
    <a class="right d-none d-md-block" href="/project/FrontController?command=showprices"> <i class="fas fa-clipboard-list"></i>    Opdater priser</a>
    <% } else {%>
    <a class="left" href="/project/jsp/frontpage.jsp">FOG CARPORT</a>
    <a class="right d-none d-md-block" href="/project/FrontController?command=logout"> <i class="fas fa-sign-out-alt"></i>    Log ud</a>
    <a class="right d-none d-md-block" href="/project/FrontController?command=showresponses"> <i class="fas fa-clipboard-list"></i>    Vis tilbud</a>
    <% }%>
</div>

<%if (error != null) {%>
<!-- if request contains an error -->
<div class="errordiv" id="errordiv">
    <h1><%=error%></h1>
    <%if (detail != null) {%>
    <p><%=detail%></p>
    <%} %>
    <button class="notmemberbtn" onclick="removeDiv()">OK</button>
</form>
</div>
<% } %>

<div id="smallnav" class="d-none d-md-none smallnav">
    <div class="smallnavrow">
        <% if (user == null) {%>
        <a href="/project/jsp/register.jsp"> <i class="fas fa-user-plus"></i>    Opret bruger</a>
        <a href="/project/jsp/login.jsp"> <i class="fas fa-sign-in-alt"></i>    Log ind</a>
        <% } else if (user != null && user.isSeller()) { %>
        <a href="/project/FrontController?command=logout"> <i class="fas fa-sign-out-alt"></i>    Log ud</a>
        <a href="/project/FrontController?command=showrequests"> <i class="fas fa-clipboard-list"></i>    Vis forespørgsler</a>
        <% } else if (user != null && user.isAdmin()) { %>
        <a href="/project/FrontController?command=logout"> <i class="fas fa-sign-out-alt"></i>    Log ud</a>
        <a href="/project/FrontController?command=showprices"> <i class="fas fa-clipboard-list"></i>    Opdater priser</a>
        <% } else {%>
        <a href="/project/FrontController?command=logout"> <i class="fas fa-sign-out-alt"></i>    Log ud</a>
        <a href="/project/FrontController?command=showresponses"> <i class="fas fa-clipboard-list"></i>    Vis tilbud</a>
        <% }%>
    </div>
</div>
<script type="text/javascript">
    var a = 0;
    function displayNav() {
        var smallnav = document.getElementById("smallnav");
        if (a === 0) {
            smallnav.classList.remove("d-none");
            a = 1;
        } else if (a === 1) {
            smallnav.classList.add("d-none");
            a = 0;
        }
    }
    function removeDiv() {
        var div = document.getElementById("errordiv");
        div.classList.add("d-none");
    }
</script>