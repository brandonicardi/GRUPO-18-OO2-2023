package com.unla.grupo18.entities;

import java.time.LocalDateTime;


import org.hibernate.annotations.ColumnDefault;

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
//@NoArgsConstructor
@AllArgsConstructor
@Table(name = "dispositivoRegador")
@PrimaryKeyJoinColumn(referencedColumnName="idDispositivo")
public class DispositivoRegador extends Dispositivo {
	
	@Column(name="humedadActual")
	private float humedadActual;
	
	@Column(name="humedadPrenderRegador")
	//@ColumnDefault("30.0")
	private float humedadPrenderRegador;
	
	@Column(name="humedadApagarRegador")
	//@ColumnDefault("90")
	private float humedadApagarRegador;
	
	@Column(name="estaPrendido")
	private boolean estaPrendido;
	
	@Column(name="zona")
	private String zona;
	
	
	public DispositivoRegador() {
		super();
		//Agrego los valores predeterminados
		this.estaPrendido = false;
		this.humedadPrenderRegador = 30;
		this.humedadApagarRegador = 90;
	}
	
	
}
