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
    
    
}
