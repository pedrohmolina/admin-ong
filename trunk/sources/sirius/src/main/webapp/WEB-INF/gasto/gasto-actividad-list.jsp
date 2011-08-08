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
</script>

<div class="form">
	<html:form action="/gasto/gasto-actividad-list-validate.do?method=list">
	<div style="float:left; width: 100%;">
		<p>
		<label for="filtroFechaDesde"><bean:message key="sirius.gasto.fechaDesde.label" />:</label>
		<html:text property="filtroFechaDesde" styleClass="datepicker"/>
		</p><br><p>
		<label for="filtroFechaHasta"><bean:message key="sirius.gasto.fechaHasta.label" />:</label>
		<html:text property="filtroFechaHasta" styleClass="datepicker"/>
		</p><br>
	</div>

	<div style="float: left; width: 100%;">
		<div class="boton">
			<a href="#" onclick="gastoActividadQuery.submit();"><bean:message key="antares.base.buscar.label" /></a>
		</div>
	</div>
	
	</html:form>
	
	<div style="clear: both;" class="errores">
		<html:errors />
	</div>

	<h1><bean:message key="antares.base.result.label" /></h1>
	<display-el:table export="true" defaultsort="1" pagesize="${requestScope['displayTagPageSize']}" class="tabla" name="sessionScope.gastoActividadQuery.result" id="item"
		requestURI="/gasto/gasto-actividad-list.do" sort="list" >

		<c:if test="${not empty requestScope['notShowMessage']}">
			<display:setProperty name="basic.msg.empty_list"><table width="100%" border="0" cellspacing="0" cellpadding="0" class="tablaTitulo"><tr><td align="center"></td></tr></table></display:setProperty>
		</c:if>
	
		<display:column sortable="true" property="actividad.proyecto.nombre" 	titleKey="sirius.gasto.proyecto.label" />
		<display:column sortable="true" property="actividad.nombre" 			titleKey="sirius.gasto.actividad.label" />
		<display:column sortable="true" property="fecha" 						titleKey="sirius.gasto.fecha.label"  	format="{0,date,dd/MM/yyyy}"  style="text-align: center"/>
		<display:column sortable="true" property="rubro.nombre" 				titleKey="sirius.gasto.rubro.label" />
		<display:column sortable="true" property="importe" 						titleKey="sirius.gasto.importe.label" style="text-align: right" decorator="com.antares.commons.view.decorator.DoubleDecorator" />
	</display-el:table>
</div>
