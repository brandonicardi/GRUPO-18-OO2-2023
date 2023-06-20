package com.unla.grupo18.models;

import java.time.LocalDate;

import com.unla.grupo18.entities.Edificio;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DispositivoModel {
    
    protected int idDispositivo;
    protected String nombreDispositivo;
    protected LocalDate fechaCreacion;
    protected LocalDate fechaModificacion;
    protected LocalDate fechaBaja;
    protected boolean isBaja;
    protected Edificio edificio;
    
    // CONSTRUCTOR MANUAL, ACONDICIONAR AMBIENTE NO TOMA LA NOTACION LOMBOK
	public DispositivoModel(String nombreDispositivo, LocalDate fechaCreacion,
			LocalDate fechaModificacion, LocalDate fechaBaja, boolean isBaja, Edificio edificio) {
		super();
		this.nombreDispositivo = nombreDispositivo;
		this.fechaCreacion = fechaCreacion;
		this.fechaModificacion = fechaModificacion;
		this.fechaBaja = fechaBaja;
		this.isBaja = isBaja;
		this.edificio = edificio;
	}


    
	
    
    
}
