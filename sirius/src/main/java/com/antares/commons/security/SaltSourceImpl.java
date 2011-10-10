package com.antares.commons.security;

import org.acegisecurity.providers.dao.SaltSource;
import org.acegisecurity.userdetails.UserDetails;

public class SaltSourceImpl implements SaltSource {

	@Override
	public Object getSalt(UserDetails user) {
		return user.getUsername();
	}

}
