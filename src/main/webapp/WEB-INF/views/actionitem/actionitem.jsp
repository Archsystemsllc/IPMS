<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<div id="breadcrumb">
	<ul>
		<li><a href="${pageContext.request.contextPath}/app/groupdashboard">Home</a>
			<span> >> </span></li>
		<li><a href="${pageContext.request.contextPath}/app/artifacts">Artifacts</a>
			<span> >> </span></li>
		<li><a href="#" style="text-decoration: none;">Actionitem</a></li>
	</ul>
</div>
<div class="content">
	<div class="grid_container">
		<div class="grid_12 full_block">
			<div class="widget_wrap">
				<div class="widget_top">
					<span class="h_icon list"></span>
					<h6>Action Item</h6>
				</div>
				<div class="widget_content">
					<form action="../edit-actionitem/${actionItem.id}"
						class="form_container left_label">

						<ul>
							<li>
								<div class="form_grid_12">
									<label class="field_title">ID</label>
									<div class="form_input">
										<span class="uneditable-input mid">${actionItem.id }</span>
									</div>
								</div>
							</li>
							<li>
								<div class="form_grid_12">
									<label class="field_title">Summary</label>
									<div class="form_input">
										<span class="uneditable-input textarea">${actionItem.summary
											}</span>
									</div>
								</div>
							</li>

							<c:if test="${not empty(actionItem.meeting) }">
								<li>
									<div class="form_grid_12">
										<label class="field_title">Meeting</label>
										<div class="form_input">
											<span class="uneditable-input textarea">${actionItem.meeting.title
												}</span>
										</div>
									</div>
								</li>
							</c:if>
							<c:if test="${not empty(actionItem.issue) }">
								<li>
									<div class="form_grid_12">
										<label class="field_title">Issue</label>
										<div class="form_input">
											<span class="uneditable-input textarea">${actionItem.issue.summary
												}</span>
										</div>
									</div>
								</li>
							</c:if>
							<li>
								<div class="form_grid_12">
									<label class="field_title">Assigned To</label>
									<div class="form_input">
										<span class="uneditable-input mid">${actionItem.assignedTo.name
											}</span>
									</div>
								</div>
							</li>
							<li>
								<div class="form_grid_12">
									<label class="field_title">Date Created</label>
									<div class="form_input">
										<span class="uneditable-input mid"><fmt:formatDate
												type="date" value="${actionItem.dateCreated }" /></span>
									</div>
								</div>
							</li>
							<li>
								<div class="form_grid_12">
									<label class="field_title">Due Date</label>
									<div class="form_input">
										<span class="uneditable-input mid"><fmt:formatDate
												type="date" value="${actionItem.dueDate }" /></span>
									</div>
								</div>
							</li>
							<li>
								<div class="form_grid_12">
									<label class="field_title">Priority</label>
									<div class="form_input">
										<span class="uneditable-input mid"><c:out
												value="${actionItem.priority }" /></span>
									</div>
								</div>
							</li>
							<li>
								<div class="form_grid_12">
									<label class="field_title">Status</label>
									<div class="form_input">
										<span class="uneditable-input mid">${actionItem.status}</span>
									</div>
								</div>
							</li>
						</ul>
						<ul>
							<li>
								<div class="form_grid_12">
									<a
										href="${pageContext.request.contextPath}/app/edit-actionitem/${actionItem.id}">
										<button class="btn_small btn_blue">Edit Action Item</button>
									</a>
								</div>
							</li>
						</ul>
					</form>
				</div>

				<div class="widget_wrap tabby">
					<div class="widget_top">
						<div id="widget_tab" style="float: left;">
							<ul>
								<li><a href="#tab1">Revision History</a></li>
							</ul>
						</div>
					</div>
					<div class="widget_content">
						<div id="tab1">
							<table class="display data_tbl">
								<thead>
									<tr>
										<th>Date</th>
										<th>User</th>
										<th>Manage></th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="revision" items="${actionitem.revisions}">
										<tr>
											<td><fmt:formatDate type="date"
													value='${revision.updatedAt}' /></td>
											<td>${revision.principal.name }</td>
											<td>${revision.text}</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>

					</div>
				</div>
			</div>
		</div>
	</div>
</div>
