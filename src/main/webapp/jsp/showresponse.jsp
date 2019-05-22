<%@page import="Data.Entity.User"%>
<%@page import="Data.Entity.Shed"%>
<%@page import="Data.Entity.Request"%>
<%@page import="Data.Entity.Response"%>
<%
    Response r = (Response) request.getAttribute("response");
    Request req = (Request) request.getAttribute("request");
    
    User user = (User) session.getAttribute("user");
    if(user == null) {
        response.sendRedirect("jsp/login.jsp");
    }else if(user != null && (user.isAdmin() || user.isSeller())){
        request.getRequestDispatcher("/FrontController?command=frontpageredirect").forward(request, response);
    }
%>
<jsp:include page='/include/sitehead.jsp'></jsp:include>
    <body class="background2">
    <jsp:include page='/include/sitemenu.jsp'></jsp:include>
    <div class="container">
        
            <div class="row d-flex justify-content-center" style="margin-top: 100px;">
                <div class="col-6" style="border: 1px solid #ddd; background-color: #fff; border-radius: 2px; padding: 20px;">
                    
                    <div style="border: 1px solid #ddd; border-radius: 2px 0px; padding: 10px; width: 100%; text-align: center;">
                        <h6>Carport mål</h6>
                        <div class="row">
                            <div class="col text-center">
                                <div><%= req.getCarport().getWidth() %></div>
                                <div class="font-weight-bolder">Bredde</div>
                            </div>

                            <div class="col text-center">
                                <div><%= req.getCarport().getLength()%></div>
                                <div class="font-weight-bolder">Længde</div>
                            </div>
                            
                            <% 
                            if(req.getCarport().getInclination() > 0){  
                            %>    
                            <div class="col text-center">
                                <div><%= req.getCarport().getInclination()%></div>
                                <div class="font-weight-bolder">Hældning</div>
                            </div>
                            <%}%>
                        </div>
                    </div>
                    
                    <% 
                    if(req.getCarport().getShed() != null){
                    %>
                    <div style="border: 1px solid #ddd; border-radius: 0px 2px; padding: 10px; width: 100%; margin-top: -1px; text-align: center;">
                        <h6>Redskabsrum mål</h6>
                        <div class="row">
                            <div class="col text-center">
                                <div><%= req.getCarport().getShed().getWidth() %></div>
                                <div class="font-weight-bolder">Bredde</div>
                            </div>

                            <div class="col text-center">
                                <div><%= req.getCarport().getShed().getLength() %></div>
                                <div class="font-weight-bolder">Længde</div>
                            </div>

                        </div>
                    </div>
                    <%}%>

                    <div style="border: 1px solid #ddd; border-radius: 2px; padding: 10px; width: 100%; margin-top: 10px; text-align: center;">
                        <h6>Tilbud på ønskede carport</h6>

                        <div>
                            <span style="color: #8a8a8a;">Vi kan levere den ønskede carport for kr. <%= r.getSellPrice() %></div>
                            <div class="row" style="margin-top: 10px;">
                                <div class="col" style="padding-right: 5px;">
                                    <a class="btn btn-danger w-100" style="border-radius: 2px;" href="FrontController?command=deleteresponse&responseID=<%= r.getRequestId()%>" role="button">Afvis tilbud</a>
                                </div>

                                <div class="col" style="padding-left: 5px;">
                                    <a class="btn btn-primary w-100" style="border-radius: 2px;" href="#" role="button">Accepter tilbud</a>
                                </div>
                            </div>
                        </div>
                    </div>
                    
                </div>
            </div>
        
        
        
    </div>
<jsp:include page='/include/sitefoot.jsp'></jsp:include>