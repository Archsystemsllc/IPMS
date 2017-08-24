<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<script src="${pageContext.request.contextPath}/resources/js/jquery-1.10.2.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/jquery-ui-1.10.4.custom.js"></script>		
		
<script type="text/javascript">
	$(function() {
		$("name").bind("mouseenter", function(e) {
			$("#ToolTipDIv").offset({
				left : e.pageX,
				top : e.pageY
			});
			$("#ToolTipDIv").show('normal');
		});
		$("name").bind("mouseleave", function(e) {
			$("#ToolTipDIv").hide(); 
		});
	});
</script>
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
		<li><a href="${pageContext.request.contextPath}/app/tasks">Tasks</a>
			<span> >> </span></li>
		<li><a href="#" style="text-decoration: none;">New Task</a></li>
	</ul>
</div>
<div class="content">
	<div class="grid_container">
		<div class="grid_12 full_block">
			<div class="widget_wrap">
				<div class="widget_top">
					<span class="h_icon list_image"></span>
					<h6>Tasks</h6>
				</div>
				<form:form modelAttribute="task" class="form_container left_label"
					action="${pageContext.request.contextPath}/app/new-task"
					method="post">
					<div class="widget_content">
					<span class="subheader-title">Create New Task</span>
						<form:errors path="*" cssClass="errorblock" element="div" />
						<ul>

							<li>
								<div class="form_grid_12">
									<label for="name" class="field_title">Name</label>
									<div id="name"
										style="background-color: Yellow; display: none; width: 20%;">
										This is a text</div>
									<div class="form_input">
										<form:input type="text" class="mid" id="name" name="name"
											path="name"></form:input>
									</div>
								</div>
							</li>
							<li>
								<div class="form_grid_12">
									<label for="description" class="field_title">Description</label>
									<div class="form_input">
										<form:textarea type="text" class="textarea" id="description"
											name="description" path="description"></form:textarea>
									</div>
								</div>
							</li>
							<li>
								<div class="form_grid_12">
									<label for="assignedToId" class="field_title">Assigned
										To</label>

									<div class="form_input">
										<form:select id="assignedToId" name="assignedToId"
											path="assignedToId" class="mid">
											//Adding additional field as none so that while creating a task it is not assigned to anyone
											<form:option value="-1">None</form:option>
											<form:options items="${referenceData.assignedToList}" />
										</form:select>
									</div>
								</div>
							</li>
							<li>
								<div class="form_grid_12">
									<label for="createdById" class="field_title">Created By</label>

									<div class="form_input">
										<form:input path="createdById" type="hidden" class="mid"
											name="createdById" id="createdById"
											value="${task.createdById}" />
										<input type="text" id="createdBy" name="createdBy"
											path="createdBy" disabled="disabled" class="bcolor mid"
											value="${task.createdBy.name}">
									</div>
								</div>
							</li>
							<c:if test="${empty task.program}">
								<c:if test="${empty task.project}">
									<li>
										<div class="form_grid_12">
											<label for="projectId" class="field_title">Project</label>

											<div class="form_input">
												<c:if test="${empty task.project }">
													<form:select id="projectId" name="projectId"
														path="projectId" class="mid">
														<form:options items="${referenceData.projectList}" />
													</form:select>
												</c:if>

											</div>
										</div>
									</li>
									<li>
										<div class="form_grid_12">
											<label for="programId" class="field_title">Program</label>

											<div class="form_input">

												<form:select id="programId" name="programId"
													path="programId" class="mid">
													<form:options items="${referenceData.programList}" />
												</form:select>

											</div>
										</div>
									</li>
								</c:if>
							</c:if>
							<c:if test="${not empty task.program}">
								<li>
									<div class="form_grid_12">
										<label for="programId" class="field_title">Program</label>

										<div class="form_input">


											<form:input type="hidden" id="programId" name="programId"
												path="programId"></form:input>
											<input type="text" id="programId" name="programId"
												path="programId" disabled="disabled" class="bcolor mid"
												value="${task.program.name}">

										</div>
									</div>
								</li>
							</c:if>
							<c:if test="${not empty task.project}">
								<li>
									<div class="form_grid_12">
										<label for="projectId" class="field_title">Project</label>

										<div class="form_input">


											<form:input type="hidden" id="projectId" name="projectId"
												path="projectId"></form:input>
											<input type="text" id="projectId" name="projectId"
												path="projectId" disabled="disabled" class="bcolor mid"
												value="${task.project.name}">

										</div>
									</div>
								</li>
							</c:if>
							<li>
								<div class="form_grid_12">
									<label for="dueDate" class="field_title">Due Date</label>

									<div class="form_input">
										<form:input type="text" name="dueDate" id="dueDate" path="dueDate" class='datepicker mid'></form:input>
									</div>
								</div>
							</li>
							  
							<li>
								<div class="form_grid_12">
									<div class="form_input">
										<button type="submit" class="btn_small btn_blue">
											<span>Create</span>
										</button>
										<button type="reset" class="btn_small btn_blue">
											<span>Reset</span>
										</button>
									</div>
								</div>
							</li>
						</ul>

					</div>
				</form:form>
			</div>
		</div>
	</div>
</div>