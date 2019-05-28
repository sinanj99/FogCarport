<%-- 
    Document   : prebuiltCarport
    Created on : 29-04-2019, 10:52:53
    Author     : Kasper Jeppesen
--%>

<%@page import="Data.Entity.PrebuiltCarport"%>
<%@page import="java.util.ArrayList"%>
<jsp:include page='/include/sitehead.jsp'></jsp:include>
<jsp:include page='/include/sitemenu.jsp'></jsp:include>
<link rel="stylesheet" href="/project/cssprebuilt.css">
  <body class="background1">
    <%
        if(request.getAttribute("access") == null) response.sendRedirect("FrontController?command=prebuiltcarport");
        
        ArrayList<PrebuiltCarport> prebuiltCarports = (ArrayList<PrebuiltCarport>) request.getAttribute("prebuiltcarport");
        int numberOfButton = 1;
    %>
    <div class="custom-container">
          <div class="row">
              <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 col-xl-12 ">
                  <br/>
                  
                  <div class="card-deck ">
                      
                      <% 
                          
                          for(int i = 0; i < 5; i++)
                          {
                              if(i < prebuiltCarports.size())
                              {
                      %>
                                <div class="card text-white bg-secondary border-info  " style="max-width: 319px; ">
                                    <form method="get" action="FrontController">
                                    <img src=<%= prebuiltCarports.get(i).getImgPpath()  %> class="card-img-top" alt="Card image"  />

                                      <div class="card-body ">

                                          <h4 class="card-subtitle"  >Mål på carporten: </h4>

                                          <ul class="card-list" >
                                              <li >Bredde: <%= prebuiltCarports.get(i).getCarportWidth() %>cm
                                              <li>Længde: <%= prebuiltCarports.get(i).getCarportLength()%>cm
                                         </ul>

                                          <%
                                              if(prebuiltCarports.get(i).getShed() != null)
                                              {
                                          %>
                                                <h4 class="card-subtitle" > Mål på skuret: </h4>

                                                <ul class="card-list" >
                                                     <li >Bredde: <%= prebuiltCarports.get(i).getShed().getWidth()%>cm
                                                     <li>Længde: <%= prebuiltCarports.get(i).getShed().getLength()%>cm
                                                </ul>
                                          <% 
                                                }
                                                else
                                                {
                                          %>
                                                <h4 class="card-subtitle" > Skur medfølger ikke </h4>
                                          <%
                                                }
                                          %>
                                         <br>
                                         <p class="card-text price ">pr. stk <span class="blue"><%= prebuiltCarports.get(i).getPrice()  %>,-</span></p>
                                                
                                         <input class="btn btn-success"  type="submit" value="læg i indkøbskurv" name=<%=numberOfButton %> >
                                         <% numberOfButton++; %>
                                         <input type="hidden" name="command" value="shop">
                                      </div>
                                    </form>

                                 </div>  
                        <%
                               }
                            }
                        %>
                  </div>
              </div>
              
               <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 col-xl-12">
                  <br/>
                  
                  <div class="card-deck">
                      
                      <% 
                          for(int i = 5; i < 10; i++)
                          {
                              if(i < prebuiltCarports.size())
                              {
                      %>
                                <div class="card text-white bg-secondary border-info  " style="max-width: 319px; ">
                                    <form method="get" action="FrontController">
                                    <img src=<%= prebuiltCarports.get(i).getImgPpath()  %> class="card-img-top" alt="Card image"  />

                                      <div class="card-body ">

                                          <h4 class="card-subtitle"  >Mål på carporten: </h4>

                                          <ul class="card-list" >
                                              <li >Bredde: <%= prebuiltCarports.get(i).getCarportWidth() %>cm
                                              <li>Længde: <%= prebuiltCarports.get(i).getCarportLength()%>cm
                                         </ul>

                                          <%
                                              if(prebuiltCarports.get(i).getShed() != null)
                                              {
                                          %>
                                                <h4 class="card-subtitle" > Mål på skuret: </h4>

                                                <ul class="card-list" >
                                                    <li >Bredde: <%= prebuiltCarports.get(i).getShed().getWidth()%>cm
                                                    <li>Længde: <%= prebuiltCarports.get(i).getShed().getLength()%>cm
                                                </ul>
                                          <% 
                                                }
                                                else
                                                {
                                          %>
                                                <h4 class="card-subtitle" > Skur medfølger ikke </h4>
                                          <%
                                                }
                                          %>
                                         <br>
                                         <p class="card-text price ">pr. stk <span class="blue"><%= prebuiltCarports.get(i).getPrice()  %>,-</span></p>

                                           <input class="btn btn-success"  type="submit" value="læg i indkøbskurv" name=<%=numberOfButton %> >
                                         <% numberOfButton++; %>
                                         <input type="hidden" name="command" value="shop">
                                      </div>
                                    </form>  
                                 </div>  
                        <%
                               }
                            }
                        %>
                  </div>
              </div>
                  
               <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 col-xl-12">
                  <br/>
                  
                  <div class="card-deck">
                      
                      <% 
                          for(int i = 10; i < 15; i++)
                          {
                              if(i < prebuiltCarports.size())
                              {
                      %>
                                <div class="card text-white bg-secondary border-info  " style="max-width: 319px; ">
                                    <form method="get" action="FrontController">
                                    <img src=<%= prebuiltCarports.get(i).getImgPpath()  %> class="card-img-top" alt="Card image"  />

                                      <div class="card-body ">

                                          <h4 class="card-subtitle"  >Mål på carporten: </h4>

                                          <ul class="card-list" >
                                              <li >Bredde: <%= prebuiltCarports.get(i).getCarportWidth() %>cm
                                              <li>Længde: <%= prebuiltCarports.get(i).getCarportLength()%>cm
                                         </ul>

                                          <%
                                              if(prebuiltCarports.get(i).getShed() != null)
                                              {
                                          %>
                                                <h4 class="card-subtitle" > Mål på skuret: </h4>

                                                <ul class="card-list" >
                                                    <li >Bredde: <%= prebuiltCarports.get(i).getShed().getWidth()%>cm
                                                    <li>Længde: <%= prebuiltCarports.get(i).getShed().getLength()%>cm
                                                </ul>
                                          <% 
                                                }
                                                else
                                                {
                                          %>
                                                <h4 class="card-subtitle" > Skur medfølger ikke </h4>
                                          <%
                                                }
                                          %>
                                         <br>
                                         <p class="card-text price ">pr. stk <span class="blue"><%= prebuiltCarports.get(i).getPrice()  %>,-</span></p>

                                         <input class="btn btn-success"  type="submit" value="læg i indkøbskurv" name=<%=numberOfButton %> >
                                         <% numberOfButton++; %>
                                         <input type="hidden" name="command" value="shop">
                                      </div>
                                     </form>  
                                 </div>  
                        <%
                               }
                            }
                        %>
                  </div>
              </div>   
          </div>
      </div>
  </body>
</html>




