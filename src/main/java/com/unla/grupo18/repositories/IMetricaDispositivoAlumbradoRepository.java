package com.unla.grupo18.repositories;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.unla.grupo18.entities.DispositivoAlumbrado;
import com.unla.grupo18.entities.MetricaAlumbrado;

@Repository("metricaDispositivoAlumbradoRepository")
public interface IMetricaDispositivoAlumbradoRepository extends JpaRepository<MetricaAlumbrado, Integer> {

	List<MetricaAlumbrado> findByDispositivoAndFechaHoraMetricaBetween(DispositivoAlumbrado dispositivo, LocalDateTime fechaInicio, LocalDateTime fechaFin);

	List<MetricaAlumbrado> findByDispositivo(DispositivoAlumbrado dispositivo);
}