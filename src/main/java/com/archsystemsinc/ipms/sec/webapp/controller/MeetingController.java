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

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.archsystemsinc.ipms.persistence.service.IService;
import com.archsystemsinc.ipms.poi.service.DownloadService;
import com.archsystemsinc.ipms.poi.service.UploadService;
import com.archsystemsinc.ipms.sec.model.DiscussionAgenda;
import com.archsystemsinc.ipms.sec.model.Meeting;
import com.archsystemsinc.ipms.sec.model.MeetingAgendaItem;
import com.archsystemsinc.ipms.sec.model.MeetingMinutes;
import com.archsystemsinc.ipms.sec.model.MeetingPrincipal;
import com.archsystemsinc.ipms.sec.model.ParkingLot;
import com.archsystemsinc.ipms.sec.model.Principal;
import com.archsystemsinc.ipms.sec.model.Program;
import com.archsystemsinc.ipms.sec.model.Project;
import com.archsystemsinc.ipms.sec.persistence.service.IDiscussionAgendaService;
import com.archsystemsinc.ipms.sec.persistence.service.IMeetingAgendaItemService;
import com.archsystemsinc.ipms.sec.persistence.service.IMeetingMinutesService;
import com.archsystemsinc.ipms.sec.persistence.service.IMeetingPrincipalService;
import com.archsystemsinc.ipms.sec.persistence.service.IMeetingService;
import com.archsystemsinc.ipms.sec.persistence.service.IPrincipalService;
import com.archsystemsinc.ipms.sec.persistence.service.IProgramService;
import com.archsystemsinc.ipms.sec.persistence.service.IProjectService;
import com.archsystemsinc.ipms.sec.util.GenericConstants;
import com.archsystemsinc.ipms.sec.util.email.Emailer;
import com.archsystemsinc.ipms.web.common.AbstractController;

@Controller
public class MeetingController extends AbstractController<Meeting> {

	@Autowired
	private Emailer emailer;
	
	public MeetingController() {
		super(Meeting.class);
	}

	private final Log log = LogFactory.getLog(MeetingController.class);

	@Autowired
	private IMeetingMinutesService meetingminutesservice;
	
	@Autowired
	private IMeetingService service;

	@Autowired
	private IProjectService projectService;

	@Autowired
	private IProgramService programService;

	@Autowired
	private IPrincipalService principalService;

	@Autowired
	private IMeetingAgendaItemService meetingAgendaItemService;

	@Autowired
	private IMeetingPrincipalService meetingPrincipalService;

	@Autowired
	private IDiscussionAgendaService discussionAgendaService;
	
	@Autowired
	private DownloadService downloadService;
	
