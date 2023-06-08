package com.unla.grupo18.repositories;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.unla.grupo18.entities.Usuario;

@Repository("usuarioRepository")
public interface IUsuarioRepository extends JpaRepository<Usuario, Serializable> {

	@Query("SELECT u FROM Usuario u JOIN FETCH u.usuarioRol WHERE u.nombreusuario = (:nombreusuario)")
	public abstract Usuario findByUsernameAndFetchUserRolesEagerly(@Param("nombreusuario") String nombreusuario);
}
