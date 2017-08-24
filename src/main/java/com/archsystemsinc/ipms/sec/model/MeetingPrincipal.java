package com.archsystemsinc.ipms.sec.model;

import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

import com.archsystemsinc.ipms.common.INameableEntity;

@Entity
@XmlRootElement
@Table(name = "meeting_principal")
public class MeetingPrincipal implements
INameableEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "meeting_id", insertable = false, updatable = false)
	private Meeting meeting;

	@ManyToOne
	@JoinColumn(name = "principal_id", insertable = false, updatable = false)
	private Principal principal;

	@Transient
	private String name;

	@Column(name = "principal_id")
	private Long principalId;


	@Column(name = "attended", columnDefinition = "BIT", length = 1)
	private Boolean attended = false;

	@Column(name = "meeting_id")
	private Long meetingId;

	public Meeting getMeeting() {
		return meeting;
	}

	public void setMeeting(final Meeting meeting1) {
		meeting = meeting1;
	}	

	public Principal getPrincipal() {
		return principal;
	}

	public void setPrincipal(final Principal principal1) {
		principal = principal1;
	}

	public Long getMeetingId() {
		return meetingId;
	}

	public void setMeetingId(final Long meetingId1) {
		meetingId = meetingId1;
	}

	public Long getPrincipalId() {
		return principalId;
	}

	public void setPrincipalId(final Long principalIdToset) {
		principalId = principalIdToset;
	}

	public MeetingPrincipal() {
		super();
	}

	// API
	public void setName(final String name1) {
		name = name1;
	}

	//
	@Override
	public int hashCode(){
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals( final Object obj ){
		if( this == obj )
			return true;
		if( obj == null )
			return false;
		if( getClass() != obj.getClass() )
			return false;
		final MeetingPrincipal other = (MeetingPrincipal) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		}
		else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString(){
		return getName();
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public Long getId() {
		// TODO Auto-generated method stub
		return id;
	}

	@Override
	public void setId(final Long idToset) {
		// TODO Auto-generated method stub
		id = idToset;
	}

	public Boolean getAttended() {
		return attended;
	}

	public void setAttended(Boolean attended) {
		this.attended = attended;
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
