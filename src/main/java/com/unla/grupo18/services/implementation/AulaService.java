package com.unla.grupo18.services.implementation;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;


import com.unla.grupo18.entities.Aula;
import com.unla.grupo18.entities.Edificio;
import com.unla.grupo18.repositories.IAulaRepository;

@Service
public class AulaService {

    @Autowired
    private IAulaRepository aulaRepository;

    public Aula crearAula(Aula aula) {
        return aulaRepository.save(aula);
    }

    public List<Aula> obtenerTodasLasAulas() {
        return aulaRepository.findAll();
    }

    public List<Aula> obtenerAulasPorEdificio(Edificio edificio) {
        return aulaRepository.findByEdificio(edificio);
    }
    
    public Aula findByNumero(int numeroAula) {
    	return aulaRepository.findByNumero(numeroAula);
    }
    
	public Aula findById(int idAula) {
		return aulaRepository.findById(idAula);
	}


    
    
    
}