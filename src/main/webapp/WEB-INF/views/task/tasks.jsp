<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<div id="breadcrumb">
    <ul>
        <li>
            <a href="${pageContext.request.contextPath}/app/groupdashboard">Home</a> <span> >> </span>
        </li>
        <li>
            <a href="#" style="text-decoration: none;">Tasks</a>
        </li>
    </ul>
</div>
<div id="content">
    <div class="grid_container">
        <div class="grid_12">
            <div class="widget_wrap">
                <div class="widget_top">
                    <span class="h_icon documents"></span>
                    <h6>Tasks</h6>
                    <div class="c_actions">
                        <ul>
                            <li>
                                <a href="${pageContext.request.contextPath}/app/tasks/xls" title="Export to XLS"> <img
                                        src="${pageContext.request.contextPath}/resources/images/table-tools/xls_hover.png" alt="Export to XLS">
                                </a>
                              
                                <a class="action-icons c-approve" href="${pageContext.request.contextPath}/app/new-task" title="Create"></a>
                           
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
                            <th>Name</th>
                            <th>Vertical Group Name</th>
                            <th>Project Name</th>
                            <th>Created By</th>
                            <th>Assigned To</th>
                            <th>Date Due</th>
                            <th>Status</th>
                            <th>Manage></th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="task" items="${tasks}">
                            <tr>
                            
                                <td><a href="task/${task.id}">${task.name}</a></td>
                                <td>${task.program.name}</td>
                                 <td>${task.project.name}</td>
                                <td>${task.createdBy.name}</td>
                                <td>${task.assignedTo.name}</td>
                                <td><fmt:formatDate type="date" value="${task.dueDate}" /></td>
                                <td><span class="badge_style b_${fn:toLowerCase(task.status)}">${task.status }	</span></td>
                                <td>
                                    <span><a class="action-icons c-edit" href="${pageContext.request.contextPath}/app/edit-task/${task.id}" title="Edit">Edit</a></span><span><a class="action-icons c-approve" href="${pageContext.request.contextPath}/app/new-task" title="Create">Create</a></span>
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