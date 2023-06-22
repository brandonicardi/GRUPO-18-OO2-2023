package com.unla.grupo18.repositories;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.unla.grupo18.entities.DispositivoAcondicionarAmbiente;
import com.unla.grupo18.entities.DispositivoAlumbrado;
import com.unla.grupo18.entities.DispositivoEstacionamiento;


	@Repository("dispositivoEstacionamientoRepository")
	public interface IDispositivoEstacionamientoRepository extends JpaRepository<DispositivoEstacionamiento, Serializable> {
		
		@Query("SELECT d FROM DispositivoEstacionamiento d WHERE d.isBaja = false")
		List<DispositivoEstacionamiento> findByTipoAndActivoIsTrue();
		
		// Traer un unico Dispositivo Acondicionar Ambiente por ID
		public abstract DispositivoEstacionamiento findByidDispositivo(int idDispositivo);
}
