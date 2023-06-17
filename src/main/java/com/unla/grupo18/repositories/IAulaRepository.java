package com.unla.grupo18.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.unla.grupo18.entities.Aula;
import com.unla.grupo18.entities.Edificio;

@Repository("aulaRepository")
public interface IAulaRepository extends JpaRepository<Aula, Integer> {

    List<Aula> findByEdificio(Edificio edificio);
	public Aula findByNumero(@Param("numero") int numeroAula);
	public Aula findById(@Param("idAula") int idAula);
}
