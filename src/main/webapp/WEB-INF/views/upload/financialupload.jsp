<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<style>
 .required:after { content:" *"; }
</style>
<div class="content">
    <div class="grid_container">
        <div class="grid_12 full_block">
            <div class="widget_wrap">
                <div class="widget_top">
                    <span class="h_icon list_image"></span>
                    <h6>Financial Upload</h6>
                </div>
                <form:form modelAttribute="fileUpload" enctype="multipart/form-data" class="form_container left_label"
                           action="uploadfinancial" method="post">
                    <div class="widget_content">
					<span class="subheader-title">Financial Upload</span>
                         <c:if test="${not empty message }">
				 			 <div>
								<h4 class="errorblock"><c:out value="${message}" /></h4>
  							 </div>  
			   			 </c:if>
			   			 <c:if test="${not empty issuerequirederror}">
							<div class="errorblock">
								<spring:message code="${issuerequirederror}"></spring:message>
							</div>
						</c:if>
						<c:if test="${not empty meetingrequirederror}">
							<div class="errorblock">
								<spring:message code="${meetingrequirederror}"></spring:message>
							</div>
						</c:if>
			   			 <c:if test="${not empty fileUploadSuccess}">
							<div class="errorblock">
								<spring:message code="${fileUploadSuccess}"></spring:message>
							</div>
						 </c:if>
					     <c:if test="${not empty fileUploadError}">
							<div class="errorblock">
								<spring:message code="${fileUploadError}"></spring:message>
							</div>
						 </c:if>
                         <form:errors path="*" cssClass="errorblock" element="div" />
                         <ul> 
                         <li>
                                <div class="form_grid_12">
                                    <label for="projectId" class="field_title required">Project</label>
                                    <div class="form_input">
                                        <form:select id="projectId" name="projectId" path="projectId" class="mid">
                                            <form:options items="${referenceData.currentUserProjectlist}"  />
                                        </form:select>
                                    </div>
                                </div>
                            </li>
                      		<li>
                                <div class="form_grid_12">
                                    <label for="fileData" class="field_title required">WBS File</label>
                                    <div class="form_input">
                                        <form:input type="file" id="fileData" name="fileData" path="fileData" />
                                    </div>
                                </div>
                            </li>
                          	  <li>
                                <div class="form_grid_12">
                                    <label for="fileData" class="field_title required">Organization Category File</label>
                                    <div class="form_input">
                                        <form:input type="file" id="fileData" name="fileData" path="fileData" />
                                    </div>
                                </div>
                            </li>                     
							  <li>
                                <div class="form_grid_12">
                                    <label for="fileData" class="field_title required">Forecast File</label>
                                    <div class="form_input">
                                        <form:input type="file" id="fileData" name="fileData" path="fileData" />
                                    </div>
                                </div>
                            </li>
                            <li>
                                <div class="form_grid_12">
                                    <div class="form_input">
                                        <button type="submit" class="btn_small btn_blue"><span id="sub">Submit</span></button>
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