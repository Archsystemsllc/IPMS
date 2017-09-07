<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<script src="${pageContext.request.contextPath}/resources/js/jquery-1.10.2.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/jquery-ui-1.10.4.custom.js"></script>		
<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.5.0/Chart.min.js"></script>

<div class="content">
	<div class="grid_container">
		<div class="grid_12 full_block">
			<div class="widget_wrap">
				<div class="widget_top">
					<span class="h_icon list"></span>
					<h6>Issue</h6>
				</div>
				<div class="widget_content">
					<span class="subheader-title">EVM Metrics</span>
					<form:form modelAttribute="metric" class="form_container left_label">
						<form:errors path="*" cssClass="errorblock" element="div" />
						<ul>
							<li>
								<fieldset>
									<legend>EVM Metrics</legend>
									<ul>
										<li>
											<div class="form_grid_12">
												<label for="projectId" class="field_title">Project Id :</label>
												<div class="form_input">
													<form:select id="projectId" name="projectId" path="projectId" class="mid">
			                                                <form:options items="${referenceData.projectList}"  />
			                                        </form:select>
												</div>
											</div>
										</li>
										<li>
											<div class="form_grid_12">
												<label for="fromDate" class="field_title">From date:</label>
												<div class="form_input">
													<form:input type="text" name="fromDate"
														id="fromDate" path="fromDate"
														class='datepicker mid'></form:input>
												</div>
											</div>
										</li>
										<li>
											<div class="form_grid_12">
												<label for="toDate" class="field_title">To Date:</label>
												<div class="form_input">
													<form:input type="text" name="toDate" id="toDate"
														path="toDate" class='datepicker mid'></form:input>
												</div>
											</div>
										</li>
									</ul>
								</fieldset>
							</li>
							<li>
								<div class="form_grid_12">
									<div class="form_input">
										<button type="button" class="btn_small btn_blue" id="displayreport" 
											name="displayreport" value="displayreport">
											<span>Display Graph</span>
										</button>
									</div>
								</div>
							</li>
						</ul>
					</form:form>
					 <div id="center">
					 	  <canvas id="canvas" width="600" height="400"></canvas>
					 </div>
				</div>
			</div>
		</div>
	</div>
</div>
<script>


$(function(){
	  $.datepicker.setDefaults(
	    $.extend($.datepicker.regional[''])
	  );
	  $('.datepicker').datepicker();
	  
	  document.getElementById("displayreport").onclick = function() {displayGraph()};
	});

function displayGraph() {
  var canvas = document.getElementById('canvas');
  var ctx = canvas.getContext('2d');

  var myChart = new Chart(ctx, {
     type: 'line',
     data: {
        labels: ["2010", "2011", "2012", "2013"],
        datasets: [{
           label: 'Dataset 1',
           data: [150, 200, 250, 150],
           color: "#878BB6",
        }, {
           label: 'Dataset 2',
           data: [250, 100, 150, 10],
           color: "#4ACAB4",
        }]
     }
  });
};

  </script>