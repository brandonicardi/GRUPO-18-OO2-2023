package com.unla.grupo18.models;

import java.time.LocalDate;
import java.time.LocalTime;

import com.unla.grupo18.entities.Aula;
import com.unla.grupo18.entities.Edificio;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DispositivoLucesAutomaticasModel extends DispositivoModel {
    
    private Aula aula;
    private LocalTime horaEncendido;
    private LocalTime horaApagado;
    private boolean detectorPresencia;
    private boolean accionarCortina;
    private LocalTime horaActual;
    private boolean estado;

    public DispositivoLucesAutomaticasModel(int idDispositivo, String nombreDispositivo, LocalDate fechaCreacion,
                                            LocalDate fechaModificacion, LocalDate fechaBaja, boolean isBaja,
                                            Edificio edificio, Aula aula, LocalTime horaEncendido,
                                            LocalTime horaApagado, boolean detectorPresencia,
                                            boolean accionarCortina, LocalTime horaActual, boolean estado) {
        super(idDispositivo, nombreDispositivo, fechaCreacion, fechaModificacion, fechaBaja, isBaja, edificio);
        this.aula = aula;
        this.horaEncendido = horaEncendido;
        this.horaApagado = horaApagado;
        this.detectorPresencia = detectorPresencia;
        this.accionarCortina = accionarCortina;
        this.horaActual = horaActual;
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "DispositivoLucesAutomaticasModel{" +
                "aula=" + aula +
                ", horaEncendido=" + horaEncendido +
                ", horaApagado=" + horaApagado +
                ", detectorPresencia=" + detectorPresencia +
                ", accionarCortina=" + accionarCortina +
                ", horaActual=" + horaActual +
                ", estado=" + estado +
                '}';
    }
}