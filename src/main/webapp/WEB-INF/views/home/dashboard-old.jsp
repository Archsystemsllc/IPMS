<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
	
<link href="${pageContext.request.contextPath}/resources/fullcalendar/fullcalendar.min.css" rel="stylesheet" type="text/css" media="screen" />
<link href="${pageContext.request.contextPath}/resources/fullcalendar/fullcalendar.print.min.css" rel="stylesheet" type="text/css" media="print" />
	
<script src="${pageContext.request.contextPath}/resources/fullcalendar/lib/moment.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/fullcalendar/lib/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/fullcalendar/fullcalendar.min.js"></script>

<script src="https://www.google.com/jsapi"></script>
<!-- <script src= "http://code.jquery.com/jquery-1.8.3.js"></script>
<script src="http://code.jquery.com/ui/1.9.2/jquery-ui.js"></script>
 -->
<script type="text/javascript">
	$(document).ready(function() {

		$('#dashboard ul li a').append('<span></span>');

		$('#dashboard ul li a').hover(function() {
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
		
		$('#calendar').fullCalendar({
			header: {
				left: 'prev,next today',
				right: 'title'
			},
			defaultDate: '2016-12-12',
			navLinks: true, // can click day/week names to navigate views
			editable: true,
			eventLimit: true, // allow "more" link when too many events
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
					title: 'Click for Google',
					url: 'http://google.com/',
					start: '2016-12-28'
				}
			]
		});
	}); 
</script>

<script type='text/javascript' src='https://www.google.com/jsapi'></script>
<script type='text/javascript'>
	google.load("visualization", "1.0", {
		packages : [ "corechart" ]
	});
	google.setOnLoadCallback(drawChart);
	function drawChart() {
		var options = {
			cht : 'lc',
			chds : '0,160'
		};
		<c:forEach var='item' items='${projectEvmMap}'>
		<c:if test="${item.key == 10035 || item.key == 10039}">
		var dataTable = new google.visualization.DataTable();
		dataTable.addColumn('string', 'Date');
		dataTable.addColumn('number', 'PV');
		dataTable.addColumn('number', 'CV');
		dataTable.addColumn('number', 'EV');
		<c:forEach var='evm' items='${item.value}'>

		var date = '<fmt:formatDate type="date" dateStyle="short" value="${evm.date}"/>';
		var pv = <fmt:parseNumber type="number" value="${evm.bcws}"/>;
		var ac = <fmt:parseNumber type="number" value="${evm.acwp}"/>;
		var ev = <fmt:parseNumber type="number" value="${evm.bcwp}"/>;
		dataTable.addRow([ date, pv, ac, ev ]);
		</c:forEach>
		var id = 'chart_div_' + '<c:out value="${item.key}"/>';
		var chart = new google.visualization.LineChart(document
				.getElementById(id));
		chart.draw(dataTable, options);
		</c:if>
		</c:forEach>

	}
</script>
<style>
  #calendar {
		max-width: 900px;
		margin: 0 auto;
	}
