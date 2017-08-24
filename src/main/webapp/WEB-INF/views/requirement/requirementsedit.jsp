<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<div id="breadcrumb">
    <ul>
        <li>
            <a href="${pageContext.request.contextPath}/app/groupdashboard">Home</a> <span> >> </span>
        </li>
        <li>
            <a href="${pageContext.request.contextPath}/app/requirements">Requirements</a> <span> >> </span>
        </li>
        <li>
            <a href="#" style="text-decoration: none;">Edit Requirement</a>
        </li>
    </ul>
</div>
<div class="content">
    <div class="grid_container">
        <div class="grid_12 full_block">
            <div class="widget_wrap">
                <div class="widget_top">
                    <span class="h_icon list"></span>
                    <h6>Requirement</h6>
                </div>
                <div class="widget_content">
                    <span class="subheader-title">Edit Requirement</span>
                    <form:form modelAttribute="requirement" class="form_container left_label"
                               action="../edit-requirement" method="post">
                        <form:errors path="*" cssClass="errorblock" element="div"/>
                        <ul>
                            <li>
                                <fieldset>
                                    <legend>Requirement Information</legend>
                                    <ul>
                                        <li>
                                            <div class="form_grid_12">
                                                <label class="field_title">ID</label>
                                                <div class="form_input">
                                                    <span class="uneditable-input mid">${requirement.id }</span>
                                                    <form:input type="hidden" id="id" name="id" path="id"></form:input>
                                                </div>
                                            </div>
                                        </li>
                                        <li>
                                            <div class="form_grid_12">
                                                <label for="type" class="field_title">Type</label>
                                                <div class="form_input">
                                                    <form:select id="type" name="riskLevel" path="type" class="mid">
                                                        <form:options items="${referenceData.typeList}"  />
                                                    </form:select>
                                                </div>
                                            </div>
                                        </li>
                                        <li>
                                            <div class="form_grid_12">
                                                <label for="description" class="field_title">Requirement Statement</label>
                                                <div class="form_input">
                                                    <form:textarea id="description" name="description" path="description" rows="5" cols="30" />
                                                </div>
                                            </div>
                                        </li>
                                        <li>
                                            <div class="form_grid_12">
                                                <label for="priority" class="field_title">Priority</label>
                                                <div class="form_input">
                                                    <form:select id="priority" name="priority" path="priority" class="mid">
                                                        <form:options items="${referenceData.priorityList}"  />
                                                    </form:select>
                                                </div>
                                            </div>
                                        </li>
                                        <li>
                                            <div class="form_grid_12">
                                                <label for="riskLevel" class="field_title">Risk Level</label>
                                                <div class="form_input">
                                                    <form:select id="riskLevel" name="riskLevel" path="riskLevel" class="mid">
                                                        <form:options items="${referenceData.riskLevelList}"  />
                                                    </form:select>
                                                </div>
                                            </div>
                                        </li>
                                        <li>
                                            <div class="form_grid_12">
                                                <label for="riskDescription" class="field_title">Risk Description</label>
                                                <div class="form_input">
                                                    <form:textarea id="riskDescription" name="riskDescription" path="riskDescription" rows="5" cols="30" />
                                                </div>
                                            </div>
                                        </li>
                                    </ul>
                                </fieldset>
                            </li>
                            <li>
                                <div class="form_grid_12">
                                    <div class="form_input">
                                        <button type="submit" class="btn_small btn_blue"><span>Update</span></button>
                                        <button type="reset" class="btn_small btn_blue"><span>Reset</span></button>
                                    </div>
                                </div>
                            </li>
                        </ul>
                    </form:form>
                </div>
            </div>
        </div>
    </div>
</div>