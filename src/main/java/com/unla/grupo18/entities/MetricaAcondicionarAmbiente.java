package com.unla.grupo18.entities;

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
@Table(name = "metricaAcondicionarAmbiente")
@PrimaryKeyJoinColumn(referencedColumnName="idMetrica")
public class MetricaAcondicionarAmbiente extends Metrica{

	// (Se toma de la medicion del Dispositivo, compara para determinar el comportamiento de nuestro disp.
	@Column(name="temperaturaActual")
	private float temperaturaActual;

	// (Por defecto false - true en caso de reconocer presencia)
	@Column(name="sensorPresencia")
	private boolean sensorPresencia;
/*
	// Se agrega el atributo dispositivo a metrica, para poder acceder desde metrica a los atributos del dispositivo ya que no permite ingresar desde la superclase a sus atributos 
	@ManyToOne
	@JoinColumn(name = "dispositivo_acondicionar_ambiente_id")
	private DispositivoAcondicionarAmbiente dispositivo;
*/
}
