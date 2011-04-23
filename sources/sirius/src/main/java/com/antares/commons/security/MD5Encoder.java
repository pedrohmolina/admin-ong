package com.antares.commons.security;

import org.acegisecurity.providers.encoding.PasswordEncoder;
import org.springframework.dao.DataAccessException;

import com.antares.commons.util.Utils;

public class MD5Encoder implements PasswordEncoder {

	/* (non-Javadoc)
	 * @see org.acegisecurity.providers.encoding.PasswordEncoder#encodePassword(java.lang.String, java.lang.Object)
	 */
	public String encodePassword(String rawPass, Object salt) throws DataAccessException {
		return Utils.encode(rawPass);
	}

	/* (non-Javadoc)
	 * @see org.acegisecurity.providers.encoding.PasswordEncoder#isPasswordValid(java.lang.String, java.lang.String, java.lang.Object)
	 */
	public boolean isPasswordValid(String encPass, String rawPass, Object salt) throws DataAccessException {
		return encPass.equals(encodePassword(rawPass, salt));
	}
}
