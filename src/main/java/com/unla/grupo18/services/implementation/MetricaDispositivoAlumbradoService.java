package com.unla.grupo18.services.implementation;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.unla.grupo18.entities.MetricaAlumbrado;
import com.unla.grupo18.entities.DispositivoAlumbrado;

import com.unla.grupo18.repositories.IMetricaDispositivoAlumbradoRepository;

@Service("metricaDispositivoAlumbradoService")
public class MetricaDispositivoAlumbradoService {

	@Autowired
	@Qualifier("metricaDispositivoAlumbradoRepository")
	private final IMetricaDispositivoAlumbradoRepository metricaAlumbradoRepository;

	public MetricaDispositivoAlumbradoService(IMetricaDispositivoAlumbradoRepository metricaAlumbradoRepository) {
		this.metricaAlumbradoRepository = metricaAlumbradoRepository;
	}

	public MetricaAlumbrado getMetricaById(int dispositivoId) {
		return metricaAlumbradoRepository.findById(dispositivoId).orElse(null);
	}

	public MetricaAlumbrado saveMetrica(MetricaAlumbrado metrica) {
		return metricaAlumbradoRepository.save(metrica);
	}

	public List<MetricaAlumbrado> getMetricasByDispositivo(DispositivoAlumbrado dispositivo) {
		return metricaAlumbradoRepository.findByDispositivo(dispositivo);
	}

	public List<MetricaAlumbrado> getMedicionesBySensorAndFecha(DispositivoAlumbrado dispositivo,
			LocalDateTime fechaInicio, LocalDateTime fechaFin) {
		return metricaAlumbradoRepository.findByDispositivoAndFechaHoraMetricaBetween(dispositivo, fechaInicio,
				fechaFin);
	}

	// Actualizar un registro existente
	public MetricaAlumbrado updateMetrica(MetricaAlumbrado metrica) {
		if (metrica.getIdMetrica() == 0) {
			throw new IllegalArgumentException("La métrica debe tener un ID válido para ser actualizada");
		}

		// Verificar si la métrica existe en la base de datos
		MetricaAlumbrado existente = metricaAlumbradoRepository.findById(metrica.getIdMetrica()).orElse(null);
		if (existente == null) {
			throw new IllegalArgumentException("No se encontró la métrica con ID: " + metrica.getIdMetrica());
		}

		// Actualizar los campos de la métrica existente con los nuevos valores
		existente.setSensorPresencia(metrica.isSensorPresencia());
		existente.setHoraActual(metrica.getHoraActual());
		existente.setFechaHoraMetrica(metrica.getFechaHoraMetrica());

		// Guardar la métrica actualizada en la base de datos
		return metricaAlumbradoRepository.save(existente);
	}
}