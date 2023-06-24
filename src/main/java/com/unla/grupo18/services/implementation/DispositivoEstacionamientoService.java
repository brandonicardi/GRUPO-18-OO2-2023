package com.unla.grupo18.services.implementation;

import java.time.LocalDate;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.unla.grupo18.entities.DispositivoAcondicionarAmbiente;
import com.unla.grupo18.entities.DispositivoAlumbrado;
import com.unla.grupo18.entities.DispositivoEstacionamiento;
import com.unla.grupo18.models.DispositivoAcondicionarAmbienteModel;
import com.unla.grupo18.models.DispositivoAlumbradoModel;
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
	
//		-------------------------- Dispositivo ESTACIONAMIENTO por ID ---------------
		public DispositivoEstacionamiento findByIdEstacionamiento(int idDispositivo) {
			return dispositivoEstacionamientoRepository.findByidDispositivo(idDispositivo);
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
	   public DispositivoEstacionamientoModel insertOrUpdateDisp(DispositivoEstacionamiento dispositivo) {
	        System.out.println("Dispositivo antes del mapeo: " + dispositivo);
	        DispositivoEstacionamiento dispositivoNuevo = dispositivoEstacionamientoRepository.save(dispositivo);
	        DispositivoEstacionamientoModel dispositivoModel = modelMapper.map(dispositivoNuevo, DispositivoEstacionamientoModel.class);
	        System.out.println("Dispositivo mapeado: " + dispositivoModel);
	        return dispositivoModel;
	    }
	 
	 
	 
//		Implementa baja logica 
		public void borrarDispositivo(int idDispositivo) {
			DispositivoEstacionamiento nuevoDispositivo = dispositivoEstacionamientoRepository.findByidDispositivo(idDispositivo);
			nuevoDispositivo.setBaja(true);
			nuevoDispositivo.setFechaBaja(LocalDateTime.now());
			this.insertOrUpdate(nuevoDispositivo);
		}
		

	    


	 
}
