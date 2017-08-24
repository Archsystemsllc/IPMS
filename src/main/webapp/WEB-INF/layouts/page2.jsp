<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml"%>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<!doctype html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport" content="width=device-width"/>
<title>IPMS1</title>

<link rel='stylesheet' href='${pageContext.request.contextPath}/resources/fullcalendar/fullcalendar.css' />
<link rel='stylesheet' href='${pageContext.request.contextPath}/resources/fullcalendar/fullcalendar.print.css' />
<link href="${pageContext.request.contextPath}/resources/css/reset.css" rel="stylesheet" type="text/css" media="screen" />
<link href="${pageContext.request.contextPath}/resources/css/layout.css" rel="stylesheet" type="text/css" media="screen" />
<link href="${pageContext.request.contextPath}/resources/css/themes.css" rel="stylesheet" type="text/css" media="screen" />
<link href="${pageContext.request.contextPath}/resources/css/typography.css" rel="stylesheet" type="text/css" media="screen" />
<link href="${pageContext.request.contextPath}/resources/css/styles.css" rel="stylesheet" type="text/css" media="screen" />
<link href="${pageContext.request.contextPath}/resources/css/shCore.css" rel="stylesheet" type="text/css" media="screen" />
<link href="${pageContext.request.contextPath}/resources/css/bootstrap.css" rel="stylesheet" type="text/css" media="screen" />
<link href="${pageContext.request.contextPath}/resources/css/jquery.jqplot.css" rel="stylesheet" type="text/css" media="screen" />
<link href="${pageContext.request.contextPath}/resources/css/jquery-ui-1.8.18.custom.css" rel="stylesheet" type="text/css" media="screen" />
<link href="${pageContext.request.contextPath}/resources/css/data-table.css" rel="stylesheet" type="text/css" media="screen" />
<link href="${pageContext.request.contextPath}/resources/css/form.css" rel="stylesheet" type="text/css" media="screen" />
<link href="${pageContext.request.contextPath}/resources/css/ui-elements.css" rel="stylesheet" type="text/css" media="screen" />
<link href="${pageContext.request.contextPath}/resources/css/wizard.css" rel="stylesheet" type="text/css" media="screen" />
<link href="${pageContext.request.contextPath}/resources/css/sprite.css" rel="stylesheet" type="text/css" media="screen" />
<link href="${pageContext.request.contextPath}/resources/css/gradient.css" rel="stylesheet" type="text/css" media="screen" />


<!-- Jquery -->

<script src="${pageContext.request.contextPath}/resources/js/jquery-1.10.2.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/jquery-ui-1.10.4.custom.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/jquery.ui.touch-punch.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/chosen.jquery.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/uniform.jquery.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/bootstrap-dropdown.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/bootstrap-colorpicker.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/sticky.full.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/jquery.noty.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/selectToUISlider.jQuery.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/fg.menu.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/jquery.tagsinput.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/jquery.cleditor.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/jquery.tipsy.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/jquery.peity.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/jquery.simplemodal.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/jquery.jBreadCrumb.1.1.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/jquery.colorbox-min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/jquery.idTabs.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/jquery.multiFieldExtender.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/jquery.confirm.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/elfinder.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/accordion.jquery.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/autogrow.jquery.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/addrow.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/check-all.jquery.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/data-table.jquery.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/ZeroClipboard.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/TableTools.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/jeditable.jquery.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/duallist.jquery.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/easing.jquery.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/full-calendar.jquery.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/input-limiter.jquery.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/inputmask.jquery.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/iphone-style-checkbox.jquery.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/meta-data.jquery.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/quicksand.jquery.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/raty.jquery.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/smart-wizard.jquery.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/stepy.jquery.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/treeview.jquery.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/ui-accordion.jquery.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/vaidation.jquery.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/mosaic.1.0.1.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/jquery.collapse.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/jquery.cookie.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/jquery.autocomplete.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/localdata.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/excanvas.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/jquery.jqplot.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/chart-plugins/jqplot.dateAxisRenderer.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/chart-plugins/jqplot.cursor.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/chart-plugins/jqplot.logAxisRenderer.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/chart-plugins/jqplot.canvasTextRenderer.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/chart-plugins/jqplot.canvasAxisTickRenderer.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/chart-plugins/jqplot.highlighter.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/chart-plugins/jqplot.pieRenderer.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/chart-plugins/jqplot.barRenderer.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/chart-plugins/jqplot.categoryAxisRenderer.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/chart-plugins/jqplot.pointLabels.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/chart-plugins/jqplot.meterGaugeRenderer.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/custom-scripts.js"></script>
<script>
<style>
.error {
	color: #ff0000;
}
 
.errorblock {
	color: #000;
	background-color: #ffEEEE;
	border: 3px solid #ff0000;
	padding: 8px;
	margin: 16px;
}
</style>

<script type='text/javascript'>

	$(document).ready(function() {
	
		var date = new Date();
		var d = date.getDate();
		var m = date.getMonth();
		var y = date.getFullYear();
		
		$('#calendar').fullCalendar({
			header: {
				center: 'title',
				left: 'prev,next today',
				right: 'month,agendaWeek,agendaDay'
			},
			editable: true,
			events: [
             <c:forEach var="meeting" items="${meetings}">
				{
					title: '${meeting.title}',
					start: '${meeting.date}',
					end: '${meeting.endDate}',
					url: '${pageContext.request.contextPath}/app/meeting/${meeting.id}'
				},
			 </c:forEach>
				{
					id: 999,
					title: '',
					start: null,
					allDay: false
				}
			]
		});
		
	});

</script>


<style type='text/css'>
	#calendar {
		margin: 5 5 5 5;
		font-size: 10px;
		}
</style>

</head>
<body>
	
	<tiles:insertTemplate template="topbar.jsp" />	
	<tiles:insertTemplate template="breadcrumb.jsp" />
	
	<div class="main">
		<div class="container-fluid">
			 <tiles:insertTemplate template="navigation.jsp" />
			 
			 <tiles:insertAttribute name="content" />
		
		</div>
	</div>
	
	<tiles:insertTemplate template="footer.jsp" />
</body>
</html>