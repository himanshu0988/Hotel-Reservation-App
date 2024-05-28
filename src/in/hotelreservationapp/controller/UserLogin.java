package in.hotelreservationapp.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.hotelreservationapp.dao.UserDao;
import in.hotelreservationapp.model.User;

@WebServlet("/UserLogin")
public class UserLogin extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId=request.getParameter("userid");
		String password=request.getParameter("password");
		
		User login=new User();
		login.setUserId(userId);
		login.setPassword(password);
		
		UserDao loginDao=new UserDao();
		String status=loginDao.login(login);
		PrintWriter out = response.getWriter();
		
		if (status.equals("success")) {
			response.sendRedirect("welcome.html");
		} else {
			out.println("<h1 style='color:green; text-align:center;'>Login FAILED</h1>");
		}
		out.close();
	}

}
