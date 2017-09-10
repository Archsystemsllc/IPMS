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
<meta name="viewport" content="width=device-width" />
<title>Zing Logged Out</title>

<style>
.errorblock {
	color: #ff0000;
	background-color: #ffEEEE;
	border: 3px solid #ff0000;
	padding: 8px;
	margin: 16px;
}
</style>
<link href="${pageContext.request.contextPath}/resources/css/reset.css"
	rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/resources/css/layout.css"
	rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/resources/css/themes.css"
	rel="stylesheet" type="text/css">
<link
	href="${pageContext.request.contextPath}/resources/css/typography.css"
	rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/resources/css/styles.css"
	rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/resources/css/shCore.css"
	rel="stylesheet" type="text/css">
<link
	href="${pageContext.request.contextPath}/resources/css/bootstrap.css"
	rel="stylesheet" type="text/css">
<link
	href="${pageContext.request.contextPath}/resources/css/jquery.jqplot.css"
	rel="stylesheet" type="text/css">
<link
	href="${pageContext.request.contextPath}/resources/css/jquery-ui-1.8.18.custom.css"
	rel="stylesheet" type="text/css">
<link
	href="${pageContext.request.contextPath}/resources/css/data-table.css"
	rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/resources/css/form.css"
	rel="stylesheet" type="text/css">
<link
	href="${pageContext.request.contextPath}/resources/css/ui-elements.css"
	rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/resources/css/wizard.css"
	rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/resources/css/sprite.css"
	rel="stylesheet" type="text/css">
<link
	href="${pageContext.request.contextPath}/resources/css/gradient.css"
	rel="stylesheet" type="text/css">
<!--[if IE 7]>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/ie/ie7.css" />
<![endif]-->
<!--[if IE 8]>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/ie/ie8.css" />
<![endif]-->
<!--[if IE 9]>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/ie/ie9.css" />
<![endif]-->
<!-- Jquery -->
<script
	src="${pageContext.request.contextPath}/resources/js/jquery-1.7.2.min.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/js/jquery-ui-1.8.18.custom.min.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/js/jquery.ui.touch-punch.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/js/chosen.jquery.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/js/uniform.jquery.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/js/bootstrap-dropdown.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/js/bootstrap-colorpicker.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/js/sticky.full.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/js/jquery.noty.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/js/selectToUISlider.jQuery.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/fg.menu.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/js/jquery.tagsinput.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/js/jquery.cleditor.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/js/jquery.tipsy.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/js/jquery.peity.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/js/jquery.simplemodal.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/js/jquery.jBreadCrumb.1.1.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/js/jquery.colorbox-min.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/js/jquery.idTabs.min.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/js/jquery.multiFieldExtender.min.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/js/jquery.confirm.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/js/elfinder.min.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/js/accordion.jquery.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/js/autogrow.jquery.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/js/check-all.jquery.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/js/data-table.jquery.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/js/ZeroClipboard.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/js/TableTools.min.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/js/jeditable.jquery.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/js/duallist.jquery.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/js/easing.jquery.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/js/full-calendar.jquery.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/js/input-limiter.jquery.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/js/inputmask.jquery.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/js/iphone-style-checkbox.jquery.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/js/meta-data.jquery.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/js/quicksand.jquery.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/js/raty.jquery.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/js/smart-wizard.jquery.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/js/stepy.jquery.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/js/treeview.jquery.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/js/ui-accordion.jquery.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/js/vaidation.jquery.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/js/mosaic.1.0.1.min.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/js/jquery.collapse.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/js/jquery.cookie.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/js/jquery.autocomplete.min.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/js/localdata.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/js/excanvas.min.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/js/jquery.jqplot.min.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/js/chart-plugins/jqplot.dateAxisRenderer.min.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/js/chart-plugins/jqplot.cursor.min.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/js/chart-plugins/jqplot.logAxisRenderer.min.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/js/chart-plugins/jqplot.canvasTextRenderer.min.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/js/chart-plugins/jqplot.canvasAxisTickRenderer.min.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/js/chart-plugins/jqplot.highlighter.min.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/js/chart-plugins/jqplot.pieRenderer.min.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/js/chart-plugins/jqplot.barRenderer.min.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/js/chart-plugins/jqplot.categoryAxisRenderer.min.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/js/chart-plugins/jqplot.pointLabels.min.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/js/chart-plugins/jqplot.meterGaugeRenderer.min.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/js/custom-scripts.js"></script>
</head>
<script type="text/javascript">
	$(function() {
		$(window).resize(
				function() {
					$('.login_container').css(
							{
								position : 'absolute',
								left : ($(window).width() - $(
										'.login_container').outerWidth()) / 2,
								top : ($(window).height() - $(
										'.login_container').outerHeight()) / 2
							});
				});
		// To initially run the function:
		$(window).resize();
	});
</script>
<body id="theme-default" class="full_block">
	<div id="login_page">
		<div class="login_container">
			<div class="login_header blue_lgel">
				<ul class="login_branding">
					<li>
						<%--<div class="logo_small">--%>
						<div style="text-align: center; vertical-align: middle;">
							<%-- <img src="${pageContext.request.contextPath}/resources/images/IPMS_logo3.png" style="padding-left:2px;"width="324" height="70"
								alt="zing"> --%>
							<h2 style="color: navy; margin-left: 100px; text-align: center;">IPMS</h2>
						</div>
						<%-- <span>IPMS Login</span>--%> <%-- <span>IPMS Login</span>--%>

						<h2>IPMS Logged Out</h2>
						<h2>You can log back in</h2> <c:if test="${not empty error}">
							<div class="errorblock">
								Login failed, try again.<br /> Caused :
								${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}
							</div>
						</c:if>
					</li>
				</ul>
				<div class="block_container blue_d">
					<c:if test="${not empty error}">
						<div class="errorblock">Login failed, try again.Caused :
							${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}</div>
					</c:if>
					<a href="${pageContext.request.contextPath}/util/login"><input
						class="login_btn blue_lgel" name="" value="Login" type="button">
					</a>
				</div>
			</div>
		</div>
	</div>
</body>
</html>