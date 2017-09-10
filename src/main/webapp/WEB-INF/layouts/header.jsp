<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<script type='text/javascript' src='https://www.google.com/jsapi'></script>
<script type='text/javascript'>
	<script src= "http://code.jquery.com/jquery-1.8.3.js">
</script>
<script src="http://code.jquery.com/ui/1.9.2/jquery-ui.js"></script>
<script type="text/javascript"
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.9.0/jquery.min.js"></script>
<script type="text/javascript" src="jquery.timepicker.js"></script>
<script type="text/javascript" src="lib/base.js"></script>

<script type="text/javascript">
	$(document).ready(function() {

		$('#user_nav ul li a').append('<span></span>');

		$('#user_nav ul li a').hover(function() {
			$(this).find('span').animate({
				opacity : 'show',
				top : '-70'
			}, 'slow');

			var hoverTexts = $(this).attr('title');
			$(this).find('span').text(hoverTexts);
		},

		function() {
			$(this).find('span').animate({
				opacity : 'hide',
				top : '-90'
			}, 'fast');
		});
	});
</script>

<sec:authentication var="user" property="principal" />

<div id="header" class="blue_lin">
	<div class="header_left">
		<div>
			<%-- <img src="${pageContext.request.contextPath}/resources/images/IPMS_logo4.png" width="230" height="60" alt="IPMS"> --%>
			<h1 style="color: white; padding-left: 10px">IPMS</h1>
		</div>
		<div id="responsive_mnu">
			<a href="#responsive_menu" class="fg-button" id="hierarchybreadcrumb"><span
				class="responsive_icon"></span>Menu</a>
			<div id="responsive_menu" class="hidden">
				<ul>
					<li><a href="#"> Dashboard</a>
						<ul>
							<li><a href="dashboard.html">Dashboard Main</a></li>
							<li><a href="dashboard-01.html">Dashboard 01</a></li>
							<li><a href="dashboard-02.html">Dashboard 02</a></li>
							<li><a href="dashboard-03.html">Dashboard 03</a></li>
							<li><a href="dashboard-04.html">Dashboard 04</a></li>
						</ul></li>
					<li><a href="#"> Forms</a>
						<ul>
							<li><a href="form-elements.html">All Forms Elements</a></li>
							<li><a href="left-label-form.html">Left Label Form</a></li>
							<li><a href="top-label-form.html">Top Label Form</a></li>
							<li><a href="form-xtras.html">Additional Forms (3)</a></li>
							<li><a href="form-validation.html">Form Validation</a></li>
							<li><a href="signup-form.html">Signup Form</a></li>
							<li><a href="content-post.html">Content Post Form</a></li>
							<li><a href="wizard.html">wizard</a></li>
						</ul></li>
					<li><a href="table.html"> Tables</a></li>
					<li><a href="ui-elements.html">User Interface Elements</a></li>
					<li><a href="buttons-icons.html">Butons And Icons</a></li>
					<li><a href="widgets.html">All Widgets</a></li>
					<li><a href="#">Pages</a>
						<ul>
							<li><a href="post-preview.html">Content</a></li>
							<li><a href="login-01.html" target="_blank">Login 01</a></li>
							<li><a href="login-02.html" target="_blank">Login 02</a></li>
							<li><a href="login-03.html" target="_blank">Login 03</a></li>
							<li><a href="forgot-pass.html" target="_blank">Forgot
									Password</a></li>
						</ul></li>
					<li><a href="typography.html">Typography</a></li>
					<li><a href="#">Grid</a>
						<ul>
							<li><a href="content-grid.html">Content Grid</a></li>
							<li><a href="form-grid.html">Form Grid</a></li>
						</ul></li>
					<li><a href="chart.html">Chart/Graph</a></li>
					<li><a href="gallery.html">Gallery</a></li>
					<li><a href="calendar.html">Calendar</a></li>
					<li><a href="file-manager.html">File Manager</a></li>
					<li><a href="#">Error Pages</a>
						<ul>
							<li><a href="403.html" target="_blank">403</a></li>
							<li><a href="404.html" target="_blank">404</a></li>
							<li><a href="505.html" target="_blank">405</a></li>
							<li><a href="500.html" target="_blank">500</a></li>
							<li><a href="503.html" target="_blank">503</a></li>
						</ul></li>
					<li><a href="invoice.html">Invoice</a></li>
					<li><a href="#">Email Templates</a>
						<ul>
							<li><a
								href="email-templates/forgot-pass-email-template.html"
								target="_blank">Forgot Password</a></li>
							<li><a
								href="email-templates/registration-confirmation-email-template.html"
								target="_blank">Registration Confirmation</a></li>
						</ul></li>
				</ul>
			</div>
		</div>
	</div>
	<div class="header_right"  align="right">

		<span style="color: white;">Logged On: <strong><c:out value="${user.username}" /></strong></span><br/><br/>
		<span style="color: white;"> <a style="color: white;" href="${pageContext.request.contextPath}/app/profile/${user.username}" title="View Profile">Profile</a>
	    &#124; <a style="color: white;" href="#" title="Manage Settings">Settings</a> 
	    &#124; <a style="color: white;" href="${pageContext.request.contextPath}/app/help" title="Browse Help Page">Help</a>
		&nbsp;&nbsp;&nbsp;&nbsp; <a style="color: white;" href="${pageContext.request.contextPath}/util/logout" title="Logout from IPMS"><strong><u> Logout </u>
		</strong> </a>
		</span>

	</div>
</div>