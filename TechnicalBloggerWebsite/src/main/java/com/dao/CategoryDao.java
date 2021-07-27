package com.dao;

import java.sql.*;

import com.entities.Category;
import com.entities.User;

public class CategoryDao {
	
	private Connection con;
	
	public CategoryDao(Connection con) {
        this.con = con;
    }
	
	public boolean saveCategory(Category category) {
        boolean f = false;
        try {
            //user -->database

            String query = "insert into categories(name,description) values (?,?)";
            PreparedStatement psmt = this.con.prepareStatement(query);
            psmt.setString(1,category.getName());
            psmt.setString(2,category.getDescription());
            psmt.executeUpdate();
            f = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return f;

    }


}
