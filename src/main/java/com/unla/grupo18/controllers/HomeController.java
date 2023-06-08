package com.unla.grupo18.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

import com.unla.grupo18.helpers.ViewRouteHelper;

@Controller
@RequestMapping("")
public class HomeController {

	@GetMapping("/index")
	public String index() {

		return ViewRouteHelper.INDEX;

	}

	// Permite el funcionamiento de los botones (navbar)
	@GetMapping("")
	public RedirectView home() {

		return new RedirectView("/index");

	}

}
