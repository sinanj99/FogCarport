<%@page import="java.util.List"%>
<jsp:include page='/include/sitehead.jsp'></jsp:include>
<jsp:include page='/include/sitemenu.jsp'></jsp:include>
    <body class="background2">
    <%List<String> roofs = (List<String>) session.getAttribute("rooflist"); %>
    <div class="container">
        <div class="standarddiv">
            <form class="d-flex flex-column justify-content-center align-items-center">
                <p> <b>Udfyld nedenstående omhyggeligt og klik på "Bestil tilbud"</b><br>
                    Felter markeret * SKAL udfyldes! </p>
                Carport bredde
                <select class="inputbig">
                    <% for (int i = 240; i <= 750; i += 30) {%>
                    <option><%=i%></option>
                    <%}%>
                </select>
                Carport længde
                <select class="inputbig">
                    <% for (int i = 240; i <= 750; i += 30) {%>
                    <option><%=i%></option>
                    <%}%>
                </select>
                Tagtype
                <select class="inputbig">
                    <% /* for (String r : roofs) */ {%>
                    <option><%/*=r*/%></option>
                    <%}%>
                </select>
                Redskabsrum?
                <select onchange="wantShed()" id="shedChoice" class="inputbig">
                    <option value="n/a">Vælg</option>
                    <option value="1">Ja</option>
                    <option value="2">Nej</option>
                </select>
                <select id="shedDimensions" class="inputbig">
                    <option>nigga</option>
                </select>
            </form>
        </div>
    </div>
    <script>
        function wantShed(){
            var shedChoice = document.getElementById("shedChoice").value;
            var shedDimensions =  document.getElementById("shedDimensions");
            if (shedChoice == 2){
               shedDimensions.classList.add("d-none");
            }else if(shedChoice == 1){
                shedDimensions.classList.remove("d-none");
            }
        }
    </script>