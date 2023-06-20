package com.unla.grupo18.controllers;


import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
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

import com.unla.grupo18.entities.Aula;
import com.unla.grupo18.entities.DispositivoAcondicionarAmbiente;
import com.unla.grupo18.entities.DispositivoAlumbrado;
import com.unla.grupo18.entities.Edificio;
import com.unla.grupo18.helpers.ViewRouteHelper;
import com.unla.grupo18.models.DispositivoAcondicionarAmbienteModel;
import com.unla.grupo18.services.implementation.IDispositivoAcondicionarAmbienteService;

import jakarta.validation.Valid;

import com.unla.grupo18.services.implementation.AulaService;
import com.unla.grupo18.services.implementation.EdificioService;

import com.unla.grupo18.repositories.IDispositivoAcondicionarAmbienteRepository;

@Controller
@RequestMapping("/dispositivo")
public class DispositivoAcondicionarAmbienteController {

	@Autowired	// <-- Nos permite usar sus metodos sin instanciar la clase
	@Qualifier("dispositivoAcondicionarAmbienteService")
	private IDispositivoAcondicionarAmbienteService dispositivoService;

	@Autowired
	private EdificioService edificioService;

	@Autowired
	private AulaService aulaService;


	// ==================== MENU DE DISPOSITIVO ====================  
	@GetMapping("/acondicionar")
	public String dispositivoAcondicionarAmbiente(Model model) {
		model.addAttribute("dispositivoAmbiente", new DispositivoAcondicionarAmbiente());
		//model.addAttribute("edificios", edificioService.obtenerTodosLosEdificios()); // Obtener todos los edificios y
		// pasarlos al modelo
		return ViewRouteHelper.MENU_ACONDICIONAR;
	}

	// ==================== CREAR DISPOSITIVO ==================== 
	@GetMapping("/acondicionar/crear")
	public String crearDispositivoAmbiente(Model model) {
		model.addAttribute("dispositivoAmbiente", new DispositivoAcondicionarAmbiente());
		model.addAttribute("edificios", edificioService.obtenerTodosLosEdificios()); // Obtener todos los edificios y
		// pasarlos al modelo
		model.addAttribute("aulas", aulaService.obtenerTodasLasAulas());
		return ViewRouteHelper.CREAR_AMBIENTE;
	}

	@PostMapping("/acondicionar/nuevoDispositivoAcondicionar")
	public ModelAndView nuevoDispositivoAcondicionar(
			@Valid @ModelAttribute("dispositivoAcondicionar") DispositivoAcondicionarAmbiente dispositivo,
			@RequestParam("edificioId") int edificioId, @RequestParam("aulaId") int aulaId) {
		Edificio edificio = edificioService.findById(edificioId);
		Aula aula = aulaService.findById(aulaId);

		ModelAndView mV = new ModelAndView();
		dispositivo.setEdificio(edificio);
		dispositivo.setAula(aula);
		//dispositivo.getEdificio().getAulas().add(aula); // <<<<<<<<<<<<<<<<<
		dispositivoService.insertOrUpdate(dispositivo);
		//dispositivoAlumbradoService.saveDispositivo(dispositivo);
		mV.setViewName(ViewRouteHelper.NUEVO_AMBIENTE);
		mV.addObject("dispositivoAcondicionar", dispositivo);
		return mV;
	}

	@GetMapping("/acondicionar/cargarAulas")
	public ResponseEntity<Set<Aula>> cargarAulas(@RequestParam("edificioId") int edificioId) {
		Edificio edificio = edificioService.getEdificioById(edificioId);
		Set<Aula> aulas = edificio.getAulas();
		return ResponseEntity.ok(aulas);
	}

	// ==================== TRAER LISTA DISPOSITIVO ====================  
	@GetMapping("/acondicionar/lista")
	public String listaDeDispositivoAlumbrado(Model model) {
		List<DispositivoAcondicionarAmbiente> dispositivos = dispositivoService.getAll();

		if (dispositivos.size() == 0) {
			return ViewRouteHelper.VACIO_AMBIENTE;
		}
		model.addAttribute("dispositivos", dispositivos); // Pasar la lista al modelo
		return ViewRouteHelper.LISTA_ACONDICIONAR;
	}

	// ==================== ACTUALIZAR DISPOSITIVO ==================== 
	// LISTA ELEGIR CUAL ACTUALIZAR
	@GetMapping("/acondicionar/actualizar")
	public String actualizarDispositivoAcondicionarAmbiente(Model model) {
		List<DispositivoAcondicionarAmbiente> dispositivos = dispositivoService.getAll(); 
		if (dispositivos.size() == 0) {
			return ViewRouteHelper.VACIO_AMBIENTE;
		}
		model.addAttribute("dispositivos", dispositivos); // Pasar la lista al modelo
		return ViewRouteHelper.ACTUALIZAR_AMBIENTE;
	}

	// FORMULARIO PARA CAMBIAR NOMBRE DEL DISPOSITIVO
	@GetMapping("/acondicionar/actualizar/{idDispositivo}")
	public String actualizarDispositivoAcondicionarAmbiente(@PathVariable int idDispositivo, Model model) {
		DispositivoAcondicionarAmbiente dispositivo = dispositivoService.findById(idDispositivo); // Obtener el dispositivo por ID
		model.addAttribute("dispositivoAcondicionar", dispositivo); // Pasar el dispositivo al modelo
		model.addAttribute("edificios", edificioService.obtenerTodosLosEdificios()); // Obtener todos los edificios y
		// pasarlos al modelo
		model.addAttribute("aulas", aulaService.obtenerTodasLasAulas());
		return ViewRouteHelper.FORMULARIO_AMBIENTE;
		
	}

	// GUARDAR DISPOSITIVO AL ACTUALIZAR
	//@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping("/acondicionar/actualizar/{idDispositivo}/guardar")
	public ModelAndView guardarSensorAlumbrado(@PathVariable int idDispositivo, @ModelAttribute("dispositivo") DispositivoAcondicionarAmbiente dispositivo) {
		// Guardar los cambios en el dispositivo en la base de datos
		dispositivo.setIdDispositivo(idDispositivo);
		//dispositivo.setFechaModificacion(dispositivoService.findById(idDispositivo).getFechaModificacion());
		dispositivo.setFechaModificacion(dispositivoService.findById(idDispositivo).getFechaCreacion()) ;
		//dispositivo.setMediciones(sensorService.getSensorById(id).getMediciones());
		//dispositivo.setEventos(sensorService.getSensorById(id).getEventos());
		dispositivoService.insertOrUpdate(dispositivo);
		
		ModelAndView mV = new ModelAndView();
		mV.setViewName(ViewRouteHelper.NUEVO_AMBIENTE);
		mV.addObject("dispositivoAcondicionar", dispositivo);
		return mV;
	}

}
