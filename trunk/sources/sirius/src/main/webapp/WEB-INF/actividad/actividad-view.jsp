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

<div class="form">
<html:form action="/actividad/actividad-form.do?method=view">
	<div style="float: left;">
		<p>
		<label for="labelMeta"><bean:message key="sirius.actividad.meta.label" />&nbsp;:</label>
		<html:text property="labelMeta" readonly="true" />
		</p><br><p>
		<label for="nombre"><bean:message key="sirius.actividad.nombre.label" />&nbsp;:</label>
		<html:text property="nombre" readonly="true" />
		</p><br><p>
		<label for="ponderacion"><bean:message key="sirius.actividad.ponderacion.label" />&nbsp;:</label>
		<html:text property="ponderacion" readonly="true" />
		</p><br><p>
		<label for="completitud"><bean:message key="sirius.actividad.completitud.label" />&nbsp;:</label>
		<html:text maxlength="3" property="completitud" readonly="true"  styleClass="tres" />
		</p><br><p>
		<label for="fechaInicio"><bean:message key="sirius.actividad.fechaInicio.label" />&nbsp;:</label>
		<html:text property="fechaInicio" readonly="true" />
		</p><br><p>
		<label for="fechaFin"><bean:message key="sirius.actividad.fechaFin.label" />&nbsp;:</label>
		<html:text property="fechaFin" readonly="true" />
		</p><br><p>
		<label for="observaciones"><bean:message key="sirius.actividad.observaciones.label" />&nbsp;:</label>
		<html:textarea property="observaciones" rows="5" readonly="true" />
		</p><br><p>
		<label for="labelFinanciador"><bean:message key="sirius.actividad.financiador.label" />&nbsp;:</label>
		<html:text property="labelFinanciador" readonly="true" />
		<logic:notEmpty name="actividadForm" property="fechaFinalizacion" >
			</p><br><p>
			<label for="fechaFinalizacion"><bean:message key="sirius.actividad.fechaFinalizacion.label" />&nbsp;:</label>
			<html:text property="fechaFinalizacion" readonly="true" />
		</logic:notEmpty>
		</p><br>
	</div>

	<div style="clear: both;" class="errores">
		<html:errors />
	</div>

	<div class="boton">
		<a href="<c:url value="/actividad/actividad-query.do?method=lastQuery"/>"><bean:message key="antares.base.volver.label" /></a>
	</div>
	<div style="clear:both; padding:5px 0 0 0;"></div>
</html:form>
</div>

