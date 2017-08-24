<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<div class="content">
	  <div class="grid_container">
        <div class="grid_12">
            <div class="widget_wrap">
                <div class="widget_top">
                    <span class="h_icon documents"></span>
                    <h6>Issues</h6>
                    <div class="c_actions">
                        <ul>
                            <li>
                                <a href="${pageContext.request.contextPath}/app/issues/xls" title="Export to XLS"> <img
                                        src="${pageContext.request.contextPath}/resources/images/table-tools/xls_hover.png" alt="Export to XLS">
                                </a>
                               <a class="action-icons c-approve" href="${pageContext.request.contextPath}/app/new-issue" title="Create"></a>
                            </li>
                           <!--<li><a href="${pageContext.request.contextPath}/app/issues/pdf" title="Export to PDF"> <img
                                    src="${pageContext.request.contextPath}/resources/images/table-tools/pdf_hover.png"
                                    alt="Export to PDF">
                            </a></li>  --> 
                        </ul>
                    </div>
                </div>
				<div class="widget_content">
				<c:if test="${not empty success}">
                        <div class="successblock"><spring:message code="${success}"></spring:message>
                        </div>
                  </c:if>
                    <table class="display data_tbl">
                        <thead>
                        <tr>
								<th>Summary</th>
								<th>ID</th>
								<th>Project</th>
								<th>Reported By</th>
								<th>Assigned To</th>
								<th>Date Due</th>
								<th>Priority</th>
								<th>Status</th>	
								<th>Manage></th>							
							</tr>
						</thead>
						<tbody>
						   <c:forEach var="issue" items="${issues}">
							<tr>
								<td><a href="${pageContext.request.contextPath}/app/issue/${issue.id}"><c:out value="${issue.summary}" /></a></td>
								<td><c:out value="${issue.id}" /></td>
								<td><c:out value="${issue.project.name}" /></td>
								<td><c:out value="${issue.assignee.name}" /></td>
								<td><c:out value="${issue.assigned.name}" /></td>
								<td><fmt:formatDate type="date" value="${issue.dueDate}" /></td>
								<td><span class="badge_style b_${fn:toLowerCase(issue.priority)}">${issue.priority}</span></td>
							    <td><span class="badge_style b_${fn:toLowerCase(issue.status)}">${issue.status}</span></td>
							    <td>
                                    <span><a class="action-icons c-edit" href="${pageContext.request.contextPath}/app/edit-issue/${issue.id}" title="Edit">Edit</a></span><span><a class="action-icons c-approve" href="${pageContext.request.contextPath}/app/new-issue" title="Create">Create</a></span>
                                </td>						    
							</tr>
							</c:forEach>
						</tbody>
						</table>
				</div>
            </div>
        </div>
        <span class="clear"></span>
    </div>
</div>