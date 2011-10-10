package com.antares.commons.security;

import org.acegisecurity.GrantedAuthority;
import org.acegisecurity.ldap.LdapDataAccessException;
import org.acegisecurity.providers.ldap.LdapAuthoritiesPopulator;
import org.acegisecurity.userdetails.UserDetails;
import org.acegisecurity.userdetails.ldap.LdapUserDetails;

import com.antares.sirius.service.UsuarioService;

public class DbAuthoritiesPopulatorImpl implements LdapAuthoritiesPopulator {

	private UsuarioService usuarioService;

	@Override
	public GrantedAuthority[] getGrantedAuthorities(LdapUserDetails userDetails) throws LdapDataAccessException {
		UserDetails usuario = usuarioService.loadUserByUsername(userDetails.getUsername());
		return usuario.getAuthorities();
	}

	public void setUsuarioService(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}

}
