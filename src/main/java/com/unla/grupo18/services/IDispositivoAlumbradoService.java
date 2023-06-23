package com.unla.grupo18.services;

import java.util.List;

import com.unla.grupo18.entities.DispositivoAlumbrado;
import com.unla.grupo18.entities.MetricaAlumbrado;
import com.unla.grupo18.models.DispositivoAlumbradoModel;

public interface IDispositivoAlumbradoService {

	// pre: no requiere argumentos
	//post: Retorna la lista completa de Dispositivos Acondicionar Ambienet
	public List<DispositivoAlumbrado> getAll();
	//====================================================================================================
	// pre: idDispositivo del tipo int - De la superclase
	//post: Traer un unico Dispositivo por ID
	public DispositivoAlumbrado findById(int idDispositivo);
	//====================================================================================================
	// pre: dispositivo del tipo Dispositivo Acondicionar Ambiente no Nulo
	//post: Permite la insercion - actualizacion , del dispositivo
	//		Lo trae por ID, le Setea valores, implementa persistencia
	public DispositivoAlumbradoModel saveDispositivo(DispositivoAlumbrado dispositivo);
    //====================================================================================================
	// pre: it del tipo int no nulo
	//post: Elimina un Dispositivo de la BD - NO APLICAR - USAMOS BAJA LOGICA 
    public boolean remove(int idDispositivo);
    //====================================================================================================
	// pre: idDispositivo del tipo int no nulo
    //post: Aplica baja logica, setea el valor isBaja de Dispositivo a "false"
	public void borrarDispositivo(int idDispositivo);
	// ====================================================================================================
	// pre: Sin argumentos
	//post: Retorna una lista completa de metricas del dispositivo Acondicionar ambiente x HQL
	public List<MetricaAlumbrado> traerMetricas();
	
}
