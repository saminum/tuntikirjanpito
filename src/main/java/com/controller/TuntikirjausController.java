package com.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


import javax.inject.Inject;
import javax.validation.Valid;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.test.web.servlet.result.FlashAttributeResultMatchers;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.servlet.FlashMap;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.HtmlUtils;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.request.SessionScope;

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
		List<HenkilotImpl> henkiloidenTiedot = dao.haeHenkilot();
		model.addAttribute("henkiloTiedot", henkiloidenTiedot);
		model.addAttribute("henkiloidenTunnit", henkiloidenTunnit);
		if(!model.containsAttribute("henkilo")){
	    Henkilot tyhjaHenkilo = new HenkilotImpl();
		model.addAttribute("henkilo", tyhjaHenkilo);
	  	}
		System.out.println("datepicker " + henkilot.get(0).getTunnit().get(0).getStringdate());
		return "index";
	}
	
	// Käyttäjän tuntien lisäys
	
	@RequestMapping(value="/", method=RequestMethod.POST)

	public String create( @ModelAttribute(value="henkilo") @Valid HenkilotImpl henkilot, BindingResult result, RedirectAttributes attr){
		if(result.hasErrors()){
			System.out.println("RESULT " + result);
			attr.addFlashAttribute("org.springframework.validation.BindingResult.henkilo", result);
		    attr.addFlashAttribute("henkilo", henkilot);
			return "redirect:/";
		}else{
			String escapedHtml = HtmlUtils.htmlEscape(henkilot.getTunnit().get(0).getKuvaus());
			henkilot.getTunnit().get(0).setKuvaus(escapedHtml);
			dao.talleta(henkilot);
			return "redirect:/";
		}

	}
	@RequestMapping(value="henkilo", method=RequestMethod.POST)
	public String hae(@RequestParam("tunti_id") int henk_id, Model model){
		System.out.println("tunti_id " + henk_id);
		List<HenkilotImpl> henkilot = dao.haeHenkilonTunnit(henk_id);
		model.addAttribute("henkilot", henkilot);
		System.out.println(henkilot.toString());
		List<HenkilotImpl> henkiloidenTunnit = dao.summaaTunnit();
		model.addAttribute("henkiloidenTunnit", henkiloidenTunnit);
		List<HenkilotImpl> henkiloidenTiedot = dao.haeHenkilot();
		model.addAttribute("henkiloTiedot", henkiloidenTiedot);
		Henkilot tyhjaHenkilo = new HenkilotImpl();
		model.addAttribute("henkilo", tyhjaHenkilo);
		HenkilotImpl get_id = new HenkilotImpl();
		System.out.println("henk_id " + henk_id);
		get_id.setId(henk_id);
		System.out.println("get_id " + get_id.getId());
		model.addAttribute("henkilot_id", get_id);
		return "index";
	}
	@RequestMapping(value="henkilo", method=RequestMethod.GET)
	public String haeGet(@ModelAttribute(value="henkilot_id") HenkilotImpl get_id, Model model){
		int henk_id = get_id.getId();
		logger.debug("henk_id " + henk_id);
		System.out.println("henki_id " + henk_id);
		List<HenkilotImpl> henkilot = dao.haeHenkilonTunnit(henk_id);
		model.addAttribute("henkilot", henkilot);
		System.out.println(henkilot.toString());
		List<HenkilotImpl> henkiloidenTunnit = dao.summaaTunnit();
		model.addAttribute("henkiloidenTunnit", henkiloidenTunnit);
		List<HenkilotImpl> henkiloidenTiedot = dao.haeHenkilot();
		model.addAttribute("henkiloTiedot", henkiloidenTiedot);
		Henkilot tyhjaHenkilo = new HenkilotImpl();
		model.addAttribute("henkilo", tyhjaHenkilo);
		return "index";
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
