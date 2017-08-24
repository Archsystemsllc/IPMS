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

import com.archsystemsinc.ipms.common.INameableEntity;
import com.thoughtworks.xstream.annotations.XStreamAlias;

@Entity
@XStreamAlias("projectmilestone")
@Table(name = "project_milestone")
public class ProjectMileStone implements INameableEntity{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "project_milestoneid")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "project_id")
	private Project project;
	
	@Column
	private String name;
	
	@Column(name="approved_schedule")
	private String approvedSchedule;

	@Column
	private String actual;
	
	@Column(name="current_forecast")
	private String currentForecast;
	
	@Column
	private String status;
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getApprovedSchedule() {
		return approvedSchedule;
	}

	public void setApprovedSchedule(String approvedSchedule) {
		this.approvedSchedule = approvedSchedule;
	}

	public String getActual() {
		return actual;
	}

	public void setActual(String actual) {
		this.actual = actual;
	}

	public String getCurrentForecast() {
		return currentForecast;
	}

	public void setCurrentForecast(String currentForecast) {
		this.currentForecast = currentForecast;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public int hashCode(){
		final int prime = 31;
		int result = 1;
		result = prime * result + ( ( name == null ) ? 0 : name.hashCode() );
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
		final Project other = (Project) obj;
		if( name == null ){
			if( other.name != null )
				return false;
		}
		else if( !name.equals( other.name ) )
			return false;
		return true;
	}

	@Override
	public String toString(){
		return getName();
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}

	@Override
	public Principal getOwner() {
		// TODO Auto-generated method stub
		return project.getOwner();
	}

	@Override
	public Set<Principal> getStakeHolders() {
		// TODO Auto-generated method stub
		return null;
	}
}
