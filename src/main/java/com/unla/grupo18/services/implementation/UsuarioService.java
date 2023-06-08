package com.unla.grupo18.services.implementation;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.unla.grupo18.entities.UsuarioRol;
import com.unla.grupo18.repositories.IUsuarioRepository;

@Service("usuarioService")
public class UsuarioService implements UserDetailsService {

	@Autowired
	@Qualifier("usuarioRepository")
	private IUsuarioRepository usuarioRepository;

	@Override
	public UserDetails loadUserByUsername(String nombreusuario) throws UsernameNotFoundException {
		com.unla.grupo18.entities.Usuario usuario = usuarioRepository.findByUsernameAndFetchUserRolesEagerly(nombreusuario);
		return buildUser(usuario, buildGrantedAuthorities(usuario.getUsuarioRol()));
	}

	private User buildUser(com.unla.grupo18.entities.Usuario usuario, List<GrantedAuthority> grantedAuthorities) {
		return new User(usuario.getNombreusuario(), usuario.getClave(), usuario.isEnabled(),
						true, true, true, //accountNonExpired, credentialsNonExpired, accountNonLocked,
						grantedAuthorities);
	}

	private List<GrantedAuthority> buildGrantedAuthorities(Set<UsuarioRol> usuarioRol) {
		Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
		for(UsuarioRol usuarioRol1: usuarioRol) {
			grantedAuthorities.add(new SimpleGrantedAuthority(usuarioRol1.getRol()));
		}
		return new ArrayList<>(grantedAuthorities);
	}
}