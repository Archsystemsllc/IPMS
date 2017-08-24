<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<SCRIPT language="javascript">
	function addRow(tableID) {

		var table = document.getElementById(tableID);

		var rowCount = table.rows.length;
		var row = table.insertRow(rowCount);

		var colCount = table.rows[0].cells.length;

		for ( var i = 0; i < colCount; i++) {

			var newcell = row.insertCell(i);

			newcell.innerHTML = table.rows[0].cells[i].innerHTML;
			//alert(newcell.childNodes);
			switch (newcell.childNodes[0].type) {
			case "text":
				newcell.childNodes[0].value = "";
				newcell.childNodes[0].id = "text"+rowCount+1;
				newcell.childNodes[0].name = "text"+rowCount+1;
				break;
			case "checkbox":
				newcell.childNodes[0].checked = false;
				break;
			case "select-one":
				newcell.childNodes[0].selectedIndex = 0;
				newcell.childNodes[0].id = "select"+rowCount+1;
				newcell.childNodes[0].name = "select"+rowCount+1;
				break;
			}
		}
	}

	function deleteRow(tableID) {
		try {
			var table = document.getElementById(tableID);
			var rowCount = table.rows.length;

			for ( var i = 0; i < rowCount; i++) {
				var row = table.rows[i];
				var chkbox = row.cells[0].childNodes[0];
				if (null != chkbox && true == chkbox.checked) {
					if (rowCount <= 1) {
						alert("Cannot delete all the rows.");
						break;
					}
					table.deleteRow(i);
					rowCount--;
					i--;
				}
			}
		} catch (e) {
			alert(e);
		}
	}
	
	function selectbox(){
		var idforbox = "86";
		alert("Before Ajax..!" + idforbox );
        $.ajax({  
        	type: "post", 
             url : "${pageContext.request.contextPath}/app/meetingAjaxCall",  
             cache: false,
             data : {"uuid":idforbox}, 
             success: function(response)
             {
            	 var meetingsList = new Array();
            	 var meetingsList = JSON.parse(response);
            	 $('#date')= meetingsList[0].value;
            	 $('#time')= meetingsList[1].value;
            	 $('#locationInfo')= meetingsList[2].value;           	 
              },           	  
        });
        alert("onUnLoad Byes");
		
	}
	
</SCRIPT>
<div id="breadcrumb">
	<ul>
		<li><a
			href="${pageContext.request.contextPath}/app/groupdashboard">Home</a>
			<span> >> </span></li>
		<li><a href="${pageContext.request.contextPath}/app/meetings">Meetings</a>
			<span> >> </span></li>
		<li><a href="#" style="text-decoration: none;">Add Agenda</a></li>
	</ul>
