<%@page import="Data.Entity.Response"%>
<%@page import="Data.Entity.User"%>
<%@page import="Data.Entity.ShippingAddress"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="Data.Entity.Request"%>
<%
    User user = (User) request.getSession().getAttribute("user");
    Request r = (Request) request.getAttribute("request");
    ShippingAddress s = (ShippingAddress) request.getAttribute("shippingAddress");
    
    String status = (String) request.getAttribute("status");
    
    int productionPrice = (int) request.getAttribute("productionPrice");
    Object updatedPrice = request.getAttribute("updatedPrice");
    int sellPrice = productionPrice * 12;
    if(updatedPrice != null) sellPrice = (int) request.getAttribute("updatedPrice");
    
    if(status.equals("offersend")){
        Response rp = (Response) request.getAttribute("response");
        sellPrice = rp.getSellPrice();
    }
    
    String stringDate = r.getDatePlaced().substring(0, 10);
    SimpleDateFormat formatter = new SimpleDateFormat("y-MM-d");
    Date date = formatter.parse(stringDate);   
    String date_ = new SimpleDateFormat("d-MM-y").format(date);
    
    int reqId = r.getReq_id();
    int userId = r.getUser_id();
    int empId = user.getId();
    int carportId = r.getCarport().getCarportId();
    int shedId = r.getCarport().getShed_().getShedId();
    
%>

<jsp:include page='/include/sitehead.jsp'></jsp:include>
    <body class="background2">
    <jsp:include page='/include/sitemenu.jsp'></jsp:include>
    <div class="container">
        <div class="standarddiv" style="padding-bottom: 5px;">
            <div class="row">

                <div class="col-md-4">

                    <div class="w-100" style="border-radius: 2px; border: 1px solid #dddddd; padding: 10px;">
                        <table class="table table-borderless" style="margin-bottom: 0; line-height: 40%;">
                            <tbody>

                                <tr>
                                    <th>Ordrenr.:</th>
                                    <td><b>#</b><%= r.getReq_id() %></td>
                                </tr>

                                <tr>
                                    <th>Dato:</th>
                                    <td><%= date_ %></td>
                                </tr>

                                <tr>
                                    <th>Kunde:</th>
                                    <td><%= s.getFirstname() + " " + s.getLastname() %></td>
                                </tr>
                                <tr>
                                    <th>Adresse:</th>
                                    <td><%= s.getAddress() %></td>
                                </tr>
                                <tr>
                                    <th></th>
                                    <td><%= s.getZipcode() %>, <%= s.getCity()%></td>
                                </tr>
                            </tbody>
                        </table>
                    </div>

                    <div class="w-100 text-center" style="margin-top: 20px; border-radius: 2px; border: 1px solid #dddddd; padding: 10px;">

                        <div style="padding-bottom: 10px;">
                            <b>Produktionspris: </b><br>
                            <span>kr. <%= productionPrice %></span>
                        </div>
                        
                        <%
                            if(status.equals("offersend")){
                                    out.print("<b>Salgspris:</b><br>"
                                            + sellPrice);
                            }else{
                        %>
                        
                        <form action="FrontController" method="GET">
                            <label class="font-weight-bold">Salgspris:</label><br>
                            <input onChange="updatePriceBtn()" id="sellprice" style="border-radius: 2px;" type="text" name="sellprice" class="form-control text-center " min="1" value="<%= sellPrice %>">
                            <input id="sellpricebtn" style="margin-top: 5px; border-radius: 2px;" type="submit" class="w-100 btn btn-primary" value="Opdater salgspris" disabled>
                            <input type="hidden" name="command" value="updateresponseprice">
                            <input type="hidden" name="requestID" value="<%= r.getReq_id() %>">
                        </form>
                        <%}%>
                    </div>

                </div>

                <div class="col-md-8">

                    <div class="w-100" style="border-radius: 2px; border: 1px solid #dddddd; padding: 10px;">
                        <h6>Carport mål</h6>

                        <div class="row">
                            
                            <div class="col-6 text-center">
                                <div><%= r.getCarport().getWidth() %></div>
                                <div class="font-weight-bolder">Bredde</div>
                            </div>
                            <div class="col-6 text-center">
                                <div><%= r.getCarport().getLength()%></div>
                                <div class="font-weight-bolder">Længde</div>
                            </div>

                        </div>
                    </div>
                    
                    <%
                       if(r.getCarport().getShed_() != null){             
                    %>
                    <div class="w-100" style="margin-top: 20px; border-radius: 2px; border: 1px solid #dddddd; padding: 10px;">
                        <h6>Redskabsrum mål</h6>
                        <div class="row">
                            
                            <div class="col-6 text-center">
                                <div><%= r.getCarport().getShed_().getWidth()%></div>
                                <div class="font-weight-bolder">Bredde</div>
                            </div>
                            <div class="col-6 text-center">
                                <div><%= r.getCarport().getShed_().getLength()%></div>
                                <div class="font-weight-bolder">Længde</div>
                            </div>

                        </div>
                    </div>
                    <%}%>

                    <div class="col-12" style="margin-top: 20px;">
                        <div class="row" >

                            <%
                            if(status.equals("offersend")){
                            %>
                            <div class="col-12" style="padding: 0px 15px;">
                                <div class="alert alert-warning" role="alert">
                                    <h4 class="alert-heading">Et tilbud er blevet sendt til kunden</h4>
                                    <p style="padding-left: 0;">
                                        Vi venter på at kunden vender tilbage!
                                    </p>  
                                </div>
                            </div>
                            <%
                            }else{
                            %>
                            <div class="col-6">
                                <a class="btn btn-danger w-100" style="border-radius: 2px;" href="#" role="button">Slet forespørgelse</a>
                            </div>

                            <div class="col-6">
                                <form action="FrontController" method="GET" class="w-100">
                                    <input type="hidden" name="command" value="insertresponse"> 
                                    <input type="hidden" name="requestID" value="<%= reqId %>">
                                    <input type="hidden" name="userID" value="<%= userId %>">
                                    <input type="hidden" name="empID" value="<%= empId %>">
                                    <input type="hidden" name="carportID" value="<%= carportId %>">
                                    <input type="hidden" name="shedID" value="<%= shedId %>">
                                    <input type="hidden" name="productionprice" value="<%= productionPrice %>">
                                    <input type="hidden" name="sellprice" value="<%= sellPrice %> ">
                                    <input type="submit" class="btn btn-primary w-100" style="border-radius: 2px;" value="Send tilbud">
                                </form>
                            </div>
                            <%}%>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <script type="text/javascript">
        var sellPrice = document.getElementById("sellprice").value;
        updatePriceBtn();
        
        function updatePriceBtn() {
            if(document.getElementById("sellprice").value === sellPrice){
                document.getElementById("sellpricebtn").disabled = true;
            }else{
                document.getElementById("sellpricebtn").disabled = false;
            }
        }
    </script>
<jsp:include page='/include/sitefoot.jsp'></jsp:include>