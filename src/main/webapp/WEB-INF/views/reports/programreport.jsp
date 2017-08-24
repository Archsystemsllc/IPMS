<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
	
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
        <li>
            <a href="${pageContext.request.contextPath}/app/groupdashboard">Home</a> <span> >> </span>
        </li>
        <li>
            <a href="#" style="text-decoration: none;">Model Report</a>
        </li>
    </ul>
</div>
<div id="content">
	<div class="grid_container">
		<div class="grid_12">
			<div class="widget_wrap">
				<div class="widget_top">
					<span class="h_icon documents"></span>
					<h6>Model Report</h6>
					
					
				</div>
				<form id="select_program" name="select_program" method="post"
						action="${pageContext.request.contextPath}/app/programreport">
						<label for="programId">Select Program</label>
						<select id="programId" name="programId">
							<c:forEach items="${programs}" var="program">
								<option value="${program.id}" ${program.id == programId ? 'selected="selected"' : ''}>${program.name}</option>
							</c:forEach>
						</select>
						<input type="submit" value="Select" />
					</form>
					<div class="c_actions">
                <ul>
                    <li>
                   	   <c:if test="${not empty programs}">
                       		<a href="${pageContext.request.contextPath}/app/programreport/xls"
                           		class='btn btn-mini btn-square tip' title="Export to XLS">
                            	<img src="${pageContext.request.contextPath}/resources/images/table-tools/xls_hover.png"
                                 	alt="">
                        	</a>
                        </c:if>
                    </li>   
                   <!--<li>
                        <a href="#" class='btn btn-mini btn-square tip' title="Export to PDF">
                            <img src="${pageContext.request.contextPath}/resources/images/table-tools/pdf_hover.png"
                                 alt="">
                        </a>
                    </li> --> 
                </ul>
            </div>
				<c:if test="${not empty projects}">

					<div class="grid_12">
						<div class="widget_wrap">
							<div class="widget_top" id="table">
								<span class="h_icon list_images"></span>
								<h6 title="Projects Under the selected programs">Projects</h6>

							</div>
							<div class="widget_content">
								<table class="display data_tbl" id="table">
									<thead>
										<tr>
											<th title="Project Id">ID</th>
											<th title="Project Name">Name</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="project" items="${projects}">
											<c:if test="${not project.active }">
												<c:set var="linkcolor" value="activeobjectlink" />
											</c:if>
											<c:if test="${project.active }">
												<c:set var="linkcolor" value="" />
											</c:if>
											<tr>
												<td><a class="${linkcolor }">${project.id }</a></td>
												<td><a class="${linkcolor }"
													href="project/${project.id}">${project.name }</a></td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
						</div>
					</div>
					<span class="clear"></span>

					<div class="grid_12">
						<div class="widget_wrap">
							<div class="widget_top" id="table">
								<span class="h_icon list_images"></span>
								<h6 title="Issues of Selected Program">Issues</h6>

							</div>
							<div class="widget_content">
								<table class="display data_tbl" id="table">
									<thead>
										<tr>
										    <th title="Issue Id">ID</th>
											<th title="Issue Name">Name</th>
											<th title="Issue Description">Description</th>
											<th title="Issue Status"> Status</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="issue" items="${issues}">
											<tr>
											    <td><c:out value="${issue.id}" /></td>
												<td><c:out value="${issue.name}" /></td>
												<td><c:out value="${issue.description}" /></td>
												<td><span
													class="badge_style b_${fn:toLowerCase(issue.status)}">${issue.status
														}</span></td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
						</div>
					</div>
					<span class="clear"></span>

					<div class="grid_12">
						<div class="widget_wrap">
							<div class="widget_top" id="title">
								<span class="h_icon documents"></span>
								<h6 title="Action Items Under the Program Selected">Action Items List</h6>
							</div>
							<div class="widget_content">
								<%--<h3>My Action Items</h3>
                    <p>
                        Cras erat diam, consequat quis tincidunt nec, eleifend a turpis. Aliquam ultrices feugiat metus, ut imperdiet erat mollis at. Curabitur mattis risus sagittis nibh lobortis vel.
                    </p>--%>
								<table class="display data_tbl" id="table">
									<thead>
										<tr>

											<th title="Action Item Id">ID</th>
											<th title="Action Item Summary">Summary</th>
											<th title="Action Item Assignee">Assigned To</th>
											<th title="Action Item Status">Status</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="actionitem" items="${actionItems}">
											<tr>

												<td><c:out value="${actionitem.id}" /></td>
												<td><c:out value="${actionitem.summary}" /></td>
												<td><c:out value="${actionitem.assignedTo.name}" /></td>
												<td><span
													class="badge_style b_${fn:toLowerCase(actionitem.status)}">${actionitem.status
														}</span></td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
						</div>
					</div>
					<span class="clear"></span>

					<div class="grid_12">
						<div class="widget_wrap">
							<div class="widget_top" id="table">
								<span class="h_icon documents"></span>
								<h6 title="Lessons Learned Under the Selected Program">Lessons Learned</h6>
							</div>
							<div class="widget_content">
								<table class="display data_tbl" id="table">
									<thead>
										<tr>
											<th title="Lessons Learned Id">ID</th>
											<th title="Lessons Learned Summary">Summary</th>

										</tr>
									</thead>
									<tbody>
										<c:forEach var="lessonsLearned" items="${lessonsLearned}">
											<tr>

												<td><c:out value="${lessonsLearned.id}" /></td>
												<td><c:out value="${lessonsLearned.summary}" /></td>

											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
						</div>
					</div>
					<span class="clear"></span>


					<div class="grid_12">
						<div class="widget_wrap">
							<div class="widget_top" id="table">
								<span class="h_icon documents"></span>
								<h6 title="Risks Under the selected Program">Risks</h6>
							</div>
							<div class="widget_content">
								<table class="display data_tbl" id="table">
									<thead>
										<tr>

											<th title="Risk Id">ID</th>
											<th title="Risk Summary">Summary</th>

										</tr>
									</thead>
									<tbody>
										<c:forEach var="risk" items="${risks}">
											<tr>

												<td><c:out value="${risk.id}" /></td>
												<td><c:out value="${risk.riskSummary}" /></td>

											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
						</div>
					</div>
					<span class="clear"></span>
				</c:if>
				<c:if test="${empty projects}">
					<h3>No Data Available</h3>
				</c:if>
			</div>
			<span class="clear"></span>
		</div>
	</div>
</div>