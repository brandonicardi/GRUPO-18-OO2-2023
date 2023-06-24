package com.unla.grupo18.controllers;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.unla.grupo18.entities.Aula;
import com.unla.grupo18.entities.DispositivoAlumbrado;
import com.unla.grupo18.entities.DispositivoEstacionamiento;
import com.unla.grupo18.entities.Edificio;
import com.unla.grupo18.helpers.ViewRouteHelper;
import com.unla.grupo18.models.DispositivoEstacionamientoModel;
import com.unla.grupo18.models.DispositivoRegadorModel;
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

	// FORMULARIO DE ENVIO DE DATOS PARA QUE EL POST PUEDA IMPACTAR EN LA BD
	@GetMapping("/estacionamiento/formularioEsta")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public ModelAndView formularioEsta() {
		ModelAndView mo = new ModelAndView(ViewRouteHelper.nuevoDispositivo);
		mo.addObject("dispositivoEstacionamiento", new DispositivoEstacionamiento());
		List<Edificio> edificios = edificioService.obtenerTodosLosEdificios();
		mo.addObject("edificios", edificios);

		return mo;
	}
	// CREO NUEVOS DISPOSITIVOS Y IMPACTA EN LA BD

	@PostMapping("/estacionamiento/create")
	public String nuevoDispositivoEstacionamiento(
			@Valid @ModelAttribute("dispositivoEstacionamiento") DispositivoEstacionamiento dispositivo,
			@RequestParam(value = "edificioId", required = false) int edificioId) {

		Edificio edificio = edificioService.findById(edificioId);
		dispositivo.setEdificio(edificio);

		dispositivoEstacionamientoService.saveDispositivo(dispositivo);
		return ViewRouteHelper.MENU_DISP_Estacionamiento;
	}

	// ELIMINAR
	
	@GetMapping("/estacionamiento/eliminar")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public String eliminarDispositivoAlumbrado(Model model) {
		List<DispositivoEstacionamiento> dispositivos = dispositivoEstacionamientoService.getAllDispositivos();
		if (dispositivos.size() == 0) {
			return ViewRouteHelper.NO_EXISTE_DISPOSITIVO_ESTACIONAMIENTO;
		}
		model.addAttribute("dispositivos", dispositivos);
		return ViewRouteHelper.eliminar;
	}

	@GetMapping("/estacionamiento/eliminar/{id}")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public ModelAndView eliminarDispositivoAlumbrado(@PathVariable int id) {
		ModelAndView mV = new ModelAndView();
		DispositivoEstacionamiento dispositivo = dispositivoEstacionamientoService.getDispositivoById(id);
		dispositivoEstacionamientoService.borrarDispositivo(id);
		mV.setViewName(ViewRouteHelper.MENU_DISP_Estacionamiento); // Cambia la vista de retorno según corresponda
		mV.addObject("dispositivoEstacionamiento", dispositivo);
		return mV;
	}
/*
	@GetMapping("/estacionamiento/modificarForm")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public String modificarDispositivoEstacionamiento(Model model) {
		List<DispositivoEstacionamiento> dispositivos = dispositivoEstacionamientoService.getAllDispositivos();

		if (dispositivos.isEmpty()) {
			return ViewRouteHelper.NO_EXISTE_DISPOSITIVO_ESTACIONAMIENTO;
		}
		ModelAndView mo = new ModelAndView(ViewRouteHelper.MODIFICAR_DISP_ESTACIONAMIENTO);
		// List<Edificio> edificios = edificioService.obtenerTodosLosEdificios();
		// mo.addObject("edificios", edificios);
		model.addAttribute("dispositivos", dispositivos); // Agregar el objeto "dispositivo" al modelo

		return ViewRouteHelper.MODIFICAR_DISP_ESTACIONAMIENTO;
	}

	@GetMapping("/estacionamiento/modificarForm/{id}")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public String modificarDispositivoEstacionamiento(@PathVariable int idDispositivo, Model model) {
		DispositivoEstacionamiento dispositivos = dispositivoEstacionamientoService.getDispositivoById(idDispositivo);
		model.addAttribute("dispositivos", dispositivos);

		return ViewRouteHelper.MODIFICAR_DISP_Form_Estacionamiento;
	}*/

