<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<div id="breadcrumb">
	<ul>
		<li><a href="${pageContext.request.contextPath}/app/groupdashboard">Home</a>
			<span> >> </span></li>
		<li><a href="${pageContext.request.contextPath}/app/artifacts">Artifacts</a>
			<span> >> </span></li>
		<li><a href="#" style="text-decoration: none;">Edit Risk</a></li>
	</ul>
</div>
<div class="content">
	<div class="grid_container">
		<div class="grid_12 full_block">
			<div class="widget_wrap">
				<div class="widget_top">
					<span class="h_icon list"></span>
					<h6>Risks</h6>
				</div>
				<div class="widget_content">
					<span class="subheader-title">Edit Risk</span>
					<form:form modelAttribute="risk" class="form_container left_label"
						action="${pageContext.request.contextPath}/app/edit-risk"
						method="post">
						<form:errors path="*" cssClass="errorblock" element="div" />
						<ul>
							<li>
								<fieldset>
									<legend>Risk Information</legend>
									<ul>
										<li>
											<div class="form_grid_12">
												<label for="riskSummary" class="field_title">Risk
													Summary</label>
												<div class="form_input">
													<form:input type="hidden" name="id" path="id" id="id"
														class='mid' />
													<form:input type="text" id="riskSummary" name="riskSummary"
														path="riskSummary" class="mid"></form:input>
												</div>
											</div>
										</li>
										<sec:authorize access="hasRole('ROLE_PROGRAM_MANAGER')">
											<li>
												<div class="form_grid_12">
													<label for="programId" class="field_title">Program</label>
													<div class="form_input">
														<form:select id="programId" name="programId"
															path="programId" class="mid">
															<form:options items="${referenceData.programList}" />
														</form:select>
													</div>
												</div>
											</li>
										</sec:authorize>

										<sec:authorize access="hasRole('ROLE_PROJECT_MANAGER')">
											<li>
												<div class="form_grid_12">
													<label for="projectId" class="field_title">Project</label>
													<div class="form_input">
														<form:select id="projectId" name="projectId"
															path="projectId" class="mid">
															<form:options items="${referenceData.projectList}" />
														</form:select>
													</div>
												</div>
											</li>
										</sec:authorize>
										<li>
											<div class="form_grid_12">
												<label for="likelihood" class="field_title">Likelihood</label>
												<div class="form_input">
													<span><form:radiobutton class="radio"
															id="likelihood" name="likelihood" path="likelihood"
															value="Low" />Low</span> <span><form:radiobutton
															class="radio" id="likelihood" name="likelihood"
															path="likelihood" value="Medium" />Medium</span> <span><form:radiobutton
															class="radio" id="likelihood" name="likelihood"
															path="likelihood" value="High" />High</span>
												</div>
											</div>
										</li>
										<li>
											 <div class="form_grid_12">
                                              <label for="impact" class="field_title">Impact</label>
                                               <div class="form_input">
                                                   <form:select id="impact" name="impact" path="impact" class="mid">
                                                   <form:option value="">--Choose Impacts--</form:option>
                                                   <form:options items="${referenceData.RiskImpactListPage}"  />
                                               </form:select>
                                             </div>
                                            </div>
										</li>
									 <li>
                                <div class="form_grid_12">
                                    <label for="mitigatingFactors" class="field_title">Mitigating factors</label>
                                    <div class="form_input">
                                        <form:select id="mitigatingFactors" name="mitigatingFactors" path="mitigatingFactors" class="mid">
                                            <form:option value="">--Choose Mitigating factors--</form:option>
                                            <form:options items="${referenceData.RiskMitigatingListPage}"  />
                                        </form:select>
                                    </div>
                                </div>
                            </li>
										<li>
			                                <div class="form_grid_12">
			                                    <label for="status" class="field_title">Status</label>
			                                    <div class="form_input">
			                                        <form:select id="status" name="status" path="status" class="mid">
			                                            <form:option value="">--Choose Status--</form:option>
			                                            <form:options items="${referenceData.statusList}"  />
			                                        </form:select>
			                                    </div>
			                                </div>
			                            </li>

										<li>
											<div class="form_grid_12">
												<label for="programId" class="field_title">Program</label>

												<div class="form_input">
													<form:select id="programId" name="programId"
														path="programId">
														<form:options items="${referenceData.programList}" />
													</form:select>
												</div>
											</div>
										</li>
										
										<li>
											<div class="form_grid_12">
												<label for="projectId" class="field_title">Project</label>

												<div class="form_input">
													<form:select id="projectId" name="projectId"
														path="projectId">
														<form:options items="${referenceData.projectList}" />
													</form:select>
												</div>
											</div>
										</li>
									</ul>
								</fieldset>
							</li>
						</ul>

						<ul>
							<li>
								<div class="form_grid_12">
									<button type="submit" class="btn_small btn_blue">
										<span>Update</span>
									</button>
									<button type="reset" class="btn_small btn_blue">
										<span>Reset</span>
									</button>
								</div>
							</li>
						</ul>
					</form:form>
				</div>
			</div>
		</div>
	</div>
</div>