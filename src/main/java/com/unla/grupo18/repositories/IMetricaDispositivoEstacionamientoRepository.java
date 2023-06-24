package com.unla.grupo18.repositories;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.unla.grupo18.entities.DispositivoAlumbrado;
import com.unla.grupo18.entities.DispositivoEstacionamiento;
import com.unla.grupo18.entities.MetricaAlumbrado;
import com.unla.grupo18.entities.MetricaEstacionamiento;
@Repository("metricaDispositivoEstacionamientoRepository")
public interface IMetricaDispositivoEstacionamientoRepository extends JpaRepository<MetricaEstacionamiento, Serializable>{
	List<MetricaEstacionamiento> findByDispositivoAndFechaHoraMetricaBetween(DispositivoEstacionamiento dispositivo, LocalDateTime fechaInicio, LocalDateTime fechaFinal);

	List<MetricaEstacionamiento> findByDispositivo(DispositivoEstacionamiento dispositivo);
}
