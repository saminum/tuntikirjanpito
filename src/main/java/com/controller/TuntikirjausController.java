package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.beans.Henkilot;
import com.beans.Henkilot2;
import com.beans.HenkilotImpl;
import com.dao.TuntiDAO;


@Controller
public class TuntikirjausController {
	
	
	@Autowired
	private TuntiDAO dao;
	
    @RequestMapping("/tuntikirjanpito")
	public String getView(Model model){
    	System.out.println("LADATAAN ETUSIVU");
//		logger.info("Listataan tunnit ja luodaan formi.");
//		List<HenkilotImpl> henkilot = dao.haeTunnit();
//		model.addAttribute("henkilot", henkilot);
		List<Henkilot2> henkilotesti = dao.findAll();
		System.out.println(henkilotesti);
		List<HenkilotImpl> henkiloidenTunnit = dao.summaaTunnit();
		System.out.println(henkiloidenTunnit);
//		List<HenkilotImpl> henkiloidenTiedot = dao.haeHenkilot();
//		model.addAttribute("henkiloTiedot", henkiloidenTiedot);
		model.addAttribute("henkiloidenTunnit", henkiloidenTunnit);
//		if(!model.containsAttribute("henkilo")){
//	    Henkilot tyhjaHenkilo = new HenkilotImpl();
//		model.addAttribute("henkilo", tyhjaHenkilo);
//	  	}
//		System.out.println("datepicker " + henkilot.get(0).getTunnit().get(0).getStringdate());
		return "index";
	}
    
    
    
}
