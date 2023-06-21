package com.unla.grupo18.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table(name = "metricaAcondicionarAmbiente")
@PrimaryKeyJoinColumn(referencedColumnName="idMetrica")
public class MetricaAcondicionarAmbiente extends Metrica{

	// (Se toma de la medicion del Dispositivo, compara para determinar el comportamiento de nuestro disp.
	@Column(name="temperaturaActual")
	private float temperaturaActual;
	

}
