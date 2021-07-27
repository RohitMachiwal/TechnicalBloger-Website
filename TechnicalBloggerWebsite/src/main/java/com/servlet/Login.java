package com.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.UserDao;
import com.entities.Message;
import com.entities.User;
import com.helper.ConnectionProvider;

@WebServlet("/Login")

public class Login extends HttpServlet {
	
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
		
		String userEmail = req.getParameter("email");
        String userPassword = req.getParameter("password");

        UserDao dao = new UserDao(ConnectionProvider.getConnection());

        User u = dao.getUserByEmailAndPassword(userEmail, userPassword);
        
        if(u==null) {
        	
        	// resp.getWriter().println("Invalid Details Try Again ...");
        	
         Message msg= new Message("Invalid Username and Password","error","alert-danger");
         
          HttpSession s=req.getSession();
          s.setAttribute("msg",msg);
          
          resp.sendRedirect("Login.jsp");
        	
        }
		
        else {
        	
        	HttpSession s=req.getSession();
        	s.setAttribute("currentUser", u);
        	resp.sendRedirect("profile.jsp");
        	
        }
		
	}

}
