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
	hacerSubmit('gasto/gasto-organizacion-query.do?method=initQuery');
}

function confirmarAccion(mensaje) {
	return confirm(mensaje);
}

</script>

<div class="form">
	<html:form action="/gasto/gasto-organizacion-query-validate.do?method=query">
	<div style="float:left; width: 100%;">
		<p>
		<label for="filtroIdRubro"><bean:message key="sirius.gasto.rubro.label" />:</label>
		<html:select property="filtroIdRubro">
			<html:option value=""><bean:message key="antares.base.seleccione.label"/></html:option>
			<html:optionsCollection name="gastoOrganizacionQuery" property="rubros" label="nombre" value="id"/>
		</html:select>
		</p><br><p>
		<label for="filtroFechaDesde"><bean:message key="sirius.gasto.fechaDesde.label" />:</label>
		<html:text property="filtroFechaDesde" styleClass="datepicker"/>
		</p><br><p>
		<label for="filtroFechaHasta"><bean:message key="sirius.gasto.fechaHasta.label" />:</label>
		<html:text property="filtroFechaHasta" styleClass="datepicker"/>
		</p><br>
	</div>

	<div style="float: left; width: 100%;">
		<div class="boton">
			<a href="#" onclick="javascript:limpiarFiltro();"><bean:message key="antares.base.limpiarfiltro.label"/></a>
			<authz:authorize ifAllGranted="ENTIDAD_GASTO_ORGANIZACION-LISTADO">
				<a href="#" onclick="gastoOrganizacionQuery.submit();"><bean:message key="antares.base.buscar.label" /></a>
			</authz:authorize>
			<authz:authorize ifAllGranted="ENTIDAD_GASTO_ORGANIZACION-ALTA">
				<a href="#" onclick="return hacerSubmit('gasto/gasto-organizacion-form.do?method=initCreate');"><bean:message key="antares.base.nuevo.label" /></a>
			</authz:authorize>
		</div>
	</div>
	
	</html:form>
	
	<div style="clear: both;" class="errores">
		<html:errors />
	</div>

	<h1><bean:message key="antares.base.result.label" /></h1>
	<display-el:table export="true" pagesize="${requestScope['displayTagPageSize']}" class="tabla" name="sessionScope.gastoOrganizacionQuery.result" id="item"
		requestURI="/gasto/gasto-organizacion-query.do" sort="list" >

		<display:column sortable="true" property="fecha" 					titleKey="sirius.gasto.fecha.label"  	format="{0,date,dd/MM/yyyy}" />
		<display:column sortable="true" property="rubro.nombre" 			titleKey="sirius.gasto.rubro.label" maxLength="30" />
		<display:column sortable="true" property="importe" 					titleKey="sirius.gasto.importe.label"  style="text-align: right" decorator="com.antares.commons.view.decorator.DoubleDecorator" />

		<display:column title="Acciones" media="html" style="text-align: center">
			<authz:authorize ifAllGranted="ENTIDAD_GASTO_ORGANIZACION-DETALLE">
				<a href="<c:url value="/gasto/gasto-organizacion-form.do?method=view&id="/><bean:write name="item" property="id"/>"><img border="0" alt="Visualizar" title="Visualizar"
					src="<c:url value="/img/icon.lupa.gif"/>" /></a>
			</authz:authorize>
			<authz:authorize ifAllGranted="ENTIDAD_GASTO_ORGANIZACION-MODIFICACION">
				<a href="<c:url value="/gasto/gasto-organizacion-form.do?method=initUpdate&id="/><bean:write name="item" property="id"/>"><img border="0" alt="Editar" title="Modificar"
					src="<c:url value="/img/icoModificar.gif"/>" /></a>
			</authz:authorize>
			<authz:authorize ifAllGranted="ENTIDAD_GASTO_ORGANIZACION-BAJA">
				<a href="<c:url value="/gasto/gasto-organizacion-query.do?method=remove&id="/><bean:write name="item" property="id"/>"><img border="0" alt="Eliminar" title="Eliminar"
					src="<c:url value="/img/icons/cross.png"/>" onclick="return confirmarAccion('Est&aacute; seguro que desea eliminar el registro?')" /></a>
			</authz:authorize>
		</display:column>
	</display-el:table>
</div>
