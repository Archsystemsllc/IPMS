<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<script>

	function chooseReports() {
		searchParam.reportTypeId[0].selected = true;
		document.getElementById("reportIds").innerHTML = "";
		searchParam.submit();
	}

	function chooseReportTypes() {
		document.getElementById("reportIds").innerHTML = "";
		searchParam.submit();
	}
	
	function switchAction() {
		searchParam.action="${pageContext.request.contextPath}/app/financialreport";
		searchParam.submit();
	}

	
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
            <a href="#">Financial Management</a> <span> >> </span>
        </li>
        <li>
            <a href="#" style="text-decoration: none;">Financial Report</a>
        </li>
    </ul>
</div>
<div class="content">
	<div class="grid_container">
		<div class="grid_12 full_block">
			<div class="widget_wrap">
				<div class="widget_top">
					<span class="h_icon list_image"></span>
					<h6>Financial Report</h6>
				</div>
				<form:form modelAttribute="searchParam" class="form_container left_label"
					action="${pageContext.request.contextPath}/app/choosefinancialreport"
					method="post">
					<form:hidden path="inceptionStartDate"/>
					<form:hidden path="inceptionEndDate"/>
					
					<div class="widget_content">
						<form:errors path="*" cssClass="errorblock" element="div" />
						<ul>

							<li>
								<div class="form_grid_12">
									<label for="projectId" class="field_title">Project</label>

									<div class="form_input">
										<c:if test="${empty task.project }">
											<form:select id="projectId" name="projectId"
												path="projectId" class="mid" onchange="chooseReports();">
												<form:options items="${referenceData.projectList}" />
											</form:select>
										</c:if>

									</div>
								</div>
							</li>
							
							<li>
								<div class="form_grid_12">
									<label for="projectId" class="field_title">Report Type</label>
									<div class="form_input">
										<form:select id="reportTypeId" name="reportTypeId"
											path="reportTypeId" class="mid" onchange="chooseReportTypes();">
											<form:options items="${referenceData.reportTypes}" />
										</form:select>
									</div>
								</div>
							</li>
							
							 <li>
                                <div class="form_grid_12">
                                    <label for="privileges" class="field_title">Selected Report(s)</label>
                                    <div class="form_input" w>
                                        <form:select mulitiple="true" id="reportIds" name="reportIds" path="reportIds" size="${fn:length(referenceData.reportIds)}">
										<form:options items="${referenceData.reportIds}"  />
									</form:select></div>
                                </div>
                            </li>
                            <ul>
                            <li>
                                <div class="form_grid_12">
                                    <button type="button" name="action" value="submit" class="btn_small btn_blue" onclick="switchAction()"><span>View Report</span></button>
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