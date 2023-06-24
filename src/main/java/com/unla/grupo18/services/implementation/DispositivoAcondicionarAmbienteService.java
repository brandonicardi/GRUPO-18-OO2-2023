package com.unla.grupo18.services.implementation;

import java.time.LocalDateTime;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.unla.grupo18.repositories.IDispositivoAcondicionarAmbienteRepository;
import com.unla.grupo18.services.IDispositivoAcondicionarAmbienteService;
import com.unla.grupo18.entities.DispositivoAcondicionarAmbiente;
import com.unla.grupo18.entities.MetricaAcondicionarAmbiente;
import com.unla.grupo18.models.DispositivoAcondicionarAmbienteModel;

@Service("dispositivoAcondicionarAmbienteService")
public class DispositivoAcondicionarAmbienteService implements IDispositivoAcondicionarAmbienteService{

	// Permite gracias a Spring poder usar los metodos de repositirio sin instancia una clase de esta
	@Autowired
	@Qualifier("DispositivoAcondicionarAmbienteRepository")
	private IDispositivoAcondicionarAmbienteRepository dispositivoAcondicionarAmbienteRepository;
	
	// ================== Permite transformar Entidad a Modelo ================== 
	private ModelMapper modelMapper = new ModelMapper();
	
	//	================== Traemos todos los Dispositivos del tipo AcondicionarAmbiente ==================
	public List<DispositivoAcondicionarAmbiente> getAll(){
		return dispositivoAcondicionarAmbienteRepository.findAll();
	}

	//	================== Traer Dispositivo por ID ==================
	public DispositivoAcondicionarAmbiente findById(int idDispositivo) {
		return dispositivoAcondicionarAmbienteRepository.findByidDispositivo(idDispositivo);
	}
	
	// ================== Agregar o actualiza un Dispositivo ==================
	@Override
	public DispositivoAcondicionarAmbienteModel insertOrUpdate(DispositivoAcondicionarAmbiente dispositivo) {
		DispositivoAcondicionarAmbiente dispositivoNuevo = dispositivoAcondicionarAmbienteRepository.save(dispositivo);
		
		return modelMapper.map(dispositivoNuevo, DispositivoAcondicionarAmbienteModel.class);
	
	}	
	
	// ================== REMOVER DISPOSITIVO X ID DE LA BD - NO USAR ==================
	public boolean remove(int idDispositivo) {
		try {
			dispositivoAcondicionarAmbienteRepository.deleteById(idDispositivo);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	//	================== Implementa baja logica isBaja = false ==================  
	public void borrarDispositivo(int idDispositivo) {
		DispositivoAcondicionarAmbiente nuevoDispositivo = dispositivoAcondicionarAmbienteRepository.findByidDispositivo(idDispositivo);
		if(nuevoDispositivo.isBaja() == false) {
			nuevoDispositivo.setBaja(true);
			nuevoDispositivo.setFechaBaja(LocalDateTime.now());
		}else {
			nuevoDispositivo.setBaja(false);
			nuevoDispositivo.setFechaModificacion(LocalDateTime.now());
		}
		
		this.insertOrUpdate(nuevoDispositivo);
	}
	
	//	================== Retorna Lista de Metricas ==================
	public List<MetricaAcondicionarAmbiente> traerMetricas(){
		return dispositivoAcondicionarAmbienteRepository.traerMetricasAmbiente();
	}
	
		
	
}
