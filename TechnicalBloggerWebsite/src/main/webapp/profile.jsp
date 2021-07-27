<%@page import="com.entities.Category"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.helper.ConnectionProvider"%>
<%@page import="com.dao.PostDao"%>
<%@page import="com.entities.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%

User user= (User)session.getAttribute("currentUser");
if(user==null){
	
	response.sendRedirect("login.jsp");
}


%>  
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Profile</title>

<!-- css -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
 <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
 <link href="css/mystyle.css" rel="stylesheet" type="text/css"/> 

</head>
<body>

<!-- Navbar -->
<nav class="navbar navbar-expand-lg navbar-dark bg-dark primary-background">
  <a class="navbar-brand" href="#"> <span class="fa fa-desktop"></span>  TechnicalBlogger</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>

  <div class="collapse navbar-collapse" id="navbarSupportedContent">
    <ul class="navbar-nav mr-auto">
      <li class="nav-item active">
        <a class="nav-link" href="#"> <span class="fa fa-bell-o"></span> LearnCoding <span class="sr-only">(current)</span></a>
      </li>
  
      <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
         <span class="fa fa-folder-o"></span> Categories
        </a>
        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
          <a class="dropdown-item" href="#">Programming Language</a>
          <a class="dropdown-item" href="#">Project Development</a>
          <div class="dropdown-divider"></div>
          <a class="dropdown-item" href="#">Data Structure</a>
        </div>
      </li>
      
          <li class="nav-item">
        <a class="nav-link" href="#"><span class="	fa fa-phone"></span>  Contact</a>
      </li>
      
        <li class="nav-item">
        <a class="nav-link" href="#" data-toggle="modal" data-target="#category-modal"><span class="fa fa-folder-open"></span>  Add Category</a>
      </li>
      
      
       <li class="nav-item">
        <a class="nav-link" href="#" data-toggle="modal" data-target="#add-post-modal"><span class="	fa fa-comment"></span> Add Post</a>
      </li>
           
    </ul>
    
    <ul class="navbar-nav mr-right">
  
   <li class="nav-item">
        <a class="nav-link" href="#" data-toggle="modal" data-target="#profile-modal" ><span class="fa fa-user-circle"></span> <%=user.getName() %></a>
      </li>
  
    <li class="nav-item">
        <a class="nav-link" href="Logout"><span class="	fa fa-user-plus"></span> Logout</a>
      </li>
     
  
    </ul>
    
  </div>
</nav>


 <!--main body of the page-->
 <main>
 
   <div class="container">
  
         <div class="row mt-4" style="left: 5px;">
         <div class=""col-md-4">
          <!-- Categories -->
                 <div class="list-group">
  <a href="#" class="list-group-item list-group-item-action active">
    All Posts
  </a>
  <!-- Categories -->
   
                <%            
                PostDao d = new PostDao(ConnectionProvider.getConnection());
                                
                ArrayList<Category> list1 = d.getAllCategories();
                                
                for (Category cc : list1) {

                      %>
             <a href="#" class="list-group-item list-group-item-action"><%=cc.getName() %></a>


             <%               }

                            %>

</div>
          </div> 
  
         <div class=""col-md-8">
            <!-- posts -->
             <div class="container text-center" id="loader">
                            <i class="fa fa-refresh fa-4x fa-spin"></i>
                            <h3 class="mt-2">Loading...</h3>
                        </div>
                        
                        <div class="container-fluid" id="post-container">

                        </div>
             
 
           </div> 
 
 
 </div>
 
 </div>
 </main>


<!-- Profile Modal -->

<!-- Modal -->
<div class="modal fade" id="profile-modal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header secondary-background text-white text-center" >
        <h5 class="modal-title" id="exampleModalLabel">TechnicalBlogger</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
      
      <div class="container text-center" class="img-fluid" style="border-radius:20%; width: 300px ">  
                
         <img src="pics/<%=user.getProfile() %>" style="height: 100px;width:100px;">   
         <br>    
         <h5 class="modal-title mt-3" id="exampleModalLabel"><%=user.getName() %></h5>
         <br>
         
         <!-- Details -->
                       <div id="profile-details">
                        <table class="table" width="300px" height="auto">

                                    <tbody>
                                        <tr>
                                            <th scope="row"> ID</th>
                                            <td> <%= user.getId()%></td>

                                        </tr>
                                        <tr>
                                            <th scope="row"> Email</th>
                                            <td><%= user.getEmail()%></td>

                                        </tr>
                                        <tr>
                                            <th scope="row">Gender</th>
                                            <td><%= user.getGender()%></td>

                                        </tr>
                                        <tr>
                                            <th scope="row">Status</th>
                                            <td><%= user.getAbout()%></td>

                                        </tr>
                                    
                                    </tbody>
                                </table>
                                </div>
         
      
      </div>
      
      <!-- Profile edit -->
      <div id="profile-edit" style="display:none; text-align: center">
      <h3 class="mt-1">Please edit it carefully</h3>
     
         <form action="Edit" method="post" enctype="multipart/form-data">
                                    <table class="table">
                                        <tr>
                                            <td>ID</td>
                                            <td><%= user.getId()%></td>
                                        </tr>
                                        <tr>
                                            <td>Email</td>
                                            <td> <input type="email" class="form-control" name="user_email" value="<%= user.getEmail()%>" > </td>
                                        </tr>
                                        <tr>
                                            <td>Name</td>
                                            <td> <input type="text" class="form-control" name="user_name" value="<%= user.getName()%>" > </td>
                                        </tr>
                                        <tr>
                                            <td>Password</td>
                                            <td> <input type="password" class="form-control" name="user_password" value="<%= user.getPassword()%>" > </td>
                                        </tr>
                                        <tr>
                                            <td>Gender</td>
                                            <td> <%= user.getGender().toUpperCase()%> </td>
                                        </tr>
                                        <tr>
                                            <td>About  :</td>
                                            <td>
                                                <textarea rows="3" class="form-control" name="user_about" ><%= user.getAbout()%>
                                                </textarea>

                                            </td>
                                        </tr>
                                        <tr>
                                            <td>New Profile</td>
                                            <td>
                                                <input type="file" name="image" class="form-control" >
                                            </td>
                                        </tr>

                                    </table>

                                    <div class="container">
                                        <button type="submit" class="btn btn-success">Save</button>
                                    </div>

                                </form>    
         
     
      </div>
      
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
        <button id="edit-profile-button" type="button" class="btn btn-primary">Edit</button>
      </div>
    </div>
  </div>
