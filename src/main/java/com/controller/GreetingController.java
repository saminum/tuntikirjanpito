package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.beans.Henkilot2;
import com.dao.TuntiDAO2;

@Controller
@RequestMapping (value="/")

public class GreetingController {
	
	
	@Autowired
	private TuntiDAO2 dao;

//    @RequestMapping()
//    public String greeting(@RequestParam(value="name", required=false, defaultValue="World") String name, Model model) {
//    	
//        model.addAttribute("name", name);
//        return "greeting";
//    }
    
    
    @RequestMapping()
	public String getView(Model model){
    	System.out.println("LADATAAN ETUSIVU");
//		logger.info("Listataan tunnit ja luodaan formi.");
//		List<HenkilotImpl> henkilot = dao.haeTunnit();
//		model.addAttribute("henkilot", henkilot);
		List<Henkilot2> henkiloidenTunnit = dao.findAll();
		System.out.println(henkiloidenTunnit);
//		List<HenkilotImpl> henkiloidenTiedot = dao.haeHenkilot();
//		model.addAttribute("henkiloTiedot", henkiloidenTiedot);
		model.addAttribute("henkiloidenTunnit", henkiloidenTunnit);
//		if(!model.containsAttribute("henkilo")){
//	    Henkilot tyhjaHenkilo = new HenkilotImpl();
//		model.addAttribute("henkilo", tyhjaHenkilo);
//	  	}
//		System.out.println("datepicker " + henkilot.get(0).getTunnit().get(0).getStringdate());
		return "greeting";
	}
    
    
    
}
