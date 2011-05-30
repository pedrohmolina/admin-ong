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
	hacerSubmit('proyecto/proyecto-query.do?method=initQuery');
}

function confirmarAccion(mensaje) {
	return confirm(mensaje);
}

</script>

<div class="form">
	<html:form action="/proyecto/proyecto-query.do?method=query">
	<h1>Búsqueda de Proyectos</h1>
	<div style="float:left; width: 100%;">
		<p>
		<label for="filtroNombre"><bean:message key="sirius.proyecto.nombre.label" />:</label>
		<html:text property="filtroNombre" />
		</p><br><p>
		<label for="filtroFechaInicio"><bean:message key="sirius.proyecto.fechaInicio.label" />:</label>
		<html:text property="filtroFechaInicio" />
		</p><br><p>
		<label for="filtroFechaFin"><bean:message key="sirius.proyecto.fechaFin.label" />:</label>
		<html:text property="filtroFechaFin" />
		</p><br><p>
		<label for="filtroIdResponsable"><bean:message key="sirius.proyecto.responsable.label" />:</label>
		<html:select property="filtroIdResponsable">
			<html:option value=""><bean:message key="antares.base.seleccione.label"/></html:option>
			<html:optionsCollection name="proyectoForm" property="responsables" label="nombreYApellido" value="id"/>
		</html:select>
		</p><br><p>
		<label for="filtroIdCoordinador"><bean:message key="sirius.proyecto.coordinador.label" />:</label>
		<html:select property="filtroIdCoordinador">
			<html:option value=""><bean:message key="antares.base.seleccione.label"/></html:option>
			<html:optionsCollection name="proyectoForm" property="coordinadores" label="nombreYApellido" value="id"/>
		</html:select>
		</p><br><p>
		<label for="filtroIdFinanciador"><bean:message key="sirius.proyecto.financiador.label" />:</label>
		<html:select property="filtroIdFinanciador">
			<html:option value=""><bean:message key="antares.base.seleccione.label"/></html:option>
			<html:optionsCollection name="proyectoForm" property="financiadores" label="nombre" value="id"/>
		</html:select>
		</p><br><p>
		<label for="filtroIdAreaTematica"><bean:message key="sirius.proyecto.areasTematicas.label" />:</label>
		<html:select property="filtroIdAreaTematica">
			<html:option value=""><bean:message key="antares.base.seleccione.label"/></html:option>
			<html:optionsCollection name="proyectoForm" property="areasTematicas" label="descripcion" value="id"/>
		</html:select>
		</p><br>
	</div>

	<div style="float: left; width: 100%;">
		<div class="boton">
			<a href="#" onclick="javascript:limpiarFiltro();"><bean:message key="antares.base.limpiarfiltro.label"/></a>
			<authz:authorize ifAllGranted="ENTIDAD_PROYECTO-LISTADO">
				<a href="#" onclick="proyectoForm.submit();"><bean:message key="antares.base.buscar.label" /></a>
			</authz:authorize>
			<authz:authorize ifAllGranted="ENTIDAD_PROYECTO-ALTA">
				<a href="#" onclick="return hacerSubmit('proyecto/proyecto-form.do?method=initCreate');"><bean:message key="antares.base.nuevo.label" /></a>
			</authz:authorize>
		</div>
	</div>
	
	</html:form>
	
	<div style="clear: both;">
		<html:errors />
	</div>

	<h1>Resultado de la Búsqueda</h1>
	<display-el:table export="true" defaultsort="1" pagesize="${requestScope['displayTagPageSize']}" class="tabla" name="sessionScope.proyectoForm.result" id="item"
		requestURI="/proyecto/proyecto-query.do" sort="list" >

		<c:if test="${not empty requestScope['notShowMessage']}">
			<display:setProperty name="basic.msg.empty_list"><table width="100%" border="0" cellspacing="0" cellpadding="0" class="tablaTitulo"><tr><td align="center"></td></tr></table></display:setProperty>
		</c:if>
	
		<display:column sortable="true" property="nombre" 							titleKey="sirius.proyecto.nombre.label" />
		<display:column sortable="true" property="responsable.nombreYApellido" 		titleKey="sirius.proyecto.responsable.label" />
		<display:column sortable="true" property="fechaInicio" 						titleKey="sirius.proyecto.fechaInicio.label" />
		<display:column sortable="true" property="fechaFin" 						titleKey="sirius.proyecto.fechaFin.label" />
		<display:column sortable="true" property="financiador.nombre" 				titleKey="sirius.proyecto.financiador.label" />
		<display:column sortable="true" property="estadoProyecto.descripcion" 		titleKey="sirius.proyecto.estadoProyecto.label" />

		<display:column title="Acciones" media="html">
			<authz:authorize ifAllGranted="ENTIDAD_PROYECTO-DETALLE">
				<a href="<c:url value="/proyecto/proyecto-form.do?method=view&id="/><bean:write name="item" property="id"/>"><img border="0" alt="Visualizar" title="Visualizar"
					src="<c:url value="/img/icon.lupa.gif"/>" /></a>
			</authz:authorize>
			<authz:authorize ifAllGranted="ENTIDAD_PROYECTO-MODIFICACION">
				<a href="<c:url value="/proyecto/proyecto-form.do?method=initUpdate&id="/><bean:write name="item" property="id"/>"><img border="0" alt="Editar" title="Modificar"
					src="<c:url value="/img/icoModificar.gif"/>" /></a>
			</authz:authorize>
			<authz:authorize ifAllGranted="ENTIDAD_PROYECTO-BAJA">
				<a href="<c:url value="/proyecto/proyecto-query.do?method=remove&id="/><bean:write name="item" property="id"/>"><img border="0" alt="Eliminar" title="Eliminar"
					src="<c:url value="/img/icons/cross.png"/>" onclick="return confirmarAccion('Est&aacute; seguro que desea eliminar el registro <bean:write name="item" property="nombre"/>?')" /></a>
			</authz:authorize>
			<authz:authorize ifAllGranted="ENTIDAD_PROYECTO-CAMBIAR_ESTADO">
				<logic:iterate id="estado" name="item" property="estadoProyecto.proximosEstadosPosibles">
					<a href="<c:url value="/proyecto/proyecto-query.do?method=cambiarEstado&id="/><bean:write name="item" property="id"/>&idEstado=<bean:write name="estado" property="id"/>"><img border="0" 
						alt="Pasar a estado <bean:write name="estado" property="descripcion"/>" 
						title="Pasar a estado <bean:write name="estado" property="descripcion"/>"
						src="<c:url value="/img/icoEstados.gif"/>" /></a>
				</logic:iterate>
			</authz:authorize>
		</display:column>
	</display-el:table>
</div>
