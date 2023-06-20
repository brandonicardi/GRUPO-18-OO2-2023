package com.unla.grupo18.controllers;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.unla.grupo18.entities.Aula;
import com.unla.grupo18.entities.DispositivoAlumbrado;
import com.unla.grupo18.entities.Edificio;
import com.unla.grupo18.helpers.ViewRouteHelper;
import com.unla.grupo18.services.AulaService;
import com.unla.grupo18.services.DispositivoAlumbradoService;
import com.unla.grupo18.services.EdificioService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/dispositivo")
public class DispositivoAlumbradoController {

	@Autowired
	private DispositivoAlumbradoService dispositivoAlumbradoService;

	@Autowired
	private EdificioService edificioService;

	@Autowired
	private AulaService aulaService;

	@GetMapping("/alumbrado")
	public String dispositivoAlumbradoInteligente(Model model) {
		model.addAttribute("dispositivoAlumbrado", new DispositivoAlumbrado());
		//model.addAttribute("edificios", edificioService.obtenerTodosLosEdificios()); // Obtener todos los edificios y
																						// pasarlos al modelo
		return ViewRouteHelper.MENU_DISP_ALUMBRADO;
	}

	// CREAR
	@GetMapping("/alumbrado/crear")
	public String crearDispositivoAlumbrado(Model model) {
		model.addAttribute("dispositivoAlumbrado", new DispositivoAlumbrado());
		model.addAttribute("edificios", edificioService.obtenerTodosLosEdificios()); // Obtener todos los edificios y
																						// pasarlos al modelo
		model.addAttribute("aulas", aulaService.obtenerTodasLasAulas());
		return ViewRouteHelper.CREAR_DISP_ALUMBRADO;
	}

	@PostMapping("/alumbrado/nuevoDispositivoAlumbrado")
	public ModelAndView nuevoDispositivoAlumbrado(
			@Valid @ModelAttribute("dispositivoAlumbrado") DispositivoAlumbrado dispositivo,
			@RequestParam("edificioId") int edificioId, @RequestParam("aulaId") int aulaId) {
		Edificio edificio = edificioService.findById(edificioId);
		Aula aula = aulaService.findById(aulaId);

		ModelAndView mV = new ModelAndView();
		dispositivo.setEdificio(edificio);
		dispositivo.setAula(aula);
		dispositivoAlumbradoService.saveDispositivo(dispositivo);
		mV.setViewName(ViewRouteHelper.NUEVO_DISP_ALUMBRADO);
		mV.addObject("dispositivoAlumbrado", dispositivo);
		return mV;
	}

	@GetMapping("/alumbrado/cargarAulas")
	public ResponseEntity<Set<Aula>> cargarAulas(@RequestParam("edificioId") int edificioId) {
		Edificio edificio = edificioService.getEdificioById(edificioId);
		Set<Aula> aulas = edificio.getAulas();
		return ResponseEntity.ok(aulas);
	}

	// ELIMINAR
	@GetMapping("/alumbrado/eliminar")
	public String eliminarDispositivoAlumbrado(Model model) {
		List<DispositivoAlumbrado> dispositivos = dispositivoAlumbradoService.getAllActiveDispositivos(); // Obtener la
																											// lista de
		// dispositivos
		if (dispositivos.size() == 0) {
			return ViewRouteHelper.NO_EXISTE_DISPOSITIVO;
		}
		model.addAttribute("dispositivos", dispositivos); // Pasar la lista al modelo
		return ViewRouteHelper.ELIMINAR_DISP_ALUMBRADO;
	}

	@GetMapping("/alumbrado/eliminar/{id}")
	public ModelAndView eliminarDispositivoAlumbrado(@PathVariable int id) {
		ModelAndView mV = new ModelAndView();
		DispositivoAlumbrado dispositivo = dispositivoAlumbradoService.getDispositivoById(id);
		dispositivoAlumbradoService.deleteDispositivo(id);
		mV.setViewName(ViewRouteHelper.MENU_DISP_ALUMBRADO); // Cambia la vista de retorno según corresponda
		mV.addObject("dispositivoAlumbrado", dispositivo);
		return mV;
	}

	// MODIFICAR
	@GetMapping("/alumbrado/modificar")
	public String modificarDispositivoAlumbrado(Model model) {
		List<DispositivoAlumbrado> dispositivos = dispositivoAlumbradoService.getAllActiveDispositivos(); // Obtener la
																											// lista de
		// dispositivos
		if (dispositivos.size() == 0) {
			return ViewRouteHelper.NO_EXISTE_DISPOSITIVO;
		}
		model.addAttribute("dispositivos", dispositivos); // Pasar la lista al modelo
		return ViewRouteHelper.MODIFICAR_DISP_ALUMBRADO;
	}

	// MODIFICAR - Formulario
	@GetMapping("/alumbrado/modificar/{id}")
	public String modificarDispositivoAlumbrado(@PathVariable int id, Model model) {
		DispositivoAlumbrado dispositivo = dispositivoAlumbradoService.getDispositivoById(id); // Obtener el dispositivo
																								// por ID
		model.addAttribute("dispositivoAlumbrado", dispositivo); // Pasar el dispositivo al modelo
		return ViewRouteHelper.MODIFICAR_DISP_ALUMBRADO_FORM;
	}

	// Lista plana
	@GetMapping("/alumbrado/lista")
	public String listaDeDispositivoAlumbrado(Model model) {
		List<DispositivoAlumbrado> dispositivos = dispositivoAlumbradoService.getAllActiveDispositivos(); // obtener //
																											// activos
		if (dispositivos.size() == 0) {
			return ViewRouteHelper.NO_EXISTE_DISPOSITIVO;
		}
		model.addAttribute("dispositivos", dispositivos); // Pasar la lista al modelo
		return ViewRouteHelper.LISTA_DISP_ALUMBRADO;
	}

}