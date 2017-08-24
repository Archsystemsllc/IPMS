<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<div class="content">
    <div class="grid_container">
        <div class="grid_12 full_block">
            <div class="widget_wrap">
                <div class="widget_top">
                    <span class="h_icon list_image"></span>
                    <h6>Issues Upload</h6>
                </div>
                <form:form modelAttribute="fileUpload" enctype="multipart/form-data" class="form_container left_label"
                           action="upload" method="post">
                    <div class="widget_content">
                        <h6>Issues Upload</h6>
                         <c:if test="${not empty message }">
				   <div>
								<h4 class="alert-heading"><c:out value="${message}" /></h4>
  								
				    </div>  
			    </c:if>
                        <form:errors path="*" cssClass="errorblock" element="div" />
                        <ul>                          
							  <li>
                                <div class="form_grid_12">
                                    <label for="fileData" class="field_title">Issues File</label>
                                    <div class="form_input">
                                        <form:input type="file" id="fileData" name="fileData" path="fileData" />
                                    </div>
                                </div>
                            </li>
                            <li>
                                <div class="form_grid_12">
                                    <div class="form_input">
                                        <button type="submit" class="btn_small btn_blue"><span id="sub">Upload</span></button>
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