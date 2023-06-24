package com.unla.grupo18.models;


import org.hibernate.validator.internal.util.StringHelper;

import com.unla.grupo18.entities.UserRole;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data @NoArgsConstructor @AllArgsConstructor
public class UsuarioModel {
	private int idUsuario;
	private String nombreusuario;
	private String clave;
	private boolean enabled;
	private UserRole rol;
	@NotNull
	@Valid
	private String nombre;
	private String apellido;
	@Size(max = 8, message = "No se pueden ingresar mas de 8 digitos")
	private String dni;
	private String email;
	
}
