package com.dcl;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@WebServlet("/login")
public class login extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String mail= req.getParameter("mail");
		String password=req.getParameter("password");
		
		String query="select MAIL,PASSWORD from student where MAIL=? AND PASSWORD=? ";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/vijay_demo","root","Rashivu@246");
			
			PreparedStatement ps=con.prepareStatement(query);
			ps.setString(1, mail);
			ps.setString(2, password);
			ResultSet rs=ps.executeQuery();
			
			if(rs.next()) {
				resp.getWriter().println("<h1>login succesfully<h1>");
			}else {
				resp.getWriter().println("<h1>invalid email or password<h1>");
				
			}
			
			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	

}