</style>
<div id="content">
	<div class="grid_container">
		<div class="grid_12 full_block">
				<div class="grid_12" id="programDId">
					<div class="widget_wrap">
						<div class="widget_top">
							<span class="h_icon documents"></span>
							<h6>Programs</h6>
						</div>
						<div class="widget_content">
							<c:if test="${not empty success}">
								<div class="successblock">
									<spring:message code="${success}"></spring:message>
								</div>
							</c:if>
							<table class="display data_tbl" id="dashboard">
								<thead>
									<tr>
										<th class="center" title="Program Id">ID</th>
										<th title="Program Names with project dropdown">Program
											Name</th>
										<th title="User Assigned to the Program">Manager</th>
										<th title="No of Issues"># of Issues</th>
										<th title="Program Start Date">Start Date</th>
										<th title="Program End Date">End Date</th>
										<th title="Edit or Create Program">Action</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="program" items="${programs}">
										<c:if test="${not program.active }">
											<c:set var="linkcolor" value="activeobjectlink" />
										</c:if>
										<c:if test="${program.active }">
											<c:set var="linkcolor" value="" />
										</c:if>
										<tr>
											<td><a class="${linkcolor }"><c:out
														value='${program.id}' /></a></td>
											<td>

												<div class="grid_12">
													<div class="collapsible_widget">
														<div class="c_widget_top inactive">
															<span class="c_h_icon"></span> <a class="${linkcolor }"
																href="${pageContext.request.contextPath}/app/program/${program.id}">
																<h6>
																	<c:out value='${program.name}' />
																</h6>
															</a>
														</div>
														<div class="widget_content c_content">
															<c:forEach var="project" items="${program.projects}">
																<a
																	href="${pageContext.request.contextPath}/app/project/<c:out value='${project.id }' />">
																	<span class="c_inner_content"> <c:out
																			value='${project.name }' />
																</span> <span class="clear"></span>
																</a>
															</c:forEach>
														</div>
													</div>
												</div>

											</td>
											<td><a class="${linkcolor }"><c:out
														value='${program.manager.name}' /></a></td>
											<td><a class="${linkcolor }"><c:out
														value='${fn:length(program.issues)}' /></a></td>
											<td><a class="${linkcolor }"><fmt:formatDate
														type="date" value="${program.startDate}" /></a></td>
											<td><a class="${linkcolor }"><fmt:formatDate
														type="date" value="${program.endDate}" /></a></td>
											<td>
											<c:if test="${program.active }">
											<span><a class="action-icons c-edit"
													href="${pageContext.request.contextPath}/app/edit-program/${program.id}"
													title="Edit">Edit</a></span><span><a
													class="action-icons c-approve"
													href="${pageContext.request.contextPath}/app/new-program"
													title="Create">Create</a></span></c:if>
											</td>
											
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
					</div>
				</div>
				<span class="clear"></span>
			</div>


		<sec:authorize
			access="hasRole('ROLE_PROJECT_MANAGER') or hasRole('AdminOfSecurityService') or hasRole('ROLE_USER')">
			<div class="grid_12">
				<div class="widget_wrap">
					<div class="widget_top">
						<span class="h_icon documents"></span>
						<h6>Projects Snapshot</h6>
					</div>
					<div class="widget_content">
						<table class="display data_tbl" id="dashboard">
							<thead>
								<tr>
									<th title="Project Name">Project</th>
									<th title="Program Assigned to Project">Program</th>
									<th title="No of Issues">Issues</th>
									<th title="No of Action Items">Action Items</th>
									<th title="No of Risks">Risks</th>
									<th>Cost Variance</th>
									<th>Schedule Variance</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="project" items="${projects}">
									<c:if test="${not project.active }">
										<c:set var="linkcolor" value="activeobjectlink" />
									</c:if>
									<tr>
										<td><a class="${linkcolor }"
											href="${pageContext.request.contextPath}/app/project/${project.id}">
												<c:out value="${project.name }" />
										</a></td>
										<td><a class="${linkcolor }"><c:out
													value="${project.program.name}" /></a></td>
										<td><a class="${linkcolor }"><c:out
													value="${fn:length(project.issues) }" /></a></td>
										<td><a class="${linkcolor }"><c:out
													value="${fn:length(project.actionItems) }" /></a></td>
										<td><a class="${linkcolor }"><c:out
													value="${fn:length(project.risks) }" /></a></td>
										<td><c:forEach var="evmMap" items="${projectEvmMap}">

												<c:if test="${evmMap.key == project.id}">
													<c:forEach var="projectevm" items="${evmMap.value}"
														varStatus="loop">
														<c:if test="${loop.last}">
															<c:if
																test="${projectevm.cvPercent >= 0 && projectevm.cvPercent <= 4}">
																<img
																	src="${pageContext.request.contextPath}/resources/images/green_ball.gif"
																	alt="Green" />
															</c:if>
															<c:if
																test="${projectevm.cvPercent > 4 && projectevm.cvPercent <= 10}">
																<img
																	src="${pageContext.request.contextPath}/resources/images/yellow_ball.gif"
																	alt="Yellow" />
															</c:if>
															<c:if
																test="${projectevm.cvPercent > 10 || projectevm.cvPercent < 0}">
																<img
																	src="${pageContext.request.contextPath}/resources/images/red_ball.gif"
																	alt="Red" />
															</c:if>
														</c:if>
													</c:forEach>
												</c:if>
											</c:forEach></td>
										<td><c:forEach var="evmMap" items="${projectEvmMap}">

												<c:if test="${evmMap.key == project.id}">
													<c:forEach var="projectevm" items="${evmMap.value}"
														varStatus="loop">
														<c:if test="${loop.last}">
															<c:if
																test="${projectevm.svPercent >= 0 && projectevm.svPercent <= 4}">
																<img
																	src="${pageContext.request.contextPath}/resources/images/green_ball.gif"
																	alt="Green" />
															</c:if>
															<c:if
																test="${projectevm.svPercent > 4 && projectevm.svPercent <= 10}">
																<img
																	src="${pageContext.request.contextPath}/resources/images/yellow_ball.gif"
																	alt="Yellow" />
															</c:if>
															<c:if
																test="${projectevm.svPercent > 10 || projectevm.svPercent < 0}">
																<img
																	src="${pageContext.request.contextPath}/resources/images/red_ball.gif"
																	alt="Red" />
															</c:if>
														</c:if>
													</c:forEach>
												</c:if>
											</c:forEach></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
			</div>
			<span class="clear"></span>
		</sec:authorize>

		<sec:authorize
			access="hasRole('ROLE_PROJECT_MANAGER') or hasRole('AdminOfSecurityService')">
			<c:forEach var='item' items='${projectEvmMap}'>
				<c:if test="${item.key == 10035 || item.key == 10039}">
					<div class="grid_6">
						<div class="widget_wrap">
							<div class="widget_top">
								<span class="h_icon list"></span>
								<c:forEach var='project' items='${projects}'>
									<c:if test="${item.key == project.id}">
										<h6>EVM for ${project.name}</h6>
									</c:if>
								</c:forEach>
							</div>
							<div class="widget_content">
								<div id="chart_div_${item.key}" style="height: 250px;"></div>
							</div>
						</div>
					</div>
				</c:if>
			</c:forEach>
		</sec:authorize>



		<div class="grid_6">
			<div class="widget_wrap">
				<div class="widget_top">
					<span class="h_icon list_images"></span>
					<h6>Task List</h6>
				</div>
				<div class="widget_content">
					<table class="display" id="action_tbl" id="dashboard">
						<thead>
							<tr>
								<th title="Task Names">Task</th>
								<th title="Name of the User">Created By</th>
								<th title="Task Start Date">Date Created</th>
								<th title="Task Status">Status</th>
								<th title="Task Due Date">Date Due</th>
								<th title="Edit or Create Task">Action</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="task" items="${tasks}">
								<tr>


									<td><a href="task/${task.id}"><c:out
												value="${task.name }" /></a></td>
									<td><c:out value="${task.createdBy.name }" /></td>
									<td><fmt:formatDate type="both" dateStyle="short"
											timeStyle="short" value="${task.dateCreated }" /></td>
									<td><span
										class="badge_style b_${fn:toLowerCase(task.status)}">${task.status
											}</span></td>
									<td><fmt:formatDate type="both" dateStyle="short"
											timeStyle="short" value="${task.dueDate }" /></td>
									<td><span><a class="action-icons c-edit"
											href="${pageContext.request.contextPath}/app/edit-task/${task.id}"
											title="Edit">Edit</a></span><span><a
											class="action-icons c-approve"
											href="${pageContext.request.contextPath}/app/new-task"
											title="Create">Create</a></span></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>


		<div class="grid_6">
			<div class="widget_wrap">
				<div class="widget_top">
					<span class="h_icon list_images"></span>
					<h6>Action Items List</h6>
				</div>
				<div class="widget_content">
					<c:if test="${not empty success}">
						<%-- <div class="successblock"><spring:message code="${success}"></spring:message>
               </div> --%>
					</c:if>
					<table class="display data_tbl" id="dashboard">
						<thead>
							<tr>
								<th title="Action Item Summary">Summary</th>
								<th title="Action Item Id">ID</th>
								<th title="User Assigned with Action Item">Assigned To</th>
								<th title="Action Item Due Date">Date Due</th>
								<th title="Action Item Status">Status</th>
								<th title="Create or Edit Action Item">Action</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="actionitem" items="${actionItems}">
								<tr>
									<td><a
										href="${pageContext.request.contextPath}/app/actionitem/${actionitem.id}"><c:out
												value="${actionitem.summary}" /></a></td>
									<td><c:out value="${actionitem.id}" /></td>
									<td><c:out value="${actionitem.assignedTo.name}" /></td>
									<td><fmt:formatDate type="date"
											value="${actionitem.dueDate}" /></td>
									<td><span
										class="badge_style b_${fn:toLowerCase(actionitem.status)}">${actionitem.status
											}</span></td>
									<td><span><a class="action-icons c-edit"
											href="${pageContext.request.contextPath}/app/edit-actionitem/${actionitem.id}"
											title="Edit">Edit</a></span><span><a
											class="action-icons c-approve"
											href="${pageContext.request.contextPath}/app/new-actionitem"
											title="Create">Create</a></span></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>
		<span class="clear"></span>


		<div class="grid_6">
			<div class="widget_wrap tabby">
				<div class="widget_top">
					<span class="h_icon bended_arrow_right"></span>
					<h6>User Activity</h6>
				</div>
				<div class="widget_content">
					<div id="tab1">
						<div class="user_list">
							<!--<c:forEach var='event' items='${projectEvents}'>
								<div class="user_block">
									<div class="info_block">
										<div class="widget_thumb">
											<img
												src="${pageContext.request.contextPath}/resources/images/user-thumb1.png"
												width="40" height="40" alt="User">
										</div>
										<ul class="list_info">
											<li><span>Date: <i><c:out value="${event.dateCreated}" /></i></span></li>
											<li><span>Project: <c:out value="'${event.entityName}' was ${event.activityType}D" /></span></li>
											<li><span>By:  <i><c:out value="${event.user}" /></i></span></li>
										</ul>
									</div>
								</div>
							</c:forEach>-->
							<c:forEach var='artifact' items='${artifactEvents}'>
								<div class="user_block">
									<div class="info_block">
										<div class="widget_thumb">
											<img
												src="${pageContext.request.contextPath}/resources/images/user-thumb1.png"
												width="40" height="40" alt="User">
										</div>
										<ul class="list_info">
											<li><span>Date: <i><fmt:formatDate type="both" dateStyle="long" timeStyle="long" value="${artifact.dateCreated}" /></i></span></li>
											<li><span>Activity: <c:out value="${artifact.entityType}" /> <b><c:out value="${artifact.entityName} " /></b><c:out value=" was " /> <i><c:out value="${artifact.activityType}D" /></i></span></li>
											<li><span>By:  <i><c:out value="${artifact.user}" /></i></span></li>
										</ul>
									</div>
								</div> 
							</c:forEach>
						</div>
					</div>
				</div>
			</div>
		</div>


		<div id="content">
			<div class="grid_container">
				<div class="grid_6">
					<div class="widget_wrap">
						<div class="widget_top">
							<span class="h_icon list"></span>
							<h6>My Meetings</h6>
						</div>
						<div class="widget_content">
							<div id='calendar'></div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

