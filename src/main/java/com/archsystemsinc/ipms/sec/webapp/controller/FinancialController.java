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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.archsystemsinc.ipms.poi.service.UploadService;
import com.archsystemsinc.ipms.sec.model.FinancialExpenses;
import com.archsystemsinc.ipms.sec.model.FinancialHours;
import com.archsystemsinc.ipms.sec.model.FinancialReportType;
import com.archsystemsinc.ipms.sec.model.Principal;
import com.archsystemsinc.ipms.sec.model.Project;
import com.archsystemsinc.ipms.sec.model.dto.FinancialReportSearchParams;
import com.archsystemsinc.ipms.sec.persistence.service.IFinancialExpensesService;
import com.archsystemsinc.ipms.sec.persistence.service.IFinancialHeaderService;
import com.archsystemsinc.ipms.sec.persistence.service.IFinancialHoursService;
import com.archsystemsinc.ipms.sec.persistence.service.IPrincipalService;
import com.archsystemsinc.ipms.sec.persistence.service.IProjectService;

@Controller
public class FinancialController {
	
	@Autowired
	private IProjectService projectService;
	
	@Autowired
	private IPrincipalService principalService;
	
	@Autowired
	private IFinancialHeaderService financialHeaderService;
	
	@Autowired
	private IFinancialExpensesService financialExpensesService;
	
	@Autowired
	private IFinancialHoursService financialHoursService;
	
	@Autowired
	private UploadService uploadService;
	
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

	
	@RequestMapping(value = "/uploadfinancial" , method = RequestMethod.GET)
	public String uploadFinancial(final Model model) {
		//final Issue issue = new Issue();
		//final Metrics metrics = new Metrics();
		model.addAttribute(new FileUpload());
		//model.addAttribute("metrics", metrics);
		model.addAttribute(("referenceData"), referenceData());
		return "uploadfinancial";
	}
	
	
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
	
	@RequestMapping(value = "/choosefinancialreport", method = {RequestMethod.GET, RequestMethod.POST})
	public String choosefinancialreport(@Valid @ModelAttribute("searchParam") final FinancialReportSearchParams searchParam,
			final BindingResult result, final Model model, HttpServletRequest request) {
		
		chooseReport(searchParam, model);
		return "choosefinancialreport";
		
	}
		
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
