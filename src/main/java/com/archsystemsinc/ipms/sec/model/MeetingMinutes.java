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
@XStreamAlias("MeetingMinutes")
@Table(name = "meetingminute")
public class MeetingMinutes implements INameableEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "meetingminute_id")
	private Long id;

	@Column
	private String discussion;

	@Column(name = "meeting_id", nullable = true)
	private Long meetingid;

	@Column(name = "attendee_id", nullable = true)
	private Long attendeeid;

	public Long getMeetingid() {
		return meetingid;
	}

	public void setMeetingid(Long meetingid) {
		this.meetingid = meetingid;
	}

	public Long getAttendeeid() {
		return attendeeid;
	}

	public void setAttendeeid(Long attendeeid) {
		this.attendeeid = attendeeid;
	}

	@Column(name = "meeting_minute_name", nullable = true)
	private String name;

	@Transient
	private String attendeesList;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "meeting_principal", joinColumns = { @JoinColumn(name = "meeting_id") }, inverseJoinColumns = { @JoinColumn(name = "principal_id") })
	@NotNull
	private Set<Principal> attendees = new HashSet<Principal>();

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "meeting_attendee", joinColumns = { @JoinColumn(name = "meeting_id") }, inverseJoinColumns = { @JoinColumn(name = "attendee_id") })
	private Set<Attendee> externalAttendees = new HashSet<Attendee>();

	@Column(nullable = true)
	@NotEmpty
	private String scribe;

	@Column(name = "start_time", nullable = true)
	private String startTime;

	@Column(name = "end_time", nullable = true)
	private String endTime;

	public String getDiscussion() {
		return discussion;
	}

	public void setDiscussion(String discussion) {
		this.discussion = discussion;
	}

	public String getAttendeesList() {
		return attendeesList;
	}

	public void setAttendeesList(String attendeesList) {
		this.attendeesList = attendeesList;
	}

	public Set<Principal> getAttendees() {
		return attendees;
	}

	public void setAttendees(Set<Principal> attendees) {
		this.attendees = attendees;
	}

	public String getScribe() {
		return scribe;
	}

	public void setScribe(String scribe) {
		this.scribe = scribe;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public Set<Attendee> getExternalAttendees() {
		return externalAttendees;
	}

	public void setExternalAttendees(final Set<Attendee> externalAttendees1) {
		externalAttendees = externalAttendees1;
	}

	@Override
	public Long getId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setId(Long id) {
		// TODO Auto-generated method stub

	}

	@Override
	public Principal getOwner() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Principal> getStakeHolders() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	

}