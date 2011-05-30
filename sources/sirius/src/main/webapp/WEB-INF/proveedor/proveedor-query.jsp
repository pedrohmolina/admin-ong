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
	hacerSubmit('proveedor/proveedor-query.do?method=initQuery');
}

function confirmarAccion(mensaje) {
	return confirm(mensaje);
}

</script>

<div class="form">
	<html:form action="/proveedor/proveedor-query.do?method=query">
	<h1>B�squeda de Proveedores</h1>
	<div style="float:left; width: 100%;">
		<p>
		<label for="filtroNombre"><bean:message key="sirius.proveedor.nombre.label" />:</label>
		<html:text property="filtroNombre" />
		</p><br><p>
		<label for="filtroIdTipoProveedor"><bean:message key="sirius.proveedor.tipoProveedor.label" />:</label>
		<html:select property="filtroIdTipoProveedor">
			<html:option value=""><bean:message key="antares.base.seleccione.label"/></html:option>
			<html:optionsCollection name="proveedorForm" property="tiposProveedor" label="descripcion" value="id"/>
		</html:select>
		</p><br><p>
		<label for="filtroCuit"><bean:message key="sirius.proveedor.cuit.label" />:</label>
		<html:text property="filtroCuit" />
		</p><br><p>
		<label for="filtroCbu"><bean:message key="sirius.proveedor.cbu.label" />:</label>
		<html:text property="filtroCbu" />
		</p><br><p>
		<label for="filtroTelefono"><bean:message key="sirius.proveedor.telefono.label" />:</label>
		<html:text property="filtroTelefono" />
		</p><br><p>
		<label for="filtroDireccion"><bean:message key="sirius.proveedor.direccion.label" />:</label>
		<html:text property="filtroDireccion" />
		</p><br><p>
		<label for="filtroContacto"><bean:message key="sirius.proveedor.contacto.label" />:</label>
		<html:text property="filtroContacto" />
		</p><br><p>
		<label for="filtroCelular"><bean:message key="sirius.proveedor.celular.label" />:</label>
		<html:text property="filtroCelular" />
		</p><br><p>
		<label for="filtroEmail"><bean:message key="sirius.proveedor.email.label" />:</label>
		<html:text property="filtroEmail" />
		</p><br>
	</div>

	<div style="float: left; width: 100%;">
		<div class="boton">
			<a href="#" onclick="javascript:limpiarFiltro();"><bean:message key="antares.base.limpiarfiltro.label"/></a>
			<authz:authorize ifAllGranted="ENTIDAD_PROVEEDOR-LISTADO">
				<a href="#" onclick="proveedorForm.submit();"><bean:message key="antares.base.buscar.label" /></a>
			</authz:authorize>
			<authz:authorize ifAllGranted="ENTIDAD_PROVEEDOR-ALTA">
				<a href="#" onclick="return hacerSubmit('proveedor/proveedor-form.do?method=initCreate');"><bean:message key="antares.base.nuevo.label" /></a>
			</authz:authorize>
		</div>
	</div>
	
	</html:form>
	
	<div style="clear: both;">
		<html:errors />
	</div>

	<h1>Resultado de la B�squeda</h1>
	<display-el:table export="true" defaultsort="1" pagesize="${requestScope['displayTagPageSize']}" class="tabla" name="sessionScope.proveedorForm.result" id="item"
		requestURI="/proveedor/proveedor-query.do" sort="list" >

		<c:if test="${not empty requestScope['notShowMessage']}">
			<display:setProperty name="basic.msg.empty_list"><table width="100%" border="0" cellspacing="0" cellpadding="0" class="tablaTitulo"><tr><td align="center"></td></tr></table></display:setProperty>
		</c:if>
	
		<display:column sortable="true" property="nombre" 							titleKey="sirius.proveedor.nombre.label" />
		<display:column sortable="true" property="tipoProveedor.descripcion" 		titleKey="sirius.proveedor.tipoProveedor.label" />
		<display:column sortable="true" property="contacto" 						titleKey="sirius.proveedor.contacto.label" />
		<display:column sortable="true" property="direccion" 						titleKey="sirius.proveedor.direccion.label" />
		<display:column sortable="true" property="telefono" 						titleKey="sirius.proveedor.telefono.label" />

		<display:column title="Acciones" media="html">
			<authz:authorize ifAllGranted="ENTIDAD_PROVEEDOR-DETALLE">
				<a href="<c:url value="/proveedor/proveedor-form.do?method=view&id="/><bean:write name="item" property="id"/>"><img border="0" alt="Visualizar" title="Visualizar"
					src="<c:url value="/img/icon.lupa.gif"/>" /></a>
			</authz:authorize>
			<authz:authorize ifAllGranted="ENTIDAD_PROVEEDOR-MODIFICACION">
				<a href="<c:url value="/proveedor/proveedor-form.do?method=initUpdate&id="/><bean:write name="item" property="id"/>"><img border="0" alt="Editar" title="Modificar"
					src="<c:url value="/img/icoModificar.gif"/>" /></a>
			</authz:authorize>
			<authz:authorize ifAllGranted="ENTIDAD_PROVEEDOR-BAJA">
				<a href="<c:url value="/proveedor/proveedor-query.do?method=remove&id="/><bean:write name="item" property="id"/>"><img border="0" alt="Eliminar" title="Eliminar"
					src="<c:url value="/img/icons/cross.png"/>" onclick="return confirmarAccion('Est&aacute; seguro que desea eliminar el registro <bean:write name="item" property="nombre"/>?')" /></a>
			</authz:authorize>
		</display:column>
	</display-el:table>
</div>
