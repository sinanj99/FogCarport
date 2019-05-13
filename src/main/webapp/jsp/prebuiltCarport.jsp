<%-- 
    Document   : prebuiltCarport
    Created on : 29-04-2019, 10:52:53
    Author     : Kasper Jeppesen
--%>

<%@page import="Data.Entity.PrebuiltCarport"%>
<%@page import="java.util.ArrayList"%>
<jsp:include page='/include/sitehead.jsp'></jsp:include>
<jsp:include page='/include/sitemenu.jsp'></jsp:include>

<!doctype html>
<html lang="en">
  <head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

    <title>Hello, world!</title>
    <style>
        .card-img-top
        {
            height: 30vh;
        }
        .card-text
        {
            maring: 0px;
        }
        .blue
        {
            color: blue;
        }
        .btn
        {
            position: absolute;
            bottom: 0;
            right: 0;
        }
        .price
        {
            position: absolute;
            bottom: 20px;
            right: 0;
        }
        
    </style>
   
    
    
  </head>
  <body class="background1">
    <%
        ArrayList<PrebuiltCarport> prebuiltCarports = (ArrayList<PrebuiltCarport>) request.getAttribute("prebuiltCarport");
        
    %>
    <div class="containter"  >
          <div class="row">
              <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 col-xl-12">
                  <br/>
                  
                  <div class="card-deck ">
                      
                      <% 
                          for(int i = 0; i < 5; i++)
                          {
                              if(i < prebuiltCarports.size())
                              {
                      %>
                                <div class="card text-white bg-secondary border-info  " style="max-width: 319px; ">
                                    <img src=<%= prebuiltCarports.get(i).getImgPpath()  %> class="card-img-top" alt="Card image"  />

                                      <div class="card-body ">

                                          <h4 class="card-subtitle" style="margin-bottom: 2px" >Mål på carporten: </h4>

                                          <ul style="padding-left: 20px">
                                              <li >Bredde: <%= prebuiltCarports.get(i).getCarportWidth() %>cm
                                              <li>Længde: <%= prebuiltCarports.get(i).getCarportLength()%>cm
                                         </ul>

                                          <%
                                              if(prebuiltCarports.get(i).isShed() == true)
                                              {
                                          %>
                                                <h4 class="card-subtitle" style="margin-bottom: 2px"> Mål på skuret: </h4>

                                                <ul style="padding-left: 20px">
                                                     <li >Bredde: <%= prebuiltCarports.get(i).getShedWidth()%>cm
                                                     <li>Længde: <%= prebuiltCarports.get(i).getShedLength()  %>cm
                                                </ul>
                                          <% 
                                                }
                                                else
                                                {
                                          %>
                                                <h4 class="card-subtitle" style="margin-bottom: 2px"> Skur medfølger ikke </h4>
                                          <%
                                                }
                                          %>
                                                
                                                
                                                
                                         <br>
                                         <p class="card-text price ">pr. stk <span class="blue"><%= prebuiltCarports.get(i).getPrice()  %>,-</span></p>

                                          <a href="" class="btn btn-success" >Læg i indkubskurv</a>
                                      </div>

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
                                    <img src=<%= prebuiltCarports.get(i).getImgPpath()  %> class="card-img-top" alt="Card image"  />

                                      <div class="card-body ">

                                          <h4 class="card-subtitle" style="margin-bottom: 2px" >Mål på carporten: </h4>

                                          <ul style="padding-left: 20px">
                                              <li >Bredde: <%= prebuiltCarports.get(i).getCarportWidth() %>cm
                                              <li>Længde: <%= prebuiltCarports.get(i).getCarportLength()%>cm
                                         </ul>

                                          <%
                                              if(prebuiltCarports.get(i).isShed() == true)
                                              {
                                          %>
                                                <h4 class="card-subtitle" style="margin-bottom: 2px"> Mål på skuret: </h4>

                                                <ul style="padding-left: 20px">
                                                     <li >Bredde: <%= prebuiltCarports.get(i).getShedWidth()%>cm
                                                     <li>Længde: <%= prebuiltCarports.get(i).getShedLength()  %>cm
                                                </ul>
                                          <% 
                                                }
                                                else
                                                {
                                          %>
                                                <h4 class="card-subtitle" style="margin-bottom: 2px"> Skur medfølger ikke </h4>
                                          <%
                                                }
                                          %>
                                         <br>
                                         <p class="card-text price ">pr. stk <span class="blue"><%= prebuiltCarports.get(i).getPrice()  %>,-</span></p>

                                          <a href="" class="btn btn-success" >Læg i indkubskurv</a>
                                      </div>

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
                                    <img src=<%= prebuiltCarports.get(i).getImgPpath()  %> class="card-img-top" alt="Card image"  />

                                      <div class="card-body ">

                                          <h4 class="card-subtitle" style="margin-bottom: 2px" >Mål på carporten: </h4>

                                          <ul style="padding-left: 20px">
                                              <li >Bredde: <%= prebuiltCarports.get(i).getCarportWidth() %>cm
                                              <li>Længde: <%= prebuiltCarports.get(i).getCarportLength()%>cm
                                         </ul>

                                          <%
                                              if(prebuiltCarports.get(i).isShed() == true)
                                              {
                                          %>
                                                <h4 class="card-subtitle" style="margin-bottom: 2px"> Mål på skuret: </h4>

                                                <ul style="padding-left: 20px">
                                                     <li >Bredde: <%= prebuiltCarports.get(i).getShedWidth()%>cm
                                                     <li>Længde: <%= prebuiltCarports.get(i).getShedLength()  %>cm
                                                </ul>
                                          <% 
                                                }
                                                else
                                                {
                                          %>
                                                <h4 class="card-subtitle" style="margin-bottom: 2px"> Skur medfølger ikke </h4>
                                          <%
                                                }
                                          %>
                                         <br>
                                         <p class="card-text price ">pr. stk <span class="blue"><%= prebuiltCarports.get(i).getPrice()  %>,-</span></p>

                                          <a href="" class="btn btn-success" >Læg i indkubskurv</a>
                                      </div>

                                 </div>  
                        <%
                               }
                            }
                        %>
                  </div>
              </div>   
          </div>
      </div>
      
      
    

    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
  </body>
</html>




