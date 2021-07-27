package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.UserDao;
import com.entities.User;
import com.helper.ConnectionProvider;

@WebServlet("/Register")
public class Register extends HttpServlet {
	
	
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
	
	private void doprocess(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException{
		
		resp.setContentType("text/html");
		
        try(PrintWriter out = resp.getWriter()){
          
      
//            fetch all form data
            String check = req.getParameter("check");
            if (check == null) {
                out.println("Accept The Terms And Conditions");
            } else {
               
                String name = req.getParameter("user_name");
                String email = req.getParameter("user_email");
                String password = req.getParameter("user_password");
                String gender = req.getParameter("gender");
                String about = req.getParameter("about");
                
                //create user object and set all data to that object..
                
               User user=new User(name,email,password,gender,about);

                //create a user dao object
               
                UserDao dao = new UserDao(ConnectionProvider.getConnection());
                if (dao.saveUser(user)) {
//                save..
                    out.println("done");
                    resp.sendRedirect("index.jsp");
                } else {
                    out.println("error");
                }
            }
         
        }
	
		
		
	}
}
