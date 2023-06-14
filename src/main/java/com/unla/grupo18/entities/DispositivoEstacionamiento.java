package com.unla.grupo18.entities;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Table(name = "dispositivo_estacionamiento")
@PrimaryKeyJoinColumn(referencedColumnName = "idDispositivo")

public class DispositivoEstacionamiento {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected int idEstacionamiento;
	@Column(name = "listaDeEspacios")
	private Set<Integer> listaEspacios;

	public DispositivoEstacionamiento(Set<Integer> listaEspacios) {
		super();
		this.listaEspacios = new HashSet();
	}

	public Set<Integer> getListaEspacios() {
		return listaEspacios;
	}

	public void setListaEspacios(Set<Integer> listaEspacios) {
		this.listaEspacios = listaEspacios;
	}

	@Override
	public String toString() {
		return "DispositivoEstacionamiento [listaEspacios=" + listaEspacios + "]";
	}

}
