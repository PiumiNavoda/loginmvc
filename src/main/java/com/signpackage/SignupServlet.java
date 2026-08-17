package com.signpackage;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/login")
public class SignupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private SignupDao signupdao=new SignupDao();
	
	public void init() {
		signupdao=new SignupDao();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String uname=request.getParameter("uname");
		String password=request.getParameter("password");
		
		SignupModel signupmodel=new SignupModel();
		signupmodel.setUname(uname);
		signupmodel.setPassword(password);
		
		try {
			if(signupdao.validate(signupmodel)) {
				response.sendRedirect("loginsuccessful.jsp");
			}else {
				HttpSession session=request.getSession();
				session.setAttribute("user", uname);
				response.sendRedirect("loginunsuccessful.jsp");
			}
		}catch(Exception e){
			e.printStackTrace();
			response.sendRedirect("signup.jsp?message=Error occured.");
		}
		
		
	}

}
