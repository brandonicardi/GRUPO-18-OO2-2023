package com.unla.grupo18.repositories;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.unla.grupo18.entities.DispositivoRegador;
import com.unla.grupo18.entities.MetricaRegador;

@Repository("metricaRegadorRepository")
public interface IMetricaRegadorRepository extends JpaRepository<MetricaRegador, Integer> {
	List<MetricaRegador> findByDispositivoAndFechaHoraMetricaBetween(DispositivoRegador dispositivo, LocalDateTime fechaInicio, LocalDateTime fechaFin);

	List<MetricaRegador> findByDispositivo(DispositivoRegador dispositivo);
}
