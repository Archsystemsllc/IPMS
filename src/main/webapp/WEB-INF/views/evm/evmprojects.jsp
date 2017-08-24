<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<script type='text/javascript' src='https://www.google.com/jsapi'></script>
<script type='text/javascript'>
	google.load("visualization", "1.0", {
		packages : [ "corechart" ]
	});
	google.setOnLoadCallback(drawChart);
	function drawChart() {
		var options = {
			cht : 'lc',
			chds : '0,160'
		};
		<c:forEach var='item' items='${projectEvmMap}'>
		<c:if test="${item.key == 10035 || item.key == 10039}">
		var dataTable = new google.visualization.DataTable();
		dataTable.addColumn('string', 'Date');
		dataTable.addColumn('number', 'PV');
		dataTable.addColumn('number', 'CV');
		dataTable.addColumn('number', 'EV');
		<c:forEach var='evm' items='${item.value}'>

		var date = '<fmt:formatDate type="date" dateStyle="short" value="${evm.date}"/>';
		var pv = <fmt:parseNumber type="number" value="${evm.bcws}"/>;
		var ac = <fmt:parseNumber type="number" value="${evm.acwp}"/>;
		var ev = <fmt:parseNumber type="number" value="${evm.bcwp}"/>;
		dataTable.addRow([ date, pv, ac, ev ]);
		</c:forEach>
		var id = 'chart_div_' + '<c:out value="${item.key}"/>';
		var chart = new google.visualization.LineChart(document
				.getElementById(id));
		chart.draw(dataTable, options);
		</c:if>
		</c:forEach>

	}
</script>


<div id="breadcrumb">
	<ul>
		<li><a href="${pageContext.request.contextPath}/app/groupdashboard">Home</a>
			<span> >> </span></li>
		<li><a href="#" style="text-decoration: none;">EVM Projects</a></li>
	</ul>
</div>
<div id="content">
	<div class="grid_container">
		<div class="grid_12">
			<div class="widget_wrap">
				<div class="widget_top">
					<span class="h_icon documents"></span>
					<h6>Projects</h6>
				</div>
				<div class="widget_content">
					<table class="display data_tbl" id="dashboard">
						<thead>
							<tr>
								<th title="Project Name">Project</th>
								<th title="Project ID">Project ID</th>
								<th title="No of Issues">Issues</th>
								<th title="No of Action Items">Action Items</th>
								<th title="No of Risks">Risks</th>
								<th>Cost Variance</th>
								<th>Schedule Variance</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="project" items="${projects}">
								<c:if test="${not project.active }">
									<c:set var="linkcolor" value="activeobjectlink" />
								</c:if>
								<c:if test="${project.active }">
									<c:set var="linkcolor" value="" />
								</c:if>
								<tr>
									<td><a class="${linkcolor }" href="evmproject/${project.id}">${project.name
											}</a></td>
									<td><a class="${linkcolor }">${project.id }</a></td>
									<td><a class="${linkcolor }"><c:out
												value="${fn:length(project.issues) }" /></a></td>
									<td><a class="${linkcolor }"><c:out
												value="${fn:length(project.actionItems) }" /></a></td>
									<td><a class="${linkcolor }"><c:out
												value="${fn:length(project.risks) }" /></a></td>
									<td><c:forEach var="evmMap" items="${projectEvmMap}">

											<c:if test="${evmMap.key == project.id}">
												<c:forEach var="projectevm" items="${evmMap.value}"
													varStatus="loop">
													<c:if test="${loop.last}">
															<c:if
																test="${projectevm.cvPercent >= 0 && projectevm.cvPercent <= 4}">
																<img
																	src="${pageContext.request.contextPath}/resources/images/green_ball.gif"
																	alt="Green" />
															</c:if>
															<c:if
																test="${projectevm.cvPercent > 4 && projectevm.cvPercent <= 10}">
																<img
																	src="${pageContext.request.contextPath}/resources/images/yellow_ball.gif"
																	alt="Yellow" />
															</c:if>
															<c:if
																test="${projectevm.cvPercent > 10 || projectevm.cvPercent < 0}">
																<img
																	src="${pageContext.request.contextPath}/resources/images/red_ball.gif"
																	alt="Red" />
															</c:if>
													</c:if>
												</c:forEach>
											</c:if>
										</c:forEach></td>
									<td><c:forEach var="evmMap" items="${projectEvmMap}">

											<c:if test="${evmMap.key == project.id}">
												<c:forEach var="projectevm" items="${evmMap.value}"
													varStatus="loop">
													<c:if test="${loop.last}">
														<c:if
															test="${projectevm.svPercent >= 0 && projectevm.svPercent <= 4}">
															<img
																src="${pageContext.request.contextPath}/resources/images/green_ball.gif"
																alt="Green" />
														</c:if>
														<c:if
															test="${projectevm.svPercent > 4 && projectevm.svPercent <= 10}">
															<img
																src="${pageContext.request.contextPath}/resources/images/yellow_ball.gif"
																alt="Yellow" />
														</c:if>
														<c:if
															test="${projectevm.svPercent > 10 || projectevm.svPercent < 0}">
															<img
																src="${pageContext.request.contextPath}/resources/images/red_ball.gif"
																alt="Red" />
														</c:if>
													</c:if>
												</c:forEach>
											</c:if>
										</c:forEach></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>
		<span class="clear"></span>
	</div>
</div>