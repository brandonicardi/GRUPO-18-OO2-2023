package com.unla.grupo18.services;

import java.util.List;

import com.unla.grupo18.entities.DispositivoRegador;
import com.unla.grupo18.entities.MetricaRegador;
import com.unla.grupo18.models.DispositivoRegadorModel;

public interface IDispositivoRegadorService {

	//Alta o modificacion
	public DispositivoRegadorModel insertOrUpdate(DispositivoRegadorModel dispositivoModel);
	

	//Baja
	public void delete(int id);
	
	//Traer
	public DispositivoRegadorModel getDispositivoById(int id);
	
	public DispositivoRegador findDispositivoById(int id);
	
	public List<DispositivoRegadorModel> getAll();
	
	public List<DispositivoRegadorModel> getAllActive();
	
	public List<DispositivoRegadorModel> getAllInactive();
	
	public List<DispositivoRegadorModel> getAllPrendido();
	
	public List<DispositivoRegadorModel> getAllApagado();
	
	//Traer metricas del dispositivo
	public List<MetricaRegador> traerMetricas();
}
