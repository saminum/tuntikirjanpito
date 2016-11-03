package com.controller;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


	@Controller
	@RequestMapping (value="/")
	public class LoginController {
		
		
	 
		@RequestMapping(value="/loginpage", method = RequestMethod.GET)
		public String login(Model model) {
			return "login";
	 
		}
	 
		@RequestMapping(value="/loginfail", method = RequestMethod.GET)
		public String loginerror(Model model) {
			model.addAttribute("loginerror", "true");
			return "login";
	 
		}
	 
		@RequestMapping(value="/logout", method = RequestMethod.GET)
		public String logout(Model model,  HttpServletResponse response) {
			model.addAttribute("loggedout", "true");
			response.setHeader("Pragma", "no-cache");
		    response.setHeader("Cache-Control", "no-cache");
		    response.setDateHeader("Expires", 0);
			return "login";
	 
		}
		
	}
