package com.unla.grupo18.entities;

import java.time.LocalDateTime;

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
@Table(name = "metrica")
@PrimaryKeyJoinColumn(referencedColumnName="idMetrica")
public class Metrica {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idMetrica;
	
	@ManyToOne
	@JoinColumn(name="idDispositivo", nullable=false)
	private Dispositivo dispositivo;
	
	@Column(name="fechaHora")
	private LocalDateTime fechaHoraMetrica;

	
	
}
