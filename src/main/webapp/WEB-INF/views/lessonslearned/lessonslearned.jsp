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
		<li><a href="#" style="text-decoration: none;">Lessons
				Learned</a></li>
	</ul>
</div>
<div class="content">
	<div class="grid_container">
		<div class="grid_12 full_block">
			<div class="widget_wrap">
				<div class="widget_top">
					<span class="h_icon list"></span>
					<h6>Lessons Learned</h6>
				</div>
				<div class="widget_content">
					<form action="../edit-lessonslearned/${lessonsLearned.id}"
						class="form_container left_label">
						<span class="subheader-title">Lessons Learned: <c:out
								value='${lessonsLearned.summary }' /></span>
						<ul>
							<li>
								<div class="form_grid_12">
									<label class="field_title">ID</label>
									<div class="form_input">
										<span class="uneditable-input mid">${lessonsLearned.id
											}</span>
									</div>
								</div>
							</li>
							<li>
								<div class="form_grid_12">
									<label class="field_title">Summary</label>
									<div class="form_input">
										<span class="uneditable-input textarea">${lessonsLearned.summary
											}</span>
									</div>
								</div>
							</li>
							<li>
								<div class="form_grid_12">
									<label class="field_title">Impact</label>
									<div class="form_input">
										<span class="uneditable-input textarea">${lessonsLearned.impact
											}</span>
									</div>
								</div>
							</li>
							<li>
								<div class="form_grid_12">
									<label class="field_title">Recommendation</label>
									<div class="form_input">
										<span class="uneditable-input textarea">${lessonsLearned.recommendation
											}</span>
									</div>
								</div>
							</li>
							<li>
								<div class="form_grid_12">
									<label class="field_title">Success factors</label>
									<div class="form_input">
										<span class="uneditable-input textarea">${lessonsLearned.successFactors
											}</span>
									</div>
								</div>
							</li>
							<li>
								<div class="form_grid_12">
									<label class="field_title">Issue</label>
									<div class="form_input">
										<span class="uneditable-input textarea">${lessonsLearned.issue.summary
											}</span>
									</div>
								</div>
							</li>
						</ul>
						<ul>
							<li>
								<div class="form_grid_12">
									<a
										href="${pageContext.request.contextPath}/app/edit-lessonslearned/${lessonsLearned.id}">
										<button class="btn_small btn_blue">Edit Lessons
											Learned</button>
									</a>
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
									<th>Manage</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="revision" items="${lessonsLearned.revisions}">
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