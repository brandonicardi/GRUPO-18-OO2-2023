package com.unla.grupo18.entities;

import java.time.LocalDate;
import java.time.LocalTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@PrimaryKeyJoinColumn(referencedColumnName="idDispositivo", name= "id_dispositivoAcondicionarAmbiente") 
public class DispositivoAcondicionarAmbiente extends Dispositivo {

	// ================== ATRIBUTOS ================== 
	
	@Column(name="temperaturaActivarFrio")
	private float temperaturaActivarFrio;
	
	@Column(name="temperaturaActivarCalor")
	float temperaturaActivarCalor;
	
	@Column(name="temperaturaActual")
	float temperaturaActual;
	
	@Column(name="estado")
	boolean estado; // Por defecto false, indica si el aire está encendido
	
	@Column(name="modoAire")
	String modoAire; // Apagado - Frio - Calor
	
	@Column(name="fechaActivacion")
	@CreationTimestamp
	LocalDate fechaActivacion;

	@Column(name="horaActivacion")
	@CreationTimestamp
	LocalTime horaActivacion;
	
	// ================== CONSTRUCTOR CON ID ==================
	
	public DispositivoAcondicionarAmbiente(int idDispositivo, String nombreDispositivo, LocalDate fechaCreacion,
			LocalDate fechaModificacion, LocalDate fechaBaja, boolean isBaja, Edificio edificio,
			float temperaturaActivarFrio, float temperaturaActivarCalor, float temperaturaActual,
			LocalDate fechaActivacion, LocalTime horaActivacion ) {
		super(idDispositivo, nombreDispositivo, fechaCreacion, fechaModificacion, fechaBaja, isBaja, edificio);
		this.temperaturaActivarFrio = temperaturaActivarFrio;
		this.temperaturaActivarCalor = temperaturaActivarCalor;
		this.temperaturaActual = temperaturaActual;
		this.estado = false;		// Por defecto falso indicando que el aire esta apagado
		this.modoAire = "apagado";	// Indica un String si esta funciona Frio-Calor
		this.fechaActivacion = fechaActivacion;
		this.horaActivacion = horaActivacion;
	}

	// ================== CONSTRUCTOR SIN ID ==================
	public DispositivoAcondicionarAmbiente(String nombreDispositivo, LocalDate fechaCreacion,
			LocalDate fechaModificacion, LocalDate fechaBaja, boolean isBaja, Edificio edificio,
			float temperaturaActivarFrio, float temperaturaActivarCalor, float temperaturaActual,
			LocalDate fechaActivacion, LocalTime horaActivacion) {
		super(nombreDispositivo, fechaCreacion, fechaModificacion, fechaBaja, isBaja, edificio);
		this.temperaturaActivarFrio = temperaturaActivarFrio;
		this.temperaturaActivarCalor = temperaturaActivarCalor;
		this.temperaturaActual = temperaturaActual;
		this.estado = false;		// Por defecto falso indicando que el aire esta apagado
		this.modoAire = "apagado";	// Indica un String si esta funciona Frio-Calor
		this.fechaActivacion = fechaActivacion;
		this.horaActivacion = horaActivacion;
	} 
	
	// ================== METODOS ==================
	
	// pre: temperaturaActivarFrio y temperaturaActual son del tipo float
	//post: estado pasa a true si temperaturaActual > temperaturaActivarFrio (osea manda señal para prender Aire)
	//		Retorna un int, siendo éste el modoAire
	int activarFrio(float temperaturaActivarFrio, float temperaturaActual) {
		int funcionando = 0; // 0 false - 1 true
		if(temperaturaActual > temperaturaActivarFrio ) {
			estado = true;
			modoAire = "Frio";
			funcionando = 1;
		}
		return funcionando;
	}

	// pre: temperaturaActivarCalor y temperaturaActual son del tipo float
	//post: estado pasa a true si temperaturaActual < temperaturaActivarCalor (osea manda señal para prender Aire)
	//		Retorna un int, siendo éste el modoAire
	int activarCalor(float temperaturaActivarCalor, float temperaturaActual) {
		int funcionando = 0; // 0 false - 1 true
		if(temperaturaActual > temperaturaActivarFrio ) {
			estado = true;
			modoAire = "Calor";
			funcionando = 1;
		}
		return funcionando;
	}
		
	
}
