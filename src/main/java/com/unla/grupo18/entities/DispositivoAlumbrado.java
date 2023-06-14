package com.unla.grupo18.entities;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "dispositivo_alumbrado")
@PrimaryKeyJoinColumn(referencedColumnName="idDispositivo") 
public class DispositivoAlumbrado extends Dispositivo {	

	@Column(name="horadeencendido")
	@CreationTimestamp
	private LocalTime horadeEncendido;
	
	@Column(name="horadeapagado")
	@CreationTimestamp
	private LocalTime horadeApagado;
	
	@Column(name="detector_presencia")
	private boolean detectorPresencia;
	
	@Column(name="accionar_cortina")
	private boolean accionarCortina;

	@Column(name="hora_actual")
	private LocalTime horaActual;
	
	@Column(name="estado")
	boolean estado; // Por defecto indica si // true

	public DispositivoAlumbrado(String nombreDispositivo, LocalDate fechaCreacion, LocalDate fechaModificacion,
			LocalDate fechaBaja, boolean isBaja, Edificio edificio, LocalTime horadeEncendido, LocalTime horadeApagado,
			boolean detectorPresencia, boolean accionarCortina, LocalTime horaActual, boolean estado) {
		super(nombreDispositivo, fechaCreacion, fechaModificacion, fechaBaja, isBaja, edificio);
		this.horadeEncendido = horadeEncendido;
		this.horadeApagado = horadeApagado;
		this.detectorPresencia = detectorPresencia;
		this.accionarCortina = accionarCortina;
		this.horaActual = horaActual;
		this.estado = estado;
	}

	public DispositivoAlumbrado(int idDispositivo, String nombreDispositivo, LocalDate fechaCreacion,
			LocalDate fechaModificacion, LocalDate fechaBaja, boolean isBaja, Edificio edificio,
			LocalTime horadeEncendido, LocalTime horadeApagado, boolean detectorPresencia, boolean accionarCortina,
			LocalTime horaActual, boolean estado) {
		super(idDispositivo, nombreDispositivo, fechaCreacion, fechaModificacion, fechaBaja, isBaja, edificio);
		this.horadeEncendido = horadeEncendido;
		this.horadeApagado = horadeApagado;
		this.detectorPresencia = detectorPresencia;
		this.accionarCortina = accionarCortina;
		this.horaActual = horaActual;
		this.estado = estado;
	}

	public DispositivoAlumbrado(int idDispositivo, String nombreDispositivo, LocalDate fechaCreacion,
			LocalDate fechaModificacion, LocalDate fechaBaja, boolean isBaja, LocalTime horadeEncendido,
			LocalTime horadeApagado, boolean detectorPresencia, boolean accionarCortina, LocalTime horaActual,
			boolean estado) {
		super(idDispositivo, nombreDispositivo, fechaCreacion, fechaModificacion, fechaBaja, isBaja);
		this.horadeEncendido = horadeEncendido;
		this.horadeApagado = horadeApagado;
		this.detectorPresencia = detectorPresencia;
		this.accionarCortina = accionarCortina;
		this.horaActual = horaActual;
		this.estado = estado;
	}
	
	
}


	
	