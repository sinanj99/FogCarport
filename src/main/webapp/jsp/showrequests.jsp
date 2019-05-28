<%@page import="Data.Entity.User"%>
<%@page import="Data.Entity.Request"%>
<%@page import="java.util.List"%>
<%
    if(request.getAttribute("access") == null)  response.sendRedirect("FrontController?command=showrequests");
    User user = (User) session.getAttribute("user");
    if(user == null) {
        response.sendRedirect("jsp/login.jsp");
    }else if(user != null && !user.isSeller()){
        request.getRequestDispatcher("/FrontController?command=frontpageredirect").forward(request, response);
    }
%>
<jsp:include page='/include/sitehead.jsp'></jsp:include>
<jsp:include page='/include/sitemenu.jsp'></jsp:include>

<% List<Request> requests = (List) request.getAttribute("requests");%>
<body class="background2">
    <div class="container d-flex flex-column justify-content-center">
        <div class="standarddiv">
            <div class="row">
                <div class="col-md-12">
                    <h1>Forespørgelser</h1>
                    <table class="table showorders-table">
                        <thead class="showorders-thead">
                            <tr>
                                <th class="showorders-th" scope="col">Ordrenr.:</th>
                                <th class="showorders-th" scope="col">Kunde</th>
                                <th class="showorders-th" scope="col">Dato</th>
                                <th class="showorders-th" scope="col">Type</th>
                                <th class="showorders-th" scope="col">Status</th>
                                <th class="showorders-th" scope="col"></th>
                            </tr>
                        </thead>
                        <tbody>
                            <% for (Request r : requests) {%>
                            <tr class="showorders-tr">
                                <td><%=r.getRequestId()%></td>
                                <td><%=r.getAddress().getFirstname() + " " + r.getAddress().getLastname()%></td>
                                <td><%=r.getDatePlaced()%></td>
                                <td>
                                <%
                                    if (r.getCarport().getInclination() != 0 && r.getCarport().getShed() == null) {
                                        out.println("Carport med rejsning");
                                    } else if (r.getCarport().getInclination() != 0 && r.getCarport().getShed() != null) {
                                        out.println("Carport med rejsning & skur");
                                    } else if (r.getCarport().getInclination() == 0 && r.getCarport().getShed() != null) {
                                        out.println("Carport uden rejsning, men med skur");
                                    }else{
                                        out.println("Carport uden rejsning");
                                    }
                                %>
                                </td>
                                <td>
                                    <span class="badge badge-secondary">Tilbud ej sendt</span>
                                </td>
                                <td>
                                    <a class="btn btn-primary" href="FrontController?command=createoffer&requestID=<%=r.getRequestId()%>" role="button" style="border-radius: 2px; font-weight: 600; font-size: .8rem; padding: 0px 5px;">SE ORDRE</a>
                                </td>
                            </tr>
                            <%}%>
                        </tbody>
                    </table>

                </div>
            </div>
        </div>
    </div>
    <jsp:include page='/include/sitefoot.jsp'></jsp:include>
