package com.unla.grupo18.repositories;

import java.io.Serializable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.unla.grupo18.entities.Dispositivo;

@Repository("dispositivoRepository")
public interface IDispositivoRepository extends JpaRepository<Dispositivo, Serializable> {

	public Dispositivo findByNombreDispositivo(@Param("nombreDispositivo") String nombreDispositivo);

	public Dispositivo findByIdDispositivo(@Param("idDispositivo") int idDispositivo);
	
}
