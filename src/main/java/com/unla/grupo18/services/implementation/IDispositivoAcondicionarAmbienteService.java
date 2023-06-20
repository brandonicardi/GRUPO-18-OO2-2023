package com.unla.grupo18.services.implementation;

import com.unla.grupo18.entities.DispositivoAcondicionarAmbiente;
import com.unla.grupo18.models.DispositivoAcondicionarAmbienteModel;
import java.util.List;

public interface IDispositivoAcondicionarAmbienteService {

	// Traemos todos los Dispositivos del tipo AcondicionarAmbiente
	public List<DispositivoAcondicionarAmbiente> getAll();
	
	// Traer Dispositivo por ID
	public DispositivoAcondicionarAmbiente findById(int idDispositivo);
	
	// Agregar - Editar Dispositivo
	public DispositivoAcondicionarAmbienteModel insertOrUpdate(DispositivoAcondicionarAmbiente dispositivo);
    
    // Remover Dispositivo
    public boolean remote(int id);
    
}
