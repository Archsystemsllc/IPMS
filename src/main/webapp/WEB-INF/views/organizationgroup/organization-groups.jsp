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
		<li><a href="${pageContext.request.contextPath}/app/dashboard">Home</a>
			<span> >> </span></li>
		<li><a href="#" style="text-decoration: none;">Groups</a></li>
	</ul>
</div>
<div id="content">
<div class="grid_container">
	
	<div class="grid_12" id="programDId">
					<div class="widget_wrap">
						<div class="widget_top">
							<span class="h_icon documents"></span>
							<h6>Groups</h6>
						</div>
						<div class="widget_content">
							<c:if test="${not empty success}">
								<div class="successblock">
									<spring:message code="${success}"></spring:message>
								</div>
							</c:if>
							<table class="display data_tbl" id="dashboard">
								<thead>
									<tr>
										
										<th title="Group Name">Group's Name</th>
										<th title="Director">Group's Director</th>
										<th title="Number of Programs"># of Programs</th>
										<th title="Program Start Date">Start Date</th>
										<th title="Program End Date">End Date</th>
										<th title="Edit or Create Program">Action</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="group" items="${organizationGroups}">
										<tr>
											
											<!-- <td>
												 <a href="${pageContext.request.contextPath}/app/organization-group/${group.id}">
													<c:out value='${group.name}' />
												</a>
											</td>-->
											<td>
											<div class="grid_12">
													<div class="collapsible_widget">
														<div class="c_widget_top inactive">
															<span class="c_h_icon"></span> <a class="${linkcolor }"
																href="${pageContext.request.contextPath}/app/organization-group/${group.id}">
																<h6>
																	<c:out value='${group.name}' />
																</h6>
															</a>
														</div>
														<div class="widget_content c_content">
															<c:forEach var="program" items="${group.programs}">
																<a
																	href="${pageContext.request.contextPath}/app/program/<c:out value='${program.id }' />">
																	<span class="c_inner_content"> <c:out
																			value='${program.name }' />
																</span> <span class="clear"></span>
																</a>
															</c:forEach>
														</div>
													</div>
												</div>
											</td>
											<td><c:out
														value='${group.director}' /></td>
											<td><c:out
														value='${fn:length(group.programs)}' /></td>
											<td><fmt:formatDate
														type="date" value="${group.startDate}" /></td>
											<td><fmt:formatDate
														type="date" value="${group.endDate}" /></td>
											<td>
											<span><a class="action-icons c-edit"
													href="${pageContext.request.contextPath}/app/organization-group/${group.id}"
													title="Edit">Edit</a></span><span><a
													class="action-icons c-approve"
													href="${pageContext.request.contextPath}/app/new-organization-group"
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



