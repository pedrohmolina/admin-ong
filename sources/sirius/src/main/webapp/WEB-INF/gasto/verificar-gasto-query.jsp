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
function confirmarAccion(mensaje) {
	return confirm(mensaje);
}
function changeProyecto(mensaje) {
	verificarGastoQuery.submit();
}
</script>

<div class="form">
	<html:form action="/gasto/verificar-gasto-query.do?method=query">
	<div style="float:left; width: 100%;">
		<p>
		<label for="filtroIdProyecto"><bean:message key="sirius.gasto.proyecto.label" />:</label>
		<html:select property="filtroIdProyecto" styleId="proyecto" onchange="changeProyecto(this, 'actividad')">
			<html:option value=""><bean:message key="antares.base.seleccione.label"/></html:option>
			<html:optionsCollection name="verificarGastoQuery" property="proyectos" label="nombre" value="id"/>
		</html:select>
		</p><br>
	</div>

	<div style="float: left; width: 100%;">
		<div class="boton">
			<a href="#" onclick="verificarGastoQuery.submit();"><bean:message key="antares.base.buscar.label" /></a>
		</div>
	</div>
	
	</html:form>
	
	<div style="clear: both;" class="errores">
		<html:errors />
	</div>

	<h1><bean:message key="antares.base.result.label" /></h1>
	<display-el:table export="true" defaultsort="3" defaultorder="descending" pagesize="${requestScope['displayTagPageSize']}" class="tabla" name="sessionScope.verificarGastoQuery.result" id="item"
		requestURI="/gasto/verificar-gasto-query.do" sort="list" >

		<display:column sortable="true" 										titleKey="sirius.gasto.proyecto.label" maxLength="50">
			<logic:notEmpty name="item" property="proyecto">
				<bean:write name="item" property="proyecto.nombre" />
			</logic:notEmpty>
			<logic:notEmpty name="item" property="actividad">
				<bean:write name="item" property="actividad.meta.objetivoEspecifico.objetivoGeneral.proyecto.nombre" />
			</logic:notEmpty>
		</display:column>
		<display:column sortable="true" 										titleKey="sirius.gasto.actividad.label" maxLength="30">
			<logic:notEmpty name="item" property="actividad">
				<bean:write name="item" property="actividad.nombre" />
			</logic:notEmpty>
		</display:column>
		<display:column sortable="true" property="fecha" 						titleKey="sirius.gasto.fecha.label" format="{0,date,dd/MM/yyyy}"  style="text-align: center"/>
		<display:column sortable="true" property="rubro.nombre" 				titleKey="sirius.gasto.rubro.label" maxLength="30" />
		<display:column sortable="true" property="importe" 						titleKey="sirius.gasto.importe.label" style="text-align: right" decorator="com.antares.commons.view.decorator.DoubleDecorator" />

		<display:column title="Acciones" media="html" style="text-align: center">
			<a href="<c:url value="/gasto/verificar-gasto-query.do?method=confirmar&id="/><bean:write name="item" property="id"/>"><img border="0" alt="Confirmar" title="Confirmar"
				src="<c:url value="/img/tick.png"/>" onclick="return confirmarAccion('Est&aacute; seguro que desea confirmar el gasto?')" /></a>
		</display:column>
	</display-el:table>

	<div class="boton">
		<a href="<c:url value="/gasto/verificar-gasto-query.do?method=save"/>"><bean:message key="antares.base.aceptar.label" /></a>
		<a href="<c:url value="/gasto/verificar-gasto-query.do?method=initQuery"/>"><bean:message key="antares.base.cancelar.label" /></a>
	</div>
	<div style="clear:both; padding:5px 0 0 0;"></div>
</div>
