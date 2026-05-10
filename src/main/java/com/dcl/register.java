package com.dcl;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/register")

public class register extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String name = req.getParameter("name");
		String phone = req.getParameter("phone");
		String mail = req.getParameter("mail");
		String password = req.getParameter("password");
		String conpassword = req.getParameter("confirmPassword");

		String query = "INSERT INTO student VALUES(0,?,?,?,?)";
		int i = 0;

		if (password.equals(conpassword)) {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/DATABASE_NAME", "USER(root)",
						"PASSWORD");
				PreparedStatement ps = con.prepareStatement(query);
				ps.setString(1, name);
				Long phn = Long.parseLong(phone);
				ps.setLong(2, phn);
				ps.setString(3, mail);
				ps.setString(4, password);
				i = ps.executeUpdate();

			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		if (i > 0) {
			resp.getWriter().print("<h1>data added succsfully<h1>");
		} else {
			resp.getWriter().print("<h1>not added</h1>");
		}
		// TODO Auto-generated method stub
	}

}
