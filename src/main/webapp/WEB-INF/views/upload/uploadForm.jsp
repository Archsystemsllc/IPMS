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
                    <h6>MS Project Integration</h6>
                </div>
                <form:form modelAttribute="fileUpload" enctype="multipart/form-data" class="form_container left_label"
                           action="upload" method="post">
                    <div class="widget_content">
                        <h6>MS Project Integration</h6>
                         <c:if test="${not empty message }">
				   <div>
								<h4 class="alert-heading"><c:out value="${message}" /></h4>
  								
				    </div>  
			    </c:if>
                        <form:errors path="*" cssClass="errorblock" element="div" />
                        <ul>                          
							  <li>
                                <div class="form_grid_12">
                                    <label for="fileData" class="field_title">File</label>
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
                        
                        
                        <c:if test="${not empty uploadItemVal}">
							<div class="widget_content">
							<ul>
                            <li>
						 	<fieldset>
                                    <legend>File Information</legend>
                                    <ul>
                                    <li>
                                <div class="form_grid_12">
                                    <div class="form_input">
                                        <button name="btnAction" type="submit" value="Import" class="btn_small btn_blue"><span>Import</span></button>
                                    </div>
                                </div>
                            </li>

                            <li>
                                <div class="form_grid_12">
                                    <label for="programId" class="field_title">Choose Program</label>
                                    <div class="form_input">
                                        <form:select id="programId" name="programId" path="programId">
                                            <form:options items="${uploadItemVal.programs}"  />
                                        </form:select>
                                    </div>
                                </div>
                            </li>
                            
                           <li>
                                <div class="form_grid_12">
                                    <label class="field_title">Name</label>
                                    <div class="form_input">
                                        <span class="uneditable-input mid">${uploadItemVal.name }</span>
                                    </div>
                                </div>
                            </li>
                            <li>
                                <div class="form_grid_12">
                                    <label class="field_title">StartDate</label>
                                    <div class="form_input">
                                        <span class="uneditable-input mid">${uploadItemVal.startDate }</span>
                                    </div>
                                </div>
                            </li>
                            <li>
                                <div class="form_grid_12">
                                    <label class="field_title">EndDate</label>
                                    <div class="form_input">
                                        <span class="uneditable-input mid">${uploadItemVal.endDate }</span>
                                    </div>
                                </div>
                            </li>
                            </ul>
							
							</fieldset>
							</li>
							</ul>
							</div>
                            </c:if>
                            
                            <c:if test="${not empty uploadItemVal.tasks}">
							<div class="widget_content">
							<ul>
                            <li>
						 	<fieldset>
                                    <legend>Task Information</legend>
                                    <table class="display" id="data_tbl_tools">
                        <thead>
                        <tr>
                            <th>Name</th>
                        </tr>
                        </thead>
                        <tbody>
                         <c:forEach var="task" items="${uploadItemVal.tasks}">
                           <tr>
										<td><c:out value="${task.name }" /></td>
									</tr>
                        </c:forEach>
                        </tbody>
                    </table>
							
							</fieldset>
							</li>
							</ul>
							</div>
                            </c:if>  
					</form:form>
						
                    </div>
                    </div>
            </div>
        </div> 