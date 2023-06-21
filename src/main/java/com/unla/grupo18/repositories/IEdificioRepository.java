package com.unla.grupo18.repositories;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.unla.grupo18.entities.Edificio;

@Repository("edificioRepository")
public interface IEdificioRepository extends JpaRepository<Edificio, Serializable> {

	public Edificio findById(@Param("id") int id);
	public Edificio findByNombre(@Param("nombre") String nombreEdificio);
	@Query("SELECT e FROM Edificio e")
	public List<Edificio> obtenerTodosLosEdificios();
	
	
}