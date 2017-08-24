
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>
<%@ taglib prefix="sec"
           uri="http://www.springframework.org/security/tags" %>
           
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
            <a href="#" style="text-decoration: none;">Project Report</a>
        </li>
    </ul>
</div>
<div id="content">
<div class="grid_container">
<div class="grid_8">
    <div class="widget_wrap">
        <div class="widget_top">
            <span class="h_icon documents"></span>
            <h6>Project Report</h6>
            <form id="select_project" name="select_project" method="post"
						action="${pageContext.request.contextPath}/app/projectreport">
						<label for="projectId">Select Project</label>
						<select id="projectId" name="projectId">
							<c:forEach items="${projects}" var="project">
								<option value="${project.id}" ${project.id == projectId ? 'selected="selected"' : ''}>${project.name}</option>
							</c:forEach>
						</select>
						<input type="submit" value="Select" />
					</form>
            <div class="c_actions">
                <ul>
                    <li>
                      <c:if test="${not empty projects}">
                      		<a href="${pageContext.request.contextPath}/app/projectreport/xls?projectId=${projectId}"
                           	class='btn btn-mini btn-square tip' title="Export to XLS">
                            	<img src="${pageContext.request.contextPath}/resources/images/table-tools/xls_hover.png"
                                 alt="">
                        	</a> 
                        </c:if>
                    </li>
                  <!--  <li>
                        <a href="#" class='btn btn-mini btn-square tip' title="Export to PDF">
                            <img src="${pageContext.request.contextPath}/resources/images/table-tools/pdf_hover.png"
                                 alt="">
                        </a>
                    </li> --> 
                </ul>
            </div>
        </div>


        <div class="grid_12">
            <div class="widget_wrap">
                <div class="widget_top">
                    <span class="h_icon list_images"></span>
                    <h6>Issues</h6>

                </div>
                <div class="widget_content">
                    <table class="display data_tbl">
                        <thead>
                        <tr>
                            <th>ID</th>
                            <th>Name</th>
                            <th>Description</th>
                            <th>Status</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="issue" items="${issues}">
                            <tr>
                                <td><c:out value="${issue.id}"/></td>
                                <td><c:out value="${issue.name}"/></td>
                                <td><c:out value="${issue.description}"/></td>
                                <td><span class="badge_style b_${fn:toLowerCase(issue.status)}">${issue.status }</span></td>
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
                    <h6 title="Action Items under Selected Project">Action Items List</h6>
                </div>
                <div class="widget_content">
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

                                <td><c:out value="${actionitem.id}"/></td>
                                <td><c:out value="${actionitem.summary}"/></td>
                                <td><c:out value="${actionitem.assignedTo.name}"/></td>
                                <td><span class="badge_style b_${fn:toLowerCase(actionitem.status)}">${actionitem.status }</span></td>
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
                    <h6 title="Lessons Learned under the selected Project">Lessons Learned</h6>
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

                                <td><c:out value="${lessonsLearned.id}"/></td>
                                <td><c:out value="${lessonsLearned.summary}"/></td>

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
                    <h6 title="Risks Under the Selected Project">Risks</h6>
                </div>
                <div class="widget_content">
                    <table class="display data_tbl">
                        <thead>
                        <tr>

                            <th title="Risk Id">ID</th>
                            <th title="Risk Summary">Summary</th>

                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="risk" items="${risks}">
                            <tr>

                                <td><c:out value="${risk.id}"/></td>
                                <td><c:out value="${risk.riskSummary}"/></td>

                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
        <span class="clear"></span>

    </div>
    <span class="clear"></span>
</div>


<div class="grid_4">
    <div class="widget_wrap">
        <div class="widget_top">
            <h3>Statistics </h3>
        </div>
        <div class="widget_content">
            <table class="display" style="border: 1px solid #E1E1E1;">
                <thead>
                <tr>

                    <th>Type</th>
                    <th>Open and In Progress / Total(%)</th>

                </tr>
                </thead>
                <tbody>
                <tr>
                    <td>Issues Resolution Rate</td>
                    <td><c:out value="${issueResolutionRate}"/></td>
                </tr>
                <tr>
                    <td>Action Items Resolution Rate</td>
                    <td><c:out value="${actionItemResolutionRate}"/></td>
                </tr>
                <tr>
                    <td>Risks Mitigation Rate</td>
                    <td>%</td>
                </tr>

                </tbody>
            </table>

        </div>
        <br/><br/>

        <div class="widget_content">
            <table class="display" style="border: 1px solid #E1E1E1;">

                <tbody>
                <tr>
                    <td>Number of Issues</td>
                    <td>${fn:length(issues)}</td>
                </tr>
                <tr>
                    <td>Number of Action Items</td>
                    <td>${fn:length(actionItems)}</td>
                </tr>
                <tr>
                    <td>Number of Lessons Learned</td>
                    <td>${fn:length(lessonsLearned)}</td>
                </tr>
                <tr>
                    <td>Number of Risks</td>
                    <td>${fn:length(risks)}</td>
                </tr>
                <tr>
                    <td>Number of Meetings</td>
                    <td>${fn:length(meetings)}</td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>
    <%--</div>--%>
</div>
</div>
</div>