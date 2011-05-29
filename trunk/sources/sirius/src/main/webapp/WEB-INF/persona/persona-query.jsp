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
	hacerSubmit('persona/persona-query.do?method=initQuery');
}

function confirmarAccion(mensaje) {
	return confirm(mensaje);
}

</script>

<div class="form">
	<html:form action="/persona/persona-query.do?method=query">
	<h1>Búsqueda de Personas</h1>
	<div style="float:left; width: 100%;">
	<p>
		<label for="filtroApellido"><bean:message key="sirius.persona.apellido.label" />:</label>
		<html:text property="filtroApellido" />
		</p><br><p>
		<label for="filtroNombre"><bean:message key="sirius.persona.nombre.label" />:</label>
		<html:text property="filtroNombre" />
		</p><br><p>
		<label for="filtroCuit"><bean:message key="sirius.persona.cuit.label" />:</label>
		<html:text property="filtroCuit" />
		</p><br><p>
		<label for="filtroNumeroDocumento"><bean:message key="sirius.persona.numeroDocumento.label" />:</label>
		<html:text property="filtroNumeroDocumento" />
		</p><br><p>
		<label for="filtroIdRelacionContractual"><bean:message key="sirius.persona.relacionContractual.label" />:</label>
		<html:select property="filtroIdRelacionContractual">
			<html:option value=""><bean:message key="antares.base.seleccione.label"/></html:option>
			<html:optionsCollection name="personaForm" property="relacionesContractuales" label="descripcion" value="id"/>
		</html:select>
		</p><br>
	</div>

	<div style="float: left; width: 100%;">
		<div class="boton">
			<a href="#" onclick="javascript:limpiarFiltro();"><bean:message key="antares.base.limpiarfiltro.label"/></a>
			<authz:authorize ifAllGranted="ENTIDAD_PERSONA-LISTADO">
				<a href="#" onclick="personaForm.submit();"><bean:message key="antares.base.buscar.label" /></a>
			</authz:authorize>
			<authz:authorize ifAllGranted="ENTIDAD_PERSONA-ALTA">
				<a href="#" onclick="return hacerSubmit('persona/persona-form.do?method=initCreate');"><bean:message key="antares.base.nuevo.label" /></a>
			</authz:authorize>
		</div>
	</div>
	
	</html:form>
	
	<div style="clear: both;">
		<html:errors />
	</div>

	<h1>Resultado de la Búsqueda</h1>
	<display-el:table export="true" defaultsort="1" pagesize="${requestScope['displayTagPageSize']}" class="tabla" name="sessionScope.personaForm.result" id="item"
		requestURI="/persona/persona-query.do" sort="list" >

		<c:if test="${not empty requestScope['notShowMessage']}">
			<display:setProperty name="basic.msg.empty_list"><table width="100%" border="0" cellspacing="0" cellpadding="0" class="tablaTitulo"><tr><td align="center"></td></tr></table></display:setProperty>
		</c:if>
	
		<display:column sortable="true" property="apellido" 					titleKey="sirius.persona.apellido.label" />
		<display:column sortable="true" property="nombre" 						titleKey="sirius.persona.nombre.label" />
		<display:column sortable="true" property="tipoDocumento.descripcion"	titleKey="sirius.persona.tipoDocumento.label" />
		<display:column sortable="true" property="numeroDocumento" 				titleKey="sirius.persona.numeroDocumento.label" />

		<display:column title="Acciones" media="html">
			<authz:authorize ifAllGranted="ENTIDAD_PERSONA-DETALLE">
				<a href="<c:url value="/persona/persona-form.do?method=view&id="/><bean:write name="item" property="id"/>"><img border="0" alt="Visualizar" title="Visualizar"
					src="<c:url value="/img/icon.lupa.gif"/>" /></a>
			</authz:authorize>
			<authz:authorize ifAllGranted="ENTIDAD_PERSONA-MODIFICACION">
				<a href="<c:url value="/persona/persona-form.do?method=initUpdate&id="/><bean:write name="item" property="id"/>"><img border="0" alt="Editar" title="Modificar"
					src="<c:url value="/img/icoModificar.gif"/>" /></a>
			</authz:authorize>
			<authz:authorize ifAllGranted="ENTIDAD_PERSONA-BAJA">
				<a href="<c:url value="/persona/persona-query.do?method=remove&id="/><bean:write name="item" property="id"/>"><img border="0" alt="Eliminar" title="Eliminar"
					src="<c:url value="/img/icons/cross.png"/>" onclick="return confirmarAccion('Est&aacute; seguro que desea eliminar el registro <bean:write name="item" property="nombre"/>?')" /></a>
			</authz:authorize>
			<authz:authorize ifAllGranted="ENTIDAD_USUARIO-ALTA">
				<logic:empty name="item" property="usuario">
					<a href="<c:url value="/usuario/usuario-persona-form.do?method=initCreateUsuarioPersona&idPersona="/><bean:write name="item" property="id"/>"><img border="0" alt="Crear Usuario" title="Crear Usuario"
						src="<c:url value="/img/add_ico.gif"/>" /></a>
				</logic:empty>
			</authz:authorize>
		</display:column>
	</display-el:table>
</div>
