package com.archsystemsinc.ipms.sec.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import com.archsystemsinc.ipms.common.INameableEntity;
import com.thoughtworks.xstream.annotations.XStreamAlias;

@Entity
@XmlRootElement
@XStreamAlias("discussionagenda")
@Table(name = "discussion_agenda")
public class DiscussionAgenda implements INameableEntity {
	/**
	 * 
	 */ 
	private static final long serialVersionUID = -2480234211784738786L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "discussionagenda_id")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "meetingagenda_id" , nullable=true)
	private MeetingAgendaItem meetingAgendaItem;
	
	@Column
	private String item;
	
	@Column
	private String name;
	
	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	@Column
	private String facilitatorname;

	public String getFacilitatorname() {
		return facilitatorname;
	}

	public void setFacilitatorname(String facilitatorname) {
		this.facilitatorname = facilitatorname;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public MeetingAgendaItem getMeetingAgendaItem() {
		return meetingAgendaItem;
	}

	public void setMeetingAgendaItem(MeetingAgendaItem meetingAgendaItem) {
		this.meetingAgendaItem = meetingAgendaItem;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return name;
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

	
}
