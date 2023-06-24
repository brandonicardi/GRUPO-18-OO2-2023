package com.unla.grupo18.controllers;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.unla.grupo18.entities.DispositivoAcondicionarAmbiente;
import com.unla.grupo18.entities.DispositivoAlumbrado;
import com.unla.grupo18.entities.DispositivoEstacionamiento;
import com.unla.grupo18.entities.Evento;
import com.unla.grupo18.entities.MetricaAcondicionarAmbiente;
import com.unla.grupo18.helpers.ViewRouteHelper;
import com.unla.grupo18.models.EventoModel;
import com.unla.grupo18.services.IDispositivoAcondicionarAmbienteService;
import com.unla.grupo18.services.IEventoService;
import com.unla.grupo18.services.implementation.DispositivoAlumbradoService;
import com.unla.grupo18.services.implementation.DispositivoEstacionamientoService;

@Controller
@RequestMapping("/dispositivo")
public class EventoController {

	@Autowired
	@Qualifier("eventoServicio")
	private IEventoService eventoService;

	// ==================== Acondicionar Ambiente

	
	@Autowired // <-- Nos permite usar sus metodos sin instanciar la clase
	@Qualifier("dispositivoAcondicionarAmbienteService")
	private IDispositivoAcondicionarAmbienteService dispositivoService;
	// ==============================================================================================

	// ==================== Acondicionar Ambiente

	@Autowired
	private DispositivoAlumbradoService dispositivoAlumbradoService;
	//-----------------------------ESTACIONAMIENTO-----------------------------------------------
	@Autowired
	private DispositivoEstacionamientoService dispositivoEstacionamientoService;
	// ==============================================================================================


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
}
