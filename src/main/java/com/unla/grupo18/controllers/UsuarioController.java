package com.unla.grupo18.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.unla.grupo18.helpers.ViewRouteHelper;
import com.unla.grupo18.services.IUsuarioService;

@Controller
@PreAuthorize("hasAuthority('administrador')")
@RequestMapping("/usuarios")
public class UsuarioController {

	@Autowired
	@Qualifier("usuarioService")
	private IUsuarioService usuarioService;

	@GetMapping("/")
	public ModelAndView index() {

		ModelAndView mAV = new ModelAndView(ViewRouteHelper.USUARIO_INDEX);

		mAV.addObject("usuarios", usuarioService.getAll());

		return mAV;
	}

}
