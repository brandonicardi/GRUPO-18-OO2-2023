package com.unla.grupo18.services.implementation;

import java.util.List;





import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import com.unla.grupo18.entities.DispositivoRegador;
import com.unla.grupo18.entities.MetricaRegador;
import com.unla.grupo18.models.DispositivoRegadorModel;
import com.unla.grupo18.repositories.IDispositivoRegadorRepository;


@Service
public class DispositivoRegadorService {
	@Autowired
	@Qualifier("dispositivoRegadorRepository")
	private IDispositivoRegadorRepository dispositivoRegadorRepository;

	private ModelMapper modelMapper = new ModelMapper();

	public DispositivoRegadorService(IDispositivoRegadorRepository dispositivoRegadorRepository) {
		this.dispositivoRegadorRepository = dispositivoRegadorRepository;
	}
	
	//Alta o modificacion
	public DispositivoRegadorModel insertOrUpdate(DispositivoRegadorModel dispositivoModel) {
		DispositivoRegador dispositivo = dispositivoRegadorRepository.save(modelMapper.map(dispositivoModel, DispositivoRegador.class));
		return modelMapper.map(dispositivo, DispositivoRegadorModel.class);
	}
	

	//Baja
	public void delete(int id) {
			DispositivoRegador dispositivo = dispositivoRegadorRepository.findById(id).orElse(null);
			dispositivo.setEstaPrendido(false);
			dispositivo.setBaja(true);
			dispositivoRegadorRepository.save(dispositivo);
	}
	
	
	//Traer
	public DispositivoRegadorModel getDispositivoById(int id) {
		DispositivoRegador dispositivo = dispositivoRegadorRepository.findById(id).orElse(null);
		return modelMapper.map(dispositivo, DispositivoRegadorModel.class);
	}
	
	public List<DispositivoRegadorModel> getAll(){
		List<DispositivoRegador> dispositivos = dispositivoRegadorRepository.findAll();
		return mapList(dispositivos, DispositivoRegadorModel.class);
	}
	
	public List<DispositivoRegadorModel> getAllActive(){
		List<DispositivoRegador> dispositivos = dispositivoRegadorRepository.findByIsBaja(false);
		return mapList(dispositivos, DispositivoRegadorModel.class);
	}
	
	public List<DispositivoRegadorModel> getAllInactive(){
		List<DispositivoRegador> dispositivos = dispositivoRegadorRepository.findByIsBaja(true);
		return mapList(dispositivos, DispositivoRegadorModel.class);
	}
	
	public List<DispositivoRegadorModel> getAllPrendido(){
		List<DispositivoRegador> dispositivos = dispositivoRegadorRepository.findByEstaPrendido(true);
		return mapList(dispositivos, DispositivoRegadorModel.class);
	}
	
	public List<DispositivoRegadorModel> getAllApagado(){
		List<DispositivoRegador> dispositivos = dispositivoRegadorRepository.findByEstaPrendido(false);
		return mapList(dispositivos, DispositivoRegadorModel.class);
	}
	
	//Metodo auxiliar para transformar lista de una entidad en otra
	private <T, U> List<U> mapList(List<T> sourceList, Class<U> destinationClass) {
	    ModelMapper modelMapper = new ModelMapper();
	    java.lang.reflect.Type targetListType = new TypeToken<List<U>>() {}.getType();
	    return modelMapper.map(sourceList, targetListType);
	}
	
	//Traer metricas del dispositivo
	public List<MetricaRegador> traerMetricas(){
		return dispositivoRegadorRepository.traerMetricas();
	}
	/*
	//Actualizar el estado del dispositivo y generar los eventos
	/**
	 * 

	public void generarEventos() {
		List<MetricaRegador> metricas = this.traerMetricas();
		DispositivoRegador d; //Dispositivo de la metrica
		float humedadMedida; //Humedad registrada en la métrica
		float humedadActual; //Humedad registrada en dispositivo
		float humedadPrenderRegador; //Si la humedad es menor a esta se prende el regador
		float humedadApagarRegador; //Si la humedad es mayor a esta se apaga el regador
		boolean estaPrendido; //Registra si el regador esta prendido(true) o apagado(false)
		
		
		for (MetricaRegador m : metricas) {
			d = m.getDispositivo();
			humedadMedida = m.getHumedadMedida();
			humedadActual = d.getHumedadActual(); 
			humedadApagarRegador = d.getHumedadApagarRegador();
			humedadPrenderRegador = d.getHumedadPrenderRegador();
			
			
			if (humedadMedida < humedadPrenderRegador && !estaPrendido) {
				d.setEstaPrendido(true);
				//generar evento(dispositivo, "Se prendio regador", metrica)
			}
			
			if (humedadMedida > humedadApagarRegador && estaPrendido) {
				d.setEstaPrendido(false);
				//generar evento(dispositivo, "Se apago regador", metrica)
			}
		
		}
			 
		
	}*/
	
	
	
	
	
}
