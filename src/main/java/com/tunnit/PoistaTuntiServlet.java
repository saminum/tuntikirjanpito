package com.tunnit;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.dao.tuntiDAO;

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
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"spring-config.xml");
		String id = request.getParameter("tunti_id");
		tuntiDAO tDAO = (tuntiDAO) context.getBean("daoLuokka");
		tDAO.poista(Integer.parseInt(id));
		((AbstractApplicationContext) context).close();
		
		response.sendRedirect("TuntiServlet");
	}

}
