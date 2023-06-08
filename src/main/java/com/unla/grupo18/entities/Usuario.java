package com.unla.grupo18.entities;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.util.DigestUtils;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idUsuario;

	@Column(name = "nombreusuario", unique = true, nullable = false, length = 45)
	private String nombreusuario;

	@Column(name = "clave", nullable = false, length = 60)
	private String clave;

	@Column(name = "nombre", nullable = false, length = 45)
	private String nombre;

	@Column(name = "apellido", nullable = false, length = 45)
	private String apellido;

	@Column(name = "dni", unique = true, nullable = false, length = 8)
	private Long dni;

	@Column(name = "email", unique = true, nullable = false, length = 45)
	private String email;

	@Column(name = "enabled", columnDefinition = "boolean default true")
	private boolean enabled;

	@Column(name = "fechacreacion", updatable = false)
	@CreationTimestamp
	private LocalDateTime fechaCreacion;

	@Column(name = "fechamod", nullable = false)
	@UpdateTimestamp
	private LocalDateTime fechaMod;
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="usuario")
	private Set<UsuarioRol> usuarioRol = new HashSet<>();
	
	


	public Usuario(String nombreusuario, String clave, String nombre, String apellido, Long dni, String email,
			boolean enabled, LocalDateTime fechaCreacion, LocalDateTime fechaMod) {
		this.nombreusuario = nombreusuario;
		this.clave = clave;
		this.nombre = nombre;
		this.apellido = apellido;
		this.dni = dni;
		this.email = email;
		this.enabled = enabled;
		this.fechaCreacion = fechaCreacion;
		this.fechaMod = fechaMod;
	}
	
	public Usuario(String nombreusuario, String clave, String nombre, String apellido, Long dni, String email,
			boolean enabled, LocalDateTime fechaCreacion, LocalDateTime fechaMod, Set<UsuarioRol> usuarioRol) {
		this.nombreusuario = nombreusuario;
		this.clave = clave;
		this.nombre = nombre;
		this.apellido = apellido;
		this.dni = dni;
		this.email = email;
		this.enabled = enabled;
		this.fechaCreacion = fechaCreacion;
		this.fechaMod = fechaMod;
		this.usuarioRol = usuarioRol;
	}


}
