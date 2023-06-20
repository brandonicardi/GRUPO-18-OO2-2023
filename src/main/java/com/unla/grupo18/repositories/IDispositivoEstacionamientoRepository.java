package com.unla.grupo18.repositories;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.unla.grupo18.entities.DispositivoAlumbrado;
import com.unla.grupo18.entities.DispositivoEstacionamiento;


	@Repository("dispositivoEstacionamientoRepository")
	public interface IDispositivoEstacionamientoRepository extends JpaRepository<DispositivoEstacionamiento, Serializable> {
		
}
