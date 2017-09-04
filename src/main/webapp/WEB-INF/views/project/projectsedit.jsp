<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
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
            <a href="#" style="text-decoration: none;">Edit Model</a>
        </li>
    </ul>
</div>
<div class="content">
    <div class="grid_container">
        <div class="grid_12 full_block">
            <div class="widget_wrap">
                <div class="widget_top">
                    <span class="h_icon list"></span>
                    <h6>Project</h6>
                    <form:errors path="*" cssClass="errorblock" element="div" />
                </div>
                <div class="widget_content">
                    <h6>Edit Project : <c:out value='${project.name}' /></h6>
                    <form:form modelAttribute="project" class="form_container left_label"
                               action="../edit-project" method="post">
                                <c:if test="${not empty error}">
                                            <div class="errorblock"><spring:message code="${error}"></spring:message>
                                            </div>
                                </c:if>
                        <form:errors path="*" cssClass="errorblock" element="div" />
                        <form:input type="hidden" name="id" path="id" ></form:input>
                        <ul>
                            <li>
                                <fieldset>
                                    <legend>Model Information</legend>
                                    <ul>
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
                                                <label for="name" class="field_title">Manager Name</label>

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
                                                <label for="name" class="field_title">Meeting EMail</label>
                                                <div class="form_input">
                                                 <form:input type="text" id="email" name="email" path="email" ></form:input>
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
                                    </ul>
                                </fieldset>
                            </li>
                        </ul>

                        <ul>
                            <li>
                                <div class="form_grid_12">
                                    <c:if test="${project.active}">
                                        <button type="submit" name="btnAction" class="btn_small btn_blue"  value="Deactivate"><span>Deactivate</span></button>
                                    </c:if>
                                    <c:if test="${not project.active}">
                                        <button type="submit" name="btnAction" class="btn_small btn_blue"  value="Activate"><span>Activate</span></button>
                                    </c:if>
                                    <button type="submit" name="btnAction" class="btn_small btn_blue" value="End Project"><span>End Model</span></button>
                                    <button type="submit" name="btnAction" class="btn_small btn_blue" value="Update"><span>Update</span></button>
                                    <button type="reset" name="btnAction" class="btn_small btn_blue" value="Reset"><span>Reset</span></button>
                                </div>
                            </li>
                        </ul>
                    </form:form>
                </div>
            </div>
        </div>
    </div>
</div>