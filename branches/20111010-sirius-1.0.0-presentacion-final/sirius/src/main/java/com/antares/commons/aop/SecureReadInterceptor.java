package com.antares.commons.aop;

import java.lang.reflect.ParameterizedType;

import org.aopalliance.intercept.MethodInvocation;

import com.antares.commons.util.Utils;
import com.antares.sirius.model.BusinessObject;

/**
 * Interceptor para el manejo de la seguridad por valor en los métodos find de los DAO
 *
 * @version 1.0.0 Created 01/05/2011 by Julian Martinez
 * @author <a href:mailto:otakon@gmail.com> Julian Martinez </a>
 */
public class SecureReadInterceptor extends SecurityInterceptor {

	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		Object returnValue = invocation.proceed();

		Object target = invocation.getThis();
		ParameterizedType type = Utils.findParameterizedType(target.getClass());
		Class<?> entityClass = (Class<?>)type.getActualTypeArguments()[0];

		// Primero verifico que sea una clase de negocio
		if (BusinessObject.class.isAssignableFrom(entityClass)) {
	        String entityName = entityClass.getSimpleName();

			// Reviso que el usuario este logueado
			if (Utils.isAuthenticated()) {
				String username = Utils.getUsername();

				// Me aseguro que el resultado devuelto por el metodo no este vacio
				if (returnValue != null) {
					// Filtro el resultado aplicando las reglas de seguridad por valor
					returnValue = filterReturnValue(returnValue, username, entityName);
				}
			}
		}
		return returnValue;
	}

}
