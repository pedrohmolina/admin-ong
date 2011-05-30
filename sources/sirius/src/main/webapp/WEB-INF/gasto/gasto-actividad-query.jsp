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
	hacerSubmit('gasto/gasto-actividad-query.do?method=initQuery');
}

function confirmarAccion(mensaje) {
	return confirm(mensaje);
}

function cargarComboActividad(select, destinationCombo){
 	$("#" + destinationCombo).removeOption(/^[^-]/i);
 	$("#" + destinationCombo).val("");

 	var selectedOption = $(select).val();
 	if (selectedOption != "") {
		url = "<c:url value='/gasto/gasto-actividad-form.do?method=cargarComboActividad' />";
 		$("#" + destinationCombo).ajaxAddOption(url, {idProyecto:selectedOption}, false);
 	}
}

function initReferencia(id) {
	var url = "<c:url value='/gasto/gasto-actividad-form.do?method=initReferencia&id=' />" + id;
    var options = "fullscreen = 'no'" +
                 ", toolbar = 'no'" +
                 ", location = 'no'" +
                 ", status = 'no'" +
                 ", menubar = 'no'" +
                 ", scrollbars = 'no'" +
                 ", resizable = 'no'" +
                 ", width= 1000" +
                 ", height = 200" +
                 ", left = 200" +
                 ", top = 200";
     var ventana = window.open(url,"",options); 
}
</script>

