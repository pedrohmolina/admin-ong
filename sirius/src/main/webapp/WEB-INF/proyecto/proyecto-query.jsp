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
	<html:form action="/proyecto/proyecto-query-validate.do?method=query">
	<div style="float:left; width: 100%;">
		<p>
		<label for="filtroNombre"><bean:message key="sirius.proyecto.nombre.label" />:</label>
		<html:text property="filtroNombre" />
		</p><br><p>
		<label for="filtroFechaInicioDesde"><bean:message key="sirius.proyecto.fechaInicioDesde.label" />:</label>
		<html:text property="filtroFechaInicioDesde"  styleClass="datepicker"/>
		</p><br><p>
		<label for="filtroFechaInicioHasta"><bean:message key="sirius.proyecto.fechaInicioHasta.label" />:</label>
		<html:text property="filtroFechaInicioHasta" styleClass="datepicker"/>
		</p><br><p>
		<label for="filtroIdFinanciador"><bean:message key="sirius.proyecto.financiador.label" />:</label>
		<html:select property="filtroIdFinanciador">
			<html:option value=""><bean:message key="antares.base.seleccione.label"/></html:option>
			<html:optionsCollection name="proyectoQuery" property="financiadores" label="nombre" value="id"/>
		</html:select>
		</p><br><p>
		<label for="filtroIdEstadoProyecto"><bean:message key="sirius.proyecto.estadoProyecto.label" />:</label>
		<html:select property="filtroIdEstadoProyecto">
			<html:option value=""><bean:message key="antares.base.seleccione.label"/></html:option>
			<html:optionsCollection name="proyectoQuery" property="estadosProyecto" label="descripcion" value="id"/>
		</html:select>
		</p><br>
	</div>

	<div style="float: left; width: 100%;">
		<div class="boton">
			<a href="#" onclick="javascript:limpiarFiltro();"><bean:message key="antares.base.limpiarfiltro.label"/></a>
			<authz:authorize ifAllGranted="ENTIDAD_PROYECTO-LISTADO">
				<a href="#" onclick="proyectoQuery.submit();"><bean:message key="antares.base.buscar.label" /></a>
			</authz:authorize>
			<authz:authorize ifAllGranted="ENTIDAD_PROYECTO-ALTA">
				<a href="#" onclick="return hacerSubmit('proyecto/proyecto-form.do?method=initCreate');"><bean:message key="antares.base.nuevo.label" /></a>
			</authz:authorize>
		</div>
	</div>
	
	</html:form>
	
	<div style="clear: both;" class="errores">
		<html:errors />
	</div>

	<h1><bean:message key="antares.base.result.label" /></h1>
	<display-el:table export="true" defaultsort="1" pagesize="${requestScope['displayTagPageSize']}" class="tabla" name="sessionScope.proyectoQuery.result" id="item"
		requestURI="/proyecto/proyecto-query.do" sort="list" >

		<display:column sortable="true" property="nombre" 							titleKey="sirius.proyecto.nombre.label" maxLength="30" />
		<display:column sortable="true" property="fechaInicio" 						titleKey="sirius.proyecto.fechaInicio.label" 	format="{0,date,dd/MM/yyyy}" style="text-align: center"/>
		<display:column sortable="true" property="fechaFin" 						titleKey="sirius.proyecto.fechaFin.label" 		format="{0,date,dd/MM/yyyy}" style="text-align: center"/>
		<display:column sortable="true" property="financiador.nombre" 				titleKey="sirius.proyecto.financiador.label" maxLength="30" />
		<display:column sortable="true" property="estadoProyecto.descripcion" 		titleKey="sirius.proyecto.estadoProyecto.label" style="text-align: center" />

		<display:column title="Acciones" media="html" style="text-align: center">
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
						src="<c:url value="/img/arrow_refresh.png"/>" /></a>
				</logic:iterate>
			</authz:authorize>
			<authz:authorize ifAllGranted="ENTIDAD_PROYECTO-ADMINISTRAR_PRESUPUESTO">
				<a href="<c:url value="/proyecto/presupuesto-form.do?method=show&idProyecto="/><bean:write name="item" property="id"/>"><img border="0" alt="Administrar Prespuesto" title="Administrar Prespuesto"
					src="<c:url value="/img/application_form_edit.png"/>" /></a>
			</authz:authorize>
		</display:column>
	</display-el:table>
</div>