//MUESTRO TODOS LOS ESTACIONAMIENTOS TRAYENDO DESDE LA BD
	@GetMapping("/estacionamiento/mostrarEstacionamiento")
	
	public String mostrarDispositivoEstacionamientos(Model model) {
		List<DispositivoEstacionamiento> dispositivos = dispositivoEstacionamientoService.getAllActiveDispositivos();
		model.addAttribute("dispositivos", dispositivos);
		return ViewRouteHelper.Mostrar_Estacionamientos;
	}

//MENU DE OPCIONES 
	@GetMapping("/estacionamiento")
	public String dispositivoEstacionamiento(Model model) {
		model.addAttribute("dispositivoEstacionamiento", new DispositivoEstacionamiento());

		return ViewRouteHelper.MENU_DISP_Estacionamiento;
	}
	
	
	//--------------------------
	// MODIFICAR
		@GetMapping("/estacionamiento/modificarE")
		@PreAuthorize("hasAuthority('ROLE_ADMIN')")
		public String modificarDispositivoEstacionamiento(Model model) {
			List<DispositivoEstacionamiento> dispositivos = dispositivoEstacionamientoService.getAllActiveDispositivos();
			if (dispositivos.size() == 0) {
				return ViewRouteHelper.NO_EXISTE_DISPOSITIVO;
			}
			model.addAttribute("dispositivos", dispositivos); // Pasar la lista al modelo
			return ViewRouteHelper.MODIFICAR_DISP_ESTACIONAMIENTO;
		}

		@GetMapping("/estacionamiento/modificarE/{idDispositivo}")
		@PreAuthorize("hasAuthority('ROLE_ADMIN')")
		public String modificarDispositivoEstacionamiento(@PathVariable int idDispositivo, Model model) {

			// Trae unico Dispositivo x ID
			DispositivoEstacionamiento dispositivo = dispositivoEstacionamientoService.findByIdEstacionamiento(idDispositivo);

			// Convertimos a modelo el dispositivo, el edificio y las aulas
			model.addAttribute("dispositivoEstacionamiento", dispositivo);
			model.addAttribute("edificios", edificioService.obtenerTodosLosEdificios());
			

			return ViewRouteHelper.MODIFICAR_DISP_Form_Estacionamiento;

		}

		@PostMapping("/estacionamiento/modificarE/{idDispositivo}")
		@PreAuthorize("hasAuthority('ROLE_ADMIN')")
		public ModelAndView modificarDispositivoEstacionamiento(@PathVariable int idDispositivo,
		        @ModelAttribute("dispositivoEstacionamiento") DispositivoEstacionamiento dispositivo, @RequestParam("edificioId") int edificioId,
		        @RequestParam("espacio") boolean estado) {

		   
		    dispositivo.setIdDispositivo(idDispositivo);

		  
		    dispositivo.setFechaModificacion(LocalDateTime.now());
		    dispositivo.setEstado(estado);

		   
		    Edificio edificio = edificioService.findById(edificioId);
		    

		    
		    dispositivo.setEdificio(edificio);
		   
		    dispositivo.setMetricas(dispositivoEstacionamientoService.findByIdEstacionamiento(idDispositivo).getMetricas());
		    
		 
		    dispositivoEstacionamientoService.insertOrUpdateDisp(dispositivo);

		 
			ModelAndView mV = new ModelAndView();
			mV.setViewName(ViewRouteHelper.MODIFICAR_DISP_ESTACIONAMIENTO);
			mV.addObject("dispositivoEstacionamiento", dispositivo);
			return mV;
		}
		
}
