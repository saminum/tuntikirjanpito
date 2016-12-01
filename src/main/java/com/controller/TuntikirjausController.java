package com.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import org.springframework.security.access.prepost.PreAuthorize;

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
import com.beans.Projekti;
import com.beans.ProjektiHenkilo;
import com.beans.ProjektiHenkiloImpl;
import com.beans.ProjektiImpl;
import com.dao.TuntiDAO;


@Controller
public class TuntikirjausController {
	public int projekti_id;
	final static Logger logger = LoggerFactory.getLogger(TuntikirjausController.class);
	
	@Autowired
	private TuntiDAO dao;
	
	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String etusivu(Model model, HttpServletRequest request ) {
		HttpSession session = request.getSession(true);		
		logger.info("Siirrytään palvelun etusivulle. Adminille ja käyttäjälle eri näkymät");
		ProjektiHenkiloImpl projektiHenkilo = new ProjektiHenkiloImpl();
		projektiHenkilo.sethenkilot(dao.haeHenkilot());
		projektiHenkilo.setprojektit(dao.haeProjektit());
		model.addAttribute("henkiloProjekti", projektiHenkilo);
		String virhe = (String) session.getAttribute("virhe");
		String proj_luonti_virhe = (String) session.getAttribute("projektin_luonti_virhe");
		model.addAttribute("virhe", virhe);
		model.addAttribute("proj_luonti_virhe", proj_luonti_virhe);
		session.removeAttribute("virhe");
		session.removeAttribute("projektin_luonti_virhe");

		if(!model.containsAttribute("projekti")){
			Projekti tyhjaProjekti = new ProjektiImpl();
			model.addAttribute("projekti", tyhjaProjekti);
	  	}
		if(!model.containsAttribute("henkiloProjekti")){
			ProjektiHenkilo tyhjaProjektiHenkilo = new ProjektiHenkiloImpl();
			model.addAttribute("henkiloProjekti", tyhjaProjektiHenkilo);
	  	}
		List<ProjektiImpl> projektit = dao.haeProjektit();
		model.addAttribute("projektit", projektit);
		return "etusivu";
	}
	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/listaa_projektit", method = RequestMethod.POST)
	public String haeProjektit(Model model, RedirectAttributes redirectAttributes, @ModelAttribute(value="Projekti") ProjektiImpl projekti){
		logger.info("Siirrytään projektinäkymään, projekti_id= " + projekti.getProjekti_id());
		projekti_id = projekti.getProjekti_id();
		model.addAttribute("projekti", projekti);
		return "redirect:/projekti";
	}
	
	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value="lisaa_henkilo_projektiin", method=RequestMethod.POST)
	public String createUserForProject(Model model, HttpServletRequest request, @ModelAttribute(value="henkiloProjekti") @Valid ProjektiHenkiloImpl henkiloProjekti, BindingResult result, RedirectAttributes attr){
		logger.info("lisätään henkilö id:llä " + henkiloProjekti.gethenkilo_id() + " projektiin id:llä " + henkiloProjekti.getprojekti_id());
		if(result.hasErrors()){
			logger.info("Syöttökentissä virheitä, palataan sivulle");
			attr.addFlashAttribute("org.springframework.validation.BindingResult.henkiloProjekti", result);
		    attr.addFlashAttribute("henkiloProjekti", henkiloProjekti);
			return "redirect:/";
		}
		int onnistui = dao.lisaaHenkiloProjektiin(henkiloProjekti.gethenkilo_id(), henkiloProjekti.getprojekti_id());
		HttpSession session = request.getSession(true);
		if (onnistui == 0){
			String virheViesti = "Henkilö on jo valitsemassasi projektissa";
			logger.info(virheViesti);	
			session.setAttribute("virhe", virheViesti);
		}else{
			logger.info("Henkilö lisätty onnistuneesti projektiin");
			session.removeAttribute("virhe");
		}
		return "redirect:/";
	}
	
	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value="luo_projekti", method=RequestMethod.POST)
	public String createNewProject(Model model, @ModelAttribute(value="projekti") @Valid ProjektiImpl projekti, BindingResult result, RedirectAttributes attr, HttpServletRequest request ){
		if(result.hasErrors()){
			attr.addFlashAttribute("org.springframework.validation.BindingResult.projekti", result);
		    attr.addFlashAttribute("projekti", projekti);
			return "redirect:/";
		}
		HttpSession session = request.getSession(true);
		String projektiNimi = projekti.getNimi();
		String projektiKuvaus = projekti.getKuvaus();
		logger.info("Yritetään lisätä uusi projekti nimellä: " +projektiNimi+ " kuvauksella: " + projektiKuvaus);
		int onnistui = dao.luoProjekti(projekti);
		if (onnistui == 0){
			String virheViesti = "Saman niminen projekti on jo olemassa. Valitse uusi nimi.";
			session.setAttribute("projektin_luonti_virhe", virheViesti);
			logger.info("Saman niminen projekti on jo olemassa. Valitse uusi nimi.");
		}else{
			logger.info("Uusi projekti lisätty onnistuneesti");
			session.removeAttribute("projektin_luonti_virhe");
		}
		return "redirect:/";
	}
	
	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value="poista_projekti", method=RequestMethod.POST)
	public String Delete_project(@ModelAttribute(value="projekti") ProjektiImpl projekti){
		int projektiId = projekti.getProjekti_id();
		logger.info("Yritetään poistaa projekti id:llä: " +projektiId);
		dao.PoistaProjekti(projekti);
		return "redirect:/";
	}
	
    @RequestMapping(value="/projekti", method=RequestMethod.GET)
	public String getView(Model model, HttpServletRequest request){
    	logger.info("projekti id = : " + projekti_id);
		logger.info("Listataan tunnit ja luodaan formi.");

//		PROJEKTI ID TUODAAN ETUSIVUN NÄKYMÄSTÄ MISSÄ VALITAAN MIKÄ PROJEKTI NÄYTETÄÄN
//		model.GET ATTRIBUTE TAI JOTAIN..ATTRIBUTE. @ModelAttribute
		List<HenkilotImpl> henkilot = dao.haeTunnit(projekti_id);
		model.addAttribute("henkilot", henkilot);
		List<HenkilotImpl> henkiloidenTunnit = dao.summaaTunnit(projekti_id);
		List<HenkilotImpl> henkiloidenTiedot = dao.haeProjektiHenkilot(projekti_id);

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
			System.out.println("RESULT " + result);
			attr.addFlashAttribute("org.springframework.validation.BindingResult.henkilo", result);
		    attr.addFlashAttribute("henkilo", henkilot);
			return "redirect:/projekti";
		}else{
			String escapedHtml = HtmlUtils.htmlEscape(henkilot.getTunnit().get(0).getKuvaus());
			henkilot.getTunnit().get(0).setKuvaus(escapedHtml);
			dao.talleta(henkilot, projekti_id);
			return "redirect:/projekti";
		}
    }
    
    @RequestMapping(value="henkilo", method=RequestMethod.POST)
	public String hae(@RequestParam("tunti_id") int henk_id, Model model, HttpServletRequest request){
		HttpSession session = request.getSession(true);
		session.setAttribute("henk_id", henk_id);
		List<HenkilotImpl> henkilot = dao.haeHenkilonTunnit(henk_id, projekti_id);
		model.addAttribute("henkilot", henkilot);
		List<HenkilotImpl> henkiloidenTunnit = dao.summaaTunnit(projekti_id);
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
		List<HenkilotImpl> henkilot = dao.haeHenkilonTunnit(henk_id, projekti_id);
		model.addAttribute("henkilot", henkilot);
		List<HenkilotImpl> henkiloidenTunnit = dao.summaaTunnit(projekti_id);
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
		return "redirect:/projekti";
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