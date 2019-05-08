<%@page import="Data.Entity.User"%>
<% User user = (User) session.getAttribute("user"); %>
<div id="nav">
    <a class="right d-md-none" href="#" onclick="displayNav()"><i class="fas fa-bars"></i></a>
        <% if (user == null) {%>
    <a class="left" href="frontpage.jsp">FOG CARPORT</a>
    <a class="right d-none d-md-block" href="register.jsp"> <i class="fas fa-user-plus"></i>    Opret bruger</a>
    <a class="right d-none d-md-block" href="login.jsp"> <i class="fas fa-sign-in-alt"></i>    Log ind</a>
    <% } else if(user != null && user.isSeller()){ %>
    <a class="left" href="sellerfrontpage.jsp">FORSIDE</a>
    <a class="right d-none d-md-block" href="FrontController?command=logout"> <i class="fas fa-sign-out-alt"></i>    Log ud</a>
    <a class="right d-none d-md-block" href="FrontController?command=showrequests"> <i class="fas fa-clipboard-list"></i>    Vis forespørgsler</a>
    <% } else { %>
    <a class="left" href="frontpage.jsp">FOG CARPORT</a>
    <a class="right d-none d-md-block" href="FrontController?command=logout"> <i class="fas fa-sign-out-alt"></i>    Log ud</a>
    <% } %>
</div>
<div id="smallnav" class="d-none d-md-none smallnav">
    <div class="smallnavrow">
        <% if (session.getAttribute("user") == null) {%>
        <a class="" href="register.jsp"> <i class="fas fa-user-plus"></i>    Opret bruger</a><br>
        <hr style="background-color: #dbdeff;">
        <a class="" href="login.jsp"> <i class="fas fa-sign-in-alt"></i>    Log ind</a>
        <% } else { %>
        <a class="" href="FrontController?command=logout"> <i class="fas fa-sign-out-alt"></i>    Log ud</a>
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
</script>