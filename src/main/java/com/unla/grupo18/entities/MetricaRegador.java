package com.unla.grupo18.entities;

import java.time.LocalDateTime;

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
@Table(name = "metricaRegador")
@PrimaryKeyJoinColumn(referencedColumnName="idMetrica")
public class MetricaRegador extends Metrica {
	
	private float humedadMedida;
	
}
