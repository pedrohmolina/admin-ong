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

	function submitenter(myfield,e)	{
		var keycode;
		if (window.event) {
			keycode = window.event.keyCode;
		} else if (e) {
			keycode = e.which;
		} else {
			return true;
		}
		
		if (keycode == 13) {
		   myfield.form.submit();
		   return false;
		} else {
		   return true;
		}
	}
	
</script>

<div class="form">
<html:form action="/usuario/usuario-password-form-validate.do?method=savePassword" styleId="abmForm">

	<h1>Datos</h1>
	<div style="float:left;">
		<p>
		<label for="passwordActual"><bean:message key="sirius.usuario.passwordActual.label" />(*)&nbsp;:</label>
		<html:password property="passwordActual" />
		</p><br><p>
		<label for="password"><bean:message key="sirius.usuario.password.label" />(*)&nbsp;:</label>
		<html:password property="password" />
		</p><br><p>
		<label for="password2"><bean:message key="sirius.usuario.password2.label" />(*)&nbsp;:</label>
		<html:password property="password2" onKeyPress="return submitenter(this,event)" />
		</p><br>
	</div>
	
	<div style="clear:both;" class="errores">
		<html:errors />
	</div>
	<div class="boton">
		<a href="#" onclick="usuarioPasswordForm.submit();" tabindex="17"><bean:message key="antares.base.aceptar.label" /></a>
		<a href="<c:url value="//base/home_init.do"/>"><bean:message key="antares.base.cancelar.label" /></a>
	</div>
</html:form>

</div>

<script type="text/javascript">
</script>
