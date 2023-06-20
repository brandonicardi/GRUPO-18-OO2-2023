package com.unla.grupo18.services.implementation.implementacion;

import java.time.LocalDateTime;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.unla.grupo18.repositories.IDispositivoAcondicionarAmbienteRepository;

import com.unla.grupo18.entities.DispositivoAcondicionarAmbiente;
import com.unla.grupo18.entities.DispositivoAlumbrado;
import com.unla.grupo18.models.DispositivoAcondicionarAmbienteModel;

import com.unla.grupo18.services.implementation.IDispositivoAcondicionarAmbienteService;

@Service("dispositivoAcondicionarAmbienteService")
public class DispositivoAcondicionarAmbienteService implements IDispositivoAcondicionarAmbienteService{

	@Autowired
	@Qualifier("DispositivoAcondicionarAmbienteRepository")
	private IDispositivoAcondicionarAmbienteRepository dispositivoAcondicionarAmbienteRepository;
	
	private ModelMapper modelMapper = new ModelMapper();
	
	// Traemos todos los Dispositivos del tipo AcondicionarAmbiente
	public List<DispositivoAcondicionarAmbiente> getAll(){
		return dispositivoAcondicionarAmbienteRepository.findAll();
	}

	// Traer Dispositivo por ID
	public DispositivoAcondicionarAmbiente findById(int idDispositivo) {
		return dispositivoAcondicionarAmbienteRepository.findByidDispositivo(idDispositivo);
	}
	
	@Override
	public DispositivoAcondicionarAmbienteModel insertOrUpdate(DispositivoAcondicionarAmbiente dispositivo) {
		DispositivoAcondicionarAmbiente dispositivoNuevo = dispositivoAcondicionarAmbienteRepository.save(dispositivo);
		return modelMapper.map(dispositivoNuevo, DispositivoAcondicionarAmbienteModel.class);
		
		/*
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
        dispositivoExistente.setFechaModificacion(LocalDateTime.now());
        
        // Guardar el sensor actualizado en la base de datos
        return dispositivoAlumbradoRepository.save(dispositivoExistente);
		 */
	}	
	
	// Remover Dispositivo x id
	public boolean remote(int id) {
		try {
			dispositivoAcondicionarAmbienteRepository.deleteById(id);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	
}
