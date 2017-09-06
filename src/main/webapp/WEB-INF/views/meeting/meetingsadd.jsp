<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
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
        <li>
            <a href="${pageContext.request.contextPath}/app/groupdashboard">Home</a> <span> >> </span>
        </li>
        <li>
            <a href="${pageContext.request.contextPath}/app/meetings">Meetings</a> <span> >> </span>
        </li>
        <li>
            <a href="#" style="text-decoration: none;">New Meeting</a>
        </li>
    </ul>
</div>
<div class="content">
    <div class="grid_container">
        <div class="grid_12 full_block">
            <div class="widget_wrap">
                <div class="widget_top">
                    <span class="h_icon list_image"></span>
                    <h6>Schedule New Meeting</h6>
                </div>
                <form:form modelAttribute="meeting" class="form_container left_label"
                           action="${pageContext.request.contextPath}/app/new-meeting" method="post">
                    <div class="widget_content">
                        <!-- <span class="subheader-title">Schedule a Meeting</span> -->
                        <form:errors path="*" cssClass="errorblock" element="div"/>
                        <ul>
                            <li>
                                <div class="form_grid_12">
                                    <label for="name" class="field_title">Title</label>
                                    <div class="form_input">
                                        <form:input type="text" class="mid" id="title" name="title" path="title"></form:input>
                                    </div>
                                </div>
                            </li>
                            <li>
                                <div class="form_grid_12">
                                    <label for="organizerId" class="field_title">Organizer</label>
                                    <div class="form_input">
                                        <form:select id="organizerId" name="organizerId"
                                                     path="organizerId">
                                            <form:options items="${referenceData.organizerList}"/>
                                        </form:select>
                                    </div>
                                </div>
                            </li>
                            <li>
                                <div class="form_grid_12">
                                    <label for="date" class="field_title">Date</label>
                                    <div class="form_input">
                                        <form:input type="text" path="date" name="date" id="date"
                                                    class='datepicker mid'></form:input>
                                    </div>
                                </div>
                            </li>
                            <li>
                                <div class="form_grid_12">
                                    <label for="time" class="field_title">Time</label>
                                    <div class="form_input">
                                        <form:input type="text" path="time" name="time" id="time"
                                                    class='timepicker mid'></form:input>
                                    </div>
                                </div>
                            </li>
                            <li>
                                <div class="form_grid_12">
                                    <label for="type" class="field_title">Type</label>
                                    <div class="form_input">
                                        <form:select id="type" name="type"
                                                     path="type">
                                            <form:options items="${referenceData.typeList}"/>
                                        </form:select>
                                    </div>
                                </div>
                            </li>
                            <li>
                                <div class="form_grid_12">
                                    <label for="duration" class="field_title">Duration (HRS)</label>
                                    <div class="form_input">
                                        <form:input type="text" id="duration" name="duration" class="mid"
                                                    path="duration"></form:input>
                                    </div>
                                </div>
                            </li>
                            <li>
                                <div class="form_grid_12">
                                    <label for="locationInfo" class="field_title">Location</label>
                                    <div class="form_input">
                                        <form:input type="text" id="locationInfo" name="locationInfo" class="mid"
                                                    path="locationInfo"></form:input>
                                    </div>
                                </div>
                            </li>
                            <li>
                                <div class="form_grid_12">
                                    <label for="otherInfo" class="field_title">Conf Call#</label>
                                    <div class="form_input">
                                        <form:input type="text" id="otherInfo" name="otherInfo" class="mid"
                                                    path="otherInfo"></form:input>
                                    </div>

                                </div>
                            </li>
                            <li>
                                <div class="form_grid_12">
                                    <label for="attendeesList" class="field_title">Facilitator</label>
                                    <div class="form_input">
                                        <form:select style="width:600px" name="attendeesList" id="attendeesList"
                                                     path="attendeesList" class="mid" multiple="true">
                                            <form:options items="${referenceData.attendeesList}"/>
                                        </form:select>
                                    </div>
                                </div>
                            </li>
                            <c:if test="${meeting.projectMeeting}">
                                <li>
                                    <div class="form_grid_12">
                                        <label for="projectId" class="field_title">Project</label>
                                        <div class="form_input">
                                            <c:if test="${empty meeting.project }">
                                                <form:select id="projectId" name="projectId" class="mid"
                                                             path="projectId">
                                                    <form:options items="${referenceData.projectList}"/>
                                                </form:select>
                                            </c:if>
                                            <c:if test="${not empty meeting.project }">
                                                <form:input type="hidden" id="projectId" name="projectId"
                                                            path="projectId"></form:input>
										<span class="uneditable-input mid" >${meeting.project.name }</span>
                                            </c:if>
                                        </div>
                                    </div>
                                </li>
                            </c:if>

                            <c:if test="${not meeting.projectMeeting}">
                                <li>
                                    <div class="form_grid_12">
                                        <label for="programId" class="field_title">Vertical Group</label>
                                        <div class="form_input">
                                            <c:if test="${empty meeting.program }">
                                                <form:select id="programId" name="programId"
                                                             path="programId" class="mid">
                                                    <form:options items="${referenceData.programList}"/>
                                                </form:select>
                                            </c:if>
                                            <c:if test="${not empty meeting.program }">
                                                <form:input type="hidden" id="programId" name="programId"
                                                            path="programId"></form:input>
										<span class="uneditable-input mid">${meeting.program.name }</span>
                                            </c:if>
                                        </div>
                                    </div>
                                </li>
                            </c:if>
                            <li>
                                <div class="form_grid_12">
                                    <div class="form_input">
                                        <button type="submit" class="btn_small btn_blue"><span>Create</span></button>
                                        <button type="reset" class="btn_small btn_blue"><span>Reset</span></button>
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