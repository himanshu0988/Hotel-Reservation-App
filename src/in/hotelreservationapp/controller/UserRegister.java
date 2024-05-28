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

@WebServlet("/Register")
public class UserRegister extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String userId=request.getParameter("userid");
		String name=request.getParameter("name");
		String email=request.getParameter("email");
		String password=request.getParameter("password");
		
		User register=new User();
		register.setUserId(userId);
		register.setName(name);
		register.setEmail(email);
		register.setPassword(password);
		
		UserDao registerDao=new UserDao();
		String status=registerDao.register(register);
		PrintWriter out = response.getWriter();
		
		if (status.equals("success")) {
			response.sendRedirect("sucessregister.html");
		} else {
			out.println("<h1 style='color:green; text-align:center;'>REGISTRATION FAILED</h1>");
		}
		out.close();
	}
}
