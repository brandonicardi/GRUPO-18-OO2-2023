package com.unla.grupo18.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.unla.grupo18.entities.DispositivoAlumbrado;

@Repository("dispositivoAlumbradoRepository")
public interface IDispositivoAlumbradoRepository extends JpaRepository<DispositivoAlumbrado, Integer> {
	
	@Query("SELECT d FROM DispositivoAlumbrado d WHERE d.isBaja = false")
	List<DispositivoAlumbrado> findByTipoAndActivoIsTrue();
}
