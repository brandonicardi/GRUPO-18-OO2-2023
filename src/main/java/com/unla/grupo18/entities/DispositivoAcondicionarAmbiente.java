package com.unla.grupo18.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "dispositivoAcondicionarAmbiente")
@PrimaryKeyJoinColumn(referencedColumnName="idDispositivo")
public class DispositivoAcondicionarAmbiente extends Dispositivo {

	// ================== ATRIBUTOS ================== 
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "aula_id")
	private Aula aula;
		
	//(Para comparar con temperatura actual, siendo este mayor, debe enfriar)
	@Column(name="temperaturaActivarFrio")
	//@DecimalMin(value = "20", message = "El valor minimo permitido es 20 por cuestiones del dispositivo")
	//@DecimalMax(value = "50", message = "El valor máximo permitido es 50 por cuestiones del dispositivo")
	//@Min(20)
	//@Max(50)
	private float temperaturaActivarFrio;
	
	// (Para comparar con temperatura actual, siendo este menor, debe calefaccionar)
	@Column(name="temperaturaActivarCalor")
    //@DecimalMin(value = "-10", message = "El valor máximo permitido es -10 por cuestiones del dispositivo")
	//@DecimalMax(value = "50", message = "El valor máximo permitido es -50 por cuestiones del dispositivo")
	//@Min(-10)
	//@Max(50)
	private float temperaturaActivarCalor;
	/*
	// (Por defecto false - Si hay personas en el lugar) --> Lo testeamos en metrica Ambiente (entities - model )
	@Column(name="sensorPresencia")
	private boolean sensorPresencia;
	*/
	// (Por defecto false, indica si el aire está encendido o no)
	@Column(name="estado")
	private boolean estado; // 
		
	// ==================  CONSTRUCTOR  ==================
	public DispositivoAcondicionarAmbiente(String nombreDispositivo, LocalDateTime fechaCreacion,
			LocalDateTime fechaModificacion, LocalDateTime fechaBaja, boolean isBaja, Edificio edificio,
			Aula aula,float temperaturaActivarFrio, float temperaturaActivarCalor) {
		super(nombreDispositivo, fechaCreacion, fechaModificacion, fechaBaja, isBaja, edificio);
		this.aula = aula;
		//this.temperaturaActual = temperaturaActual;
		this.temperaturaActivarFrio = temperaturaActivarFrio;
		this.temperaturaActivarCalor = temperaturaActivarCalor;
		//this.sensorPresencia = false;
		this.estado = false;
		
	}

	@Override
	public String toString() {
		return "DispositivoAcondicionarAmbiente [aula=" + aula +  
				"\ntemperaturaActivarFrio=" + temperaturaActivarFrio + 
				"\ntemperaturaActivarCalor="+ temperaturaActivarCalor + 
				/*"\nsensorPresencia=" + sensorPresencia +*/ 
				"\nestado=" + estado + "\n";
	}

	// ==================  to String ==================
	/*
	@Override
	public String toString() {
		return "DispositivoAcondicionarAmbiente [aula=" + aula + 
				"\ntemperaturaActual=" + temperaturaActual + 
				"\ntemperaturaActivarFrio=" + temperaturaActivarFrio + 
				"\ntemperaturaActivarCalor="+ temperaturaActivarCalor + 
				"\nsensorPresencia=" + sensorPresencia + 
				"\nestado=" + estado + 
				"\nmodoAire=" + modoAire+
				"\n";
	}
	*/
	
	
	
	
	
	


	
}
