package com.antares.commons.aop;

import org.aopalliance.intercept.MethodInvocation;

import com.antares.commons.exception.RestrictedAccessException;
import com.antares.commons.util.Utils;
import com.antares.sirius.model.BusinessObject;

/**
 * Interceptor que impide la ejecucion de metodos de escritura de los dao.
 * El primer parámetro de dichos metodos debe ser el objeto de negocio que se quiere escribir. 
 *
 * @version 1.0.0 Created 02/05/2011 by Julian Martinez
 * @author <a href:mailto:otakon@gmail.com> Julian Martinez </a>
 */
public class SecureWriteInterceptor extends SecurityInterceptor {

	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {

		if (invocation.getArguments().length > 0) {
			Object object = invocation.getArguments()[0];
	
			// Primero verifico que sea una clase de negocio
			if (object != null && object instanceof BusinessObject) {
				
				BusinessObject target = (BusinessObject)object;
		        String entityName = target.getClass().getSimpleName();
				
				// Reviso que el usuario este logueado
				if (Utils.isAuthenticated()) {
					String username = Utils.getUsername();
					
					// Filtro evaluo si debe ejecutarse la accion o no
					if (!filterObject(target, username, entityName)) {
						// Ejecuto la invocacion original
						invocation.proceed();
					} else {
						throw new RestrictedAccessException("Restricted Access: Cannot execute " + invocation.getMethod().getName() + " on entity " + entityName);
					}
				}
	
			} else {
				invocation.proceed();
			}
		} else {
			invocation.proceed();
		}
		return null;
	}

}
