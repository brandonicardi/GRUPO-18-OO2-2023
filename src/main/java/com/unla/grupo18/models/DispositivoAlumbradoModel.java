package com.unla.grupo18.models;

import java.time.LocalTime;

import com.unla.grupo18.entities.Aula;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DispositivoAlumbradoModel extends DispositivoModel {
    private Aula aula;
    private LocalTime horaEncendido;
    private LocalTime horaApagado;
    private boolean estado;

}