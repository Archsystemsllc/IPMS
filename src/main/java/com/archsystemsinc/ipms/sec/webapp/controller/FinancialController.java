/**
* Copyright (c) 2017, Archsystems Inc and/or its affiliates. All rights reserved.
*/
package com.archsystemsinc.ipms.sec.webapp.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.lang.time.DateUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.archsystemsinc.ipms.poi.service.UploadService;
import com.archsystemsinc.ipms.sec.model.FinancialExpenses;
import com.archsystemsinc.ipms.sec.model.FinancialHours;
import com.archsystemsinc.ipms.sec.model.FinancialReportType;
import com.archsystemsinc.ipms.sec.model.FinancialsUpload;
import com.archsystemsinc.ipms.sec.model.Principal;
import com.archsystemsinc.ipms.sec.model.Project;
import com.archsystemsinc.ipms.sec.model.dto.FinancialReportSearchParams;
import com.archsystemsinc.ipms.sec.persistence.service.IFinancialExpensesService;
import com.archsystemsinc.ipms.sec.persistence.service.IFinancialHeaderService;
import com.archsystemsinc.ipms.sec.persistence.service.IFinancialHoursService;
import com.archsystemsinc.ipms.sec.persistence.service.IPrincipalService;
import com.archsystemsinc.ipms.sec.persistence.service.IProjectService;

/**
* The Financial Controller for an financial related end points
* @author Benigna
* @version 0.2.1
*/
@Controller
public class FinancialController {
	
	/**
	 * The Autowired ProjectService
	 */
	@Autowired
	private IProjectService projectService;
	
	/**
	 * The Autowired PrincipalService
	 */
	@Autowired
	private IPrincipalService principalService;
	
	/**
	 * The Autowired FinancialHeaderService
	 */
	@Autowired
	private IFinancialHeaderService financialHeaderService;
	
	/**
	 * The Financial Expenses service
	 */
	@Autowired
	private IFinancialExpensesService financialExpensesService;
	
	/**
	 * The financial Hours Service
	 */
	@Autowired
	private IFinancialHoursService financialHoursService;
	
	/**
	 * The upload service
	 */
	@Autowired
	private UploadService uploadService;
	
	/**
	 * This methods finds all reference datas
	 * 
	 * @return The reference data to attach on the model
	 */
	protected Map referenceData() {
		final Map referenceData = new HashMap();
		final List<Project> projectlist = projectService.findActiveProjects();
		final Map<Integer, String> pList = new LinkedHashMap<Integer, String>();
		pList.put(0, "--Select Project--");
		for (int i = 0; i < projectlist.size(); i++) {
			pList.put(projectlist.get(i).getId().intValue(), projectlist.get(i)
					.getName());
		}
		referenceData.put("projectList", pList);
		
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
		
		
		final Map<Integer, String> reportTypes = new LinkedHashMap<Integer, String>();
		reportTypes.put(0, "--Select Report Type --");
		for(FinancialReportType type: FinancialReportType.values()) {
			reportTypes.put(type.getKey(), type.getValue());
		}
		referenceData.put("reportTypes", reportTypes);
		
		
		return referenceData;
	}

	
	/**
	 * The end point to upload the financial data
	 * @param model The <code>Model</code>
	 * @return The tiles definition
	 */
	@RequestMapping(value = "/uploadfinancial" , method = RequestMethod.GET)
	public String uploadFinancial(final Model model) {
		model.addAttribute(new FinancialsUpload());
		model.addAttribute(("referenceData"), referenceData());
		return "uploadfinancial";
	}
	
	@RequestMapping(value = "/uploadfinancial", method = RequestMethod.POST)
	public String uploadFinancial(@ModelAttribute("financialsUpload") final FinancialsUpload uploadItem, final Principal principal,
			final BindingResult result, final HttpServletRequest request, final RedirectAttributes redirectAttributes, final Model model) throws InvalidFormatException {		
				
	if(result.hasErrors()) {
		redirectAttributes.addFlashAttribute("fileUploadError", "error.upload.internal.problem");
		return "redirect:uploadfinancial";
	} else {
		//Delegate to UploadService
		String returnString = uploadService.uploadXLS(uploadItem, redirectAttributes);
		if(returnString.equalsIgnoreCase("fileUploadError")) {
			return "redirect:uploadfinancial";
		} else {
			return returnString;
		}
	}
  }
	
