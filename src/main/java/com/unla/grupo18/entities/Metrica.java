package com.unla.grupo18.entities;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
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
@Table(name = "metrica")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Metrica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idMetrica;

    @Column(name = "fechahora")
    @CreationTimestamp
    private LocalDateTime fechaHoraMetrica;

    @OneToOne(mappedBy = "metrica")
    private Evento evento;

    // Constructor, getters y setters
}