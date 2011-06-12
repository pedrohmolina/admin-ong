<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c"%>

<script type="text/javascript" language="javascript" src="<c:url value="/scripts/jquery-1.5.2.min.js"/>"></script>
<script type="text/javascript" language="javascript" src="<c:url value="/scripts/jquery.selectboxes.js"/>"></script>
<script type="text/javascript" language="javascript" src="<c:url value="/scripts/jquery-ui-1.8.13.custom.min.js"/>"></script>

<script type="text/javascript">
	function hacerSubmit(act, form) {
		if (!form) {
			form = document.forms[0];
		}
		if (act) {
			form.action = getContextUrl(act);
		}
		form.submit();
	}

	function getContextUrl(url) {
		return '<c:url value="/" />' + url;
	}
	
	$(function() {
		$( ".datepicker" ).datepicker({ 
			dateFormat: 'dd/MM/yy',
			showOn: "button",
			buttonImage: "../img/calendar.gif",
			buttonImageOnly: true
		});
	});
	
	


</script>
