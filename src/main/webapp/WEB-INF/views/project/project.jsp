<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<script type='text/javascript' src='https://www.google.com/jsapi'></script>
<script type='text/javascript'>
	<script src= "http://code.jquery.com/jquery-1.8.3.js">
</script>
<script src="http://code.jquery.com/ui/1.9.2/jquery-ui.js"></script>

<script type="text/javascript">
	$('document').ready(function() {
		$("#table1234 th[title]").hover(function() {
			showTooltip($(this));
		}, function() {
			hideTooltip();
		});

		function showTooltip($el) {
			$tip.html($el.attr('title'));
		}
		function hideTooltip() {
			$tip.hide();
		}

	});

	google.load("visualization", "1.0", {
		packages : [ "corechart" ]
	});
	google.setOnLoadCallback(drawChart);
	function drawChart() {
		var dataTable = new google.visualization.DataTable();
		dataTable.addColumn('string', 'Date');
		dataTable.addColumn('number', 'PV');
		dataTable.addColumn('number', 'CV');
		dataTable.addColumn('number', 'EV');

		//        dataTable.addRow(['jan', 21500, 19000]);
		//         dataTable.addRow(['feb', 10000,24000]);
		//         dataTable.addRow(['mar', 157, 40]);
		//         dataTable.addRow(['apr', 90, 20]);
		//         dataTable.addRow(['apr', 90, 20]);
<%--alert('<c:out value="${fn:length(projectEvm)}"/>');--%>
	<c:forEach var='evm' items='${projectEvm}'>
		var date = '<fmt:formatDate type="date" dateStyle="short" value="${evm.date}"/>';
<%--var d =  <fmt:parseDate type="date" dateStyle="short" value="${evm.date}"/>;--%>
	var pv = <fmt:parseNumber type="number" value="${evm.bcws}"/>;
		var ac = <fmt:parseNumber type="number" value="${evm.acwp}"/>;
		var ev = <fmt:parseNumber type="number" value="${evm.bcwp}"/>;
		dataTable.addRow([ date, pv, ac, ev ]);
		</c:forEach>

		var options = {
			cht : 'lc',
			chds : '0,160'
		};
		var chart = new google.visualization.LineChart(document
				.getElementById('chart_div'));
		chart.draw(dataTable, options);
	}
</script>
<div id="breadcrumb">
	<ul>
		<li><a href="${pageContext.request.contextPath}/app/groupdashboard">Home</a>
			<span> >> </span></li>
		<li><a href="${pageContext.request.contextPath}/app/projects">Projects</a>
			<span> >> </span></li>
		<li><a href="#" style="text-decoration: none;">${project.name }</a></li>
	</ul>
