package com.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.beans.Henkilot;
import com.beans.HenkilotImpl;
import com.beans.Tunnit;
import com.dao.TuntiDAO;


@Controller
@RequestMapping (value="")
public class TuntikirjausController {
	
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
		List<HenkilotImpl> henkilot = dao.haeTunnit();
		model.addAttribute("henkilot", henkilot);
		List<HenkilotImpl> henkiloidenTunnit = dao.summaaTunnit();
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
			attr.addFlashAttribute("org.springframework.validation.BindingResult.henkilo", result);
		    attr.addFlashAttribute("henkilo", henkilot);
		    System.out.println("result! " + result);
			return "redirect:/";
		}else{
			dao.talleta(henkilot);
			return "redirect:/";
		}
	}
	
	
	// Poista metodin vastaanotto	
	
	@RequestMapping(value="poista", method=RequestMethod.POST)
	public String poista(@RequestParam("tunti_id") int henk_id)  {
		dao.poista(henk_id);
		return "redirect:/";
	}
	
}
