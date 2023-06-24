package com.unla.grupo18.services.implementation;

import java.time.LocalDateTime;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.unla.grupo18.entities.DispositivoRegador;
import com.unla.grupo18.entities.MetricaRegador;
import com.unla.grupo18.models.DispositivoRegadorModel;
import com.unla.grupo18.repositories.IMetricaRegadorRepository;
import com.unla.grupo18.services.IMetricaRegadorService;

@Service("metricaRegadorService")

public class MetricaRegadorService{
	@Autowired
	@Qualifier("metricaRegadorRepository")
	private final IMetricaRegadorRepository metricaRegadorRepository;
	private ModelMapper modelMapper = new ModelMapper();
	
	public MetricaRegadorService(IMetricaRegadorRepository metricaRegadorRepository) {
		this.metricaRegadorRepository = metricaRegadorRepository;
	}

	public MetricaRegador getMetricaById(int dispositivoId) {
		return metricaRegadorRepository.findById(dispositivoId).orElse(null);
	}

	public MetricaRegador saveMetrica(MetricaRegador metrica) {
		return metricaRegadorRepository.save(metrica);
	}
	
	public List<MetricaRegador> getMetricasByDispositivo(DispositivoRegadorModel dispositivo) {
		DispositivoRegador disp = modelMapper.map(dispositivo, DispositivoRegador.class);
		return metricaRegadorRepository.findByDispositivo(disp);
	}
	
	public List<MetricaRegador> getMetricasByDispositivoAndFechaHora(DispositivoRegador dispositivo, LocalDateTime fechaInicio, LocalDateTime fechafin){
		return metricaRegadorRepository.findByDispositivoAndFechaHoraMetricaBetween(dispositivo, fechaInicio, fechafin);
	}

	
	
	
	
}
