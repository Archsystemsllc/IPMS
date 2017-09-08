<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<!-- Live chat demo Javascript 
<script src="https://code.jquery.com/jquery-1.10.2.js"></script>
<script src="https://code.jquery.com/ui/1.10.4/jquery-ui.js"></script>-->
<!-- <script src="${pageContext.request.contextPath}/resources/js/external-source.js"></script>-->
<script src="http://code.jquery.com/jquery-1.11.1.min.js"></script>
<script src="http://code.jquery.com/ui/1.11.1/jquery-ui.min.js"></script>

<script type="text/javascript">

</script>

<script>



</script>
<style>
/* The Modal (background) */
.modal {
    display: none; /* Hidden by default */
    position: fixed; /* Stay in place */
    z-index: 1; /* Sit on top */
    padding-top: 100px; /* Location of the box */
    left: 0;
    top: 0;
    width: 100%; /* Full width */
    height: 100%; /* Full height */
    overflow: auto; /* Enable scroll if needed */
    background-color: rgb(0,0,0); /* Fallback color */
    background-color: rgba(0,0,0,0.4); /* Black w/ opacity */
}

/* Modal Content */
.modal-content {
    position: relative;
    background-color: #fefefe;
    margin: auto;
    padding: 0;
    border: 1px solid #888;
    width: 80%;
    box-shadow: 0 4px 8px 0 rgba(0,0,0,0.2),0 6px 20px 0 rgba(0,0,0,0.19);
    -webkit-animation-name: animatetop;
    -webkit-animation-duration: 0.4s;
    animation-name: animatetop;
    animation-duration: 0.4s
}

/* Add Animation */
@-webkit-keyframes animatetop {
    from {top:-300px; opacity:0} 
    to {top:0; opacity:1}
}

@keyframes animatetop {
    from {top:-300px; opacity:0}
    to {top:0; opacity:1}
}

/* The Close Button */
.close {
    color: white;
    float: right;
    font-size: 28px;
    font-weight: bold;
}

.close:hover,
.close:focus {
    color: #000;
    text-decoration: none;
    cursor: pointer;
}

.modal-header {
    padding: 2px 16px;
    background-color: #e9e7e9;
    color: white;
}

.modal-body {padding: 2px 16px;}

.modal-footer {
    padding: 2px 16px;
    background-color: #e9e7e9;
    color: white;
}
</style>

