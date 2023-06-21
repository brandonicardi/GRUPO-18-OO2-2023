package com.unla.grupo18.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.unla.grupo18.entities.DispositivoAcondicionarAmbiente;
import com.unla.grupo18.entities.Evento;
import com.unla.grupo18.entities.MetricaAcondicionarAmbiente;
import com.unla.grupo18.helpers.ViewRouteHelper;
import com.unla.grupo18.models.EventoModel;
import com.unla.grupo18.services.IDispositivoAcondicionarAmbienteService;
import com.unla.grupo18.services.IEventoService;

@Controller
@RequestMapping("/evento")
public class EventoController {

	@Autowired
	@Qualifier("eventoServicio")
	private IEventoService eventoService;

	// ==================== Acondicionar Ambiente
	// Permite gracias a Spring poder usar los metodos de repositirio sin instancia una clase de esta
	@Autowired	// <-- Nos permite usar sus metodos sin instanciar la clase
	@Qualifier("dispositivoAcondicionarAmbienteService")
	private IDispositivoAcondicionarAmbienteService dispositivoService;
	// ==============================================================================================
	
	// IMPLEMENTAR VIEW ROUTE HELPERS Y SUS DEBIDOS TEMPLATES

	/*
	@GetMapping("/")
    public ModelAndView index(){
        ModelAndView mAV = new ModelAndView(ViewRouteHelper.EVENTO_INDEX);
        mAV.addObject("eventos",eventoService.getAll());
        mAV.addObject("evento", new Evento());
        return mAV;
    }

	 @PostMapping("/")
	    public RedirectView create(@ModelAttribute("evento") EventoModel eventoModel){
	        eventoService.insertOrUpdate(eventoModel);
	        return new RedirectView(ViewRouteHelper.EVENTO_ROOT);
	    }
	 */

	// ==================== TRAER LISTA EVENTOS  ====================  
	@GetMapping("/acondicionar/eventosLista")
	public String listaDeEventosDispositivoAcondicionar(Model model) {
		List<MetricaAcondicionarAmbiente> metricas = dispositivoService.traerMetricas();
/*
		if (metricas.size() == 0) {
			return ViewRouteHelper.VACIO_AMBIENTE;
		}*/
		model.addAttribute("metricas", metricas);
		return ViewRouteHelper.EVENTO_AMBIENTE;
	}

}
