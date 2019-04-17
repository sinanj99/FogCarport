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
                <!--Opdaterede til onchange, s� hver gang der bliver valgt en ny option vil wantShed blive k�rt-->
                <select onchange="wantShed()" id="shedChoice" class="inputbig">
                    <option value="n/a">V�lg</option>
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
            // Returner en liste med alle de elementer som indeholder classen d-none. 
            //Vi bruger den til at sikre at vi kun kan tilf�je d-none �n gang p� klasse shedDimension
            var isDisabled = document.getElementsByClassName('d-none');

            if (shedChoice == 2 && isDisabled.length == 0){
               shedDimensions.classList.add("d-none");
            }else if(shedChoice == 1 && isDisabled.length > 0){
                shedDimensions.classList.remove("d-none");
            }
        }
    </script>