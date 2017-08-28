<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<script type='text/javascript' src='https://www.google.com/jsapi'></script>
<script type='text/javascript'>
<script src= "http://code.jquery.com/jquery-1.8.3.js"></script>
<script src="http://code.jquery.com/ui/1.9.2/jquery-ui.js"></script>

<script type="text/javascript">
$(document).ready(function(){
 
    $('#table ul li a').append('<span></span>');
 
    $('#table ul li a').hover(
        function(){ 
            $(this).find('span').animate({opacity:'show', top: '-70'}, 'slow');
 
            var hoverTexts = $(this).attr('title');
             $(this).find('span').text(hoverTexts);
        },
 
        function(){ 
            $(this).find('span').animate({opacity:'hide', top: '-90'}, 'fast');
        }
    );
});
</script>
<div id="breadcrumb">
	<ul>
		<li><a href="${pageContext.request.contextPath}/app/groupdashboard">Home</a>
			<span> >> </span></li>
		<li><a href="#" style="text-decoration: none;">Models</a></li>
	</ul>
</div>
<sec:authorize access="hasRole('ROLE_PROJECT_MANAGER')">
<div id="content">
<div class="grid_container">
	<div class="grid_12">
			<div class="widget_wrap">
				<div class="widget_top">
					<span class="h_icon documents"></span>
					<h6>Models</h6>
					<div class="c_actions" id= "table">
						<ul>
							<li><a
								href="${pageContext.request.contextPath}/app/Models/xls"
								title="Export to XLS"> <img
									src="${pageContext.request.contextPath}/resources/images/table-tools/xls_hover.png"
									alt="Export to XLS">
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
					<table  id="table" class="display data_tbl">
						<thead>
							<tr>
								<th class="center" title="Model Id">ID</th>
								<th title="Model Names with project dropdown">Model Name</th>
								<th title="User Assigned to the Model">Manager</th>
								<!--<th title="No of Issues"># of Issues</th>-->
								<th title="Model Start Date">Start Date</th>
								<th title="Model End Date">End Date</th>
								<th title="Edit or Create Model">Action</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="modelIPMS" items="${models}">
								<c:if test="${not Model.active }">
									<c:set var="linkcolor" value="activeobjectlink" />
								</c:if>
								<c:if test="${modelIPMS.active }">
									<c:set var="linkcolor" value="" />
								</c:if>
								<tr>
									<td><a class="${linkcolor }"><c:out
												value='${modelIPMS.id}' /></a></td>
									<td>

										<div class="grid_12">
											<div class="collapsible_widget">
												<div class="c_widget_top inactive">
													<span class="c_h_icon"></span> <a class="${linkcolor }"
														href="${pageContext.request.contextPath}/app/model/${modelIPMS.id}">
														<h6>
															<c:out value='${modelIPMS.name}' />
														</h6>
													</a>
												</div>
												<div class="widget_content c_content">
													<c:forEach var="project" items="${modelIPMS.projects}">
														<a
															href="${pageContext.request.contextPath}/app/project/<c:out value='${project.id }' />">
															<span class="c_inner_content"> <c:out
																	value='${project.name }' />
														</span> <span class="clear"></span>
														</a>
													</c:forEach>
												</div>
											</div>
										</div>

									</td>
									<td><a class="${linkcolor }"><c:out
												value='${modelIPMS.manager.name}' /></a></td>
									<!--<td><a class="${linkcolor }"><c:out
												value='${fn:length(Model.issues)}' /></a></td>-->
									<td><a class="${linkcolor }"><fmt:formatDate
												type="date" value="${modelIPMS.startDate}" /></a></td>
									<td><a class="${linkcolor }"><fmt:formatDate
												type="date" value="${modelIPMS.endDate}" /></a></td>
									<td><span><a class="action-icons c-edit"
											href="${pageContext.request.contextPath}/app/edit-model/${modelIPMS.id}"
											title="Edit">Edit</a></span><span><a
											class="action-icons c-approve"
											href="${pageContext.request.contextPath}/app/new-Model"
											title="Create">Create</a></span></td>
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
</sec:authorize>


