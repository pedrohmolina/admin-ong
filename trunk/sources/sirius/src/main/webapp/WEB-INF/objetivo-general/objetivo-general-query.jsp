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
	hacerSubmit('objetivo-general/objetivo-general-query.do?method=initQuery');
}

function confirmarAccion(mensaje) {
	return confirm(mensaje);
}

</script>

<div class="form">
	<html:form action="/objetivo-general/objetivo-general-query.do?method=query">
	<h1>Búsqueda de Objetivos Generales</h1>
	<div style="float:left; width: 100%;">
		<label for="filtroIdProyecto"><bean:message key="sirius.objetivoGeneral.proyecto.label" />&nbsp;:</label>
		<html:select property="filtroIdProyecto">
			<html:option value=""><bean:message key="antares.base.seleccione.label"/></html:option>
			<html:optionsCollection name="objetivoGeneralForm" property="proyectos" label="nombre" value="id"/>
		</html:select>
		<br>
		<label for="filtroNombre"><bean:message key="sirius.objetivoGeneral.nombre.label" />:</label>
		<html:text maxlength="255" property="filtroNombre"></html:text>
		<br>
	</div>

	<div style="float: left; width: 100%;">
		<div class="boton">
			<a href="#" onclick="javascript:limpiarFiltro();"><bean:message key="antares.base.limpiarfiltro.label"/></a>
			<a href="#" onclick="objetivoGeneralForm.submit();"><bean:message key="antares.base.buscar.label" /></a>
			<a href="#" onclick="return hacerSubmit('objetivo-general/objetivo-general-form.do?method=initCreate');"><bean:message key="antares.base.nuevo.label" /></a>
		</div>
	</div>
	
	</html:form>
	
	<div style="clear: both;">
		<html:errors />
	</div>

	<h1>Resultado de la Búsqueda</h1>
	<display-el:table export="true" defaultsort="1" pagesize="${requestScope['displayTagPageSize']}" class="tabla" name="sessionScope.objetivoGeneralForm.result" id="item"
		requestURI="/objetivo-general/objetivo-general-query.do" sort="list" >

		<c:if test="${not empty requestScope['notShowMessage']}">
			<display:setProperty name="basic.msg.empty_list"><table width="100%" border="0" cellspacing="0" cellpadding="0" class="tablaTitulo"><tr><td align="center"></td></tr></table></display:setProperty>
		</c:if>

		<display:column sortable="true" property="nombre" 		titleKey="sirius.objetivoGeneral.nombre.label" />
		<display:column sortable="true" property="ponderacion" 	titleKey="sirius.objetivoGeneral.ponderacion.label" />
		<display:column sortable="true" property="completitud" 	titleKey="sirius.objetivoGeneral.completitud.label" />

		<display:column title="Acciones" media="html">
			<a href="<c:url value="/objetivo-general/objetivo-general-form.do?method=view&id="/><bean:write name="item" property="id"/>"><img border="0" alt="Visualizar" title="Visualizar"
				src="<c:url value="/img/icon.lupa.gif"/>" /></a>
			<a href="<c:url value="/objetivo-general/objetivo-general-form.do?method=initUpdate&id="/><bean:write name="item" property="id"/>"><img border="0" alt="Editar" title="Modificar"
				src="<c:url value="/img/icoModificar.gif"/>" /></a>
			<a href="<c:url value="/objetivo-general/objetivo-general-query.do?method=remove&id="/><bean:write name="item" property="id"/>"><img border="0" alt="Eliminar" title="Eliminar"
				src="<c:url value="/img/icons/cross.png"/>" onclick="return confirmarAccion('Est&aacute; seguro que desea eliminar el registro <bean:write name="item" property="nombre"/>?')" /></a>
		</display:column>
	</display-el:table>
</div>
