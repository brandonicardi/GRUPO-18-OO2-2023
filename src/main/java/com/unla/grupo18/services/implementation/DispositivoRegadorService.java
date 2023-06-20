package com.unla.grupo18.services.implementation;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.unla.grupo18.entities.DispositivoRegador;
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
			DispositivoRegador dispositivo = this.getDispositivoById(id);
			dispositivo.setEstaPrendido(false);
			dispositivo.setBaja(true);
			dispositivoRegadorRepository.save(dispositivo);
	}
	
	
	//Traer
	public DispositivoRegador getDispositivoById(int id) {
		return dispositivoRegadorRepository.findById(id).orElse(null);
	}
	
	public List<DispositivoRegador> getAll(){
		return dispositivoRegadorRepository.findAll();
	}
	
	public List<DispositivoRegador> getAllActive(){
		return dispositivoRegadorRepository.findByIsBaja(false);
	}
	
	public List<DispositivoRegador> getAllInactive(){
		return dispositivoRegadorRepository.findByIsBaja(true);
	}
	
	public List<DispositivoRegador> getAllPrendido(){
		return dispositivoRegadorRepository.findByEstaPrendido(true);
	}
	
	public List<DispositivoRegador> getAllApagado(){
		return dispositivoRegadorRepository.findByEstaPrendido(false);
	}
}
