package com.unla.grupo18.entities;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "aula")
public class Aula {

	// ATRIBUTOS
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected int idAula;

	@Column(name = "numero")
	protected int numero;

	// Bidireccional
	@ManyToOne(fetch = FetchType.LAZY) // Lazy trae un solo edificio
	@JoinColumn(name = "idEdificio")
	protected Edificio edificio;

	// CONSTRUCTOR
	public Aula(int idAula, int numero, Edificio edificio) {
		super();
		this.idAula = idAula;
		this.numero = numero;
		this.edificio = edificio;
	}
	

	@Override
	public int hashCode() {
		return Objects.hash(edificio, idAula, numero);
	}

	@Override
	public boolean equals(Object obj) {
		Aula other = (Aula) obj;
		return this.idAula == other.idAula;
	}

	@Override
	public String toString() {
		return "idAula: " + idAula 
				+ "\nnumero:" + numero; 
	}
	
}
