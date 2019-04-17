<div id="nav">
    <a class="left" href="frontpage.jsp">FOG CARPORT</a>
    <a class="right d-md-none" href="#" onclick="displayNav()"><i class="fas fa-bars"></i></a>
    <a class="right d-none d-md-block" href="register.jsp"> <i class="fas fa-user-plus"></i>    Opret bruger</a>
    <a class="right d-none d-md-block" href="login.jsp"> <i class="fas fa-sign-in-alt"></i>    Log ind</a>
</div>
<div id="smallnav" class="d-none d-md-none smallnav justify-content-center">
    <div class="smallnavrow">
        <a class="" href="register.jsp"> <i class="fas fa-user-plus"></i>    Opret bruger</a><br>
        <hr style="background-color: #dbdeff;">
        <a class="" href="login.jsp"> <i class="fas fa-sign-in-alt"></i>    Log ind</a>
    </div>
</div>
<script type="text/javascript">
    var a = 0;
    function displayNav() {
        if (a === 0) {
            var smallnav = document.getElementById("smallnav");
            smallnav.classList.remove("d-none");
            a = 1;
        } else if (a === 1) {
            smallnav.classList.add("d-none");
            a = 0;
        }
    }
</script>