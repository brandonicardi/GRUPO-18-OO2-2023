package com.unla.grupo18.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "dispositivoRegador")
@PrimaryKeyJoinColumn(referencedColumnName="idDispositivo")
public class DispositivoRegador extends Dispositivo {
	
	@Column(name="humedadActual")
	private float humedadActual;
	
	@Column(name="humedadActivarRegador")
	private float humedadActivarRegador;
	
	@Column(name="estaPrendido")
	private boolean estaPrendido;
	
	public DispositivoRegador(int idDispositivo, String nombreDispositivo, LocalDateTime fechaCreacion,
			LocalDateTime fechaModificacion, LocalDateTime fechaBaja, boolean isBaja, float humedadActual,
			float humedadActivarRegador) {
		super(idDispositivo, nombreDispositivo, fechaCreacion, fechaModificacion, fechaBaja, isBaja);
		this.humedadActual = humedadActual;
		this.humedadActivarRegador = humedadActivarRegador;
		this.estaPrendido = false; 
	}
	
}
