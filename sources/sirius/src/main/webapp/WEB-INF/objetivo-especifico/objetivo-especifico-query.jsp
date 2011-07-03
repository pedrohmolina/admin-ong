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
	hacerSubmit('objetivo-especifico/objetivo-especifico-query.do?method=initQuery');
}

function confirmarAccion(mensaje) {
	return confirm(mensaje);
}

</script>

<div class="form">
	<html:form action="/objetivo-especifico/objetivo-especifico-query.do?method=query">
	<div style="float: left; width: 100%;">
		<p>
		<label for="filtroIdObjetivoGeneral"><bean:message key="sirius.objetivoEspecifico.objetivoGeneral.label" />&nbsp;:</label>
		<html:select property="filtroIdObjetivoGeneral">
			<html:option value=""> <bean:message key="antares.base.seleccione.label" /></html:option>
			<html:optionsCollection name="objetivoEspecificoForm" property="objetivosGenerales" label="nombre" value="id" />
		</html:select>
		</p><br><p>
		<label for="filtroNombre"><bean:message key="sirius.objetivoEspecifico.nombre.label" />:</label>
		<html:text maxlength="255" property="filtroNombre"></html:text>
		</p><br>
	</div>

	<div style="float: left; width: 100%;">
		<div class="boton">
			<a href="#" onclick="javascript:limpiarFiltro();"><bean:message key="antares.base.limpiarfiltro.label" /></a>
			<authz:authorize ifAllGranted="ENTIDAD_OBJETIVO_ESPECIFICO-LISTADO">
				<a href="#" onclick="objetivoEspecificoForm.submit();"><bean:message key="antares.base.buscar.label" /></a>
			</authz:authorize>
			<authz:authorize ifAllGranted="ENTIDAD_OBJETIVO_ESPECIFICO-ALTA">
				<a href="#" onclick="return hacerSubmit('objetivo-especifico/objetivo-especifico-form.do?method=initCreate');"><bean:message key="antares.base.nuevo.label" /></a>
			</authz:authorize>
		</div>
	</div>

	</html:form>

	<div style="clear: both;" class="errores">
		<html:errors />
	</div>

	<h1>Resultado de la Búsqueda</h1>
	<display-el:table export="true" defaultsort="1" pagesize="${requestScope['displayTagPageSize']}" class="tabla" name="sessionScope.objetivoEspecificoForm.result" id="item"
		requestURI="/objetivo-especifico/objetivo-especifico-query.do" sort="list" >

		<c:if test="${not empty requestScope['notShowMessage']}">
			<display:setProperty name="basic.msg.empty_list"><table width="100%" border="0" cellspacing="0" cellpadding="0" class="tablaTitulo"><tr><td align="center"></td></tr></table></display:setProperty>
		</c:if>

		<display:column sortable="true" property="nombre" 		titleKey="sirius.objetivoEspecifico.nombre.label" />
		<display:column sortable="true" property="ponderacion" 	titleKey="sirius.objetivoEspecifico.ponderacion.label" />
		<display:column sortable="true" property="completitud" 	titleKey="sirius.objetivoEspecifico.completitud.label" />

		<display:column title="Acciones" media="html">
			<authz:authorize ifAllGranted="ENTIDAD_OBJETIVO_ESPECIFICO-DETALLE">
				<a href="<c:url value="/objetivo-especifico/objetivo-especifico-form.do?method=view&id="/><bean:write name="item" property="id"/>"><img border="0" alt="Visualizar" title="Visualizar"
					src="<c:url value="/img/icon.lupa.gif"/>" /></a>
			</authz:authorize>
			<authz:authorize ifAllGranted="ENTIDAD_OBJETIVO_ESPECIFICO-MODIFICACION">
				<a href="<c:url value="/objetivo-especifico/objetivo-especifico-form.do?method=initUpdate&id="/><bean:write name="item" property="id"/>"><img border="0" alt="Editar" title="Modificar"
					src="<c:url value="/img/icoModificar.gif"/>" /></a>
			</authz:authorize>
			<authz:authorize ifAllGranted="ENTIDAD_OBJETIVO_ESPECIFICO-BAJA">
				<a href="<c:url value="/objetivo-especifico/objetivo-especifico-query.do?method=remove&id="/><bean:write name="item" property="id"/>"><img border="0" alt="Eliminar" title="Eliminar"
					src="<c:url value="/img/icons/cross.png"/>" onclick="return confirmarAccion('Est&aacute; seguro que desea eliminar el registro <bean:write name="item" property="nombre"/>?')" /></a>
			</authz:authorize>
		</display:column>
	</display-el:table>
</div>