<div id="left_bar">
	<div id="primary_nav" class="g_blue">
		<ul>
			<li><a href="#" title="Dashboard"><span
					class="icon_block m_dashboard">Dashboard</span></a></li>
			<li><a href="#" title="Projects"><span
					class="icon_block m_projects">Projects</span></a></li>
			<li><a href="#" title="Events"><span
					class="icon_block m_events">Events</span></a></li>
			<li><a href="#" title="Address Book"><span
					class="icon_block p_book">Address Book</span></a></li>
			<li><a href="#" title="Media"><span
					class="icon_block m_media">Media</span></a></li>
			<li><a href="#" title="Settings"><span
					class="icon_block m_settings">Settings</span></a></li>
		</ul>
	</div>
	<div id="start_menu">
		<ul>
			<li class="jtop_menu">
				<div class="icon_block black_gel">
					<span class="start_icon">Quick Menu</span>
				</div>
				<ul class="black_gel">
					<li><a
						href="${pageContext.request.contextPath}/app/projectreport"><span
							class="list-icon graph_b">&nbsp;</span>Analytics<span
							class="mnu_tline">Tagline</span></a></li>
					<li><a href="#"><span class="list-icon cog_4_b">&nbsp;</span>Settings<span
							class="mnu_tline">Tagline</span></a></li>
					<li><a href="#"><span class="list-icon vault_b">&nbsp;</span>The
							Archive<span class="mnu_tline">Tagline</span></a></li>
					<li><a href="#"><span class="list-icon list_images_b">&nbsp;</span>Task
							List<span class="mnu_tline">Tagline</span></a></li>
					<li><a href="#"><span class="list-icon documents_b">&nbsp;</span>Content
							List<span class="mnu_tline">Tagline</span></a></li>
					<li><a href="#"><span class="list-icon folder_b">&nbsp;</span>Media<span
							class="mnu_tline">Tagline</span></a></li>
					<li><a href="#"><span class="list-icon phone_3_b">&nbsp;</span>Contact<span
							class="mnu_tline">Tagline</span></a></li>
					<li><a href="#"><span class="list-icon users_b">&nbsp;</span>User<span
							class="mnu_tline">Tagline</span></a>
						<ul>
							<li><a href="#"><span class="list-icon user_2_b">&nbsp;</span>Add
									New User<span class="mnu_tline">Tagline</span></a></li>
							<li><a href="#"><span class="list-icon money_b">&nbsp;</span>Paid
									Users<span class="mnu_tline">Tagline</span></a></li>
							<li><a href="#"><span class="list-icon users_2_b">&nbsp;</span>All
									Users<span class="mnu_tline">Tagline</span></a></li>
						</ul></li>
				</ul>
			</li>
		</ul>
	</div>
	<div id="sidebar">
		<div id="secondary_nav" id="nav">
			<ul id="sidenav" class="accordion_mnu collapsible">
				<li><a href="#" title="Dashboard"><span
						class="nav_icon computer_imac"></span>Dashboards<span
						class="up_down_arrow">&nbsp;</span></a>

					<ul class="acitem">
					<sec:authorize
						access="hasRole('AdminOfSecurityService') or hasRole('EXECUTIVE')"  >
						<li><a
							href="${pageContext.request.contextPath}/app/groupdashboard"><span
								class="list-icon">&nbsp;</span>Horizontal Groups Dashboard</a></li>
					</sec:authorize>
					<sec:authorize
						access="hasRole('AdminOfSecurityService')  or hasRole('EXECUTIVE')">
						<li><a
							href="${pageContext.request.contextPath}/app/groupdashboard/business"><span
								class="list-icon">&nbsp;</span>Vertical Groups Dashboard</a></li>
					</sec:authorize>
					<sec:authorize
						access="hasRole('AdminOfSecurityService')  or hasRole('EXECUTIVE')">
						<li><a
							href="${pageContext.request.contextPath}/app/modeldashboard"><span
								class="list-icon">&nbsp;</span>Model Dashboard</a></li>
					</sec:authorize>
					<sec:authorize
						access="hasRole('AdminOfSecurityService')  or hasRole('ROLE_PROJECT_MANAGER')">
						<li><a
							href="${pageContext.request.contextPath}/app/project/36"><span
								class="list-icon">&nbsp;</span>Project Manager Dashboard</a></li>	
					</sec:authorize>							
					</ul></li>
				<sec:authorize
						access="hasRole('AdminOfSecurityService')  or hasRole('EXECUTIVE')">
				<li><a href="#"><span class="nav_icon frames"></span>
						Model Management<span class="up_down_arrow">&nbsp;</span></a>
					<ul class="acitem">
					
						<li><a href="${pageContext.request.contextPath}/app/models"><span
								class="list-icon">&nbsp;</span>View Models</a></li>
						<li><a
							href="${pageContext.request.contextPath}/app/new-Model"><span
								class="list-icon">&nbsp;</span>Add New Model</a></li>					
					</ul></li>
				</sec:authorize>
				<sec:authorize
						access="hasRole('ROLE_PROJECT_MANAGER') or hasRole('AdminOfSecurityService')">
				<li><a href="#" title="Manage Projects"><span
						class="nav_icon frames"></span> Project Management<span
						class="up_down_arrow">&nbsp;</span></a>
					<ul class="acitem">
						<li><a href="${pageContext.request.contextPath}/app/projects"><span
								class="list-icon">&nbsp;</span>View Projects</a></li>
						<li><a href="${pageContext.request.contextPath}/app/tasks"><span
								class="list-icon">&nbsp;</span>Task Management</a>
								<ul style="text-indent:10px">		
								<!-- Added Upload Functionality Buttons-->			
									<li><a href="${pageContext.request.contextPath}/app/new-task">Add Task</a></li>
									<li><a href="${pageContext.request.contextPath}/app/tasksupload">Upload Tasks</a></li>	
									<li><a href="${pageContext.request.contextPath}/app/tasks">View Tasks</a></li>				
								</ul>
						</li>
						<li><a href="${pageContext.request.contextPath}/app/projectevm/evmprojects"><span
								class="list-icon">&nbsp;</span>Financial Management</a></li>

						<li><a href="${pageContext.request.contextPath}/app/evmproject"><span
								class="list-icon">&nbsp;</span>Financial Management</a>
								<ul style="text-indent:10px">					
									<li><a href="${pageContext.request.contextPath}/app/uploadfinancial">EVM Upload</a></li>
									<li><a href="${pageContext.request.contextPath}/app/choosefinancialreport">Financial Report</a></li>
								    <li><a href="${pageContext.request.contextPath}/app/evmMetrics">EVM Metrics</a></li>
								</ul>
						</li>

						<li><a href="${pageContext.request.contextPath}/app/risks"><span
								class="list-icon">&nbsp;</span>Risk Management</a>
								<ul style="text-indent:10px">					
									<li><a href="${pageContext.request.contextPath}/app/new-risk">Add Risk</a></li>
									<li><a href="#">Upload Risks</a></li>	
									<li><a href="${pageContext.request.contextPath}/app/risks">View Risks</a></li>				
								</ul>
						</li>
						<li><a href="${pageContext.request.contextPath}/app/issues"><span
								class="list-icon">&nbsp;</span>Issue Management</a>
								<ul style="text-indent:10px">					
									<li><a href="${pageContext.request.contextPath}/app/new-issue">Add Issue</a></li>
									<li><a href="${pageContext.request.contextPath}/app/issues/upload">Upload Issues</a></li>	
									<li><a href="${pageContext.request.contextPath}/app/issues">View Issues</a></li>				
								</ul>
						</li>
						<li><a href="${pageContext.request.contextPath}/app/upload"><span
								class="list-icon">&nbsp;</span>MS Project Integration</a></li>
						<li><a href="${pageContext.request.contextPath}/app/meetings"><span
								class="list-icon">&nbsp;</span>Meetings Management</a>
								<ul style="text-indent:10px">					
									<li><a href="${pageContext.request.contextPath}/app/new-programmeeting">Add Meeting</a></li>										
									<li><a href="${pageContext.request.contextPath}/app/meetings">View Meetings</a></li>	
									<li><a href="${pageContext.request.contextPath}/app/meetingminutesupload">Upload Meeting Minutes</a></li>				
								</ul>
						</li>						
						<li><a href="${pageContext.request.contextPath}/app/new-project"><span
								class="list-icon">&nbsp;</span>Add Project</a></li>						
					</ul>
						
						<li><a href="${pageContext.request.contextPath}/app/uploadlessonslearned"><span
											class="list-icon">&nbsp;</span>Upload Lessons Learned</a></li>	
										<ul style="text-indent:10px">					
									
									<li><a href="${pageContext.request.contextPath}/app/lessonslearned">View Lessons Learned</a></li>
									<li><a href="${pageContext.request.contextPath}/app/uploadactionitems"><span
											class="list-icon">&nbsp;</span>Upload Action Items</a></li>
									<li><a href="${pageContext.request.contextPath}/app/actionitems">View Action items</a></li>				
								</ul>
					<li><a href="${pageContext.request.contextPath}/app/sharepoint"><span
								class="list-icon">&nbsp;</span>SharePoint</a></li>
				</li>
				</sec:authorize>
				<sec:authorize
						access="hasRole('ROLE_PROJECT_MANAGER') or hasRole('AdminOfSecurityService') or hasRole('EXECUTIVE')">
						<li><a
							href="${pageContext.request.contextPath}/app/artifacts"><span
								class="nav_icon blocks_images"></span> Artifacts Manager</a></li>
				</sec:authorize> 
				<li><a href="#"><span class="nav_icon documents"></span>Reports<span
						class="up_down_arrow">&nbsp;</span></a>
					<ul class="acitem">
						<sec:authorize
						access="hasRole('AdminOfSecurityService') or hasRole('EXECUTIVE')">
						<li><a
							href="${pageContext.request.contextPath}/app/groupreport"><span
								class="list-icon">&nbsp;</span>Group Report</a></li>
						</sec:authorize>
						<sec:authorize
						access="hasRole('AdminOfSecurityService') or hasRole('EXECUTIVE')">
						<li><a
							href="${pageContext.request.contextPath}/app/programreport"><span
								class="list-icon">&nbsp;</span>Model Report</a></li>
						</sec:authorize>
						<sec:authorize
						access="hasRole('ROLE_PROJECT_MANAGER') or hasRole('AdminOfSecurityService')">
						<li><a
							href="${pageContext.request.contextPath}/app/projectreport"><span
								class="list-icon">&nbsp;</span>Project Report</a></li>
						</sec:authorize>						
					</ul></li>
				<sec:authorize
						access="hasRole('AdminOfSecurityService')">
				<li><a href="#"><span class="nav_icon frames"></span>Stakeholder
						Administration<span class="up_down_arrow">&nbsp;</span></a>
					<ul class="acitem">
						<li><a
							href="${pageContext.request.contextPath}/app/principals"><span
								class="list-icon">&nbsp;</span>Users</a></li>
						<li><a href="${pageContext.request.contextPath}/app/roles"><span
								class="list-icon">&nbsp;</span>Roles</a></li>
						<li><a
							href="${pageContext.request.contextPath}/app/privileges"><span
								class="list-icon">&nbsp;</span>Privileges</a></li>
					</ul></li>	
				</sec:authorize>					
			</ul>           
          	<!-- <div> -->
			<div id="left_bar">
				<div class="grid_2">
					<div class="widget_wrap" style="width: 200px;">
						<div class="widget_top">
							<span class="h_icon list"></span>
							<h6>Alerts</h6>
						</div>
						<div class="widget_content">

							<h3></h3>
							<p></p>

						</div>

					</div>
				</div>

				<div></div>
				<div id="left_bar">
					
				</div>
			</div>

		</div>
	</div>
</div>