<div class="form">
	<html:form action="/gasto/gasto-actividad-query.do?method=query">
	<h1>Búsqueda de Gastos Registrados por Actividad</h1>
	<div style="float:left; width: 100%;">
		<p>
		<label for="filtroIdProyecto"><bean:message key="sirius.gasto.proyecto.label" />:</label>
		<html:select property="filtroIdProyecto" styleId="proyecto" onchange="cargarComboActividad(this, 'actividad')">
			<html:option value=""><bean:message key="antares.base.seleccione.label"/></html:option>
			<html:optionsCollection name="gastoActividadForm" property="proyectos" label="nombre" value="id"/>
		</html:select>
		</p><br><p>
		<label for="filtroIdActividad"><bean:message key="sirius.gasto.actividad.label" />:</label>
		<html:select property="filtroIdActividad" styleId="actividad">
			<html:option value=""><bean:message key="antares.base.seleccione.label"/></html:option>
			<html:optionsCollection name="gastoActividadForm" property="actividades" label="nombre" value="id"/>
		</html:select>
		</p><br><p>
		<label for="filtroIdPersona"><bean:message key="sirius.gasto.persona.label" />:</label>
		<html:select property="filtroIdPersona">
			<html:option value=""><bean:message key="antares.base.seleccione.label"/></html:option>
			<html:optionsCollection name="gastoActividadForm" property="personas" label="nombreYApellido" value="id"/>
		</html:select>
		</p><br><p>
		<label for="filtroFecha"><bean:message key="sirius.gasto.fecha.label" />:</label>
		<html:text property="filtroFecha" />
		</p><br><p>
		<label for="filtroIdRubro"><bean:message key="sirius.gasto.rubro.label" />:</label>
		<html:select property="filtroIdRubro">
			<html:option value=""><bean:message key="antares.base.seleccione.label"/></html:option>
			<html:optionsCollection name="gastoActividadForm" property="rubros" label="nombre" value="id"/>
		</html:select>
		</p><br><p>
		<label for="filtroIdOrigen"><bean:message key="sirius.gasto.origen.label" />:</label>
		<html:select property="filtroIdOrigen">
			<html:option value=""><bean:message key="antares.base.seleccione.label"/></html:option>
			<html:optionsCollection name="gastoActividadForm" property="origenes" label="descripcion" value="id"/>
		</html:select>
		</p><br><p>
		<label for="filtroIdProveedor"><bean:message key="sirius.gasto.origen.label" />:</label>
		<html:select property="filtroIdProveedor">
			<html:option value=""><bean:message key="antares.base.seleccione.label"/></html:option>
			<html:optionsCollection name="gastoActividadForm" property="proveedores" label="nombre" value="id"/>
		</html:select>
		</p>
	</div>

	<div style="float: left; width: 100%;">
		<div class="boton">
			<a href="#" onclick="javascript:limpiarFiltro();"><bean:message key="antares.base.limpiarfiltro.label"/></a>
			<authz:authorize ifAllGranted="ENTIDAD_GASTO_ACTIVIDAD-LISTADO">
				<a href="#" onclick="gastoActividadForm.submit();"><bean:message key="antares.base.buscar.label" /></a>
			</authz:authorize>
		</div>
	</div>
	
	</html:form>
	
	<div style="clear: both;">
		<html:errors />
	</div>

	<h1>Resultado de la Búsqueda</h1>
	<display-el:table export="true" defaultsort="1" pagesize="${requestScope['displayTagPageSize']}" class="tabla" name="sessionScope.gastoActividadForm.result" id="item"
		requestURI="/gasto/gasto-actividad-query.do" sort="list" >

		<c:if test="${not empty requestScope['notShowMessage']}">
			<display:setProperty name="basic.msg.empty_list"><table width="100%" border="0" cellspacing="0" cellpadding="0" class="tablaTitulo"><tr><td align="center"></td></tr></table></display:setProperty>
		</c:if>
	
		<display:column sortable="true" property="actividad.proyecto.nombre" 	titleKey="sirius.gasto.proyecto.label" />
		<display:column sortable="true" property="actividad.nombre" 			titleKey="sirius.gasto.actividad.label" />
		<display:column sortable="true" property="persona.nombreYApellido" 		titleKey="sirius.gasto.persona.label" />
		<display:column sortable="true" property="fecha" 						titleKey="sirius.gasto.fecha.label"  	format="{0,date,dd/MM/yyyy}" />
		<display:column sortable="true" property="rubro.nombre" 				titleKey="sirius.gasto.rubro.label" />
		<display:column sortable="true" property="proveedor.nombre" 			titleKey="sirius.gasto.proveedor.label" />
		<display:column sortable="true" property="importe" 						titleKey="sirius.gasto.importe.label" />

		<display:column title="Acciones" media="html">
			<authz:authorize ifAllGranted="ENTIDAD_GASTO_ACTIVIDAD-DETALLE">
			<a href="<c:url value="/gasto/gasto-actividad-form.do?method=view&id="/><bean:write name="item" property="id"/>"><img border="0" alt="Visualizar" title="Visualizar"
				src="<c:url value="/img/icon.lupa.gif"/>" /></a>
			</authz:authorize>
			<authz:authorize ifAllGranted="ENTIDAD_GASTO_ACTIVIDAD-MODIFICACION">
			<a href="<c:url value="/gasto/gasto-actividad-form.do?method=initUpdate&id="/><bean:write name="item" property="id"/>"><img border="0" alt="Editar" title="Modificar"
				src="<c:url value="/img/icoModificar.gif"/>" /></a>
			</authz:authorize>
			<authz:authorize ifAllGranted="ENTIDAD_GASTO_ACTIVIDAD-BAJA">
			<a href="<c:url value="/gasto/gasto-actividad-query.do?method=remove&id="/><bean:write name="item" property="id"/>"><img border="0" alt="Eliminar" title="Eliminar"
				src="<c:url value="/img/icons/cross.png"/>" onclick="return confirmarAccion('Est&aacute; seguro que desea eliminar el registro?')" /></a>
			</authz:authorize>
			<authz:authorize ifAllGranted="ENTIDAD_GASTO_ACTIVIDAD-CONFIRMAR">
				<logic:equal name="item" property="confirmado" value="false">
					<a href="<c:url value="/gasto/gasto-actividad-query.do?method=confirmar&id="/><bean:write name="item" property="id"/>"><img border="0" alt="Confirmar" title="Confirmar"
						src="<c:url value="/img/tick.png"/>" onclick="return confirmarAccion('Est&aacute; seguro que desea confirmar el gasto?')" /></a>
				</logic:equal>
			</authz:authorize>
			<authz:authorize ifAllGranted="ENTIDAD_GASTO_ACTIVIDAD-MODIFICACION">
				<logic:empty name="item" property="referencia">
					<a href="#"/><img border="0" alt="Asignar Referencia" title="Asignar Referencia"
						src="<c:url value="/img/tick.png"/>" onclick="initReferencia(<bean:write name="item" property="id"/>);" /></a>
				</logic:empty>
				<logic:notEmpty name="item" property="referencia">
					<a href="#"/><img border="0" alt="Modificar Referencia" title="Modificar Referencia"
						src="<c:url value="/img/tick.png"/>" onclick="initReferencia(<bean:write name="item" property="id"/>);" /></a>
					<a href="<c:url value="/gasto/gasto-actividad-query.do?method=removeReferencia&id="/><bean:write name="item" property="id"/>"><img border="0" alt="Eliminar Referencia" title="Eliminar Referencia"
						src="<c:url value="/img/icons/cross.png"/>" onclick="return confirmarAccion('Est&aacute; seguro que desea eliminar la referencia?')" /></a>
				</logic:notEmpty>
			</authz:authorize>
		</display:column>
	</display-el:table>
</div>
