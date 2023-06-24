package com.unla.grupo18.services.implementation;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.unla.grupo18.entities.Dispositivo;
import com.unla.grupo18.entities.DispositivoAcondicionarAmbiente;
import com.unla.grupo18.entities.DispositivoAlumbrado;
import com.unla.grupo18.entities.DispositivoEstacionamiento;
import com.unla.grupo18.entities.Evento;
import com.unla.grupo18.entities.MetricaAcondicionarAmbiente;
import com.unla.grupo18.entities.MetricaAlumbrado;
import com.unla.grupo18.entities.MetricaEstacionamiento;
import com.unla.grupo18.models.EventoModel;
import com.unla.grupo18.repositories.IDispositivoAcondicionarAmbienteRepository;
import com.unla.grupo18.repositories.IDispositivoEstacionamientoRepository;
import com.unla.grupo18.repositories.IEventoRepository;
import com.unla.grupo18.services.IEventoService;
import com.unla.grupo18.services.IMetricaDispositivoAcondicionarAmbienteService;

@Service("eventoServicio")
public class EventoService implements IEventoService {

	@Qualifier("eventoRepository")
	private final IEventoRepository eventoRepository;

	// --------------------------Llamamos el repositorio de Acondicioanr Ambiente-----------------------
	@Autowired
	@Qualifier("DispositivoAcondicionarAmbienteRepository")
	private IDispositivoAcondicionarAmbienteRepository dispositivoAcondicionarAmbienteRepository;
	//private IMetricaDispositivoAcondicionarAmbienteService  metricaAcondicionarService;
	private final MetricaDispositivoAcondicionarAmbiente metricaAcondicionarService;
	//---------------------------- Llamamos el repositorio de Estacionamiento---------------
		@Autowired
		@Qualifier("dispositivoEstacionamientoRepository")
		private IDispositivoEstacionamientoRepository dispositivoEstacionamientoRepository;
//------------------------------------------------------------------------------------------------------
		private final MetricaDispositivoEstacionamientoService metricaEstacionamientoService;
	private final MetricaDispositivoAlumbradoService metricaAlumbradoService;

	private ModelMapper modelMapper = new ModelMapper();

	EventoService(IEventoRepository eventoRepository, MetricaDispositivoAlumbradoService metricaAlumbradoService, MetricaDispositivoEstacionamientoService metricaEstacionamientoService, MetricaDispositivoAcondicionarAmbiente metricaAcondicionarService) {
		this.eventoRepository = eventoRepository;
		this.metricaAlumbradoService = metricaAlumbradoService;
		this.metricaEstacionamientoService=metricaEstacionamientoService;
		this.metricaAcondicionarService=metricaAcondicionarService;
		
	}

	// Trae Lista de Eventos
	@Override
	public List<Evento> getAll() {
		return eventoRepository.findAll();
	}

	// Agrega / modifica Evento
	@Override
	public EventoModel insertOrUpdate(EventoModel eventoModel) {
		Evento evento = eventoRepository.save(modelMapper.map(eventoModel, Evento.class));
		return modelMapper.map(evento, EventoModel.class);
	}

	// Retorna Evento por ID
	@Override
	public Evento findById(int id) {
		return eventoRepository.findByidEvento(id);
	}

	public List<Evento> getEventosPorDispositivo(Dispositivo dispositivo) {
		return eventoRepository.findByDispositivo(dispositivo);
	}

	public Evento saveEvento(Evento evento) {
		return eventoRepository.save(evento);
	}

