package com.antares.sirius.service.impl;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

import org.acegisecurity.userdetails.UserDetails;
import org.acegisecurity.userdetails.UserDetailsService;
import org.acegisecurity.userdetails.UsernameNotFoundException;
import org.springframework.dao.DataAccessException;

import com.antares.commons.service.impl.BusinessEntityServiceImpl;
import com.antares.sirius.dao.UsuarioDAO;
import com.antares.sirius.model.Usuario;
import com.antares.sirius.service.UsuarioService;

/**
 * Implementacion de la interfaz UsuarioService.
 *
 * @version 1.0.0 Created 23/11/2010 by Julian Martinez
 * @author <a href:mailto:otakon@gmail.com> Julian Martinez </a>
 *
 */
public class UsuarioServiceImpl extends BusinessEntityServiceImpl<Usuario, UsuarioDAO> implements UsuarioService, UserDetailsService {

	public boolean isNombreRepetido(String username, Integer id) {
		boolean isNombreRepetido = false;
		Usuario entity = dao.findByUsername(username);
		if (entity != null) {
			isNombreRepetido = !entity.getId().equals(id);
		}
		return isNombreRepetido;
	}

	public Usuario findByUsername(String username) {
		return dao.findByUsername(username);
	}

	public void ejecutarBloqueo(Usuario entity) {
		entity.setBloqueado(TRUE);
		dao.save(entity);
	}

	public void ejecutarDesbloqueo(Usuario entity) {
		entity.setBloqueado(FALSE);
		dao.save(entity);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException {
		Usuario usuario = dao.findByUsername(username);
		if (usuario == null || usuario.getBloqueado()) {
			throw new UsernameNotFoundException("User not found.");
		}
		return usuario;
	}

}
