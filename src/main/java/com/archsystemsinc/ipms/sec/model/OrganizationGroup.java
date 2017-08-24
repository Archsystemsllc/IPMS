package com.archsystemsinc.ipms.sec.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.validator.constraints.NotEmpty;

import com.archsystemsinc.ipms.common.INameableEntity;
import com.thoughtworks.xstream.annotations.XStreamAlias;

@Entity
@XmlRootElement
@XStreamAlias("OrganizationGroup")
@Table(name = "organizationgroup")
public class OrganizationGroup implements INameableEntity {

	private static final long serialVersionUID = 4337393254958910319L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "group_id")
	private Long id;

	@Override
	public String toString() {
		return "OrganizationGroup [id=" + id + "]";
	}
	
	@Column(name="director")
	private String director;
	
	@Column(name="first_deputy")
	private String firstDeputy;
	
	@Column(name="record_status")
	private String recordStatus;
	
	@Column(name="second_deputy")
	private String secondDeputy;

	@Column(name="group_name")
	@NotEmpty
	private String name;
	
	@Column(name="group_description")
	private String description;

	@Column(name = "date_created")
	@OrderBy("dateCreated asc")
	private Date dateCreated;
	
	@Column(name = "start_date")
	@OrderBy("startDate asc")
	private Date startDate;
	
	@Column(name = "end_date")
	@OrderBy("endDate asc")
	private Date endDate;

	@OneToMany(mappedBy = "organizationGroup")
	private Set<Program> programs = new HashSet<Program>();
	
	@Transient
	private Integer numberOfProjects;
	
	
	public Integer getNumberOfProjects() {
		return numberOfProjects;
	}

	public void setNumberOfProjects(Integer numberOfProjects) {
		this.numberOfProjects = numberOfProjects;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public String getFirstDeputy() {
		return firstDeputy;
	}

	public void setFirstDeputy(String firstDeputy) {
		this.firstDeputy = firstDeputy;
	}

	public String getSecondDeputy() {
		return secondDeputy;
	}

	public void setSecondDeputy(String secondDeputy) {
		this.secondDeputy = secondDeputy;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Set<Program> getPrograms() {
		return programs;
	}

	public void setPrograms(Set<Program> programs) {
		this.programs = programs;
	}

	public String getRecordStatus() {
		return recordStatus;
	}

	public void setRecordStatus(String recordStatus) {
		this.recordStatus = recordStatus;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrganizationGroup other = (OrganizationGroup) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public Principal getOwner() {
		return null;
	}

	@Override
	public Set<Principal> getStakeHolders() {
		return null;
	}
}

