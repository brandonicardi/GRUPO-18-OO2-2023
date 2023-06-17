package com.unla.grupo18.services.implementation;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.unla.grupo18.entities.Dispositivo;
import com.unla.grupo18.repositories.IDispositivoRepository;


@Service("dispositivoService")
public class DispositivoService {
	@Autowired
	@Qualifier("dispositivoRepository")
	private IDispositivoRepository dispositivoRepository;
	
	public List<Dispositivo> getAll() {
		return this.dispositivoRepository.findAll();
	}
	
    public Dispositivo guardarDispositivo(Dispositivo dispositivo) {
        return dispositivoRepository.save(dispositivo);
    }

    public Dispositivo getDispositivoById(int dispositivoId) {
        return dispositivoRepository.findById(dispositivoId).orElse(null);
    }

    public List<Dispositivo> getAllDispositivos() {
        return dispositivoRepository.findAll();
    }

    public void deleteDevice(int dispositivoId) {
    	dispositivoRepository.deleteById(dispositivoId);
    }
}
