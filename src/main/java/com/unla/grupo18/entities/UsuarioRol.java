package com.unla.grupo18.entities;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "usuario_rol", uniqueConstraints = @UniqueConstraint(columnNames = { "rol", "id_usuario" }))
public class UsuarioRol {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_usuario", nullable = false)
	private Usuario usuario;

	@Column(name = "rol", nullable = false, length = 100)
	private String rol;

	@Column(name = "createdat")
	@CreationTimestamp
	private LocalDateTime createdAt;

	@Column(name = "updatedat")
	@UpdateTimestamp
	private LocalDateTime updatedAt;

	public UsuarioRol(int id, Usuario usuario, String rol) {
		this.id = id;
		this.usuario = usuario;
		this.rol = rol;
	}
}
