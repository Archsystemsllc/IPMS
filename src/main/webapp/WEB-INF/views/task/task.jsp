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
		<li><a href="${pageContext.request.contextPath}/app/tasks">Tasks</a>
			<span> >> </span></li>
		<li><a href="#" style="text-decoration: none;">Task</a></li>
	</ul>
</div>
<div class="content">
	<div class="grid_container">
	    <div class="grid_12">
			<div class="widget_wrap">
				<div class="widget_top">
					<span class="h_icon list"></span>
					<h6>TASK: <c:out value='${task.name }' /></h6>
				</div>
				<div class="widget_content">
				<form
						action="${pageContext.request.contextPath}/app/edit-task/${task.id}"
						class="form_container left_label">
						<span class="subheader-title">Task: ${task.name }</span>
						<ul>
							<li>
								<div class="form_grid_12">
									<label class="field_title">ID</label>
									<div class="form_input">
										<span class="uneditable-input mid">${task.id }</span>
									</div>
								</div>
							</li>
							<li>
								<div class="form_grid_12">
									<label class="field_title">Description</label>
									<div class="form_input">
										<span class="uneditable-input mid">${task.description }</span>
									</div>
								</div>
							</li>
							<sec:authorize access="hasRole('ROLE_PROJECT_MANAGER')">
								<c:if test="${not empty(task.project) }">
									<li>
										<div class="form_grid_12">
											<label class="field_title">Project</label>
											<div class="form_input">
												<span class="uneditable-input mid">${task.project.name}</span>
											</div>
										</div>
									</li>
								</c:if>
							</sec:authorize>
							<sec:authorize access="hasRole('ROLE_PROGRAM_MANAGER')">
								<c:if test="${not empty(task.program) }">
									<li>
										<div class="form_grid_12">
											<label class="field_title">Program</label>
											<div class="form_input">
												<span class="uneditable-input mid">${task.program.name}</span>
											</div>
										</div>
									</li>
								</c:if>
							</sec:authorize>
							<li>
								<div class="form_grid_12">
									<label class="field_title">Assigned To</label>
									<div class="form_input">
										<span class="uneditable-input mid">${task.assignedTo.name
											}</span>
									</div>
								</div>
							</li>
							<li>
								<div class="form_grid_12">
									<label class="field_title">Created By</label>
									<div class="form_input">
										<span class="uneditable-input mid">${task.createdBy.name
											}</span>
									</div>
								</div>
							</li>
							<li>
								<div class="form_grid_12">
									<label class="field_title">Date Created</label>
									<div class="form_input">
										<span class="uneditable-input mid"><fmt:formatDate
												type="date" value="${task.dateCreated}" /></span>
									</div>
								</div>
							</li>
							<li>
								<div class="form_grid_12">
									<label class="field_title">Due Date</label>
									<div class="form_input">
										<span class="uneditable-input mid"><fmt:formatDate
												type="date" value="${task.dueDate}" /></span>
									</div>
								</div>
							</li>
						</ul>
						<ul>
							<li>
								<div class="form_grid_12">
									<a href="../edit-task/${task.id}"><button
											class="btn_small btn_blue">Edit Task</button></a>
								</div>
							</li>
						</ul>
					</form>
				</div>
			</div>
		</div>
		
		<div class="grid_12">
			<div class="widget_wrap">
				<div class="widget_top">
					<span class="h_icon list"></span>
					<h6>Sub Tasks</h6>
				</div>
				<div class="widget_content">
					<div id="tab1">
						<table class="display data_tbl">
                        <thead>
                        <tr>
                            <th>Name</th>
                            <th>Program Name</th>
                            <th>Project Name</th>
                            <th>Created By</th>
                            <th>Assigned To</th>
                            <th>Date Due</th>
                            <th>Status</th>
                            <th>Manage></th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:set var="context" value="${pageContext.request.contextPath}" />
                        <c:forEach var="task" items="${subTasks}">
                            <tr>
                            
                                <td><a href="${context}/app/task/${task.id}">${task.name}</a></td>
                                <td>${task.program.name}</td>
                                 <td>${task.project.name}</td>
                                <td>${task.createdBy.name}</td>
                                <td>${task.assignedTo.name}</td>
                                <td><fmt:formatDate type="date" value="${task.dueDate}" /></td>
                                <td><span class="badge_style b_${fn:toLowerCase(task.status)}">${task.status }</span></td>
                                <td>
                                    <span><a class="action-icons c-edit" href="${pageContext.request.contextPath}/app/edit-task/${task.id}" title="Edit">Edit</a></span><span><a class="action-icons c-approve" href="${pageContext.request.contextPath}/app/new-task" title="Create">Create</a></span>
                                </td>
                            
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
					</div>
				</div>	
		    </div>
		</div>
		
		<div class="grid_12">
			<div class="widget_wrap">
				<div class="widget_top">
					<span class="h_icon list"></span>
					<h6>Revision History</h6>
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
								<c:forEach var="revision" items="${task.revisions}">
									<tr>
										<td><fmt:formatDate type="date"
												value='${revision.updatedAt }' /></td>
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







				