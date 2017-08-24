<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<script>
	// Wait until the DOM has loaded before querying the document
	$(document).ready(function() {

		$("#minutes").change(function() {
			

			alert("mobeena");
			$("input[type=hidden]").val("meetingselected");
			alert("mobeena1");
			$('#meetingminutesform').submit();	
		});

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
                    <h6>Meeting Minutes Add</h6>
                </div>
                <form:form modelAttribute="meeting" class="form_container left_label"
                           action="${pageContext.request.contextPath}/app/meetingminutesadd1/" method="post" name="meetingminutesform" id="meetingminutesform">
                    <div class="widget_content">
                        <!-- <span class="subheader-title">Schedule a Meeting</span> -->
                        <form:errors path="*" cssClass="errorblock" element="div"/>
                        <ul>
                         <li>
                                    <div class="form_grid_12">
                                        <label for="minutes" class="field_title">Meetings</label>
                                        <div class="form_input">
                                          
                                                <form:select id="minutes" name="minutes" class="mid"
                                                             path="minutes">
                                                    <form:options items="${referenceData.MeetingList}"/>
                                                </form:select>
                                           <input type="hidden" name="meetingselectbox" value="true" /> 
                                        </div>
                                    </div>
                            </li>
              
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
										<span class="uneditable-input mid" >${meeting.project.name}</span>
                                            </c:if>
                                        </div>
                                    </div>
                                </li>
                           
                           
                                <li>
                                    <div class="form_grid_12">
                                        <label for="programId" class="field_title">Model</label>
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
                                    <label for="name" class="field_title">Subject</label>
                                    <div class="form_input">
                                        <form:input type="text" class="mid" id="title" name="title" path="title"></form:input>
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
                                    <label for="facilitator" class="field_title">Facilitator</label>
                                    <div class="form_input">
                                        <form:input type="text" path="facilitator" name="facilitator" id="facilitator"
                                                    class='timepicker mid'></form:input>
                                    </div>
                                </div>
                            </li>
                            <li>
                                <div class="form_grid_12">
                                    <label for="attendeesList" class="field_title">Attendees</label>
                                    <div class="form_input">
                                        <form:select style="width:600px" name="attendeesList" id="attendeesList"
                                                     path="attendeesList" class="mid" multiple="true">
                                            <form:options items="${referenceData.attendeesList}"/>
                                        </form:select>
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
                                                <label class="field_title" for="agendaItemdescription">Agenda</label>
                                                <div class="form_input">
                                                  <form:textarea type="text" id="agendaItemdescription" name="agendaItemdescription" path="agendaItemdescription" ></form:textarea>
                                                </div>
                                </div>
                            </li>
                             <li>
                                <div class="form_grid_12">
                                                <label class="field_title" for="discussion">Discussion</label>
                                                <div class="form_input">
                                                    <form:textarea type="text" id="discussion" name="discussion" path="discussion" ></form:textarea>
                                                </div>
                                </div>
                            </li>   
                            <li>
                                <div class="form_grid_12">
                                                <label class="field_title" for="actionItem">Action Items</label>
                                                <div class="form_input">
                                                    <form:textarea type="text" id="actionItem" name="actionItem" path="actionItem" ></form:textarea>
                                                </div>
                                </div>
                            </li>
                             <li>
                                <div class="form_grid_12">
                                                <label class="field_title" for="issues">Issues</label>
                                                <div class="form_input">
                                                    <form:textarea type="text" id="issues" name="issues" path="issues" ></form:textarea>
                                                </div>
                                </div>
                            </li>
                             
                              <li>
                                <div class="form_grid_12">
                                                <label class="field_title" for="parkingLot">Parking Lot</label>
                                                <div class="form_input">
                                                    <form:textarea type="text" id="parkingLot" name="parkingLot" path="parkingLot" ></form:textarea>
                                                </div>
                                </div>
                            </li>
                             
                             
                             
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