</div>



<!-- Add Category -->


<!-- Modal -->
<div class="modal fade" id="category-modal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">Enter The Category Details</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
      
      
        <form action="AddCategory" method="post">
        
         <div class="form-group">
         <input name="cname" type="text" placeholder="Enter  the name of the  category " class="form-control"/>
           </div>
           
            <div class="form-group">
         <input name="cdescription" type="text" placeholder="Enter  the description" class="form-control"/>
         
           </div>
          
          <div class="container text-center">
                                <button type="submit" class="btn btn-success">Save</button>
                            </div>
        
        
        
        </form>
        
        
        
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
       
      </div>
    </div>
  </div>
</div>





<!-- Add Post Modal -->


<!-- Modal -->

<div class="modal fade" id="add-post-modal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">Enter The Post Details...</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
      
        <form  id="add-post-form" action="AddPost" method="post" enctype="multipart/form-data">
        
        
                            <div class="form-group">
                                <select class="form-control" name="cid">
                                    <option selected disabled>---Select Category---</option>
                                <%
                                
                                PostDao postd =new PostDao(ConnectionProvider.getConnection());
                             ArrayList<Category> list= postd.getAllCategories();
                             for(Category c:list){
                                
                                %>   
                                <option value="<%=c.getCid() %>"><%=c.getName() %></option>
                                
                                <%
                             }
                                %>
                                
                                </select>
                            </div>
       
                            <div class="form-group">
                                <input name="pTitle" type="text" placeholder="Enter post Title" class="form-control"/>
                            </div>

                            <div class="form-group">
                                <textarea name="pContent" class="form-control" style="height: 200px;" placeholder="Enter your content"></textarea>
                            </div>
                            <div class="form-group">
                                <textarea name="pCode" class="form-control" style="height: 200px;" placeholder="Enter your program (if any)"></textarea>
                            </div>
                            <div class="form-group">
                                <label>Select your image for the post</label>
                                <br>
                                <input type="file" name="pPic"  >
                            </div>

                            <div class="container text-center">
                                <button type="submit" class="btn btn-success">Post </button>
                            </div>

       
        
        </form>
        
        
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary">Edit</button>
      </div>
    </div>
  </div>
</div>



<!-- Javascript -->

<script
            src="https://code.jquery.com/jquery-3.4.1.min.js"
            integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="
        crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/2.1.2/sweetalert.min.js"></script>

<script type="text/javascript" src="js/myjs.js"></script>

<script>

<!-- Profile details -->

   $(document).ready(function(){
	   
	   let editStatus=false;
	   $('#edit-profile-button').click(function (){
		   
	     if(editStatus==false)
	    	 {
	    	 
	    	 $('#profile-details').hide();
	    	 
	    	 $('#profile-edit').show();
	    	
	    	 editStatus= true;
	    	 
	    	 $(this).text("Back");
	    	  
	    	 }
	     else
	    	 {
	    	 
             $('#profile-details').show();
	    	 
	    	 $('#profile-edit').hide();
	    	
	    	 editStatus=false;
	    	 
	    	 $(this).text("Back");
	    	 
	    	 }
		   
		   
	   })
	   
   })
   
   </script>
   
<!--Add post js -->

   <script>
/*
$(document).ready(function(){
	
	$("#add-post-form").on("submit",function(event){
		
		// This will be called when form will be submitted
		
		event.preventDefault();
		console.log("you have ")
		
		let form= new FormData(this);
		
		//now requesting server
		
		$ajax({
			
			url:"AddPost",
			type:'POST',
			data:form,
			success: function (data, textStatus, jqXHR){
				//success	
				console.log(data);
                            if (data.trim() == 'done')
                            {
                                swal("Good job!", "saved successfully", "success");
                            } else
                            {
                                swal("Error!!", "Something went wrong try again...", "error");
                            }
				
			},
			
			error: function (jqXHR, textStatus, errorThrown) {
                //error..
				 swal("Error!!", "Something went wrong try again...", "error");
                
            },
            
            processData: false,
            contentType: false
			
		});
	});
});

 */
 
</script>



<!-- Loading Posts using AJAx -->

<script>
$(document).ready(function(){
                   
	            $.ajax({
	            	
	          url:"load_posts.jsp",
	          success: function (data, textStatus, jqXHR){
	        	  
	        	  console.log(data);
	        	  $("#loader").hide();
	        	  $('#post-container').html(data)
	          }
	            	
	            });
});
</script>



</body>
</html>