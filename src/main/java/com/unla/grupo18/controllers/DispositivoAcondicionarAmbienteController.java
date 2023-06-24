 package com.unla.grupo18.controllers;

// Java
import java.util.List;
import java.util.Set;
import java.time.LocalDateTime;
// OTROS
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

// Annotation
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

// Model and View
import org.springframework.web.servlet.ModelAndView;

// Entities
import com.unla.grupo18.entities.Aula;
import com.unla.grupo18.entities.DispositivoAcondicionarAmbiente;
import com.unla.grupo18.entities.Edificio;
import com.unla.grupo18.helpers.ViewRouteHelper;
import com.unla.grupo18.services.IDispositivoAcondicionarAmbienteService;
import com.unla.grupo18.services.implementation.AulaService;
import com.unla.grupo18.services.implementation.EdificioService;

// Valid
import jakarta.validation.Valid;


@Controller
@RequestMapping("/dispositivo")
public class DispositivoAcondicionarAmbienteController {

	// Permite gracias a Spring poder usar los metodos de repositirio sin instancia una clase de esta
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
	// >> Vista Crear Dispositivo
	@GetMapping("/acondicionar/crear")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public String crearDispositivoAmbiente(Model model) {
		model.addAttribute("dispositivoAmbiente", new DispositivoAcondicionarAmbiente());
		model.addAttribute("edificios", edificioService.obtenerTodosLosEdificios()); // Obtener todos los edificios y
		// pasarlos al modelo
		model.addAttribute("aulas", aulaService.obtenerTodasLasAulas());
		return ViewRouteHelper.CREAR_AMBIENTE;
	}

	// >> 
	@PostMapping("/acondicionar/nuevoDispositivoAcondicionar")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public ModelAndView nuevoDispositivoAcondicionar(@Valid @ModelAttribute("dispositivoAmbiente") DispositivoAcondicionarAmbiente dispositivo, @RequestParam("edificioId") int edificioId, @RequestParam("aulaId") int aulaId) {

		// Instanciamos Edificio y Aula, buscandolos de la BD por su respectivo ID
		Edificio edificio = edificioService.findById(edificioId);
		Aula aula = aulaService.findById(aulaId);

		// Seteamos Edificio y Aula
		
		dispositivo.setEdificio(edificio);
		dispositivo.setAula(aula);

		// Guarda en BD el Dispositivo con sus nuevos parametros
		dispositivoService.insertOrUpdate(dispositivo);

		ModelAndView mV = new ModelAndView();

		// Redireccionamos y mostramos los datos del nuevo Dispositivo creado
		mV.setViewName(ViewRouteHelper.NUEVO_AMBIENTE);
		mV.addObject("dispositivoAcondicionar", dispositivo);

		return mV;
	}	

	// >> Metodo para controlar la generacion de aulas dentro del http, se re utiliza en la vista 
	// Se trae Edificio x id desde la BD, se le agregan sus respectivas Aulas.
	@GetMapping("/acondicionar/cargarAulas")
	@PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_AUDITOR')")
	public ResponseEntity<Set<Aula>> cargarAulas(@RequestParam("edificioId") int edificioId) {
		Edificio edificio = edificioService.getEdificioById(edificioId);
		Set<Aula> aulas = edificio.getAulas();
		return ResponseEntity.ok(aulas);
	}

	// ==================== TRAER LISTA DISPOSITIVO ====================  
	// >> HTML ERROR SI NO HAY DISPOSITIVOS CREADOS
	// >> DIRECCIONA A VISTA CON LA LISTA DE OBJETOS DESEADA
	@GetMapping("/acondicionar/lista")
	@PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_AUDITOR')")
	public String listaDeDispositivoAcondicionar(Model model) {
		List<DispositivoAcondicionarAmbiente> dispositivos = dispositivoService.getAll();

		if (dispositivos.size() == 0) {
			return ViewRouteHelper.VACIO_AMBIENTE;
		}
		model.addAttribute("dispositivos", dispositivos);
		return ViewRouteHelper.LISTA_ACONDICIONAR;
	}

	// ==================== ACTUALIZAR DISPOSITIVO ==================== 
	// >> MUESTRA LISTA DE DISP. Y PERMITE ELEGIR CUAL ACTUALIZAR
	@GetMapping("/acondicionar/actualizar")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public String actualizarDispositivoAcondicionarAmbiente(Model model) {
		// Trae Lista
		List<DispositivoAcondicionarAmbiente> dispositivos = dispositivoService.getAll();

		// Direcciona si no hay ninguno creado
		if (dispositivos.size() == 0) {
			return ViewRouteHelper.VACIO_AMBIENTE;
		}

		// Modelamos la lista y direccionamos a vista para actualizar disp.
		model.addAttribute("dispositivos", dispositivos); 
		return ViewRouteHelper.ACTUALIZAR_AMBIENTE;
	}

