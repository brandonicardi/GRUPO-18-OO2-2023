package com.unla.grupo18.services.implementation;

import java.time.LocalDate;
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

    //guardar nuevo registro
    public DispositivoAlumbrado saveDispositivo(DispositivoAlumbrado dispositivo) {
        return dispositivoAlumbradoRepository.save(dispositivo);
    }
    
 // Actualizar un registro existente
    public DispositivoAlumbrado updateDispositivo(DispositivoAlumbrado dispositivo) {
        if (dispositivo.getIdDispositivo() == 0) {
            throw new IllegalArgumentException("El dispositivo debe tener un ID válido para ser modificado");
        }

        // Verificar si el dispositivo existe en la base de datos
        DispositivoAlumbrado dispositivoExistente = this.getDispositivoById(dispositivo.getIdDispositivo());
        if (dispositivoExistente == null) {
            throw new IllegalArgumentException("No se encontró el dispositivo de alumbrado con ID: " + dispositivo.getIdDispositivo());
        }

        // Actualizar los campos del sensor existente con los nuevos valores
        dispositivoExistente.setNombreDispositivo(dispositivo.getNombreDispositivo());
        dispositivoExistente.setEdificio(dispositivo.getEdificio());
        dispositivoExistente.setFechaModificacion(LocalDate.now());
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
    	dispositivo.setFechaBaja(LocalDate.now());
    	this.saveDispositivo(dispositivo);
    }
}