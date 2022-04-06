package com.ifce.br.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	
	
	@GetMapping("/livraria/home")
	public String home () {
		return"home";
		
	}
	

}
