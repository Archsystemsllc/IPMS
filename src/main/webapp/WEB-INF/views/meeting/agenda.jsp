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
            <a href="#" style="text-decoration: none;">Agendas</a>
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
                    <h6>Agendas</h6>
                    <div class="c_actions">
                        <ul>
                            <li>
                                <a href="${pageContext.request.contextPath}/app/new-agendameeting" title="Export to XLS"> <img
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
                         
								<form:form modelAttribute="meeting"
									class="form_container left_label"
									action="${pageContext.request.contextPath}/app/edit-meeting/${meeting.id}"
									method="get">

									<span class="subheader-title">Meeting Agenda</span>
									<table class="display data_tbl">
										<thead>
											<tr>
												<th>Agenda Topic</th>
												<th>Owner</th>
												<th>Description</th>
												<th>Actions</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach var="agendaItem" items="${meetingAgendaItem}">
												<tr>
													<td><a
														href="${pageContext.request.contextPath}/app/edit-meetingagenda/${agendaItem.id}">${agendaItem.topic
															}</a></td>
													<td>${agendaItem.meetingAgendaOwner.name }</td>
													<td>
													   <c:choose>
															<c:when test="${fn:length(agendaItem.description) gt 50}">
																<c:out
																	value="${fn:substring(agendaItem.description,0,50)}....."
																	escapeXml="false" />
															</c:when>
															<c:otherwise>
																<c:out value='${agendaItem.description }'
																	escapeXml="false" />
															</c:otherwise>
														</c:choose>
													 </td>
													<td><span><a class="action-icons c-edit"
															href="${pageContext.request.contextPath}/app/edit-meetingagenda/${meeting.id}"
															title="Edit">Edit</a></span><span><a
															class="action-icons c-approve"
															href="${pageContext.request.contextPath}/app/new-agendameeting"
															title="Create">Create</a></span></td>
												</tr>
											</c:forEach>

										</tbody>
									</table>

								</form:form>
							</div>

            </div>
        </div>
        <span class="clear"></span>
    </div>
</div>