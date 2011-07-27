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
	hacerSubmit('reportes/reporte-proveedor.do?method=initForm');
}

function confirmarAccion(mensaje) {
	return confirm(mensaje);
}

</script>

<div class="form">
	<html:form action="/reportes/reporte-proveedor.do?method=generarReporteProveedor">
	
	<div style="float:left; width: 100%;">
		<p>
		<h2><bean:message key="sirius.reportes.filtros" /></h2>
		<label for="nombre"><bean:message key="sirius.proveedor.nombre.label" />:</label>
		<html:text property="nombre" />
		</p><br><p>
		<label for="cuit"><bean:message key="sirius.proveedor.cuit.label" />:</label>
		<html:text property="cuit" />
		</p><br><p>
		<label for="cbu"><bean:message key="sirius.proveedor.cbu.label" />:</label>
		<html:text property="cbu" />
		</p><br><p>
	</div>

	<br>
	<br>
	
	<div style="float: left; width: 100%;">
	<p>
		<div class="boton">
			<a href="#" onclick="javascript:limpiarFiltro();"><bean:message key="antares.base.limpiarfiltro.label"/></a>
			<a href="#" onclick="return hacerSubmit('/reportes/reporte-proveedor.do?method=verResultados');"><bean:message key="sirius.reportes.vistaPrevia" /></a>
		</div>
	</p><br>	
	</div>


	<div style="float: left; width: 100%;">
	<p>	
	<h2><bean:message key="sirius.reportes.vistaPreviaResultados" /></h2>
	<display-el:table export="false" defaultsort="1" pagesize="${requestScope['displayTagPageSize']}" class="tabla" name="sessionScope.reporteProveedorForm.result" id="item"
		requestURI="/reportes/reporte-proveedor.do" sort="list" >

		<c:if test="${not empty requestScope['notShowMessage']}">
			<display:setProperty name="basic.msg.empty_list"><table width="100%" border="0" cellspacing="0" cellpadding="0" class="tablaTitulo"><tr><td align="center"></td></tr></table></display:setProperty>
		</c:if>

		<display:column sortable="true" property="nombre" 						titleKey="sirius.proveedor.nombre.label" />
		<display:column sortable="true" property="tipoProveedor.descripcion" 	titleKey="sirius.proveedor.tipoProveedor.label" />
		<display:column sortable="true" property="cuit" 						titleKey="sirius.proveedor.cuit.label" />
		<display:column sortable="true" property="cbu"							titleKey="sirius.proveedor.cbu.label" />
		<display:column sortable="true" property="direccion"	 				titleKey="sirius.proveedor.direccion.label" />

	</display-el:table>
	</p><br>	
	</div>

	<div style="float:left;">
	<p>	
		<h2 onclick="hideShow('divColumnas')"><bean:message key="sirius.reportes.columnas" /></h2>
		<div id="divColumnas">
			<p>
				<label for="verTipoProveedor"><bean:message key="sirius.proveedor.tipoProveedor.label" />:</label>
				<html:checkbox property="verTipoProveedor" value="true" style="width:20px" />
				<html:hidden property="verTipoProveedor" value="false" />
			</p><br><p>
				<label for="verCuit"><bean:message key="sirius.proveedor.cuit.label" />:</label>
				<html:checkbox property="verCuit" value="true" style="width:20px" />
				<html:hidden property="verCuit" value="false" />
			</p><br><p>
				<label for="verCBU"><bean:message key="sirius.proveedor.cbu.label" />:</label>
				<html:checkbox property="verCBU" value="true" style="width:20px" />
				<html:hidden property="verCBU" value="false" />
			</p><br><p>
				<label for="verContacto"><bean:message key="sirius.proveedor.contacto.label" />:</label>
				<html:checkbox property="verContacto" value="true" style="width:20px" />
				<html:hidden property="verContacto" value="false" />
			</p><br><p>
				<label for="verObservaciones"><bean:message key="sirius.proveedor.observaciones.label" />:</label>
				<html:checkbox property="verObservaciones" value="true" style="width:20px" />
				<html:hidden property="verObservaciones" value="false" />
			</p><br><p>
				<label for="verDireccion"><bean:message key="sirius.proveedor.direccion.label" />:</label>
				<html:checkbox property="verDireccion" value="true" style="width:20px" />
				<html:hidden property="verDireccion" value="false" />
			</p><br><p>
				<label for="verTelefono"><bean:message key="sirius.proveedor.telefono.label" />:</label>
				<html:checkbox property="verTelefono" value="true" style="width:20px" />
				<html:hidden property="verTelefono" value="false" />
			</p><br><p>
				<label for="verEmail"><bean:message key="sirius.proveedor.email.label" />:</label>
				<html:checkbox property="verEmail" value="true" style="width:20px" />
				<html:hidden property="verEmail" value="false" />
			</p><br>
		</div>
	</div>

	<div style="float: left; width: 100%;">
		<p>
		<label for="formatosReporte"><bean:message key="sirius.reportes.formatoSalida" />:</label>
		<html:select property="formatoReporte">
			<html:optionsCollection name="reporteProveedorForm" property="formatosReporte" label="descripcion" value="id"/>
		</html:select>
		<div class="boton">
			<a href="#" onclick="reporteProveedorForm.submit();"><bean:message key="sirius.reportes.generar" /></a>
		</div>
		</p><br>
	</div>

	</html:form>
	
	<div style="clear: both;" class="errores">
		<html:errors />
	</div>

</div>
