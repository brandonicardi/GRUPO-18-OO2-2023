package com.unla.grupo18.services.implementation;

import java.time.LocalDateTime;
import java.util.List;






import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;


import com.unla.grupo18.entities.DispositivoRegador;
import com.unla.grupo18.entities.MetricaRegador;
import com.unla.grupo18.models.DispositivoRegadorModel;
import com.unla.grupo18.repositories.IDispositivoRegadorRepository;
import com.unla.grupo18.services.IDispositivoRegadorService;


@Service("dispositivoRegadorService")
public class DispositivoRegadorService implements IDispositivoRegadorService{
	@Autowired
	@Qualifier("dispositivoRegadorRepository")
	private IDispositivoRegadorRepository dispositivoRegadorRepository;

	private ModelMapper modelMapper = new ModelMapper();


	
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
			dispositivo.setFechaBaja(LocalDateTime.now());
			dispositivoRegadorRepository.save(dispositivo);
	}
	
	
	//Traer
	public DispositivoRegadorModel getDispositivoById(int id) {
		DispositivoRegador dispositivo = dispositivoRegadorRepository.findById(id).orElse(null);
		return modelMapper.map(dispositivo, DispositivoRegadorModel.class);
	}
	
	public DispositivoRegador findDispositivoById(int id) {
		return dispositivoRegadorRepository.findById(id).orElse(null);
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

	
	
}
