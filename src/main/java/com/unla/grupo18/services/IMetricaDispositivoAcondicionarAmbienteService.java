package com.unla.grupo18.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.unla.grupo18.entities.DispositivoAcondicionarAmbiente;

import com.unla.grupo18.entities.MetricaAcondicionarAmbiente;



public interface IMetricaDispositivoAcondicionarAmbienteService {

	// pre: dispositivoId del tipo entero
	//post: Retorna una Metrica del tipo Acondicioanr Ambiente por ID
	public MetricaAcondicionarAmbiente obtenerMetricaPorId(int dispositivoId);
	//====================================================================================================
	// pre: Requiere una Metrica del Tipo Acondicionar Ambiente
	//post: Lo guarda persistiendo en BD
	public MetricaAcondicionarAmbiente insertOrUpdate(MetricaAcondicionarAmbiente metrica);
	//====================================================================================================
	// pre: Requiere una Dispositivo del Tipo Acondicionar Ambiente
	//post: Traer todas las metricas ordenas por fecha y hora
	public List<MetricaAcondicionarAmbiente> traerMetricasDeUnDispositivo(DispositivoAcondicionarAmbiente dispositivo);
	//====================================================================================================
	
	
}
