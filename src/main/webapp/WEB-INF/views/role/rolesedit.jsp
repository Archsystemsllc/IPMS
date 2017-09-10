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
            <a href="${pageContext.request.contextPath}/app/roles">Roles</a> <span> >> </span>
        </li>
        <li>
            <a href="#" style="text-decoration: none;">Edit Role</a>
        </li>
    </ul>
</div>
<div class="content">
	<div class="grid_container">
        <div class="grid_12 full_block">
            <div class="widget_wrap">
                <div class="widget_top">
                    <span class="h_icon list"></span>
                    <h6>Role</h6>
                </div>
				 <div class="widget_content">
				 <h6>Edit Role : <c:out value='${role.name}' /></h6>
					<form:form modelAttribute="role" class="form_container left_label"
						action="../edit-role" method="post">
						<form:errors path="*" cssClass="errorblock" element="div" />
						<ul>
                            <li>
						 <fieldset>
                                    <legend>Role Information</legend>
							<ul>
                                        <li>
                                            <div class="form_grid_12">
                                                <label for="id" class="field_title">ID</label>

                                                <div class="form_input">
                                                    <span class="uneditable-input input-xlarge input-square"><c:out value='${role.id }'/></span>
                                                    <form:input type="hidden" id="id" name="id"
                                                    path="id"></form:input>
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
                                    <label for="privileges" class="field_title">Privileges</label>
                                    <div class="form_input">
                                        <form:select mulitiple="true" id="privileges" name="privilegesIds" path="privilegesIds" size="${fn:length(referenceData.privilegeList)}">
										<form:options items="${referenceData.privilegeList}"  />
									</form:select></div>
                                </div>
                            </li>  </ul>
                                </fieldset>
                            </li>
                        </ul>		
							
							
										
						<ul>
                            <li>
                                <div class="form_grid_12">
                                    <button type="submit" name="action" value="submit" class="btn_small btn_blue"><span>Update</span></button>
                                    <button type="submit" name="action" value="reset" class="btn_small btn_blue"><span>Reset</span></button>
                                </div>
                            </li>
                        </ul>
					</form:form>
				</div>
			</div>
		</div>
	</div>
</div>