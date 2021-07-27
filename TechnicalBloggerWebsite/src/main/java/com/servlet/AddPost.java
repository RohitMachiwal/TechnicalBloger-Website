package com.servlet;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.dao.PostDao;
import com.entities.User;
import com.entities.posts;
import com.helper.ConnectionProvider;
import com.helper.DeleteImageFile;
import com.mysql.cj.Session;

@WebServlet("/AddPost")

@MultipartConfig
public class AddPost extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doprocess(req, resp);
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doprocess(req, resp);
	}
	
	
	private void doprocess(HttpServletRequest req, HttpServletResponse resp) throws ServletException,IOException{
		
		int cid=Integer.parseInt(req.getParameter("cid"));
		String pTitle=req.getParameter("pTitle");
		String pContent=req.getParameter("pContent");
		String pCode= req.getParameter("pCode");
		Part part=req.getPart("pPic");
		// gettng userid
		
		HttpSession s=req.getSession();
		
		User user=(User)s.getAttribute("currentUser");
		posts p= new posts(pTitle,pContent,pCode,part.getSubmittedFileName(),cid,user.getId());
		
		PostDao pd=new PostDao(ConnectionProvider.getConnection());
		
		if(pd.savePost(p)) {
			
			String path= req.getRealPath("/")+"Blog_pics"+File.separator+part.getSubmittedFileName();
			DeleteImageFile.saveFile(part.getInputStream(), path);
			
			resp.getWriter().println("Post Saved Successfully");
			
		}
		
		else {
			
			resp.getWriter().println("Adding Post Failed!!!");
		}
		
		
		resp.sendRedirect("profile.jsp");		
		
	}

}
