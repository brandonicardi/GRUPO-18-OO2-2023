package com.unla.grupo18.repositories;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.unla.grupo18.entities.Dispositivo;
import com.unla.grupo18.entities.Evento;
import com.unla.grupo18.entities.MetricaAlumbrado;
import com.unla.grupo18.entities.MetricaEstacionamiento;

@Repository("eventoRepositorio")
public interface IEventoRepository extends JpaRepository <Evento, Serializable> {

	// Trae x descripcion
	Evento findByDescripcionEvento(String descripcionEvento);
	// Trae x ID
	Evento findByidEvento(int idEvento);
	// Trae por fecha hora evento
	Evento findByFechahoraEvento(LocalDateTime fechahoraEvento);
	
	List<Evento> findByDispositivo(Dispositivo dispositivo);
	
	Evento findByDispositivoAndMetrica(Dispositivo dispositivo, MetricaAlumbrado metricaAlumbrado);
	Evento findByDispositivoAndMetrica(Dispositivo dispositivo, MetricaEstacionamiento metricaEstacionamiento);
	
	List<Evento> findByDescripcionEventoContainingIgnoreCase(String descripcionEvento);
	
	@Query("SELECT e FROM Evento e WHERE e.metrica.fechaDeteccion = :fechaDeteccion")
	List<Evento> findByFechaMetrica(LocalDate fechaDeteccion);
	
}
