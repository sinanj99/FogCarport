<%@page import="java.util.List"%>
<jsp:include page='/include/sitehead.jsp'></jsp:include>
<jsp:include page='/include/sitemenu.jsp'></jsp:include>
    <body class="background2">
    <%List<String> roofs = (List<String>) session.getAttribute("rooflist");
        int a;
        int b; %>

    <div class="container">
        <div class="standarddiv">
            <form class="d-flex flex-column justify-content-center align-items-center">
                <p> <b>Udfyld nedenstående omhyggeligt og klik på "Bestil tilbud"</b><br>
                    Felter markeret * SKAL udfyldes! </p>
                Carport bredde
                <select class="inputbig" id="carportWidth" onchange="widthSubtract30">
                    <option value="n/a">Vælg</option>
                    <% b = 210;
                        for (int i = 0; i < 18; i += 1) {
                            b += 30;%>
                    <option id="widthOption<%=i%>"><%=b%></option>
                    <%}%>
                </select>
                Carport længde
                <select class="inputbig" id="carportWidth" onchange="lengthSubtract30">
                    <option value="n/a">Vælg</option>
                    <% b = 210;
                        for (int i = 0; i < 18; i += 1) {
                            b += 30;%>
                    <option id="lengthOption<%=i%>"><%=b%></option>
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

                <select id="shedDimensions" class="inputbig d-none">
                    <option value="n/a">Vælg</option>
                    <% a=180;
                        for (int i = 0; i < 18; i += 1) {
                            a += 30;%>
                    <option> <%= a%> </option>
                    <% }%>
                </select>

                <select id="shedDimensions2" class="inputbig d-none">
                    <option value="n/a">Vælg</option>
                    <% a=180;
                        for (int i = 0; i < 18; i += 1) {
                            a += 30;%>
                    <option> <%= a%> </option>
                    <% }%>
                </select>
            </form>
        </div>
    </div>
    <script>
        function wantShed() {
            var shedChoice = document.getElementById("shedChoice").value;
            var shedDimensions = document.getElementById("shedDimensions");
            var shedDimensions2 = document.getElementById("shedDimensions2");
            if (shedChoice == 1) {
                shedDimensions.classList.remove("d-none");
                shedDimensions2.classList.remove("d-none");
            } else if (shedChoice == 2) {
                shedDimensions.classList.add("d-none");
                shedDimensions2.classList.add("d-none");
            }
        }
        function widthSubtract30()
        {
            var chosenWidth = document.getElementById("carportWidth").value;
            for (i = chosenWidth - 1; i <= 18; i++)
            {
                document.getElementById("widthOption" + i).classList.add("d-none");
            }

        }
        function lengthSubtract30()
        {
            var chosenLength = document.getElementById("carportLength").value;
            for (i = chosenLength - 1; i <= 18; i++)
            {
                document.getElementById("lengthOption" + i).classList.add("d-none");
            }

        }
    </script>