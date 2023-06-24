package com.unla.grupo18.entities;

import java.util.Objects;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonManagedReference;

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
	private int id; 

	@Column(name = "nombre")
	private String nombre;
	
	// Bidireccional
	@JsonManagedReference
	@OneToMany(mappedBy = "edificio", fetch = FetchType.LAZY) // Trae una sola Aula
	private Set<Aula> aulas;

	// CONSTRUCTOR CON ATRIBUTOS 
	public Edificio(int id, String nombre, Set<Aula> aulas) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.aulas = aulas;
	}
	
	@Override
	public String toString() {
		return nombre + " - id: " + id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(nombre, id);
	}	
	
}
