package com.unla.grupo18.entities;

import java.time.LocalDateTime;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter

@Table(name = "dispositivo_estacionamiento")

@PrimaryKeyJoinColumn(referencedColumnName = "idDispositivo")


public class DispositivoEstacionamiento extends Dispositivo{
	
	@Column(name = "estado")
	private boolean estado;
	

	public  DispositivoEstacionamiento () {}


	public DispositivoEstacionamiento(String nombreDispositivo, LocalDateTime fechaCreacion,
			LocalDateTime fechaModificacion, LocalDateTime fechaBaja, boolean isBaja, Edificio edificio,
			boolean estado) {
		super(nombreDispositivo, fechaCreacion, fechaModificacion, fechaBaja, isBaja, edificio);
		this.estado = true;
	}


	
	



}