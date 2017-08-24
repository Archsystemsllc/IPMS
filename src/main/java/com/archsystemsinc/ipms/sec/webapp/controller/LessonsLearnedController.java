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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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
import com.archsystemsinc.ipms.poi.service.DownloadService;
import com.archsystemsinc.ipms.sec.model.Issue;
import com.archsystemsinc.ipms.sec.model.LessonsLearned;
import com.archsystemsinc.ipms.sec.model.Meeting;
import com.archsystemsinc.ipms.sec.model.Principal;
import com.archsystemsinc.ipms.sec.model.RevisionHistory;
import com.archsystemsinc.ipms.sec.model.Task;
import com.archsystemsinc.ipms.sec.persistence.service.IIssueService;
import com.archsystemsinc.ipms.sec.persistence.service.ILessonsLearnedService;
import com.archsystemsinc.ipms.sec.persistence.service.IMeetingService;
import com.archsystemsinc.ipms.sec.persistence.service.IPrincipalService;
import com.archsystemsinc.ipms.sec.persistence.service.IProjectService;
import com.archsystemsinc.ipms.sec.persistence.service.IRevisionHistoryService;
import com.archsystemsinc.ipms.sec.util.GenericConstants;
import com.archsystemsinc.ipms.web.common.AbstractController;

