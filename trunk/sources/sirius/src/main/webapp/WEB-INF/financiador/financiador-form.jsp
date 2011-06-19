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
<html:form action="/financiador/financiador-form-validate.do?method=save" styleId="abmForm">

	<h1>Datos</h1>
	<div style="float:left;">
		<p>
		<label for="nombre"><bean:message key="sirius.financiador.nombre.label" />(*)&nbsp;:</label>
		<html:text property="nombre" />
		</p><br><p>
		<label for="idTipoFinanciador"><bean:message key="sirius.financiador.tipoFinanciador.label" />(*)&nbsp;:</label>
		<html:select property="idTipoFinanciador">
			<html:option value=""><bean:message key="antares.base.seleccione.label"/></html:option>
			<html:optionsCollection name="financiadorForm" property="tiposFinanciador" label="descripcion" value="id"/>
		</html:select>
		</p><br><p>
		<label for="idEstadoFinanciador"><bean:message key="sirius.financiador.estadoFinanciador.label" />(*)&nbsp;:</label>
		<html:select property="idEstadoFinanciador">
			<html:option value=""><bean:message key="antares.base.seleccione.label"/></html:option>
			<html:optionsCollection name="financiadorForm" property="estadosFinanciador" label="descripcion" value="id"/>
		</html:select>
		</p><br><p>
		<label for="cuit"><bean:message key="sirius.financiador.cuit.label" />&nbsp;:</label>
		<html:text property="cuit" />
		</p><br><p>
		<label for="cbu"><bean:message key="sirius.financiador.cbu.label" />&nbsp;:</label>
		<html:text property="cbu" />
		</p><br><p>
		<label for="direccion"><bean:message key="sirius.financiador.direccion.label" />(*)&nbsp;:</label>
		<html:text property="direccion" />
		</p><br><p>
		<label for="contacto"><bean:message key="sirius.financiador.contacto.label" />&nbsp;:</label>
		<html:text property="contacto" />
		</p><br><p>
		<label for="telefono"><bean:message key="sirius.financiador.telefono.label" />(*)&nbsp;:</label>
		<html:text property="telefono" />
		</p><br><p>
		<label for="celular"><bean:message key="sirius.financiador.celular.label" />&nbsp;:</label>
		<html:text property="celular" />
		</p><br><p>
		<label for="email"><bean:message key="sirius.financiador.email.label" />&nbsp;:</label>
		<html:text property="email" />
		</p><br><p>
		<label for="observaciones"><bean:message key="sirius.financiador.observaciones.label" />:</label>
		<html:textarea property="observaciones" rows="5" />
		</p><br>
	</div>
	<div style="clear:both;" class="errores">
		<html:errors />
	</div>	

	<div class="boton">
		<a href="#" onclick="financiadorForm.submit();" tabindex="17"><bean:message key="antares.base.aceptar.label" /></a>
		<a href="<c:url value="/financiador/financiador-query.do?method=lastQuery"/>"><bean:message key="antares.base.cancelar.label" /></a>
	</div>
	<div style="clear:both; padding:5px 0 0 0;"></div>
</html:form>

</div>

<script type="text/javascript">
</script>
