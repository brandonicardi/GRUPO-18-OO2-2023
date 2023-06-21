package com.unla.grupo18.repositories;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.unla.grupo18.entities.DispositivoAcondicionarAmbiente;
import com.unla.grupo18.entities.MetricaAcondicionarAmbiente;


@Repository("DispositivoAcondicionarAmbienteRepository")
public interface IDispositivoAcondicionarAmbienteRepository extends JpaRepository<DispositivoAcondicionarAmbiente, Serializable>{

	// Traer todos los dispositivos
	public abstract List<DispositivoAcondicionarAmbiente> findAll();

	// Traer un unico Dispositivo Acondicionar Ambiente por ID
	public abstract DispositivoAcondicionarAmbiente findByidDispositivo(int idDispositivo);

	//Traer metricas del dispositivo
	@Query("from MetricaAcondicionarAmbiente m inner join fetch DispositivoAcondicionarAmbiente d")
	public List<MetricaAcondicionarAmbiente> traerMetricasAmbiente();

}
