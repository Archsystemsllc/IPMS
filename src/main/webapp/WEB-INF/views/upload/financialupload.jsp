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
                    <h6>EVM Upload</h6>
                </div>
                <form:form modelAttribute="financialsUpload" enctype="multipart/form-data" class="form_container left_label"
                           action="uploadfinancial" method="post">
                    <div class="widget_content">
					<span class="subheader-title">EVM Upload</span>
                         <c:if test="${not empty message }">
				 			 <div>
								<h4 class="errorblock"><c:out value="${message}" /></h4>
  							 </div>  
			   			 </c:if>
			   			 <c:if test="${not empty projectrequirederror}">
							<div class="errorblock">
								<spring:message code="${projectrequirederror}"></spring:message>
							</div>
						</c:if>
			   			 <c:if test="${not empty forecastUploadSuccess}">
							<div class="successblock">
								<spring:message code="${forecastUploadSuccess}"></spring:message>
							</div>
						</c:if>
						<c:if test="${not empty forecastUploadError}">
							<div class="errorblock">
								<spring:message code="${forecastUploadError}"></spring:message>
							</div>
						</c:if>
						<c:if test="${not empty orgcatUploadSuccess}">
							<div class="successblock">
								<spring:message code="${orgcatUploadSuccess}"></spring:message>
							</div>
						</c:if>
						<c:if test="${not empty orgcatUploadError}">
							<div class="errorblock">
								<spring:message code="${orgcatUploadError}"></spring:message>
							</div>
						</c:if>
			   			<c:if test="${not empty wbsUploadSuccess}">
							<div class="successblock">
								<spring:message code="${wbsUploadSuccess}"></spring:message>
							</div>
						</c:if>
						<c:if test="${not empty wbsUploadError}">
							<div class="errorblock">
								<spring:message code="${wbsUploadError}"></spring:message>
							</div>
						</c:if>
						<c:if test="${not empty fileUploadError}">
							<div class="errorblock">
								<spring:message code="${fileUploadError}"></spring:message>
							</div>
						 </c:if>
			   			 <c:if test="${not empty fileUploadSuccess}">
							<div class="successblock">
								<spring:message code="${fileUploadSuccess}"></spring:message>
							</div>
						 </c:if>
					     <c:if test="${not empty fileUploadNull}">
							<div class="errorblock">
								<spring:message code="${fileUploadNull}"></spring:message>
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
                                    <label for="forecastFileData" class="field_title">Forecast File</label>
                                    <div class="form_input">
                                        <form:input type="file" id="forecastFileData" name="forecastFileData" path="forecastFileData" />
                                    </div>
                                </div>
                            </li>
                            <li>
                                <div class="form_grid_12">
                                    <label for="organizationcategoryFileData" class="field_title">Organization Category File</label>
                                    <div class="form_input">
                                        <form:input type="file" id="organizationcategoryFileData" name="organizationcategoryFileData" path="organizationcategoryFileData" />
                                    </div>
                                </div>
                            </li>
                            <li>
                                <div class="form_grid_12">
                                    <label for="wbsFileData" class="field_title">Work Breakdown Structure File</label>
                                    <div class="form_input">
                                        <form:input type="file" id="wbsFileData" name="wbsFileData" path="wbsFileData" />
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