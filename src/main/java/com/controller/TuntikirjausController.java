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
		model.addAttribute("virhe", virhe);
		session.removeAttribute("virhe");

		if(!model.containsAttribute("projekti")){
			Projekti tyhjaProjekti = new ProjektiImpl();
			model.addAttribute("projekti", tyhjaProjekti);
	  	}
		return "etusivu";
	}
	
	//http://stackoverflow.com/questions/7429649/how-to-pass-model-attributes-from-one-spring-mvc-controller-to-another-controlle
	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value="lisaa_henkilo_projektiin", method=RequestMethod.POST)
	public String createUserForProject(Model model, HttpServletRequest request, @ModelAttribute(value="henkiloProjekti") ProjektiHenkiloImpl henkiloProjekti){
		logger.info("lisätään henkilö id:llä " + henkiloProjekti.gethenkilo_id() + " projektiin id:llä " + henkiloProjekti.getprojekti_id());
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
	public String createNewProject(@ModelAttribute(value="projekti") ProjektiImpl projekti, BindingResult result, RedirectAttributes attr){
		String projektiNimi = projekti.getNimi();
		String projektiKuvaus = projekti.getKuvaus();
		logger.info("Yritetään lisätä uusi projekti nimellä: " +projektiNimi+ " kuvauksella: " + projektiKuvaus);
		int onnistui = dao.luoProjekti(projekti);
		
		return "redirect:/";
	}
	
    @RequestMapping(value="/projekti", method=RequestMethod.GET)
	public String getView(Model model ){
		logger.info("Listataan tunnit ja luodaan formi.");
//		PROJEKTI ID TUODAAN ETUSIVUN NÄKYMÄSTÄ MISSÄ VALITAAN MIKÄ PROJEKTI NÄYTETÄÄN
//		model.GET ATTRIBUTE TAI JOTAIN..ATTRIBUTE. @ModelAttribute
		List<HenkilotImpl> henkilot = dao.haeTunnit(1);
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
