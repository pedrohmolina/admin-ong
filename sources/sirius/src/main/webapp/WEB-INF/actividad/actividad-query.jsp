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
	hacerSubmit('actividad/actividad-query.do?method=initQuery');
}

function confirmarAccion(mensaje) {
	return confirm(mensaje);
}
</script>

<div class="form">
	<html:form action="/actividad/actividad-query-validate.do?method=query">
	<div style="float:left; width:100%;">
		<p>
		<label for="filtroIdMeta"><bean:message key="sirius.actividad.meta.label" />&nbsp;:</label>
		<html:select property="filtroIdMeta">
			<html:option value=""><bean:message key="antares.base.seleccione.label"/></html:option>
			<html:optionsCollection name="actividadQuery" property="metas" label="nombre" value="id"/>
		</html:select>
		</p><br><p>
		<label for="filtroNombre"><bean:message key="sirius.actividad.nombre.label" />:</label>
		<html:text maxlength="255" property="filtroNombre"></html:text>
		</p><br><p>
		<label for="filtroIdEstadoActividad"><bean:message key="sirius.actividad.estadoActividad.label" />:</label>
		<html:select property="filtroIdEstadoActividad">
			<html:option value=""><bean:message key="antares.base.seleccione.label"/></html:option>
			<html:optionsCollection name="actividadQuery" property="estadosActividad" label="descripcion" value="id"/>
		</html:select>
		</p><br>
	</div>

	<div style="float: left; width: 100%;">
		<div class="boton">
			<a href="#" onclick="javascript:limpiarFiltro();"><bean:message key="antares.base.limpiarfiltro.label" /></a>
			<authz:authorize ifAllGranted="ENTIDAD_ACTIVIDAD-LISTADO">
				<a href="#" onclick="actividadQuery.submit();"><bean:message key="antares.base.buscar.label" /></a>
			</authz:authorize>
			<authz:authorize ifAllGranted="ENTIDAD_ACTIVIDAD-ALTA">
				<a href="#" onclick="return hacerSubmit('actividad/actividad-form.do?method=initCreate');"><bean:message key="antares.base.nuevo.label" /></a>
			</authz:authorize>
		</div>
	</div>

	</html:form>

	<div style="clear: both;" class="errores">
		<html:errors />
	</div>

	<h1><bean:message key="antares.base.result.label" /></h1>
	<display-el:table export="true" defaultsort="1" pagesize="${requestScope['displayTagPageSize']}" class="tabla" name="sessionScope.actividadQuery.result" id="item"
		requestURI="/actividad/actividad-query.do" sort="list">

		<c:if test="${not empty requestScope['notShowMessage']}">
			<display:setProperty name="basic.msg.empty_list"><table width="100%" border="0" cellspacing="0" cellpadding="0" class="tablaTitulo"><tr><td align="center"></td></tr></table></display:setProperty>
		</c:if>

		<display:column sortable="true" property="proyecto.nombre" 				titleKey="sirius.actividad.proyecto.label" 			maxLength="30" />
		<display:column sortable="true" property="meta.nombre" 					titleKey="sirius.actividad.meta.label" 				maxLength="30" />
		<display:column sortable="true" property="nombre" 						titleKey="sirius.actividad.nombre.label" 			maxLength="30" />
		<display:column sortable="true" property="fechaInicio" 					titleKey="sirius.actividad.fechaInicio.label"  		format="{0,date,dd/MM/yyyy}" 	style="text-align: center" />
		<display:column sortable="true" property="fechaFin" 					titleKey="sirius.actividad.fechaFin.label"  		format="{0,date,dd/MM/yyyy}" 	style="text-align: center" />
		<display:column sortable="true" property="ponderacion" 					titleKey="sirius.actividad.ponderacion.label"  		style="text-align: right" />
		<display:column sortable="true" property="completitud" 					titleKey="sirius.actividad.completitud.label"  		style="text-align: right" 		decorator="com.antares.commons.view.decorator.DoubleDecorator" />
		<display:column sortable="true" property="estadoActividad.descripcion" 	titleKey="sirius.actividad.estadoActividad.label" 	style="text-align: center"/>

		<display:column title="Acciones" media="html" style="text-align: center">
			<authz:authorize ifAllGranted="ENTIDAD_ACTIVIDAD-DETALLE">
				<a href="<c:url value="/actividad/actividad-form.do?method=view&id="/><bean:write name="item" property="id"/>"><img border="0" alt="Visualizar" title="Visualizar"
					src="<c:url value="/img/icon.lupa.gif"/>" /></a>
			</authz:authorize>
			<authz:authorize ifAllGranted="ENTIDAD_ACTIVIDAD-MODIFICACION">
				<a href="<c:url value="/actividad/actividad-form.do?method=initUpdate&id="/><bean:write name="item" property="id"/>"><img border="0" alt="Editar" title="Modificar"
					src="<c:url value="/img/icoModificar.gif"/>" /></a>
			</authz:authorize>
			<authz:authorize ifAllGranted="ENTIDAD_ACTIVIDAD-BAJA">
				<a href="<c:url value="/actividad/actividad-query.do?method=remove&id="/><bean:write name="item" property="id"/>"><img border="0" alt="Eliminar" title="Eliminar"
					src="<c:url value="/img/icons/cross.png"/>" onclick="return confirmarAccion('Est&aacute; seguro que desea eliminar el registro <bean:write name="item" property="nombre"/>?')" /></a>
			</authz:authorize>
			<authz:authorize ifAllGranted="ENTIDAD_ACTIVIDAD-CAMBIAR_ESTADO">
				<logic:iterate id="estado" name="item" property="estadoActividad.proximosEstadosPosibles">
					<a href="<c:url value="/actividad/actividad-query.do?method=cambiarEstado&id="/><bean:write name="item" property="id"/>&idEstado=<bean:write name="estado" property="id"/>"><img border="0" 
						alt="Pasar a estado <bean:write name="estado" property="descripcion"/>" 
						title="Pasar a estado <bean:write name="estado" property="descripcion"/>"
						src="<c:url value="/img/arrow_refresh.png"/>" /></a>
				</logic:iterate>
			</authz:authorize>
		</display:column>
	</display-el:table>
</div>
