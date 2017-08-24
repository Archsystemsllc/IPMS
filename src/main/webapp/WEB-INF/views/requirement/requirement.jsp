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
            <a href="${pageContext.request.contextPath}/app/requirements">Requirements</a> <span> >> </span>
        </li>
        <li>
            <a href="#" style="text-decoration: none;">Requirement</a>
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
                    <form action="../edit-requirement/${requirement.id}" class="form_container left_label">
                        <span class="subheader-title">Requirement Information</span>
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
                                                  <span class="uneditable-input mid">${requirement.requirementTypeString}</span>  
                                                </div>
                                            </div>
                                        </li>
                                        <li>
                                            <div class="form_grid_12">
                                                <label for="description" class="field_title">Requirement Statement</label>
                                                <div class="form_input">
                                                 <span class="uneditable-input mid">${requirement.description}</span>
                                                 </div>
                                            </div>
                                        </li>
                                        <li>
                                            <div class="form_grid_12">
                                                <label for="priority" class="field_title">Priority</label>
                                                <div class="form_input">
                                                    <span class="uneditable-input mid">${requirement.priorityString}</span>
                                                </div>
                                            </div>
                                        </li>
                                        <li>
                                            <div class="form_grid_12">
                                                <label for="riskLevel" class="field_title">Risk Level</label>
                                                <div class="form_input">
                                                    <span class="uneditable-input mid">${requirement.riskLevelString}</span>
                                                </div>
                                            </div>
                                        </li>
                                        <li>
                                            <div class="form_grid_12">
                                                <label for="riskDescription" class="field_title">Risk Description</label>
                                                <div class="form_input">
                                                    <span class="uneditable-input mid">${requirement.riskDescription}</span>
                                                </div>
                                            </div>
                                        </li>
                                    </ul>
                        <ul>
                            <li>
                                <div class="form_grid_12">
                                    <a href="../edit-requirement/${requirement.id}"><button class="btn_small btn_blue">Edit Requirement</button></a>
                                </div>
                            </li>
                        </ul>
                        </form>

                    <div class="widget_wrap tabby">
                        <div class="widget_top">
                            <div id="widget_tab" style="float:left;">
                                <ul>
                                    <li><a href="#tab1">Comments</a></li>
                                    <li><a href="#tab2">Revision History</a></li>
                                </ul>
                            </div>
                        </div>
                        <div class="widget_content">
                            <div id="tab1">
                                <!-- <a style="align: right" class="btn btn-primary"
                                   data-toggle="modal" href="#myModal">Add Comment</a>
                                <div class="modal hide" id="myModal">
                                    <div class="modal-header">
                                        <button type="button" class="close" data-dismiss="modal">x</button> -->
                                        <h3>Add Comment</h3>
                                    </div>
                                    <script type="text/javascript">
                                        function submitform() {
                                            if (document.commentForm.onsubmit &&
                                                    !document.commentForm.onsubmit()) {
                                                return;
                                            }
                                            document.commentForm.submit();
                                        }
                                    </script>
                                    <form:form modelAttribute="comment" id="commentForm" class="form_container left_label"
                                               action="../new-comment" method="post">
                                        <div class="modal-body">
                                            <form:input type="hidden" value="${comment.requirement.id }"
                                                        name="requirementId"
                                                        path="requirementId"></form:input>
                                            <form:textarea name="text" path="text"
                                                           cssClass="span12 input-square" rows="5" cols="200"/>

                                        </div>
                                        <div class="modal-footer">
                                            <button class="btn_small btn_blue" type="submit">Add</button>
                                        </div>
                                    </form:form>
                                </div>
                                <br/> <br/>

                                <ul class="messages">
                                    <c:set var="usr" value="2" scope="session"/>
                                    <c:forEach var="comment" items="${requirement.comments}">
                                        <c:if test="${usr=='1'}">
                                            <c:set var="usr" value="2" scope="session"/>
                                        </c:if>
                                        <c:if test="${usr=='2'}">
                                            <c:set var="usr" value="1" scope="session"/>
                                        </c:if>
                                        <li class="user${usr}">
                                            <div class="info">
                                                <span class="arrow"></span>
												<div class="detail">
												<span class="sender"> <strong><c:out
                                                        value='${comment.principal.name }'/></strong> says:
												</span> <span class="time"><c:out
                                                        value='${comment.timeElapsed }'/></span>
                                                </div>
                                                <div class="message">
                                                    <p><c:out value='${comment.text }'/></p>
                                                </div>
                                            </div>
                                        </li>
                                    </c:forEach>
                                </ul>
                            </div>
                            <div id="tab2">
                                <table class="display data_tbl">
                                    <thead>
                                    <tr>
                                        <th>Date</th>
                                        <th>User</th>
                                        <th>Manage></th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach var="revision" items="${requirement.revisions}">
                                        <tr>
                                            <td><fmt:formatDate type="date" value='${revision.updatedAt }' /></td>
                                            <td>${revision.principal.name }</td>
                                            <td> ${revision.text} </td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
  