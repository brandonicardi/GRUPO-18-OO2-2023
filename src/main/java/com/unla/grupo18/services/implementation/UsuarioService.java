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

import com.unla.grupo18.entities.Usuario;
import com.unla.grupo18.entities.UsuarioRol;
import com.unla.grupo18.repositories.IUsuarioRepository;
import com.unla.grupo18.services.IUsuarioService;


@Service("usuarioService")
public class UsuarioService implements UserDetailsService, IUsuarioService {

	@Autowired
	@Qualifier("usuarioRepository")
	private IUsuarioRepository usuarioRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = usuarioRepository.findByUsernameAndFetchUserRolesEagerly(username);
		return buildUser(usuario, buildGrantedAuthorities(usuario.getusuarioRol()));
	}

	private User buildUser(Usuario usuario, List<GrantedAuthority> grantedAuthorities) {
		return new User(usuario.getnombreusuario(), usuario.getClave(), usuario.isEnabled(),
						true, true, true, //accountNonExpired, credentialsNonExpired, accountNonLocked,
						grantedAuthorities);
	}

	private List<GrantedAuthority> buildGrantedAuthorities(Set<UsuarioRol> usuariosRoles) {
		Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
		for(UsuarioRol usuarioRol: usuariosRoles) {
			grantedAuthorities.add(new SimpleGrantedAuthority(usuarioRol.getRol()));
		}
		return new ArrayList<>(grantedAuthorities);
	}


	@Override
	public List<Usuario> getAll() {
		return this.usuarioRepository.findAll();
	}
}