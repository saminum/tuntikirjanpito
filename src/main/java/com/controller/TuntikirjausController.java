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
	public String etusivu(Model model, @ModelAttribute("virhe") Object virheObject ) {
		logger.info("Siirrytään palvelun etusivulle. Adminille ja käyttäjälle eri näkymät");
		ProjektiHenkiloImpl projektiHenkilo = new ProjektiHenkiloImpl();
		projektiHenkilo.sethenkilot(dao.haeHenkilot());
		projektiHenkilo.setprojektit(dao.haeProjektit());
		model.addAttribute("henkiloProjekti", projektiHenkilo);
		System.out.println("virheobject: " + virheObject);
		model.addAttribute("virhe", virheObject);
		
		if(!model.containsAttribute("projekti")){
			Projekti tyhjaProjekti = new ProjektiImpl();
			model.addAttribute("projekti", tyhjaProjekti);
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
	
	//http://stackoverflow.com/questions/7429649/how-to-pass-model-attributes-from-one-spring-mvc-controller-to-another-controlle
	
	@RequestMapping(value="lisaa_henkilo_projektiin", method=RequestMethod.POST)
	public String createUserForProject(Model model, RedirectAttributes redirectAttributes, @ModelAttribute(value="henkiloProjekti") ProjektiHenkiloImpl henkiloProjekti){
		logger.info("lisätään henkilö id:llä " + henkiloProjekti.gethenkilo_id() + " projektiin id:llä " + henkiloProjekti.getprojekti_id());
		int onnistuiko = dao.lisaaHenkiloProjektiin(henkiloProjekti.gethenkilo_id(), henkiloProjekti.getprojekti_id());
		if (onnistuiko == 0){
			logger.info("Henkilö on jo valitsemassasi projektissa");
			String virheViestiLisaaHlo = "Henkilö kuuluu jo projektiin";
			redirectAttributes.addFlashAttribute("virhe", virheViestiLisaaHlo);
		}else{
			logger.info("Henkilö lisätty onnistuneesti projektiin");
		}
		return "redirect:/";
	}
	
	@RequestMapping(value="luo_projekti", method=RequestMethod.POST)
	public String createNewProject(@ModelAttribute(value="henkiloProjekti") ProjektiHenkiloImpl henkiloProjekti, BindingResult result, RedirectAttributes attr){
		System.out.println("############### projektin id: " + henkiloProjekti.getprojekti_id());
		System.out.println("#############  henkilon id: "+ henkiloProjekti.gethenkilo_id());
		//dao.lisaaHenkiloProjektiin(projekti);
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
			return "redirect:/";
		}else{
			String escapedHtml = HtmlUtils.htmlEscape(henkilot.getTunnit().get(0).getKuvaus());
			henkilot.getTunnit().get(0).setKuvaus(escapedHtml);
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
		List<HenkilotImpl> henkilot = dao.haeHenkilonTunnit(henk_id);
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
