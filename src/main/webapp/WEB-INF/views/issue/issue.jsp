<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<div id="breadcrumb">
    <ul>
        <li>
            <a href="${pageContext.request.contextPath}/app/groupdashboard">Home</a> <span> >> </span>
        </li>
        <li>
            <a href="${pageContext.request.contextPath}/app/artifacts">Artifacts</a> <span> >> </span>
        </li>
        <li>
            <a href="#" style="text-decoration: none;">Issue</a>
        </li>
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
                    <form action="../edit-issue/${issue.id}" class="form_container left_label">
                        <span class="subheader-title">Issue Information</span>
                        <ul>
                            <li>
                                <div class="form_grid_12">
                                    <label class="field_title">ID</label>
                                    <div class="form_input">
                                    	<c:if test="${issue.source =='IPMS'}">
                                        	<span class="uneditable-input mid">${issue.id }</span>
                                       	</c:if>
                                    	<c:if test="${issue.source =='JIRA'}">
                                        	<span class="uneditable-input mid">${issue.jiraId }</span>
                                       	</c:if>
                                    </div>
                                </div>
                            </li>
                            <li>
                                <div class="form_grid_12">
                                    <label class="field_title">Reported By</label>
                                    <div class="form_input">
                                        <span class="uneditable-input mid">${issue.assignee.name }</span>
                                    </div>
                                </div>
                            </li>
                            <li>
                                <div class="form_grid_12">
                                    <label class="field_title">Assigned To</label>
                                    <div class="form_input">
                                        <span class="uneditable-input mid">${issue.assigned.name }</span>
                                    </div>
                                </div>
                            </li>
                            <li>
                                <div class="form_grid_12">
                                    <label class="field_title">Date Reported</label>
                                    <div class="form_input">
                                        <span class="uneditable-input mid"><fmt:formatDate type="date" value="${issue.dateReported}" /></span>
                                    </div>
                                </div>
                            </li>
                            <li>
                                <div class="form_grid_12">
                                    <label class="field_title">Due Date</label>
                                    <div class="form_input">
                                        <span class="uneditable-input mid"><fmt:formatDate type="date" value="${issue.dueDate}" /></span>
                                    </div>
                                </div>
                            </li>
                            <li>
                                <div class="form_grid_12">
                                    <label class="field_title">Project</label>
                                    <div class="form_input">
                                        <span class="uneditable-input mid">${issue.project.name }</span>
                                    </div>
                                </div>
                            </li>
                            <li>
                                <div class="form_grid_12">
                                    <label class="field_title">Priority</label>
                                    <div class="form_input">
                                        <span class="uneditable-input mid">${issue.priority }</span>
                                    </div>
                                </div>
                            </li>
                            <li>
                                <div class="form_grid_12">
                                    <label class="field_title">Status</label>
                                    <div class="form_input">
                                        <span class="uneditable-input mid">${issue.status }</span>
                                    </div>
                                </div>
                            </li>
                        </ul>
                        <ul>
                            <li>
                                <div class="form_grid_12">
                                    <a href="../edit-issue/${issue.id}"><button class="btn_small btn_blue">Edit Issue</button></a>
                                </div>
                            </li>
                        </ul>

                    </form>

                    <div class="widget_wrap tabby">
                        <div class="widget_top">
                            <div id="widget_tab" style="float:left;">
                                <ul>
                                    <li><a href="#tab1">Action Items</a></li>
                                    <li><a href="#tab2">Lessons Learned</a></li>
                                     <li><a href="#tab3">Revision History</a></li>
                                </ul>
                            </div>
                        </div>
                        <div class="widget_content">
                            <div id="tab1">
                                <table class="display data_tbl">
                                    <thead>
                                    <tr>
                                        <th>ID</th>
                                        <th>Summary</th>
                                        <th>Assigned To</th>
                                        <th>Date Due</th>
                                        <th>Status</th>
                                        <th>Manage</th>
                                    </tr>
                                    </thead>

                                    <tbody>
                                    <c:forEach var="actionitem" items="${issue.actionItems}">
                                        <tr>
                                            <td>${actionitem.id}</td>
                                            <td><a href="${pageContext.request.contextPath}/app/actionitem/${actionitem.id}"><c:out value="${actionitem.summary}" /></a></td>
                                            <td>${actionitem.assignedTo.name}</td>
                                            <td><fmt:formatDate type="date" value="${actionitem.dueDate}" /></td>
                                            <td><span class="badge_style b_${fn:toLowerCase(actionitem.status)}">${actionitem.status}</span></td>
                                            <td style="white-space: nowrap;">
                                                <span><a class="action-icons c-edit" href="${pageContext.request.contextPath}/app/edit-actionitem/${actionitem.id}" title="Edit">Edit</a></span><span><a class="action-icons c-delete" href="#" title="delete">Delete</a></span><span><a class="action-icons c-approve" href="${pageContext.request.contextPath}/app/new-actionitem" title="Create">Create</a></span>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                    <tfoot>
                                    <tr>
                                        <td colspan="6"><a class="activeobjectlink" href="${pageContext.request.contextPath}/app/new-issueactionitem/${issue.id}"><button
                                            class="btn_small btn_blue">Add Action Item</button></a></td>
                                    </tr>
                                    </tfoot>
                                </table>
                            </div>
                            <div id="tab2">
                                <table class="display data_tbl">
                                    <thead>
                                    <tr>
                                        <th>ID</th>
                                        <th>Summary</th>
                                        <th>Impact</th>
                                        <th>Recommendation</th>
                                        <th>Manage></th>
                                    </tr>
                                    </thead>

                                    <tbody>
                                    <c:forEach var="lessonsLearned" items="${issue.lessonsLearneds}">
                                        <tr>
                                            <td>${lessonsLearned.id}</td>
                                            <td><a href="lessonslearned/${lessonsLearned.id}">${lessonsLearned.summary}</a></td>
                                            <td>${lessonsLearned.impact}</td>
                                            <td>${lessonsLearned.recommendation}</td>
                                            <td style="white-space: nowrap;">
                                                <span><a class="action-icons c-edit" href="${pageContext.request.contextPath}/app/edit-lessonslearned/${lessonsLearned.id}" title="Edit">Edit</a></span><span><a class="action-icons c-delete" href="#" title="delete">Delete</a></span><span><a class="action-icons c-approve" href="${pageContext.request.contextPath}/app/new-lessonslearned" title="Create">Create</a></span>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                    <tfoot>
                                    <tr>
                                        <td colspan="5"><a class="activeobjectlink" href="${pageContext.request.contextPath}/app/new-lessonslearned"><button
                                                class="btn_small btn_blue">Add Lessons Learned</button></a></td>
                                    </tr>
                                    </tfoot>
                                    
                                </table>
                            </div>
                            <div id="tab3">
                                <table class="display data_tbl">
                                    <thead>
                                    <tr>
                                        <th>Date</th>
                                        <th>User</th>
                                        <th>Manage></th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach var="revision" items="${issue.revisions}">
                                        <tr>
                                            <td><fmt:formatDate type="date" value='${revision.updatedAt}' /></td>
                                            <td>${revision.principal.name }</td>
                                            <td> ${revision.text} </td>
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
    </div>
</div>