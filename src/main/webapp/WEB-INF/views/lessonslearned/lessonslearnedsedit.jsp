<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<div id="breadcrumb">
    <ul>
        <li>
            <a href="${pageContext.request.contextPath}/app/groupdashboard">Home</a> <span> >> </span>
        </li>
        <li>
            <a href="${pageContext.request.contextPath}/app/artifacts">Artifacts</a> <span> >> </span>
        </li>
        <li>
            <a href="#" style="text-decoration: none;">Edit Lessons Learned</a>
        </li>
    </ul>
</div>
<div class="content">
    <div class="grid_container">
        <div class="grid_12 full_block">
            <div class="widget_wrap">
                <div class="widget_top">
                    <span class="h_icon list"></span>
                    <h6>Edit Lessons Learned</h6>
                </div>
                <div class="widget_content">
                    <%-- <span class="subheader-title">Edit Lessons Learned : <c:out value="${lessonsLearned.summary }" /></span> --%>
                    <form:form modelAttribute="lessonsLearned" class="form_container left_label"
                               action="../edit-lessonslearned" method="post">
                        <form:input type="hidden" id="id" name="id" path="id"></form:input>
                        <form:errors path="*" cssClass="errorblock" element="div"/>
                        <ul>
                            <li>
                                <fieldset>
                                    <legend>Lessons Learned Information</legend>
                                    <ul>
                                        <li>
                                            <div class="form_grid_12">
                                                <label for="summary" class="field_title">Summary</label>
                                                <div class="form_input">
                                                    <form:textarea path="summary" name="summary" id="summary" rows="2"></form:textarea>
                                                </div>
                                            </div>
                                        </li>
                                        <li>
                                            <div class="form_grid_12">
                                                <label for="impact" class="field_title">Impact</label>
                                                <div class="form_input">
                                                    <form:textarea path="impact" name="impact" id="impact" rows="3"></form:textarea>
                                                </div>
                                            </div>
                                        </li>
                                        <li>
                                            <div class="form_grid_12">
                                                <label for="recommendation" class="field_title">Recommendation</label>
                                                <div class="form_input">
                                                    <form:textarea path="recommendation" name="recommendation" id="recommendation" rows="4"></form:textarea>
                                                </div>
                                            </div>
                                        </li>
                                        <li>
                                            <div class="form_grid_12">
                                                <label for="successFactors" class="field_title">Success factors</label>
                                                <div class="form_input">
                                                    <form:textarea path="successFactors" name="successFactors" id="successFactors" rows="6"></form:textarea>
                                                </div>
                                            </div>
                                        </li>
                                        <li>
                                            <div class="form_grid_12">
                                                <label for="areasOfImprovement" class="field_title">Areas Of Improvement</label>
                                                <div class="form_input">
                                                    <form:textarea path="areasOfImprovement" name="areasOfImprovement" id="areasOfImprovement" rows="6"></form:textarea>
                                                </div>
                                            </div>
                                        </li>
                                        <li>
                                            <div class="form_grid_12">
                                                <label for="issueId" class="field_title">Issue</label>
                                                <div class="form_input">
                                                    <form:select id="issueId" name="issueId" path="issueId" class="mid">
                                                        <form:options items="${referenceData.issueList}" />
                                                    </form:select>
                                                </div>
                                            </div>
                                        </li>
                                        <li>
                                    <div class="form_grid_12">
                                        <label for="meetingId" class="field_title">Meeting</label>
                                        <div class="form_input">
                                            <form:select id="meetingId" name="meetingId" path="meetingId" class="mid">
                                                <form:options items="${referenceData.meetingList}" />
                                            </form:select>
                                        </div>
                                    </div>
                                </li>
                
                           <li>
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