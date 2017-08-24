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
		<li><a
			href="${pageContext.request.contextPath}/app/organization-groups">Groups</a>
			<span> >> </span></li>
		<li><a href="#" style="text-decoration: none;">Group</a></li>
	</ul>
</div>
<div class="content">
	<div class="grid_container">
		<div class="grid_12 full_block">
			<div class="widget_wrap">
				<div class="widget_top">
					<span class="h_icon list"></span>
					<h6>Group Info</h6>
				</div>
				<div class="widget_content">
					<c:if test="${not empty success}">
						<div class="successblock">
							<spring:message code="${success}"></spring:message>
						</div>
					</c:if>
					<form
						action="${pageContext.request.contextPath}/app/edit-organization-group/${organizationGroup.id}"
						class="form_container left_label">
						<span class="subheader-title">Group: ${organizationGroup.name }</span>

						<ul>
							<li>
								<div class="form_grid_12">
									<label class="field_title">ID</label>
									<div class="form_input">
										<span class="uneditable-input mid">${organizationGroup.id }</span>
									</div>
								</div>
							</li>
							<li>
								<div class="form_grid_12">
									<label class="field_title">Name</label>
									<div class="form_input">
										<span class="uneditable-input mid">${organizationGroup.name }</span>
									</div>
								</div>
							</li>
							<li>
								<div class="form_grid_12">
									<label class="field_title">Description</label>
									<div class="form_input">
										<span class="uneditable-input mid">${organizationGroup.description }</span>
									</div>
								</div>
							</li>
							<li>
								<div class="form_grid_12">
									<label class="field_title">Start Date</label>
									<div class="form_input">
										<span class="uneditable-input mid"><fmt:formatDate
												type="date" value="${organizationGroup.startDate}" /></span>
									</div>
								</div>
							</li>
							<li>
								<div class="form_grid_12">
									<label class="field_title">End Date</label>
									<div class="form_input">
										<span class="uneditable-input mid"><fmt:formatDate
												type="date" value="${organizationGroup.endDate}" /></span>
									</div>
								</div>
							</li>
							<li>
								<div class="form_grid_12">
									<label class="field_title">Director</label>
									<div class="form_input">
										<span class="uneditable-input mid">${organizationGroup.director }</span>
									</div>
								</div>
							</li>

						</ul>
						<ul>
							<li>
								<div class="form_grid_12">
									<a href="../organization-group-edit/${organizationGroup.id}"><button
											class="btn_small btn_blue">Edit Group</button></a>
								</div>
							</li>

						</ul>
					</form>
				</div>
				<table class="display data_tbl" id="dashboard">
					<thead>
						<tr>
							
							<th title="Program Name">Program's Name</th>
							<th title="Manager">Program's Manager</th>
							<th title="Program Start Date">Start Date</th>
							<th title="Program End Date">End Date</th>
							<th title="Active">Active</th>
							<th title="Action">Action</th>
						</tr>
					</thead>
					<tbody>
                    <c:forEach var="program" items="${organizationGroup.programs}">
                    <c:if test="${not program.active }">
									<c:set var="linkcolor" value="activeobjectlink" />
								</c:if>
								<c:if test="${program.active }">
									<c:set var="linkcolor" value="" />
								</c:if>
                <tr>
                	<td><a class="${linkcolor }" href="${pageContext.request.contextPath}/app/program/${program.id}"><c:out value='${program.name}' /></a></td>
                	<td><c:out value='${program.manager.name}' /></td>
                	<td><fmt:formatDate type="date" value="${program.startDate}" /></td>
                	<td><fmt:formatDate type="date" value="${program.endDate}" /></td>
                	<td><c:out value='${program.active}' /></td>
                	<td><span><a class="action-icons c-edit"
											href="${pageContext.request.contextPath}/app/edit-program/${program.id}"
											title="Edit">Edit</a></span>
							<span><a class="action-icons c-approve"
											href="${pageContext.request.contextPath}/app/new-program/${organizationGroup.id}"
											title="New">New</a></span>
											</td>
				</tr>
                </c:forEach>
                    </tbody>
                </table>
			</div>
		</div>
	</div>
</div>
