<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<script src="${pageContext.request.contextPath}/resources/js/jquery-1.10.2.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/jquery-ui-1.10.4.custom.js"></script>		
		<script>
		
$(function(){
  $.datepicker.setDefaults(
    $.extend($.datepicker.regional[''])
  );
  $('.datepicker').datepicker();
});

	</script>
<div id="breadcrumb">
    <ul>
        <li>
            <a href="${pageContext.request.contextPath}/app/groupdashboard">Home</a> <span> >> </span>
        </li>
        <li>
            <a href="${pageContext.request.contextPath}/app/projects">Projects</a> <span> >> </span>
        </li>
        <li>
            <a href="#" style="text-decoration: none;">New Project</a>
        </li>
    </ul>
</div>
<div class="content">
    <div class="grid_container">
        <div class="grid_12 full_block">
            <div class="widget_wrap">
                <div class="widget_top">
                    <span class="h_icon list_image"></span>
                    <h6>Project</h6>
                </div>
                <form:form modelAttribute="project" class="form_container left_label"
                           action="${pageContext.request.contextPath}/app/new-project" method="post">
                    <div class="widget_content">
                        <h6>Create New Project</h6>
                        <form:errors path="*" cssClass="errorblock" element="div" />
                          <c:if test="${not empty success}">
                        <div class="successblock"><spring:message code="${success}"></spring:message>
                        </div>
                        </c:if>
                          <c:if test="${not empty error}">
                        <div class="errorblock"><spring:message code="${error}"></spring:message>
                        </div>
                        </c:if>
                        <form:input type="hidden" name="id" path="id" ></form:input>
                        <ul>
                            <c:if test="${empty project.programId }">
                                <li>
                                    <div class="form_grid_12">
                                        <label for="programId" class="field_title">Program</label>
                                        <div class="form_input">
                                            <form:select id="programId" name="programId" path="programId">
                                                <form:options items="${referenceData.programList}"  />
                                            </form:select>
                                        </div>
                                    </div>
                                </li>
                            </c:if>
                            <c:if test="${not empty project.programId }">
                                <form:input type="hidden" id="programId" name="programId" path="programId" />
                            </c:if>

                            <li>
                                <div class="form_grid_12">
                                
                                    <label for="name" class="field_title">Name</label>
                                    <div class="form_input">

                                            <form:input type="text" id="name" name="name" path="name" ></form:input>
                                       
                                    </div>
                                </div>
                            </li>
                             <li>
                                <div class="form_grid_12">
                                
                                    <label for="managerName" class="field_title">Manager Name</label>
                                    <div class="form_input">

                                            <form:input type="text" id="managerName" name="managerName" path="managerName" ></form:input>
                                       
                                    </div>
                                </div>
                            </li>
                            <li>
                                <div class="form_grid_12">
                                                <label class="field_title">Description</label>
                                                <div class="form_input">
                                                    <form:textarea type="text" id="description" name="description" path="description" ></form:textarea>
                                                </div>
                                            </div>
                            </li>
                            
                             <li>
                                <div class="form_grid_12">
                                    <label for="name" class="field_title">JIRA Project Name</label>
                                    <div class="form_input">
                                      <form:select id="jiraProjectKey" name="jiraProjectKey" path="jiraProjectKey">
                                          <form:options items="${referenceData.jiraProjectList}"  />
                                      </form:select>
                                      </div>
                                </div>
                            </li>
                            <li>
                                <div class="form_grid_12">
                                    <label for="managerId" class="field_title">Manager</label>

                                    <div class="form_input">
                                        <form:select id="managerId" name="managerId" path="managerId">
                                            <form:options items="${referenceData.principalList}"  />
                                        </form:select>
                                    </div>
                                </div>
                            </li>
                            <li>
                                <div class="form_grid_12">
                                    <label for="startDate" class="field_title">Start Date</label>
                                    <div class="form_input">
                                        <form:input type="text" name="startDate" id="startDate" path="startDate" class='datepicker'></form:input>
                                    </div>
                                </div>
                            </li>
                            <li>
                                <div class="form_grid_12">
                                    <label for="endDate" class="field_title">End Date</label>

                                    <div class="form_input">
                                        <form:input type="text" name="endDate" id="endDate" path="endDate" class='datepicker'></form:input>
                                    </div>
                                </div>
                            </li>
                            <li>
                                <div class="form_grid_12">
                                    <div class="form_input">
                                        <button type="submit" class="btn_small btn_blue"><span>Submit</span></button>
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