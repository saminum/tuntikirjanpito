package com.tunnit;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/PoistaTuntiServlet")
public class PoistaTuntiServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public PoistaTuntiServlet() {
        super();
        
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("tunti_id");
		System.out.println("tunti_id " + id);
		
		response.sendRedirect("TuntiServlet");
	}

}
