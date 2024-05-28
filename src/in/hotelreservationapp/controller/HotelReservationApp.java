package in.hotelreservationapp.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.hotelreservationapp.dao.CustomerDao;
import in.hotelreservationapp.model.Customer;

@WebServlet("/controller/*")
public class HotelReservationApp extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request,response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}
	private void doProcess(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		CustomerDao customerDao=new CustomerDao();
		
			if(request.getRequestURI().endsWith("addform")){
				
					String reservation_id=request.getParameter("reid");
					String name=request.getParameter("cuname");
					String mobile=request.getParameter("mob");
					String room_num=request.getParameter("roomnum");
					
					Customer customer=new Customer();
					customer.setReservation_id(Integer.parseInt(reservation_id));
					customer.setName(name);
					customer.setMobile(mobile);
					customer.setRoom_num(room_num);
			
					String status=customerDao.addCustomer(customer);
					PrintWriter out = response.getWriter();
					
					RequestDispatcher rd = null;
					if (status.equals("success")) {
						rd=request.getRequestDispatcher("../sucesscheck-in.html");
						rd.forward(request, response);
					} else {
						out.println("<h1 style='color:green; text-align:center;'>RESERVATION FAILED</h1>");
					}
					out.close();	
				}
		
		if(request.getRequestURI().endsWith("searchform")){
			
			String Reservation_id=request.getParameter("Reservation_id");
			Customer customer=customerDao.searchCustomer(Integer.parseInt(Reservation_id));
			
			PrintWriter out = response.getWriter();
			if (customer != null) {
				out.println("<body>");
				out.println("<br/><br/><br/>");
				out.println("<center>");
				out.println("<table border='1'>");
				out.println("<tr><th>RESERVATION ID</th><td>" + customer.getReservation_id() + "</td></tr>");
				out.println("<tr><th>NAME</th><td>" + customer.getName() + "</td></tr>");
				out.println("<tr><th>MOBILE</th><td>" + customer.getMobile() + "</td></tr>");
				out.println("<tr><th>ROOM NUMBER</th><td>" + customer.getRoom_num() + "</td></tr>");
				
				out.println("</table>");
				out.println("</center>");
				out.println("</body>");
			} else {
				out.println("<h1 style='color:red;text-align:center;'>RECORD NOT AVAILABLE FOR THE GIVEN ID " + Reservation_id+ "</h1>");
			}
			out.close();	
		}
		
		if (request.getRequestURI().endsWith("deleteform")) {
			
			String reservation_id = request.getParameter("reservation_id");
			String status = customerDao.deleteCustomer(Integer.parseInt(reservation_id));

			PrintWriter out = response.getWriter();
			RequestDispatcher rd=null;
			if (status.equals("success")) {
				rd=request.getRequestDispatcher("../sucesscheck-out.html");
				rd.forward(request, response);
			} else if (status.equals("failure")) {
				out.println("<body>");
				out.println("<h1 style='color:red;text-align:center;'>Check-Out  FAILED</h1>");
				out.println("</body>");

			} else {
				out.println("<body>");
				out.println("<h1 style='color:green;text-align:center;'>RECORD NOT FOUND FOR Check-Out </h1>");
				out.println("</body>");
			}
			out.close();
		}
		
		if (request.getRequestURI().endsWith("editform")) {
			
			String reservation_id = request.getParameter("reservation_id");

			Customer customer = customerDao.searchCustomer(Integer.parseInt(reservation_id));
			PrintWriter out = response.getWriter();
			if ( customer  != null) {
				// display student records as a form data so it is editable
				out.println("<body>");
				out.println("<center>");
				out.println("<form method='post' action='./controller/updateRecord'>");
				out.println("<table>");
				out.println("<tr><th>Reservation ID</th><td><input type='text' name='Reservation_id' value='" +  customer .getReservation_id()+ "'/></td></tr>");
				out.println("<tr><th>NAME</th><td><input type='text' name='ename' value='" +  customer .getName()+ "'/></td></tr>");
				out.println("<tr><th>Mobile</th><td><input type='text' name='mobile' value='" +  customer.getMobile()+ "'/></td></tr>");
				out.println("<tr><th>Room</th><td><input type='text' name='roomnum' value='" +  customer .getRoom_num()+ "'/></td></tr>");
						
				out.println("<tr><td></td><td><input type='submit' value='update'/></td></tr>");
				out.println("</table>");
				out.println("</form>");
				out.println("</center>");
				out.println("</body>");
			} else {
				// display not found message
				out.println("<body>");
				out.println("<h1 style='color:red;text-align:center;'>Record not avaialable for the give id :: " + reservation_id+ "</h1>");
				out.println("</body>");
			}
			out.close();
		}
		
		if (request.getRequestURI().endsWith("updateRecord")) {
			
			String reservation_id = request.getParameter("Reservation_id");
			String name = request.getParameter("ename");
			String mob = request.getParameter("mobile");
			String room_num=request.getParameter("roomnum");
			
			Customer  customer = new Customer();
			customer .setReservation_id(Integer.parseInt(reservation_id));
			customer .setName(name);
			customer .setMobile(mob);
			customer .setRoom_num(room_num);

			String status = customerDao.updateCustomer( customer );
			PrintWriter out = response.getWriter();

			if (status.equals("success")) {
				out.println("<h1 style='color:green; text-align:center;'> RECORD UPDATED SUCCESSFULLY</h1>");
			} else {
				out.println("<h1 style='color:green; text-align:center;'>RECORD UPDATION FAILED</h1>");
			}
			out.close();
		}
	}
}
