<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<div id="breadcrumb">
	<ul>
		<li><a href="${pageContext.request.contextPath}/app/groupdashboard">Home</a>
			<span> >> </span></li>
		<li><a href="#" style="text-decoration: none;">Models</a></li>
	</ul>
</div>
<div id="content">
	<div class="grid_container">
		<div class="grid_12">
			<div class="widget_wrap">
				<div class="widget_top">
					<span class="h_icon documents"></span>
					<h6>Models</h6>
					<div class="c_actions">
						<ul>
							<li><a
								href="${pageContext.request.contextPath}/app/projects/xls"
								title="Export to XLS"> <img style="width: 30px; height: 30px; margin-top:5px"
									src="${pageContext.request.contextPath}/resources/images/table-tools/xls_hover.png"
									alt="Export to XLS">
							</a><a
								href="${pageContext.request.contextPath}/app/upload"
								title="Import from MS Project"> <img style="width: 20px; height: 20px; margin-bottom:4px"
									src="${pageContext.request.contextPath}/resources/images/table-tools/Microsoft_Project_icon.png"
									alt="Import from MS Project">
							</a></li>
							<!--<li><a href="#" title="Export to PDF"> <img
									src="${pageContext.request.contextPath}/resources/images/table-tools/pdf_hover.png"
									alt="Export to PDF">
							</a></li>  -->
						</ul>
					</div>
				</div>
				<div class="widget_content">
					<c:if test="${not empty success}">
						<div class="successblock">
							<spring:message code="${success}"></spring:message>
						</div>
					</c:if>
					<table class="display data_tbl">
						<thead>
							<tr>

							<tr>
								<th title="Project Name">Model</th>
								<th title="Manager Name">Manager Name</th>
									<th title="Model Assigned to Project">Vertical Group</th>									
									<th title="No of Issues"># of Issues</th>
									<th title="No of Risks"># of Risks</th>
									<th>Start Date</th>
									<th>End Date</th>
									<th>Manage</th>
								</tr>
							
                        </thead>
                        <tbody>
                        <c:forEach var="project" items="${projects}">
                            <c:if test="${not project.active }">
                                <c:set var="linkcolor"
										value="activeobjectlink" />
                            </c:if>
                            <c:if test="${project.active }">
                                <c:set var="linkcolor" value="" />
                            </c:if>
                            <tr>
                                <td><a class="${linkcolor }"
										href="project/${project.id}">${project.name }</a></td>
                               
                                <td><a class="${linkcolor }">${project.managerName }</a></td>
                                <td><a class="${linkcolor }">${project.program.name }</a></td>
                                <td><a class="${linkcolor }">${fn:length(project.issues)}</a></td>
                                <td><a class="${linkcolor }">${fn:length(project.risks)}</a></td>
                                <td><a class="${linkcolor }"><fmt:formatDate
												type="date" value="${project.startDate }" /></a></td>
                                <td><a class="${linkcolor }"><fmt:formatDate
												type="date" value="${project.endDate }" /></a></td>
                                <td>
                                    <span><a
											class="action-icons c-edit"
											href="${pageContext.request.contextPath}/app/edit-project/${project.id}"
											title="Edit">Edit</a></span><span><a
											class="action-icons c-approve"
											href="${pageContext.request.contextPath}/app/new-project"
											title="Create">Create</a></span>
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