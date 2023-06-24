package com.unla.grupo18.controllers;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.unla.grupo18.entities.DispositivoAcondicionarAmbiente;
import com.unla.grupo18.entities.DispositivoAlumbrado;
import com.unla.grupo18.entities.DispositivoEstacionamiento;
import com.unla.grupo18.entities.DispositivoRegador;
import com.unla.grupo18.entities.Evento;
import com.unla.grupo18.entities.MetricaAcondicionarAmbiente;
import com.unla.grupo18.helpers.ViewRouteHelper;
import com.unla.grupo18.services.IDispositivoAcondicionarAmbienteService;
import com.unla.grupo18.services.IEventoService;
import com.unla.grupo18.services.implementation.DispositivoAlumbradoService;
import com.unla.grupo18.services.implementation.DispositivoEstacionamientoService;
import com.unla.grupo18.services.implementation.DispositivoRegadorService;

@Controller
@RequestMapping("/dispositivo")
public class EventoController {

	@Autowired
	@Qualifier("eventoServicio")
	private IEventoService eventoService;

	// ==================== Acondicionar Ambiente ====================================
	@Autowired
	@Qualifier("dispositivoAcondicionarAmbienteService")
	private IDispositivoAcondicionarAmbienteService dispositivoService;
	// ============================= ALUMBRADO =======================================
	@Autowired
	private DispositivoAlumbradoService dispositivoAlumbradoService;
	// ============================= ESTACIONAMIENTO =================================
	@Autowired
	private DispositivoEstacionamientoService dispositivoEstacionamientoService;
	// ============================= REGADOR =========================================
	@Autowired
	private DispositivoRegadorService dispositivoRegadorService;
	
	// ===============================================================================


	// ==================== TRAER LISTA EVENTOS ====================
	@GetMapping("/acondicionar/eventosLista")
	public String listaDeEventosDispositivoAcondicionar(Model model) {
		List<MetricaAcondicionarAmbiente> metricas = dispositivoService.traerMetricas();
		/*
		 * if (metricas.size() == 0) { return ViewRouteHelper.VACIO_AMBIENTE; }
		 */
		model.addAttribute("metricas", metricas);
		return ViewRouteHelper.EVENTO_AMBIENTE;
	}

	// ==================== ESTOS METODOS SON PARA EL DISPOSITIVO ALUMBRADO ====================

	@GetMapping("/alumbrado/eventos/{id}")
	public String listaEventos(@PathVariable int id, Model model) {
		DispositivoAlumbrado dispositivoAlumbrado = dispositivoAlumbradoService.findById(id);
		List<Evento> eventos = eventoService.getEventosPorDispositivo(dispositivoAlumbrado);
		model.addAttribute("dispositivoAlumbrado", dispositivoAlumbrado);
		model.addAttribute("eventos", eventos);
		if (eventos.size() == 0) {
			return ViewRouteHelper.NO_EXISTEN_EVENTOS;
		}
		return ViewRouteHelper.EVENTOS_ALUMBRADO;
	}

	@GetMapping("/alumbrado/eventos/{id}/buscar")
	public String buscarEventos(@PathVariable int id, @RequestParam("descripcionEvento") String descripcionEvento,
			Model model) {
		DispositivoAlumbrado dispositivoAlumbrado = dispositivoAlumbradoService.findById(id);
		List<Evento> eventos = eventoService.buscarPorDescripcion(descripcionEvento);
		model.addAttribute("dispositivoAlumbrado", dispositivoAlumbrado);
		model.addAttribute("eventos", eventos);
	    if (eventos.isEmpty()) {
	        model.addAttribute("error", "No se encontraron eventos para la busqueda especificada.");
	    }
		return ViewRouteHelper.EVENTOS_ALUMBRADO;
	}

	@PostMapping("/alumbrado/eventos/{id}/buscar")
	public ModelAndView buscarEventos(@PathVariable int id,
			@RequestParam("descripcionEvento") String descripcionEvento) {
		ModelAndView mav = new ModelAndView(ViewRouteHelper.EVENTOS_ALUMBRADO);
		DispositivoAlumbrado dispositivoAlumbrado = dispositivoAlumbradoService.findById(id);
		List<Evento> eventos = eventoService.buscarPorDescripcion(descripcionEvento);
		mav.addObject("dispositivoAlumbrado", dispositivoAlumbrado);
		mav.addObject("eventos", eventos);
		return mav;
	}
	
	@GetMapping("/alumbrado/eventos/{id}/buscarPorFecha")
	public String buscarEventosPorFecha(@PathVariable int id,
	                                    @RequestParam("fechaDeteccion") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaDeteccion,
	                                    Model model) {
	    DispositivoAlumbrado dispositivoAlumbrado = dispositivoAlumbradoService.findById(id);
	    List<Evento> eventos = eventoService.buscarPorFechaMetrica(fechaDeteccion);
	    model.addAttribute("dispositivoAlumbrado", dispositivoAlumbrado);
	    model.addAttribute("eventos", eventos);
	    if (eventos.isEmpty()) {
	        model.addAttribute("error", "No se encontraron eventos para la fecha especificada.");
	    }
	    return ViewRouteHelper.EVENTOS_ALUMBRADO;
	}
	
