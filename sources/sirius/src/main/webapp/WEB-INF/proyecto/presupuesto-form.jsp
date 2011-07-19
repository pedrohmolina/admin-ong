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
	<html:form action="/proyecto/presupuesto-form.do?method=save" styleId="abmForm" enctype="multipart/form-data">
	</html:form>
	<h2><bean:write name="presupuestoForm" property="nombreProyecto"/></h2>
	<div align="center">
		<table id="jQGrid">
		</table>
	</div>
	<div class="presupuesto">
		<p>
		<label for="nombre"><bean:message key="sirius.proyecto.presupuestoTotalDisponible.label" />&nbsp;:</label>
		<label id="presupuestoDisponible">&nbsp;</label>
		</p><br>
	</div>
	<div style="clear:both; padding:5px 0 0 0;"></div>
	<div class="boton">
		<a href="#" onclick="aceptar();" tabindex="17"><bean:message key="antares.base.aceptar.label" /></a>
		<a href="<c:url value="/proyecto/proyecto-query.do?method=lastQuery"/>"><bean:message key="antares.base.cancelar.label" /></a>
	</div>
	<div style="clear:both; padding:5px 0 0 0;"></div>
</div>

<link rel="stylesheet" type="text/css" href="<c:url value="/css/ui.jqgrid.css"/>" />
<script type="text/javascript" language="javascript" src="<c:url value="/scripts/i18n/grid.locale-es.js"/>"></script>
<script type="text/javascript" language="javascript" src="<c:url value="/scripts/jquery.jqGrid.min.js"/>"></script>

<script>
	var lastsel;
    function fillGridOnEvent(){
        $("#jQGrid").jqGrid({
			url: getContextUrl('/proyecto/presupuesto-form.do?method=getData'),
			datatype: "json",
			scrollOffset: 2,
			autowidth: true,
			shrinkToFit: true,
			colNames: ['Actividad', <bean:write name="presupuestoForm" property="nombresColumnas" filter="false"/> '% TOTAL', 'TOTAL'],
			colModel: [
				{name: 'Actividad', index: 'Actividad', sortable: false},
				<logic:iterate id="rubro" name="presupuestoForm" property="rubros">
				    {
				    	name: '<bean:write name="rubro" property="id"/>', 
				    	index: '<bean:write name="rubro" property="nombre"/>',
				    	align: "right", 
				    	sortable: false, 
				    	editable: true, 
				    	edittype: 'text'
				    },
				</logic:iterate>
				{name: '% TOTAL', index: '% TOTAL', align: "right", sortable: false, width: 40},
				{name: 'TOTAL', index: 'TOTAL', align: "right", sortable: false, width: 40}
			],			
			cellurl: '<c:url value="/proyecto/presupuesto-form.do"/>?method=updateData',
			afterSaveCell: afterSave,
			loadComplete: loadComplete,
			multiselect: false,
			paging: false,
			cellEdit: true,
			height: 400
		});
	}

	function loadComplete() {
		actualizarPresupuestoDisponible();
		changeEditable();
	}

	// Es hace que las ultimas dos filas no sean editables (filas de totales)
	function changeEditable() {
	    var cm = $("#jQGrid").jqGrid('getGridParam','colModel');
	    for (var i = 0; i < cm.length; i++) {
			var cells = $("tbody > tr.jqgrow > td:nth-child(" + (i+1) + ")", $("#jQGrid")[0]);
			for (var j = 0; j < cells.length; j++) {
				if (j >= cells.length - 2) {
					var cell = $(cells[j]);
					cell.addClass('not-editable-cell');
				}
			}
		} 
	}

	function afterSave(result) {
		$("#jQGrid").trigger("reloadGrid");  
	} 
    
	function aceptar() {
		document.presupuestoForm.submit();
	}

	function actualizarPresupuestoDisponible() {
		var url = "<c:url value='/proyecto/presupuesto-form.do?method=getPresupuestoDisponible' />";
		$.getJSON(url, {}, function(json){
			$("#presupuestoDisponible").html(json.presupuestoDisponible);
			if (parseFloat(json.presupuestoDisponible) >= 0) {
				$("#presupuestoDisponible").removeClass('textrojo');
			} else {
				$("#presupuestoDisponible").addClass('textrojo');
			}
		});
	}

    $(document).ready(function (){
        fillGridOnEvent();
    });
</script>
