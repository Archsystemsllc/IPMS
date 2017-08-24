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
            <a href="${pageContext.request.contextPath}/app/privileges">Privileges</a> <span> >> </span>
        </li>
        <li>
            <a href="#" style="text-decoration: none;">Edit Privilege</a>
        </li>
    </ul>
</div>
<div class="content">
	<div class="grid_container">
        <div class="grid_12 full_block">
            <div class="widget_wrap">
                <div class="widget_top">
                    <span class="h_icon list"></span>
                    <h6>Privilege</h6>
                </div>
				 <div class="widget_content">
                    <h6>Edit Privilege : <c:out value='${privilege.name}' /></h6>
					<form:form modelAttribute="privilege" class="form_container left_label"
						action="${pageContext.request.contextPath}/app/edit-privilege" method="post">
						<form:errors path="*" cssClass="errorblock" element="div" />
						<ul>
                            <li>
						 <fieldset>
                                    <legend>Privilege Information</legend>
                                    
                                   
                                    <ul>
                                    <li>
                                <div class="form_grid_12">
                                    <label for="id" class="field_title">Privilege Name</label>
                                    <div class="form_input">
                                        <form:input type="text" id="name" name="name" path="name" ></form:input>
                                        <form:input type="hidden" id="id" name="id" path="id"></form:input>
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
                                    <button type="submit" class="btn_small btn_blue"><span>Update</span></button>
                                    <button type="reset" class="btn_small btn_blue"><span>Reset</span></button>
                                </div>
                            </li>
                        </ul>
					</form:form>
				</div>
			</div>
		</div>
	</div>
</div>