</div>
<div id="content">
	<div class="grid_container">
		<div class="grid_6">
			<div class="widget_wrap">
				<div class="widget_top">
					<span class="h_icon list_images"></span>
					<h6>Project Info</h6>
					<div class="c_actions">
						<ul>
							<li><a
								href="${pageContext.request.contextPath}/resources/mpxj/services_schedule.mpp"
								title="Download as MS Project"> <img style="width: 20px; height: 20px; margin-bottom:4px"
									src="${pageContext.request.contextPath}/resources/images/table-tools/Microsoft_Project_icon.png"
									alt="Download as MS Project">
							</a></li>
						</ul>
					</div>
				</div>
				<div class="widget_content">
					<c:if test="${not empty success}">
						<div class="successblock">
							<spring:message code="${success}"></spring:message>
						</div>
					</c:if>
					<form action="../edit-project/${project.id}"
						class="form_container left_label">

						<ul>
							<li>
								<div class="form_grid_12">
									<label class="field_title">ID</label>
									<div class="form_input">
										<span class="uneditable-input ">${project.id }</span>
									</div>
								</div>
							</li>
							<li>
								<div class="form_grid_12">
									<label class="field_title">Name</label>
									<div class="form_input">
										<span class="uneditable-input">${project.name }</span>
									</div>
								</div>
							</li>
							<li>
								<div class="form_grid_12">
									<label class="field_title">Description</label>
									<div class="form_input">
										<span class="uneditable-input">${project.description
												}</span>
									</div>
								</div>
							</li>
							
							
							<li>
								<div class="form_grid_12">
									<label class="field_title">JIRA Project Name</label>
									<div class="form_input">
										<span class="uneditable-input">${project.jiraProjectName }</span>
									</div>
								</div>
							</li>
							
                              <li>
                                 <div class="form_grid_12">
                                     <label for="name" class="field_title">Meeting EMail</label>
                                     <div class="form_input">
										<span class="uneditable-input">${project.email }</span>
                                       </div>
                                 </div>
                             </li>
							<li>
								<div class="form_grid_12">
									<label class="field_title">Start Date</label>
									<div class="form_input">
										<span class="uneditable-input"><fmt:formatDate
												type="date" value="${project.startDate}" /></span>
									</div>
								</div>
							</li>
							<li>
								<div class="form_grid_12">
									<label class="field_title">Due Date</label>
									<div class="form_input">
										<span class="uneditable-input"><fmt:formatDate
												type="date" value="${project.endDate}" /></span>
									</div>
								</div>
							</li>
							
							<li>
								<div class="form_grid_12">
									<label class="field_title">Status</label>
									<div class="form_input">
										<c:if test="${project.active }">
											<span class="uneditable-input">${project.active}</span>
										</c:if>
									</div>
								</div>
							</li>
						</ul>
						<ul>
							<li>
								<div class="form_grid_12">
									<a href="../edit-project/${project.id}"><button
											class="btn_small btn_blue">Edit Model</button></a>
								</div>
							</li>

						</ul>

					</form>
				</div>
			</div>
		</div>
		<div class="grid_6">
			<div class="widget_wrap">
				<div class="widget_top">
					<span class="h_icon list_images"></span>
					<h6>Project Burn Rate</h6>
				</div>
				<div class="widget_content">
					<div id="chart_div" style="height: 400px;"></div>
				</div>
			</div>
		</div>
		<span class="clear"></span>
		<div class="grid_12">
			<div class="widget_wrap">
				<div class="widget_top">
					<span class="h_icon list_images"></span>
					<h6>EVM</h6>
				</div>
				<div class="widget_content">
					<table class="display data_tbl" id="table1234">
						<thead>
							<tr>
								<th>Date</th>
								<th
									title="CPI = Budgeted Cost of work Performed/Actual Cost of Work Performed">CPI</th>
								<th
									title="SPI = Budgeted Cost of work Performed/Budgeted cost of work scheduled">SPI</th>
								<th title="BCWS = BCWP-SPI">BCWS</th>
								<th title="BCWP = BCWS+SPI">BCWP</th>
								<th title="ACWP = BCWS-CV">ACWP</th>
								<th title="SV = BCWP-BCWS">SV</th>
								<th title="CV = BCWP-ACWP">CV</th>
								<!--  <th title="EAC = BAC-VAC">EAC</th>
								<th title="VAC =BAC-EAC ">VAC</th>-->

							</tr>
						</thead>

						<tbody>

							<tr>
								<td><fmt:formatDate type="date" value="${currentEvm.date }" /></td>
								<td><c:out value="${currentEvm.cpi}" /></td>
								<td><c:out value="${currentEvm.spi}" /></td>
								<td><c:out value="${currentEvm.bcws}" /></td>
								<td><c:out value="${currentEvm.bcwp}" /></td>
								<td><c:out value="${currentEvm.acwp}" /></td>
								<td><c:out value="${currentEvm.sv}" /></td>
								<td><c:out value="${currentEvm.cv}" /></td>
								<!--<td><c:out value="${currentEvm.eac}" /></td>
								<td><c:out value="${currentEvm.vac}" /></td> -->

							</tr>

						</tbody>
						<tfoot>
							<tr style="height: 1px">

								<td colspan="10"><a class="activeobjectlink"></a></td>

							</tr>
						</tfoot>
					</table>
				</div>
			</div>
		</div>
		<span class="clear"></span>
		<div class="grid_12">
			<div class="widget_wrap">
				<div class="widget_top">
					<span class="h_icon list_images"></span>
					<h6>Task List</h6>
				</div>
				<div class="widget_content">
					<table class="display data_tbl">
						<thead>
							<tr>
								<th>ID</th>
								<th>Summary</th>								
								<th>Reported By</th>
								<th>Status</th>								
								<th>Updated</th>
								<th>Due Date</th>
							</tr>
						</thead>

						<tbody>
							<c:forEach var="task" items="${project.tasks}">
								<tr>
									<td>${task.id }</td>
									<td><a href="../task/${task.id }">${task.description
															}</a></td>									
									<td>${task.assignedTo.name }</td>
									<td><span
										class="badge_style b_${fn:toLowerCase(task.status)}">${task.status
															}</span></td>
									<td><fmt:formatDate type="date" value='${task.dueDate }' /></td>
									<td><fmt:formatDate type="date" value='${task.dueDate }' /></td>
								</tr>
							</c:forEach>
						</tbody>
						<tfoot>
							<tr style="height: 1px">
								<c:if test="${project.active }">
									<td colspan="6"><a
										href="${pageContext.request.contextPath}/app/new-projecttask/${project.id}"><button
												class="btn_small btn_blue">Add Task</button></a></td>
								</c:if>
							</tr>
						</tfoot>
					</table>
				</div>
			</div>
		</div>
		
		<span class="clear"></span>
		<div class="grid_9">
			<div class="widget_wrap">
				<div class="widget_top">
					<span class="h_icon list_images"></span>
					<h6>Issues</h6>
				</div>
				<div class="widget_content">
					<table class="display data_tbl">
						<thead>
							<tr>
								<th>Summary</th>
								<th>Assignee</th>
								<th>Reported By</th>
								<th>Status</th>
								<th>Priority</th>
								<th>Resolution</th>
								<th>Due Date</th>
							</tr>
						</thead>

						<tbody>
							<c:forEach var="issue" items="${project.issues}">
								<tr>
									<td><a href="../issue/${issue.id }">${issue.summary
															}</a></td>
									<td>${issue.assignee.name }</td>
									<td>${issue.assigned.name }</td>
									<td><span
										class="badge_style b_${fn:toLowerCase(issue.status)}">${issue.status
															}</span></td>
									<td><span
										class="badge_style b_${fn:toLowerCase(issue.priority)}">${issue.priority
															}</span></td>
									<td>${issue.summary }</td>

									<td><fmt:formatDate type="date" value='${issue.dueDate }' /></td>
								</tr>
							</c:forEach>
						</tbody>
						<tfoot>
							<tr style="height: 1px">
								<c:if test="${project.active }">
									<td colspan="7"><a
										href="${pageContext.request.contextPath}/app/new-issue/${project.id}"><button
												class="btn_small btn_blue">Add Issue</button></a></td>
								</c:if>
							</tr>
						</tfoot>
					</table>
				</div>
			</div>
		</div>
		<div class="grid_3">
			<div class="widget_wrap">
				<div class="widget_top">
					<span class="h_icon list_images"></span>
					<h6>Meetings</h6>
				</div>
				<div class="widget_content">

					<c:forEach var="meeting" items="${project.meetings}">
						<table class="display data_tbl">
							<tr>
								<td><b>MeetingID</b></td>
								<td>${meeting.id }</td>
							</tr>
							<tr>
								<td><b>Title</b></td>
								<td><a
									href="${pageContext.request.contextPath}/app/meeting/${meeting.id}">${meeting.title
															}</a></td>
							</tr>
							<tr>
								<td><b>Date</b></td>
								<td>${meeting.date }</td>
							</tr>
							<tr>
								<td><b>Organizer</b></td>
								<td>${meeting.organizer.name }</td>
							</tr>
							<tr>
								<td><b>Duration</b></td>
								<td>${meeting.duration }</td>
							</tr>
						</table>
					</c:forEach>
					<br />
					<div>
						<c:if test="${project.active }">
							<a
								href="${pageContext.request.contextPath}/app/new-projectmeeting/${project.id}"><button
									class="btn_small btn_blue">Schedule Meeting</button></a>
						</c:if>
					</div>

				</div>
			</div>
			<span class="clear"></span>
		</div>
		<span class="clear"></span>
		<div class="grid_12">
			<div class="widget_wrap">
				<div class="widget_top">
					<span class="h_icon list_images"></span>
					<h6>Risks</h6>
				</div>
				<div class="widget_content">
					<table class="display data_tbl">
						<thead>
							<tr>
								<th>ID</th>
								<th>Description</th>
								<th>Mitigation Plan</th>
								<th>Risk/Cost</th>
								<th>Risk - Accept/transfer/Avoid</th>
								<th>Manage></th>
							</tr>
						</thead>

						<tbody>
							<c:forEach var="risk" items="${project.risks}">
								<tr>
									<td>${risk.id }</td>
									<td><a href="../risk/${risk.id }">${risk.riskSummary}</a></td>
									<td>${risk.mitigatingFactors}</td>
									<td>${risk.likelihood}</td>
									<td>${risk.impact}</td>
									<c:if test="${project.active }">
										<td style="white-space: nowrap;"><span><a
												class="action-icons c-edit"
												href="${pageContext.request.contextPath}/app/edit-risk/${risk.id}"
												title="Edit">Edit</a></span><span><a
												class="action-icons c-approve"
												href="${pageContext.request.contextPath}/app/new-risk"
												title="Create">Create</a></span></td>
									</c:if>
									<c:if test="${project.active }">
										<td style="white-space: nowrap;"></td>
									</c:if>
								</tr>
							</c:forEach>
						</tbody>
						<tfoot>
							<tr>
								<c:if test="${project.active }">
									<td colspan="6"><a
										href="${pageContext.request.contextPath}/app/new-projectrisk/${project.id}">
											<button class="btn_small btn_blue">Add Risks</button>
									</a></td>
								</c:if>
							</tr>
						</tfoot>
					</table>
				</div>
			</div>
		</div>
		<div class="grid_12">
			<div class="widget_wrap">
				<div class="widget_top">
					<span class="h_icon list_images"></span>
					<h6>Lessons Learned</h6>
				</div>
				<div class="widget_content">
					<table class="display data_tbl">
						<thead>
							<tr>
								<th>ID</th>
								<th>Summary</th>
								<th>Impact</th>
								<th>Strategy for Improvement</th>
								<th>Recommendation</th>
								<th>Manage></th>
							</tr>
						</thead>

						<tbody>
							<c:forEach var="lessonsLearned" items="${project.lessonsLearned}">
								<tr>
									<td>${lessonsLearned.id }</td>
									<td><a href="lessonslearned">${lessonsLearned.summary
															}</a></td>
									<td>${lessonsLearned.impact }</td>
									<td>${lessonsLearned.successFactors }</td>
									<td>${lessonsLearned.recommendation }</td>
									<c:if test="${project.active }">
										<td style="white-space: nowrap;"><span><a
												class="action-icons c-edit"
												href="${pageContext.request.contextPath}/app/edit-lessonslearned/${lessonsLearned.id}"
												title="Edit">Edit</a></span><span><a
												class="action-icons c-approve"
												href="${pageContext.request.contextPath}/app/new-lessonslearned"
												title="Create">Create</a></span></td>
									</c:if>
									<c:if test="${project.active }">
										<td style="white-space: nowrap;"></td>
									</c:if>
								</tr>
							</c:forEach>
						</tbody>
						<tfoot>
							<tr>
								<c:if test="${project.active }">
									<td colspan="6"><a
										href="${pageContext.request.contextPath}/app/new-lessonslearned">
											<button class="btn_small btn_blue">Add
												LessonsLearned</button>
									</a></td>
								</c:if>
							</tr>
						</tfoot>
					</table>
				</div>
			</div>
		</div>
		<span class="clear"></span>
		<div class="grid_6">
			<div class="widget_wrap">
				<div class="widget_top">
					<span class="h_icon list_images"></span>
					<h6>Meeting Action Items</h6>
				</div>
				<div class="widget_content">
					<table class="display data_tbl">
						<thead>
							<tr>
								<th>ID</th>
								<th>Summary</th>
								<th>Assined To</th>
								<th>Due Date</th>
								<th>Status</th>
								<th>Priority</th>
								<th>Manage></th>
							</tr>
						</thead>

						<tbody>
							<c:forEach var="actionitem" items="${project.actionItems}">
								<tr>
									<td>${actionitem.id }</td>
									<td><a href="../actionitem/${actionitem.id }">${actionitem.summary
															}</a></td>
									<td>${actionitem.assignedTo.name }</td>
									<td><fmt:formatDate type="date"
											value="${actionitem.dueDate}" /></td>
									<td><span
										class="badge_style b_${fn:toLowerCase(actionitem.status)}">${actionitem.status
															}</span></td>
									<td><span
										class="badge_style b_${fn:toLowerCase(actionitem.priority)}">${actionitem.priority
															}</span></td>
									<c:if test="${project.active }">
										<td style="white-space: nowrap;"><span><a
												class="action-icons c-edit"
												href="${pageContext.request.contextPath}/app/edit-actionitem/${actionItem.id}"
												title="Edit">Edit</a></span><span><a
												class="action-icons c-approve"
												href="${pageContext.request.contextPath}/app/new-actionitem"
												title="Create">Create</a></span></td>
									</c:if>
									<c:if test="${project.active }">
										<td style="white-space: nowrap;"></td>
									</c:if>
								</tr>
							</c:forEach>
						</tbody>
						<tfoot>
							<tr style="height: 1px">
								<c:if test="${not project.active }">
									<td colspan="5"><a class="activeobjectlink"></a></td>
								</c:if>
								<c:if test="${project.active }">
									<td colspan="7"><a
										href="${pageContext.request.contextPath}/app/new-actionitem/${actionitem.id}">
											<button class="btn_small btn_blue">Add Action Items</button>
									</a></td>
								</c:if>
							</tr>
						</tfoot>
					</table>
				</div>
			</div>
		</div>
		<div class="grid_6">
			
		</div>
		<span class="clear"></span>
	</div>
</div>
