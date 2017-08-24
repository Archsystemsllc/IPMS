<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml"%>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1" />
<title>IPMS Login</title>

<style>
.errorblock {
	color: #ff0000;
	background-color: #ffEEEE;
	border: 3px solid #ff0000;
	padding: 8px;
	margin: 16px;
}
</style>
<link href="https://fonts.googleapis.com/css?family=Bitter" rel="stylesheet">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<style>
.carousel-inner>.item>img, .carousel-inner>.item>a>img {
	/* width: 60%; */
	margin: auto;
	height: 260px;
	width: 60%;
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
<script type="text/javascript">
	$(function() {
		$(window).resize(
			function() {
				$('.login_container').css(
					{
						position : 'absolute',
						/*left : ($(window).width() + $('#left_bar').width() - $(
								'.login_container').outerWidth()) / 2,*/
						right : 15,
						bottom : 57,
						width : 300
					/* 	top : ($(window).height() - $(
								'.login_container').outerHeight()) / 2 */
					});
			});
		// To initially run the function:
		$(window).resize();
	});
</script>
</head>
<body id="theme-default" class="full_block">
	<div id="login_page">
		<!-- <div id="header" class="blue_lin"></div>


		<div id="left_bar" style="background-color:#A3CAE1;width: 300px !important;">  -->
	</div>
	<div class="container">
		<br>
		<div id="myCarousel" class="carousel slide" data-ride="carousel"
			style="position: relative; z-index: 1;">
			<!-- Indicators -->
			<ol class="carousel-indicators" style="z-index: 1 !important">
				<li data-target="#myCarousel" data-slide-to="0" class="active"></li>
				<li data-target="#myCarousel" data-slide-to="1"></li>
				<li data-target="#myCarousel" data-slide-to="2"></li>
			</ol>

			<!-- Wrapper for slides -->
			<div class="carousel-inner" role="listbox">
				<div class="item active">
					<img
						src="${pageContext.request.contextPath}/resources/images/CMS.png"
						alt="1">
				</div>

				<div class="item">
					<img
						src="${pageContext.request.contextPath}/resources/images/cms_CMMI.png"
						alt="2">
				</div>

				<div class="item">
					<img
						src="${pageContext.request.contextPath}/resources/images/CMS_medicare.jpg"
						alt="3">
				</div>
			</div>

			<!-- Left and right controls -->
			<a class="left carousel-control" href="#myCarousel" role="button"
				data-slide="prev"> <span
				class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
				<span class="sr-only">Previous</span>
			</a> <a class="right carousel-control" href="#myCarousel" role="button"
				data-slide="next"> <span
				class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
				<span class="sr-only">Next</span>
			</a>
		</div>
	</div>
	<div class="container"
		style="position: absolute; left: 15px; bottom: 45px; width: 1020px;">
		<p style="font-size:16px;text-align:justify;color:white;font-family: 'Bitter', serif;">The Integrated Project Management Suite (IPMS) is a web-based,
			scalable program management tool designed to allow for the agile
			management of all models and model support services at CMMI.</p>
		<p style="font-size:16px;text-align:justify;color:white;font-family: 'Bitter', serif;">IPMS has been developed to provides role-based, intuitive
			enterprise integration of all the project management tools and
			information that CMMI model groups currently use in a disparate,
			siloed fashion. With IPMS, requirements, resources, tasks, issues,
			lessons learned, action items and meetings can all be created,
			prioritized and revised while maximizing knowledge and time
			management.</p>
		<p style="font-size:16px;text-align:justify;color:white;font-family: 'Bitter', serif;">IPMS provides customizable dashboards to show tasks, action
			items project schedules, variances, EVM and burn rate among other
			many possible views. Group Directors Deputy Directors, Technical and
			Program Leads can oversee and direct multiple quality reporting
			programs, see real-time status of model groups and individual models
			down to the daily scrum level. IPMS is a tool and a process to
			achieve the true promise of the Innovation Center's development and
			program management capabilities.</p>
	</div>
	<div class="login_container">
		<div class="login_header blue_lgel" style="width: 300px;">
			<ul class="login_branding" style="width: 286px;">
				<li>
					<%--<div class="logo_small">--%>
					<div style="text-align: center; vertical-align: middle;">
						<!-- <img src="${pageContext.request.contextPath}/resources/images/logo_zing.png" width="130" height="50"
								alt="zing">
								<img align="top" src="${pageContext.request.contextPath}/resources/images/ipmslogo.png" width="130" height="80"
								alt="ipms">-->
					<%-- 	<img
							src="${pageContext.request.contextPath}/resources/images/IPMS_logo3.png"
							width="281" height="68" style="padding-left:2px" alt="IPMS"> --%>
						<h2 style="color:navy;margin-left: 10px; text-align: center;">IPMS</h2>
					</div> <%-- <span>IPMS Login</span>--%>

				</li>

			</ul>
		</div>
		<div class="block_container blue_d">
			<c:if test="${not empty error}">
				<div class="errorblock">Login failed, try again.Caused :
					${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}</div>
			</c:if>
			<form name='f' action="<c:url value='/j_spring_security_check' />"
				method='POST'>
				<div class="block_form">
					<ul>
						<li class="login_user"><input type="text" id="user"
							name="j_username"></li>
						<li class="login_pass"><input type="password" id="pw"
							name="j_password"></li>
					</ul>
					<a href="${pageContext.request.contextPath}/app/groupdashboard"><input
						class="login_btn blue_lgel" name="" value="Login" type="submit"></a>
				</div>
				<ul class="login_opt_link">
					<li><a href="#">Forgot Password?</a></li>
					<li class="remember_me right"><input name="" class="rem_me"
						type="checkbox" value="checked"> Remember Me</li>
				</ul>
			</form>
		</div>
	</div>



	<div class="grid_container">
		<div class="grid_12 full_block"
			style="position: absolute; bottom: 0px; background-color: white; width: 100%; margin: auto;">
			<p class="left"
				style="padding-left: 10px; font-weight: bold; padding-top: 1em; margin-bottom: 1em">IPMS
				V2.0</p>
			<p class="right"
				style="padding-right: 10px; font-weight: bold; padding-top: 1em; margin-bottom: 1em">
				Copyright &copy; 2017 - <a href="http://www.archsystemsinc.com"
					title="">www.archsystemsinc.com</a>
			</p>
		</div>
	</div>


</body>
</html>