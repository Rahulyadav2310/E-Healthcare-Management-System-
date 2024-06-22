package com.user.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.AppointmentDAO;
import com.db.DBConnect;
import com.entity.Appointment;

@WebServlet("/appAppointment")
public class AppointmentServlet extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		 
		int userId = Integer.parseInt(req.getParameter("user_id"));
		String fullName = req.getParameter("fullname");
		String gender = req.getParameter("gender");

		String age = req.getParameter("age");

		String appoinDate = req.getParameter("appointDate");

		String email = req.getParameter("email");

		String phNo = req.getParameter("phNo");

		String Diseases = req.getParameter("diseases");
		int doctorId = Integer.parseInt(req.getParameter("doctor_id"));
		
		String address = req.getParameter("address");
		
		Appointment ap = new Appointment(userId, fullName, gender, age, appoinDate, email, phNo, Diseases, doctorId, address, "pending");
		
		AppointmentDAO dao = new AppointmentDAO(DBConnect.getConn());
		HttpSession session = req.getSession();
		if (dao.addAppointment(ap)) {
			session.setAttribute("succMsg", "Appointment Successfully");
			resp.sendRedirect("user_appointment.jsp");
		}else {
			
			session.setAttribute("errorMsg", "Something went wrong on server");
			resp.sendRedirect("user_appointment.jsp");
			
		}
 
		
	}
	
	

}
