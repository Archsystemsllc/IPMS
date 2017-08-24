<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<script src="${pageContext.request.contextPath}/resources/js/jquery-1.10.2.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/jquery-ui-1.10.4.custom.js"></script>		
		<script>
		
$(function(){
  $.datepicker.setDefaults(
    $.extend($.datepicker.regional[''])
  );
  $('.datepicker').datepicker();
});

	</script>
<div id="breadcrumb">
	<ul>
		<li><a href="${pageContext.request.contextPath}/app/groupdashboard">Home</a>
			<span> >> </span></li>
		<li><a href="${pageContext.request.contextPath}/app/programs">Programs</a>
			<span> >> </span></li>
		<li><a href="#" style="text-decoration: none;">Edit Program</a></li>
	</ul>
</div>
<div class="content">
	<div class="grid_container">
		<div class="grid_12 full_block">
			<div class="widget_wrap">
				<div class="widget_top">
					<span class="h_icon list"></span>
					<h6>Program</h6>
				</div>
				<div class="widget_content">
					<h6>
						Edit Program :
						<c:out value='${program.name}' />
					</h6>
					<form:form modelAttribute="program"
						class="form_container left_label"
						action="${pageContext.request.contextPath}/app/edit-program"
						method="post">
						<form:errors path="*" cssClass="errorblock" element="div" />
						<c:if test="${not empty success}">
							<div class="successblock">
								<spring:message code="${success}"></spring:message>
							</div>
						</c:if>
						<c:if test="${not empty error}">
							<div class="errorblock">
								<spring:message code="${error}"></spring:message>
							</div>
						</c:if>
						<form:input type="hidden" name="id" path="id"></form:input>
						<ul>
							<li>
								<fieldset>
									<legend>Program Information</legend>
									<%-- <c:if test="${program.active }"> --%>
									<ul>
										<li>
											<div class="form_grid_12">
												<label for="name" class="field_title">Name</label>

												<div class="form_input">
													<form:input type="text" id="name" name="name" path="name"></form:input>
												</div>
											</div>
										</li>
										<li>
											<div class="form_grid_12">
												<label class="field_title">Program Description</label>
												<div class="form_input">
													<form:textarea type="text" id="description"
														name="description" path="description"></form:textarea>
												</div>
											</div>
										</li>
										<li>
											<div class="form_grid_12">
												<label for="managerId" class="field_title">Manager</label>

												<div class="form_input">
													<form:select id="managerId" name="managerId"
														path="managerId">
														<form:options items="${referenceData.principalList}" />
													</form:select>
												</div>
											</div>
										</li>
										<li>
											<div class="form_grid_12">
												<label for="startDate" class="field_title">Start
													Date</label>
												<div class="form_input">
													<form:input type="text" name="startDate" id="startDate"
														path="startDate" class='datepicker'></form:input>
												</div>
											</div>
										</li>
										<li>
											<div class="form_grid_12">
												<label for="endDate" class="field_title">End Date</label>

												<div class="form_input">
													<form:input type="text" name="endDate" id="endDate"
														path="endDate" class='datepicker'></form:input>
												</div>
											</div>
										</li>
										<li>
											<div class="form_grid_12">
												<label class="field_title">Status</label>
												<div class="form_input">
													<c:if test="${program.active }">
														<span class="uneditable-input mid">Active</span>
													</c:if>
													<c:if test="${not program.active }">
														<span class="uneditable-input mid">In-Active</span>
													</c:if>
												</div>
											</div>
										</li>
										<li>
											<div class="form_grid_12">
												<label for="managerId" class="field_title">Group</label>

												<div class="form_input">
													<form:select id="organizationGroupId" name="organizationGroupId"
														path="organizationGroup.id">
														<form:option value="0">Remove </form:option>
														<c:forEach items="${referenceData.organizationGroupList}" var="group">
                                                           <c:if test="${group.id eq program.organizationGroup.id}">
                                                           	<form:option selected="selected"  value="${group.id}">${group.name} </form:option>
                                                           </c:if>
                                                           <c:if test="${group.id ne program.organizationGroup.id}">
                                                           	<form:option value="${group.id}">${group.name} </form:option>
                                                           </c:if>
                                                        </c:forEach>
													</form:select>
												</div>
											</div>
										</li>
									</ul>
									<%-- </c:if>
                                    <c:if test="${not program.active }">
                                    <li>
			                                <div class="form_grid_12">
			                                    <label class="field_title">ID</label>
			                                    <div class="form_input">
			                                        <span class="uneditable-input mid">${program.id }</span>
			                                    </div>
			                                </div>
			                            </li>
			                            <li>
			                                <div class="form_grid_12">
			                                    <label class="field_title">Name</label>
			                                    <div class="form_input">
			                                        <span class="uneditable-input mid">${program.name }</span>
			                                    </div>
			                                </div>
			                            </li>
			                            <li>
			                                <div class="form_grid_12">
			                                    <label class="field_title">Description</label>
			                                    <div class="form_input">
			                                        <span class="uneditable-input mid">${program.description }</span>
			                                    </div>
			                                </div>
			                            </li>
			                            <li>
			                                <div class="form_grid_12">
			                                    <label class="field_title">Start Date</label>
			                                    <div class="form_input">
			                                        <span class="uneditable-input mid"><fmt:formatDate type="date" value="${program.startDate}" /></span>
			                                    </div>
			                                </div>
			                            </li>
			                            <li>
			                                <div class="form_grid_12">
			                                    <label class="field_title">Due Date</label>
			                                    <div class="form_input">
			                                        <span class="uneditable-input mid"><fmt:formatDate type="date" value="${program.endDate}" /></span>
			                                    </div>
			                                </div>
			                            </li>
			                            <li>
			                                <div class="form_grid_6">
			                                    <label class="field_title">Manager</label>
			                                    <div class="form_input">
			                                        <span class="uneditable-input mid">${program.managerId}</span>
			                                    </div>
			                                </div>
			                            </li>
			                            <li>
			                                <div class="form_grid_12">
			                                    <label class="field_title">Status</label>
			                                    <div class="form_input">
			                                    <c:if test="${program.active }">
			                                        <span class="uneditable-input mid">${program.active}</span>
			                                    </c:if>
			                                    </div>
			                                </div>
			                            </li>
                                    </c:if> --%>

								</fieldset>
							</li>
						</ul>

						<ul>
							<li>
								<div class="form_grid_12">
									<c:if test="${program.active }">
										<button type="submit" class="btn_small btn_blue"
											name="btnAction" value="Deactivate">
											<span>Deactivate</span>
										</button>
									</c:if>
									<c:if test="${not program.active }">
										<button type="submit" class="btn_small btn_blue"
											name="btnAction" value="Activate">
											<span>Activate</span>
										</button>
									</c:if>
									<c:if test="${program.active }">
										<button type="submit" class="btn_small btn_blue"
											name="btnAction" value="End Program">
											<span>End Program</span>
										</button>
										<button type="submit" class="btn_small btn_blue"
											name="btnAction" value="Update">
											<span>Update</span>
										</button>
										<button type="reset" class="btn_small btn_blue"
											name="btnAction" value="Update">
											<span>Reset</span>
										</button>
									</c:if>
								</div>
							</li>
						</ul>
					</form:form>
				</div>
			</div>
		</div>
	</div>
</div>