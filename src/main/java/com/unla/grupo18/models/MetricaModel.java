package com.unla.grupo18.models;

import java.time.LocalDateTime;
import com.unla.grupo18.entities.Dispositivo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MetricaModel {

	private int idMetrica;
	
	private Dispositivo dispositivo;
	
	private LocalDateTime fechaHoraMetrica;

	@Override
	public String toString() {
		return "idMetrica: " + idMetrica + 
				" dispositivo: " + dispositivo + 
				" fechaHoraMetrica: " + fechaHoraMetrica;
	}
	
	
	
}
