<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tlds/struts-bean-el.tld" prefix="bean-el"%>
<%@ taglib uri="/WEB-INF/tlds/struts-html-el.tld" prefix="html-el"%>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/tlds/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/tlds/struts-nested.tld" prefix="nested"%>
<%@ taglib uri="/WEB-INF/tlds/displaytag.tld" prefix="display"%>
<%@ taglib uri="/WEB-INF/tlds/displaytag-el.tld" prefix="display-el"%>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c"%>
<div class="boxTitulo">
	<div class="bi">
		<div class="bt"><div></div></div>
		<h1>Datos</h1>
		<div class="bb"><div></div></div>
	</div>
</div>
<div class="form">
<html:form action="/proyecto/proyecto-query.do?method=query">
	<h1>Datos</h1>
	<div style="float:left;">
		<label for="nombre"><bean:message key="sirius.proyecto.nombre.label" />&nbsp;:</label>
		<html:text property="nombre" readonly="true" />
		<br>
		<label for="descripcion"><bean:message key="sirius.proyecto.descripcion.label" />&nbsp;:</label>
		<html:textarea property="descripcion" rows="5" readonly="true" />
		<br>
		<label for="fechaInicio"><bean:message key="sirius.proyecto.fechaInicio.label" />&nbsp;:</label>
		<html:text property="fechaInicio" readonly="true" />
		<br>
		<label for="fechaFin"><bean:message key="sirius.proyecto.fechaFin.label" />&nbsp;:</label>
		<html:text property="fechaFin" readonly="true" />
		<br>
		<label for="ubicacion"><bean:message key="sirius.proyecto.ubicacion.label" />&nbsp;:</label>
		<html:text property="ubicacion" readonly="true" />
		<br>
		<label for="idCoordinadores"><bean:message key="sirius.proyecto.coordinadores.label" />&nbsp;:</label>
		<html:select property="idCoordinadores" multiple="true">
			<html:optionsCollection name="proyectoForm" property="coordinadores" label="nombreYApellido" value="id"/>
		</html:select>
		<br>
		<label for="idResponsable"><bean:message key="sirius.proyecto.responsable.label" />&nbsp;:</label>
		<html:text property="labelResponsable" readonly="true" />
		<br>
		<label for="beneficiariosDirectos"><bean:message key="sirius.proyecto.beneficiariosDirectos.label" />&nbsp;:</label>
		<html:textarea property="beneficiariosDirectos" rows="5" readonly="true" />
		<br>
		<label for="beneficiariosIndirectos"><bean:message key="sirius.proyecto.beneficiariosIndirectos.label" />&nbsp;:</label>
		<html:textarea property="beneficiariosIndirectos" rows="5" readonly="true" />
		<br>
		<label for="idFinanciador"><bean:message key="sirius.proyecto.financiador.label" />&nbsp;:</label>
		<html:text property="labelFinanciador" readonly="true" />
		<br>
		<label for="idAreaTematica"><bean:message key="sirius.proyecto.areasTematicas.label" />&nbsp;:</label>
		<html:select property="idAreaTematica" multiple="true">
			<html:optionsCollection name="proyectoForm" property="areasTematicas" label="descripcion" value="id"/>
		</html:select>
		<br>
	</div>

	<div style="clear: both;">
		<html:errors />
	</div> 
	
	<div class="boton">
		<a href="<c:url value="/proyecto/proyecto-query.do?method=lastQuery"/>"><bean:message key="antares.base.volver.label" /></a>
	</div>
</html:form>
</div>

