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
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
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
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.archsystemsinc.ipms.persistence.service.IService;
import com.archsystemsinc.ipms.poi.service.DownloadService;
import com.archsystemsinc.ipms.poi.service.UploadService;
import com.archsystemsinc.ipms.sec.model.ActionItem;
import com.archsystemsinc.ipms.sec.model.ActionItemPriority;
import com.archsystemsinc.ipms.sec.model.ActionItemStatus;
import com.archsystemsinc.ipms.sec.model.Issue;
import com.archsystemsinc.ipms.sec.model.Meeting;
import com.archsystemsinc.ipms.sec.model.Principal;
import com.archsystemsinc.ipms.sec.model.RevisionHistory;
import com.archsystemsinc.ipms.sec.persistence.service.IActionItemService;
import com.archsystemsinc.ipms.sec.persistence.service.IIssueService;
import com.archsystemsinc.ipms.sec.persistence.service.IMeetingService;
import com.archsystemsinc.ipms.sec.persistence.service.IPrincipalService;
import com.archsystemsinc.ipms.sec.persistence.service.IProjectService;
import com.archsystemsinc.ipms.sec.persistence.service.IRevisionHistoryService;
import com.archsystemsinc.ipms.sec.util.GenericConstants;
import com.archsystemsinc.ipms.web.common.AbstractController;


@Controller
public class ActionItemController extends AbstractController<ActionItem> {


	public ActionItemController() {
		super(ActionItem.class);
	}

	private final Log log = LogFactory.getLog(ActionItemController.class);

	@Autowired
	private IActionItemService service;

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
	private UploadService uploadService;

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
	@RequestMapping(value = "/actionitems/xls", method = RequestMethod.GET)
	public void getXLS(final HttpServletResponse response, final Model model,
			final java.security.Principal principal)
					throws ClassNotFoundException {
		logger.debug("Received request to download action items report as an XLS");
		final String sheetName = GenericConstants.ACTION_ITEMS;
		final String[] coloumnNames = { "1", "2", "3", "4", "5" };
		// Delegate to downloadService. Make sure to pass an instance of
		// HttpServletResponse
		final Principal currentUser = principalService.findByName(principal
				.getName());
		final List actionItems = service
				.findCurrentUserActionItems(currentUser);
		downloadService.downloadXLS(response, sheetName, actionItems,
				coloumnNames);
	}

	/*@RequestMapping(value = "/actionitems")
	public String actionItem(final Model model,
			final java.security.Principal principal) {
		final Principal currentUser = principalService.findByName(principal
				.getName());
		final List<ActionItem> actionItems = service
				.findCurrentUserActionItems(currentUser);
		Collections.sort(actionItems);
		final ArrayList<ActionItem> actionItemsArrayList = new ArrayList<ActionItem>(
				actionItems);
		model.addAttribute("actionItems", actionItemsArrayList);
		final String icurrentUser = SecurityContextHolder.getContext()
				.getAuthentication().getName();
		model.addAttribute("currentUser", icurrentUser);
		return "actionitems";
	}*/
	/**
	* This class consists service methods for ActionItems View page.*   
	 * @author Nikhil Dasari
	* @version 1.1
	*/
	@RequestMapping(value = "/actionitems")
	public String actionItemstest(final Model model) {
		final List<ActionItem> actionItems = service.findAll();
		model.addAttribute("actionItems", actionItems);
		return "actionitems";
	}

	@RequestMapping(value = "/actionitem/{id}", method = RequestMethod.GET)
	public String actionItem(@PathVariable("id") final Long id,
			final Model model) {
		final String currentUser = SecurityContextHolder.getContext()
				.getAuthentication().getName();
		model.addAttribute("currentUser", currentUser);
		final ActionItem actionItem = service.findOne(id);
		actionItem.setAssignedToId(actionItem.getAssignedTo().getId());
		try {
			actionItem.setMeetingId(actionItem.getMeeting().getId());
		} catch (final Exception e) {
		}
		try {
			actionItem.setIssueId(actionItem.getIssue().getId());
		} catch (final Exception e) {
		}
		final List<RevisionHistory> revisionHist = revisionHistoryService
				.findByActionItem(id);
		actionItem.setRevisions(new HashSet<RevisionHistory>(revisionHist));
		model.addAttribute("actionItem", actionItem);
		model.addAttribute("referenceData", referenceData());
		return "actionitem";
	}

