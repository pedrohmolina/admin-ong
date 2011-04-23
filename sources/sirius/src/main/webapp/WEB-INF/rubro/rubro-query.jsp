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
	hacerSubmit('rubro/rubro-query.do?method=initQuery');
}

function confirmarAccion(mensaje) {
	return confirm(mensaje);
}

</script>

<div class="form">
	<html:form action="/rubro/rubro-query.do?method=query">
	<h1>Búsqueda de Rubros</h1>
	<div style="float:left; width: 100%;">
		<label for="filtroNombre"><bean:message key="sirius.rubro.nombre.label" />:</label>
		<html:text maxlength="255" property="filtroNombre"></html:text>
		<br>
	</div>

	<div style="float: left; width: 100%;">
		<div class="boton">
			<a href="#" onclick="javascript:limpiarFiltro();"><bean:message key="antares.base.limpiarfiltro.label"/></a>
			<authz:authorize ifAllGranted="ENTIDAD_RUBRO-LISTADO">
				<a href="#" onclick="rubroForm.submit();"><bean:message key="antares.base.buscar.label" /></a>
			</authz:authorize>
			<authz:authorize ifAllGranted="ENTIDAD_RUBRO-ALTA">
				<a href="#" onclick="return hacerSubmit('rubro/rubro-form.do?method=initCreateNivelUno');"><bean:message key="sirius.rubro.nuevo.nivelUno.label" /></a>
				<a href="#" onclick="return hacerSubmit('rubro/rubro-form.do?method=initCreateNivelDos');"><bean:message key="sirius.rubro.nuevo.nivelDos.label" /></a>
				<a href="#" onclick="return hacerSubmit('rubro/rubro-form.do?method=initCreateNivelTres');"><bean:message key="sirius.rubro.nuevo.nivelTres.label" /></a>
			</authz:authorize>
		</div>
	</div>
	
	</html:form>
	
	<div style="clear: both;">
		<html:errors />
	</div>

	<h1>Resultado de la Búsqueda</h1>
	<display-el:table export="true" defaultsort="1" pagesize="${requestScope['displayTagPageSize']}" class="tabla" name="sessionScope.rubroForm.result" id="item"
		requestURI="/rubro/rubro-query.do" sort="list" >

		<c:if test="${not empty requestScope['notShowMessage']}">
			<display:setProperty name="basic.msg.empty_list"><table width="100%" border="0" cellspacing="0" cellpadding="0" class="tablaTitulo"><tr><td align="center"></td></tr></table></display:setProperty>
		</c:if>

		<display:column sortable="true" property="nombre" 		titleKey="sirius.rubro.nombre.label" />
		<display:column sortable="true" property="descripcion" 	titleKey="sirius.rubro.descripcion.label" />
		<display:column sortable="true" titleKey="sirius.rubro.rubroPadre.label">
			<logic:empty name="item" property="rubroPadre">------</logic:empty>
			<logic:notEmpty name="item" property="rubroPadre">
				<bean:write name="item" property="rubroPadre.nombre" />
			</logic:notEmpty>
		</display:column>

		<display:column title="Acciones" media="html">
			<authz:authorize ifAllGranted="ENTIDAD_RUBRO-DETALLE">
				<a href="<c:url value="/rubro/rubro-form.do?method=view&id="/><bean:write name="item" property="id"/>"><img border="0" alt="Visualizar" title="Visualizar"
					src="<c:url value="/img/icon.lupa.gif"/>" /></a>
			</authz:authorize>
			<authz:authorize ifAllGranted="ENTIDAD_RUBRO-MODIFICACION">
				<a href="<c:url value="/rubro/rubro-form.do?method=initUpdate&id="/><bean:write name="item" property="id"/>"><img border="0" alt="Editar" title="Modificar"
					src="<c:url value="/img/icoModificar.gif"/>" /></a>
			</authz:authorize>
			<authz:authorize ifAllGranted="ENTIDAD_RUBRO-BAJA">
				<a href="<c:url value="/rubro/rubro-query.do?method=remove&id="/><bean:write name="item" property="id"/>"><img border="0" alt="Eliminar" title="Eliminar"
					src="<c:url value="/img/icons/cross.png"/>" onclick="return confirmarAccion('Est&aacute; seguro que desea eliminar el registro <bean:write name="item" property="nombre"/>?')" /></a>
			</authz:authorize>
		</display:column>
	</display-el:table>
</div>
