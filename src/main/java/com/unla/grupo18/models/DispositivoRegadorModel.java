package com.unla.grupo18.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DispositivoRegadorModel extends DispositivoModel {
	private float humedadActual;
	private float humedadActivarRegador;
	private boolean estaPrendido;
}
