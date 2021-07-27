package com.dao;


import java.util.*;
import com.entities.Category;
import com.entities.posts;

import java.sql.*;
public class PostDao {
	Connection con;

    public PostDao(Connection con) {
        this.con = con;
    }

    public ArrayList<Category> getAllCategories() {
        ArrayList<Category> list = new ArrayList<>();

        try {

            String q = "select * from categories";
            Statement st = this.con.createStatement();
            ResultSet set = st.executeQuery(q);
            while (set.next()) {
                int cid = set.getInt("cid");
                String name = set.getString("name");
                String description = set.getString("description");
                Category c = new Category(cid, name, description);
                list.add(c);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
    
    
   public  boolean savePost(posts p) {
	   
	   boolean f=false;
	   
	   try {
		   
		   String q = "insert into posts(pTitle,pContent,pCode,pPic,cid,id) values(?,?,?,?,?,?)";
		   
		   PreparedStatement pstmt=con.prepareStatement(q);
		   
		   pstmt.setString(1, p.getpTitle());
           pstmt.setString(2, p.getpContent());
           pstmt.setString(3, p.getpCode());
           pstmt.setString(4, p.getpPic());
           pstmt.setInt(5, p.getCid());
           pstmt.setInt(6, p.getId());
           pstmt.executeUpdate();
           f = true;   
		   
		   
	   }catch(Exception e) {
		   
		   e.printStackTrace();
	   }
    	
    	return f;
    }
   
   //All the Posts
   
   public List<posts>getAllPosts(){
	
	   List<posts> list=new ArrayList<>();
	   
	   
	   //fetch all the posts
	   
       try {

           PreparedStatement p = con.prepareStatement("select * from posts order by pid desc");

           ResultSet set = p.executeQuery();

           while (set.next()) {

               int pid = set.getInt("pid");
               String pTitle = set.getString("pTitle");
               String pContent = set.getString("pContent");
               String pCode = set.getString("pCode");
               String pPic = set.getString("pPic");
               int cid = set.getInt("cid");
               int id = set.getInt("id");
               posts post = new posts(pid, pTitle, pContent, pCode, pPic,cid, id);

               list.add(post);
           }

       } catch (Exception e) {
           e.printStackTrace();
       }
	   
	   return list;
	   
	   
   }
   
   public List<posts>getPostByCatId(int cid){
	   
	  List<posts> list= new ArrayList<>();
	  
	//fetch all post by category id i.e cid
      try {

          PreparedStatement p = con.prepareStatement("select * from posts where catId=?");
          p.setInt(1, cid);
          ResultSet set = p.executeQuery();

          while (set.next()) {

              int pid = set.getInt("pid");
              String pTitle = set.getString("pTitle");
              String pContent = set.getString("pContent");
              String pCode = set.getString("pCode");
              String pPic = set.getString("pPic");
              int id = set.getInt("id");
              posts post = new posts(pid, pTitle, pContent, pCode, pPic,cid,id);

              list.add(post);
          }

      } catch (Exception e) {
          e.printStackTrace();
      }
	  
	  return list;
   }
   
   
    
    

}