@Controller
public class LessonsLearnedController extends
		AbstractController<LessonsLearned> {

	public LessonsLearnedController() {
		super(LessonsLearned.class);
	}

	private final Log log = LogFactory.getLog(LessonsLearnedController.class);

	@Autowired
	private ILessonsLearnedService service;

	@Autowired
	private IProjectService projectService;

	@Autowired
	private IPrincipalService principalService;

	@Autowired
	private IIssueService issueService;

	@Autowired
	private IMeetingService meetingService;

	@Autowired
	private DownloadService downloadService;
	
	@Autowired
	private IRevisionHistoryService revisionHistoryService;

	@Override
	@InitBinder
	public void initBinder(final WebDataBinder binder) {
		final SimpleDateFormat dateFormat = new SimpleDateFormat(
				GenericConstants.DEFAULT_DATE_FORMAT);
		dateFormat.setLenient(false);
		// true passed to CustomDateEditor constructor means convert empty
		// String to null
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				dateFormat, true));
	}

	/**
	 * Downloads the report as an Excel format.
	 * <p>
	 * Make sure this method doesn't return any model. Otherwise, you'll get an
	 * "IllegalStateException: getOutputStream() has already been called for this response"
	 * final HttpServletResponse response, final String sheetName, final
	 * List<Object> exportList, final String[] coloumnNames
	 */
	@RequestMapping(value = "/lessonslearneds/xls", method = RequestMethod.GET)
	public void getXLS(final HttpServletResponse response, final Model model,
			final java.security.Principal principal)
			throws ClassNotFoundException {
		logger.debug("Received request to download lessons learned report as an XLS");
		final String sheetName = GenericConstants.LESSONS_LEARNED;
		final String[] coloumnNames = { "1", "2", "3", "4", "5", "6" };
		// Delegate to downloadService. Make sure to pass an instance of
		// HttpServletResponse
		final List lessonsLearned = service.findAll();
		downloadService.downloadXLS(response, sheetName, lessonsLearned,
				coloumnNames);
	}

	@RequestMapping(value = "/lessonslearneds")
	public String lessonsLearned(final Model model) {
		final List<LessonsLearned> lessonsLearneds = service.findAll();
		model.addAttribute("lessonsLearneds", lessonsLearneds);
		return "lessonslearneds";
	}

	@RequestMapping(value = "/lessonslearned/{id}", method = RequestMethod.GET)
	public String lessonsLearned(@PathVariable("id") final Long id,
			final Model model) {
		final LessonsLearned lessonsLearned = service.findOne(id);
		lessonsLearned.setIssueId(lessonsLearned.getIssue().getId());
		final List<RevisionHistory> revisionHist = revisionHistoryService
				.findByTask(id);
		lessonsLearned.setRevisions(new HashSet<RevisionHistory>(revisionHist));
		model.addAttribute("lessonsLearned", lessonsLearned);
		model.addAttribute("referenceData", referenceData());
		return "lessonslearned";
	}

	@RequestMapping(value = "/new-lessonslearned", method = RequestMethod.GET)
	public String newLessonsLearned(final Model model) {
		final LessonsLearned lessonsLearned = new LessonsLearned();
		model.addAttribute("lessonsLearned", lessonsLearned);
		model.addAttribute("referenceData", referenceData());
		return "lessonslearnedsadd";
	}

	@RequestMapping(value = "/edit-lessonslearned/{id}", method = RequestMethod.GET)
	public String editLessonsLearned(@PathVariable("id") final Long id,
			final Model model) {
		final LessonsLearned lessonsLearned = service.findOne(id);
		if (lessonsLearned.getMeeting() != null) {
			lessonsLearned.setMeetingId(lessonsLearned.getMeeting().getId());
		}
		model.addAttribute("lessonsLearned", lessonsLearned);
		model.addAttribute("referenceData", referenceData());
		return "lessonslearnedsedit";
	}
	
	private List<RevisionHistory> buildRevisionHistory(LessonsLearned oldLessonsLearned,
			LessonsLearned lessonsLearned, Principal principal) {
		List<RevisionHistory> returnList = new ArrayList<RevisionHistory>();
		RevisionHistory revisionHistory = null;
		
		if(!oldLessonsLearned.getSummary().equalsIgnoreCase(
				lessonsLearned.getSummary())){
			revisionHistory = new RevisionHistory();
			revisionHistory.setPrincipal(principal);
			revisionHistory.setPrincipalId(principal.getId());
			revisionHistory.setLessonsLearnedId(lessonsLearned.getId());
			
			revisionHistory.setText("Risk Level : " + oldLessonsLearned.getSummary() + " - "
					+ lessonsLearned.getSummary());
			revisionHistory.setLessonsLearned(lessonsLearned);
			returnList.add(revisionHistory);
		}
		return returnList;
		
	}

	@RequestMapping(value = "/new-lessonslearned", method = RequestMethod.POST)
	public String addLessonsLearned(
			@Valid @ModelAttribute("lessonsLearned") final LessonsLearned lessonsLearned,
			final BindingResult result, final Model model) {
		String returnView = "";
		final Issue issue = issueService.findOne(lessonsLearned.getIssueId());
		final Meeting meeting = meetingService.findOne(lessonsLearned
				.getMeetingId());
		lessonsLearned.setIssue(issue);
		lessonsLearned.setMeeting(meeting);
		if (result.hasErrors()) {
			returnView = "lessonslearnedsadd";
			model.addAttribute("lessonsLearned", lessonsLearned);
			model.addAttribute("referenceData", referenceData());
		} else {
			service.create(lessonsLearned);
		
			if (issue != null)
				returnView = "redirect:issue/" + issue.getId() + "#tab2";
			else if (meeting != null)
				returnView = "redirect:meeting/" + meeting.getId() + "#tab5";
			else
				returnView = "redirect:artifacts";
			model.addAttribute("success", "success.lessonsLearned.created");
			if (meeting != null) {
				model.addAttribute("page", "lessons");
				returnView = "redirect:/app/meeting/" + meeting.getId();
			} else {
				returnView = "redirect:artifacts";
			}
		}
		return returnView;
	}

	@RequestMapping(value = "/edit-lessonslearned", method = RequestMethod.POST)
	public String updateLessonsLearned(
			@Valid @ModelAttribute("lessonsLearned") final LessonsLearned lessonsLearned,
			final BindingResult result, final Model model) {
		String returnView = "";
		final Issue issue = issueService.findOne(lessonsLearned.getIssueId());
		lessonsLearned.setIssue(issue);
		final Meeting meeting = meetingService.findOne(lessonsLearned
				.getMeetingId());
		lessonsLearned.setMeeting(meeting);
		if (result.hasErrors()) {
			returnView = "lessonslearnedsedit";
			model.addAttribute("lessonsLearned", lessonsLearned);
			model.addAttribute("referenceData", referenceData());
		} else {
			final String currentUser = SecurityContextHolder.getContext()
					.getAuthentication().getName();
			Principal principal = principalService.findByName(currentUser);
			LessonsLearned oldLessonsLearned = service.findOne(lessonsLearned.getId());
			List<RevisionHistory> histList = buildRevisionHistory(oldLessonsLearned,
					lessonsLearned, principal);
			service.update(lessonsLearned);
			if(null != histList && histList.size() > 0){
				revisionHistoryService.bulkCreate(histList);
			}
			model.addAttribute("success","success.lessonsLearned.updated");
			if (meeting != null) {
				model.addAttribute("page", "lessons");
				returnView = "redirect:/app/meeting/" + meeting.getId();
			} else {
				returnView = "redirect:lessonslearneds";
			}
		}
		model.addAttribute("lessonsLearned", lessonsLearned);
		model.addAttribute("referenceData", referenceData());
		return returnView;
	}

	@RequestMapping(value = "/delete-lessonslearned/{id}/{returnPage}", method = RequestMethod.GET)
	public String deleteLessonsLearned(@PathVariable("id") final Long id,
			@PathVariable("returnPage") final String returnPage,
			final Model model) {
		String returnView = "";
		service.delete(id);
		model.addAttribute("success", "success.lessonsLearned.deleted");
		if (returnPage.equalsIgnoreCase("")) {
			returnView = "forward:lessonslearneds";
		} else {
			returnView = "forward:" + returnPage;
		}
		return returnView;
	}

	protected Map referenceData() {
		final Map referenceData = new HashMap();
		final List<Issue> ilist = issueService.findAll();
		final Map<Integer, String> iList = new LinkedHashMap<Integer, String>();
		for (int i = 0; i < ilist.size(); i++) {
			iList.put(ilist.get(i).getId().intValue(), ilist.get(i)
					.getSummary());
		}
		referenceData.put("issueList", iList);

		final List<Meeting> meetingDbList = meetingService.findAll();
		final Map<Integer, String> meetingList = new LinkedHashMap<Integer, String>();
		meetingList.put(0, "");
		for (int i = 0; i < meetingDbList.size(); i++) {
			meetingList.put(meetingDbList.get(i).getId().intValue(),
					meetingDbList.get(i).getTitle());
		}
		referenceData.put("meetingList", meetingList);

		return referenceData;
	}

	@Override
	protected IService<LessonsLearned> getService() {
		return service;
	}

}
