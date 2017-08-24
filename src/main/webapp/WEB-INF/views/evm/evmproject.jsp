<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<script type='text/javascript' src='https://www.google.com/jsapi'></script>
<script type='text/javascript'>
	<script src= "http://code.jquery.com/jquery-1.8.3.js">
</script>
<script src="http://code.jquery.com/ui/1.9.2/jquery-ui.js"></script>

<script type="text/javascript">
	$('document').ready(function() {
		$("#table1234 th[title]").hover(function() {
			showTooltip($(this));
		}, function() {
			hideTooltip();
		});

		function showTooltip($el) {
			$tip.html($el.attr('title'));
		}
		function hideTooltip() {
			$tip.hide();
		}

	});

	google.load("visualization", "1.0", {
		packages : [ "corechart" ]
	});
	google.setOnLoadCallback(drawChart);
	function drawChart() {
		var dataTable = new google.visualization.DataTable();
		dataTable.addColumn('string', 'Date');
		dataTable.addColumn('number', 'PV');
		dataTable.addColumn('number', 'CV');
		dataTable.addColumn('number', 'EV');

		//        dataTable.addRow(['jan', 21500, 19000]);
		//         dataTable.addRow(['feb', 10000,24000]);
		//         dataTable.addRow(['mar', 157, 40]);
		//         dataTable.addRow(['apr', 90, 20]);
		//         dataTable.addRow(['apr', 90, 20]);
<%--alert('<c:out value="${fn:length(projectEvm)}"/>');--%>
	<c:forEach var='evm' items='${projectEvm}'>
		var date = '<fmt:formatDate type="date" dateStyle="short" value="${evm.date}"/>';
<%--var d =  <fmt:parseDate type="date" dateStyle="short" value="${evm.date}"/>;--%>
	var pv = <fmt:parseNumber type="number" value="${evm.bcws}"/>;
		var ac = <fmt:parseNumber type="number" value="${evm.acwp}"/>;
		var ev = <fmt:parseNumber type="number" value="${evm.bcwp}"/>;
		dataTable.addRow([ date, pv, ac, ev ]);
		</c:forEach>

		var options = {
			cht : 'lc',
			chds : '0,160'
		};
		var chart = new google.visualization.LineChart(document
				.getElementById('chart_div'));
		chart.draw(dataTable, options);
	}
</script>
<div id="content">
	<div class="grid_container">
		<div class="grid_12">
			<div class="widget_wrap">
				<div class="grid_12">
					<div class="widget_wrap">
						<div class="widget_top">
							<span class="h_icon list_images"></span>
							<h6>Project Burn Rate</h6>

						</div>
						<div class="widget_content">
							<br />
							<%--Project burn value chart--%>
							<div id="chart_div" style="height: 250px;"></div>
						</div>
					</div>
				</div>
				<span class="clear"></span> <br /> <br />
				<div id="content">
					<div class="grid_container">
						<div class="grid_12">
							<div class="widget_wrap">
								<div class="grid_12">
									<div class="widget_wrap">
										<div class="widget_top">
											<div id="widget_tab" style="float: left;"></div>
											<div class="widget_top">
												<span class="h_icon documents"></span>
												<h6>Earn Value Management Data</h6>
											</div>
											<table class="display data_tbl">
												<thead>
													<tr>
														<th>EVM Varibles</th>
														<th>EVM Data</th>

													</tr>
												</thead>
												<tbody>
													<tr>
														<td>Cost Performance Indicator (CPI)</td>
												<td><c:out value="${currentEvm.cpi}" /></td>
													</tr>
													<tr>
														<td>Schedule Performance Indicator (SPI)</td>
												<td><c:out value="${currentEvm.spi}" /></td>
													</tr>
													<tr>
														<td>Budgeted Cost of Work Scheduled (BCWS)</td>
												<td><c:out value="${currentEvm.bcws}" /></td>
													</tr>
													<tr>
														<td>Budgeted Cost of Work Performed (BCWP)</td>
												<td><c:out value="${currentEvm.bcwp}" /></td>
													</tr>
													<tr>
														<td>Actual Cost of Work Performed (ACWP)</td>
												<td><c:out value="${currentEvm.acwp}" /></td>
													</tr>
													<tr>
														<td>Schedule Variance (SV)</td>
												<td><c:out value="${currentEvm.sv}" /></td>
													</tr>
													<tr>
														<td>Cost Variance (CV)</td>
												<td><c:out value="${currentEvm.cv}" /></td>
													</tr>
													<tr>
														<td>Estimate at Completion (EAC)</td>
												<td><c:out value="${currentEvm.eac}" /></td>
													</tr>
													<tr>
														<td>Variance at Completion (VAC)</td>
												<td><c:out value="${currentEvm.vac}" /></td>
													</tr>

												</tbody>
											</table>

										</div>
										<br /> <br />

									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
