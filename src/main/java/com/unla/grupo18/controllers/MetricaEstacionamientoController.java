package com.unla.grupo18.controllers;

import java.time.LocalTime;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.unla.grupo18.entities.Dispositivo;
import com.unla.grupo18.entities.DispositivoAlumbrado;
import com.unla.grupo18.entities.DispositivoEstacionamiento;
import com.unla.grupo18.entities.Evento;
import com.unla.grupo18.entities.MetricaAlumbrado;
import com.unla.grupo18.entities.MetricaEstacionamiento;
import com.unla.grupo18.helpers.ViewRouteHelper;
import com.unla.grupo18.services.implementation.DispositivoEstacionamientoService;
import com.unla.grupo18.services.implementation.EventoService;
import com.unla.grupo18.services.implementation.MetricaDispositivoEstacionamientoService;

@Controller
@RequestMapping("/dispositivo")
public class MetricaEstacionamientoController {
	private final MetricaDispositivoEstacionamientoService metricaService;
	private final DispositivoEstacionamientoService dispositivoService;
	private final EventoService eventoService;
	
	
	public MetricaEstacionamientoController(MetricaDispositivoEstacionamientoService metricaService,
			DispositivoEstacionamientoService dispositivoService, EventoService eventoService) {
	
		this.metricaService = metricaService;
		this.dispositivoService = dispositivoService;
		this.eventoService = eventoService;
	}

	@GetMapping("/estacionamiento/metricas/{id}")
	public String listaMetricasDispositivoEstacionamiento(@PathVariable int id, Model model) {
		DispositivoEstacionamiento dispositivo = dispositivoService.getDispositivoById(id);
		List<MetricaEstacionamiento> metricas = metricaService.getMetricasByDispositivo(dispositivo);
		model.addAttribute("dispositivoEstacionamiento", dispositivo);
		model.addAttribute("metricaDispositivoEstacionamiento", metricas);

		return ViewRouteHelper.METRICAS_ESTACIONAMIENTO;
	}

	@PostMapping("/estacionamiento/actualizar/{id}")
	public String actualizarEventosDelDispositivoEstacionamiento (@PathVariable int id, Model model) {
	    DispositivoEstacionamiento dispositivoEstacionamiento = dispositivoService.findByIdEstacionamiento(id);
	    eventoService.actualizarEventosEstacionamientoDesdeMetricas (dispositivoEstacionamiento);
	    dispositivoService.insertOrUpdateDisp(dispositivoEstacionamiento );
	    model.addAttribute("dispositivoEstacionamiento", dispositivoEstacionamiento );
	    model.addAttribute("eventos", eventoService.getEventosPorDispositivo(dispositivoEstacionamiento));
	    return ViewRouteHelper.EVENTOS_ESTACIONAMIENTO;
	}

		
	
}
