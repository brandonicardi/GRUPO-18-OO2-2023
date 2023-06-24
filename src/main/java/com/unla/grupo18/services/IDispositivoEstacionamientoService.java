package com.unla.grupo18.services;
import java.util.List;

import com.unla.grupo18.entities.DispositivoAlumbrado;
import com.unla.grupo18.entities.DispositivoEstacionamiento;
import com.unla.grupo18.entities.MetricaEstacionamiento;
import com.unla.grupo18.models.DispositivoEstacionamientoModel;



public interface IDispositivoEstacionamientoService {

	
	public List<DispositivoEstacionamiento> getAll();
	
	public DispositivoEstacionamiento findById(int idDispositivo);
	//====================================================================================================
	
	
	public DispositivoEstacionamientoModel saveDispositivo(DispositivoEstacionamiento dispositivo);
    //====================================================================================================
	// Elimina un Dispositivo de la BD 
    public boolean remove(int idDispositivo);
    //====================================================================================================
	
    //Aplica baja logica, setea el valor isBaja de Dispositivo a "false"
	public void borrarDispositivo(int idDispositivo);
	// ====================================================================================================
	
	//post: Retorna una lista completa de metricas del dispositivo ESTACIONAMIENTO x HQL
	public List<MetricaEstacionamiento> traerMetricas();
	
}

