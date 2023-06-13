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
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "dispositivo_luces")
@PrimaryKeyJoinColumn(referencedColumnName="idDispositivo") 
public class DisLucesAuto extends Dispositivo {	
	
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "aula_id")
    private Aula aula;
	
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

	public DisLucesAuto(int idDispositivo, String nombreDispositivo, LocalDate fechaCreacion,
			LocalDate fechaModificacion, LocalDate fechaBaja, boolean estaActivo, Edificio edificio, Aula aula,
			LocalTime horadeEncendido, LocalTime horadeApagado, boolean detectorPresencia, boolean accionarCortina,
			LocalTime horaActual, boolean estado) {
		super(idDispositivo, nombreDispositivo, fechaCreacion, fechaModificacion, fechaBaja, estaActivo, edificio);
		this.aula = aula;
		this.horadeEncendido = horadeEncendido;
		this.horadeApagado = horadeApagado;
		this.detectorPresencia = detectorPresencia;
		this.accionarCortina = accionarCortina;
		this.horaActual = horaActual;
		this.estado = estado;
	}

	public DisLucesAuto(String nombreDispositivo, LocalDate fechaCreacion, LocalDate fechaModificacion,
			LocalDate fechaBaja, boolean estaActivo, Edificio edificio, Aula aula, LocalTime horadeEncendido,
			LocalTime horadeApagado, boolean detectorPresencia, boolean accionarCortina, LocalTime horaActual,
			boolean estado) {
		super(nombreDispositivo, fechaCreacion, fechaModificacion, fechaBaja, estaActivo, edificio);
		this.aula = aula;
		this.horadeEncendido = horadeEncendido;
		this.horadeApagado = horadeApagado;
		this.detectorPresencia = detectorPresencia;
		this.accionarCortina = accionarCortina;
		this.horaActual = horaActual;
		this.estado = estado;
	}

	public DisLucesAuto(int idDispositivo, String nombreDispositivo, LocalDate fechaCreacion,
			LocalDate fechaModificacion, LocalDate fechaBaja, boolean estaActivo, Aula aula, LocalTime horadeEncendido,
			LocalTime horadeApagado, boolean detectorPresencia, boolean accionarCortina, LocalTime horaActual,
			boolean estado) {
		super(idDispositivo, nombreDispositivo, fechaCreacion, fechaModificacion, fechaBaja, estaActivo);
		this.aula = aula;
		this.horadeEncendido = horadeEncendido;
		this.horadeApagado = horadeApagado;
		this.detectorPresencia = detectorPresencia;
		this.accionarCortina = accionarCortina;
		this.horaActual = horaActual;
		this.estado = estado;
	}


	public DisLucesAuto(String nombreDispositivo, LocalDate fechaCreacion, LocalDate fechaModificacion,
			LocalDate fechaBaja, boolean estaActivo, Edificio edificio) {
		super(nombreDispositivo, fechaCreacion, fechaModificacion, fechaBaja, estaActivo, edificio);
	}

	@Override
	public String toString() {
		return "DisLucesAuto [aula=" + aula + ", horadeEncendido=" + horadeEncendido + ", horadeApagado="
				+ horadeApagado + ", detectorPresencia=" + detectorPresencia + ", accionarCortina=" + accionarCortina
				+ ", horaActual=" + horaActual + ", estado=" + estado + "]";
	}
	
	

	
	
	
	
	
	
	
	
}


	
	