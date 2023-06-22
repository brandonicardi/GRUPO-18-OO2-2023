package com.unla.grupo18.services.implementation;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.unla.grupo18.entities.Dispositivo;
import com.unla.grupo18.entities.DispositivoAcondicionarAmbiente;
import com.unla.grupo18.entities.Evento;
import com.unla.grupo18.entities.MetricaAcondicionarAmbiente;
import com.unla.grupo18.models.EventoModel;
import com.unla.grupo18.repositories.IDispositivoAcondicionarAmbienteRepository;
import com.unla.grupo18.repositories.IEventoRepository;
import com.unla.grupo18.services.IEventoService;

@Service("eventoServicio")
public class EventoService implements IEventoService{

	@Qualifier("eventoRepositorio")
	private final IEventoRepository eventoRepository;

	// Llamamos el repositorio de Acondicioanr Ambiente
	@Autowired
	@Qualifier("DispositivoAcondicionarAmbienteRepository")
	private IDispositivoAcondicionarAmbienteRepository dispositivoAcondicionarAmbienteRepository;

	private ModelMapper modelMapper = new ModelMapper();

	EventoService(IEventoRepository eventoRepository) {
		this.eventoRepository = eventoRepository;
	}

	// Trae Lista de Eventos 
	@Override
	public List<Evento> getAll(){
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
	
	public List<Evento> getEventosPorDispositivo(Dispositivo dispositivo){
		return eventoRepository.findByDispositivo(dispositivo);
	}
	
    public Evento saveEvento(Evento evento) {
        return eventoRepository.save(evento);
    }

	// TESTEO 
	//
	//Actualizar el estado del dispositivo y generar los eventos
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
	
	
	
}
