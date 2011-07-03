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
	hacerSubmit('financiador/financiador-query.do?method=initQuery');
}

function confirmarAccion(mensaje) {
	return confirm(mensaje);
}

</script>

<div class="form">
	<html:form action="/financiador/financiador-query.do?method=query">
	<div style="float:left; width: 100%;">
		<p>
		<label for="filtroNombre"><bean:message key="sirius.financiador.nombre.label" />:</label>
		<html:text property="filtroNombre" />
		</p><br><p>
		<label for="filtroIdTipoFinanciador"><bean:message key="sirius.financiador.tipoFinanciador.label" />:</label>
		<html:select property="filtroIdTipoFinanciador">
			<html:option value=""><bean:message key="antares.base.seleccione.label"/></html:option>
			<html:optionsCollection name="financiadorForm" property="tiposFinanciador" label="descripcion" value="id"/>
		</html:select>
		</p><br><p>
		<label for="filtroCuit"><bean:message key="sirius.financiador.cuit.label" />:</label>
		<html:text property="filtroCuit" />
		</p><br><p>
		<label for="filtroCbu"><bean:message key="sirius.financiador.cbu.label" />:</label>
		<html:text property="filtroCbu" />
		</p><br><p>
		<label for="filtroTelefono"><bean:message key="sirius.financiador.telefono.label" />:</label>
		<html:text property="filtroTelefono" />
		</p><br><p>
		<label for="filtroDireccion"><bean:message key="sirius.financiador.direccion.label" />:</label>
		<html:text property="filtroDireccion" />
		</p><br><p>
		<label for="filtroContacto"><bean:message key="sirius.financiador.contacto.label" />:</label>
		<html:text property="filtroContacto" />
		</p><br><p>
		<label for="filtroCelular"><bean:message key="sirius.financiador.celular.label" />:</label>
		<html:text property="filtroCelular" />
		</p><br><p>
		<label for="filtroEmail"><bean:message key="sirius.financiador.email.label" />:</label>
		<html:text property="filtroEmail" />
		</p><br><p>
		<label for="filtroIdEstadoFinanciador"><bean:message key="sirius.financiador.estadoFinanciador.label" />:</label>
		<html:select property="filtroIdEstadoFinanciador">
			<html:option value=""><bean:message key="antares.base.seleccione.label"/></html:option>
			<html:optionsCollection name="financiadorForm" property="estadosFinanciador" label="descripcion" value="id"/>
		</html:select>
		</p><br>
	</div>

	<div style="float: left; width: 100%;">
		<div class="boton">
			<a href="#" onclick="javascript:limpiarFiltro();"><bean:message key="antares.base.limpiarfiltro.label"/></a>
			<authz:authorize ifAllGranted="ENTIDAD_FINANCIADOR-LISTADO">
				<a href="#" onclick="financiadorForm.submit();"><bean:message key="antares.base.buscar.label" /></a>
			</authz:authorize>
			<authz:authorize ifAllGranted="ENTIDAD_FINANCIADOR-ALTA">
				<a href="#" onclick="return hacerSubmit('financiador/financiador-form.do?method=initCreate');"><bean:message key="antares.base.nuevo.label" /></a>
			</authz:authorize>
		</div>
	</div>
	
	</html:form>
	
	<div style="clear: both;" class="errores">
		<html:errors />
	</div>

	<h1>Resultado de la B�squeda</h1>
	<display-el:table export="true" defaultsort="1" pagesize="${requestScope['displayTagPageSize']}" class="tabla" name="sessionScope.financiadorForm.result" id="item"
		requestURI="/financiador/financiador-query.do" sort="list" >

		<c:if test="${not empty requestScope['notShowMessage']}">
			<display:setProperty name="basic.msg.empty_list"><table width="100%" border="0" cellspacing="0" cellpadding="0" class="tablaTitulo"><tr><td align="center"></td></tr></table></display:setProperty>
		</c:if>
	
		<display:column sortable="true" property="nombre" 							titleKey="sirius.financiador.nombre.label" />
		<display:column sortable="true" property="tipoFinanciador.descripcion" 		titleKey="sirius.financiador.tipoFinanciador.label" />
		<display:column sortable="true" property="estadoFinanciador.descripcion" 	titleKey="sirius.financiador.estadoFinanciador.label" />
		<display:column sortable="true" property="contacto" 						titleKey="sirius.financiador.contacto.label" />
		<display:column sortable="true" property="direccion" 						titleKey="sirius.financiador.direccion.label" />
		<display:column sortable="true" property="telefono" 						titleKey="sirius.financiador.telefono.label" />

		<display:column title="Acciones" media="html">
			<authz:authorize ifAllGranted="ENTIDAD_FINANCIADOR-DETALLE">
				<a href="<c:url value="/financiador/financiador-form.do?method=view&id="/><bean:write name="item" property="id"/>"><img border="0" alt="Visualizar" title="Visualizar"
					src="<c:url value="/img/icon.lupa.gif"/>" /></a>
			</authz:authorize>
			<authz:authorize ifAllGranted="ENTIDAD_FINANCIADOR-MODIFICACION">
				<a href="<c:url value="/financiador/financiador-form.do?method=initUpdate&id="/><bean:write name="item" property="id"/>"><img border="0" alt="Editar" title="Modificar"
					src="<c:url value="/img/icoModificar.gif"/>" /></a>
			</authz:authorize>
			<authz:authorize ifAllGranted="ENTIDAD_FINANCIADOR-BAJA">
				<a href="<c:url value="/financiador/financiador-query.do?method=remove&id="/><bean:write name="item" property="id"/>"><img border="0" alt="Eliminar" title="Eliminar"
					src="<c:url value="/img/icons/cross.png"/>" onclick="return confirmarAccion('Est&aacute; seguro que desea eliminar el registro <bean:write name="item" property="nombre"/>?')" /></a>
			</authz:authorize>
		</display:column>
	</display-el:table>
</div>
