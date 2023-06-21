package com.unla.grupo18.services;

import java.util.List;

import com.unla.grupo18.entities.Evento;
import com.unla.grupo18.models.EventoModel;

public interface IEventoService {
	
	public List<Evento> getAll();
	
	public EventoModel insertOrUpdate(EventoModel eventoModel);
	
	public Evento findById (int idEvento);

}
