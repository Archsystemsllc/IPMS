package com.archsystemsinc.ipms.sec.model;
/**
* This is Model Class for Action Items  
 * @author Nikhil Dasari
* @version 1.0
*/
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.validator.constraints.NotEmpty;

import com.archsystemsinc.ipms.common.INameableEntity;
import com.thoughtworks.xstream.annotations.XStreamAlias;


@Entity
@XmlRootElement
@XStreamAlias("ActionItem")
@Table(name = "actionitem")
public class ActionItem implements INameableEntity, Comparable<ActionItem> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "actionitem_id")
	private Long id;

	@Override
	public String toString() {
		return "ActionItem [id=" + id + "]";
	}

	@ManyToOne
	@JoinColumn(name = "meeting_id")
	private Meeting meeting;

	@ManyToOne
	@JoinColumn(name = "issue_id")
	private Issue issue;

	@Column(nullable = true)
	@NotEmpty
	private String summary;

	@Column(nullable = true)
	@NotEmpty
	private String priority;

	@Column(nullable = true)
	@NotEmpty
	private String status = ActionItemStatus.Pending.toString();

	@Column(name = "due_date", nullable = true)
	@OrderBy("dueDate asc")
	@NotNull
	private Date dueDate;

	@Column(name = "date_created", nullable = true)
	@OrderBy("dateCreated asc")
	private Date dateCreated;

	@Column(name="name",nullable = true)
	private String name;

	@ManyToOne
	@JoinColumn(name = "assigned_to")
	private Principal assignedTo;
	
	@OneToMany( mappedBy = "actionItem")
	private Set<RevisionHistory> revisions = new HashSet<RevisionHistory>();

	@Transient
	private Long assignedToId;

	@Transient
	private boolean createdFromMeeting;

	@Transient
	private Long issueId;

	@Transient
	private Long meetingId;
	
	@Column(name = "notes",nullable = true)
	private String notes;
	
	@Column(name="description",nullable = true)
	private String description;
	
	@Column(name = "responsible_party",nullable = true)
	private String responsibleParty;
	
	@Column(name = "ainumber_from_excel",nullable = true)
	private String aiNumberFromExcel;
	
	@Column(name = "date_completed", nullable = true)
	private Date dateCompleted;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getResponsibleParty() {
		return responsibleParty;
	}

	public void setResponsibleParty(String responsibleParty) {
		this.responsibleParty = responsibleParty;
	}

	public String getAiNumberFromExcel() {
		return aiNumberFromExcel;
	}

	public void setAiNumberFromExcel(String aiNumberFromExcel) {
		this.aiNumberFromExcel = aiNumberFromExcel;
	}

	public Date getDateCompleted() {
		return dateCompleted;
	}

	public void setDateCompleted(Date dateCompleted) {
		this.dateCompleted = dateCompleted;
	}


	@Override
	public int compareTo(final ActionItem actionItem) {

		int result = 0;
		try {
			final Date thisActionItemDate = getDateCreated();
			final Date actionItemDate = actionItem.getDateCreated();
			result = actionItemDate.compareTo(thisActionItemDate);
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return result;

	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(final Date dateCreated1) {
		dateCreated = dateCreated1;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(final String status1) {
		status = status1;
	}

	public Principal getAssignedTo() {
		return assignedTo;
	}

	public void setAssignedTo(final Principal assignedToSet) {
		assignedTo = assignedToSet;
	}


	public Issue getIssue() {
		return issue;
	}

	public void setIssue(final Issue issue1) {
		issue = issue1;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(final String summary1) {
		summary = summary1;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(final Date dueDateToSet) {
		dueDate = dueDateToSet;
	}

	public Meeting getMeeting() {
		return meeting;
	}

	public void setMeeting(final Meeting meetingToSet) {
		meeting = meetingToSet;
	}

	public Long getAssignedToId() {
		return assignedToId;
	}

	public void setAssignedToId(final Long assignedToIdToSet) {
		assignedToId = assignedToIdToSet;
	}

	public boolean isCreatedFromMeeting() {
		return createdFromMeeting;
	}

	public void setCreatedFromMeeting(final boolean createdFromMeeting1) {
		createdFromMeeting = createdFromMeeting1;
	}

	public Long getIssueId() {
		return issueId;
	}

	public void setIssueId(final Long issueIdToSet) {
		issueId = issueIdToSet;
	}

	public Long getMeetingId() {
		return meetingId;
	}

	public void setMeetingId(final Long meetingIdToSet) {
		meetingId = meetingIdToSet;
	}

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(final Long id1) {
		id = id1;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(final String priority1) {
		priority = priority1;
	}

	@Override
	public Principal getOwner() {
		return this.assignedTo;
	}

	@Override
	public Set<Principal> getStakeHolders() {
		Set<Principal>list = new HashSet<Principal>();
		list.addAll(issue.getStakeHolders());
		list.addAll(meeting.getStakeHolders());
		return list;
	}

	public Set<RevisionHistory> getRevisions() {
		return revisions;
	}

	public void setRevisions(Set<RevisionHistory> revisions) {
		this.revisions = revisions;
	}

}
