package com.unla.grupo18.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.unla.grupo18.services.implementation.DispositivoService;
import com.unla.grupo18.helpers.ViewRouteHelper;

@Controller
@PreAuthorize("hasAuthority('administrador')")
@RequestMapping("/admin")
public class DispositivoController {

	@Qualifier("dispositivoService")
	@Autowired
	private DispositivoService dispositivoService;

	@GetMapping("dispositivos")
	public ModelAndView index() {

		ModelAndView mAV = new ModelAndView(ViewRouteHelper.ADMIN_DISPOSITIVO);
		mAV.addObject("dispositivos", dispositivoService.getAll());
		return mAV;
	}

}