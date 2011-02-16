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
	<html:form action="/usuario/usuario-query.do?method=query">
	<h1>Búsqueda de Usuarios</h1>
	<div style="float:left; width: 100%;">
		<label for="filtroApellido"><bean:message key="sirius.usuario.apellido.label" />:</label>
		<html:text property="filtroApellido" />
		<br>
		<label for="filtroNombre"><bean:message key="sirius.usuario.nombre.label" />:</label>
		<html:text property="filtroNombre" />
		<br>
		<label for="filtroCuit"><bean:message key="sirius.usuario.cuit.label" />:</label>
		<html:text property="filtroCuit" />
		<br>
		<label for="filtroNumeroDocumento"><bean:message key="sirius.usuario.numeroDocumento.label" />:</label>
		<html:text property="filtroNumeroDocumento" />
		<br>
		<label for="filtroIdRelacionContractual"><bean:message key="sirius.usuario.relacionContractual.label" />:</label>
		<html:select property="filtroIdRelacionContractual">
			<html:option value=""><bean:message key="antares.base.seleccione.label"/></html:option>
			<html:optionsCollection name="usuarioForm" property="relacionesContractuales" label="descripcion" value="id"/>
		</html:select>
		<br>
	</div>

	<div style="float: left; width: 100%;">
		<div class="boton">
			<a href="#" onclick="javascript:limpiarFiltro();"><bean:message key="antares.base.limpiarfiltro.label"/></a>
			<a href="#" onclick="usuarioForm.submit();"><bean:message key="antares.base.buscar.label" /></a>
			<a href="#" onclick="return hacerSubmit('usuario/usuario-form.do?method=initCreate');"><bean:message key="antares.base.nuevo.label" /></a>
		</div>
	</div>
	
	</html:form>
	
	<div style="clear: both;">
		<html:errors />
	</div>

	<h1>Resultado de la Búsqueda</h1>
	<display-el:table export="true" defaultsort="1" pagesize="${requestScope['displayTagPageSize']}" class="tabla" name="sessionScope.usuarioForm.result" id="item"
		requestURI="/usuario/usuario-query.do" sort="list" >

		<c:if test="${not empty requestScope['notShowMessage']}">
			<display:setProperty name="basic.msg.empty_list"><table width="100%" border="0" cellspacing="0" cellpadding="0" class="tablaTitulo"><tr><td align="center"></td></tr></table></display:setProperty>
		</c:if>
	
		<display:column sortable="true" property="username" 							titleKey="sirius.usuario.username.label" />
		<display:column sortable="true" property="perfil.nombre" 						titleKey="sirius.usuario.perfil.label" />
		<display:column sortable="true" property="persona.apellido" 					titleKey="sirius.usuario.apellido.label" />
		<display:column sortable="true" property="persona.nombre" 						titleKey="sirius.usuario.nombre.label" />
		<display:column sortable="true" property="persona.tipoDocumento.descripcion"	titleKey="sirius.usuario.tipoDocumento.label" />
		<display:column sortable="true" property="persona.numeroDocumento" 				titleKey="sirius.usuario.numeroDocumento.label" />

		<display:column title="Acciones" media="html">
			<a href="<c:url value="/usuario/usuario-form.do?method=view&id="/><bean:write name="item" property="id"/>"><img border="0" alt="Visualizar" title="Visualizar"
				src="<c:url value="/img/icon.lupa.gif"/>" /></a>
			<a href="<c:url value="/usuario/usuario-form.do?method=initUpdate&id="/><bean:write name="item" property="id"/>"><img border="0" alt="Editar" title="Modificar"
				src="<c:url value="/img/icoModificar.gif"/>" /></a>
			<logic:equal name="item" property="bloqueado" value="false">
				<a href="<c:url value="/usuario/usuario-query.do?method=bloquear&id="/><bean:write name="item" property="id"/>"><img border="0" alt="Bloquear" title="Bloquear"
					src="<c:url value="/img/disable_user.gif"/>" onclick="return confirmarAccion('Est&aacute; seguro que desea bloquear el usuario <bean:write name="item" property="username"/>?')" /></a>
			</logic:equal>
			<logic:equal name="item" property="bloqueado" value="true">
				<a href="<c:url value="/usuario/usuario-query.do?method=desbloquear&id="/><bean:write name="item" property="id"/>"><img border="0" alt="Desbloquear" title="Desbloquear"
					src="<c:url value="/img/enable_user.gif"/>" onclick="return confirmarAccion('Est&aacute; seguro que desea desbloquear el usuario <bean:write name="item" property="username"/>?')" /></a>
			</logic:equal>
		</display:column>
	</display-el:table>
</div>
