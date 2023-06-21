package com.unla.grupo18.models;

import java.time.LocalDateTime;

import com.unla.grupo18.entities.Dispositivo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EventoModel {

	private int idEvento;
	
	private Dispositivo dispositivo;
	
	private String descripcionEvento;
	
	private LocalDateTime fechahoraEvento;
	
	
}
