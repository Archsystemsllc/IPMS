package com.archsystemsinc.ipms.sec.model;




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
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import com.archsystemsinc.ipms.common.INameableEntity;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

@Entity
@XmlRootElement
@XStreamAlias("meetingAgenda")
@Table(name = "meetingagenda")
public class MeetingAgendaItem implements INameableEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "meetingagenda_id")
	@XStreamAsAttribute
	private Long id;

	@ManyToOne
	@JoinColumn(name = "meeting_id")
	private Meeting meeting;
	
	@ManyToOne
	@JoinColumn(name = "parking_id", nullable=true)
	private ParkingLot parkinglot;

//	@ManyToOne
//	@JoinColumn(name = "discussion_id" , nullable=true)
//	private DiscussionAgenda discussionagenda;

	@OneToMany(mappedBy = "meetingAgendaItem")
	@Cascade({CascadeType.SAVE_UPDATE})
	private List<DiscussionAgenda> discussionAgendas;
	
	public ParkingLot getParkinglot() {
		return parkinglot;
	}

	public void setParkinglot(ParkingLot parkinglot) {
		this.parkinglot = parkinglot;
	}

	public List<DiscussionAgenda> getDiscussionAgendas() {
		return discussionAgendas;
	}

	public void setDiscussionAgendas(List<DiscussionAgenda> discussionAgendas) {
		this.discussionAgendas = discussionAgendas;
	}

	@Column(nullable = false)
	private String topic;

	@Column(nullable = false)
	private String discussion;
	
	@Transient
	private Integer meetingAgendaOwnerId;

	@ManyToOne
	@JoinColumn(name = "meeting_agenda_owner", nullable=true)
	private Principal meetingAgendaOwner;

	@Column(nullable = true)
	private String name;

	@Column(nullable = true)
	private String description;

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(final Long id1) {
		id = id1;
	}	

	public Integer getMeetingAgendaOwnerId() {
		return meetingAgendaOwnerId;
	}

	public void setMeetingAgendaOwnerId(Integer meetingAgendaOwnerId) {
		this.meetingAgendaOwnerId = meetingAgendaOwnerId;
	}

	public String getDiscussion() {
		return discussion;
	}

	public void setDiscussion(final String discussion1) {
		discussion = discussion1;
	}

	public Meeting getMeeting() {
		return meeting;
	}

	public void setMeeting(final Meeting meeting1) {
		meeting = meeting1;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(final String topic1) {
		topic = topic1;
	}

	public Principal getMeetingAgendaOwner() {
		return meetingAgendaOwner;
	}

	public void setMeetingAgendaOwner(final Principal meetingAgendaOwner1) {
		meetingAgendaOwner = meetingAgendaOwner1;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(final String description1) {
		description = description1;
	}

	public void setName(final String name1) {
		name = name1;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public Principal getOwner() {
		// TODO Auto-generated method stub
		return meeting.getOrganizer();
	}

	@Override
	public Set<Principal> getStakeHolders() {
		// TODO Auto-generated method stub
		return null;
	}


}
