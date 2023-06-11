package com.unla.grupo18.entities;

import java.util.Objects;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "edificio")
public class Edificio {

	// ATRIBUTOS
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idEdificio; 

	@Column(name = "edificio")
	private String edificio;
	
	//Bidireccional
	@OneToMany(mappedBy = "edificio", fetch = FetchType.LAZY) //trae una sola Aula
	private Set<Aula> aulas;

	// CONSTRUCTOR CON ATRIBUTOS 
	public Edificio(int idEdificio, String edificio, Set<Aula> aulas) {
		super();
		this.idEdificio = idEdificio;
		this.edificio = edificio;
		this.aulas = aulas;
	}
	
	@Override
	public String toString() {
		return "idEdificio: " + idEdificio 
				+ "\nNombreEdificio: " + edificio 
				+ "\naulas: " + aulas;
	}

	@Override
	public int hashCode() {
		return Objects.hash(edificio, idEdificio);
	}	
	
}
