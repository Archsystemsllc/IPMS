<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml"%>
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
            <a href="#" style="text-decoration: none;">Add Role</a>
        </li>
    </ul>
</div>
<div class="content">
	<div class="grid_container">
        <div class="grid_12 full_block">
            <div class="widget_wrap">
                <div class="widget_top">
                    <span class="h_icon list_image"></span>
                    <h6>Role</h6>
                </div>
				<form:form modelAttribute="role" class="form_container left_label"
						action="new-role" method="post">
						
						 <div class="widget_content">
                        <h6>Create New Role</h6>
                        <form:errors path="*" cssClass="errorblock" element="div" />
							
							<ul>
                            <li>
                                <div class="form_grid_12">
                                    <label for="name" class="field_title">Role Name</label>
                                    <div class="form_input">
                                        <form:input type="text" id="name" name="name" path="name" ></form:input>
                                    </div>
                                </div>
                            </li>
                            
                            <li>
                                <div class="form_grid_12">
                                    	<label for="privileges" class="control-label">Privileges</label>
                                    <div class="form_input">
                                       <form:select style="width:600px" mulitiple="true" class='cho' cssClass="cho" multiple="true" id="privilegesIds" name="privilegesIds" path="privilegesIds">
										<form:options items="${referenceData.privilegeList}"  />
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
							
							</ul></div>
					</form:form>
				</div>
			</div>
		</div>
	</div>
