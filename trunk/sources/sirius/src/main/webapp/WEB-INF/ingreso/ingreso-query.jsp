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
	hacerSubmit('ingreso/ingreso-query.do?method=initQuery');
}

function confirmarAccion(mensaje) {
	return confirm(mensaje);
}	
</script>

<div class="form">
	<html:form action="/ingreso/ingreso-query.do?method=query">
		<h1>Búsqueda de Ingresos</h1>
		<div style="float: left; width: 100%;">
			<p>
				<label for="filtroFecha"><bean:message key="sirius.ingreso.fecha.label" />:</label>
				<html:text property="filtroFecha" />
			</p>
			<br>
			<p>
				<label for="filtroIdTipoIngreso"><bean:message key="sirius.ingreso.tipoIngreso.label" />:</label>
				<html:select property="filtroIdTipoIngreso">
					<html:option value=""><bean:message key="antares.base.seleccione.label" /></html:option>
					<html:optionsCollection name="ingresoForm" property="tiposIngreso" label="descripcion" value="id" />
				</html:select>
			</p>
			<br>
		</div>

		<div style="float: left; width: 100%;">
			<div class="boton">
				<a href="#" onclick="javascript:limpiarFiltro();"><bean:message key="antares.base.limpiarfiltro.label" /> </a>
				<authz:authorize ifAllGranted="ENTIDAD_INGRESO-LISTADO">
					<a href="#" onclick="ingresoForm.submit();"><bean:message key="antares.base.buscar.label" /></a>
				</authz:authorize>
				<authz:authorize ifAllGranted="ENTIDAD_INGRESO-ALTA">
					<a href="#" onclick="return hacerSubmit('ingreso/ingreso-form.do?method=initCreate');"><bean:message key="antares.base.nuevo.label" /></a>
				</authz:authorize>
			</div>
		</div>

	</html:form>

	<div style="clear: both;">
		<html:errors />
	</div>

	<h1>Resultado de la Búsqueda</h1>
	<display-el:table export="true" defaultsort="1" pagesize="${requestScope['displayTagPageSize']}" class="tabla" name="sessionScope.ingresoForm.result" id="item"
		requestURI="/ingreso/ingreso-query.do" sort="list">

		<c:if test="${not empty requestScope['notShowMessage']}">
			<display:setProperty name="basic.msg.empty_list"><table width="100%" border="0" cellspacing="0" cellpadding="0" class="tablaTitulo"><tr><td align="center"></td></tr></table></display:setProperty>
		</c:if>

		<display:column sortable="true" property="tipoIngreso.descripcion" titleKey="sirius.ingreso.tipoIngreso.label" />
		<display:column sortable="true" property="fecha" titleKey="sirius.ingreso.fecha.label" format="{0,date,dd/MM/yyyy}" />
		<display:column sortable="true"	titleKey="sirius.ingreso.financiador.label">
			<logic:empty name="item" property="financiador">&nbsp;-&nbsp;</logic:empty>
			<logic:notEmpty name="item" property="financiador">
				<bean:write name="item" property="financiador.nombre" />
			</logic:notEmpty>
		</display:column>

		<display:column sortable="true" property="monto" titleKey="sirius.ingreso.monto.label" />

		<display:column title="Acciones" media="html">
			<authz:authorize ifAllGranted="ENTIDAD_INGRESO-DETALLE">
				<a href="<c:url value="/ingreso/ingreso-form.do?method=view&id="/><bean:write name="item" property="id"/>"><img border="0" alt="Visualizar" title="Visualizar"
					src="<c:url value="/img/icon.lupa.gif"/>" /></a>
			</authz:authorize>
			<authz:authorize ifAllGranted="ENTIDAD_INGRESO-MODIFICACION">
				<a href="<c:url value="/ingreso/ingreso-form.do?method=initUpdate&id="/><bean:write name="item" property="id"/>"><img border="0" alt="Editar" title="Modificar"
					src="<c:url value="/img/icoModificar.gif"/>" /></a>
			</authz:authorize>
			<authz:authorize ifAllGranted="ENTIDAD_INGRESO-BAJA">
				<a href="<c:url value="/ingreso/ingreso-query.do?method=remove&id="/><bean:write name="item" property="id"/>"><img border="0" alt="Eliminar" title="Eliminar"
					src="<c:url value="/img/icons/cross.png"/>" onclick="return confirmarAccion('Est&aacute; seguro que desea eliminar el registro?')" /></a>
			</authz:authorize>
		</display:column>
	</display-el:table>
</div>
