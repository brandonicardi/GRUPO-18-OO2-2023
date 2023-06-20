package com.unla.grupo18.controllers;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.unla.grupo18.entities.Edificio;
import com.unla.grupo18.helpers.ViewRouteHelper;
import com.unla.grupo18.models.DispositivoRegadorModel;
import com.unla.grupo18.services.implementation.DispositivoRegadorService;
import com.unla.grupo18.services.implementation.EdificioService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/dispositivo/regador")
public class DispositivoRegadorController {

	@Autowired
	private DispositivoRegadorService dispositivoRegadorService;
	
	@Autowired
	private EdificioService edificioService;
	
	@GetMapping("")
	public String dispositivoEspacioVerde (Model model) {
		model.addAttribute("dispositivoRegador", new DispositivoRegadorModel());
		model.addAttribute("edificios", edificioService.obtenerTodosLosEdificios());
		
		return ViewRouteHelper.MENU_DISP_REGADOR;
	}
	
	
	//Create
	@GetMapping("/crear")
	public String crearDispositivoRegador(Model model) {
		model.addAttribute("dispositivoRegador", new DispositivoRegadorModel());
		model.addAttribute("edificios", edificioService.obtenerTodosLosEdificios()); //Para poder ver la lista de edificios
		return ViewRouteHelper.CREAR_DISP_REGADOR;
	}
	
	@PostMapping("/nuevoDispositivoRegador")
	public ModelAndView nuevoDispositivoRegador(@Valid @ModelAttribute("dispositivoRegador") DispositivoRegadorModel dispositivo
			,@RequestParam("edificio.id") int edificioId
			) {
		Edificio edificio = edificioService.findById(edificioId);
		
		ModelAndView mV = new ModelAndView();
		dispositivo.setEdificio(edificio);
		dispositivoRegadorService.insertOrUpdate(dispositivo);
		mV.setViewName(ViewRouteHelper.NUEVO_DISP_REGADOR);
		mV.addObject("dispositivoRegador",dispositivo);
		
		return mV;
	}
	
	
	//Read
	@GetMapping("/lista")
	public String listaDeDispositivoRegador(Model model) {
		List<DispositivoRegadorModel> dispositivos = dispositivoRegadorService.getAllActive();
		
		if (dispositivos.size() == 0) {
			return ViewRouteHelper.NO_EXISTE_DISPOSITIVO_REGADOR;
		}
		
		model.addAttribute("dispositivos", dispositivos);
		
		return ViewRouteHelper.LISTA_DISP_REGADOR;
	}
	
	
	//Update
	@GetMapping("/modificar")
	public String modificarDispositivoRegador(Model model) {
		List<DispositivoRegadorModel> dispositivos = dispositivoRegadorService.getAllActive();
		
		if (dispositivos.size() == 0) {
			return ViewRouteHelper.NO_EXISTE_DISPOSITIVO_REGADOR;
		}
		
		model.addAttribute("dispositivos", dispositivos);
		
		return ViewRouteHelper.MODIFICAR_DISP_REGADOR;
	}
	
	@GetMapping("modificar/{id}")
	public String modificarDispositivoRegador(@PathVariable int id, Model model) {
		DispositivoRegadorModel dispositivo = dispositivoRegadorService.getDispositivoById(id);
		model.addAttribute("dispositivoRegador", dispositivo);
		
		return ViewRouteHelper.MODIFICAR_DISP_REGADOR_FORM;
	}
}
