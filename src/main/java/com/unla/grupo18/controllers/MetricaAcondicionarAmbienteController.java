package com.unla.grupo18.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.unla.grupo18.entities.DispositivoAcondicionarAmbiente;

import com.unla.grupo18.entities.MetricaAcondicionarAmbiente;

import com.unla.grupo18.helpers.ViewRouteHelper;
import com.unla.grupo18.services.IDispositivoAcondicionarAmbienteService;
import com.unla.grupo18.services.IMetricaDispositivoAcondicionarAmbienteService;
import com.unla.grupo18.services.implementation.DispositivoAcondicionarAmbienteService;
import com.unla.grupo18.services.implementation.EventoService;
import com.unla.grupo18.services.implementation.MetricaDispositivoAcondicionarAmbiente;



@Controller
@RequestMapping("/dispositivo/acondicionar")
public class MetricaAcondicionarAmbienteController {
	
	// -------------- Traemos de la capa de Services --------------  
	//	EVENTO - Metrica - Dispositivo
	
	@Autowired
	private EventoService eventoService;
	
	@Qualifier("metricaDispositivoAcondicionarAmbiente")
	private IMetricaDispositivoAcondicionarAmbienteService metricaService;

	@Autowired	
	@Qualifier("dispositivoAcondicionarAmbienteService")
	private IDispositivoAcondicionarAmbienteService dispositivoService;
	
	/*
	private final MetricaDispositivoAcondicionarAmbiente metricaService;
	private final DispositivoAcondicionarAmbienteService dispositivoService;
	private final EventoService eventoService;
*/
	public MetricaAcondicionarAmbienteController(MetricaDispositivoAcondicionarAmbiente metricaService,
			DispositivoAcondicionarAmbienteService dispositivoService, EventoService eventoService) {
		this.metricaService = metricaService;
		this.dispositivoService = dispositivoService;
		this.eventoService = eventoService;
	}
	
	// Metodo para obtener metrica a travez de ID
	@GetMapping("/metricas/{id}")
	public String listaMetricasDispositivoAcondicionarAmbiente(@PathVariable int id, Model model) {
		DispositivoAcondicionarAmbiente dispositivo = dispositivoService.findById(id);
		List<MetricaAcondicionarAmbiente> metricas = metricaService.traerMetricasDeUnDispositivo(dispositivo);
		model.addAttribute("dispositivoAcondicionarAmbiente", dispositivo);
		model.addAttribute("metricaDispositivoAcondicionarAmbiente", metricas);
		
		if (metricas.size() == 0) {
			return ViewRouteHelper.VACIO_METRICAS;	// Redireccionamos a vista para comunicar que no existe metrica alguna para el dispotivo elegido
		}
		return ViewRouteHelper.METRICAS_AMBIENTE;	// Redirecciona a la vista de las metricas del Dispositivo Elegido
	}
	
	// Metodo para Generar el evento del dispositivo a traves de las metricas
	// El evento lo generamos a traves de un boton en la vista, para simular el comportamiento del dispositivo que tuviese en la vida real
	// Ingresa al sistema a traves de su ID
	@PostMapping("/actualizar/{id}")
	public String actualizarEventosDelDispositivo(@PathVariable int id, Model model) {
		DispositivoAcondicionarAmbiente dispositivoAcondicionar = dispositivoService.findById(id);
		eventoService.actualizarEventosAcondicionarAmbienteDesdeMetricas(dispositivoAcondicionar);
	    dispositivoService.insertOrUpdate(dispositivoAcondicionar);
	    model.addAttribute("dispositivoAcondicionar", dispositivoAcondicionar);
	    model.addAttribute("eventos", eventoService.getEventosPorDispositivo(dispositivoAcondicionar));
	    return ViewRouteHelper.EVENTO_AMBIENTE;
	}
	 
	
	

}
