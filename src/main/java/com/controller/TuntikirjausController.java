package com.controller;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.beans.Henkilot;
import com.beans.HenkilotImpl;
import com.dao.TuntiDAO;


@Controller
@RequestMapping (value="")
public class TuntikirjausController {
	
	final static Logger logger = LoggerFactory.getLogger(TuntikirjausController.class);
	
	@Autowired
	private TuntiDAO dao;
	
	public TuntiDAO getDao() {
		return dao;
	}

	public void setDao(TuntiDAO dao) {
		this.dao = dao;
	}
	
	//Tuntien listaus ja formi
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String getView(Model model){
		logger.info("Listataan tunnit ja luodaan formi.");
		List<HenkilotImpl> henkilot = dao.haeTunnit();
		model.addAttribute("henkilot", henkilot);
		List<HenkilotImpl> henkiloidenTunnit = dao.summaaTunnit();
		model.addAttribute("henkiloidenTunnit", henkiloidenTunnit);
		Henkilot tyhjaHenkilo = new HenkilotImpl();
		model.addAttribute("henkilo", tyhjaHenkilo);
		return "index";
	}
	
	// Käyttäjän tuntien lisäys
	
	@RequestMapping(value="/", method=RequestMethod.POST)
	public String create( @ModelAttribute(value="henkilo") HenkilotImpl henkilot){
		logger.info("Lisätään käyttäjälle tunnit.");
		
		try{
			dao.talleta(henkilot);
			
		}catch (DataAccessException ex) {
			
			logger.debug("Käyttäjän tuntien lisääminen epäonnistui.");
		}
			return "redirect:/";
		}
	
	// Poista metodin vastaanotto	
	
	@RequestMapping(value="poista", method=RequestMethod.POST)
	public String poista(@RequestParam("tunti_id") int henk_id)  {
		logger.info("Poistetaan henkilön tuntirivi tietokannasta.");
		
		try{
			dao.poista(henk_id);
			
		}catch (DataAccessException ex) {
			
			logger.debug("Käyttäjän tuntirivin poisto epäonnistui.");
		}
		
		return "redirect:/";
	}
	
}