	// TESTEO
	//
	// Actualizar el estado del dispositivo y generar los eventos
	/*
	 * public void generarEventosAcondicionarAmbiente() {
	 * List<MetricaAcondicionarAmbiente> metricas =
	 * dispositivoAcondicionarAmbienteRepository.traerMetricasAmbiente();
	 * 
	 * DispositivoAcondicionarAmbiente d; //Dispositivo de la metrica
	 * 
	 * float temperaturaActual; //Temperatura registrada en dispositivo float
	 * temperaturaActivarFrio; //Si temperatura actual es mayor a este, modoAire
	 * cambia a Frio float temperaturaActivarCalor; //Si temperatura actual es menor
	 * a este, modoAire cambia a Calor boolean sensorPresencia; //Metrica registra
	 * si hay persona o no boolean estado; //Dispositivo si esta Encendido (true) o
	 * Apagado(false) String modoAire; //Nos indica la siguientes 3 leyendas: //
	 * Apagado - Frio - Calor
	 * 
	 * 
	 * for (MetricaAcondicionarAmbiente m : metricas) { if(m.getDispositivo()
	 * instanceof DispositivoAcondicionarAmbiente) { d =
	 * (DispositivoAcondicionarAmbiente)m.getDispositivo(); temperaturaActual =
	 * m.getTemperaturaActual(); temperaturaActivarFrio =
	 * d.getTemperaturaActivarFrio(); temperaturaActivarCalor =
	 * d.getTemperaturaActivarCalor(); sensorPresencia = m.isSensorPresencia();
	 * estado = d.isEstado();
	 * 
	 * if ( (temperaturaActual < temperaturaActivarCalor && !estado) &&
	 * sensorPresencia == true) { d.setEstado(true); // Enciende Dispositivo
	 * d.setModoAire("Calor"); // Modo Aire: Calor //Generar evento(dispositivo,
	 * "Se Encendio Aire Caliente", metrica) }
	 * 
	 * if ( (temperaturaActual > temperaturaActivarFrio && !estado) &&
	 * sensorPresencia == true) { d.setEstado(true); // Enciende Dispositivo
	 * d.setModoAire("Frio"); // Modo Aire: Frio //Generar evento(dispositivo,
	 * "Se Encendio Aire Caliente", metrica) }
	 * 
	 * if ( (temperaturaActual > temperaturaActivarCalor && temperaturaActual <
	 * temperaturaActivarFrio) || sensorPresencia == false ) { d.setEstado(false);
	 * // Apaga Dispositivo d.setModoAire("Apagado"); // Modo Aire: Apagado } }
	 * 
	 * 
	 * }
	 * 
	 * }
	 */

	public List<Evento> findByDispositivo(Dispositivo dispositivo) {
		return eventoRepository.findByDispositivo(dispositivo);
	}

	public Evento getEventoByDispositivoAndMetrica(Dispositivo dispositivo, MetricaAlumbrado metricaAlumbrado) {
		return eventoRepository.findByDispositivoAndMetrica(dispositivo, metricaAlumbrado);
	}
	//ESTACIONAMIENTO
	public Evento getEventoByDispositivoAndMetrica(Dispositivo dispositivo, MetricaEstacionamiento metricaEstacionamiento) {
		return eventoRepository.findByDispositivoAndMetrica(dispositivo, metricaEstacionamiento);
	}
	
	public List<Evento> buscarPorDescripcion(String descripcionEvento) {
	    return eventoRepository.findByDescripcionEventoContainingIgnoreCase(descripcionEvento);
	}
	//----------------------------------ESTACIONAMIENTO-----------------------------------------
	
	public void actualizarEventosEstacionamientoDesdeMetricas(DispositivoEstacionamiento dispositivoEstacionamiento) {
		List<MetricaEstacionamiento> metricas = metricaEstacionamientoService.getMetricasByDispositivo(dispositivoEstacionamiento);

		for (MetricaEstacionamiento metrica : metricas) {
			//Evento existenteEvento = getEventoByDispositivoAndMetrica(dispositivoEstacionamiento, metrica);

			//if (existenteEvento == null) {
				if (dispositivoEstacionamiento.isEstado()==false) {
					//LocalDateTime horaEncendido = dispositivoEstacionamiento.getFechaCreacion();
					
						Evento eventoActivo = new Evento(dispositivoEstacionamiento, "ESTACIONAMIENTO ACTIVO",
								metrica.getFechaHoraMetrica(), metrica);
						saveEvento(eventoActivo);
					}
		//		}
		
					if (dispositivoEstacionamiento.isEstado()==true) {
					//	LocalDateTime horaOcupado = dispositivoEstacionamiento.getFechaCreacion();
						
							Evento eventoOcupado = new Evento(dispositivoEstacionamiento, "ESTACIONAMIENTO OCUPADO",
									metrica.getFechaHoraMetrica(), metrica);
							saveEvento(eventoOcupado);
						}
				}
			}
		

		// Obtiene un Evento de >Acondicionar Ambiente< por Dispositivo y Metrica
	public Evento getEventoByDispositivoAndMetrica(Dispositivo dispositivo, MetricaAcondicionarAmbiente metricaAcondicionar) {
		return eventoRepository.findByDispositivoAndMetrica(dispositivo, metricaAcondicionar);
	}
	
