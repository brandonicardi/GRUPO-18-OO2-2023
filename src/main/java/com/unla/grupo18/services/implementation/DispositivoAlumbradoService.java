package com.unla.grupo18.services.implementation;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.unla.grupo18.entities.DispositivoAlumbrado;
import com.unla.grupo18.repositories.IDispositivoAlumbradoRepository;

@Service
public class DispositivoAlumbradoService {

	private final IDispositivoAlumbradoRepository dispositivoAlumbradoRepository;
	
	
    public DispositivoAlumbradoService(IDispositivoAlumbradoRepository dispositivoAlumbradoRepository) {
        this.dispositivoAlumbradoRepository = dispositivoAlumbradoRepository;
    }

    public DispositivoAlumbrado saveDispositivo(DispositivoAlumbrado dispositivo) {
        return dispositivoAlumbradoRepository.save(dispositivo);
    }
    
    
    public DispositivoAlumbrado updateDispositivo(DispositivoAlumbrado dispositivo) {
        if (dispositivo.getIdDispositivo() == 0) {
            throw new IllegalArgumentException("El dispositivo debe tener un ID válido para ser modificado");
        }

        DispositivoAlumbrado dispositivoExistente = this.getDispositivoById(dispositivo.getIdDispositivo());
        if (dispositivoExistente == null) {
            throw new IllegalArgumentException("No se encontró el dispositivo de alumbrado con ID: " + dispositivo.getIdDispositivo());
        }

        dispositivoExistente.setNombreDispositivo(dispositivo.getNombreDispositivo());
        dispositivoExistente.setFechaModificacion(LocalDateTime.now());
        
        // Guardar el sensor actualizado en la base de datos
        return dispositivoAlumbradoRepository.save(dispositivoExistente);
    }

    public DispositivoAlumbrado getDispositivoById(Integer dispositivoId) {
        return dispositivoAlumbradoRepository.findById(dispositivoId).orElse(null);
    }

    public List<DispositivoAlumbrado> getAllDispositivos() {
        return dispositivoAlumbradoRepository.findAll();
    }
    
    public List<DispositivoAlumbrado> getAllActiveDispositivos() {
        return dispositivoAlumbradoRepository.findByTipoAndActivoIsTrue();
    }

    public void deleteDispositivo(Integer dispositivoId) {
    	DispositivoAlumbrado dispositivo = this.getDispositivoById(dispositivoId);
    	dispositivo.setBaja(true);
    	dispositivo.setFechaBaja(LocalDateTime.now());
    	this.saveDispositivo(dispositivo);
    }
}