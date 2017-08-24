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
            <a href="${pageContext.request.contextPath}/app/requirements">Systems</a> <span> >> </span>
        </li>
        <li>
            <a href="#" style="text-decoration: none;">New System</a>
        </li>
    </ul>
</div>
<div class="content">
    <div class="grid_container">
        <div class="grid_12 full_block">
            <div class="widget_wrap">
                <div class="widget_top">
                    <span class="h_icon list_image"></span>
                    <h6>System</h6>
                </div>
                <form:form modelAttribute="requirement" class="form_container left_label"
                           action="${pageContext.request.contextPath}/app/new-requirement" method="post">
                    <div class="widget_content">
                        <span class="subheader-title">Create New System</span>
                        <form:errors path="*" cssClass="errorblock" element="div" />
                        <ul>
                        
                        	 <li>
                                <div class="form_grid_12">
                                
                                    <label for="name" class="field_title">System Name</label>
                                    <div class="form_input">

                                            <form:input type="text" id="name" name="name" path="name" ></form:input>
                                       
                                    </div>
                                </div>
                            </li>
                            
                            <li>
                                <div class="form_grid_12">
                                    <label for="type" class="field_title">Program</label>
                                    <div class="form_input">
                                        <form:select path="type" class="mid">
                                            <form:options items="${referenceData.programList}"  />
                                        </form:select>
                                    </div>
                                </div>
                            </li>
                            
                              <li>
                                <div class="form_grid_12">
                                    <label for="type" class="field_title">Project</label>
                                    <div class="form_input">
                                        <form:select path="riskLevel" class="mid">
                                            <form:options items="${referenceData.projectList}"  />
                                        </form:select>
                                    </div>
                                </div>
                            </li>
                            <li>
                                <div class="form_grid_12">
                                
                                    <label for="name" class="field_title">Stakeholder Name</label>
                                    <div class="form_input">

                                            <form:input type="text" id="riskDescription" name="riskDescription" path="riskDescription" ></form:input>
                                       
                                    </div>
                                </div>
                            </li>
                            <li>
                                <div class="form_grid_12">
                                    <label for="description" class="field_title">Description of Use</label>
                                    <div class="form_input">
                                        <form:textarea name="description" path="description" rows="5" cols="30" />
                                    </div>
                                </div>
                            </li>
                            <!--  <li>
                                <div class="form_grid_12">
                                    <label for="priority" class="field_title">Priority</label>
                                    <div class="form_input">
                                        <form:select path="priority" class="mid">
                                            <form:options items="${referenceData.priorityList}"  />
                                        </form:select>
                                    </div>
                                </div>
                            </li>
                            <li>
                                <div class="form_grid_12">
                                    <label for="riskLevel" class="field_title">Risk Level</label>
                                    <div class="form_input">
                                        <form:select path="riskLevel" class="mid">
                                            <form:options items="${referenceData.riskLevelList}"  />
                                        </form:select>
                                    </div>
                                </div>
                            </li>
                            <li>
                                <div class="form_grid_12">
                                    <label for="riskDescription" class="field_title">Risk Description</label>
                                    <div class="form_input">
                                        <form:textarea name="riskDescription" path="riskDescription" rows="5" cols="30" />
                                    </div>
                                </div>
                            </li>
                            <li>
                                <div class="form_grid_12">
                                    <label for="summary" class="field_title">Summary</label>
                                    <div class="form_input">
                                        <form:textarea name="summary" path="summary" rows="5" cols="30" />
                                    </div>
                                </div>
                            </li>-->
                            <li>
                                <div class="form_grid_12">
                                    <div class="form_input">
                                        <button type="submit" class="btn_small btn_blue"><span>Create</span></button>
                                        <button type="reset" class="btn_small btn_blue"><span>Reset</span></button>
                                    </div>
                                </div>
                            </li>
                        </ul>
                    </div>
                </form:form>
            </div>
        </div>
    </div>
</div>