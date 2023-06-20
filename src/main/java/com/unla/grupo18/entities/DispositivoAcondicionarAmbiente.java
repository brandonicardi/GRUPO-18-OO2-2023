package com.unla.grupo18.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
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
	
	/*
	// (Se toma de la medicion del Dispositivo, compara para determinar el comportamiento de nuestro disp.
	@Column(name="temperaturaActual")
	private float temperaturaActual;
	*/
	
	//(Para comparar con temperatura actual, siendo este mayor, debe enfriar)
	@Column(name="temperaturaActivarFrio")
	private float temperaturaActivarFrio;
	
	// (Para comparar con temperatura actual, siendo este menor, debe calefaccionar)
	@Column(name="temperaturaActivarCalor")
	private float temperaturaActivarCalor;

	// (Por defecto false - Si hay personas en el lugar)
	@Column(name="sensorPresencia")
	private boolean sensorPresencia;

	// (Por defecto false, indica si el aire está encendido o no)
	@Column(name="estado")
	private boolean estado; // 
	
	// (Por defecto Apagado) 
	// (Nos indica la siguientes 3 leyendas: // Apagado - Frio - Calor)
	// (Alternata su valor si se activo frio o calor o nunca encendio)
	@Column(name="modoAire")
	private String modoAire; 
	
	// ==================  CONSTRUCTOR  ==================
	public DispositivoAcondicionarAmbiente(String nombreDispositivo, LocalDateTime fechaCreacion,
			LocalDateTime fechaModificacion, LocalDateTime fechaBaja, boolean isBaja, Edificio edificio,
			Aula aula,float temperaturaActivarFrio, float temperaturaActivarCalor) {
		super(nombreDispositivo, fechaCreacion, fechaModificacion, fechaBaja, isBaja, edificio);
		this.aula = aula;
		//this.temperaturaActual = temperaturaActual;
		this.temperaturaActivarFrio = temperaturaActivarFrio;
		this.temperaturaActivarCalor = temperaturaActivarCalor;
		this.sensorPresencia = false;
		this.estado = false;
		this.modoAire = "apagado";
	}

	@Override
	public String toString() {
		return "DispositivoAcondicionarAmbiente [aula=" + aula +  
				"\ntemperaturaActivarFrio=" + temperaturaActivarFrio + 
				"\ntemperaturaActivarCalor="+ temperaturaActivarCalor + 
				"\nsensorPresencia=" + sensorPresencia + 
				"\nestado=" + estado + 
				"\nmodoAire=" + modoAire+
				"\n";
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
