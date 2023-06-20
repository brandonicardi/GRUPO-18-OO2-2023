package com.unla.grupo18.services.implementation;

import java.time.LocalDate;

import java.util.List;

import org.springframework.stereotype.Service;

import com.unla.grupo18.entities.DispositivoAlumbrado;
import com.unla.grupo18.entities.DispositivoEstacionamiento;
import com.unla.grupo18.repositories.IDispositivoAlumbradoRepository;
import com.unla.grupo18.repositories.IDispositivoEstacionamientoRepository;
@Service
public class DispositivoEstacionamientoService {

	private final IDispositivoEstacionamientoRepository dispositivoEstacionamientoRepository;

	public DispositivoEstacionamientoService(
			IDispositivoEstacionamientoRepository dispositivoEstacionamientoRepository) {
		
		this.dispositivoEstacionamientoRepository = dispositivoEstacionamientoRepository;
	}
	  //guardar nuevo registro
    public DispositivoEstacionamiento saveDispositivo(DispositivoEstacionamiento dispositivo) {
        return dispositivoEstacionamientoRepository.save(dispositivo);
    }
	 public List<DispositivoEstacionamiento> getAllDispositivos() {
	        return dispositivoEstacionamientoRepository.findAll();
	    }
	 
	 
	 
	 
}
