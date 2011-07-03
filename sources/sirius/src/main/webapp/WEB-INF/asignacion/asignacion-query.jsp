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
function limpiarFiltro() {
	hacerSubmit('asignacion/asignacion-query.do?method=initQuery');
}

function confirmarAccion(mensaje) {
	return confirm(mensaje);
}

function cargarComboActividad(select, destinationCombo){
 	$("#" + destinationCombo).removeOption(/^[^-]/i);
 	$("#" + destinationCombo).val("");

 	var selectedOption = $(select).val();
	url = "<c:url value='/asignacion/asignacion-query.do?method=cargarComboActividad' />";
	$("#" + destinationCombo).ajaxAddOption(url, {idProyecto:selectedOption}, false);
}
	
</script>

<div class="form">
	<html:form action="/asignacion/asignacion-query.do?method=query">
	<div style="float: left; width: 100%;">
		<p>
		<label for="filtroIdProyecto"><bean:message key="sirius.asignacion.proyecto.label" />&nbsp;:</label>
		<html:select property="filtroIdProyecto" styleId="proyecto" onchange="cargarComboActividad(this, 'actividad');">
			<html:option value=""><bean:message key="antares.base.seleccione.label"/></html:option>
			<html:optionsCollection name="asignacionForm" property="proyectos" label="nombre" value="id"/>
		</html:select>
		</p><br><p>
		<label for="filtroIdActividad"><bean:message key="sirius.asignacion.actividad.label" />&nbsp;:</label>
		<html:select property="filtroIdActividad" styleId="actividad">
			<html:option value=""><bean:message key="antares.base.seleccione.label"/></html:option>
			<html:optionsCollection name="asignacionForm" property="actividades" label="nombre" value="id"/>
		</html:select>
		</p><br><p>
		<label for="filtroIdPersona"><bean:message key="sirius.asignacion.persona.label" />&nbsp;:</label>
		<html:select property="filtroIdPersona">
			<html:option value=""><bean:message key="antares.base.seleccione.label" /></html:option>
			<html:optionsCollection name="asignacionForm" property="personas" label="nombreYApellido" value="id" />
		</html:select>
		</p><br>
	</div>

	<div style="float: left; width: 100%;">
		<div class="boton">
			<a href="#" onclick="javascript:limpiarFiltro();"><bean:message key="antares.base.limpiarfiltro.label"/></a>
			<authz:authorize ifAllGranted="ENTIDAD_ASIGNACION-LISTADO">
				<a href="#" onclick="asignacionForm.submit();"><bean:message key="antares.base.buscar.label" /></a>
			</authz:authorize>
			<authz:authorize ifAllGranted="ENTIDAD_ASIGNACION-ALTA">
				<a href="#" onclick="return hacerSubmit('asignacion/asignacion-form.do?method=initCreate');"><bean:message key="antares.base.nuevo.label" /></a>
			</authz:authorize>
		</div>
	</div>

	</html:form>

	<div style="clear: both;" class="errores">
		<html:errors />
	</div>

	<h1>Resultado de la Búsqueda</h1>
	<display-el:table export="true" defaultsort="1" pagesize="${requestScope['displayTagPageSize']}" class="tabla" name="sessionScope.asignacionForm.result" id="item"
		requestURI="/asignacion/asignacion-query.do" sort="list">

		<c:if test="${not empty requestScope['notShowMessage']}">
			<display:setProperty name="basic.msg.empty_list"><table width="100%" border="0" cellspacing="0" cellpadding="0" class="tablaTitulo"><tr><td align="center"></td></tr></table></display:setProperty>
		</c:if>

		<display:column sortable="true" property="actividad.nombre" 			titleKey="sirius.asignacion.actividad.label" />
		<display:column sortable="true" property="persona.nombreYApellido" 		titleKey="sirius.asignacion.persona.label" />
		<display:column sortable="true" property="tipoAsignacion.descripcion" 	titleKey="sirius.asignacion.tipoAsignacion.label" />
		<display:column sortable="true" property="cantidad" 					titleKey="sirius.asignacion.cantidad.label" />

		<display:column title="Acciones" media="html">
			<authz:authorize ifAllGranted="ENTIDAD_ASIGNACION-DETALLE">
				<a href="<c:url value="/asignacion/asignacion-form.do?method=view&id="/><bean:write name="item" property="id"/>"><img border="0" alt="Visualizar" title="Visualizar"
					src="<c:url value="/img/icon.lupa.gif"/>" /></a>
			</authz:authorize>
			<authz:authorize ifAllGranted="ENTIDAD_ASIGNACION-MODIFICACION">
				<a href="<c:url value="/asignacion/asignacion-form.do?method=initUpdate&id="/><bean:write name="item" property="id"/>"><img border="0" alt="Editar" title="Modificar"
					src="<c:url value="/img/icoModificar.gif"/>" /></a>
			</authz:authorize>
			<authz:authorize ifAllGranted="ENTIDAD_ASIGNACION-BAJA">
				<a href="<c:url value="/asignacion/asignacion-query.do?method=remove&id="/><bean:write name="item" property="id"/>"><img border="0" alt="Eliminar" title="Eliminar"
					src="<c:url value="/img/icons/cross.png"/>" onclick="return confirmarAccion('Est&aacute; seguro que desea eliminar el registro?')" /></a>
			</authz:authorize>
		</display:column>
	</display-el:table>
</div>
