/**
 * 
 */
package com.archsystemsinc.ipms.sec.webapp.controller;

import java.math.BigDecimal;
import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.archsystemsinc.ipms.sec.model.AjaxResponseBody;
import com.archsystemsinc.ipms.sec.model.BudgetTimeInputJSON;
import com.archsystemsinc.ipms.sec.model.BudgetTimeOutputJSON;
import com.archsystemsinc.ipms.sec.model.MetricModel;
import com.archsystemsinc.ipms.sec.model.OrganizationCategory;
import com.archsystemsinc.ipms.sec.model.Principal;
import com.archsystemsinc.ipms.sec.model.Project;
import com.archsystemsinc.ipms.sec.model.Task;
import com.archsystemsinc.ipms.sec.persistence.service.IOrganizationCategoryService;
import com.archsystemsinc.ipms.sec.persistence.service.IPrincipalService;
import com.archsystemsinc.ipms.sec.persistence.service.IProjectService;
import com.archsystemsinc.ipms.sec.persistence.service.ITaskService;

/**
 * This is the Spring Controller Class for IPMS Screen(EVM Display Screen) Functionality.
 * 
 * This controller class loads the drop down value data for all selection criteria from the look up tables 
 * and send it to metrics(Graph Display) Screen.
 * 
 * Associated Database Tables:
 * Organization_Category : To get rate for each staff
 * Tasks : To 
 * 
 * 
 * @author Ramya Gaddam
 */
@Controller

public class MapAndChartDisplayController {

	@Autowired
	private IProjectService projectService;
	
	@Autowired
	private IPrincipalService principalService;
	
	@Autowired
	private ITaskService taskService;
	
	@Autowired
	private IOrganizationCategoryService orgService;
	
	private final Log logger = LogFactory.getLog(ModelController.class);
	
	private Project proj;
	
	protected Map<String, Map<Integer, String>> referenceData() {
		
		final Map<String, Map<Integer, String>> referenceData = new HashMap<>();
		final String currentUser = SecurityContextHolder.getContext()
				.getAuthentication().getName();
		Principal principal = principalService.findByName(currentUser);
		final List<Project> currentUserProjectlist = projectService.findActiveUserProjects(principal);
		final Map<Integer, String> cpList = new LinkedHashMap<Integer, String>();
		cpList.put(0, "--Select Project--");
		for (int i = 0; i < currentUserProjectlist.size(); i++) {
			cpList.put(currentUserProjectlist.get(i).getId().intValue(), currentUserProjectlist.get(i)
					.getName());
		}
		referenceData.put("currentUserProjectlist", cpList);
		return referenceData;
	}

	@RequestMapping(value = "/evmMetrics" , method = RequestMethod.GET)
	public String mapAndChartScreen(final HttpServletResponse response, final Model model,
			final java.security.Principal currentUser) {
		
		model.addAttribute("referenceData", referenceData());
		MetricModel metric = new MetricModel();
		model.addAttribute("metric", metric);
		// View Page: mapandchartdisplay.jsp
		return "evmMetrics";		
	}	
	
