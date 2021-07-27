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

import com.dao.UserDao;
import com.entities.Message;
import com.entities.User;
import com.helper.ConnectionProvider;
import com.helper.DeleteImageFile;

@MultipartConfig
@WebServlet("/Edit")
public class Edit extends HttpServlet {
	
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
	
	private void doprocess(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
		
		String userEmail=req.getParameter("user_email");
		String userName = req.getParameter("user_name");
        String userPassword = req.getParameter("user_password");
        String userAbout = req.getParameter("user_about");
        Part part = req.getPart("image");
        String imageName=part.getSubmittedFileName();
         
      //get the user from the session...
        HttpSession s = req.getSession();
        User user = (User) s.getAttribute("currentUser");
        
        user.setEmail(userEmail);
        user.setName(userName);
        user.setPassword(userPassword);
        user.setAbout(userAbout);
        String oldFile=user.getProfile();
        
        user.setProfile(imageName);
        
        //User database
        
        UserDao userDao= new UserDao(ConnectionProvider.getConnection());
        
        boolean ans = userDao.updateUser(user);
        
        if(ans) {
        	
        	String path= req.getRealPath("/")+File.separator+user.getProfile();
        	
        	
        	//delete code
        	
        	//Delete path of old file
        	String pathOldFile= req.getRealPath("/")+File.separator+oldFile;
        	
        	//Deletion Of Old File 
        	
        	if(!oldFile.equals("default.png")) {
        		DeleteImageFile.deleteFile(pathOldFile); 
        	}  
        	
        	//New Path of file
        	
        	DeleteImageFile.deleteFile(path);
        		
        		if(DeleteImageFile.saveFile(part.getInputStream(), path)) {
        			
        		 	resp.getWriter().println("Profile Updated Successfully");
        		 	
        		 	Message msg= new Message("Profile Updated Successfully","success","alert-success");
        	          s.setAttribute("msg",msg);
        	          
        		 	
        		}
        		
        		else {
        			
        		 	resp.getWriter().println("File Not Save Successfully");
        		 	
        		 	Message msg= new Message("Something went Wrong","error","alert-danger");
      	          s.setAttribute("msg",msg);
        			
        		}
        			
        	
        	}
        
        else {
        	resp.getWriter().println("Updation failed");
         	Message msg= new Message("Updation failed....","error","alert-danger");
	          s.setAttribute("msg",msg);
        	
        }

        resp.sendRedirect("profile.jsp");
		
	}

}
