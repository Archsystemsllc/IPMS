<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<script src="${pageContext.request.contextPath}/resources/js/jquery-1.10.2.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/jquery-ui-1.10.4.custom.js"></script>		
		<script>
		
$(function(){
  $.datepicker.setDefaults(
    $.extend($.datepicker.regional[''])
  );
  $('.datepicker').datepicker();
});

	</script>

<div id="breadcrumb">
	<ul>
		<li><a href="${pageContext.request.contextPath}/app/groupdashboard">Home</a>
			<span> >> </span></li>
		<li><a href="${pageContext.request.contextPath}/app/artifacts">Artifacts</a>
			<span> >> </span></li>
		<li><a href="#" style="text-decoration: none;">Edit Issue</a></li>
	</ul>
</div>
<div class="content">
	<div class="grid_container">
		<div class="grid_12 full_block">
			<div class="widget_wrap">
				<div class="widget_top">
					<span class="h_icon list"></span>
					<h6>Issue</h6>
				</div>
				<div class="widget_content">
					<span class="subheader-title">Edit Issue : <c:out
							value='${issue.summary }' /></span>
					<form:form modelAttribute="issue" class="form_container left_label"
						action="../edit-issue" method="post">
						<form:input type="hidden" id="id" name="id" path="id"></form:input>
						<form:errors path="*" cssClass="errorblock" element="div" />
						<ul>
							<li>
								<fieldset>
									<legend>Issue Information</legend>
									<ul>
										<li>
											<div class="form_grid_12">
												<label for="summary" class="field_title">Summary</label>
												<div class="form_input">
													<form:textarea id="summary" name="summary" path="summary" class="mid" ></form:textarea>
												</div>
											</div>
										</li>
										<li>
											<div class="form_grid_12">
												<label for="assigneeId" class="field_title">Reported
													By</label>
												<div class="form_input">
													<form:select id="assigneeId" name="assigneeId"
														path="assigneeId" class="mid">
														<form:options items="${referenceData.assignList}" />
													</form:select>
												</div>
											</div>
										</li>
										<li>
											<div class="form_grid_12">
												<label for="assignedId" class="field_title">Assigned
													To</label>
												<div class="form_input">
													<form:select id="assignedId" name="assignedId"
														path="assignedId" class="mid">
														<form:options items="${referenceData.assignList}" />
													</form:select>
												</div>
											</div>
										</li>
										<li>
											<div class="form_grid_12">
												<label for="dateReported" class="field_title">Date
													Reported</label>
												<div class="form_input">
													<form:input type="text" name="dateReported"
														id="dateReported" path="dateReported"
														class='datepicker mid'></form:input>
												</div>
											</div>
										</li>
										<li>
											<div class="form_grid_12">
												<label for="dueDate" class="field_title">Due Date</label>
												<div class="form_input">
													<form:input type="text" name="dueDate" id="dueDate"
														path="dueDate" class='datepicker mid'></form:input>
												</div>
											</div>
										</li>
										<li>
											<div class="form_grid_12">
												<label for="priority" class="field_title">Priority</label>
												<div class="form_input">
													<form:select id="priority" name="priority" path="priority"
														class="mid">
														<form:options items="${referenceData.priorityList}" />
													</form:select>
												</div>
											</div>
										</li>
										<li>
											<div class="form_grid_12">
												<label for="status" class="field_title">Status</label>
												<div class="form_input">
													<form:select id="status" name="status" path="status"
														class="mid">
														
														<form:option value="Pending">--Choose Status--</form:option>
														<form:options items="${referenceData.statusList}" />
													</form:select>
<%-- 													<form:hidden path="status" id="statusvalue"/>
 --%>												</div>
											</div>
										</li>
										<li>
											<div class="form_grid_12">
												<label for="projectId" class="field_title">Project</label>
												<div class="form_input">
													<form:select id="projectId" name="projectId"
														path="projectId" class="mid">
														<form:options items="${referenceData.projectList}" />
													</form:select>
												</div>
											</div>
										</li>
									</ul>
								</fieldset>
							</li>
							<li>
								<div class="form_grid_12">
									<div class="form_input">
										<button type="submit" class="btn_small btn_blue"
											name="btnAction" value="Update">
											<span>Update</span>
										</button>
										<button type="reset" class="btn_small btn_blue"
											name="btnAction" value="Reset">
											<span>Reset</span>
										</button>
									</div>
								</div>
							</li>
						</ul>
					</form:form>
				</div>
			</div>
		</div>
	</div>
</div>
<!-- 
<script type="text/javascript">
$(document).ready(function() {
	$("#statusvalue").val($("#status").val());
	//alert($("#statusvalue").val());
    
});
</script>
<script type="text/javascript">
$("#status").change(function(){
	var current = $(this).val();
	if($("#statusvalue").val() == "Reopened" && current == "Open")
		{
			alert("Cannot open a closed one");
		}

});
</script>
 -->