	//----------------------------------ACONDICIONAR AMBIENTE-----------------------------------------
	public void actualizarEventosAcondicionarAmbienteDesdeMetricas(DispositivoAcondicionarAmbiente dispositivoAcondicionar) {
		List<MetricaAcondicionarAmbiente> metricas = metricaAcondicionarService.traerMetricasDeUnDispositivo(dispositivoAcondicionar);

		// Se itera x todas las metricas del dispositivo
		for (MetricaAcondicionarAmbiente metrica : metricas) {
			Evento existingEvento = getEventoByDispositivoAndMetrica(dispositivoAcondicionar, metrica); // Se trae desde la BD Evento del dispositivo y metrica correspondiente

			if (existingEvento == null) {	// No posee evento, ingresa al condicional

				if (!dispositivoAcondicionar.isEstado()) { // Si el dispositivo esta encendido....

					float temperaturaActivarFrio = dispositivoAcondicionar.getTemperaturaActivarFrio(); 	// Seteamos el valor de activar Frio del dispositivo creado
					float temperaturaActivarCalor= dispositivoAcondicionar.getTemperaturaActivarCalor();	// Seteamos el valor de activar Calor del dispositivo creado

					// AGREGAR QUE NO PRENDE CUANDO EL AIRE ESTA DENTRO DE LOS EXTREMOS !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! 12312412412
					
					// Condicional Frio
					if(metrica.isSensorPresencia() && ( metrica.getTemperaturaActual() > temperaturaActivarFrio ) ) {

						// Indica que hay personas, que la temperatura actual supera el valor maximo seteado en dispositivo
						// Por lo que debe climatizar el ambiente con aire Frio
						dispositivoAcondicionar.setEstado(true);	// Dispositivo esta funcionando

						// Instanciamos Evento, se le da descripcion, se setea la hora del evento con el valor de cuando fue generada la metrica
						Evento eventoAcondicionar = new Evento (dispositivoAcondicionar,"Enfriar ambiente con Aire Acondicionado hasta alcanzar los 24°C", metrica.getFechaHoraMetrica(),metrica);

						this.saveEvento(eventoAcondicionar);	// Se persiste el evento

					}else {

						// Condicional Calor
						if(metrica.isSensorPresencia() && ( metrica.getTemperaturaActual() < temperaturaActivarCalor ) ) {
							// Indica que hay personas, que la temperatura actual es menor al valor seteado en dispositivo
							// Por lo que debe climatizar el ambiente con aire Caliente
							dispositivoAcondicionar.setEstado(true);	// Dispositivo esta funcionando

							// Instanciamos Evento, se le da descripcion, se setea la hora del evento con el valor de cuando fue generada la metrica
							Evento eventoCalefaccionar= new Evento (dispositivoAcondicionar,"Encender la calefaccion hasta alcanzar los 24°C", metrica.getFechaHoraMetrica(),metrica);

							this.saveEvento(eventoCalefaccionar);	// Se persiste el evento
						}
					}
				}
			}
		}
	}
	
	
	
	
	
	
	
	
	//----------------------------------ALUMBRADO-----------------------------------------
	public void actualizarEventosAlumbradoDesdeMetricas(DispositivoAlumbrado dispositivoAlumbrado) {
		List<MetricaAlumbrado> metricas = metricaAlumbradoService.getMetricasByDispositivo(dispositivoAlumbrado);

		for (MetricaAlumbrado metrica : metricas) {
			Evento existingEvento = getEventoByDispositivoAndMetrica(dispositivoAlumbrado, metrica);

			if (existingEvento == null) {
				if (!dispositivoAlumbrado.isEstado()) {
					LocalTime horaEncendido = dispositivoAlumbrado.getHoradeEncendido();
					LocalTime horaApagado = dispositivoAlumbrado.getHoradeApagado();

					if (metrica.isSensorPresencia() && metrica.getHoraDeteccion().isAfter(horaEncendido)
							&& metrica.getHoraDeteccion().isBefore(horaApagado)) {
						dispositivoAlumbrado.setEstado(true);
						Evento eventoPrenderLaLuz = new Evento(dispositivoAlumbrado, "Se prendió la luz",
								metrica.getFechaHoraMetrica(), metrica);
						saveEvento(eventoPrenderLaLuz);
					}
				} else {
					if (!metrica.isSensorPresencia()) {
						dispositivoAlumbrado.setEstado(false);
						Evento eventoApagarLaLuz = new Evento(dispositivoAlumbrado, "Se apagó la luz",
								metrica.getFechaHoraMetrica(), metrica);
						saveEvento(eventoApagarLaLuz);
					}
				}
			}
		}
	}

}