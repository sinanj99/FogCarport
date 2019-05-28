<%@page import="Data.Entity.User"%>
<%@page import="Data.Entity.Response"%>
<%@page import="java.util.List"%>
<%
    if(request.getAttribute("access") == null)  response.sendRedirect("FrontController?command=showresponses");
    User user = (User) session.getAttribute("user");
    if(user == null) {
        response.sendRedirect("jsp/login.jsp");
    }else if(user != null && (user.isAdmin() || user.isSeller())){
        request.getRequestDispatcher("/FrontController?command=frontpageredirect").forward(request, response);
    }
    List<Response> responses = (List) request.getAttribute("responses");
%>
<jsp:include page='/include/sitehead.jsp'></jsp:include>
    <body class="background2">
    <jsp:include page='/include/sitemenu.jsp'></jsp:include>
        <div class="container d-flex flex-column justify-content-center">
            <div class="standarddiv">
                <div class="row">
                    <div class="col-md-12">
                        <h1>Dine tilbud</h1>
                        
                        <%
                        if(responses == null || responses.size() == 0){
                            out.print("<div class=\"w-100 text-center\">Du har ikke fået nogle tilbud endnu</div>");
                        }else{
                            
                        %>
                        <table class="table showorders-table">
                            <thead class="showorders-thead">
                                <tr>
                                    <th class="showorders-th" scope="col">Dato</th>
                                    <th class="showorders-th" scope="col">Pris</th>
                                    <th class="showorders-th" scope="col">Status</th>
                                </tr>
                            </thead>
                            <tbody>
                            <%
                                for(Response r : responses){
                            %>
                                <tr class="showorders-tr">
                                    <td><%= r.getDatePlaced()%></td>
                                    <td><%= r.getSellPrice()%></td>                                
                                    <td>
                                        <%
                                            if(r.getStatus() == 1){
                                        %>
                                            <span class="badge badge-primary">Tilbud modtaget</span>
                                        <% }else{%>
                                            <span class="badge badge-secondary">Afventer tilbud</span>
                                        <%}%>
                                    </td>

                                    <td class="text-right">
                                        <a class="btn btn-primary" href="FrontController?command=showresponse&requestID=<%= r.getRequest().getRequestId() %>" role="button" style="border-radius: 2px; font-weight: 600; font-size: .8rem; padding: 0px 5px;">SE TILBUD</a>
                                    </td>
                                </tr>
                            <%}%>
                            </tbody>
                        </table>
                        <%}%>
                    </div>
                </div>
            </div>
        </div>
<jsp:include page='/include/sitefoot.jsp'></jsp:include>
