package com.unla.grupo18.entities;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = "aula")
public class Aula {

	// ATRIBUTOS
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected int idAula;

	@Column(name = "numero")
	protected int numero;

	// Bidireccional
	@JsonBackReference
	@ManyToOne(fetch = FetchType.LAZY) // Lazy trae un solo edificio
	@JoinColumn(name = "idEdificio")
	protected Edificio edificio;


	// CONSTRUCTOR
	public Aula(int numero, Edificio edificio) {
		this.numero = numero;
		this.edificio = edificio;
	}
	
	@Override
	public String toString() {
		return "Numero de aula: " + numero + " - ID de Aula: " + idAula;
	}
	
}
