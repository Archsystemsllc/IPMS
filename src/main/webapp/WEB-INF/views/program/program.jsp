<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<div id="breadcrumb">
	<ul>
		<li><a href="${pageContext.request.contextPath}/app/dashboard">Home</a>
			<span> >> </span></li>
		<li><a href="${pageContext.request.contextPath}/app/Models">Vertical Groups</a>
			<span> >> </span></li>
		<li><a href="#" style="text-decoration: none;">Vertical Group</a></li>
	</ul>
</div>
<div id="content">
	<div class="grid_container">
		<div class="grid_6">
			<div class="widget_wrap">
				<div class="widget_top">
					<span class="h_icon list_images"></span>
					<h6>Vertical Group Info</h6>
				</div>
				<div class="widget_content">
					<c:if test="${not empty success}">
						<div class="successblock">
							<spring:message code="${success}"></spring:message>
						</div>
					</c:if>
					<form
						action="${pageContext.request.contextPath}/app/edit-Model/${Model.id}"
						class="form_container left_label">
						<span class="subheader-title">Vertical Group: ${Model.name }</span>

						<ul>
							<li>
								<div class="form_grid_12">
									<label class="field_title">ID</label>
									<div class="form_input">
										<span class="uneditable-input">${Model.id }</span>
									</div>
								</div>
							</li>
							<li>
								<div class="form_grid_12">
									<label class="field_title">Name</label>
									<div class="form_input">
										<span class="uneditable-input">${Model.name }</span>
									</div>
								</div>
							</li>
							<li>
								<div class="form_grid_12">
									<label class="field_title">Description</label>
									<div class="form_input">
										<span class="uneditable-input">${Model.description }</span>
									</div>
								</div>
							</li>
							<li>
								<div class="form_grid_12">
									<label class="field_title">Start Date</label>
									<div class="form_input">
										<span class="uneditable-input"><fmt:formatDate
												type="date" value="${Model.startDate}" /></span>
									</div>
								</div>
							</li>
							<li>
								<div class="form_grid_12">
									<label class="field_title">Due Date</label>
									<div class="form_input">
										<span class="uneditable-input"><fmt:formatDate
												type="date" value="${Model.endDate}" /></span>
									</div>
								</div>
							</li>
							<%-- <li>
                                <div class="form_grid_6">
                                    <label class="field_title">Manager</label>
                                    <div class="form_input">
                                        <span class="uneditable-input mid">${Model.managerId}</span>
                                    </div>
                                </div>
                            </li> --%>
							<li>
								<div class="form_grid_12">
									<label class="field_title">Status</label>
									<div class="form_input">
										<c:if test="${Model.active }">
											<span class="uneditable-input">Active</span>
										</c:if>
										<c:if test="${not Model.active }">
											<span class="uneditable-input">In-Active</span>
										</c:if>
									</div>
								</div>
							</li>
							<li>
								<div class="form_grid_12">
									<label class="field_title">Group</label>
									<div class="form_input">
										<span class="uneditable-input">${Model.organizationGroup.name }</span>
									</div>
								</div>
							</li>
						</ul>
						<ul>
							<li>
								<div class="form_grid_12">
									<a href="../edit-Model/${Model.id}"><button
											class="btn_small btn_blue">Edit Group</button></a>
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
					<h6>Meeting</h6>
				</div>
				<table class="display data_tbl">
					<thead>
						<tr>
							<th>ID</th>
							<th>Title</th>
							<th>Date</th>
							<th>Organizer</th>
							<th>Duration</th>
						</tr>
					</thead>

					<tbody>
						<c:forEach var="meeting" items="${Model.meetings}">
							<tr>
								<td>${meeting.id }</td>
								<td><a
									href="${pageContext.request.contextPath}/app/meeting/${meeting.id}">${meeting.title
															}</a></td>
								<td>${meeting.date }</td>
								<td>${meeting.organizer.name }</td>
								<td>${meeting.duration }</td>
							</tr>
						</c:forEach>
					</tbody>
					<tfoot>
						<tr>
							<c:if test="${Model.active }">
								<td colspan="5"><a
									href="${pageContext.request.contextPath}/app/new-Modelmeeting/${Model.id}"><button
											class="btn_small btn_blue">Schedule Meeting</button></a></td>
							</c:if>
						</tr>
					</tfoot>
				</table>
			</div>
		</div>
		<span class="clear"></span>
		<div class="grid_12">
			<div class="widget_wrap">
				<div class="widget_top">
					<span class="h_icon list_images"></span>
					<h6>Meeting</h6>
				</div>
				<table class="display data_tbl">
					<thead>
						<tr>
							<th>ID</th>
							<th>Summary</th>
							<th>Assignee</th>
							<th>Reported By</th>
							<th>Status</th>
							<th>Priority</th>
							<th>Resolution</th>
							<th>Created</th>
							<th>Updated</th>
							<th>Due Date</th>
						</tr>
					</thead>

					<tbody>
						<c:forEach var="issue" items="${Model.issues}">
							<tr>
								<td>${issue.id }</td>
								<td><a href="../issue/${issue.id }">${issue.summary }</a></td>
								<td>${issue.assignee.name }</td>
								<td>${issue.assigned.name }</td>
								<td><span
									class="badge_style b_${fn:toLowerCase(issue.status)}">${issue.status }</span></td>
								<td><span
									class="badge_style b_${fn:toLowerCase(issue.priority)}">${issue.priority }</span></td>
								<td><c:out value='${issue.summary }' /></td>
								<td><fmt:formatDate type="date"
										value='${issue.dateReported }' /></td>
								<td><fmt:formatDate type="date" value='${issue.dueDate }' /></td>
								<td><fmt:formatDate type="date" value='${issue.dueDate }' /></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
		<span class="clear"></span>
		<div class="grid_12">
			<div class="widget_wrap">
				<div class="widget_top">
					<span class="h_icon list_images"></span>
					<h6>Risks</h6>
				</div>
				<table class="display data_tbl">
					<thead>
						<tr>
							<th>ID</th>
							<th>Description</th>
							<th>Mitigation Plan</th>
							<th>Risk/Cost</th>
							<th>Risk - Accept/transfer/Avoid</th>
							<th>Actions</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="risk" items="${Model.risks}">
							<tr>
								<td>${risk.id }</td>
								<td>${risk.riskSummary}</a></td>
								<td>${risk.mitigatingFactors}</td>
								<td>${risk.likelihood}</td>
								<td>${risk.impact}</td>
								<c:if test="${not Model.active }">
									<td></td>
								</c:if>
								<c:if test="${Model.active }">
									<td style="white-space: nowrap;"><span><a
											class="action-icons c-edit"
											href="${pageContext.request.contextPath}/app/edit-risk/${risk.id}"
											title="Edit">Edit</a></span><span><a
											class="action-icons c-approve"
											href="${pageContext.request.contextPath}/app/new-risk"
											title="Create">Create</a></span></td>
								</c:if>

							</tr>
						</c:forEach>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="6"><a class="activeobjectlink"
								href="${pageContext.request.contextPath}/app/new-risk"><button
										class="btn_small btn_blue">Add Risk</button></a></td>
						</tr>
					</tfoot>
				</table>
			</div>
		</div>
		<span class="clear"></span>
		<div class="grid_6">
			<div class="widget_wrap">
				<div class="widget_top">
					<span class="h_icon list_images"></span>
					<h6>Projects</h6>
				</div>
				<table class="display data_tbl">
					<thead>
						<tr>
							<th class="center">ID</th>
							<th>Project Name</th>
							<th>Manager</th>
							<th>Start Date</th>
							<th>Issues</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="project" items="${Model.projects}">
							<tr>
								<td><c:out value='${project.id }' /></td>
								<td><a
									href="${pageContext.request.contextPath}/app/project/<c:out value='${project.id }' />">
										<c:out value='${project.name }' />
								</a></td>
								<td><c:out value='${project.manager.name }' /></td>
								<td><fmt:formatDate type="date"
										value='${project.startDate }' /></td>
								<td><c:out value='${fn:length(project.issues)}' /></td>
							</tr>
						</c:forEach>
					</tbody>
					<tfoot>
						<tr>
							<c:if test="${not Model.active }">
								<td colspan="12"><a class="activeobjectlink">Add
										Model</a></td>
							</c:if>
							<c:if test="${Model.active }">
								<td colspan="12"><a
									href="${pageContext.request.contextPath}/app/new-project/${Model.id}"><button
											class="btn_small btn_blue">Add Project</button></a></td>
							</c:if>
						</tr>
					</tfoot>
				</table>
			</div>
		</div>
		<div class="grid_6">
			<div class="widget_wrap">
				<div class="widget_top">
					<span class="h_icon list_images"></span>
					<h6>Action Items</h6>
				</div>
				<table class="display data_tbl">
					<thead>
						<tr>
							<th>ID</th>
							<th>Date Added</th>
							<th>Owner</th>
							<th>Status</th>
							<th>Weekly Status</th>
						</tr>
					</thead>

					<tbody>
						<c:forEach var="actionitem" items="${Model.actionItems}">
							<tr>
								<td>${actionitem.id }</td>
								<td>${actionitem.dateCreated}</td>
								<td>${actionitem.assignedTo.name}</td>
								<td><span
									class="badge_style b_${fn:toLowerCase(actionitem.status)}">${actionitem.status}</span></td>
								<td>${actionitem.summary }</td>
							</tr>
						</c:forEach>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="4"><a
								href="${pageContext.request.contextPath}/app/new-actionitem">
									<!-- <button class="btn_small btn_blue">Add Lessons Learned</button>-->
							</a></td>
						</tr>
					</tfoot>
				</table>
			</div>
		</div>
		<span class="clear"></span>

		<div class="grid_12">
			<div class="widget_wrap">
				<div class="widget_top">
					<span class="h_icon list_images"></span>
					<h6>Lessons Learned</h6>
				</div>
				<table class="display data_tbl">
					<thead>
						<tr>
							<th>ID</th>
							<th>Summary</th>
							<th>Impact</th>
							<th>Strategy for Improvement</th>
							<th>Recommendation</th>
						</tr>
					</thead>

					<tbody>
						<c:forEach var="lessonsLearned" items="${Model.lessonsLearned}">
							<tr>
								<td>${lessonsLearned.id }</td>
								<td><a href="lessonslearned">${lessonsLearned.summary }</a></td>
								<td>${lessonsLearned.impact }</td>
								<td>${lessonsLearned.successFactors }</td>
								<td>${lessonsLearned.recommendation }</td>
								<c:if test="${not Model.active }">
									<td></td>
								</c:if>
								<c:if test="${Model.active }">
									<td style="white-space: nowrap;"><span><a
											class="action-icons c-edit"
											href="${pageContext.request.contextPath}/app/edit-lessonslearned/${lessonsLearned.id}"
											title="Edit">Edit</a></span><span><a
											class="action-icons c-delete" href="#" title="delete">Delete</a></span><span><a
											class="action-icons c-approve"
											href="${pageContext.request.contextPath}/app/new-lessonslearned"
											title="Create">Create</a></span></td>
								</c:if>

							</tr>
						</c:forEach>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="4"><a
								href="${pageContext.request.contextPath}/app/new-lessonslearned">
									<!-- <button class="btn_small btn_blue">Add Lessons Learned</button>-->

							</a></td>
						</tr>
					</tfoot>
				</table>
			</div>
		</div>

		
		<span class="clear"></span>
	</div>
</div>