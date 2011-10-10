<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tlds/struts-html-el.tld" prefix="html-el"%>
<%@ taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/tlds/struts-logic-el.tld" prefix="logic-el"%>
<%@ taglib uri="/WEB-INF/tlds/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/tlds/struts-nested.tld" prefix="nested"%>
<%@ taglib uri="/WEB-INF/tlds/displaytag.tld" prefix="display"%>
<%@ taglib uri="/WEB-INF/tlds/displaytag-el.tld" prefix="display-el"%>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tlds/authz.tld" prefix="authz"%>

<div class="form">
<html:form action="/proyecto/proyecto-form-validate.do?method=save" styleId="abmForm" enctype="multipart/form-data">

	<div style="float:left;">
		<p>
		<label for="nombre"><bean:message key="sirius.proyecto.nombre.label" />(*)&nbsp;:</label>
		<html:text property="nombre" />
		</p><br><p>
		<label for="descripcion"><bean:message key="sirius.proyecto.descripcion.label" />(*)&nbsp;:</label>
		<html:textarea property="descripcion" rows="5"/>
		</p><br><p>
		<label for="fechaInicio"><bean:message key="sirius.proyecto.fechaInicio.label" />&nbsp;:</label>
		<html:text property="fechaInicio" styleClass="datepicker"/>
		</p><br><p>
		<label for="fechaFin"><bean:message key="sirius.proyecto.fechaFin.label" />&nbsp;:</label>
		<html:text property="fechaFin" styleClass="datepicker"/>
		</p><br><p>
		<label for="ubicacion"><bean:message key="sirius.proyecto.ubicacion.label" />&nbsp;:</label>
		<html:text property="ubicacion" />
		</p><br><p>
		<label for="idCoordinadores"><bean:message key="sirius.proyecto.coordinadores.label" />&nbsp;:</label>
		<html:select property="idCoordinadores" multiple="true" style="height: 100px">
			<html:optionsCollection name="proyectoForm" property="coordinadores" label="nombreYApellido" value="id"/>
		</html:select>
		<html:hidden property="idCoordinadores" value="0" />
		</p><br><p>
		<label for="idResponsable"><bean:message key="sirius.proyecto.responsable.label" />(*)&nbsp;:</label>
		<html:select property="idResponsable">
			<html:option value=""><bean:message key="antares.base.seleccione.label"/></html:option>
			<html:optionsCollection name="proyectoForm" property="responsables" label="nombreYApellido" value="id"/>
		</html:select>
		</p><br><p>
		<label for="beneficiariosDirectos"><bean:message key="sirius.proyecto.beneficiariosDirectos.label" />&nbsp;:</label>
		<html:textarea property="beneficiariosDirectos" rows="5"/>
		</p><br><p>
		<label for="beneficiariosIndirectos"><bean:message key="sirius.proyecto.beneficiariosIndirectos.label" />&nbsp;:</label>
		<html:textarea property="beneficiariosIndirectos" rows="5"/>
		</p><br><p>
		<label for="idFinanciador"><bean:message key="sirius.proyecto.financiador.label" />(*)&nbsp;:</label>
		<html:select property="idFinanciador">
			<html:option value=""><bean:message key="antares.base.seleccione.label"/></html:option>
			<html:optionsCollection name="proyectoForm" property="financiadores" label="nombre" value="id"/>
		</html:select>
		</p><br><p>
		<label for="presupuestoTotal"><bean:message key="sirius.proyecto.presupuestoTotal.label" />(*)&nbsp;:</label>
		<html:text property="presupuestoTotal" />
		</p><br><p>
		<label for="idAreaTematica"><bean:message key="sirius.proyecto.areasTematicas.label" />(*)&nbsp;:</label>
		<html:select property="idAreaTematica" multiple="true" style="height: 100px">
			<html:optionsCollection name="proyectoForm" property="areasTematicas" label="descripcion" value="id"/>
		</html:select>
		<html:hidden property="idAreaTematica" value="0" />
		</p><br>
		<logic:iterate id="tipoAgrupamiento" name="proyectoForm" property="tiposAgrupamiento">
			<p>
			<label><bean:write name="tipoAgrupamiento" property="descripcion" /> :</label>
			<logic:equal name="proyectoForm" property="modificarAgrupamiento" value="true">
				<html-el:radio property="idTipoAgrupamiento" value="${tipoAgrupamiento.id}" style="width: 20px" />
			</logic:equal>
			<logic:equal name="proyectoForm" property="modificarAgrupamiento" value="false">
				<html-el:radio property="idTipoAgrupamiento" value="${tipoAgrupamiento.id}" disabled="true" style="width: 20px"/>
			</logic:equal>
			</p><br>
		</logic:iterate>
		<br><p>
		<label for="idArchivo"><bean:message key="sirius.proyecto.archivo.label" />&nbsp;:</label>
		<html:file property="archivo" style="width:300px"/>
		</p><br>
		<logic:notEmpty name="proyectoForm" property="hashArchivo">
			<p>
			<label>&nbsp;</label>
			<c:url var="url" value="/archivo/archivo.do">
				<c:param name="method" value="download" />
				<c:param name="hash" value="${proyectoForm.hashArchivo}" />
			</c:url>
			<a href="<c:out value="${url}"/>"><bean:write name="proyectoForm" property="nombreArchivo"/></a>
			</p><br>
		</logic:notEmpty>
	</div>
	
	<div style="clear:both;" class="errores">
		<html:errors />
	</div>
	<div class="boton">
		<a href="#" onclick="proyectoForm.submit();" tabindex="17"><bean:message key="antares.base.aceptar.label" /></a>
		<a href="<c:url value="/proyecto/proyecto-query.do?method=lastQuery"/>"><bean:message key="antares.base.cancelar.label" /></a>
	</div>
	<div style="clear:both; padding:5px 0 0 0;"></div>
</html:form>

</div>

<script type="text/javascript">
</script>
