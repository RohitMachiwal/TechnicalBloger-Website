<%@page import="java.util.List"%>
<%@page import="com.helper.ConnectionProvider"%>
<%@page import="com.dao.PostDao"%>
<%@page import="com.entities.posts"%>
<div class="row">
<% 

PostDao d=new PostDao(ConnectionProvider.getConnection());
 

List<posts> list= d.getAllPosts();

for(posts p:list){
%>
<div class="col-md-6 mt-2">

   <div class="card" style="width:18em;text-align: center;">
   <img class="card-img-top" src="Blog_pics/<%=p.getpPic() %>" alt="Card image cap">
   <div class="card=body">
   
   <b><%=p.getpTitle() %></b>
   <p class="card-text"><%=p.getpContent() %></p>
   <pre><%=p.getpCode() %></pre>
   
   </div>
   </div>

</div>

	
<%	
	
     }

%>
</div>