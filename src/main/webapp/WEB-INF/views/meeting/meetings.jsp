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
            <a href="#" style="text-decoration: none;">Meetings</a>
        </li>
    </ul>
</div>
<div id="content">
    <div class="grid_container">
        <div class="grid_12">
            <div class="widget_wrap">
                <div class="widget_top">
                <c:if test="${not empty success}">
                        <div class="successblock"><spring:message code="${success}"></spring:message>
                        </div>
                    </c:if>
                    <!--  <span class="h_icon documents"></span>-->
                    <h6>Meetings</h6>
                      
                    <div class="c_actions">
                        <ul>
                            <li>
                                <a href="${pageContext.request.contextPath}/app/meetings/xls" title="Export to XLS"> <img
                                        src="${pageContext.request.contextPath}/resources/images/table-tools/xls_hover.png" alt="Export to XLS">
                                </a>
                                <span><a class="action-icons c-approve" href="${pageContext.request.contextPath}/app/new-programmeeting" title="Create"></a></span>
                        
                            </li>
                            <!--<li><a href="#" title="Export to PDF"> <img
                                    src="${pageContext.request.contextPath}/resources/images/table-tools/pdf_hover.png"
                                    alt="Export to PDF">
                            </a></li>  -->
                        </ul>
                    </div>
                </div>
                <div class="widget_content">
                    <table class="display data_tbl">
                        <thead>
                        <tr>
                            <th>ID</th>
                            <th>Title</th>
                            <th>Organizer</th>
                            <th>Date</th>
                            <th>Start Time</th>
                            <th>Duration (HRS)</th>
                            <th>Type</th>
                            <th>Project</th>
                            <th>Model</th>
                            <th>Status</th>
                            <th>Manage</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="meeting" items="${meetings}">
                            <tr>
                                <td>${meeting.id }</td>
                                 <c:if test="${not empty meeting.id}">
                                	<td><a href="meeting/${meeting.id }">${meeting.title }</a></td>
                                </c:if>
                                
                                 <c:if test="${empty meeting.id}">
                                	<td><a href="#" disabled="disabled">${meeting.title }</a></td>
                                </c:if>
                                
                                <td>${meeting.organizer.name }</td>
                                <td><fmt:formatDate type="date" value="${meeting.date}"/></td>
                                <td>${meeting.time}</td>
                                <td>${meeting.duration }</td>
                                <td>${meeting.typeString }</td>
                                <td>${meeting.project.name }</td>
                                <td>${meeting.program.name }</td>
                                <td>
                                    <c:if test="${not empty meeting.status}">
                                    <span class="badge_style b_${fn:toLowerCase(meeting.status)}">${meeting.status}</span></td>
                                </c:if>
                                <td style="white-space: nowrap;">
                                <c:if test="${not empty meeting.id}">
                                    <span><a class="action-icons c-edit" href="${pageContext.request.contextPath}/app/edit-meeting/${meeting.id}" title="Edit">Edit</a></span>
                                   <!--  <span><a class="action-icons c-delete" href="#" title="delete">Delete</a></span> -->
                                    <span><a class="action-icons c-approve" href="${pageContext.request.contextPath}/app/new-programmeeting" title="Create">Create</a></span>
                                </td>
                                </c:if>
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