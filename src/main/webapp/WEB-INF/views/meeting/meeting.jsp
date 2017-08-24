<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<div id="breadcrumb">
	<ul>
		<li><a
			href="${pageContext.request.contextPath}/app/groupdashboard">Home</a>
			<span> >> </span></li>
		<li><a href="${pageContext.request.contextPath}/app/meetings">Meetings</a>
			<span> >> </span></li>
		<li><a href="#" style="text-decoration: none;">Meeting Minutes</a></li>
	</ul>
</div>
<div class="content">
	<div class="grid_container">
		<div class="grid_12">
			<div class="widget_wrap">
				<div class="widget_top">
					<span class="h_icon list_image"></span>
					<h6>Meeting</h6>
					         
				</div>
				<div class="widget_content">
					<div class="widget_wrap tabby">	
						<div id="tab1">
							<div class="widget_content">
								<form:form modelAttribute="meeting"
									class="form_container left_label"
									action="${pageContext.request.contextPath}/app/edit-meeting/${meeting.id}"
									method="get">

									<ul>
										<li>
											<div class="form_grid_12">
												<label class="field_title">Title</label>
												<div class="form_input">
													<span class="uneditable-input mid">${meeting.title}</span>
												</div>
											</div>
										</li>

										<li>
											<div class="form_grid_12">
												<label class="field_title">Organizer</label>
												<div class="form_input">
													<span class="uneditable-input mid">${meeting.organizer.name}</span>
												</div>
											</div>
										</li>
										<li>
											<div class="form_grid_12">
												<label class="field_title">Date</label>
												<div class="form_input">
													<span class="uneditable-input mid">${meeting.date}</span>
												</div>
											</div>
										</li>
										<li>
											<div class="form_grid_12">
												<label class="field_title">Time</label>
												<div class="form_input">
													<span class="uneditable-input mid">${meeting.time}</span>
												</div>
											</div>
										</li>
										<li>
											<div class="form_grid_12">
												<label class="field_title">Type</label>
												<div class="form_input">
													<span class="uneditable-input mid">${meeting.typeString}</span>
												</div>
											</div>
										</li>
										<li>
											<div class="form_grid_12">
												<label class="field_title">Duration (HRS)</label>
												<div class="form_input">
													<span class="uneditable-input mid">${meeting.duration}</span>
												</div>
											</div>
										</li>
										<li>
											<div class="form_grid_12">
												<label class="field_title">Location</label>
												<div class="form_input">
													<span class="uneditable-input mid">${meeting.locationInfo}</span>
												</div>
											</div>
										</li>
										<li>
											<div class="form_grid_12">
												<label class="field_title">Other Info</label>
												<div class="form_input">
													<span class="uneditable-input mid">${meeting.otherInfo}</span>
												</div>
											</div>
										</li>
										<c:if test="${not empty(meeting.project) }">
											<li>
												<div class="form_grid_12">
													<label class="field_title">Project</label>
													<div class="form_input">
														<span class="uneditable-input mid">${meeting.project.name
															}</span>
													</div>
												</div>
											</li>
										</c:if>
										<c:if test="${not empty(meeting.program) }">
											<li>
												<div class="form_grid_12">
													<label class="field_title">Model</label>
													<div class="form_input">
														<span class="uneditable-input mid">${meeting.program.name
															}</span>
													</div>
												</div>
											</li>
										</c:if>
									</ul>
									<ul>
										<li>
											<div class="form_grid_12">
												<a href="../edit-meeting/${meeting.id}"><button
														class="btn_small btn_blue">Edit Meeting</button></a>
											</div>
										</li>
									</ul>

								</form:form>
							</div>
						</div>

					</div>
				</div>
			</div>
		</div>
		
		<div class="grid_12">
			<div class="widget_wrap">
				<div class="widget_top">
					<span class="h_icon list_image"></span>
					<h6>Meeting Agenda</h6>
				</div>
				<div class="widget_content">
					<div class="widget_wrap tabby">
					    
					    <div id="tab2">
							<%--<c:if test="${page=='agenda'}">--%>
							<div class="widget_content">
								<c:if test="${not empty success}">
									<div class="successblock">
										<spring:message code="${success}"></spring:message>
									</div>
								</c:if>

								<form:form modelAttribute="meeting"
									class="form_container left_label"
									action="${pageContext.request.contextPath}/app/edit-meeting/${meeting.id}"
									method="get">

									<span class="subheader-title">Meeting Agenda</span>
									<table class="display data_tbl">
										<thead>
											<tr>
												<th>Agenda Topic</th>
												<th>Owner</th>
												<th>Description</th>
												<th>Actions <span><a
															class="action-icons c-approve"
															href="${pageContext.request.contextPath}/app/new-meetingagenda/${meeting.id}"
															title="Create">Create</a></span></th>
											</tr>
										</thead>
										<tbody>
											<c:forEach var="agendaItem" items="${meeting.agendaItems}">
												<tr>
													<td><a
														href="${pageContext.request.contextPath}/app/edit-meetingagenda/${agendaItem.id}">${agendaItem.topic
															}</a></td>
													<td>${agendaItem.meetingAgendaOwner.name }</td>
													<td><c:choose>
															<c:when test="${fn:length(agendaItem.description) gt 50}">
																<c:out
																	value="${fn:substring(agendaItem.description,0,50)}....."
																	escapeXml="false" />
															</c:when>
															<c:otherwise>
																<c:out value='${agendaItem.description }'
																	escapeXml="false" />
															</c:otherwise>
														</c:choose></td>
													<td><span><a class="action-icons c-edit"
															href="${pageContext.request.contextPath}/app/edit-meetingagenda/${meeting.id}"
															title="Edit">Edit</a></span></td>
												</tr>
											</c:forEach>

										</tbody>
									</table>

								</form:form>
							</div>

                            <!-- 
							<div class="widget_content">
								<span class="subheader-title">Create/Update Agenda Item</span>
								<form:form modelAttribute="meetingAgendaItem"
									class="form_container left_label"
									action="${pageContext.request.contextPath}/app/update-meetingagenda/${meeting.id}"
									method="post">

									<input type="hidden" name="topicId"
										value="${meetingAgendaItem.id}" />
									<ul>
										<li>
											<div class="form_grid_12">
												<label for="topic" class="field_title">Topic</label>
												<div class="form_input">
													<input class="mid" type="text" name="topic"
														value="${meetingAgendaItem.topic}" />
												</div>
											</div>
										</li>
										<li>
											<div class="form_grid_12">
												<label for="owners" class="field_title">Agenda
													Owner/Facilitator</label>
												<div class="form_input">
													<select name="meetingAgendaOwnerId"
														id="meetingAgendaOwnerId" class="mid">
														<c:forEach var="attendee" items="${meeting.attendees}">
															<option value="${attendee.id}">${attendee.name}</option>
														</c:forEach>
													</select>
												</div>
											</div>
										</li>
										<li>
											<div class="form_grid_12">
												<label for="description" class="field_title">Description</label>
												<div class="form_input">
													<form:textarea style="width:500px" path="description"
														rows="5" cols="30" />
												</div>
											</div>
										</li>

										<li>
											<div class="form_grid_12">
												<button type="submit" class="btn_small btn_blue">
													<span>Create/Update Agenda Item</span>
												</button>
											</div>
										</li>
									</ul>

								</form:form>
							</div>-->
							<%--</c:if>--%>
						</div>
					    
					</div>
				</div>
			</div>
		</div>
		
		<div class="grid_12">
			<div class="widget_wrap">
				<div class="widget_top">
					<span class="h_icon list_image"></span>
					<h6>Meeting Minutes</h6>
					<div class="c_actions">
									<ul>
										<li><a
											href="${pageContext.request.contextPath}/app/exportword/minutes/${meeting.id}"
											title="Export to Word"> <img
												src="${pageContext.request.contextPath}/resources/images/table-tools/msword1.png"
												alt="Export to Word">
										</a></li>

									</ul>
								</div>
				</div>
				
				<div class="widget_content">
					<div class="widget_wrap tabby">
						<div id="tab3">
							<%--<c:if test="${page=='minutes'}">--%>
							<div class="widget_content">
								<c:if test="${not empty success}">
									<div class="successblock">
										<spring:message code="${success}"></spring:message>
									</div>
								</c:if>
								<c:forEach var="agendaItem" items="${meeting.agendaItems}">
									<form
										action="${pageContext.request.contextPath}/app/update-meetingminute/${agendaItem.id}"
										class="form_container left_label" method="post">
										<span class="subheader-title">Agenda Item</span>
										<ul>
											<li>
												<div class="form_grid_12">
													<label class="field_title">Topic</label>
													<div class="form_input">
														<input type="text" class="uneditable-input mid"
															name="topic" value="${agendaItem.topic}" />
													</div>
												</div>
											</li>
											<li>
												<div class="form_grid_12">
													<label class="field_title">Owner/Facilitator</label>
													<div class="form_input">
														<span class="uneditable-input mid">${agendaItem.meetingAgendaOwner.name}</span>
													</div>
												</div>
											</li>
											<li>
												<div class="form_grid_12">
													<label class="field_title">Discussion <span
														class="label_intro">Normal Text Area</span></label>
													<!-- <div class="form_input">
										<textarea name="filed05" id="txt_editor" cols="50" rows="5" tabindex="4"></textarea>
									</div> -->
													<div class="form_input">
														<textarea name="discussion${agendaItem.id}" cols="50"
															rows="5" tabindex="4">${agendaItem.discussion}</textarea>
													</div>
												</div>
											</li>
										</ul>
										<ul>
											<li>
												<div class="form_grid_12">

													<button type="submit" class="btn_small btn_blue"
														name="submitagenda${agendaItem.id}">Update
														Meeting Minutes</button>
												</div>
											</li>
										</ul>
									</form>
								</c:forEach>



								<c:if test="${empty meeting.agendaItems}">
									<tfoot>
										<tr>
											<td colspan="1">No Agenda Items Found...</td>
										</tr>
									</tfoot>
								</c:if>
							</div>
							<%--</c:if>--%>
						</div>
					</div>
				</div>
			</div>
		<div>
				<a href="${pageContext.request.contextPath}/app/meetingminutesadd">
													<button class="btn_small btn_blue">Add Meeting Minutes</button>
											</a>
                            </div></div>
		
		
		
		<div class="grid_12">
			<div class="widget_wrap">
				<div class="widget_top">
					<span class="h_icon list_image"></span>
					<h6>Meeting's Action Items</h6>
				</div>
				<div class="widget_content">
					<div class="widget_wrap tabby">
					
					     <div id="tab4">
							<%--<c:if test="${page=='actionitems'}">--%>
							<div class="widget_content">
								<table class='display data_tbl'>
									<thead>
										<tr>
											<th>ID</th>
											<th>Summary</th>
											<th>Due Date</th>
											<th>Assigned To</th>
											<th>Actions</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="actionItem" items="${meeting.actionItems}">
											<tr>
												<td>${actionItem.id }</td>
												<td><a
													href="${pageContext.request.contextPath}/app/actionitem/${actionItem.id }">
														<c:choose>
															<c:when test="${fn:length(actionItem.summary) gt 50}">
																<c:out
																	value="${fn:substring(actionItem.summary,0,50)}....." />
															</c:when>
															<c:otherwise>
										                    ${actionItem.summary}
										                    </c:otherwise>
														</c:choose>
												</a></td>
												<td><fmt:formatDate type="date"
														value='${actionItem.dueDate }' /></td>
												<td>${actionItem.assignedTo.name }</td>

												<td style="white-space: nowrap;"><span><a
														class="action-icons c-edit"
														href="${pageContext.request.contextPath}/app/edit-actionitem/${actionItem.id}"
														title="Edit">Edit</a></span><span><a
														class="action-icons c-approve"
														href="${pageContext.request.contextPath}/app/new-meetingactionitem/${meeting.id}"
														title="Create">Create</a></span></td>
											</tr>
										</c:forEach>
									</tbody>
									<tfoot>
										<tr style="height: 1px">
											<td colspan="4"><a
												href="${pageContext.request.contextPath}/app/new-meetingactionitem/${meeting.id}">
													<button class="btn_small btn_blue">Add Action Item</button>
											</a></td>
										</tr>
									</tfoot>
								</table>
							</div>
						</div>	
					
					</div>
				</div>
			</div>
		</div>
		
		
		<div class="grid_12">
			<div class="widget_wrap">
				<div class="widget_top">
					<span class="h_icon list_image"></span>
					<h6>Meeting's Lessons Learned</h6>
				</div>
				<div class="widget_content">

					<div class="widget_wrap tabby">
					   <div id="tab5">
							<%--<c:if test="${page=='actionitems'}">--%>
							<div class="widget_content">
								<table class="display data_tbl">
									<thead>
										<tr>
											<th>Summary</th>
											<th>Impact</th>
											<th>Recommendation</th>
											<th>Manage></th>

										</tr>
									</thead>
									<tbody>
										<c:forEach var="lessonslearned"
											items="${meeting.lessonsLearneds}">
											<tr>
												<td><a
													href="${pageContext.request.contextPath}/app/lessonslearned/${lessonslearned.id}">${lessonslearned.summary
														}</a></td>
												<td>${lessonslearned.impact }</td>
												<td>${lessonslearned.recommendation }</td>
												<td style="white-space: nowrap;"><span><a
														class="action-icons c-edit"
														href="${pageContext.request.contextPath}/app/edit-lessonslearned/${lessonslearned.id}"
														title="Edit">Edit</a></span><span><a
														class="action-icons c-approve"
														href="${pageContext.request.contextPath}/app/new-lessonslearned"
														title="Create">Create</a></span></td>
											</tr>
										</c:forEach>
									</tbody>
									<tfoot>
										<tr>
											<td colspan="4"><a
												href="${pageContext.request.contextPath}/app/new-lessonslearned">
													<button class="btn_small btn_blue">Add Lessons
														Learned</button>
											</a></td>
										</tr>
									</tfoot>
								</table>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		
		
	
	</div>
</div>

<script type="text/javascript">
	<c:if test="${page=='agenda'}">
	document.getElementById("agenda").setAttribute("class", "active_tab");
	</c:if>
	<c:if test="${page=='minutes'}">
	document.getElementById("minutes").setAttribute("class", "active_tab");
	</c:if>
	<c:if test="${page=='actionItems'}">
	document.getElementById("actionItems").setAttribute("class", "active_tab");
	</c:if>
	<c:if test="${page=='lessons'}">
	document.getElementById("lessons").setAttribute("class", "active_tab");
	</c:if>
</script>