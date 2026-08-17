package com.registerpackage;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.registerpackage.UserDao;
import com.registerpackage.User;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private UserDao userDao;
	
    public void init() {
        userDao=new UserDao();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	RequestDispatcher dispatcher=request.getRequestDispatcher("/registration.jsp");
		dispatcher.forward(request, response);
    }
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String name=request.getParameter("name");
		String address=request.getParameter("address");
		String contact=request.getParameter("contact");
		String uname=request.getParameter("uname");
		String password=request.getParameter("password");
		
		User user=new User();
		
		user.setName(name);
		user.setAddress(address);
		user.setContact(contact);
		user.setUname(uname);
		user.setPassword(password);
		
		try {
			userDao.registerUser(user);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		RequestDispatcher dispatcher=request.getRequestDispatcher("/userdetails.jsp");
		dispatcher.forward(request, response);
	}
		
}


