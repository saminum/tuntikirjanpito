package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping (value="/")

public class GreetingController {

    @RequestMapping()
    public String greeting(@RequestParam(value="name", required=false, defaultValue="World") String name, Model model) {
    	System.out.println("------ TOIIIIMIIIIKOOOO ?????   sadjfkladsjfladskfjadsklfö");
        model.addAttribute("name", name);
        return "greeting";
    }
}
