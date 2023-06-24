package com.unla.grupo18.controllers;

//Java
import java.util.List;
import java.util.Set;
import java.time.LocalDateTime;
import java.time.LocalTime;

//OTROS
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

//Annotation
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

//Model and View
import org.springframework.web.servlet.ModelAndView;

import com.unla.grupo18.entities.Aula;
import com.unla.grupo18.entities.DispositivoAlumbrado;
import com.unla.grupo18.entities.Edificio;
import com.unla.grupo18.helpers.ViewRouteHelper;
import com.unla.grupo18.services.implementation.AulaService;
import com.unla.grupo18.services.implementation.DispositivoAlumbradoService;
import com.unla.grupo18.services.implementation.EdificioService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/dispositivo")
public class DispositivoAlumbradoController {

	@Autowired	// <-- Nos permite usar sus metodos sin instanciar la clase
	@Qualifier("dispositivoAlumbradoService")
	private DispositivoAlumbradoService dispositivoAlumbradoService;

	@Autowired
	private EdificioService edificioService;

	@Autowired
	private AulaService aulaService;

	@GetMapping("/alumbrado")
	public String dispositivoAlumbradoInteligente(Model model) {
		model.addAttribute("dispositivoAlumbrado", new DispositivoAlumbrado());
		return ViewRouteHelper.MENU_DISP_ALUMBRADO;
	}

	// CREAR
	@GetMapping("/alumbrado/crear")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public String crearDispositivoAlumbrado(Model model) {
		model.addAttribute("dispositivoAlumbrado", new DispositivoAlumbrado());
		model.addAttribute("edificios", edificioService.obtenerTodosLosEdificios());
		model.addAttribute("aulas", aulaService.obtenerTodasLasAulas());
		return ViewRouteHelper.CREAR_DISP_ALUMBRADO;
	}

