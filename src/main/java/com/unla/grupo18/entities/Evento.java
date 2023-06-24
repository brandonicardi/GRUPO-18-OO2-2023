package com.unla.grupo18.entities;

import java.time.LocalDateTime;


import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Data
@NoArgsConstructor
@Getter
@Setter
public class Evento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idEvento;

    @ManyToOne
    @JoinColumn(name = "id_dispositivo", nullable = false)
    @JsonBackReference
    private Dispositivo dispositivo;

    @Column(name = "descripcion")
    private String descripcionEvento;

    @Column(name = "fechahora")
    private LocalDateTime fechahoraEvento;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idMetrica")
    private Metrica metrica;

    public Evento(Dispositivo dispositivo, String descripcionEvento, LocalDateTime fechahoraEvento, Metrica metrica) {
        this.dispositivo = dispositivo;
        this.descripcionEvento = descripcionEvento;
        this.fechahoraEvento = fechahoraEvento;
        this.metrica = metrica;
    }

    // Getters y setters
}