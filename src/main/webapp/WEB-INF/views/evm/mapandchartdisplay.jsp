<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<script src="${pageContext.request.contextPath}/resources/js/jquery-1.10.2.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/jquery-ui-1.10.4.custom.js"></script>		
<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.5.0/Chart.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/Chart.bundle.js"></script>


<div class="content">
	<div class="grid_container">
		<div class="grid_12 full_block">
			<div class="widget_wrap">
				<div class="widget_top">
					<span class="h_icon list"></span>
					<h6>EVM Metrics</h6>
				</div>
				<div class="widget_content" id ="widget_content">
					
					<span class="subheader-title">EVM Metrics</span>
					<form:form modelAttribute="metric" class="form_container left_label">
						<form:errors path="*" cssClass="errorblock" element="div" />
						<div id="error" class="errorblock" style="display:none"></div>
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
				                                            <form:options items="${referenceData.currentUserProjectlist}"  />
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
				</div>
			</div>
			<div id="message" style="display:none; margin:auto; border:none; margin-left:10px; color:inherit;background:inherit;width:auto"></div>
			<div id ="canvasDiv" class="chartjs" style="display: block; width: 770px; height: 385px;align :center;">
				<canvas id="canvas" ></canvas>
			</div>
		</div>
	</div>
	
</div>
<script>


$(function(){
	  $.datepicker.setDefaults(
	    $.extend($.datepicker.regional[''])
	  );
	  $('.datepicker').datepicker( "option", "maxDate", '+0m +0w' );
	  document.getElementById("displayreport").onclick = function() {
		  searchViaAjax()
	  };
	});
	
var url = "${pageContext.request.contextPath}/app/evmMetrics";
var lineChartData = null;
function displayGraph() {
  var canvas = document.getElementById('canvas');
  var ctx = canvas.getContext('2d');
 
  var myChart = new Chart(ctx, {
     type: 'line',
     data: {
        labels: lineChartData.label,
        datasets: [{
           label:lineChartData.projectName,
           data: lineChartData.data,
           color: "#878BB6",
           borderColor:"rgb(75, 192, 192)",
   		   fill : false,
   		   responsive : true,
   		   lineTension: 0.1,}],
   		
     },
     options: {
         responsive: true,
         title:{
             display:true,
             text:'Line graph for ' +lineChartData.projectName + ' between ' + $("#fromDate").val() + ' and ' + $("#toDate").val()
         },
         tooltips: {
             mode: 'index',
             intersect: false,
         },
         hover: {
             mode: 'nearest',
             intersect: true
         },
         scales: {
             xAxes: [{
                 display: true,
                 scaleLabel: {
                     display: true,
                     labelString: 'Time'
                 }
             }],
             yAxes: [{
                 display: true,
                 scaleLabel: {
                     display: true,
                     labelString: 'Budget'
                 },
	             ticks: {
	                 min: 0,
	                 max: lineChartData.maxBudget + 1000,
	                 stepSize: ((lineChartData.maxBudget%10) > 0 ? 500 : lineChartData.maxBudget%10)
	             }
             }]
         }
     }
  });
};


$.ajax({
    type: "GET",
    url: url,
    cache: false,                    
    contentType: "application/json; charset=utf-8",
    success: function(response){
          
		var responsePIE = jQuery.parseJSON(response);
		var myChart = new Chart(document.getElementById("canvas").getContext("2d")).Line(responsePIE);
		legend(document.getElementById("canvasLegend"), responsePIE);
	  },
    error: function(response){              
           alert('Error while request..');
    }
});

function searchViaAjax() {

	var search = {}
	//search["projectId"] = $("#projectId").val();
	//search["fromDate"] = $("#fromDate").val();
	//search["toDate"] = $("#toDate").val();
	
	var search = {
            "projectId" : $("#projectId").val(),
            "fromDate" : $("#fromDate").val(),
            "toDate" : $("#toDate").val(),
    }
	var labels;
	resetCanvas();

	$.ajax({
		type : "POST",
		contentType : "application/json",
		url : url,
		data : JSON.stringify(search),
		dataType : 'json',
		timeout : 100000,
		beforeSend: function(xhr) {
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		},
		success : function(data) {
			if(data.status == "SUCCESS"){
				$('#error').hide();
				lineChartData = data.result;
				if(lineChartData.data.length > 0) {
					$('#canvasDiv').show();
					displayGraph();
					$('#message').hide();
				} else {
					$('#message').html("No records available for given criteria.");
					$('#message').show();
					$('#canvasDiv').hide();
					
				}
				
			} else {
				errorInfo = "";
                for(i =0 ; i < data.result.length ; i++){
                    errorInfo += "<br>" + (i + 1) +". " + data.result[i].defaultMessage;
                }
				$('#error').html("Please correct following errors: " + errorInfo);
				$('#error').show();
				$('#message').hide();
			}
		},
		error : function(e) {
			console.log("ERROR: ", e);
			display(e);
		},
		done : function(e) {
			console.log("DONE");
			enableSearchButton(true);
		}
	});

}

var resetCanvas = function() {
	$('#canvas').remove(); // this is my <canvas> element
	$('#canvasDiv').append('<canvas id="canvas"><canvas>');
};

function enableSearchButton(flag) {
	$("#displayreport").prop("disabled", flag);
}

function display(data) {
	var json = "<h4>Ajax Response</h4><pre>"
			+ JSON.stringify(data, null, 4) + "</pre>";
	$('#widget_content').html(json);
}

  </script>