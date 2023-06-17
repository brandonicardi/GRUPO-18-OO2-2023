package com.unla.grupo18.services.implementation;
import java.util.Set;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import com.unla.grupo18.entities.DispositivoAlumbrado;
import com.unla.grupo18.entities.Edificio;
import com.unla.grupo18.repositories.IEdificioRepository;

@Service
public class EdificioService {

	private final IEdificioRepository edificioRepository;

	public EdificioService(IEdificioRepository edificioRepository) {
		this.edificioRepository = edificioRepository;
	}

	// En el método obtenerTodosLosEdificios() de EdificioService
	public List<Edificio> obtenerTodosLosEdificios() {
		return edificioRepository.findAll();
	}

	public Edificio findById(int id) {
		return edificioRepository.findById(id);
	}

	public Edificio getEdificioById(Integer edificioId) {
		return edificioRepository.findById(edificioId).orElse(null);
	}

	public Edificio findByNombre(String nombreEdificio) {
		return edificioRepository.findByNombre(nombreEdificio);
	}

}