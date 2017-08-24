package com.archsystemsinc.ipms.sec.model;

import java.util.Date;
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
import javax.xml.bind.annotation.XmlRootElement;

import com.archsystemsinc.ipms.common.INameableEntity;
import com.thoughtworks.xstream.annotations.XStreamAlias;

@Entity
@XmlRootElement
@XStreamAlias("statusreportsignatory")
@Table(name = "statusreportsignatory")
public class StatusReportSignatory implements INameableEntity{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "statusreportsignatory_id")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "project_statusreport_id")
	private ProjectStatusReport projectStatusReport;	
	
	@Column
	private String name;
	
	@Column
	private String title;
	
	@Column(name = "active", columnDefinition = "BIT", length = 1)
	private boolean signatory = true;
	
	@Column
	private Date date;
	
	public ProjectStatusReport getProjectStatusReport() {
		return projectStatusReport;
	}

	public void setProjectStatusReport(ProjectStatusReport projectStatusReport) {
		this.projectStatusReport = projectStatusReport;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public boolean isSignatory() {
		return signatory;
	}

	public void setSignatory(boolean signatory) {
		this.signatory = signatory;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
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
	public Long getId() {
		// TODO Auto-generated method stub
		return id;
	}

	@Override
	public void setId(Long id) {
		// TODO Auto-generated method stub
		this.id = id;
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
