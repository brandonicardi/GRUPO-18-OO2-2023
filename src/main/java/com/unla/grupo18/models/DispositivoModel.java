package com.unla.grupo18.models;

import java.time.LocalDateTime;

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
    protected LocalDateTime fechaCreacion;
    protected LocalDateTime fechaModificacion;
    protected LocalDateTime fechaBaja;
    protected boolean isBaja;
    protected Edificio edificio;
    
    
}
