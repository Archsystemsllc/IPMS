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
            <a href="#">Issue Management</a> <span> >> </span>
        </li>
        <li>
            <a href="#" style="text-decoration: none;">Search Issues</a>
        </li>
    </ul>
</div>
<div class="content">
	<div class="grid_container">
		<div class="grid_12 full_block">
			<div class="widget_wrap">
				<div class="widget_top">
					<span class="h_icon list_image"></span>
					<h6>Search Issues</h6>
				</div>
				<form:form modelAttribute="searchParam" class="form_container left_label"
					action="${pageContext.request.contextPath}/app/searchresults"
					method="post">
					
					<div class="widget_content">
						<form:errors path="*" cssClass="errorblock" element="div" />
						<ul>

							 <li>
                                <div class="form_grid_12">
                                    <label for="privileges" class="field_title">Projects</label>
                                    <div class="form_input" w>
                                        <form:select mulitiple="true" id="projectIds" name="projectIds" path="projectIds" size="5">
										<form:options items="${referenceData.projectList}"  />
									</form:select></div>
                                </div>
                            </li>
							
							<li>
                                <div class="form_grid_12">
                                    <label for="privileges" class="field_title">Assignees</label>
                                    <div class="form_input" w>
                                        <form:select mulitiple="true" id="principalIds" name="principalIds" path="principalIds" size="5">
										<form:options items="${referenceData.assignList}"  />
									</form:select></div>
                                </div>
                            </li>
							
							<li>
                                <div class="form_grid_12">
                                    <label for="privileges" class="field_title">Statuses</label>
                                    <div class="form_input" w>
                                        <form:select mulitiple="true" id="statuses" name="statuses" path="statuses" size="4">
										<form:options items="${referenceData.jirastatuses}"  />
									</form:select></div>
                                </div>
                            </li>
                            
                            <li>
                                <div class="form_grid_12">
                                    <button type="submit" name="action" value="submit" class="btn_small btn_blue"><span>Search Issue</span></button>
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