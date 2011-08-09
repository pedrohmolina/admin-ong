<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tlds/struts-bean-el.tld" prefix="bean-el"%>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/tlds/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/tlds/struts-nested.tld" prefix="nested"%>
<%@ taglib uri="/WEB-INF/tlds/displaytag.tld" prefix="display"%>
<%@ taglib uri="/WEB-INF/tlds/displaytag-el.tld" prefix="display-el"%>
<%@ taglib uri="/WEB-INF/tlds/authz.tld" prefix="authz"%>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c"%>

<script>
function limpiarFiltro(){
	hacerSubmit('usuario/usuario-query.do?method=initQuery');
}

function confirmarAccion(mensaje) {
	return confirm(mensaje);
}

</script>

<div class="form">
	<html:form action="/usuario/usuario-query-validate.do?method=query">
	<div style="float:left; width: 100%;">
		<p>
		<label for="filtroUsername"><bean:message key="sirius.usuario.username.label" />:</label>
		<html:text property="filtroUsername" />
		</p><br><p>
		<label for="filtroIdPerfil"><bean:message key="sirius.usuario.relacionContractual.label" />:</label>
		<html:select property="filtroIdPerfil">
			<html:option value=""><bean:message key="antares.base.seleccione.label"/></html:option>
			<html:optionsCollection name="usuarioQuery" property="perfiles" label="nombre" value="id"/>
		</html:select>
		</p><br>
	</div>

	<div style="float: left; width: 100%;">
		<div class="boton">
			<a href="#" onclick="javascript:limpiarFiltro();"><bean:message key="antares.base.limpiarfiltro.label"/></a>
			<authz:authorize ifAllGranted="ENTIDAD_USUARIO-LISTADO">
				<a href="#" onclick="usuarioQuery.submit();"><bean:message key="antares.base.buscar.label" /></a>
			</authz:authorize>
			<authz:authorize ifAllGranted="ENTIDAD_USUARIO-ALTA">
				<a href="#" onclick="return hacerSubmit('usuario/usuario-form.do?method=initCreate');"><bean:message key="antares.base.nuevo.label" /></a>
			</authz:authorize>
		</div>
	</div>
	
	</html:form>
	
	<div style="clear: both;" class="errores">
		<html:errors />
	</div>

	<h1><bean:message key="antares.base.result.label" /></h1>
	<display-el:table export="true" defaultsort="1" pagesize="${requestScope['displayTagPageSize']}" class="tabla" name="sessionScope.usuarioQuery.result" id="item"
		requestURI="/usuario/usuario-query.do" sort="list" >

		<c:if test="${not empty requestScope['notShowMessage']}">
			<display:setProperty name="basic.msg.empty_list"><table width="100%" border="0" cellspacing="0" cellpadding="0" class="tablaTitulo"><tr><td align="center"></td></tr></table></display:setProperty>
		</c:if>
	
		<display:column sortable="true" property="username" 							titleKey="sirius.usuario.username.label" maxLength="30" />
		<display:column sortable="true" property="perfil.nombre" 						titleKey="sirius.usuario.perfil.label" maxLength="30" />
		<display:column sortable="true" property="persona.apellido" 					titleKey="sirius.usuario.apellido.label" maxLength="30" />
		<display:column sortable="true" property="persona.nombre" 						titleKey="sirius.usuario.nombre.label" maxLength="30" />

		<display:column title="Acciones" media="html" style="text-align: center">
			<authz:authorize ifAllGranted="ENTIDAD_USUARIO-DETALLE">
				<a href="<c:url value="/usuario/usuario-form.do?method=view&id="/><bean:write name="item" property="id"/>"><img border="0" alt="Visualizar" title="Visualizar"
					src="<c:url value="/img/icon.lupa.gif"/>" /></a>
			</authz:authorize>
			<authz:authorize ifAllGranted="ENTIDAD_USUARIO-MODIFICACION">
				<a href="<c:url value="/usuario/usuario-form.do?method=initUpdate&id="/><bean:write name="item" property="id"/>"><img border="0" alt="Editar" title="Modificar"
					src="<c:url value="/img/icoModificar.gif"/>" /></a>
			</authz:authorize>
			<authz:authorize ifAllGranted="ENTIDAD_USUARIO-BLOQUEAR">
				<logic:equal name="item" property="bloqueado" value="false">
					<a href="<c:url value="/usuario/usuario-query.do?method=bloquear&id="/><bean:write name="item" property="id"/>"><img border="0" alt="Bloquear" title="Bloquear"
						src="<c:url value="/img/disable_user.gif"/>" onclick="return confirmarAccion('Est&aacute; seguro que desea bloquear el usuario <bean:write name="item" property="username"/>?')" /></a>
				</logic:equal>
			</authz:authorize>
			<authz:authorize ifAllGranted="ENTIDAD_USUARIO-DESBLOQUEAR">
				<logic:equal name="item" property="bloqueado" value="true">
					<a href="<c:url value="/usuario/usuario-query.do?method=desbloquear&id="/><bean:write name="item" property="id"/>"><img border="0" alt="Desbloquear" title="Desbloquear"
						src="<c:url value="/img/enable_user.gif"/>" onclick="return confirmarAccion('Est&aacute; seguro que desea desbloquear el usuario <bean:write name="item" property="username"/>?')" /></a>
				</logic:equal>
			</authz:authorize>
			<authz:authorize ifAllGranted="ENTIDAD_USUARIO-CAMBIAR_PASSWORD">
				<a href="<c:url value="/usuario/usuario-password-form.do?method=initCambiarPassword&id="/><bean:write name="item" property="id"/>"><img border="0" alt="Cambiar Contrase&ntilde;a" title="Cambiar Contrase&ntilde;a"
					src="<c:url value="/img/key.png"/>" /></a>
			</authz:authorize>
		</display:column>
	</display-el:table>
</div>
