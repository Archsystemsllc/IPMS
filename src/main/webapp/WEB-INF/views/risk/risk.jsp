<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml"%>

<div id="breadcrumb">
	<ul>
		<li><a href="${pageContext.request.contextPath}/app/groupdashboard">Home</a>
			<span> >> </span></li>
		<li><a href="${pageContext.request.contextPath}/app/artifacts">Artifacts</a>
			<span> >> </span></li>
		<li><a href="#" style="text-decoration: none;">Risk</a></li>
	</ul>
</div>
<div class="content">
	<div class="grid_container">
		<div class="grid_12 full_block">
			<div class="widget_wrap">
				<div class="widget_top">
					<span class="h_icon list"></span>
					<h6>Risk</h6>
				</div>
				<div class="widget_content">
					<form action="../edit-risk/${risk.id}"
						class="form_container left_label">
						<span class="subheader-title">Risk : <c:out
								value='${risk.riskSummary}' /></span>
						<ul>
							<li>
								<div class="form_grid_12">
									<label class="field_title">ID</label>
									<div class="form_input">
										<span class="uneditable-input mid">${risk.id }</span>
									</div>
								</div>
							</li>
							<li>
								<div class="form_grid_12">
									<label class="field_title">Risk Summary</label>
									<div class="form_input">
										<span class="uneditable-input textarea">${risk.riskSummary
											}</span>
									</div>
								</div>
							</li>
							<c:if test="${not empty risk.program}">
								<li>
									<div class="form_grid_12">
										<label class="field_title">Vertical Group</label>
										<div class="form_input">
											<span class="uneditable-input mid">${risk.program.name
												}</span>
										</div>
									</div>
								</li>
							</c:if>
							<c:if test="${not empty risk.project}">
								<li>
									<div class="form_grid_12">
										<label class="field_title">Project</label>
										<div class="form_input">
											<span class="uneditable-input mid">${risk.project.name
												}</span>
										</div>
									</div>
								</li>
							</c:if>
							<li>
								<div class="form_grid_12">
									<label class="field_title">Impact</label>
									<div class="form_input">
										<span class="uneditable-input mid">${risk.impact }</span>
									</div>
								</div>
							</li>
							<li>
								<div class="form_grid_12">
									<label class="field_title">Likelihood</label>
									<div class="form_input">
										<span class="uneditable-input mid">${risk.likelihood }</span>
									</div>
								</div>
							</li>
							<li>
								<div class="form_grid_12">
									<label class="field_title">Mitigating Factors</label>
									<div class="form_input">
										<span class="uneditable-input mid">${risk.mitigatingFactors
											}</span>
									</div>
								</div>
							</li>
							<li>
								<div class="form_grid_12">
									<label class="field_title">Status</label>
									<div class="form_input">
										<span class="uneditable-input mid">${risk.status}</span>
									</div>
								</div>
							</li>
						</ul>
						<ul>
							<li>
								<div class="form_grid_12">
									<button type="submit" class="btn_small btn_blue">
										<span>Edit</span>
									</button>
									<button type="reset" class="btn_small btn_blue">
										<span>Reset</span>
									</button>
								</div>
							</li>
						</ul>
					</form>
				</div>
			</div>
			<div class="widget_wrap tabby">
				<div class="widget_top">
					<div id="widget_tab" style="float: left;">
						<ul>
							<li><a href="#tab3">Revision History</a></li>
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
								<c:forEach var="revision" items="${risk.revisions}">
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