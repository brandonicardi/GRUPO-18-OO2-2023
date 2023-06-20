package com.unla.grupo18.services.implementation;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.unla.grupo18.entities.Evento;
import com.unla.grupo18.models.EventoModel;
import com.unla.grupo18.repositories.IEventoRepository;
import com.unla.grupo18.services.IEventoService;

@Service("eventoServicio")
public class EventoService implements IEventoService{

	@Qualifier("eventoRepositorio")
	private final IEventoRepository eventoRepository;

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


}