	// >> FORMULARIO PARA ACTUALIZAR DISPOSITIVO, SETEA NOMBRE, EDIFICIO Y AULA
	@GetMapping("/acondicionar/actualizar/{idDispositivo}")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public String actualizarDispositivoAcondicionarAmbiente(@PathVariable int idDispositivo, Model model) {

		// Trae unico Dispositivo x ID 
		DispositivoAcondicionarAmbiente dispositivo = dispositivoService.findById(idDispositivo);

		// Convertimos a modelo el dispositivo, el edificio y las aulas
		model.addAttribute("dispositivoAcondicionar", dispositivo); 
		model.addAttribute("edificios", edificioService.obtenerTodosLosEdificios()); 
		model.addAttribute("aulas", aulaService.obtenerTodasLasAulas());

		return ViewRouteHelper.FORMULARIO_AMBIENTE;

	}

	// >> GUARDAR DISPOSITIVO AL ACTUALIZAR SUS NUEVOS ATRIBUTOS
	@PostMapping("/acondicionar/actualizar/{idDispositivo}/guardar")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public ModelAndView guardarDispositivoAcondicionar(@PathVariable int idDispositivo, @ModelAttribute("dispositivo") DispositivoAcondicionarAmbiente dispositivo,@RequestParam("edificioId") int edificioId, @RequestParam("aulaId") int aulaId ) {

		// Al dispositivo que nos devuelve la vista, seteamos su respectiva ID
		dispositivo.setIdDispositivo(idDispositivo);
		// Seteamos el nombre del Dispositivo
		//dispositivo.setNombreDispositivo(dispositivo.getNombreDispositivo());
		dispositivo.setFechaModificacion(LocalDateTime.now());
		// Traemos Edificio y Aulas por ID y los guardamos en variable auxiliar
		Edificio edificio = edificioService.findById(edificioId);
		Aula aula = aulaService.findById(aulaId);

		// Seteamos Edificio y Aula en Dispositivo
		dispositivo.setEdificio(edificio);
		dispositivo.setAula(aula);

		// Aplicamos el Update en la BD con el dispositivo seteado con los nuevos parametros
		dispositivoService.insertOrUpdate(dispositivo);

		// Instanciamos MaV para redireccionar vista y mostrar el nuevo objeto creado y agregarlo 
		ModelAndView mV = new ModelAndView();
		mV.setViewName(ViewRouteHelper.NUEVO_AMBIENTE);
		mV.addObject("dispositivoAcondicionar", dispositivo);

		return mV;
	}

	// ==================== APLICAR BAJA DE DISPOSITIVO ====================  

	// >> MUESTRA LISTA DE DISP. Y PERMITE ELEGIR CUAL ACTUALIZAR
	@GetMapping("/acondicionar/eliminar")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public String eliminarDispositivoAcondicionarAmbiente(Model model) {
		// Trae Lista
		List<DispositivoAcondicionarAmbiente> dispositivos = dispositivoService.getAll();

		// Direcciona si no hay ninguno creado
		if (dispositivos.size() == 0) {
			return ViewRouteHelper.VACIO_AMBIENTE;
		}

		// Modelamos la lista y direccionamos a vista para actualizar disp.
		model.addAttribute("dispositivos", dispositivos); 
		return ViewRouteHelper.ELIMINAR_AMBIENTE;
	}

	@RequestMapping(value= "/acondicionar/eliminar/{idDispositivo}" , method = {RequestMethod.GET, RequestMethod.POST})
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public ModelAndView eliminarDispositivoAcondicionarAmbiente(@PathVariable int idDispositivo) {
		ModelAndView mV = new ModelAndView();
		DispositivoAcondicionarAmbiente nuevoDispositivo = dispositivoService.findById(idDispositivo); // Traemos Dispositivo por ID
		dispositivoService.borrarDispositivo(idDispositivo); // Aplicamos baja logica
		mV.addObject("dispositivoAcondicionar", nuevoDispositivo);
		mV.setViewName(ViewRouteHelper.ELIMINAR_AMBIENTE);	
		mV.addObject("mensajeError", "Aplicado cambios en el dispositivo");
		List<DispositivoAcondicionarAmbiente> dispositivos = dispositivoService.getAll();
		mV.addObject("dispositivos", dispositivos);
		
		return mV;
	}

}
