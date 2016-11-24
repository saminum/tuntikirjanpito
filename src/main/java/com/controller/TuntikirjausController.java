package com.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
	
	final static Logger logger = LoggerFactory.getLogger(TuntikirjausController.class);
	
	@Autowired
	private TuntiDAO dao;
	
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
		return "projekti";
	}
    
    @RequestMapping(value="lisaa", method=RequestMethod.POST)
	public String create( @ModelAttribute(value="henkilo") @Valid HenkilotImpl henkilot, BindingResult result, RedirectAttributes attr){
		if(result.hasErrors()){
			attr.addFlashAttribute("org.springframework.validation.BindingResult.henkilo", result);
		    attr.addFlashAttribute("henkilo", henkilot);
		    attr.addFlashAttribute("tallennus", "");
			return "redirect:/";
		}else{
			String escapedHtml = HtmlUtils.htmlEscape(henkilot.getTunnit().get(0).getKuvaus());
			henkilot.getTunnit().get(0).setKuvaus(escapedHtml);
		    attr.addFlashAttribute("tallennus", "ok");
			dao.talleta(henkilot);
			return "redirect:/";
		}
	}
    
    @RequestMapping(value="henkilo", method=RequestMethod.POST)
	public String hae(@RequestParam("tunti_id") int henk_id, Model model, HttpServletRequest request){
		HttpSession session = request.getSession(true);
		session.setAttribute("henk_id", henk_id);
		List<HenkilotImpl> henkilot = dao.haeHenkilonTunnit(henk_id);
		model.addAttribute("henkilot", henkilot);
		List<HenkilotImpl> henkiloidenTunnit = dao.summaaTunnit();
		model.addAttribute("henkiloidenTunnit", henkiloidenTunnit);
		List<HenkilotImpl> henkiloidenTiedot = dao.haeHenkilot();
		model.addAttribute("henkiloTiedot", henkiloidenTiedot);
		Henkilot tyhjaHenkilo = new HenkilotImpl();
		model.addAttribute("henkilo", tyhjaHenkilo);
		HenkilotImpl get_id = new HenkilotImpl();
		get_id.setId(henk_id);
		model.addAttribute("henkilot_id", get_id);
		return "projekti";
	}
	@RequestMapping(value="henkilo", method=RequestMethod.GET)
	public String haeGet(@ModelAttribute(value="henkilot_id") HenkilotImpl get_id, Model model, HttpServletRequest request){
		HttpSession session = request.getSession(true);
		int henk_id = (Integer) session.getAttribute("henk_id");
		List<HenkilotImpl> henkilot = dao.haeHenkilonTunnit(henk_id);
		model.addAttribute("henkilot", henkilot);
		List<HenkilotImpl> henkiloidenTunnit = dao.summaaTunnit();
		model.addAttribute("henkiloidenTunnit", henkiloidenTunnit);
		List<HenkilotImpl> henkiloidenTiedot = dao.haeHenkilot();
		model.addAttribute("henkiloTiedot", henkiloidenTiedot);
		Henkilot tyhjaHenkilo = new HenkilotImpl();
		model.addAttribute("henkilo", tyhjaHenkilo);
		return "projekti";
	}
    
    @RequestMapping(value="poista", method=RequestMethod.POST)
	public String poista(@RequestParam("tunti_id") int henk_id, RedirectAttributes attr)  {
		logger.info("Poistetaan henkilön tuntirivi tietokannasta.");		
		try{
			dao.poista(henk_id);	
			attr.addFlashAttribute("poisto", "ok");
		}catch (DataAccessException ex) {		
			logger.debug("Käyttäjän tuntirivin poisto epäonnistui.");
			attr.addFlashAttribute("poisto", "");
		}		
		return "redirect:/";
	}
    @RequestMapping(value="register", method=RequestMethod.GET)
	public String rekisterointi(Model model){
    	if(!model.containsAttribute("registerhenkilo")){
			Henkilot registerhenkilo = new HenkilotImpl();
			registerhenkilo.setId(0);
			model.addAttribute("registerhenkilo", registerhenkilo);
    	}
		return "register";
	}
    @RequestMapping(value="register", method=RequestMethod.POST)
   	public String rekisterointiKantaan(@ModelAttribute(value="registerhenkilo") @Valid HenkilotImpl henkilo, BindingResult result, RedirectAttributes attr){
    	if(result.hasErrors()){
			attr.addFlashAttribute("org.springframework.validation.BindingResult.henkilo", result);
		    attr.addFlashAttribute("registerhenkilo", henkilo);
			return "register";
		}else{
	    	String encrypted = new BCryptPasswordEncoder().encode(henkilo.getSalasana());
	    	henkilo.setSalasana(encrypted);
	    	dao.lisaaKayttaja(henkilo);
	   		return "redirect:/";
		}
   	}
}