	@RequestMapping(value = "/new-meetingactionitem/{meetingid}", method = RequestMethod.GET)
	public String newMeetingActionItem(
			@Valid @PathVariable("meetingid") final Long meetingid,
			final Model model) {
		final String currentUser = SecurityContextHolder.getContext()
				.getAuthentication().getName();
		model.addAttribute("currentUser", currentUser);
		final ActionItem actionItem = new ActionItem();
		final Meeting meeting = meetingService.findOne(meetingid);
		actionItem.setIssue(null);
		actionItem.setMeeting(meeting);
		actionItem.setMeetingId(meeting.getId());
		model.addAttribute("actionItem", actionItem);
		model.addAttribute("referenceData", referenceData());
		return "actionitemsadd";
	}

	@RequestMapping(value = "/new-issueactionitem/{issueid}", method = RequestMethod.GET)
	public String newIssueActionItem(
			@PathVariable("issueid") final Long issueid,
			final Model model) {
		final String currentUser = SecurityContextHolder.getContext()
				.getAuthentication().getName();
		model.addAttribute("currentUser", currentUser);
		final Issue issue = issueService.findOne(issueid);
		final ActionItem actionItem = new ActionItem();
		actionItem.setMeeting(null);
		actionItem.setIssue(issue);
		actionItem.setIssueId(issue.getId());
		model.addAttribute("actionItem", actionItem);
		model.addAttribute("referenceData", referenceData());
		return "actionitemsadd";
	}

	public static HttpSession getSession() {
		final ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder
				.currentRequestAttributes();
		return attr.getRequest().getSession(true); // true == allow create
	}
	
	@RequestMapping(value = "/new-actionitem", method = RequestMethod.GET)
	public String newActionItem(final Model model) {
		final String currentUser = SecurityContextHolder.getContext()
				.getAuthentication().getName();
		model.addAttribute("currentUser", currentUser);
		final ActionItem actionItem = new ActionItem();
		actionItem.setMeeting(null);
		actionItem.setIssue(null);
		model.addAttribute("actionItem", actionItem);
		model.addAttribute("referenceData", referenceData());
		return "actionitemsadd";
	}
	
	private List<RevisionHistory> buildRevisionHistory(ActionItem oldActionItem,
			ActionItem actionItem, Principal principal) {
		List<RevisionHistory> returnList = new ArrayList<RevisionHistory>();
		RevisionHistory revisionHistory = null;
		if (!oldActionItem.getPriority().equalsIgnoreCase(
				actionItem.getPriority())){
			revisionHistory = new RevisionHistory();
			revisionHistory.setPrincipal(principal);
			revisionHistory.setPrincipalId(principal.getId());
			revisionHistory.setActionItemId(actionItem.getId());
			
			revisionHistory.setText("Priority String : " + oldActionItem.getPriority() + " - "
					+ oldActionItem.getPriority());
			revisionHistory.setActionItem(actionItem);
			returnList.add(revisionHistory);
		}
		
		if(!oldActionItem.getStatus().equalsIgnoreCase(
				actionItem.getStatus())){
			revisionHistory = new RevisionHistory();
			revisionHistory.setPrincipal(principal);
			revisionHistory.setPrincipalId(principal.getId());
			revisionHistory.setActionItemId(actionItem.getId());
			
			revisionHistory.setText("Risk Level : " + oldActionItem.getStatus() + " - "
					+ actionItem.getStatus());
			revisionHistory.setActionItem(actionItem);
			returnList.add(revisionHistory);
		}
		return returnList;
	}

