package com.unla.grupo18.services.implementation;

import java.time.LocalDateTime;
import org.modelmapper.ModelMapper;

import java.time.LocalTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.unla.grupo18.entities.DispositivoAlumbrado;
import com.unla.grupo18.entities.MetricaAcondicionarAmbiente;
import com.unla.grupo18.entities.MetricaAlumbrado;
import com.unla.grupo18.models.DispositivoAlumbradoModel;
import com.unla.grupo18.repositories.IDispositivoAlumbradoRepository;

@Service
public class DispositivoAlumbradoService {

	private final IDispositivoAlumbradoRepository dispositivoAlumbradoRepository;
	
	private ModelMapper modelMapper = new ModelMapper();

	
    public DispositivoAlumbradoService(IDispositivoAlumbradoRepository dispositivoAlumbradoRepository) {
        this.dispositivoAlumbradoRepository = dispositivoAlumbradoRepository;
    }

    public DispositivoAlumbradoModel insertOrUpdateDisp(DispositivoAlumbrado dispositivo) {
    	DispositivoAlumbrado dispositivoNuevo = dispositivoAlumbradoRepository.save(dispositivo);
        return modelMapper.map(dispositivoNuevo, DispositivoAlumbradoModel.class);
    }
    
//	================== Traemos todos los Dispositivos del tipo ALUMBRADO ==================
	public List<DispositivoAlumbrado> getAll(){
		return dispositivoAlumbradoRepository.findAll();
	}

	//	================== Traer Dispositivo por ID ==================
	public DispositivoAlumbrado findById(int idDispositivo) {
		return dispositivoAlumbradoRepository.findByidDispositivo(idDispositivo);
	}

    public List<DispositivoAlumbrado> getAllDispositivos() {
        return dispositivoAlumbradoRepository.findAll();
    }
    
    public List<DispositivoAlumbrado> getAllActiveDispositivos() {
        return dispositivoAlumbradoRepository.findByTipoAndActivoIsTrue();
    }

    public void deleteDispositivo(Integer dispositivoId) {
    	DispositivoAlumbrado dispositivo = this.findById(dispositivoId);
    	dispositivo.setBaja(true);
    	dispositivo.setFechaBaja(LocalDateTime.now());
    	this.insertOrUpdateDisp(dispositivo);
    }
    
	//	================== Retorna Lista de Metricas ==================
	public List<MetricaAlumbrado> traerMetricas(){
		return dispositivoAlumbradoRepository.traerMetricasAlumbrado();
	}
	
}