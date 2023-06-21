package com.unla.grupo18.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.unla.grupo18.entities.Aula;
import com.unla.grupo18.entities.DispositivoAlumbrado;
import com.unla.grupo18.entities.DispositivoEstacionamiento;
import com.unla.grupo18.entities.Edificio;
import com.unla.grupo18.helpers.ViewRouteHelper;
import com.unla.grupo18.services.implementation.DispositivoAlumbradoService;
import com.unla.grupo18.services.implementation.DispositivoEstacionamientoService;
import com.unla.grupo18.services.implementation.EdificioService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/dispositivo")
public class DispositivoEstacionamientoController {
	@Autowired
	private DispositivoEstacionamientoService dispositivoEstacionamientoService;

	@Autowired
	private EdificioService edificioService;

	@GetMapping("/estacionamiento/formularioEsta")
	public ModelAndView formularioEsta() {
		ModelAndView mo = new ModelAndView(ViewRouteHelper.nuevoDispositivo);
		mo.addObject("dispositivoEstacionamiento", new DispositivoEstacionamiento());
		List<Edificio> edificios = edificioService.obtenerTodosLosEdificios();
		mo.addObject("edificios", edificios);
		
		return mo;
	}
	
	@PostMapping("/estacionamiento/create")
	public RedirectView nuevoDispositivoEstacionamiento(
	        @Valid @ModelAttribute("dispositivoEstacionamiento") DispositivoEstacionamiento dispositivo,
	        @RequestParam(value = "edificioId", required = false) int edificioId) {
	     
	        
	        Edificio edificio = edificioService.findById(edificioId);
	        dispositivo.setEdificio(edificio);
	    

	    dispositivoEstacionamientoService.saveDispositivo(dispositivo);
	    return new RedirectView(ViewRouteHelper.Mostrar_Esta);
	}

/*
	@PostMapping("/estacionamiento/create")
	public RedirectView nuevoDispositivoEstacionamiento(
			@Valid @ModelAttribute("dispositivoEstacionamiento") DispositivoEstacionamiento dispositivo,
			@RequestParam("edificioId") int edificioId) {
		Edificio edificio = edificioService.findById(edificioId);

		ModelAndView mV = new ModelAndView();
		dispositivo.setEdificio(edificio);
		
		
	
		dispositivoEstacionamientoService.saveDispositivo(dispositivo);
		return new RedirectView(ViewRouteHelper.Mostrar_Esta);
	}*/

	@PostMapping("/estacionamiento/mostrarEsta")
	public ModelAndView nuevoEstaciona(
			@ModelAttribute("dispositivoEstacionamiento") DispositivoEstacionamiento dispositivoEstacionamiento) {
		ModelAndView mo = new ModelAndView();
		mo.setViewName(ViewRouteHelper.Mostrar_Esta);
		mo.addObject("dispositivoEstacionamiento", dispositivoEstacionamiento);
		return mo;
	}

	@GetMapping("/estacionamiento")
	public String dispositivoAlumbradoInteligente(Model model) {
		model.addAttribute("dispositivoAlumbrado", new DispositivoEstacionamiento());
		model.addAttribute("edificios", edificioService.obtenerTodosLosEdificios()); // Obtener todos los edificios y
																						// pasarlos al modelo
		return ViewRouteHelper.MENU_DISP_Estacionamiento;
	}
}