	@RequestMapping(value = "/edit-actionitem/{id}", method = RequestMethod.GET)
	public String editActionItem(@PathVariable("id") final Long id,
			final Model model) {
		final String currentUser = SecurityContextHolder.getContext()
				.getAuthentication().getName();
		model.addAttribute("currentUser", currentUser);
		final ActionItem actionItem = service.findOne(id);
		if(actionItem.getMeeting() != null) {
			actionItem.setMeetingId(actionItem.getMeeting().getId());
		}		
		model.addAttribute("actionItem", actionItem);
		model.addAttribute("referenceData", referenceData());
		return "actionitemsedit";
	}

	
	@RequestMapping(value = "/new-actionitem", method = RequestMethod.POST)
	public String addActionItem(
			@Valid @ModelAttribute("actionItem") final ActionItem actionItem,
			final BindingResult result, final Model model) {
		final String currentUser = SecurityContextHolder.getContext()
				.getAuthentication().getName();
		model.addAttribute("currentUser", currentUser);
		String returnView = "";
		Meeting meeting = null;
		Issue issue = null;

		final Principal assignedTo = principalService.findOne(actionItem
				.getAssignedToId());

		try {
			issue = issueService.findOne(actionItem.getIssueId());
		} catch (final Exception e) {
		}
		try {
			meeting = meetingService.findOne(actionItem.getMeetingId());
		} catch (final Exception e) {
		}
		actionItem.setIssue(issue);
		actionItem.setMeeting(meeting);
		actionItem.setDateCreated(new Date());
		actionItem.setAssignedTo(assignedTo);
		if (result.hasErrors()) {
			returnView = "actionitemsadd";
			model.addAttribute("actionItem", actionItem);
			model.addAttribute("referenceData", referenceData());
		} else {
			service.create(actionItem);
			if(issue!=null)
			   returnView = "redirect:issue/"+issue.getId()+"#tab1";
			else if(meeting!=null)
				returnView = "redirect:meeting/"+meeting.getId()+"#tab4";
			else
				returnView = "redirect:artifacts";
			
			model.addAttribute("success","success.actionItem.created");
			if(meeting != null) {
				model.addAttribute("page", "actionItems");
				returnView = "redirect:/app/meeting/" + meeting.getId();
			} else {
				returnView = "forward:artifacts";
			}
			
		}		
		return returnView;
	}

	@RequestMapping(value = "/edit-actionitem", method = RequestMethod.POST)
	public String updateActionItem(
			@Valid @ModelAttribute("actionItem") final ActionItem actionItem,
			final BindingResult result, final Model model) {
		final String currentUser = SecurityContextHolder.getContext()
				.getAuthentication().getName();
		model.addAttribute("currentUser", currentUser);
		String returnView = "";
		// using name as long --bad idea
		final Principal assignedTo = principalService.findOne(actionItem
				.getAssignedToId());
		Issue issue = null;
		Meeting meeting = null;
		try {
			issue = issueService.findOne(actionItem.getIssueId());
		} catch (final Exception e) {
		}
		try {
			meeting = meetingService.findOne(actionItem
					.getMeetingId());
		} catch (final Exception e) {
		}
		actionItem.setIssue(issue);
		actionItem.setMeeting(meeting);
		actionItem.setAssignedTo(assignedTo);
		if (result.hasErrors()) {
			returnView = "actionitemsedit";
			model.addAttribute("actionItem", actionItem);
			model.addAttribute("referenceData", referenceData());
		} else {
			Principal principal = principalService.findByName(currentUser);
			ActionItem oldActionItem = service.findOne(actionItem.getId());
			List<RevisionHistory> histList = buildRevisionHistory(oldActionItem,
														actionItem, principal);
			service.update(actionItem);
			if(null != histList && histList.size() > 0){
				revisionHistoryService.bulkCreate(histList);
			}
			service.update(actionItem);
			model.addAttribute("success","success.actionItem.updated");
			if(meeting != null) {
				model.addAttribute("page", "actionItems");
				returnView = "redirect:/app/meeting/" + meeting.getId();
			} else {
				returnView = "redirect:actionitems";
			}			
		}
		
		return returnView;
	}
	