	@PostMapping("/alumbrado/nuevoDispositivoAlumbrado")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public ModelAndView nuevoDispositivoAlumbrado(
			@Valid @ModelAttribute("dispositivoAlumbrado") DispositivoAlumbrado dispositivo,
			@RequestParam("edificioId") int edificioId, @RequestParam("aulaId") int aulaId,
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) LocalTime horadeEncendido,
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) LocalTime horadeApagado) {
		Edificio edificio = edificioService.findById(edificioId);
		Aula aula = aulaService.findById(aulaId);

		
		dispositivo.setEdificio(edificio);
		dispositivo.setAula(aula);
		dispositivo.setHoradeEncendido(horadeEncendido);
		dispositivo.setHoradeApagado(horadeApagado);
		dispositivoAlumbradoService.insertOrUpdateDisp(dispositivo);
		ModelAndView mV = new ModelAndView();
		mV.setViewName(ViewRouteHelper.NUEVO_DISP_ALUMBRADO);
		mV.addObject("dispositivoAlumbrado", dispositivo);
		return mV;
	}

	@GetMapping("/alumbrado/cargarAulas")
	@PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_AUDITOR')")
	public ResponseEntity<Set<Aula>> cargarAulas(@RequestParam("edificioId") int edificioId) {
		Edificio edificio = edificioService.getEdificioById(edificioId);
		Set<Aula> aulas = edificio.getAulas();
		return ResponseEntity.ok(aulas);
	}

	// ELIMINAR
	@GetMapping("/alumbrado/eliminar")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public String eliminarDispositivoAlumbrado(Model model) {
		List<DispositivoAlumbrado> dispositivos = dispositivoAlumbradoService.getAllActiveDispositivos();
		if (dispositivos.size() == 0) {
			return ViewRouteHelper.NO_EXISTE_DISPOSITIVO;
		}
		model.addAttribute("dispositivos", dispositivos);
		return ViewRouteHelper.ELIMINAR_DISP_ALUMBRADO;
	}

	@PostMapping("/alumbrado/eliminar/{id}")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public ModelAndView eliminarDispositivoAlumbrado(@PathVariable int id) {
	    ModelAndView mV = new ModelAndView();
	    DispositivoAlumbrado dispositivo = dispositivoAlumbradoService.findById(id);
	    dispositivoAlumbradoService.deleteDispositivo(id);
	    mV.setViewName(ViewRouteHelper.ELIMINAR_DISP_ALUMBRADO); // Cambia la vista de retorno según corresponda
	    mV.addObject("dispositivoAlumbrado", dispositivo);
	    mV.addObject("mensaje", "El dispositivo se dio de baja correctamente");
	    
	    // Obtener la lista actualizada de dispositivos
	    List<DispositivoAlumbrado> dispositivos = dispositivoAlumbradoService.getAllActiveDispositivos();
	    mV.addObject("dispositivos", dispositivos);
	    return mV;
	}

	// MODIFICAR
	@GetMapping("/alumbrado/modificar")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public String modificarDispositivoAlumbrado(Model model) {
		List<DispositivoAlumbrado> dispositivos = dispositivoAlumbradoService.getAllActiveDispositivos();
		if (dispositivos.size() == 0) {
			return ViewRouteHelper.NO_EXISTE_DISPOSITIVO;
		}
		model.addAttribute("dispositivos", dispositivos); // Pasar la lista al modelo
		return ViewRouteHelper.MODIFICAR_DISP_ALUMBRADO;
	}

	@GetMapping("/alumbrado/modificar/{idDispositivo}")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public String modificarDispositivoAlumbrado(@PathVariable int idDispositivo, Model model) {

		// Trae unico Dispositivo x ID
		DispositivoAlumbrado dispositivo = dispositivoAlumbradoService.findById(idDispositivo);

		// Convertimos a modelo el dispositivo, el edificio y las aulas
		model.addAttribute("dispositivoAlumbrado", dispositivo);
		model.addAttribute("edificios", edificioService.obtenerTodosLosEdificios());
		model.addAttribute("aulas", aulaService.obtenerTodasLasAulas());

		return ViewRouteHelper.MODIFICAR_DISP_ALUMBRADO_FORM;

	}

	@PostMapping("/alumbrado/modificar/{idDispositivo}")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public ModelAndView modificarDispositivoAlumbrado(@PathVariable int idDispositivo,
	        @ModelAttribute("dispositivoAlumbrado") DispositivoAlumbrado dispositivo, @RequestParam("edificioId") int edificioId,
	        @RequestParam("aulaId") int aulaId,
	        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) LocalTime horadeEncendido,
	        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) LocalTime horadeApagado) {

	    // Paso 1: Establecer el ID del dispositivo
	    dispositivo.setIdDispositivo(idDispositivo);

	    // Paso 2: Establecer la fecha de modificación del dispositivo
	    dispositivo.setFechaModificacion(LocalDateTime.now());

	    // Paso 3: Obtener el edificio y el aula por sus respectivos IDs
	    Edificio edificio = edificioService.findById(edificioId);
	    Aula aula = aulaService.findById(aulaId);

	    // Paso 4: Establecer el edificio y el aula en el dispositivo
	    dispositivo.setEdificio(edificio);
	    dispositivo.setAula(aula);
	    dispositivo.setMetricas(dispositivoAlumbradoService.findById(idDispositivo).getMetricas());
	    
	    // Paso 5: Aplicar la actualización del dispositivo en la base de datos
	    dispositivoAlumbradoService.insertOrUpdateDisp(dispositivo);

	    // Paso 6: Crear una instancia de ModelAndView para redireccionar a una vista y mostrar el objeto actualizado
		ModelAndView mV = new ModelAndView();
		mV.setViewName(ViewRouteHelper.DISP_ALUMBRADO_MODIFICADO);
		mV.addObject("dispositivoAlumbrado", dispositivo);
		return mV;
	}

	// Lista
	@GetMapping("/alumbrado/lista")
	@PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_AUDITOR')")
	public String listaDeDispositivoAlumbrado(Model model) {
		List<DispositivoAlumbrado> dispositivos = dispositivoAlumbradoService.getAllActiveDispositivos();
		if (dispositivos.size() == 0) {
			return ViewRouteHelper.NO_EXISTE_DISPOSITIVO;
		}
		model.addAttribute("dispositivos", dispositivos); // Pasar la lista al modelo
		return ViewRouteHelper.LISTA_DISP_ALUMBRADO;
	}
	
	
	// REACTIVAR UN DISPOSITIVO DADO DE BAJA
	@GetMapping("/alumbrado/reactivar")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public String reactivarDispositivoAlumbrado(Model model) {
		List<DispositivoAlumbrado> dispositivos = dispositivoAlumbradoService.getAllInactiveDispositivos();
		if (dispositivos.size() == 0) {
			return ViewRouteHelper.NO_EXISTE_DISPOSITIVO;
		}
		model.addAttribute("dispositivos", dispositivos);
		return ViewRouteHelper.REACTIVAR_DISP_ALUMBRADO;
	}
	
	@PostMapping("/alumbrado/reactivar/{id}")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public ModelAndView reactivarDispositivoAlumbrado(@PathVariable int id) {
	    ModelAndView mV = new ModelAndView();
	    DispositivoAlumbrado dispositivo = dispositivoAlumbradoService.findById(id);
	    dispositivoAlumbradoService.reactivarDispositivo(id);
	    mV.setViewName(ViewRouteHelper.REACTIVAR_DISP_ALUMBRADO); // Cambia la vista de retorno según corresponda
	    mV.addObject("dispositivoAlumbrado", dispositivo);
	    mV.addObject("mensaje", "El dispositivo se reactivo correctamente.");
	    
	    // Obtener la lista actualizada de dispositivos
	    List<DispositivoAlumbrado> dispositivos = dispositivoAlumbradoService.getAllInactiveDispositivos();
	    mV.addObject("dispositivos", dispositivos);
	    return mV;
	}
	
	

}