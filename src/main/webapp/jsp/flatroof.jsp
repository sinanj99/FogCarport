<%@page import="java.util.List"%>
<jsp:include page='/include/sitehead.jsp'></jsp:include>
<jsp:include page='/include/sitemenu.jsp'></jsp:include>
    <body class="background2">
    <%List<String> roofs = (List<String>) session.getAttribute("rooflist"); %>
    <div class="container">
        <div class="standarddiv">
            <form class="d-flex flex-column justify-content-center align-items-center">
                <p> <b>Udfyld nedenst�ende omhyggeligt og klik p� "Bestil tilbud"</b><br>
                    Felter markeret * SKAL udfyldes! </p>
                Carport bredde
                <select class="inputbig">
                    <% for (int i = 240; i <= 750; i += 30) {%>
                    <option><%=i%></option>
                    <%}%>
                </select>
                Carport l�ngde
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
                <select onclick="myFuntion()" id="shedChoice" class="inputbig">
                    <option onclick="myFuntion()" value="1">Ja</option>
                    <option onclick="myFuntion()" value="2">Nej</option>
                </select>
                <select id="shedDimensions" class="inputbig">
                    <option>nigga</option>
                </select>
            </form>
        </div>
    </div>
    <script>
        function myFunction(){
            var shedChoice = document.getElementById("shedChoice");
            var isShed = shedChoice.options[shedChoice.selectedIndex].value;
            if (isShed === 2)
            {
                document.getElementById("shedDimensions").style.display = "none";
            }
        }
    </script>