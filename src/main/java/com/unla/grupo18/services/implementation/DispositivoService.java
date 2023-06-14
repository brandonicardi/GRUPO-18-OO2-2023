package com.unla.grupo18.services.implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.unla.grupo18.entities.Dispositivo;
import com.unla.grupo18.repositories.IDispositivoRepository;
import com.unla.grupo18.services.IDispositivoService;

@Service("dispositivoService")
public class DispositivoService implements IDispositivoService {
	@Autowired
	@Qualifier("dispositivoRepository")
	private IDispositivoRepository dispositivoRepository;
	
	@Override
	public List<Dispositivo> getAll() {
		return this.dispositivoRepository.findAll();
	}


}
