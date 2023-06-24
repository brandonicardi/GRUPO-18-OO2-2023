package com.unla.grupo18.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.unla.grupo18.entities.DispositivoAcondicionarAmbiente;
import com.unla.grupo18.entities.MetricaAcondicionarAmbiente;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Repository("MetricaAcondicionarAmbienteRepository")
public interface IMetricaDispositivoAcondicionarAmbienteRepository extends JpaRepository<MetricaAcondicionarAmbiente, Integer>{
	
	// Metodo buscado que trae de la BD internamente, una instancia de dicho objeto entre fechas.
	List<MetricaAcondicionarAmbiente> findByDispositivoAndFechaHoraMetricaBetween(DispositivoAcondicionarAmbiente dispositivo, LocalDateTime fechaInicio, LocalDateTime fechaFin);

	// Metodo de busqueda de todoas las metricas de un dispositivo
	List<MetricaAcondicionarAmbiente> findByDispositivo( DispositivoAcondicionarAmbiente dispositivo);
	
}