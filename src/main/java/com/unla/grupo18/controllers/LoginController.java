package com.unla.grupo18.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.unla.grupo18.helpers.ViewRouteHelper;


@Controller
public class LoginController {

	@GetMapping("/login")
	public String login(Model model,
						@RequestParam(name="error",required=false) String error,
						@RequestParam(name="logout", required=false) String logout) {
		model.addAttribute("error", error);
		model.addAttribute("logout", logout);
		return ViewRouteHelper.USER_LOGIN;
	}

	
	// Si se cierra la sesion el usuario vuelve al login index
	@GetMapping("/logout")
	public String logout(RedirectAttributes redirectAttrs) {
	    redirectAttrs.addFlashAttribute("message", "Sesión cerrada con éxito!");
	    return "redirect:/login";
	}

	// Si el inicio de sesion es correcto el usuario es direccionado al index.
	@GetMapping("/loginsuccess")
	public String loginCheck() {
		return "redirect:/index";
	}
}
