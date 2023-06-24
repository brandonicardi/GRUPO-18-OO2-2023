package com.unla.grupo18.services;
import java.util.Set;

import com.unla.grupo18.entities.Aula;

public interface IAulaService{
	
	// pre: requiere ID de Edificio del tipo int
	//post: Retorna una lista de Aulas, del Edificio que se trae x ID
	Set<Aula> cargarAulasPorEdificio(int edificioId);
	
}
