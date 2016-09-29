package com.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.beans.Henkilot;
import com.beans.HenkilotImpl;
import com.dao.TuntiDAO;




@Controller
@RequestMapping (value="")
public class TuntikirjausController {
	
	@Inject
	private TuntiDAO dao;
	
	public TuntiDAO getDao() {
		return dao;
	}

	public void setDao(TuntiDAO dao) {
		this.dao = dao;
	}
	
	//Tuntien listaus
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String getView(Model model){
		List<HenkilotImpl> henkilot = dao.haeTunnit();
		model.addAttribute("henkilot", henkilot);
		List<HenkilotImpl> henkiloidenTunnit = dao.summaaTunnit();
		model.addAttribute("henkiloidenTunnit", henkiloidenTunnit);
		return "index";
	}
	
	// Poista metodin vastaanotto	
	
	@RequestMapping(value="poista", method=RequestMethod.POST)
	public String poista(@RequestParam("tunti_id") int henk_id)  {
		dao.poista(henk_id);
		return "redirect:/";
	}
	
//	@RequestMapping(value="/", method=RequestMethod.GET)
//	public String getCreateForm(Model model) {	
//		Henkilot tyhjaHenkilo = new HenkilotImpl();
//		tyhjaHenkilo.setEtunimi("oletusetunimi");
//		
//		model.addAttribute("henkilo", tyhjaHenkilo);
//		return "index	";
//	}
	

//	//FORMIN TEKEMINEN
//	@RequestMapping(value="uusi", method=RequestMethod.GET)
//	public String getCreateForm(Model model) {
//		Henkilot tyhjaHenkilo = new Henkilot();
//		tyhjaHenkilo.setEtunimi("oletusetunimi");
//		
//		model.addAttribute("henkilo", tyhjaHenkilo);
//		return "henk/createForm";
//	}
	
	

}
