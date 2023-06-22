package com.unla.grupo18.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.hibernate.annotations.ColumnDefault;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "metrica_alumbrado")
public class MetricaAlumbrado extends Metrica {

    @Column(name = "sensorPresencia")
    @ColumnDefault("false")
    private boolean sensorPresencia;

    @Column(name = "fechaDeteccion")
    private LocalDate fechaDeteccion;
    
    @Column(name = "horaDeteccion")
    private LocalTime horaDeteccion;

    @ManyToOne
    @JoinColumn(name = "dispositivo_alumbrado_id")
    private DispositivoAlumbrado dispositivo;

    // Constructor, getters y setters
}
