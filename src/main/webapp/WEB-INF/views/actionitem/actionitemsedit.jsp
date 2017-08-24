<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<div id="breadcrumb">
    <ul>
        <li>
            <a href="${pageContext.request.contextPath}/app/groupdashboard">Home</a> <span> >> </span>
        </li>
        <li>
            <a href="${pageContext.request.contextPath}/app/artifacts">Artifacts</a> <span> >> </span>
        </li>
        <li>
            <a href="#" style="text-decoration: none;">Edit Actionitem</a>
        </li>
    </ul>
</div>
<div class="content">
    <div class="grid_container">
        <div class="grid_12 full_block">
            <div class="widget_wrap">
                <div class="widget_top">
                    <span class="h_icon list"></span>
                    <h6>Action Item</h6>
                </div>
                <div class="widget_content">
                    <span class="subheader-title">Edit Action Item : <c:out value='${actionItem.summary }'/></span>
                    <form:form modelAttribute="actionItem" class="form_container left_label"
                               action="../edit-actionitem" method="post">
                         <c:if test="${not empty success}">
                        <div class="successblock"><spring:message code="${success}"></spring:message>
                        </div>
                  		</c:if>
                        <form:errors path="*" cssClass="errorblock" element="div"/>
                        <ul>
                            <li>
                                <fieldset>
                                    <legend>Action Item Information</legend>
                                    <ul>
                                        <li>
                                            <div class="form_grid_12">
                                                <label class="field_title">ID</label>
                                                <div class="form_input">
                                                    <span class="uneditable-input mid">${actionItem.id }</span>
                                                    <form:input type="hidden" id="id" name="id" path="id"></form:input>
                                                </div>
                                            </div>
                                        </li>
                                        <li>
                                            <div class="form_grid_12">
                                                <label class="field_title">Summary</label>
                                                <div class="form_input">
                                                    <form:textarea id="summary" name="summary" path="summary" class="mid"></form:textarea>
                                                </div>
                                            </div>
                                        </li>
                                        <li>
                                            <div class="form_grid_12">
                                                <label for="assignedToId" class="field_title">Assigned To</label>
                                                <div class="form_input">
                                                    <form:select id="assignedToId" name="assignedToId" path="assignedToId" class="mid">
                                                        <form:options items="${referenceData.assignedToList}" />
                                                    </form:select>
                                                </div>
                                            </div>
                                        </li>
                                        <li>
                                            <div class="form_grid_12">
                                                <label for="priority" class="field_title">Priority</label>
                                                <div class="form_input">
                                                    <form:select id="priority" name="priority" path="priority" class="mid">
                                                        <form:options items="${referenceData.priorityList}"  />
                                                    </form:select>
                                                </div>
                                            </div>
                                        </li>
                                        <c:if test="${not empty(actionItem.issue) }">
                                            <li>
                                                <div class="form_grid_12">
                                                    <label for="issueId" class="field_title">Issue</label>
                                                    <form:select id="issueId" name="issueId"
                                                                 path="issueId">
                                                        <form:options items="${referenceData.issueList}" />
                                                    </form:select>
                                                </div>
                                            </li>
                                        </c:if>
                                        <c:if test="${actionItem.meeting != null }">
                                            <li>
                                                <div class="form_grid_12">
                                                    <label for="meetingId" class="field_title">Meeting</label>
                                                    <form:select id="meetingId" name="meetingId"
                                                                 path="meetingId">
                                                        <form:options items="${referenceData.meetingList}" />
                                                    </form:select>
                                                </div>
                                            </li>
                                        </c:if>
                                        <li>
                                            <div class="form_grid_12">
                                                <label for="dateCreated" class="field_title">Date Created</label>
                                                <div class="form_input">
                                                    <form:input type="text" name="dateCreated" id="dateCreated" path="dateCreated"
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
                                                <label for="status" class="field_title">Status</label>
                                                <div class="form_input">
                                                    <form:select id="status" name="status" path="status" class="mid">
                                                        <form:option value="Pending">--Choose Status--</form:option>
                                                        <form:options items="${referenceData.statusList}"/>
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
                                        <button type="submit" class="btn_small btn_blue" name="btnAction" value="Update"><span>Update</span></button>
                                        <button type="reset" class="btn_small btn_blue" name="btnAction" value="Reset"><span>Reset</span></button>
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