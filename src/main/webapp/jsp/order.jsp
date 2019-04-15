<%@page import="Data.LineItem"%>
<%@page import="Data.BOM"%>
<jsp:include page='/include/sitehead.jsp'></jsp:include>
<jsp:include page='/include/sitemenu2.jsp'></jsp:include>
    <table>
        <thead>
            <tr>Navn</tr>
            <tr>Længde</tr>
            <tr>Antal</tr>
            <tr>Enhed</tr>
            <tr>Pris</tr>
        </thead>
    <% BOM bom = (BOM) request.getAttribute("bom");
        int fullPrice = 0;
        for (LineItem l : bom.getLineitems()) {
            fullPrice += l.getPrice();
    %>
    <tbody>
        <tr><%= l.getMaterial().getName()%></tr>
        <tr><%= l.getMaterial().getLength()%></tr>
        <tr><%= l.getQty()%></tr>
        <tr><%= l.getPrice()%></tr>
    </tbody>
    <% }%>
    <thead>
        <tr>Fuld pris</tr>
    </thead>
    <tbody>
        <tr><%= fullPrice%></tr>
    </tbody>
</table>
<jsp:include page='/include/sitefoot.jsp'></jsp:include>