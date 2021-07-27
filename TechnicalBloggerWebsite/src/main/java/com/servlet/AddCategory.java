package com.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.CategoryDao;
import com.entities.Category;
import com.helper.ConnectionProvider;
@WebServlet("/AddCategory")
public class AddCategory  extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		doprocess(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doprocess(req, resp);
	}
	
	
	private void doprocess(HttpServletRequest req, HttpServletResponse resp) throws ServletException,IOException{
		
		
		String cname=req.getParameter("cname");
		
		String desc=req.getParameter("cdescription");
		
		Category category=new Category(cname,desc);
		
		CategoryDao cd=new CategoryDao(ConnectionProvider.getConnection());
		
		boolean ans= cd.saveCategory(category);
		
		if(ans) {
			
			resp.getWriter().println("Category Successfully Added ");
			
		}
		
		else {
			
			resp.getWriter().println("Failed To Add Category ");
			
		}
		
		resp.sendRedirect("profile.jsp");
		
	}

}
