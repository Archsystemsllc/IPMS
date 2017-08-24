<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<div id="breadcrumb">
    <ul>
        <li>
            <a href="${pageContext.request.contextPath}/app/groupdashboard">Home</a> <span> >> </span>
        </li>
        <li>
            <a href="${pageContext.request.contextPath}/app/tasks">Tasks</a> <span> >> </span>
        </li>
        <li>
            <a href="#" style="text-decoration: none;">Edit Task</a>
        </li>
    </ul>
</div>
<div class="content">
    <div class="grid_container">
        <div class="grid_12 full_block">
            <div class="widget_wrap">
                <div class="widget_top">
                    <span class="h_icon list"></span>
                    <h6>Task</h6>
                </div>
                <div class="widget_content">
                    <span class="subheader-title">Task: ${task.name}</span>
                    <form:form modelAttribute="task" class="form_container left_label"
                               action="../edit-task" method="post">
                        <form:errors path="*" cssClass="errorblock" element="div" />
                        <ul>
                            <li>
                                <fieldset>
                                    <legend>Task Information</legend>
                                    <ul>
                                        <li>
                                            <div class="form_grid_12">
                                                <label for="name" class="field_title">Name</label>

                                                <div class="form_input">
                                                    <form:input type="hidden" name="id" path="id" ></form:input>
                                                    <form:input type="text" class="mid" id="name" name="name" path="name" ></form:input>
                                                </div>
                                            </div>
                                        </li>
                                        <li>
                                            <div class="form_grid_12">
                                                <label class="field_title">Description</label>
                                                <div class="form_input">
                                                    <form:textarea type="text" class="textarea" id="description" name="description" path="description" ></form:textarea>
                                                </div>
                                            </div>
                                        </li>
                                        <li>
                                            <div class="form_grid_12">
                                                <label for="assignedToId" class="field_title">Assigned To</label>

                                                <div class="form_input">
                                                    <form:select id="assignedToId" name="assignedToId" path="assignedToId" class="mid">
                                                        <form:options items="${referenceData.assignedToList}"  />
                                                    </form:select>
                                                </div>
                                            </div>
                                        </li>
                                        <li>
                                            <div class="form_grid_12">
                                                <label for="createdById" class="field_title">Created By</label>

                                                <div class="form_input">
                                                    <form:select id="createdById" name="createdById" path="createdById" class="mid">
                                                        <form:options items="${referenceData.createdByList}"  />
                                                    </form:select>
                                                </div>
                                            </div>
                                        </li>
                                        <sec:authorize access="hasRole('ROLE_PROGRAM_MANAGER')">
                                            <li>
                                                <div class="form_grid_12">
                                                    <label for="programId" class="field_title">Program</label>

                                                    <div class="form_input">
                                                        <form:select id="programId" name="programId" path="programId" class="mid">
                                                            <form:options items="${referenceData.programList}"  />
                                                        </form:select>
                                                    </div>
                                                </div>
                                            </li>
                                        </sec:authorize>
                                        <sec:authorize access="hasRole('ROLE_PROJECT_MANAGER')">
                                            <li>
                                                <div class="form_grid_12">
                                                    <label for="projectId" class="field_title">Project</label>

                                                    <div class="form_input">
                                                        <form:select id="projectId" name="projectId" path="projectId">
                                                            <form:options items="${referenceData.projectList}"  />
                                                        </form:select>
                                                    </div>
                                                </div>
                                            </li>
                                        </sec:authorize>
                                        <li>
                                            <div class="form_grid_12">
                                                <label for="dateCreated" class="field_title">Date Created</label>
                                                <div class="form_input">
                                                    <form:input type="text"  name="dateCreated" id="dateCreated" path="dateCreated" class='datepicker mid '></form:input>
                                                </div>
                                            </div>
                                        </li>
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
                                                <label for="status" class="field_title">Status</label>

                                                <div class="form_input">
                                                    <form:select id="status" name="status" path="status" class="mid">
                                                        <form:option value="">--Choose Status--</form:option>
                                                        <form:options items="${referenceData.statusList}"  />
                                                   // adding function for status workflow in tasks
                                                    <script>
													$(document).ready(function () {
													
														  var currentStatus = $('#status').val();
														  if(currentStatus == "Open" ){
														    	$('#status option[value="Reopened"]').prop("disabled", true);
															  } else 
																  //if(currentStatus != "Open" ){
													    	$('#status option[value="Open"]').prop("disabled", true);
														 
													   }); 																			
													 </script>
                                                     </form:select>
                                                   
                                                </div>
                                          	</div>
                                        </li>
                                    </ul>
                                </fieldset>
                            </li>
                        </ul>

                        <ul>
                            <li>
                                <div class="form_grid_12">
                                    <button type="submit" class="btn_small btn_blue"><span>Update</span></button>
                                    <button type="reset" class="btn_small btn_blue"><span>Reset</span></button>
                                </div>
                            </li>
                        </ul>
                    </form:form>
                </div>
            </div>
        </div>
    </div>
</div>