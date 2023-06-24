package com.unla.grupo18.services;

import java.time.LocalDate;
import java.util.List;

import com.unla.grupo18.entities.Dispositivo;
import com.unla.grupo18.entities.Evento;
import com.unla.grupo18.models.EventoModel;

public interface IEventoService {
	
	public List<Evento> getAll();
	
	public EventoModel insertOrUpdate(EventoModel eventoModel);
	
	public Evento findById (int idEvento);
	
	public List<Evento> getEventosPorDispositivo(Dispositivo dispositivo);
	
	public List<Evento> findByDispositivo(Dispositivo dispositivo);
	
	public List<Evento> buscarPorDescripcion(String descripcionEvento);

	public List<Evento> buscarPorFechaMetrica(LocalDate fechaMetrica);
	
}
