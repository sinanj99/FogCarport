<%@page import="java.util.List"%>
<jsp:include page='/include/sitehead.jsp'></jsp:include>
<jsp:include page='/include/sitemenu.jsp'></jsp:include>
<%List<String> roofs = (List<String>) session.getAttribute("rooflist"); %>
<div class="container">
    <div class="standarddiv">
        <form class="d-flex flex-column justify-content-center align-items-center">
            <p> <b>Udfyld nedenstående omhyggeligt og klik på "Bestil tilbud"</b><br>
                Felter markeret * SKAL udfyldes! </p>
            <select class="inputbig">
                <% for (int i = 240; i <= 750; i += 30) {%>
                <option><%=i%></option>
                <%}%>
            </select>
            <select class="inputbig">
                <% for (int i = 240; i <= 750; i += 30) {%>
                <option><%=i%></option>
                <%}%>
            </select>
            <select class="inputbig">
                <% /* for (String r : roofs) */ {%>
                <option><%/*=r*/%></option>
                <%}%>
            </select>
            <select class="inputbig">
                <option>Ja</option>
                <option>Nej</option>
            </select>
        </form>
    </div>
</div>