package com.unla.grupo18.models;


import org.hibernate.validator.internal.util.StringHelper;

import com.unla.grupo18.entities.UsuarioRol;

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
	private UsuarioRol rol;
	@NotNull
	@Valid
	private String nombre;
	private String apellido;
	@Size(max = 8, message = "No se pueden ingresar mas de 8 digitos")
	private String dni;
	private String email;

	public String nombreApellido() {
		String caseNombre = StringHelper.decapitalize(this.nombre);
		String caseApellido = StringHelper.decapitalize(this.apellido);
		return String.format("%s %s", caseNombre, caseApellido);
	}

	public boolean hasRole(String rol) {
		boolean hasRole = false;
		if (this.rol.getRol().compareTo(rol) == 0) {
			hasRole = true;
		}
		return hasRole;
	}
}