	/***
	 * This is the post method to display graph for EVM metrics.
	 * 1.Validates the request
	 * 2.If valid, calculates the graph points by delegating to calculatePointsForGraph() method
	 * 		and wraps the response with status code and graph data and graph labels in JSON format.
	 * 3.If invalid, sends error response.
	 * 
	 * @param search
	 * @return
	 */
	@RequestMapping(value = "/evmMetrics" , method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody AjaxResponseBody getSearchResultViaAjax(@RequestBody BudgetTimeInputJSON search) {
		
		AjaxResponseBody response = new AjaxResponseBody();
		
		List<ObjectError> errorList = validate(search);
		
		long projectId = Long.valueOf(search.getProjectId());
		proj = projectService.findOne(projectId);
       
        if(errorList.isEmpty()){
        	response.setStatus("SUCCESS");
        	BudgetTimeOutputJSON resultList = calculatePointsForGraph(search);
        	resultList.setProjectName(proj.getName());
        	response.setResult(resultList);
        }else{
        	response.setStatus("FAIL");
        	response.setResult(errorList);
        }

		return response;
	}
	
	/**
	 * This method calculates labels and data based on the time span between given input dates.
	 * 
	 * 1. Fetches all tasks for given project id and whose due dates are within given from date and to date.
	 * 2. Determines the time span between the dates.
	 * 3. Based on the time span, the labels are determined 
	 * Ex: Years(2012, 2013..) for multi year
	 * Ex: Month names (Jan, Feb..) for same year.
	 * Ex: Year/month (2015/11, 2015/12,2016/01 ..) for time spanning within a year but falling in consecutive years.
	 * Ex: Dates(1,2,3) if the dates fall within the month of same year.
	 * 
	 * @param search
	 * @return
	 */
	private BudgetTimeOutputJSON calculatePointsForGraph(BudgetTimeInputJSON search){
		
		BudgetTimeOutputJSON bgt1 = new BudgetTimeOutputJSON();
		
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        Date fromDate = null;
        Date toDate = null;
		try {
			fromDate = formatter.parse(search.getFromDate());
			toDate = formatter.parse(search.getToDate());
		} catch (ParseException e) {
			logger.debug("Error while parsing input dates");
		}
		
		String timeSpan = calculateTimeSpanBetweenDates(fromDate, toDate);
		
		List<Task> taskList = taskService.findResolvedTasksBetweenForProject(fromDate, toDate,proj);
		
		Calendar today = Calendar.getInstance();
		int currentYear = today.get(Calendar.YEAR);
		
		Map<String, String> finalMap = null;
		Map<Integer, String> numberMap;
		Map<String, String> treeMap; 
		List<String> label = null;
		List<String> data = null;
		
		List<OrganizationCategory> orgList = new ArrayList<>();
		Calendar cal = Calendar.getInstance();
		
		switch(timeSpan) {
		
		case "multiYearSpan":

			finalMap =  new HashMap<>();
			for(Task task: taskList) {
				
				cal.setTime(task.getDueDate());
				int year = cal.get(Calendar.YEAR);
				orgList = orgService.findByResourceProject(task.getProject(), task.getAssignedTo());
				double total = 0;
				for(OrganizationCategory orgcat : orgList) {
					total = total + task.getTotalHoursSpent() * orgcat.getRate();
				}
				if(finalMap.containsKey(String.valueOf(year))) {
					float prevtotal = Float.valueOf(finalMap.get(String.valueOf(year)));
					total = total + prevtotal;
					finalMap.put(String.valueOf(year), Double.toString(total));
				} else if(currentYear >= year){
					finalMap.put(String.valueOf(year), Double.toString(total));
				}
			}
			treeMap = new TreeMap<String, String>(finalMap);
			label = new ArrayList<>(treeMap.keySet());
			data = new ArrayList<>(treeMap.values());
			break;
		
		case "isSameYear" :
			
			numberMap =  new TreeMap<>();
			for(Task task: taskList) {
			
				cal.setTime(task.getDueDate());
				int month = cal.get(Calendar.MONTH);
				orgList = orgService.findByResourceProject(task.getProject(), task.getAssignedTo());
				double total = 0;
				for(OrganizationCategory orgcat : orgList) {
					total = total + task.getTotalHoursSpent() * orgcat.getRate();
				}
				if(numberMap.containsKey(month)) {
					float prevtotal = Float.valueOf(numberMap.get(month));
					total = total + prevtotal;
				} else if(today.after(cal)){
					numberMap.put(month, Double.toString(total));
				}
			}
			Map<String, String> monthMap =  new HashMap<>();
			for(int mnth : numberMap.keySet())
			{
				monthMap.put(getMonthForInt(mnth), numberMap.get(mnth));
			}
			List<Integer> mlabel = new ArrayList<>(numberMap.keySet());
			List<String> monthList = new ArrayList<String>();
			for(int m : mlabel) {
				monthList.add(getMonthForInt(m));
			}
			data = new ArrayList<>(numberMap.values());
			label = new ArrayList<String>();
			label.addAll(monthList);
			
			break;
		case "isWithinYear":
			
			Map<String, String>  dateMap =  new TreeMap<>();
			SimpleDateFormat mnthYearFormatter = new SimpleDateFormat("yyyy/MM");
			for(Task task: taskList) {
			
				cal.setTime(task.getDueDate());
				String mmYY = mnthYearFormatter.format(task.getDueDate());
				orgList = orgService.findByResourceProject(task.getProject(), task.getAssignedTo());
				double total = 0;
				for(OrganizationCategory orgcat : orgList) {
					total = total + task.getTotalHoursSpent() * orgcat.getRate();
				}
				if(dateMap.containsKey(mmYY)) {
					float prevtotal = Float.valueOf(dateMap.get(mmYY));
					total = total + prevtotal;
				} else if(today.after(cal)) {
					dateMap.put(mmYY, Double.toString(total));
				}
			}
			/*Map<String, String> monthMap =  new HashMap<>();
			for(Date mnth : dateMap.keySet())
			{
				monthMap.put(formatter.format(mnth), dateMap.get(mnth));
			}*/
			data = new ArrayList<>(dateMap.values());
			label = new ArrayList<>(dateMap.keySet());
			break;
			
		case "isSameMonthOfSameYear":

			numberMap =  new TreeMap<>(); 
			for(Task task: taskList) {
				
				cal.setTime(task.getDueDate());
				int day = cal.get(Calendar.DATE);
				orgList = orgService.findByResourceProject(task.getProject(), task.getAssignedTo());
				double total = 0;
				for(OrganizationCategory orgcat : orgList) {
					total = total + task.getTotalHoursSpent() * orgcat.getRate();
				}
				if(numberMap.containsKey(day)) {
					float prevtotal = Float.valueOf(numberMap.get(day));
					total = total + prevtotal;
				} else if(today.after(cal)){
					numberMap.put(day, Double.toString(total));
				}
			}
			List<Integer> mlabel2 = new ArrayList<>(numberMap.keySet());
			List<String> daysList = new ArrayList<String>();
			for(int m : mlabel2) {
				daysList.add(String.valueOf(m));
			}
			label = new ArrayList<String>();
			label.addAll(daysList);
			data = new ArrayList<>(numberMap.values());
			break;
		}
		
		double maxBudget = 0;
		for(String max : data) {
			double tempBudget = Double.valueOf(max);
			if(maxBudget < tempBudget) {
				maxBudget = tempBudget;
			}
		}
		
		bgt1.setData(data);
		bgt1.setLabel(label);
		bgt1.setMaxBudget(new BigDecimal(maxBudget));
		return bgt1;
	}
	
	/***
	 * This method calculates the time span between given input dates and return a string.
	 * 
	 * @param fromDate
	 * @param toDate
	 * @return
	 */
	private String calculateTimeSpanBetweenDates(Date fromDate, Date toDate) {
		
		Calendar fromCal = Calendar.getInstance();
		fromCal.setTime(fromDate);
		Calendar toCal = Calendar.getInstance();
		toCal.setTime(toDate);
		
		boolean isSameYear = isSameYear(fromCal, toCal);
		boolean isSameMonthOfSameYear = isSameMonthOfSameYear(fromCal, toCal);
		boolean isWithinYear = isWithinYear(fromCal, toCal);
		//boolean multiYearSpan = false;
		
		if(!isSameYear && !isSameMonthOfSameYear && !isWithinYear) {
			return "multiYearSpan";
		} else if(isSameYear && !isSameMonthOfSameYear) { 
			return "isSameYear";
		} else if (isSameMonthOfSameYear) {
			return "isSameMonthOfSameYear";
		} else if(isWithinYear){
			return "isWithinYear";
		} else {
			return "isWithinYear";
		}
	}

	/***
	 * This is date utility method to check if given two dates are within same year
	 * Ex: 02/01/2015, 09/30/2015
	 * @param fromDt
	 * @param toDt
	 * @return
	 */
	public boolean isSameYear(Calendar fromDt, Calendar toDt) {
		int fromYear = fromDt.get(Calendar.YEAR);
		int toYear = toDt.get(Calendar.YEAR);
		
		return (fromYear==toYear);
	}
	
	/***
	 * This is date utility method to check if given two dates are within same month of same year
	 * Ex: 09/01/2015, 09/30/2015
	 * @param fromDt
	 * @param toDt
	 * @return
	 */
	public boolean isSameMonthOfSameYear(Calendar fromDt, Calendar toDt) {
		
		int fromYear = fromDt.get(Calendar.YEAR);
		int toYear = toDt.get(Calendar.YEAR);
		boolean isSameMonthInYear = false;
		if(fromYear == toYear) {
			int fromMonth = fromDt.get(Calendar.MONTH);
			int toMonth = toDt.get(Calendar.MONTH);
			if(fromMonth==toMonth) {
				isSameMonthInYear = true;
			}
		}
		
		return isSameMonthInYear;
	}
	
	/***
	 * This is date utility method to check if given two dates span within a year, can be two consecutive years.
	 * Ex: 09/28/2015, 09/28/2016
	 * @param fromDt
	 * @param toDt
	 * @return
	 */
	public boolean isWithinYear(Calendar fromDt, Calendar toDt) {
		
		boolean isSameMonthInYear = false;
		if(fromDt.get(Calendar.MONTH) == toDt.get(Calendar.MONTH))
			if(fromDt.get(Calendar.DATE) == toDt.get(Calendar.DATE))
				if(toDt.get(Calendar.YEAR)- fromDt.get(Calendar.YEAR) == 1)
				{
					isSameMonthInYear = true;
				}
		
		return isSameMonthInYear;
	}
	
	/***
	 * This is date utility method to get month name given month number.
	 * Ex: 0 - January, 1- February
	 * @param m
	 * @return
	 */
	public String getMonthForInt(int m) {
	    String month = "invalid";
	    DateFormatSymbols dfs = new DateFormatSymbols();
	    String[] months = dfs.getMonths();
	    if (m >= 0 && m <= 11 ) {
	        month = months[m];
	    }
	    return month;
	}
	
	/***
	 * This method validates input data against basic business rules and missing required fields.
	 * 
	 * @param search
	 * @return
	 */
	public List<ObjectError> validate(BudgetTimeInputJSON search) {
		
		List<ObjectError> errorList = new ArrayList<>();
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        Date fromDate = null;
        Date toDate = null;
			
		if(StringUtils.isEmpty(search.getProjectId()) || search.getProjectId().equalsIgnoreCase("0")) {
			ObjectError projError = new ObjectError("projectId", "Project can not be empty.");
			errorList.add(projError);
		}
		if(StringUtils.isEmpty(search.getFromDate())) {
			ObjectError fromDtError = new ObjectError("fromDate", "From Date can not be empty.");
			errorList.add(fromDtError);
		} else {
			try {
				fromDate = formatter.parse(search.getFromDate());
				if(fromDate.after(Calendar.getInstance().getTime())) {
					ObjectError fromDtError = new ObjectError("fromDate", "Future date not allowed for From Date");
					errorList.add(fromDtError);
				}
				/*if(proj.getStartDate() != null &&  fromDate.before(proj.getStartDate())) {
					ObjectError fromDtError = new ObjectError("fromDate", "Given From date is earlier than selected Project creation Date.");
					errorList.add(fromDtError);
				}*/
			} catch (ParseException e) {
				logger.debug("Error while parsing from date");
			}
		}
		if(StringUtils.isEmpty(search.getToDate())) {
			ObjectError toDtError = new ObjectError( "toDate", "To Date can not be empty.");
			errorList.add(toDtError);
		} else {
			try {
				toDate = formatter.parse(search.getToDate());
				if(toDate.after(Calendar.getInstance().getTime())) {
					ObjectError toDtError = new ObjectError("toDate", "Future date not allowed for To Date");
					errorList.add(toDtError);
				}
				if(toDate.before(fromDate)) {
					ObjectError toDtError = new ObjectError("toDate", "To Date should be later than From Date");
					errorList.add(toDtError);
				}
			} catch (ParseException e) {
				logger.debug("Error while parsing to date");
			}
		}
		
		return errorList;
			
	}
}
