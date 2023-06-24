package com.unla.grupo18.services.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.unla.grupo18.entities.DispositivoAlumbrado;
import com.unla.grupo18.entities.DispositivoEstacionamiento;
import com.unla.grupo18.entities.MetricaAlumbrado;
import com.unla.grupo18.entities.MetricaEstacionamiento;
import com.unla.grupo18.repositories.IMetricaDispositivoEstacionamientoRepository;

@Service("metricaDispositivoEstacionamientoService")
public class MetricaDispositivoEstacionamientoService {
	@Autowired
	@Qualifier("metricaDispositivoEstacionamientoRepository")
	private final IMetricaDispositivoEstacionamientoRepository metricaEstacionamientoRepository;
	@Autowired
	MetricaDispositivoEstacionamientoService(
			IMetricaDispositivoEstacionamientoRepository metricaEstacionamientoRepository) {
		this.metricaEstacionamientoRepository = metricaEstacionamientoRepository;
	}

	public MetricaEstacionamiento getMetricaById(int dispositivoId) {
		return metricaEstacionamientoRepository.findById(dispositivoId).orElse(null);
	}

	public MetricaEstacionamiento saveMetrica(MetricaEstacionamiento metrica) {
		return metricaEstacionamientoRepository.save(metrica);
	}

	public List<MetricaEstacionamiento> getMetricasByDispositivo(DispositivoEstacionamiento dispositivo) {
		return metricaEstacionamientoRepository.findByDispositivo(dispositivo);
	}

	
	
	//------------------ Actualizar un registro existente-----------------------------------------
		public MetricaEstacionamiento updateMetrica(MetricaEstacionamiento metrica) {
			if (metrica.getIdMetrica() == 0) {
				throw new IllegalArgumentException("La métrica debe tener un ID válido para ser actualizada");
			}

			// Verificar si la métrica existe en la base de datos
			MetricaEstacionamiento existente = metricaEstacionamientoRepository.findById(metrica.getIdMetrica()).orElse(null);
			if (existente == null) {
				throw new IllegalArgumentException("No se encontró la métrica con ID: " + metrica.getIdMetrica());
			}

			// Actualizar los campos de la métrica existente con los nuevos valores
		    existente.setEstado(metrica.isEstado());
			existente.setFechaHoraMetrica(metrica.getFechaHoraMetrica());

			// Guardar la métrica actualizada en la base de datos
			return metricaEstacionamientoRepository.save(existente);
		}
		//---------------------------------------------------------------------------------------------------
	}

