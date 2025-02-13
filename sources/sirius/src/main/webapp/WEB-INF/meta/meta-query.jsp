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
	hacerSubmit('meta/meta-query.do?method=initQuery');
}

function confirmarAccion(mensaje) {
	return confirm(mensaje);
}

</script>

<div class="form">
	<html:form action="/meta/meta-query-validate.do?method=query">
	<div style="float:left; width: 100%;">
		<p>
		<label for="filtroIdObjetivoEspecifico"><bean:message key="sirius.meta.objetivoEspecifico.label" />&nbsp;:</label>
		<html:select property="filtroIdObjetivoEspecifico">
			<html:option value=""><bean:message key="antares.base.seleccione.label"/></html:option>
			<html:optionsCollection name="metaQuery" property="objetivosEspecificos" label="nombre" value="id"/>
		</html:select>
		</p><br><p>
		<label for="filtroNombre"><bean:message key="sirius.meta.nombre.label" />:</label>
		<html:text maxlength="255" property="filtroNombre"></html:text>
		</p><br>
	</div>

	<div style="float: left; width: 100%;">
		<div class="boton">
			<a href="#" onclick="javascript:limpiarFiltro();"><bean:message key="antares.base.limpiarfiltro.label"/></a>
			<authz:authorize ifAllGranted="ENTIDAD_META-LISTADO">
				<a href="#" onclick="metaQuery.submit();"><bean:message key="antares.base.buscar.label" /></a>
			</authz:authorize>
			<authz:authorize ifAllGranted="ENTIDAD_META-ALTA">
				<a href="#" onclick="return hacerSubmit('meta/meta-form.do?method=initCreate');"><bean:message key="antares.base.nuevo.label" /></a>
			</authz:authorize>
		</div>
	</div>
	
	</html:form>
	
	<div style="clear: both;" class="errores">
		<html:errors />
	</div>

	<h1><bean:message key="antares.base.result.label" /></h1>
	<display-el:table export="true" defaultsort="1" pagesize="${requestScope['displayTagPageSize']}" class="tabla" name="sessionScope.metaQuery.result" id="item"
		requestURI="/meta/meta-query.do" sort="list" >

		<display:column sortable="true" property="proyecto.nombre" 				titleKey="sirius.meta.proyecto.label" 			maxLength="30" />
		<display:column sortable="true" property="objetivoEspecifico.nombre" 	titleKey="sirius.meta.objetivoEspecifico.label" maxLength="30" />
		<display:column sortable="true" property="nombre" 						titleKey="sirius.meta.nombre.label" 			maxLength="30" />
		<display:column sortable="true" property="ponderacion" 					titleKey="sirius.meta.ponderacion.label" 		style="text-align: right" />
		<display:column sortable="true" property="completitud" 					titleKey="sirius.meta.completitud.label" 		style="text-align: right" decorator="com.antares.commons.view.decorator.DoubleDecorator" />

		<display:column title="Acciones" media="html" style="text-align: center">
			<authz:authorize ifAllGranted="ENTIDAD_META-DETALLE">
				<a href="<c:url value="/meta/meta-form.do?method=view&id="/><bean:write name="item" property="id"/>"><img border="0" alt="Visualizar" title="Visualizar"
					src="<c:url value="/img/icon.lupa.gif"/>" /></a>
			</authz:authorize>
			<authz:authorize ifAllGranted="ENTIDAD_META-MODIFICACION">
				<a href="<c:url value="/meta/meta-form.do?method=initUpdate&id="/><bean:write name="item" property="id"/>"><img border="0" alt="Editar" title="Modificar"
					src="<c:url value="/img/icoModificar.gif"/>" /></a>
			</authz:authorize>
			<authz:authorize ifAllGranted="ENTIDAD_META-BAJA">
				<a href="<c:url value="/meta/meta-query.do?method=remove&id="/><bean:write name="item" property="id"/>"><img border="0" alt="Eliminar" title="Eliminar"
					src="<c:url value="/img/icons/cross.png"/>" onclick="return confirmarAccion('Est&aacute; seguro que desea eliminar el registro <bean:write name="item" property="nombre"/>?')" /></a>
			</authz:authorize>
		</display:column>
	</display-el:table>
</div>