	@RequestMapping(value = "/delete-actionitem/{id}/{returnPage}", method = RequestMethod.GET)
	public String deleteActionItem(@PathVariable("id") final Long id, @PathVariable("returnPage") final String returnPage,
			final Model model) {
		final String currentUser = SecurityContextHolder.getContext()
				.getAuthentication().getName();
		model.addAttribute("currentUser", currentUser);
		String returnView = "";
		service.delete(id);
		model.addAttribute("success","success.actionItem.deleted");
		if(returnPage.equalsIgnoreCase("")) {
			returnView = "forward:actionitems"; 
		} else {
			returnView = "forward:" + returnPage;	
		}			
		return returnView;
	}
	
	protected Map referenceData() {
		final Map referenceData = new HashMap();

		final List<Principal> list = principalService.findAll();
		final Map<Integer, String> aList = new LinkedHashMap<Integer, String>();
		for (int i = 0; i < list.size(); i++) {
			aList.put(list.get(i).getId().intValue(), list.get(i).getName());
		}
		referenceData.put("assignedToList", aList);

		final List<Meeting> mlist = meetingService.findAll();
		final Map<Integer, String> mList = new LinkedHashMap<Integer, String>();
		mList.put(0, "--select meeting--");
		for (int i = 0; i < mlist.size(); i++) {
			mList.put(mlist.get(i).getId().intValue(), mlist.get(i).getTitle());
		}
		referenceData.put("meetingList", mList);

		final List<Issue> ilist = issueService.findAll();
		final Map<Integer, String> iList = new LinkedHashMap<Integer, String>();
		iList.put(0, "--select issue--");
		for (int i = 0; i < ilist.size(); i++) {
			iList.put(ilist.get(i).getId().intValue(), ilist.get(i)
					.getSummary());
		}
		referenceData.put("issueList", iList);
		
		final Map<String, String> pList = new LinkedHashMap<String, String>();
		pList.put(ActionItemPriority.High.toString(),ActionItemPriority.High.toString());
		pList.put(ActionItemPriority.Medium.toString(),ActionItemPriority.Medium.toString());
		pList.put(ActionItemPriority.Low.toString(),ActionItemPriority.Low.toString());
		referenceData.put("priorityList", pList);
		
		
		final Map<String, String> sList = new LinkedHashMap<String, String>();
		sList.put(ActionItemStatus.Closed.toString(),
				ActionItemStatus.Closed.toString());
		sList.put(ActionItemStatus.Completed.toString(),
				ActionItemStatus.Completed.toString());
		sList.put(ActionItemStatus.Open.toString(),
				ActionItemStatus.Open.toString());
		sList.put(ActionItemStatus.Pending.toString(),
				ActionItemStatus.Pending.toString());
		sList.put(ActionItemStatus.In_Progress.toString(),
				ActionItemStatus.In_Progress.toString());

		referenceData.put("statusList", sList);
		return referenceData;
	}
	
	@Override
	protected IService<ActionItem> getService() {
		return service;
	}
	@RequestMapping(value = "/uploadactionitems" , method = RequestMethod.GET)
	public String actionItem(final Model model) {
		model.addAttribute(new FileUpload());
		model.addAttribute("referenceData", referenceData());
		return "uploadactionitems";
	} 
	 @RequestMapping(value = "/uploadactionitems", method = RequestMethod.POST)
		public String uploadActionItems(@ModelAttribute("fileUpload") final FileUpload uploadItem, final Principal principal,
				final BindingResult result, final HttpServletRequest request, final RedirectAttributes redirectAttributes, final Model model) throws InvalidFormatException {		
		
		 logger.debug("Received request to upload actionitems report");
		final String typeOfUpload = GenericConstants.ACTION_ITEMS;
		if(result.hasErrors()) {
			redirectAttributes.addFlashAttribute("fileUploadError", "error.upload.internal.problem");
			return "redirect:uploadactionitems";
		} else {
			//Delegate to UploadService
			String returnString = uploadService.uploadXLS(uploadItem, typeOfUpload, redirectAttributes);
			if(returnString.equalsIgnoreCase("fileUploadError")) {
				return "redirect:uploadactionitems";
			} else {
				return returnString;
			}
		}
	}
}
