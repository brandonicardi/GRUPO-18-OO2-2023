package com.unla.grupo18.services.implementation;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.unla.grupo18.entities.DispositivoAcondicionarAmbiente;
import com.unla.grupo18.entities.DispositivoAlumbrado;
import com.unla.grupo18.entities.MetricaAcondicionarAmbiente;
import com.unla.grupo18.entities.MetricaAlumbrado;
import com.unla.grupo18.models.DispositivoAcondicionarAmbienteModel;
import com.unla.grupo18.repositories.IMetricaDispositivoAcondicionarAmbienteRepository;
import com.unla.grupo18.services.IMetricaDispositivoAcondicionarAmbienteService;

@Service("metricaDispositivoAcondicionarAmbiente")
public class MetricaDispositivoAcondicionarAmbiente implements IMetricaDispositivoAcondicionarAmbienteService{

	// Permite gracias a Spring poder usar los metodos de repositirio sin instancia una clase de esta
	@Autowired
	@Qualifier("MetricaAcondicionarAmbienteRepository")
	private IMetricaDispositivoAcondicionarAmbienteRepository acondicionarRepository;

	// ================== Permite transformar Entidad a Modelo ================== 
	private ModelMapper modelMapper = new ModelMapper();

	// ================== Trae todas las Metricas ==================
	public List<MetricaAcondicionarAmbiente> traerTodasLasMetricas(){
		return acondicionarRepository.findAll();
	}
	// ================== Trae una sola metrica x ID ==================
	public MetricaAcondicionarAmbiente obtenerMetricaPorId(int metricaId) {
		return acondicionarRepository.findById(metricaId).orElse(null);
	}

	// ================== Se guarda Metrica a la BD - aplica actualizacion tmbn - Mapea de mentidad-model ==================
	@Override
	public MetricaAcondicionarAmbiente insertOrUpdate(MetricaAcondicionarAmbiente metrica) {
		MetricaAcondicionarAmbiente nuevaMetrica = acondicionarRepository.save(metrica);
		return modelMapper.map(nuevaMetrica, MetricaAcondicionarAmbiente.class);
	}

	// ================== Trae todas las metricas de un dispositivo ==================
	public List<MetricaAcondicionarAmbiente> traerMetricasDeUnDispositivo(DispositivoAcondicionarAmbiente dispositivo){
		return acondicionarRepository.findByDispositivo(dispositivo);
	}
	




}