</div>
<div class="content">
	<div class="grid_container">
		<div class="grid_12 full_block">
			<div class="widget_wrap">
				<div class="widget_top">
					<span class="h_icon list_image"></span>
					<h6>Add Agenda</h6>
				</div>
				<form:form modelAttribute="meetingAgendaItem"
					class="form_container left_label"
					action="${pageContext.request.contextPath}/app/edit-meetingagenda/${meetingAgendaItem.id}"
					method="post">
					<div class="widget_content">
						<!-- <span class="subheader-title">Schedule a Meeting</span> -->
						<form:errors path="*" cssClass="errorblock" element="div" />
						<table style="width: 100%">
							<tr>
								<td style="width: 100%"><ul>
										<li>
											<div class="form_grid_12">
												<label for="name" class="field_title">Meeting Name</label>
												<div class="form_input" style="width: 100%">
												     <c:out value="${meetingTitle}" />
												</div>
											</div>
										</li>
										
										<!-- <li>
											<div class="form_grid_12">
												<label for="agendaname" class="field_title">Agenda Name</label>
												<div class="form_input" style="width: 100%">
													<form:input type="text" id="name" name="name" class="mid"
														path="name"></form:input>
												</div>
											</div>
										</li>-->
                                        
										<li>
											<div class="form_grid_12">
												<label for="topic" class="field_title">Topic</label>
												<div class="form_input" style="width: 100%">
													<form:input type="text" id="topic" name="topic" class="mid"
														path="topic"></form:input>
												</div>
											</div>
										</li>
										<!-- <li>
											<div class="form_grid_12">
												<label for="date" class="field_title">Date</label>
												<div class="form_input">
													<form:input type="text" path="meeting.date" name="date" id="date"
														class='datepicker mid'></form:input>
												</div>
											</div>
										</li>
										
										<li>
											<div class="form_grid_12">
												<label for="time" class="field_title">Time</label>
												<div class="form_input">
													<form:input type="text" path="meeting.time" name="time" id="time"
														class='timepicker mid' value="${meeting.time}"></form:input>
												</div>
											</div>
										</li>
										<li>
											<div class="form_grid_12">
												<label for="locationInfo" class="field_title">Location</label>
												<div class="form_input">
													<form:input type="text" id="locationInfo"
														name="locationInfo" class="mid" path="meeting.locationInfo" value="${meeting.locationInfo}"></form:input>
												</div>
											</div>
										</li>
										<li>
                                			<div class="form_grid_12">
	                                    		<label for="organizerId" class="field_title">Facilitator Name</label>
	                                    		<div class="form_input">
	                                        		<form:select id="organizerId" name="organizerId"
	                                                     path="meeting.organizerId">
	                                            	<form:options items="${referenceData.organizerList}"/>
	                                        	</form:select>
	                                   			 </div>
                                			</div>
                            			</li>-->
										<li>
											<div class="form_grid_12">
												<label for="description" class="field_title">Description</label>
												<div class="form_input" style="width: 100%">
													<form:input type="text" id="description" name="description"
														class="mid" path="description" style="width: 100%"></form:input>
												</div>
											</div>
										</li>
									</ul></td>
							</tr>
						</table>
						<h4>Discussion Items</h4>
						<ul style="padding-left: 100px;">
							<li><div style="padding-bottom: 10px;">
									<input type="button" onclick="addRow('dataTable')"
										value="Add Row"> <input type="button"
										onclick="deleteRow('dataTable')" value="Delete Row">
								</div></li>
						</ul>

						<div>
							<table style="width:100%;" border="0" class="display data_tbl dataTable"
								id="dataTable">
								<tbody>
								    <c:forEach var="discussionAgenda" items="${discussionAgendas}">
									<tr>
										<td width="1%"><input type="checkbox" name="chk"></td>
										<td width="100px;"><div class="form_grid_12">
												<div>
													<input type="text" 
														size="10" name="item" id="item" value="${discussionAgenda.item}"
														></input>
												</div>
											</div></td>
										<td width="100px;"><div class="form_grid_12">

												<div class="form_input">
													<input type="text" 
														name="facilitatorname" id="facilitatorname" value="${discussionAgenda.facilitatorname}"
														></input>
												</div>
											</div></td>
									</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
						<!-- <h4>Parking Lot</h4>
						<ul style="padding-left: 100px;">
							<li><div style="padding-bottom: 10px;">
									<input type="button" onclick="addRow('parkingLotTable')"
										value="Add Row"> <input type="button"
										onclick="deleteRow('parkingLotTable')" value="Delete Row">
								</div></li>
						</ul>

						<div>
							<table width="100%" border="1" class="display data_tbl dataTable"
								id="parkingLotTable">
								<tbody>

									<tr>
										<td width="1%"><input type="checkbox" name="chk"></td>
										<td width="100px;"><div class="form_grid_12">

												<div>
													<form:input type="text" value="Enter Date Added" size="10"
														name="date" id="date" path="parkinglot.dateadded"></form:input>
												</div>
											</div></td>
										<td width="100px;"><div class="form_grid_12">

												<div class="form_input">
													<form:input type="text" value="Enter Issue" name="issue"
														id="issue" path="parkinglot.issue"></form:input>
												</div>
											</div></td>
										<td width="100px;"><div class="form_grid_12">

												<div>
													<form:input type="text" value="Enter Owner" size="10"
														name="owner" id="owner" path="parkinglot.owner"></form:input>
												</div>
											</div></td>
										<td width="100px;"><div class="form_grid_12">

												<div class="form_input">
													<form:input type="text" value="Enter Status" name="status"
														id="status" path="parkinglot.status"></form:input>
												</div>
											</div></td>
										<td width="100px;"><div class="form_grid_12">

												<div class="form_input">
													<form:input type="text" value="Enter Bi-weekly update"
														name="biweeklyupdate" id="biweeklyupdate"
														path="parkinglot.biweeklyupdate"></form:input>
												</div>
											</div></td>
									</tr>
								</tbody>
							</table>
						</div>-->

						<div class="form_grid_12">
							<div class="form_input">
								<button type="submit" class="btn_small btn_blue">
									<span>Update</span>
								</button>
								<button type="reset" class="btn_small btn_blue">
									<span>Reset</span>
								</button>
							</div>
						</div>
					</div>
				</form:form>
			</div>
		</div>
	</div>
</div>