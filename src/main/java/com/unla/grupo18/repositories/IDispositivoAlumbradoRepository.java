package com.unla.grupo18.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.unla.grupo18.entities.DispositivoAcondicionarAmbiente;
import com.unla.grupo18.entities.DispositivoAlumbrado;
import com.unla.grupo18.entities.MetricaAcondicionarAmbiente;
import com.unla.grupo18.entities.MetricaAlumbrado;

@Repository("dispositivoAlumbradoRepository")
public interface IDispositivoAlumbradoRepository extends JpaRepository<DispositivoAlumbrado, Integer> {
	
	@Query("SELECT d FROM DispositivoAlumbrado d WHERE d.isBaja = false")
	List<DispositivoAlumbrado> findByTipoAndActivoIsTrue();
	
	@Query("SELECT d FROM DispositivoAlumbrado d WHERE d.isBaja = true")
	List<DispositivoAlumbrado> findByTipoAndInactivoIsTrue();

	// Traer todos los dispositivos
	public abstract List<DispositivoAlumbrado> findAll();

	// Traer un unico Dispositivo Acondicionar Ambiente por ID
	public abstract DispositivoAlumbrado findByidDispositivo(int idDispositivo);

	@Query("from MetricaAlumbrado m inner join fetch DispositivoAlumbrado d")
	public List<MetricaAlumbrado> traerMetricasAlumbrado();

}
