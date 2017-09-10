<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<script src="${pageContext.request.contextPath}/resources/js/jquery-1.10.2.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/jquery-ui-1.10.4.custom.js"></script>		
<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.5.0/Chart.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/Chart.bundle.js"></script>
<script>

$(function(){
  $.datepicker.setDefaults(
    $.extend($.datepicker.regional[''])
  );
  $('.datepicker').datepicker();
});
</script>
<style>
select#reportIds {
    max-width: 200px;
    min-width: 200px;
    width: 50px !important;
}
</style>
<div id="breadcrumb">
    <ul>
        <li>
            <a href="${pageContext.request.contextPath}/app/groupdashboard">Home</a> <span> >> </span>
        </li>
        <li>
            <a href="#">Meetings Management</a> <span> >> </span>
        </li>
        <li>
            <a href="#" style="text-decoration: none;">Search Meetings</a>
        </li>
    </ul>
</div>
<div class="content">
	<div class="grid_container">
		<div class="grid_12 full_block">
			<div class="widget_wrap">
				<div class="widget_top">
					<span class="h_icon list_image"></span>
					<h6>Search Meetings</h6>
				</div>
				<form:form modelAttribute="searchParam" class="form_container left_label"
					action="${pageContext.request.contextPath}/app/meetingresults"
					method="post">
					
					<div class="widget_content">
						<form:errors path="*" cssClass="errorblock" element="div" />
						<ul>

							 <li>
                                <div class="form_grid_12">
                                    <label for="privileges" class="field_title">Projects</label>
                                    <div class="form_input" w>
                                        <form:select mulitiple="true" id="projectIds" name="projectIds" path="projectIds" size="${fn:length(referenceData.projectList)}">
										<form:options items="${referenceData.projectList}"  />
									</form:select></div>
                                </div>
                            </li>
							
							<li>
								<div class="form_grid_12">
                                    <label for="name" class="field_title">Subject</label>
                                    <div class="form_input">
                                        <form:input type="text" id="subject" name="subject" path="subject" ></form:input>
                                    </div>
                                </div>
							</li>
							<li>
                                <div class="form_grid_12">
                                    <label for="dueDate" class="field_title">Start Date</label>
                                    <div class="form_input">
                                        <form:input type="text" name="startDate" id="startDate" path="startDate" class='datepicker'></form:input>
                                    </div>
                                </div>
                            </li>
                            <li>
                                <div class="form_grid_12">
                                    <label for="dueDate" class="field_title">End Date</label>
                                    <div class="form_input">
                                        <form:input type="text" name="endDate" id="endDate" path="endDate" class='datepicker'></form:input>
                                    </div>
                                </div>
                            </li>
                            <li>
                                <div class="form_grid_12">
                                    <button type="submit" name="action" value="submit" class="btn_small btn_blue"><span>Search Meetings</span></button>
                                </div>
                            </li>
                        </ul>
						</ul>
					</div>
				</form:form>
			</div>
		</div>
	</div>
</div>