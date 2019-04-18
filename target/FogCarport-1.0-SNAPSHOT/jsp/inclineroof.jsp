<%@page import="java.util.List"%>
<jsp:include page='/include/sitehead.jsp'></jsp:include>
<jsp:include page='/include/sitemenu.jsp'></jsp:include>
<%List<String> roofs = (List<String>) session.getAttribute("rooflist");
    int a;
    int b; %>
<body class="background2">
    <div class="container">
        <form class="standarddiv pl-0 pr-0 d-flex flex-column justify-content-center">
            <h3>Tilpas din carport</h3>
            <div class="row">
                <div class="col-12 d-flex flex-column align-items-center">
                    <p class="p-0" style="width: 65%;">Carportens bredde</p>
                    <select class="inputbig" id="carportWidth" onchange="widthSubtract30()">
                        <option value="n/a">Vælg</option>
                        <% b = 210;
                            for (int i = 0; i < 18; i += 1) {
                                b += 30;%>
                        <option value="<%=i + 1%>"><%=b%> cm</option>
                        <%}%>
                    </select>
                </div>
            </div>
            <div class="row">
                <div class="col-12 d-flex flex-column align-items-center">
                    <p class="p-0" style="width: 65%;">Carportens længde</p>
                    <select class="inputbig" id="carportLength" onchange="lengthSubtract30()">
                        <option value="n/a">Vælg</option>
                        <% b = 210;
                            for (int i = 0; i < 18; i += 1) {
                                b += 30;%>
                        <option value="<%=i + 1%>"><%=b%> cm</option>
                        <%}%>
                    </select>
                </div>
            </div>
            <div class="row">
                <div class="col-12 d-flex flex-column align-items-center">
                    <p class="p-0" style="width: 65%;">Tagtype</p>
                    <select class="inputbig">
                        <% /* for (String r : roofs) */ {%>
                        <option><%/*=r*/%></option>
                        <%}%>
                    </select>
                </div>
            </div>
            <div class="row">
                <div class="col-12 d-flex flex-column align-items-center">
                    <p class="p-0" style="width: 65%;">Redskabsrum?</p>
                    <select onchange="wantShed()" id="shedChoice" class="inputbig">
                        <option value="n/a">Vælg</option>
                        <option value="1">Ja</option>
                        <option value="2">Nej</option>
                    </select>
                </div>
            </div>
            <div class="row">
                <div class="col-12 d-flex flex-column align-items-center">
                    <p class="p-0" style="width: 65%;" ><label id="shedWidthLabel" style="margin-bottom: 0px;" class="d-none">Redskabsrummets bredde</label></p>
                    <select id="shedWidth" class="inputbig d-none" class="d-none">
                        <option value="n/a">Vælg</option>
                        <% a = 180;
                            for (int i = 0; i < 18; i += 1) {
                                a += 30;%>
                        <option id="widthOption<%=i%>"> <%= a%> cm </option>
                        <% }%>
                    </select>
                </div>
            </div>
            <div class="row">
                <div class="col-12 d-flex flex-column align-items-center">
                    <p class="p-0" style="width: 65%;" ><label id="shedLengthLabel" style="margin-bottom: 0px;" class="d-none">Redskabsrummets Længde</label></p>
                    <select id="shedLength" class="inputbig d-none" class="d-none">
                        <option value="n/a">Vælg</option>
                        <% a = 180;
                            for (int i = 0; i < 18; i += 1) {
                                a += 30;%>
                        <option id="lengthOption<%=i%>"> <%= a%> cm </option>
                        <% }%>
                    </select>
                </div>
            </div>   
        </form>
    </div>

    <script type="text/javascript">
        function wantShed() {
            var shedChoice = document.getElementById("shedChoice").value;
            var shedWidth = document.getElementById("shedWidth");
            var shedLength = document.getElementById("shedLength");
            if (shedChoice == 1) {
                shedWidth.classList.remove("d-none");
                shedLength.classList.remove("d-none");
                document.getElementById("shedWidthLabel").classList.remove("d-none");
                document.getElementById("shedLengthLabel").classList.remove("d-none");
            } else if (shedChoice == 2) {
                shedWidth.classList.add("d-none");
                shedLength.classList.add("d-none");
                document.getElementById("shedWidthLabel").classList.add("d-none");
                document.getElementById("shedLengthLabel").classList.add("d-none");
            }
        }
        function widthSubtract30()
        {

            var shedLengthOptions = document.querySelectorAll("#shedWidth option");
            shedLengthOptions.forEach(shedOption => {
                shedOption.disabled = false;
            });

            var chosenWidth = document.getElementById("carportWidth").value;
            for (i = chosenWidth; i <= 18; i++)
            {
                document.getElementById("widthOption" + i).disabled = true;
            }

        }
        function lengthSubtract30()
        {

            var shedLengthOptions = document.querySelectorAll("#shedLength option");
            shedLengthOptions.forEach(shedOption => {
                shedOption.disabled = false;
            });

            var chosenLength = document.getElementById("carportLength").value;
            for (i = chosenLength; i <= 18; i++)
            {
                document.getElementById("lengthOption" + i).disabled = true;
            }

        }
    </script>