	@PostMapping("/alumbrado/eventos/{id}/buscarPorFecha")
	public ModelAndView buscarEventosPorFecha(@PathVariable int id,
	                                          @RequestParam("fechaDeteccion") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaDeteccion) {
	    ModelAndView mav = new ModelAndView(ViewRouteHelper.EVENTOS_ALUMBRADO);
	    DispositivoAlumbrado dispositivoAlumbrado = dispositivoAlumbradoService.findById(id);
	    List<Evento> eventos = eventoService.buscarPorFechaMetrica(fechaDeteccion);
	    mav.addObject("dispositivoAlumbrado", dispositivoAlumbrado);
	    mav.addObject("eventos", eventos);
	    return mav;
	}

	// ==================== ESTOS METODOS SON PARA EL DISPOSITIVO ESTACIONAMIENTO ====================

	@GetMapping("/estacionamiento/eventos/{id}")
	public String listaEventosEstacionamiento(@PathVariable int id, Model model) {
		DispositivoEstacionamiento dispositivoEstacionamiento = dispositivoEstacionamientoService.findByIdEstacionamiento(id);
		List<Evento> eventos = eventoService.getEventosPorDispositivo(dispositivoEstacionamiento);
		model.addAttribute("dispositivoEstacionamiento", dispositivoEstacionamiento);
		model.addAttribute("eventos", eventos);
		if (eventos.size() == 0) {
			return ViewRouteHelper.NO_EXISTEN_EVENTOS;
		}
		return ViewRouteHelper.EVENTOS_ESTACIONAMIENTO;
	}

	// ==================== CONTROLLER PARA DISPOSITIVO ACONDICIONAR AMBIENTE  ====================


	@GetMapping("/acondicionar/eventos/{id}")
	public String listaEventosAmbiente(@PathVariable int id, Model model) {
		DispositivoAcondicionarAmbiente dispositivoAcondicionar= dispositivoService.findById(id);
		List<Evento> eventos = eventoService.getEventosPorDispositivo(dispositivoAcondicionar);
		model.addAttribute("dispositivoAmbiente", dispositivoAcondicionar);
		model.addAttribute("eventos", eventos);
		if (eventos.size() == 0) {
			return ViewRouteHelper.VACIO_EVENTOS_AMBIENTE;
		}
		return ViewRouteHelper.EVENTO_AMBIENTE;
	}
	
	// =============== METODOS PARA DISPOSITIVO REGADOR ====================
	
		@GetMapping("/regador/eventos/{id}")
		public String listaEventosRegador(@PathVariable int id, Model model) {
			DispositivoRegador dispositivoRegador = dispositivoRegadorService.findDispositivoById(id);
			List<Evento> eventos = eventoService.getEventosPorDispositivo(dispositivoRegador);
			model.addAttribute("dispositivoRegador", dispositivoRegador);
			model.addAttribute("eventos", eventos);
			if (eventos.size() == 0) {
				return ViewRouteHelper.NO_EXISTEN_EVENTOS_REGADOR;
			}
			return ViewRouteHelper.EVENTOS_REGADOR;
		}

		
		@GetMapping("/regador/eventos/{id}/buscar")
		public String buscarEventosRegador(@PathVariable int id, @RequestParam("descripcionEvento") String descripcionEvento,
				Model model) {
			DispositivoRegador dispositivoRegador  = dispositivoRegadorService.findDispositivoById(id);
			List<Evento> eventos = eventoService.buscarPorDescripcion(descripcionEvento);
			model.addAttribute("dispositivoRegador ", dispositivoRegador );
			model.addAttribute("eventos", eventos);
			if (eventos.isEmpty()) {
				return ViewRouteHelper.NO_EXISTEN_EVENTOS;
			}
			return ViewRouteHelper.EVENTOS_REGADOR;
		}

		@PostMapping("/regador/eventos/{id}/buscar")
		public ModelAndView buscarEventosRegador(@PathVariable int id,
				@RequestParam("descripcionEvento") String descripcionEvento) {
			ModelAndView mav = new ModelAndView(ViewRouteHelper.EVENTOS_REGADOR);
			DispositivoRegador  dispositivoRegador  = dispositivoRegadorService.findDispositivoById(id);
			List<Evento> eventos = eventoService.buscarPorDescripcion(descripcionEvento);
			mav.addObject("dispositivoRegador ", dispositivoRegador);
			mav.addObject("eventos", eventos);
			return mav;
		}
}
