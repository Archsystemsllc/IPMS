<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
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
            <a href="${pageContext.request.contextPath}/app/artifacts">Artifacts</a> <span> >> </span>
        </li>
        <li>
            <a href="#" style="text-decoration: none;">New Actionitem</a>
        </li>
    </ul>
</div>
<div class="content">
    <div class="grid_container">
        <div class="grid_12 full_block">
            <div class="widget_wrap">
                <div class="widget_top">
                    <span class="h_icon list_image"></span>
                    <h6>Action Item</h6>
                </div>
                <form:form modelAttribute="actionItem" class="form_container left_label"
                           action="${pageContext.request.contextPath}/app/new-actionitem" method="post">
                    <div class="widget_content">
                        <span class="subheader-title">New Action Item</span>
                        <form:errors path="*" cssClass="errorblock" element="div" />
                        <ul>
                   
                            <li>
                                <div class="form_grid_12">
                                    <label for="summary" class="field_title">Summary</label>
                                    <div class="form_input">
                                        <form:textarea id="summary" name="summary" path="summary" class="mid"></form:textarea>
        							</div>
                                </div>
                            </li>
                            <li>
                                <div class="form_grid_12">
                                    <label for="assignedId" class="field_title">Assigned To</label>
                                    <div class="form_input">
                                        <form:select id="assignedToId" name="assignedToId" path="assignedToId" class="mid">
                                            <form:options items="${referenceData.assignedToList}" />
                                        </form:select>
                                    </div>
                                </div>
                            </li>
                            <li>
                                <div class="form_grid_12">
                                    <label class="field_title">Priority</label>
                                    <div class="form_input">
                                        <span><form:radiobutton class="radio" id="priority" name="priority" path="priority" value="Low"/>Low</span>
                                        <span><form:radiobutton class="radio" id="priority" name="priority" path="priority" value="Low"/>Medium</span>
                                        <span><form:radiobutton class="radio" id="priority" name="priority" path="priority" value="Low"/>High</span>
                                    </div>
                                </div>
                            </li>
                            <li>
                                <div class="form_grid_12">
                                    <label for="status" class="field_title">Status</label>
                                    <div class="form_input">
                                        <form:select id="status" name="status" path="status" class="mid">
                                            <form:option value="Pending">--Choose Status--</form:option>
                                            <form:options items="${referenceData.statusList}"  />
                                        </form:select>
                                    </div>
                                </div>
                            </li>
                            <c:if test="${empty(actionItem.issue) }">
                               <c:if test="${empty(actionItem.meeting) }">
                                    <li>
                                    <div class="form_grid_12">
                                        <label for="issueId" class="field_title">Issue</label>
                                        <div class="form_input">
                                            <form:select id="issueId" name="issueId" path="issueId" class="mid">
                                                <form:options items="${referenceData.issueList}" />
                                            </form:select>
                                        </div>
                                    </div>
                                </li>
                                <li>
                                    <div class="form_grid_12">
                                        <label for="meetingId" class="field_title">Meeting</label>
                                        <div class="form_input">
                                             <form:select id="meetingId" name="meetingId" path="meetingId" class="mid">
                                                <form:options items="${referenceData.meetingList}" />
                                            </form:select>
                                        </div>
                                    </div>
                                </li>
                               </c:if>
                            </c:if>
                            <c:if test="${not empty(actionItem.issue) }">
                                <li>
                                    <div class="form_grid_12">
                                        <label for="issueId" class="field_title">Issue</label>
                                        <div class="form_input">
                                            <form:input type="hidden" id="issueId" name="issueId" path="issueId" />
                                
                                            <span class="uneditable-input mid">${actionItem.issue.name}</span>
                                            <!--<form:select id="issueId" name="issueId" path="issueId" class="mid">
                                                <form:options items="${referenceData.issueList}" />
                                            </form:select>-->
                                        </div>
                                    </div>
                                </li>
                            </c:if>
                            <c:if test="${not empty(actionItem.meeting) }">
                            <li>
                                    <div class="form_grid_12">
                                        <label for="meetingId" class="field_title">Meeting</label>
                                        <div class="form_input">
                                            <form:select id="meetingId" name="meetingId" path="meetingId" class="mid">
                                                <form:options items="${referenceData.meetingList}" />
                                            </form:select>
                                        </div>
                                    </div>
                                </li>
                           </c:if>
                            <li>
                                <div class="form_grid_12">
                                    <label for="dueDate" class="field_title">Due Date</label>
                                    <div class="form_input">
                                        <form:input type="text" name="dueDate" id="dueDate" path="dueDate" class='datepicker'></form:input>
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

                    </div></form:form>
            </div>
        </div>
    </div>
</div>