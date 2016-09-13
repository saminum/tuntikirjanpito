package com.tunnit;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.beans.Henkilot;
import com.beans.Tunnit;
import com.dao.HenkiloDAO;

/**
 * Servlet implementation class TuntiServlet
 */
@WebServlet("/TuntiServlet")
public class TuntiServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TuntiServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String tunnit = request.getParameter("tunnit");
		String nimi = request.getParameter("nimi");
		String kuvaus = request.getParameter("kuvaus");
		String henkilo_id = request.getParameter("henkilo_id");
		
		Tunnit tunnin_tiedot = new Tunnit();
		
		Henkilot henkilo = new Henkilot();
		henkilo.setId(Integer.parseInt(henkilo_id));
		
		tunnin_tiedot.setId(Integer.parseInt(henkilo_id));
		tunnin_tiedot.setKuvaus(kuvaus);
		tunnin_tiedot.setTunnit(Integer.parseInt(tunnit));
		
		HenkiloDAO hdao = new HenkiloDAO;
		hdao.lisaaTunnitKantaan(tunnin_tiedot);
		
		response.sendRedirect("index.jsp");
		
	}

}
