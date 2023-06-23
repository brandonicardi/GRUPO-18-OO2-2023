package com.unla.grupo18.models;

import java.time.LocalDate;

import com.unla.grupo18.entities.Aula;
import com.unla.grupo18.entities.Edificio;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DispositivoAcondicionarAmbienteModel extends DispositivoModel{

	// ================== ATRIBUTOS ================== 
	private Aula aula;
	private float temperaturaActual;
	private float temperaturaActivarFrio;
	private float temperaturaActivarCalor;
	// private boolean sensorPresencia; Se testea en Metrica, corregir en entities - modelo - 
	private boolean estado; 
	
	
	
	@Override
	public String toString() {
		return "DispositivoAcondicionarAmbienteModel [aula=" + aula + ", temperaturaActual=" + temperaturaActual
				+ ", temperaturaActivarFrio=" + temperaturaActivarFrio + ", temperaturaActivarCalor="
				+ temperaturaActivarCalor + /*", sensorPresencia=" + sensorPresencia + */", estado=" + estado
				+ "]";
	}
	
	
	

	


}
