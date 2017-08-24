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
            	<span class="h_icon list_images"></span>
                    <h6>Action Items</h6>
                    <div class="c_actions">
                        <ul>
                            <li>
                                <a href="${pageContext.request.contextPath}/app/actionitems/xls" title="Export to XLS"> <img
                                        src="${pageContext.request.contextPath}/resources/images/table-tools/xls_hover.png" alt="Export to XLS">
                                </a>
                            </li>
                            <!--<li><a href="#" title="Export to PDF"> <img
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
								<th>Assigned To</th>
								<th>Date Created</th>
								<th>Due Date</th>
								<th>Status</th>
								<th>Priority</th>
								<th>Manage</th>
							</tr>
						</thead>
						<tbody>
						
						   <c:forEach var="actionitem" items="${actionItems}">
							<tr>
								<td><a href="${pageContext.request.contextPath}/app/actionitem/${actionitem.id}"><c:out value="${actionitem.summary}" /></a></td>
								<td><c:out value="${actionitem.id}" /></td>
								<td><c:out value="${actionitem.assignedTo.name}" /></td>
								<td><fmt:formatDate type="date" value="${actionitem.dateCreated}" /></td>
								<td><fmt:formatDate type="date" value="${actionitem.dueDate}" /></td>
							    <td><span class="badge_style b_${fn:toLowerCase(actionitem.status)}">${actionitem.status }</span></td>
							    <td><span class="badge_style b_${fn:toLowerCase(actionitem.priority)}">${actionitem.priority}</span></td>
							     <td>
                                    <span><a class="action-icons c-edit" href="${pageContext.request.contextPath}/app/edit-actionitem/${actionitem.id}" title="Edit">Edit</a></span><span><a class="action-icons c-approve" href="${pageContext.request.contextPath}/app/new-actionitem" title="Create">Create</a></span>
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