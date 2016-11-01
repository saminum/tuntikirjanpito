package com.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.HtmlUtils;

import com.beans.Henkilot;
import com.beans.HenkilotImpl;
import com.dao.TuntiDAO;


@Controller
public class TuntikirjausController {
	
	@Autowired
	private TuntiDAO dao;
	
    @RequestMapping("/tuntikirjanpito")
	public String getView(Model model){
//		logger.info("Listataan tunnit ja luodaan formi.");
		List<HenkilotImpl> henkilot = dao.haeTunnit();
		model.addAttribute("henkilot", henkilot);
		List<HenkilotImpl> henkiloidenTunnit = dao.summaaTunnit();
		List<HenkilotImpl> henkiloidenTiedot = dao.haeHenkilot();
		model.addAttribute("henkiloTiedot", henkiloidenTiedot);
		model.addAttribute("henkiloidenTunnit", henkiloidenTunnit);
		if(!model.containsAttribute("henkilo")){
		    Henkilot tyhjaHenkilo = new HenkilotImpl();
			model.addAttribute("henkilo", tyhjaHenkilo);
	  	}
		return "index";
	}
    
    @RequestMapping(value="/", method=RequestMethod.POST)
	public String create( @ModelAttribute(value="henkilo") @Valid HenkilotImpl henkilot, BindingResult result, RedirectAttributes attr){
		if(result.hasErrors()){
			System.out.println("RESULT " + result);
			attr.addFlashAttribute("org.springframework.validation.BindingResult.henkilo", result);
		    attr.addFlashAttribute("henkilo", henkilot);
			return "redirect:/tuntikirjanpito";
		}else{
			String escapedHtml = HtmlUtils.htmlEscape(henkilot.getTunnit().get(0).getKuvaus());
			henkilot.getTunnit().get(0).setKuvaus(escapedHtml);
			dao.talleta(henkilot);
			return "redirect:/tuntikirjanpito";
		}
	}
    
    @RequestMapping(value="/tuntikirjanpito/henkilo", method=RequestMethod.POST)
	public String hae(@RequestParam("tunti_id") int henk_id, Model model){
		List<HenkilotImpl> henkilot = dao.haeHenkilonTunnit(henk_id);
		model.addAttribute("henkilot", henkilot);
		List<HenkilotImpl> henkiloidenTunnit = dao.summaaTunnit();
		model.addAttribute("henkiloidenTunnit", henkiloidenTunnit);
		List<HenkilotImpl> henkiloidenTiedot = dao.haeHenkilot();
		model.addAttribute("henkiloTiedot", henkiloidenTiedot);
		Henkilot tyhjaHenkilo = new HenkilotImpl();
		model.addAttribute("henkilo", tyhjaHenkilo);
		return "index";
		}
    
    @RequestMapping(value="/tuntikirjanpito/poista", method=RequestMethod.POST)
	public String poista(@RequestParam("tunti_id") int henk_id)  {
//		logger.info("Poistetaan henkilön tuntirivi tietokannasta.");		
		try{
			dao.poista(henk_id);		
		}catch (DataAccessException ex) {		
//			logger.debug("Käyttäjän tuntirivin poisto epäonnistui.");
		}		
		return "redirect:/tuntikirjanpito";
	}
    
    
}
