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
            <a href="#" style="text-decoration: none;">Meeting Minutes</a>
        </li>
    </ul>
    <br>
    <ul><li><a href="${pageContext.request.contextPath}/app/meetingminutesadd">Create Meeting Minutes</a></li></ul>
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
                    <h6>Meeting Minutes</h6>
                    <div class="c_actions">
                        <ul>
                            <li>
                                <a href="${pageContext.request.contextPath}/app/meetingminutesadd" title="Export to XLS"> <img
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
                    <table class="display data_tbl">
                        <thead>
                        <tr>
                            <th>Name</th>
                            <th>Scribe</th>     
                            <th>Discussion</th>
                            <th>Start time</th>
                            <th>End time</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="meetingminutes" items="${meetingminutes}">
                            <tr>
                                <td>${meetingminutes.name }</td>
                                <td>${meetingminutes.scribe }</td>
                                <td>${meetingminutes.discussion }</td>
                                <td>${meetingminutes.startTime }</td>
                                <td>${meetingminutes.endTime }</td>
                                <td style="white-space: nowrap;">
                                    <span><a class="action-icons c-edit" href="${pageContext.request.contextPath}/app/edit-meeting/${meeting.id}" title="Edit">Edit</a></span>
                                   <!--  <span><a class="action-icons c-delete" href="#" title="delete">Delete</a></span> -->
                                    <span><a class="action-icons c-approve" href="${pageContext.request.contextPath}/app/meetingminutesadd" title="Create">Create</a></span>
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