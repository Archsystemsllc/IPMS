<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<div id="breadcrumb">
    <ul>
        <li>
            <a href="${pageContext.request.contextPath}/app/groupdashboard">Home</a> <span> >> </span>
        </li>
        <li>
        	
				<form:form  modelAttribute="searchParam" class="form_container left_label"
					action="${pageContext.request.contextPath}/app/choosefinancialreport"
					method="post">
					<form:hidden path="inceptionStartDate"/>
					<form:hidden path="inceptionEndDate"/>
					<form:hidden path="projectId"/>
					<form:hidden path="reportTypeId"/>
					<form:hidden path="reportIds"/>
            	<a href="#" onclick="document.forms['searchParam'].submit();">Financial Report</a> <span> >> </span>
				</form:form>
        </li>
        <li>
            <a href="#" style="text-decoration: none;">View Report</a>
        </li>
    </ul>
</div>
<div id="content">
    <div class="grid_container">
        <div class="grid_12">
            <div class="widget_wrap">
                <div class="widget_top">
	                <div class="widget_content">
		                <h6>Project:&nbsp${searchParam.projectName }</h6>
		                <h6>Inception Date:&nbsp${searchParam.inceptionStartDate}</h6>
		                <h6>Report:&nbsp${searchParam.reportTypeName}</h6>
		                <h6>Start Date:&nbsp${searchParam.startDate}</h6>
		                <h6>End Date:&nbsp${searchParam.endDate}</h6>
		            </div>
                </div>
                <div class="widget_content">
						<form:errors path="*" cssClass="errorblock" element="div" />
                    <table class="display data_tbl">
                        <thead>
	                        <tr>
	                            <th width="10%">As of</th>
	                            <th width="10%">Description</th>
	                            <th width="10%">Total Funding</th>
	                            <th width="10%">Current Invoice</th>
	                            <th width="10%">Total Invoiced</th>
	                            <th width="10%">Funds Remaining<br>Under/(Over)</th>
	                            <th width="10%">Current % Spent</th>
	                            <th width="10%">Projected Expenditures<br>through end of Contract POP*</th>
	                            <th width="10%">Variance Under/(Over)</th>
	                            <th width="10%">Projected % Spent</th>
	                        </tr>
                        </thead>
                        <tbody>
                         <c:forEach var="fe" items="${financialExpenses}">
                            <tr>
                            	<td align="left"><fmt:formatDate value="${fe.reportingDate}" pattern="MMM-dd" /></td>
                            	<td>${fe.description}</a></td>
                                <td align="right"><fmt:formatNumber value="${fe.totalFunding}" type="currency"/></td>
                                <td align="right"><fmt:formatNumber value="${fe.currentInvoice}" type="currency"/></td>
                                <td align="right"><fmt:formatNumber value="${fe.totalInvoiced}" type="currency"/></td>
                                <td align="right"><fmt:formatNumber value="${fe.fundsRemaining}" type="currency"/></td>
                                <td align="right"><fmt:formatNumber value="${fe.currentSpent}" type="number"  minFractionDigits="2" maxFractionDigits="2" />%</td>
                                <td align="right"><fmt:formatNumber value="${fe.prjExpnsThruEndOfContract}" type="currency"/></td>
                                <td align="right"><fmt:formatNumber value="${fe.variance}" type="currency"/></td>                       
                                <td align="right"><fmt:formatNumber value="${fe.projectedSpent}" type="number" minFractionDigits="2" maxFractionDigits="2" />%</td>                            
                            </tr>
                           </c:forEach>
                        </tbody>
                    </table>
                    
                  </div>
                </div>
              </div>
                
    <div class="grid_container">
        <div class="grid_12">
            <div class="widget_wrap">
                     <table class="display data_tbl">
                        <thead>
	                        <tr>
	                            <th width="10%">As of</th>
	                            <th width="10%">Description</th>
	                            <th width="10%">Total Hours Planned</th>
	                            <th width="10%">Current Hours</th>
	                            <th width="10%">Cumulative Total Actual Hours</th>
	                            <th width="10%">Total Hours Remaining</th>
	                            <th width="10%">Current % Used</th>
	                            <th width="10%">Estimated Hours at Completion (EAC)</th>
	                            <th width="10%">Variance from Baseline</th>
	                            <th width="10%">Projected % Spent</th>
	                        </tr>
                        </thead>
                        <tbody>
                         <c:forEach var="fe" items="${financialHours}">
                            <tr>
                            	<td align="left"><fmt:formatDate value="${fe.reportingDate}" pattern="MMM-dd" /></td>
                            	<td>${fe.description}</a></td>
                                <td align="right"><fmt:formatNumber value="${fe.totalHoursPlanned}" type="number"/></td>
                                <td align="right"><fmt:formatNumber value="${fe.currentHours}" type="number"/></td>
                                <td align="right"><fmt:formatNumber value="${fe.cumulativeTotalActualHours}" type="number"/></td>
                                <c:if test="${fe.varianceFromBaseline <0}">
                                	<td align="right"><font color="red"><fmt:formatNumber value="${fe.totalHoursRemaining}" type="number"/></font></td>
                               	</c:if>
                               	<c:if test="${fe.varianceFromBaseline >=0}">
                                	<td align="right"><fmt:formatNumber value="${fe.totalHoursRemaining}" type="number"/></td>
                               	</c:if>
                               	
                                <td align="right"><fmt:formatNumber value="${fe.currentUsedPercent}" type="number"  minFractionDigits="2" maxFractionDigits="2" />%</td>
                                <td align="right"><fmt:formatNumber value="${fe.estHoursCompletion}" type="number"/></td>
                                <c:if test="${fe.varianceFromBaseline <0}">
                                	<td align="right"><font color="red"><fmt:formatNumber value="${fe.varianceFromBaseline}" type="number"/></font></td>
                                </c:if>             
                                <c:if test="${fe.varianceFromBaseline >=0 }">         
                                	<td align="right"><fmt:formatNumber value="${fe.varianceFromBaseline}" type="number"/></font></td>
                                </c:if>
                                
                                <td align="right"><fmt:formatNumber value="${fe.projectedSpent}" type="number" minFractionDigits="2" maxFractionDigits="2" />%</td>                            
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