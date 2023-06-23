package com.unla.grupo18.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.unla.grupo18.services.implementation.DispositivoAlumbradoService;
import com.unla.grupo18.entities.DispositivoAlumbrado;
import com.unla.grupo18.helpers.ViewRouteHelper;

@Controller
@RequestMapping("/")
public class DispositivoController {

	/*
	 * private final DispositivoAlumbradoService dispositivoService;
	 * 
	 * public DispositivoController(DispositivoAlumbradoService dispositivoService)
	 * { this.dispositivoService = dispositivoService; }
	 * 
	 * @GetMapping("/dispositivo") public String deviceList(Model model) {
	 * List<DispositivoAlumbrado> dispositivos =
	 * dispositivoService.getAllActiveDispositivos();
	 * model.addAttribute("dispositivos", dispositivos); return
	 * ViewRouteHelper.LISTA_DISP_ALUMBRADO; }
	 */

}