	@Autowired
	private UploadService uploadService;

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
		final NumberFormat numberFormat = NumberFormat.getInstance();
		binder.registerCustomEditor(Float.class, new CustomNumberEditor(
				Float.class, numberFormat, true));

	}
	
	@RequestMapping(value = "/meetingminutesadd", method = RequestMethod.GET)
	public String newMeetingmintes(final Model model) {
		
		final Meeting meeting = new Meeting();
		model.addAttribute("meeting", meeting);
		model.addAttribute("referenceData", referenceData());
		return "meetingminutesadd";
	}
	/**
	 * To submit the meeting minutes add page
	 */
	@RequestMapping(value = "/meetingminutesadd1/{mid}", method = RequestMethod.POST)
	public String addMeetingMeetings(@Valid @ModelAttribute("meeting") Meeting meeting,
			final BindingResult result, final Model model, HttpServletRequest request) {
	String returnView = "";
	System.out.println("insdie the meeting minutes add");	
	/*if (request.getParameter("meetingselectbox") != null) {
		System.out.println("insdie the meeting select box");	
		meeting = service.findOne(meetingId);
		model.addAttribute("meeting", meeting);
		return "meetingminutesadd";
	}*/
	   
	
		final Set<Principal> attendees = new HashSet<Principal>();
		if (meeting.getAttendeesList() != null) {
			final StringTokenizer st = new StringTokenizer(
					meeting.getAttendeesList(), ",");

			while (st.hasMoreTokens()) {
				final Long id = Long.parseLong(st.nextToken());
				
				attendees.add(principalService.findOne(id));
			}
		}

		final Principal organizer = principalService.findOne(meeting
				.getOrganizerId());
		attendees.add(organizer);
		meeting.setOrganizer(organizer);
		Program program = null;
		Project project = null;
		MeetingAgendaItem meetingagendaitem = null;
		try {
			program = programService.findOne(meeting.getProgramId());
			meeting.setProgram(program);
		} catch (final Exception e) {
		}
		try {
			project = projectService.findOne(meeting.getProjectId());
			meeting.setProject(project);
		} catch (final Exception e) {
		}
		
		if (result.hasErrors()) {
			returnView = "meetingsadd";
			model.addAttribute("meeting", meeting);
			model.addAttribute("referenceData", referenceData());
		} else {
			meeting.setStatus("Scheduled");
			meeting = service.create(meeting);
			meeting.setAttendees(attendees);
			service.update(meeting);
			emailer.sendMail(meeting.getAttendeesEmails(), "New Meeting: " + meeting.getTitle(), meeting.getMeetingEmailBody(), null, null,null);
			if(project!=null)
				returnView = "redirect:project/"+project.getId()+"?page=meetings&success=1";
			else if(program!=null)
				returnView = "redirect:program/"+program.getId()+"?page=meetings&success=1";
			else
			    returnView = "forward:meetings";
		} 

		
		return returnView;
	}
	



	/**
	 * To get the meeting minutes page
	 */
	@RequestMapping(value = "/meetingminutes", method = RequestMethod.GET)
	public String meetings1(final Model model,
			final java.security.Principal principal) {
		final Principal currentUser = principalService.findByName(principal
				.getName());
		final List<MeetingMinutes> meetingminutes = meetingminutesservice.findAll();
		model.addAttribute("meetingminutes", meetingminutes);
		return "meetingminutes";
	}
	
	@RequestMapping(value = "/meetingminutesupload", method = RequestMethod.GET)
	public String uploadMeetingminutes(final Model model) {
		model.addAttribute(new FileUpload());
		model.addAttribute("referenceData", referenceData());
		return "meetingminutesupload";
	}
	
	@RequestMapping(value = "/meetingminutesupload", method = RequestMethod.POST)
	public String uploadMeetingminutes(@ModelAttribute("fileUpload") final FileUpload uploadItem, final Principal principal,
			final BindingResult result, final HttpServletRequest request, final RedirectAttributes redirectAttributes) {		
		
		logger.debug("Received request to upload Meeting minutes report");
		final String typeOfUpload = GenericConstants.MEETING_MINUTES;
		if(result.hasErrors()) {
			redirectAttributes.addFlashAttribute("fileUploadError", "error.upload.internal.problem");
			return "redirect:/app/meetingminutesupload";
		} else {
			//Delegate to UploadService.
			String returnString = uploadService.uploadXLS(uploadItem, typeOfUpload, redirectAttributes);
			if(returnString.equalsIgnoreCase("fileUploadError")) {
				return "redirect:/app/meetingminutesupload";
			} else {
				return returnString;
			}
		}
	}
	
	@RequestMapping(value = "/meeting/{id}")
	public String meeting(@PathVariable("id") final Long id,
			final Model model, final HttpServletRequest request) {
		final Meeting meeting = service.findOne(id);
		model.addAttribute("meeting", meeting);
		
			final String page = request.getParameter("page");
			if (page == null)
				model.addAttribute("page", "");
			else
				model.addAttribute("page", page);
		return "meeting";
	}
	
	@RequestMapping(value = "/agenda")
	public String program(final Model model) {
		final List<MeetingAgendaItem> meetingAgendaItem = meetingAgendaItemService.findAll();
		if(meetingAgendaItem != null) {
			Collections.shuffle((List<MeetingAgendaItem>) meetingAgendaItem);
		}		
		model.addAttribute("meetingAgendaItem", meetingAgendaItem);
		return "agenda";
	}
	@RequestMapping(value = "/meetings/{id}")
	public String meetings(@PathVariable("id") final Long id,
			final Model model, final HttpServletRequest request) {
		final Meeting meeting = service.findOne(id);
		model.addAttribute("meeting", meeting);
		
			final String page = request.getParameter("page");
			if (page == null)
				model.addAttribute("page", "");
			else
				model.addAttribute("page", page);
		return "meetings";
	}

	
	/**
	 * Downloads the report as an Word format.
	 * <p>
	 * Make sure this method doesn't return any model. Otherwise, you'll get an
	 * "IllegalStateException: getOutputStream() has already been called for this response"
	 * final HttpServletResponse response, final String sheetName, final
	 * List<Object> exportList, final String[] coloumnNames
	 */
	@RequestMapping(value = "/exportword/minutes/{id}", method = RequestMethod.GET)
	public void getMeetingMinutesWord(final HttpServletResponse response,
			final Model model, @PathVariable("id") final Long id,
			final HttpServletRequest request)
					throws ClassNotFoundException {
		logger.debug("Received request to download Agenda Items as Word");

		// Delegate to downloadService. Make sure to pass an instance of
		// HttpServletResponse
		String documentName = "Minutes";
		final Meeting meeting = service.findOne(id);
		if (request.getParameter("page") != null
				&& request.getParameter("page").equalsIgnoreCase("agenda")) {
			documentName = "AgendaItems";

		} else if (request.getParameter("page") != null
				&& request.getParameter("page").equalsIgnoreCase("minutes")) {
			documentName = "Minutes";
		}
		
		final Set agendaItems = meeting.getAgendaItems();
		downloadService.downloadWord(response, documentName, agendaItems);

	}
	
	@RequestMapping(value = "/meetings/xls", method = RequestMethod.GET)
	public void getXLS(final HttpServletResponse response, final Model model,
			final java.security.Principal principal)
					throws ClassNotFoundException {
		logger.debug("Received request to download meetings report as an XLS");
		final String sheetName = GenericConstants.MEETINGS;
		final String[] coloumnNames = { "1", "2", "3", "4", "5", "6","7","8","9","10","11"};
		// Delegate to downloadService. Make sure to pass an instance of
		// HttpServletResponse
		final List meetings = service.findAll();
		downloadService
				.downloadXLS(response, sheetName, meetings, coloumnNames);
	}

	@RequestMapping(value = "/meetings")
	public String meeting(final Model model,
			final java.security.Principal principal) {
		final Principal currentUser = principalService.findByName(principal
				.getName());
		final List<Meeting> meetings = service.findAll();
		model.addAttribute("meetings", meetings);
		return "meetings";
	}

	@RequestMapping(value = "/meeting/{id}", method = RequestMethod.GET)
	public String meeting(@PathVariable("id") final Long id, final Model model,
			final Principal principal, final HttpServletRequest request) {
		final Meeting meeting = service.findOne(id);
		if (request.getParameter("aid") != null) {
			try {
				final MeetingAgendaItem m = meetingAgendaItemService
						.findOne(Long.parseLong(request.getParameter("aid")));
				model.addAttribute("meetingAgendaItem", m);
			} catch (final Exception e) {

			}
		}
		meeting.setOrganizerId(principal.getId());
		try {
			if(meeting.getProgram()!=null) {
				meeting.setProgramId(meeting.getProgram().getId());
			}
		} catch (final Exception e) {
		}
		try {
			if(meeting.getProject()!=null) {
				meeting.setProjectId(meeting.getProject().getId());
			}			
		} catch (final Exception e) {
		}
		final String page = request.getParameter("page");
		if (page == null)
			model.addAttribute("page", "");
		else
			model.addAttribute("page", page);
		model.addAttribute("meeting", meeting);
		model.addAttribute("meetingAgendaItem", new MeetingAgendaItem());
		List<MeetingPrincipal> principalInvitees = meetingPrincipalService.findByMeeting(meeting.getId());
		model.addAttribute("invitees",
				principalInvitees);
		return "meeting";
	}
	
	@RequestMapping(value = "/meetingAjaxCall", method = RequestMethod.POST)
	public @ResponseBody List<String> meetingAjaxCall (@RequestParam(value="uuid") long uuid,final Model model,
			final java.security.Principal principal) {		
		final Principal currentUser = principalService.findByName(principal
				.getName());
		List<String> meetingValues = new ArrayList<String> ();
		System.out.println(uuid + "path variable1");
		final Meeting meeting = service.findOne(uuid);
		String meetingName = meeting.getLocationInfo();
		System.out.println(meeting + "what does meetings contain");
	    
	    	meetingValues.add(0,meeting.getDate().toString());
	    	meetingValues.add(1,meeting.getTime());
	    	meetingValues.add(2,meeting.getLocationInfo());
	  
	
		return meetingValues;
	}
	
	@RequestMapping(value = "/new-projectmeeting", method = RequestMethod.GET)
	public String newProjectMeeting(final Model model) {
		final Meeting meeting = new Meeting();
		meeting.setProjectMeeting(true);
		model.addAttribute("meeting", meeting);
		model.addAttribute("referenceData", referenceData());
		return "meetingsadd";
	}
	
	@RequestMapping(value = "/new-agenda", method = RequestMethod.GET)
	public String newAgendaMeeting(final Model model) {
		final List<MeetingAgendaItem> meetingAgendaItem = meetingAgendaItemService.findAll();
		if(meetingAgendaItem != null) {
			Collections.shuffle((List<MeetingAgendaItem>) meetingAgendaItem);
		}		
		model.addAttribute("meetingAgendaItem", meetingAgendaItem);
		return "agendaadd";
	}
	
	@RequestMapping(value = "/new-projectmeeting/{id}", method = RequestMethod.GET)
	public String newProjectMeeting1(@PathVariable("id") final Long id,
			final Model model) {
		final Meeting meeting = new Meeting();
		Project project = null;
		try {
			project = projectService.findOne(id);
		} catch (final Exception e) {
		}
		meeting.setProjectId(id);
		meeting.setProject(project);
		meeting.setProjectMeeting(true);
		model.addAttribute("meeting", meeting);
		model.addAttribute("referenceData", referenceData());
		return "meetingsadd";
	}

	@RequestMapping(value = "/new-programmeeting", method = RequestMethod.GET)
	public String newProgramMeeting(final Model model) {
		final Meeting meeting = new Meeting();
		meeting.setProjectMeeting(false);
		model.addAttribute("meeting", meeting);
		model.addAttribute("referenceData", referenceData());
		return "meetingsadd";
	}

	/*
	 * For Adding new agenda meeting, For displaying it on the page
	 */
	@RequestMapping(value = "/new-agendameeting", method = RequestMethod.GET)
	public String newMeetingAgenda(final Model model) {
		final Meeting meeting = new Meeting();
		final MeetingAgendaItem meetingagendaitem = new MeetingAgendaItem();
		final ParkingLot parkinglot = new ParkingLot();
		final DiscussionAgenda discussionagenda = new DiscussionAgenda();
		meeting.setProjectMeeting(false);
		meetingagendaitem.setMeeting(meeting);

		model.addAttribute("meeting", meeting);
		model.addAttribute("meetingAgendaItem", meetingagendaitem);
		model.addAttribute("parkinglot", parkinglot);
		model.addAttribute("discussionagenda", discussionagenda);
		model.addAttribute("referenceData", referenceData());
		return "agendaadd";
	}
	@RequestMapping(value = "/new-programmeeting/{id}", method = RequestMethod.GET)
	public String newProgramMeeting1(@PathVariable("id") final Long id,
			final Model model) {
		final Meeting meeting = new Meeting();
		Program program = null;
		try {
			program = programService.findOne(id);
		} catch (final Exception e) {
		}
		meeting.setProgramId(id);
		meeting.setProgram(program);
		meeting.setProjectMeeting(false);
		model.addAttribute("meeting", meeting);
		model.addAttribute("referenceData", referenceData());
		return "meetingsadd";
	}

	@RequestMapping(value = "/new-meetingagenda/{id}", method = RequestMethod.GET)
	public String newMeetingAgenda(@PathVariable("id") final Long id,
			final Model model, final java.security.Principal principal) {
		MeetingAgendaItem meetingAgendaItem = new MeetingAgendaItem();
		final com.archsystemsinc.ipms.sec.model.Principal currentUser = principalService
				.findByName(principal.getName());
		
		Meeting meeting = service.findOne(id);
//		try {
//			meetingAgendaItem = meetingAgendaItemService.findOne(id);
//		} catch (final Exception e) {
//		}
		meetingAgendaItem.setMeeting(meeting);
		meetingAgendaItem.setMeetingAgendaOwner(currentUser.getOwner());
		model.addAttribute("meetingAgendaItem", meetingAgendaItem);
		model.addAttribute("meetingTitle", meeting.getTitle());
		model.addAttribute("meetingId", meeting.getId());
		model.addAttribute("referenceData", referenceData());
		return "agendaadd";
	}
	
	
	@RequestMapping(value = "/new-meetingagenda/{mid}", method = RequestMethod.POST)
	public String newMeetingAgendaItem(@PathVariable("mid") final Long mid, @Valid @ModelAttribute("meetingAgendaItem") final MeetingAgendaItem meetingAgendaItem,
			final BindingResult result, final Model model, HttpServletRequest httpServletRequest, final java.security.Principal principal) {
		String returnView = "";
		final com.archsystemsinc.ipms.sec.model.Principal currentUser = principalService
				.findByName(principal.getName());
		meetingAgendaItem.setMeetingAgendaOwner(currentUser);
		if (result.hasErrors()) {
			returnView = "agendasadd";
			model.addAttribute("meetingAgendaItem", meetingAgendaItem);
			model.addAttribute("referenceData", referenceData());
		} else {
			Meeting meeting = service.findOne(mid);
			meetingAgendaItem.setMeeting(meeting);
			String[] facilitatornames = httpServletRequest.getParameterValues("facilitatorname");
			String[] items = httpServletRequest.getParameterValues("item");
			//meetingAgendaItem.setDiscussionAgendas(discussionAgendas);
			MeetingAgendaItem agendaItem = meetingAgendaItemService.create(meetingAgendaItem);
			for(int i=0;i<facilitatornames.length;i++) {
				DiscussionAgenda discussionAgenda = new DiscussionAgenda();
				discussionAgenda.setFacilitatorname(facilitatornames[i]);
				discussionAgenda.setItem(items[i]);
				discussionAgenda.setMeetingAgendaItem(agendaItem);
				discussionAgendaService.create(discussionAgenda);
			}
			returnView = "redirect:/app/meeting/" + mid;
					
		}
		return returnView;
	}
	@RequestMapping(value = "/update-meetingagenda/{id}", method = RequestMethod.POST)
	public String updateMeetingAgenda(@PathVariable("id") final Long id,@Valid @ModelAttribute("meetingAgendaItem") final MeetingAgendaItem meetingAgendaItem,
			final Model model,final java.security.Principal principal, final HttpServletRequest request) {
		final Meeting meeting = service.findOne(id);
		String returnView = "";
		meetingAgendaItem.setMeeting(meeting);
		if(meetingAgendaItem.getMeetingAgendaOwnerId() != null) {
			 final Principal meetingAgendaOwner = principalService.findOne(meetingAgendaItem.getMeetingAgendaOwnerId());
			 meetingAgendaItem.setMeetingAgendaOwner(meetingAgendaOwner);
		}      
		if(meetingAgendaItem.getId() == null) {
			meetingAgendaItemService.create(meetingAgendaItem);
		} else {
			meetingAgendaItemService.update(meetingAgendaItem);
		}
		
		model.addAttribute("success", "success.meetingAgendaItem.updated");
		return "redirect:/app/meeting/" + id;
	}

	
	@RequestMapping(value = "/edit-meeting/{id}", method = RequestMethod.GET)
	public String editMeeting(@PathVariable("id") final Long id,
			final Model model) {
		final Meeting meeting = service.findOne(id);
		

		Set<Principal> meetingAttendees = meeting.getAttendees();
		String attendesList = "";
		for(Principal attende: meetingAttendees) {
			if(attendesList.equalsIgnoreCase("")) {
				attendesList = attende.getId().toString();
			} else {
				attendesList = attendesList + "," +attende.getId();
			}
			
		}
		meeting.setAttendeesList(attendesList);
		meeting.setOrganizerId(meeting.getOrganizer().getId());
		model.addAttribute("meeting", meeting);
		model.addAttribute("referenceData", referenceData());
		return "meetingsedit";
	}
	
	@RequestMapping(value = "/update-meetingattendance/{id}", method = RequestMethod.POST)
	public String updateMeetingAttendance(@PathVariable("id") final Long id,
			final Model model, final HttpServletRequest request) {
		final Meeting meeting = service.findOne(id);
		final List<MeetingPrincipal>attendees = meetingPrincipalService.findByMeeting(id);
		for(int i=0;i<attendees.size();i++) {
			final MeetingPrincipal mp = attendees.get(i);
			if (request.getParameter("attendee"+mp.getPrincipalId()) != null) {
				if (request.getParameter("attendee" + mp.getPrincipalId())
						.equalsIgnoreCase("on"))
					mp.setAttended(true);
				else
					mp.setAttended(false);
				meetingPrincipalService.update(mp);
			}
		}
		model.addAttribute("meeting", meeting);
		model.addAttribute("page", "minutes");
		model.addAttribute("referenceData", referenceData());
		emailer.sendMail(meeting.getAttendeesEmails(), "Meeting : "+meeting.getTitle()+" - Attendee Updated", meeting.getMeetingEmailBody(), null, null,null);
		return "redirect:/app/meeting/" + id;
	}

	@RequestMapping(value = "/edit-meetingagenda/{id}", method = RequestMethod.GET)
	public String editMeetingAgenda(@PathVariable("id") final Long id,
			final Model model) {
		final MeetingAgendaItem meetingAgendaItem = meetingAgendaItemService.findOne(id);
		Meeting meeting = meetingAgendaItem.getMeeting();
		model.addAttribute("meetingAgendaItem", meetingAgendaItem);
		model.addAttribute("meeting", meeting);
		model.addAttribute("meetingTitle", meeting.getTitle());
		model.addAttribute("meetingId", meeting.getId());
		model.addAttribute("discussionAgendas", meetingAgendaItem.getDiscussionAgendas());
		model.addAttribute("page", "agenda");
		List<MeetingPrincipal> principalInvitees = meetingPrincipalService.findByMeeting(meeting.getId());
		model.addAttribute("invitees",
				principalInvitees);
		model.addAttribute("referenceData", referenceData());
		return "agendaedit";
	}
	
	@RequestMapping(value = "/edit-meetingagenda/{id}", method = RequestMethod.POST)
	public String editMeetingAgendaItem(@PathVariable("id") final Long id, @Valid @ModelAttribute("meetingAgendaItem") final MeetingAgendaItem meetingAgendaItem,
			final BindingResult result, final Model model, HttpServletRequest httpServletRequest, final java.security.Principal principal) {
		String returnView = "";
		final com.archsystemsinc.ipms.sec.model.Principal currentUser = principalService
				.findByName(principal.getName());
		//Meeting meeting = service.findOne(mid);
		MeetingAgendaItem existing = meetingAgendaItemService.findOne(id);
		if(existing!=null) {
			meetingAgendaItem.setId(Long.valueOf(id));
			meetingAgendaItem.setMeetingAgendaOwner(currentUser);
			if (result.hasErrors()) {
				returnView = "agendasedit";
				model.addAttribute("meetingAgendaItem", meetingAgendaItem);
				model.addAttribute("meeting", meetingAgendaItem.getMeeting());
				model.addAttribute("meetingTitle", meetingAgendaItem.getMeeting().getTitle());
				model.addAttribute("meetingId", meetingAgendaItem.getMeeting().getId());
				model.addAttribute("discussionAgendas", meetingAgendaItem.getDiscussionAgendas());
				model.addAttribute("page", "agenda");
				model.addAttribute("referenceData", referenceData());
			} else {
				
				String[] facilitatornames = httpServletRequest.getParameterValues("facilitatorname");
				String[] items = httpServletRequest.getParameterValues("item");
				//meetingAgendaItem.setDiscussionAgendas(discussionAgendas);
				List<DiscussionAgenda> list = existing.getDiscussionAgendas();
				meetingAgendaItemService.update(meetingAgendaItem);
				int i = 0;
				for(i=0;i<facilitatornames.length;i++) {
					DiscussionAgenda discussionAgenda = new DiscussionAgenda();
					if(null!=list && list.size()>i) discussionAgenda = list.get(i);
					discussionAgenda.setFacilitatorname(facilitatornames[i]);
					discussionAgenda.setItem(items[i]);
					discussionAgenda.setMeetingAgendaItem(meetingAgendaItem);
					discussionAgendaService.create(discussionAgenda);
				}
				if(i<list.size()) {
					for(int j=i;j<list.size();j++) {
						DiscussionAgenda discussionAgenda = list.get(j);
						discussionAgendaService.delete(discussionAgenda.getId());
					}
				}
				returnView = "redirect:/app/meeting/" + meetingAgendaItem.getMeeting().getId();		
				}
		} else 
			returnView = "redirect:/app/meetings";
		return returnView;
	}


	@RequestMapping(value = "/update-meetingminute/{id}", method = RequestMethod.POST)
	public String updateMeetingMinute(@PathVariable("id") final Long id,
			final Model model, final HttpServletRequest request) {
		final MeetingAgendaItem meetingAgendaItem = meetingAgendaItemService.findOne(id);
		
		final Meeting meeting = meetingAgendaItem.getMeeting();
		meetingAgendaItem.setDiscussion(request
				.getParameter("discussion"
						+ meetingAgendaItem.getId()));
		meetingAgendaItemService.update(meetingAgendaItem);
		model.addAttribute("meeting", meeting);
		model.addAttribute("page", "minutes");
		model.addAttribute("referenceData", referenceData());
		model.addAttribute("success","success.minutes.updated");
		return "redirect:/app/meeting/" + meeting.getId();
	}

	@RequestMapping(value = "/new-meeting", method = RequestMethod.POST)
	public String addMeeting(@Valid @ModelAttribute("meeting") Meeting meeting,
			final BindingResult result, final Model model) {
		String returnView = "";
		
		final Set<Principal> attendees = new HashSet<Principal>();
		if (meeting.getAttendeesList() != null) {
			final StringTokenizer st = new StringTokenizer(
					meeting.getAttendeesList(), ",");

			while (st.hasMoreTokens()) {
				final Long id = Long.parseLong(st.nextToken());
				
				attendees.add(principalService.findOne(id));
			}
		}

		final Principal organizer = principalService.findOne(meeting
				.getOrganizerId());
		attendees.add(organizer);
		meeting.setOrganizer(organizer);
		Program program = null;
		Project project = null;
		try {
			program = programService.findOne(meeting.getProgramId());
			meeting.setProgram(program);
		} catch (final Exception e) {
		}
		try {
			project = projectService.findOne(meeting.getProjectId());
			meeting.setProject(project);
		} catch (final Exception e) {
		}
		if (result.hasErrors()) {
			returnView = "meetingsadd";
			model.addAttribute("meeting", meeting);
			model.addAttribute("referenceData", referenceData());
		} else {
			meeting.setStatus("Scheduled");
			meeting = service.create(meeting);
			meeting.setAttendees(attendees);
			service.update(meeting);
			emailer.sendMail(meeting.getAttendeesEmails(), "New Meeting: " + meeting.getTitle(), meeting.getMeetingEmailBody(), null, null,null);

			model.addAttribute("success","success.meeting.created");
			
			/*if(project!=null)
				returnView = "redirect:project/"+project.getId()+"?page=meetings&success=1";
			else if(program!=null)
				returnView = "redirect:program/"+program.getId()+"?page=meetings&success=1"; 
			else*/
			    returnView = "forward:meetings";
		} 

		
		return returnView;
	}

	@RequestMapping(value = "/edit-meeting", method = RequestMethod.POST)
	public String updateMeeting(
			@Valid @ModelAttribute("meeting") final Meeting meeting,
			final BindingResult result, final Model model,final java.security.Principal principal,
			final HttpServletRequest request
			) {

		Set<Principal> attendees = meeting.getAttendees();
		if (attendees == null)
			attendees = new HashSet<Principal>();
		String returnView = "";
		if (result.hasErrors()){
			returnView = "meetingsedit";
		}
		if (request.getParameter("btnAction")
				.equalsIgnoreCase("Cancel Meeting")) {
			meeting.setStatus("CANCELLED");
			returnView = "redirect:meetings";
		}
		
		meeting.setOrganizer(principalService.findOne(meeting.getOrganizerId()));
		try {
			meeting.setProgram(programService.findOne(meeting.getProgramId()));
		} catch (final Exception e) {
		}
		try {
			meeting.setProject(projectService.findOne(meeting.getProjectId()));
		} catch (final Exception e) {
		}

		if (request.getParameter("btnAction").equalsIgnoreCase("Cancel")) {
			meeting.setStatus("CANCELLED");
		}
		if (result.hasErrors()) {
			returnView = "meetingsedit";
		} else {
			if (meeting.getAttendeesList() != null) {
				final StringTokenizer st = new StringTokenizer(
						meeting.getAttendeesList(), ",");

				while (st.hasMoreTokens()) {
					final Long id = Long.parseLong(st.nextToken());
					attendees.add(principalService.findOne(id));
				}
			}

			meeting.setOrganizer(principalService.findOne(meeting
					.getOrganizerId()));
			try {
				meeting.setProgram(programService.findOne(meeting
						.getProgramId()));
			} catch (final Exception e) {
			}
			try {
				meeting.setProject(projectService.findOne(meeting
						.getProjectId()));
			} catch (final Exception e) {
			}
			final Principal currentUser = principalService.findByName(principal
					.getName());
			final List<Meeting> meetings = service.findAll();
			model.addAttribute("meetings", meetings);
			model.addAttribute("success","success.meeting.updated");
			meeting.setAttendees(attendees);
			service.update(meeting);
			emailer.sendMail(meeting.getAttendeesEmails(), "Meeting: " + meeting.getTitle() + " Edited", meeting.getMeetingEmailBody(), null, null,null);

			returnView = "forward:meetings/"+meeting.getId();
			
		}

		model.addAttribute("program", meeting);
		model.addAttribute("referenceData", referenceData());
		return returnView;
	}

	protected Map referenceData() {
		final Map referenceData = new HashMap();
		final List<Principal> list = principalService.findAll();
		final Map<Integer, String> pList = new LinkedHashMap<Integer, String>();
		for (int i = 0; i < list.size(); i++) {
			pList.put(list.get(i).getId().intValue(), list.get(i).getName());
		}
		referenceData.put("organizerList", pList);

		final List<Project> listOfProjects = projectService.findActiveProjects();
		final Map<Integer, String> prList = new LinkedHashMap<Integer, String>();
		for (int i = 0; i < listOfProjects.size(); i++) {
			prList.put(listOfProjects.get(i).getId().intValue(), listOfProjects
					.get(i).getName());
		}
		referenceData.put("projectList", prList);

		final List<Program> listOfPrograms = programService.findActivePrograms();
		final Map<Integer, String> prgList = new LinkedHashMap<Integer, String>();
		for (int i = 0; i < listOfPrograms.size(); i++) {
			prgList.put(listOfPrograms.get(i).getId().intValue(),
					listOfPrograms.get(i).getName());
		}
		referenceData.put("programList", prgList);


		final List<Meeting> listOfMeetings = service.findAll();
		final Map<Integer, String> meetingList = new LinkedHashMap<Integer, String>();
		for (int i = 0; i < listOfMeetings.size(); i++) {
			meetingList.put(listOfMeetings.get(i).getId().intValue(),
					listOfMeetings.get(i).getTitle());
		}
		referenceData.put("MeetingList", meetingList);
		
		final Map<Integer, String> currentMeetingList = new LinkedHashMap<Integer, String>();
		currentMeetingList.put(0, "--Select Meeting--");
		currentMeetingList.putAll(meetingList);
		referenceData.put("currentMeetingList", currentMeetingList);
		
		final Map<Integer, String> typeList = new LinkedHashMap<Integer, String>();
		typeList.put(0, "Webinar");
		typeList.put(1, "Conference Call");
		typeList.put(2, "Other");
		referenceData.put("typeList", typeList);

		final List<Principal> atlist = principalService.findAll();
		final Map<String, String> atList = new LinkedHashMap<String, String>();
		for (int i = 0; i < atlist.size(); i++) {
			atList.put(atlist.get(i).getId().toString(), atlist.get(i)
					.getName());
		}
		
		referenceData.put("attendeesList", atList);

		return referenceData;
	}

	@Override
	protected IService<Meeting> getService() {
		return service;
	}

}
