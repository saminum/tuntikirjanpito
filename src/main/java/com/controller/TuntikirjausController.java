package com.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;








import javax.inject.Inject;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;








import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.test.web.servlet.result.FlashAttributeResultMatchers;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.FlashMap;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.bind.annotation.ResponseBody;








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
	public String getView(Model model, HttpServletRequest request){
		logger.info("Listataan tunnit ja luodaan formi.");
		List<HenkilotImpl> henkilot = dao.haeTunnit();
		model.addAttribute("henkilot", henkilot);
		List<HenkilotImpl> henkiloidenTunnit = dao.summaaTunnit();
		List<HenkilotImpl> henkiloidenTiedot = dao.haeHenkilot();
		model.addAttribute("henkiloTiedot", henkiloidenTiedot);
		model.addAttribute("henkiloidenTunnit", henkiloidenTunnit);
		HttpSession session = request.getSession(false);
		if(session!=null){
		session.invalidate();
		}
		if(!model.containsAttribute("henkilo")){
	    Henkilot tyhjaHenkilo = new HenkilotImpl();
		model.addAttribute("henkilo", tyhjaHenkilo);
	  	}
		System.out.println("datepicker " + henkilot.get(0).getTunnit().get(0).getStringdate());
		return "index";
	}
	
	// Käyttäjän tuntien lisäys
	@RequestMapping(value="/", method=RequestMethod.POST)
	public String create( @ModelAttribute(value="henkilo") @Valid HenkilotImpl henkilot, BindingResult result, RedirectAttributes attr, HttpServletRequest request, Model model){
		if(result.hasErrors()){
			System.out.println("RESULT " + result);
			attr.addFlashAttribute("org.springframework.validation.BindingResult.henkilo", result);
			attr.addFlashAttribute("henkilo", henkilot);
		    HttpSession session = request.getSession(false);
		if(session.getAttribute("henk_id")!=null && result.hasErrors()){
		    int hId = (Integer) session.getAttribute("henk_id");
		    model.addAttribute("org.springframework.validation.BindingResult.henkilo", result);
		    hae(hId, model, request);
			return "index";
		}else{
		    	return "redirect:/";
		    }
		}else{
			dao.talleta(henkilot);
			HttpSession session = request.getSession(false);
		if(session.getAttribute("henk_id")!=null){
		    int hId = (Integer) session.getAttribute("henk_id");
		    hae(hId, model, request);
		    return "index";
		}else{
		    	return "redirect:/";
		    }
		}

	}
	
	@RequestMapping(value="henkilo", method=RequestMethod.POST)
	public String hae(@RequestParam("tunti_id") int henk_id, Model model, HttpServletRequest request){
		HttpSession session = request.getSession(true);
		session.setAttribute("henk_id", henk_id);
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
	public String poista(@RequestParam("tunti_id") int henk_id, Model model, HttpServletRequest request)  {
		logger.info("Poistetaan henkilön tuntirivi tietokannasta.");
		
		HttpSession session = request.getSession(false);
		if(session.getAttribute("henk_id")!=null){
		    int hId = (Integer)session.getAttribute("henk_id");
		    
		    try{
				dao.poista(henk_id);
				
			}catch (DataAccessException ex) {
				
				logger.debug("Käyttäjän tuntirivin poisto epäonnistui.");
			}
		    hae(hId, model, request);
		    return "index";
		}else{
		
		try{
			dao.poista(henk_id);
			
		}catch (DataAccessException ex) {
			
			logger.debug("Käyttäjän tuntirivin poisto epäonnistui.");
		}
		
		return "redirect:/";
	}
	
}
}
