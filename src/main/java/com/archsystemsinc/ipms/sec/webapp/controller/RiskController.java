/*
 * Copyright 2011 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.archsystemsinc.ipms.sec.webapp.controller;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import com.archsystemsinc.ipms.poi.service.DownloadService;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.archsystemsinc.ipms.persistence.service.IService;
import com.archsystemsinc.ipms.sec.model.ActionItemStatus;
import com.archsystemsinc.ipms.sec.model.Principal;
import com.archsystemsinc.ipms.sec.model.Program;
import com.archsystemsinc.ipms.sec.model.Project;
import com.archsystemsinc.ipms.sec.model.RevisionHistory;
import com.archsystemsinc.ipms.sec.model.Risk;
import com.archsystemsinc.ipms.sec.model.RiskImpact;
import com.archsystemsinc.ipms.sec.model.RiskMitigatingFactors;
import com.archsystemsinc.ipms.sec.model.RiskStatus;
import com.archsystemsinc.ipms.sec.model.Task;
import com.archsystemsinc.ipms.sec.persistence.service.IIssueService;
import com.archsystemsinc.ipms.sec.persistence.service.IMeetingService;
import com.archsystemsinc.ipms.sec.persistence.service.IPrincipalService;
import com.archsystemsinc.ipms.sec.persistence.service.IProgramService;
import com.archsystemsinc.ipms.sec.persistence.service.IProjectService;
import com.archsystemsinc.ipms.sec.persistence.service.IRevisionHistoryService;
import com.archsystemsinc.ipms.sec.persistence.service.IRiskService;
import com.archsystemsinc.ipms.sec.util.GenericConstants;
import com.archsystemsinc.ipms.web.common.AbstractController;

@Controller
public class RiskController extends AbstractController<Risk> {

	public RiskController() {
		super(Risk.class);
	}

	private final Log log = LogFactory.getLog(RiskController.class);

	@Autowired
	private IRiskService service;

	@Autowired
	private IProjectService projectService;

	@Autowired
	private IProgramService programService;

	@Autowired
	private IPrincipalService principalService;

	@Autowired
	private IIssueService issueService;

	@Autowired
	private IMeetingService meetingService;

	@Autowired
	private IRevisionHistoryService revisionHistoryService;

	@Autowired
	private DownloadService downloadService;

	@Override
	@InitBinder
	public void initBinder(final WebDataBinder binder) {
		final SimpleDateFormat dateFormat = new SimpleDateFormat(GenericConstants.DEFAULT_DATE_FORMAT);
		dateFormat.setLenient(false);
		// true passed to CustomDateEditor constructor means convert empty
		// String to null
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}

	@RequestMapping(value = "/risks")
	public String risk(final Model model) {
		final List<Risk> risks = service.findAll();
		model.addAttribute("risks", risks);
		return "risks";
	}

	/**
	 * Downloads the report as an Excel format.
	 * <p>
	 * Make sure this method doesn't return any model. Otherwise, you'll get an
	 * "IllegalStateException: getOutputStream() has already been called for this
	 * response" final HttpServletResponse response, final String sheetName, final
	 * List<Object> exportList, final String[] coloumnNames
	 */
	@RequestMapping(value = "/risks/xls", method = RequestMethod.GET)
	public void getXLS(final HttpServletResponse response, final Model model, final java.security.Principal principal)
			throws ClassNotFoundException {
		logger.debug("Received request to download issues report as an XLS");
		final String sheetName = GenericConstants.RISKS;
		final String[] coloumnNames = { "1", "2", "3", "4", "5", "6" };
		// Delegate to downloadService. Make sure to pass an instance of
		// HttpServletResponse
		final Principal currentUser = principalService.findByName(principal.getName());
		final List risks = service.findAll();
		if (risks != null && risks.size() > 0) {
			downloadService.downloadXLS(response, sheetName, risks, coloumnNames);
		}

	}

	@RequestMapping(value = "/risk/{id}", method = RequestMethod.GET)
	public String risk(@PathVariable("id") final Long id, final Model model) {
		final Risk risk = service.findOne(id);
		try {
			risk.setProgramId(risk.getProgram().getId());
			risk.setProjectId(risk.getProject().getId());
		} catch (final Exception e) {
		}
		final List<RevisionHistory> revisionHist = revisionHistoryService.findByRisk(id);
		risk.setRevisions(new HashSet<RevisionHistory>(revisionHist));
		model.addAttribute("risk", risk);
		model.addAttribute("referenceData", referenceData());
		return "risk";
	}

	@RequestMapping(value = "/edit-risk/{id}", method = RequestMethod.GET)
	public String editRisk(@PathVariable("id") final Long id, final Model model) {
		final Risk risk = service.findOne(id);
		model.addAttribute("risk", risk);
		model.addAttribute("referenceData", referenceData());
		return "risksedit";
	}

	private List<RevisionHistory> buildRevisionHistory(Risk oldRisk, Risk risk, Principal principal) {
		List<RevisionHistory> returnList = new ArrayList<RevisionHistory>();
		RevisionHistory revisionHistory = null;
		if (!oldRisk.getImpact().equalsIgnoreCase(risk.getImpact())) {

			revisionHistory = new RevisionHistory();
			revisionHistory.setPrincipal(principal);
			revisionHistory.setPrincipalId(principal.getId());
			revisionHistory.setRiskId(risk.getId());

			revisionHistory.setText("Priority String : " + oldRisk.getImpact() + " - " + risk.getImpact());
			revisionHistory.setRisk(risk);
			long time = (new Date()).getTime();
			revisionHistory.setUpdatedAt(new Timestamp(time));	
			returnList.add(revisionHistory);
		}

		if (!oldRisk.getLikelihood().equalsIgnoreCase(risk.getLikelihood())) {
			revisionHistory = new RevisionHistory();
			revisionHistory.setPrincipal(principal);
			revisionHistory.setPrincipalId(principal.getId());
			revisionHistory.setRiskId(risk.getId());

			revisionHistory.setText("Risk Level : " + oldRisk.getLikelihood() + " - " + risk.getLikelihood());
			revisionHistory.setRisk(risk);
			long time = (new Date()).getTime();
			revisionHistory.setUpdatedAt(new Timestamp(time));	
			returnList.add(revisionHistory);
		}
		return returnList;
	}

	@RequestMapping(value = "/new-risk", method = RequestMethod.GET)
	public String newRisk(final Model model) {
		final Risk risk = new Risk();
		model.addAttribute("risk", risk);
		model.addAttribute("referenceData", referenceData());
		return "risksadd";
	}

	@RequestMapping(value = "/new-projectrisk/{id}", method = RequestMethod.GET)
	public String newRisk(@PathVariable("id") final Long id, final Model model) {
		final Risk risk = new Risk();
		final Project project = projectService.findOne(id);
		risk.setProject(project);
		risk.setProjectId(id);
		model.addAttribute("risk", risk);
		model.addAttribute("referenceData", referenceData());
		return "risksadd";
	}

	@RequestMapping(value = "/new-risk", method = RequestMethod.POST)
	public String addRisk(@Valid @ModelAttribute("risk") final Risk risk, final BindingResult result,
			final Model model) {

		String returnView = "";
		if (risk.getProgramId() != null) {
			final Program program = programService.findOne(risk.getProgramId());
			risk.setProgram(program);
		}
		if (risk.getProjectId() != null) {
			final Project project = projectService.findOne(risk.getProjectId());
			risk.setProject(project);
		}
		if (result.hasErrors()) {
			returnView = "risksadd";
			model.addAttribute("risk", risk);
			model.addAttribute("referenceData", referenceData());
		} else {
			service.create(risk);
			model.addAttribute("success", "success.risk.created");
			if (risk.getProject() != null)
				// page navigates back to view Risks page
				returnView = "redirect:risks";
				/*returnView = "redirect:project/" + risk.getProject().getId() + "?page=risks&success=3";*/
			else if (risk.getProgram() != null)
				returnView = "redirect:program/" + risk.getProgram().getId() + "?page=risks&success=3";
			else
				returnView = "redirect:risks";
		}
		return returnView;
	}

	@RequestMapping(value = "/edit-risk", method = RequestMethod.POST)
	public String updateRisk(@Valid @ModelAttribute("risk") final Risk risk, final BindingResult result, final Model model) {
		// using name as long --bad idea
		String returnView = "";
		if (risk.getProgramId() != null) {
			final Program program = programService.findOne(risk.getProgramId());
			final Project project = projectService.findOne(risk.getProjectId());

			risk.setProgram(program);
			risk.setProject(project);
		}
		if (result.hasErrors()) {
			returnView = "risksedit";
		} else {
			final String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
			Principal principal = principalService.findByName(currentUser);
			Risk oldRisk = service.findOne(risk.getId());
			List<RevisionHistory> histList = buildRevisionHistory(oldRisk, risk, principal);
			service.update(risk);
			model.addAttribute("success", "success.risk.updated");
			if (null != histList && histList.size() > 0) {
				revisionHistoryService.bulkCreate(histList);
			}
			returnView = "forward:risks";
		}
		model.addAttribute("risk", risk);
		model.addAttribute("referenceData", referenceData());
		return returnView;
	}

	@RequestMapping(value = "/delete-risk/{id}/{returnPage}", method = RequestMethod.GET)
	public String deleteRisk(@PathVariable("id") final Long id, @PathVariable("returnPage") final String returnPage,
			final Model model) {
		String returnView = "";
		service.delete(id);
		model.addAttribute("success", "success.risk.deleted");
		if (returnPage.equalsIgnoreCase("")) {
			returnView = "forward:risks";
		} else {
			returnView = "forward:" + returnPage;
		}
		return returnView;
	}

	protected Map referenceData() {
		final Map referenceData = new HashMap();
		final List<Project> listOfProjects = projectService.findAll();
		final Map<Integer, String> prList = new LinkedHashMap<Integer, String>();
		for (int i = 0; i < listOfProjects.size(); i++) {
			prList.put(listOfProjects.get(i).getId().intValue(), listOfProjects.get(i).getName());
		}
		referenceData.put("projectList", prList);
		final List<Program> listOfPrograms = programService.findAll();
		final Map<Integer, String> prgList = new LinkedHashMap<Integer, String>();
		for (int i = 0; i < listOfPrograms.size(); i++) {
			prgList.put(listOfPrograms.get(i).getId().intValue(), listOfPrograms.get(i).getName());
		}
		referenceData.put("programList", prgList);

		final Map<String, String> sList = new LinkedHashMap<String, String>();
		sList.put(RiskStatus.Open.toString(), RiskStatus.Open.toString());
		sList.put(RiskStatus.Closed.toString(), RiskStatus.Closed.toString());
		sList.put(RiskStatus.Pending_Mitigation.toString(), RiskStatus.Pending_Mitigation.toString());

		referenceData.put("statusList", sList);

		final Map<String, String> RiskMitigatingList = new LinkedHashMap<String, String>();
		RiskMitigatingList.put(RiskMitigatingFactors.Acceptance.toString(),
				RiskMitigatingFactors.Acceptance.toString());
		RiskMitigatingList.put(RiskMitigatingFactors.Avoidance.toString(), RiskMitigatingFactors.Avoidance.toString());
		RiskMitigatingList.put(RiskMitigatingFactors.Control.toString(), RiskMitigatingFactors.Control.toString());
		RiskMitigatingList.put(RiskMitigatingFactors.Investigation.toString(),
				RiskMitigatingFactors.Investigation.toString());
		RiskMitigatingList.put(RiskMitigatingFactors.Reduction.toString(), RiskMitigatingFactors.Reduction.toString());
		RiskMitigatingList.put(RiskMitigatingFactors.Transference.toString(),
				RiskMitigatingFactors.Transference.toString());

		referenceData.put("RiskMitigatingListPage", RiskMitigatingList);

		final Map<String, String> RiskImpactList = new LinkedHashMap<String, String>();
		RiskImpactList.put(RiskImpact.CostAndScheduleImpact.toString(), RiskImpact.CostAndScheduleImpact.toString());
		RiskImpactList.put(RiskImpact.CostImpact.toString(), RiskImpact.CostImpact.toString());
		RiskImpactList.put(RiskImpact.NoImpact.toString(), RiskImpact.NoImpact.toString());
		RiskImpactList.put(RiskImpact.Schedule.toString(), RiskImpact.Schedule.toString());

		referenceData.put("RiskImpactListPage", RiskImpactList);

		return referenceData;
	}

	@Override
	protected IService<Risk> getService() {
		return service;
	}

}
