package com.clientui.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

//@Controller
public class HomeController {
	 @RequestMapping("/")
	    public String accueil(Model model){

	      

	        return "Accueil";
	    }
}
