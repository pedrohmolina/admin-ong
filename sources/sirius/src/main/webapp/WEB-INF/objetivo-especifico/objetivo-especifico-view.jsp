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
<html:form action="/objetivo-especifico/objetivo-especifico-form.do?method=view">
	<div style="float:left;">
		<p>
		<label for="labelObjetivoGeneral"><bean:message key="sirius.objetivoEspecifico.objetivoGeneral.label" />&nbsp;:</label>
		<html:text maxlength="255" property="labelObjetivoGeneral" readonly="true" />
		</p><br><p>
		<label for="nombre"><bean:message key="sirius.objetivoEspecifico.nombre.label" />&nbsp;:</label>
		<html:text maxlength="255" property="nombre" readonly="true" />
		</p><br><p>
		<label for="ponderacion"><bean:message key="sirius.objetivoEspecifico.ponderacion.label" />&nbsp;:</label>
		<html:text maxlength="3" property="ponderacion" readonly="true"  styleClass="tres" />
		</p><br><p>
		<label for="descripcion"><bean:message key="sirius.objetivoEspecifico.descripcion.label" />&nbsp;:</label>
		<html:textarea property="descripcion" rows="5" readonly="true" />
		</p><br>
	</div>

	<div style="clear: both;" class="errores">
		<html:errors />
	</div> 
	
	<div class="boton">
		<a href="<c:url value="/objetivo-especifico/objetivo-especifico-query.do?method=lastQuery"/>"><bean:message key="antares.base.volver.label" /></a>
	</div>
	<div style="clear:both; padding:5px 0 0 0;"></div>
</html:form>
</div>

