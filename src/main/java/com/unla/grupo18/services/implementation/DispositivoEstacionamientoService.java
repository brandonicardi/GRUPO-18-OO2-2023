package com.unla.grupo18.services.implementation;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.unla.grupo18.entities.DispositivoAcondicionarAmbiente;
import com.unla.grupo18.entities.DispositivoAlumbrado;
import com.unla.grupo18.entities.DispositivoEstacionamiento;
import com.unla.grupo18.models.DispositivoAcondicionarAmbienteModel;
import com.unla.grupo18.models.DispositivoEstacionamientoModel;
import com.unla.grupo18.repositories.IDispositivoAlumbradoRepository;
import com.unla.grupo18.repositories.IDispositivoEstacionamientoRepository;
@Service
public class DispositivoEstacionamientoService {
	private ModelMapper modelMapper = new ModelMapper();
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
	 public DispositivoEstacionamiento getDispositivoById(Integer dispositivoId) {
	        return dispositivoEstacionamientoRepository.findById(dispositivoId).orElse(null);
	    }
	
	
	 
		public DispositivoEstacionamientoModel insertOrUpdate(DispositivoEstacionamiento dispositivo) {
			DispositivoEstacionamiento dispositivoNuevo = dispositivoEstacionamientoRepository.save(dispositivo);
			
			return modelMapper.map(dispositivoNuevo, DispositivoEstacionamientoModel.class);
		
		}	
		
	 
		public List<DispositivoEstacionamiento> getAllActiveDispositivos() {
	        return dispositivoEstacionamientoRepository.findByTipoAndActivoIsTrue();
	    }
		
		
		
	 public boolean remove(int idDispositivo) {
			try {
				dispositivoEstacionamientoRepository.deleteById(idDispositivo);
				return true;
			} catch (Exception e) {
				return false;
			}
		}
	    
	//Actualizar un registro existente
	    public DispositivoEstacionamiento updateDispositivo(DispositivoEstacionamiento dispositivo) {
	     
	    	 DispositivoAlumbrado dispositivoExistente = this.getDispositivoById(dispositivo.getIdDispositivo());
	      

	        // Actualizar los campos del sensor existente con los nuevos valores
	        dispositivoExistente.setNombreDispositivo(dispositivo.getNombreDispositivo());
	        dispositivoExistente.setEdificio(dispositivo.getEdificio());
	        dispositivoExistente.setFechaModificacion(LocalDateTime.now());
	        
	        // Guardar el sensor actualizado en la base de datos
	        return dispositivoEstacionamientoRepository.save(dispositivoExistente);
	    }
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
//		Implementa baja logica 
		public void borrarDispositivo(int idDispositivo) {
			DispositivoEstacionamiento nuevoDispositivo = dispositivoEstacionamientoRepository.findByidDispositivo(idDispositivo);
			nuevoDispositivo.setBaja(true);
			nuevoDispositivo.setFechaBaja(LocalDateTime.now());
			this.insertOrUpdate(nuevoDispositivo);
		}
		
		
		
		

	    


	 
}
