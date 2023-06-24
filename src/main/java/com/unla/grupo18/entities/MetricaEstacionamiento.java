package com.unla.grupo18.entities;

import java.util.Set;

import org.hibernate.annotations.ColumnDefault;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
	@Table(name = "metricaEstacionamiento")
	@PrimaryKeyJoinColumn(referencedColumnName="idMetrica")
	public class MetricaEstacionamiento extends Metrica{
		//estacionamientos
		@Column(name="estado")
		  @ColumnDefault("false")
		private boolean estado;
		
		 @ManyToOne
		    @JoinColumn(name = "dispositivo_estacionamiento_id")
		    private DispositivoEstacionamiento dispositivo;


	}


