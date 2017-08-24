package com.archsystemsinc.ipms.sec.model;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import org.hibernate.validator.constraints.NotEmpty;
import com.archsystemsinc.ipms.common.INameableEntity;
import com.thoughtworks.xstream.annotations.XStreamAlias;

@Entity
@XmlRootElement
@XStreamAlias("Meeting")
@Table(name = "meeting")
public class Meeting implements INameableEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "meeting_id")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "meetingminutes_id")
	private MeetingMinutes meetingminutes;


	@ManyToOne
	@JoinColumn(name = "project_id")
	private Project project;

	@ManyToOne
	@JoinColumn(name = "program_id")
	private Program program;

	@ManyToOne
	@JoinColumn(name = "principal_id")
	private Principal organizer;

	@OneToMany(mappedBy = "meeting")
	private Set<ActionItem> actionItems = new HashSet<ActionItem>();

	@OneToMany(mappedBy = "meeting")
	private Set<LessonsLearned> lessonsLearneds = new HashSet<LessonsLearned>();

	@OneToMany(mappedBy = "meeting")
	private Set<MeetingAgendaItem> agendaItems = new HashSet<MeetingAgendaItem>();

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "meeting_principal", joinColumns = { @JoinColumn(name = "meeting_id") }, inverseJoinColumns = { @JoinColumn(name = "principal_id") })
	@NotNull
	private Set<Principal> attendees = new HashSet<Principal>();

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "meeting_attendee", joinColumns = { @JoinColumn(name = "meeting_id") }, inverseJoinColumns = { @JoinColumn(name = "attendee_id") })
	private Set<Attendee> externalAttendees = new HashSet<Attendee>();
	private String agenda;

	@Column
	private String status;

	@Column(nullable = true)
	private String summary;

	@Column(nullable = true)
	@NotEmpty
	private String title;

	@Column(name = "location_info", nullable = true)
	@NotEmpty
	private String locationInfo;

	@Column(nullable = true)
	@NotNull
	private int type;

	@Column(name = "other_info", nullable = true)
	private String otherInfo;

	@Column(nullable = true)
	@NotNull
	private Date date;

	@Column(nullable = true)
	@NotEmpty
	private String time;

	@Column(nullable = true)
	@NotNull
	private Float duration;

	@Column(nullable = true)
	private String name;

	@Column(nullable = true)
	private String minutes;

	@Transient
	private Date endDate;

	@Transient
	private String attendeesList;

	@Transient
	private boolean projectMeeting;

	@Transient
	private String typeString;

	@Transient
	private Long organizerId;

	@Transient
	private Long programId;
	
	@Transient
	private String agendaItemdescription;
	
	@Transient
	private String discussion;
	
	@Transient
	private String actionItem;
	
	@Transient
	private String issues;

	@Transient
	private String parkingLot;
	
	@Transient
	private String facilitator;
	

	public String getFacilitator() {
		return facilitator;
	}

	public void setFacilitator(String facilitator) {
		this.facilitator = facilitator;
	}

	public String getParkingLot() {
		return parkingLot;
	}

	public void setParkingLot(String parkingLot) {
		this.parkingLot = parkingLot;
	}

	public String getIssues() {
		return issues;
	}

	public void setIssues(String issues) {
		this.issues = issues;
	}

	public String getActionItem() {
		return actionItem;
	}

	public void setActionItem(String actionItem) {
		this.actionItem = actionItem;
	}

	public String getDiscussion() {
		return discussion;
	}

	public void setDiscussion(String discussion) {
		this.discussion = discussion;
	}

	public String getAgendaItemdescription() {
		return agendaItemdescription;
	}

	public void setAgendaItemdescription(String agendaItemdescription) {
		this.agendaItemdescription = agendaItemdescription;
	}

	@Transient
	private Long projectId;

	public Set<LessonsLearned> getLessonsLearneds() {
		return lessonsLearneds;
	}

	public void setLessonsLearneds(final Set<LessonsLearned> lessonsLearneds1) {
		lessonsLearneds = lessonsLearneds1;
	}

	public Date getEndDate() {
		return date;
	}

	public void setEndDate(final Date endDateToSet) {
		endDate = endDateToSet;
	}

	public String getAttendeesList() {
		return attendeesList;
	}

	public void setAttendeesList(final String attendeesList1) {
		attendeesList = attendeesList1;
	}

	public Set<ActionItem> getActionItems() {
		return actionItems;
	}

	public void setActionItems(final Set<ActionItem> actionItemsToSet) {
		actionItems = actionItemsToSet;
	}

	public String[] getAttendeesEmails() {
		String[] list = new String[attendees.size()];
		Iterator<Principal> itr = attendees.iterator();
		int i = 0;
		while (itr.hasNext()) {
			Principal p = itr.next();
			list[i] = p.getEmail();
			i++;
		}
		return list;
	}

	public Program getProgram() {
		return program;
	}

	public void setProgram(final Program programToSet) {
		program = programToSet;
	}

	public String getAgenda() {
		return agenda;
	}

	public void setAgenda(final String agendaToSet) {
		agenda = agendaToSet;
	}

	public Set<Principal> getAttendees() {
		return attendees;
	}

	public void setAttendees(final Set<Principal> attendeesToSet) {
		attendees = attendeesToSet;
	}

	public Principal getOrganizer() {
		return organizer;
	}

	public void setOrganizer(final Principal organizerToSet) {
		organizer = organizerToSet;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(final Project projectToSet) {
		project = projectToSet;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(final String summaryToSet) {
		summary = summaryToSet;
	}

	public String getMinutes() {
		return minutes;
	}

	public void setMinutes(final String minutes1) {
		minutes = minutes1;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(final Date dateToSet) {
		date = dateToSet;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(final String titleToSet) {
		title = titleToSet;
	}

	public String getLocationInfo() {
		return locationInfo;
	}

	public void setLocationInfo(final String locationInfoToSet) {
		locationInfo = locationInfoToSet;
	}

	public int getType() {
		return type;
	}

	public void setType(final int typeToSet) {
		type = typeToSet;
	}

	public String getOtherInfo() {
		return otherInfo;
	}

	public void setOtherInfo(final String otherInfoToSet) {
		otherInfo = otherInfoToSet;
	}

	public String getTime() {
		return time;
	}

	public void setTime(final String timeToSet) {
		time = timeToSet;
	}

	public boolean isProjectMeeting() {
		return projectMeeting;
	}

	public void setProjectMeeting(final boolean projectMeetingToSet) {
		projectMeeting = projectMeetingToSet;
	}

	public Float getDuration() {
		return duration;
	}

	public void setDuration(final Float durationToSet) {
		duration = durationToSet;
	}

	public String getTypeString() {

		if (type == 0)
			return MeetingType.Webinar.name();
		else if (type == 2)
			return MeetingType.ConferenceCall.name();
		else
			return MeetingType.Other.name();

	}

	public void setTypeString(final String typeStringToSet) {
		typeString = typeStringToSet;
	}

	@Override
	public Long getId() {
		return id;
	}

	public Long getOrganizerId() {
		return organizerId;
	}

	public void setOrganizerId(final Long organizerIdToSet) {
		organizerId = organizerIdToSet;
	}

	public Long getProgramId() {
		return programId;
	}

	public void setProgramId(final Long programIdToSet) {
		programId = programIdToSet;
	}

	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(final Long projectIdToSet) {
		projectId = projectIdToSet;
	}

	@Override
	public void setId(final Long id1) {
		id = id1;
	}

	@Override
	public String getName() {
		return name;
	}

	public void setName(final String name1) {
		name = name1;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(final String status1) {
		status = status1;
	}

	public MeetingMinutes getMeetingminutes() {
		return meetingminutes;
	}

	public void setMeetingminutes(MeetingMinutes meetingminutes) {
		this.meetingminutes = meetingminutes;
	}

	public Set<Attendee> getExternalAttendees() {
		return externalAttendees;
	}

	public void setExternalAttendees(final Set<Attendee> externalAttendees1) {
		externalAttendees = externalAttendees1;
	}


	public Set<MeetingAgendaItem> getAgendaItems() {
		return agendaItems;
	}

	public void setAgendaItems(Set<MeetingAgendaItem> agendaItems) {
		this.agendaItems = agendaItems;
	}

	public String getMeetingEmailBody() {
		// TODO Auto-generated method stub
		StringBuffer buf = new StringBuffer();
		Format dateFormatter = new SimpleDateFormat("dd-MMM-yy");
		try {
			buf.append("<html><body>");
			if (this.getProgram() != null) {
				buf.append("<b>Program: </b>")
						.append(this.getProgram().getName())
						.append("<br/><br/>");
			}
			if (this.getProject() != null) {
				buf.append("<b>Project: </b>")
						.append(this.getProject().getName())
						.append("<br/><br/>");
			}
			buf.append("<b>Title : </b>").append(this.title)
					.append("<br/><br/>");
			buf.append("<b>Date: </b>")
					.append(dateFormatter.format(this.getDate()))
					.append("<br/>");
			buf.append("<b>Time: </b>").append(this.getTime()).append("<br/>");
			buf.append("<b>Duration: </b>").append(this.getDuration())
					.append("<br/>");
			buf.append("<b>Location: </b>").append(this.getLocationInfo())
					.append("<br/>");
			buf.append("<b>Other Info: </b>").append(this.getOtherInfo())
					.append("<br/>");
			buf.append("<b>Type: </b>").append(this.getTypeString())
					.append("<br/><br/>");

			if (this.getAgendaItems() != null) {
				Iterator<MeetingAgendaItem> itr = this.getAgendaItems()
						.iterator();
				if (itr.hasNext()) {
					buf.append("<b>Meeting Agenda: </b>").append("<br/><br/>");
					int i = 1;
					buf.append("<table><tr><th></th><th>Topic</th><th>Owner</th><th>Description</th></tr>");
					while (itr.hasNext()) {
						MeetingAgendaItem agendaItem = itr.next();
						buf.append("<tr>");
						buf.append("<td>").append(i).append("</td>");
						buf.append("<td>").append(agendaItem.getTopic())
								.append("</td>");
						buf.append("<td>")
								.append(agendaItem.getMeetingAgendaOwner()
										.getName()).append("</td>");
						buf.append("<td>").append(agendaItem.getDescription())
								.append("</td>");
						buf.append("</tr>");
						i++;
					}
					buf.append("</table>");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return buf.toString();
	}

	@Override
	public String toString() {
		return "Meeting [id=" + id + "]";
	}

	@Override
	public Principal getOwner() {
		// TODO Auto-generated method stub
		return organizer;
	}

	@Override
	public Set<Principal> getStakeHolders() {
		return attendees;
	}
}

enum MeetingType {
	Webinar, ConferenceCall, Other
}