package com.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.entities.Message;

@WebServlet("/Logout")
public class Logout extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doprocess(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doprocess(req, resp);
	}
	
	
	
	private void doprocess(HttpServletRequest req,HttpServletResponse resp) throws ServletException,IOException{
		
	 resp.setContentType("text/html");

	 HttpSession s=req.getSession();
	 
	 s.removeAttribute("currentUser");
	 
	 Message m= new Message("Logout Successfully","success","alert-success");
	 
	 resp.sendRedirect("Login.jsp");
	 
	 s.setAttribute("msg", m);
		
	}

}
