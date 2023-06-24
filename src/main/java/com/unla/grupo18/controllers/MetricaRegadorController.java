package com.unla.grupo18.controllers;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.unla.grupo18.entities.DispositivoAlumbrado;
import com.unla.grupo18.entities.DispositivoRegador;
import com.unla.grupo18.entities.MetricaRegador;
import com.unla.grupo18.helpers.ViewRouteHelper;
import com.unla.grupo18.models.DispositivoRegadorModel;
import com.unla.grupo18.services.implementation.DispositivoRegadorService;
import com.unla.grupo18.services.implementation.EventoService;
import com.unla.grupo18.services.implementation.MetricaRegadorService;

@Controller
@RequestMapping("/dispositivo/regador")
public class MetricaRegadorController {
	
	private final MetricaRegadorService metricaService;
	private final DispositivoRegadorService dispositivoService;
	private final EventoService eventoService;
	private ModelMapper modelMapper = new ModelMapper();
	
	public MetricaRegadorController(MetricaRegadorService metricaService, DispositivoRegadorService dispositivoService,
			EventoService eventoService) {
		this.metricaService = metricaService;
		this.dispositivoService = dispositivoService;
		this.eventoService = eventoService;
	}
	
	@GetMapping("/metricas/{id}")
	public String listaMetricasDispositivoRegador(@PathVariable int id, Model model) {
		DispositivoRegadorModel dispositivo = dispositivoService.getDispositivoById(id);
		List<MetricaRegador> metricas = metricaService.getMetricasByDispositivo(dispositivo);
		model.addAttribute("dispositivoRegador", dispositivo);
		model.addAttribute("metricaDispositivoRegador", metricas);
		if (metricas.size() == 0) {
			return ViewRouteHelper.NO_EXISTEN_METRICAS_REGADOR;
		}
		return ViewRouteHelper.METRICAS_REGADOR;
	}
	
	@PostMapping("/actualizar/{id}")
	public String actualizarEventosDelDispositivo(@PathVariable int id, Model model) {
	    DispositivoRegadorModel dispositivoRegadorModel = dispositivoService.getDispositivoById(id);
	    DispositivoRegador dispositivoRegador = modelMapper.map(dispositivoRegadorModel, DispositivoRegador.class);
	    eventoService.actualizarEventosRegadorDesdeMetricas(dispositivoRegador);
	    //System.out.println("Test 1");
	    //dispositivoService.insertOrUpdate(dispositivoRegadorModel);
	    //System.out.println("Test 2");
	    model.addAttribute("dispositivoRegador", dispositivoRegador);
	    model.addAttribute("eventos", eventoService.getEventosPorDispositivo(dispositivoRegador));
	    return ViewRouteHelper.EVENTOS_REGADOR;
	}
	
	
}
