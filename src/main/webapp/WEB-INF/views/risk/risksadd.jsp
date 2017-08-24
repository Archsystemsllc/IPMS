<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<div id="breadcrumb">
    <ul>
        <li>
            <a href="${pageContext.request.contextPath}/app/groupdashboard">Home</a> <span> >> </span>
        </li>
        <li>
            <a href="${pageContext.request.contextPath}/app/artifacts">Artifacts</a> <span> >> </span>
        </li>
        <li>
            <a href="#" style="text-decoration: none;">New Risk</a>
        </li>
    </ul>
</div>
<div class="content">
    <div class="grid_container">
        <div class="grid_12 full_block">
            <div class="widget_wrap">
                <div class="widget_top">
                    <span class="h_icon list_image"></span>
                    <h6>Risks</h6>
                </div>
                <form:form modelAttribute="risk" class="form_container left_label"
                           action="${pageContext.request.contextPath}/app/new-risk" method="post">
                    <div class="widget_content">
                        <span class="subheader-title">Create New Risk</span>
                        <form:errors path="*" cssClass="errorblock" element="div" />
                        <ul>
                            <li>
                                <div class="form_grid_12">
                                    <label for="riskSummary" class="field_title">Risk Summary</label>
                                    <div class="form_input">
                                        <form:textarea path="riskSummary" name="riskSummary" id="riskSummary" rows="2"></form:textarea>
                                    </div>
                                </div>
                            </li>
                            
                                <li>
                                    <div class="form_grid_12">
                                        <label for="programId" class="field_title">Program</label>
                                        <div class="form_input">
                                          <c:if test="${empty risk.program}">
                                            <form:select id="programId" name="programId" path="programId" class="mid">
                                                <form:options items="${referenceData.programList}" />
                                            </form:select>
                                            </c:if>
                                            <c:if test="${not empty risk.program}">
                                               <form:input type="hidden" id="programId" name="programId" path="programId" class="mid"></form:input>
                                                <span class="uneditable-input mid">${risk.program.name }</span>
                                            </c:if>
                                        </div>
                                    </div>
                                </li>
                            
                                <li>
                                    <div class="form_grid_12">
                                        <label for="projectId" class="field_title">Project</label>
                                        <div class="form_input">
                                            <c:if test="${empty risk.project}">
                                                <form:select id="projectId" name="projectId" path="projectId" class="mid">
                                                    <form:options items="${referenceData.projectList}" />
                                                </form:select>
                                            </c:if>
                                            <c:if test="${not empty risk.project}">
                                                <form:input type="hidden" id="projectId" name="projectId" path="projectId" class="mid"></form:input>
                                                <span class="uneditable-input mid">${risk.project.name }</span>
                                            </c:if>
                                        </div>
                                    </div>
                                </li>
                            
                            <li>
                                <div class="form_grid_12">
                                    <label for="likelihood" class="field_title">Likelihood</label>
                                    <div class="form_input">
                                        <span><form:radiobutton class="radio" id="likelihood" name="likelihood" path="likelihood" value="Low"/>Low</span>
                                        <span><form:radiobutton class="radio" id="likelihood" name="likelihood" path="likelihood" value="Medium"/>Medium</span>
                                        <span><form:radiobutton class="radio" id="likelihood" name="likelihood" path="likelihood" value="High"/>High</span>
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
                                    <div class="form_input">
                                        <button type="submit" class="btn_small btn_blue"><span>Create</span></button>
                                        <button type="reset" class="btn_small btn_blue"><span>Reset</span></button>
                                    </div>
                                </div>
                            </li>
                        </ul>

                    </div></form:form>
            </div>
        </div>
    </div>
</div>