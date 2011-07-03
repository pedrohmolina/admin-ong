<%@ taglib uri="/WEB-INF/tlds/struts-bean-el.tld" prefix="bean-el"%>
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

<script>
	$(document).ready(function(){
		mostrarOcultarDivs(false);
	})

	function changeEntidad() {
	 	$("#atributo").removeOption(/^[^-]/i);
	 	$("#atributo").val("");
	 	$("#operador").removeOption(/^[^-]/i);
	 	$("#operador").val("");
	 	$("#valorOpcion").removeOption(/^[^-]/i);
	 	$("#valorOpcion").val("");

	 	var selectedOption = $("#entidad").val();
	 	if (selectedOption != "") {
			url = "<c:url value='/regla/regla-form.do?method=cargarComboAtributos' />";
	 		$("#atributo").ajaxAddOption(url, {idEntidad:selectedOption}, false);
	 	}
	}
	
	function changeAtributo() {
	 	$("#operador").removeOption(/^[^-]/i);
	 	$("#operador").val("");
	 	$("#valorOpcion").removeOption(/^[^-]/i);
	 	$("#valorOpcion").val("");
		
	 	var selectedOption = $("#atributo").val();
	 	if (selectedOption != "") {
			url = "<c:url value='/regla/regla-form.do?method=cargarComboOperadores' />";
		 	$("#operador").ajaxAddOption(url, {idAtributo:selectedOption}, false);
		 }
		mostrarOcultarDivs(true);
	}

	function mostrarOcultarDivs(recargarComboValores) {
	 	var selectedOption = $("#atributo").val();
	 	if (selectedOption != "") {
			url = "<c:url value='/regla/regla-form.do?method=' />";

			$.getJSON(url + "getTipoAtributo", {idAtributo:selectedOption}, function(json){
				if (json.tipoAtributo == "1") {
					mostrarDivValorNumerico();
				} else if (json.tipoAtributo == "3") {
					mostrarDivValorFecha();
				} else if (json.tipoAtributo == "4") {
					ocultarTodoDivValor();
				} else if (json.tipoAtributo == "5") {
					mostrarDivValorCombo();
					if (recargarComboValores) {
			 			$("#valorCombo").ajaxAddOption(url + 'cargarComboValores', {idAtributo:selectedOption}, false);
					}
				} else {
					mostrarDivValor();
				}
			});
	 	} else {
	 		mostrarDivValor();
	 	}
	}

	function ocultarDivValor() {
		$("#divValor").attr("style", "display:none");
		$("#valor").val("");
	}

	function ocultarDivValorNumerico() {
		$("#divValorNumerico").attr("style", "display:none");
		$("#valorNumerico").val("");
	}

	function ocultarDivValorFecha() {
		$("#divValorFecha").attr("style", "display:none");
		$("#valorFecha").val("");
	}

	function ocultarDivValorCombo() {
		$("#divValorCombo").attr("style", "display:none");
		$("#valorCombo").val("");
	}

	function mostrarDivValor() {
		$("#divValor").attr("style", "display:block");
		ocultarDivValorNumerico();
		ocultarDivValorFecha();
		ocultarDivValorCombo();
	}

	function mostrarDivValorNumerico() {
		ocultarDivValor();
		$("#divValorNumerico").attr("style", "display:block");
		ocultarDivValorFecha();
		ocultarDivValorCombo();
	}

	function mostrarDivValorFecha() {
		ocultarDivValor();
		ocultarDivValorNumerico();
		$("#divValorFecha").attr("style", "display:block");
		ocultarDivValorCombo();
	}

	function mostrarDivValorCombo() {
		ocultarDivValor();
		ocultarDivValorNumerico();
		ocultarDivValorFecha();
		$("#divValorCombo").attr("style", "display:block");
	}

	function ocultarTodoDivValor() {
		ocultarDivValor();
		ocultarDivValorNumerico();
		ocultarDivValorFecha();
		ocultarDivValorCombo();
	}
</script>

<div class="form">
<html:form action="/regla/regla-form-validate.do?method=save" styleId="abmForm">

	<div style="float:left;">
		<p>
		<label for="idUsuario"><bean:message key="sirius.regla.usuario.label" />(*)&nbsp;:</label>
		<html:select property="idUsuario">
			<html:option value=""><bean:message key="antares.base.seleccione.label"/></html:option>
			<html:optionsCollection name="reglaForm" property="usuarios" label="username" value="id"/>
		</html:select>
		</p><br><p>
		<label for="idEntidad"><bean:message key="sirius.regla.entidad.label" />(*)&nbsp;:</label>
		<html:select property="idEntidad" styleId="entidad" onchange="changeEntidad();" >
			<html:option value=""><bean:message key="antares.base.seleccione.label"/></html:option>
			<logic:iterate id="entidad" name="reglaForm" property="entidades">
				<html-el:option value="${entidad.id}"><bean-el:message key="${entidad.descripcion}"/></html-el:option>
			</logic:iterate>
		</html:select>
		</p><br><p>
		<label for="idAtributo"><bean:message key="sirius.regla.atributo.label" />(*)&nbsp;:</label>
		<html:select property="idAtributo" styleId="atributo" onchange="changeAtributo();" >
			<html:option value=""><bean:message key="antares.base.seleccione.label"/></html:option>
			<html:optionsCollection name="reglaForm" property="atributos" label="displayName" value="id"/>
		</html:select>
		</p><br><p>
		<label for="idOperador"><bean:message key="sirius.regla.operador.label" />(*)&nbsp;:</label>
		<html:select property="idOperador" styleId="operador">
			<html:option value=""><bean:message key="antares.base.seleccione.label"/></html:option>
			<html:optionsCollection name="reglaForm" property="operadores" label="descripcion" value="id"/>
		</html:select>
		</p><br>
		<div id="divValor">
			<p>
			<label for="valor"><bean:message key="sirius.regla.valor.label" />(*)&nbsp;:</label>
			<html:text property="valor" styleId="valor" />
		</p><br>
		</div>
		<div id="divValorNumerico">
			<p>
			<label for="valorNumerico"><bean:message key="sirius.regla.valor.label" />(*)&nbsp;:</label>
			<html:text property="valorNumerico" styleId="valorNumerico" />
			</p><br>
		</div>
		<div id="divValorFecha">
			<p>
			<label for="valorFecha"><bean:message key="sirius.regla.valor.label" />(*)&nbsp;:</label>
			<html:text property="valorFecha" styleId="valorFecha" />Calendario
			</p><br>
		</div>
		<div id="divValorCombo">
			<p>
			<label for="valorCombo"><bean:message key="sirius.regla.valor.label" />(*)&nbsp;:</label>
			<html:select property="valorCombo" styleId="valorCombo">
			<html:option value=""><bean:message key="antares.base.seleccione.label"/></html:option>
				<html:optionsCollection name="reglaForm" property="valores" label="descripcion" value="id"/>
			</html:select>
			</p><br>
		</div>
	</div>
	
	<div style="clear:both;" class="errores">
		<html:errors />
	</div>
	<div class="boton">
		<a href="#" onclick="reglaForm.submit();" tabindex="17"><bean:message key="antares.base.aceptar.label" /></a>
		<a href="<c:url value="/regla/regla-query.do?method=lastQuery"/>"><bean:message key="antares.base.cancelar.label" /></a>
	</div>
	<div style="clear:both; padding:5px 0 0 0;"></div>
</html:form>

</div>

<script type="text/javascript">
</script>