	/**
	 * An utility method to load the reference data for the financial search
	 * @param searchParam
	 * @param model
	 */
	private void chooseReport(FinancialReportSearchParams searchParam,Model model) {
		Map referenceData = referenceData();
		if(searchParam.getReportTypeId() != null && searchParam.getReportTypeId() != 0l) {
			Date inceptionStartDate = financialHeaderService.findInceptionStartDate(searchParam.getProjectId());
			Date inceptionEndDate = financialHeaderService.findInceptionEndtDate(searchParam.getProjectId());
			if(inceptionStartDate != null && inceptionEndDate != null) {
				SimpleDateFormat fmt = new SimpleDateFormat("MM/dd/yyyy");
				searchParam.setInceptionStartDate(fmt.format(inceptionStartDate));
				searchParam.setInceptionEndDate(fmt.format(inceptionEndDate));
				if(inceptionStartDate != null && inceptionEndDate != null) {
					referenceData.put(("reportIds"), getReportIds(inceptionStartDate, inceptionEndDate, searchParam.getReportTypeId().intValue()));
				}
			}
		}
		model.addAttribute("referenceData", referenceData);
	}
	
	/**
	 * Search for a financial report
	 * @param searchParam The search param
	 * @param result the <code>BindingResult</code>
	 * @param model the <code>Model</code>
	 * @param request the <code>HttpServletRequest</code>
	 * @return the tiles definition for this endpoint
	 */
	@RequestMapping(value = "/choosefinancialreport", method = {RequestMethod.GET, RequestMethod.POST})
	public String choosefinancialreport(@Valid @ModelAttribute("searchParam") final FinancialReportSearchParams searchParam,
			final BindingResult result, final Model model, HttpServletRequest request) {
		
		chooseReport(searchParam, model);
		return "choosefinancialreport";
		
	}
	
	/**
	 * Search Results (Financial Report) for the given search param	
	 * @param searchParam The search param
	 * @param result <code>BindingResult</code>
	 * @param model <code>Model</code>
	 * @param request <code>HttpServletRequest</code>
	 * @return the tiles definition for the financial results page
	 * @throws Exception
	 */
	@RequestMapping(value = "/financialreport" , method = RequestMethod.POST)
	public String financialreport(@ModelAttribute("searchParam") final FinancialReportSearchParams searchParam,
			final BindingResult result, final Model model, HttpServletRequest request) throws Exception {
	
		if(searchParam.getReportIds() == null || searchParam.getReportIds().size()==0) {
			chooseReport(searchParam, model);
			result.rejectValue("reportIds", "financial.reportIds.missing");
			return "choosefinancialreport";
		}
		
		Project project = projectService.findOne(searchParam.getProjectId());
		searchParam.setProjectName(project.getName());
		
		List<java.util.Date> dateSet = new ArrayList<>();
		SimpleDateFormat fmt = new SimpleDateFormat("MM/dd/yyyy");
		for(String reportId:searchParam.getReportIds()) {
			String id[]=reportId.split(" - ");
			dateSet.add(fmt.parse(id[0]));
			dateSet.add(fmt.parse(id[1]));
		}
		
		Date startDate = dateSet.get(0);
		Date endDate = dateSet.get(dateSet.size()-1);
		
		searchParam.setStartDate(fmt.format(startDate));
		searchParam.setEndDate(fmt.format(endDate));
		searchParam.setReportTypeName(FinancialReportType.getFinancialReportType(searchParam.getReportTypeId().intValue()).getValue());
		
		List<FinancialExpenses> financialExpenses = financialExpensesService.findByProjectAndPeriods(searchParam.getProjectId(),startDate, endDate);
		model.addAttribute("financialExpenses",financialExpenses);
		
		List<FinancialHours> financialHours = financialHoursService.findByProjectAndPeriods(searchParam.getProjectId(),startDate, endDate);
		model.addAttribute("financialHours",financialHours);
		
		
		model.addAttribute(("referenceData"), referenceData());
		return "financialreport";
	}
	

	/**
	 * An utility method that returns the date rate in the id format
	 * @param startDate the start date
	 * @param endDate the end date
	 * @param months no of months to split for the given date ranage
	 * @return the slplitted id's
	 */
	private Map<String, String> getReportIds(Date startDate, Date endDate, int months) {
		
		SimpleDateFormat fmt = new SimpleDateFormat("MM/dd/yyyy");
		final Map<String, String> reportIds = new LinkedHashMap<String, String>();
		Date st = new Date(startDate.getTime());
		while(st.before(endDate)) {
			Date end = new Date(st.getTime());
			end = new Date(DateUtils.addDays(end, -1).getTime());
			end = new Date(DateUtils.addMonths(end, months).getTime());
			String reportId = fmt.format(st) + " - " + fmt.format(end);
			reportIds.put(reportId, reportId);
			st = new Date(DateUtils.addDays(end, 1).getTime());
		}
		return reportIds;
	}


}
