package com.hostelmanagement.servlet;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hostelmanagement.model.Register;
import com.hostelmanagement.service.LoginService;

import javax.servlet.annotation.WebServlet;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.getRequestDispatcher("/login.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("LL");

		int mobileNum = Integer.parseInt(request.getParameter("mobileNum"));
		String password = request.getParameter("password");

		LoginService loginServiceImpl = new LoginService();
		List<Register> list = loginServiceImpl.checkUserCredentials(mobileNum, password);
		if (list.isEmpty() != true) {

			Iterator itr = list.iterator();
			while (itr.hasNext()) {

				Object[] obj = (Object[]) itr.next();
			/*	int id = (int) obj[0];
				String firstName = String.valueOf(obj[1]);
				String lastName = String.valueOf(obj[2]);
				String email = String.valueOf(obj[3]);
				String city = String.valueOf(obj[4]);
				String mobNum = String.valueOf(obj[5]);*/
				
				System.out.println(obj[0] +","+  obj[1] +","+  obj[2] +"," + obj[3] + "," +obj[4] +"," + obj[5] + "," + obj[6] + "," + obj[7] );
				
				
				request.setAttribute("firstName", obj[0]);
				request.setAttribute("lastName", obj[1]);
				request.setAttribute("email", obj[2]);
				request.setAttribute("city", obj[4]);
				request.setAttribute("mobNum", obj[3]);
				request.setAttribute("date", obj[5]);
				request.setAttribute("roomNum", obj[6]);
				request.setAttribute("advancePaid", obj[7]);
			}

			/*PrintWriter out = response.getWriter();
			out.println(list);*/
			request.getRequestDispatcher("/residantdetails.jsp").forward(request, response);
		} else {
			request.setAttribute("errorMessage", "Invalid Credentials!");
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}

	}

}
