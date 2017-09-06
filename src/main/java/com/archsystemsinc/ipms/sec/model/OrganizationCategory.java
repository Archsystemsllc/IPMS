package com.archsystemsinc.ipms.sec.model;

import java.util.Date;
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
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

@Entity
@XmlRootElement
@XStreamAlias("organizationcategory")
@Table(name="organization_category_staff")
public class OrganizationCategory implements INameableEntity{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "orgcat_id")
	@XStreamAsAttribute
	private Long id;
	
	@Column(name="rate", nullable = true)
	private double rate;
	
	@ManyToOne
	@JoinColumn(name="resource_id", nullable = true)
	private Principal principal;
	
	@Column(name="category", nullable = true)
	private String category;
	
	@ManyToOne
	@JoinColumn(name = "created_by")
	private Principal createdBy;
	
	@ManyToOne
	@JoinColumn(name = "updated_by")
	private Principal updatedBy;
	
	@Column(name="date_created",nullable=true)
	private Date datecreated;
	
	@Column(name="date_updated",nullable=true)
	private Date dateupdated;
	
	@ManyToOne
	@JoinColumn(name = "project_id")
	private Project project;
	
	@Transient
	private long projectId;
	
	@Transient
	private long resourceId;
	
	
	public long getResourceId() {
		return resourceId;
	}

	public void setResourceId(long resourceId) {
		this.resourceId = resourceId;
	}

	public long getProjectId() {
		return projectId;
	}

	public void setProjectId(long projectId) {
		this.projectId = projectId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}

	public Principal getPrincipal() {
		return principal;
	}

	public void setPrincipal(Principal principal) {
		this.principal = principal;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Principal getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Principal createdBy) {
		this.createdBy = createdBy;
	}

	public Principal getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(Principal updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getDatecreated() {
		return datecreated;
	}

	public void setDatecreated(Date datecreated) {
		this.datecreated = datecreated;
	}

	public Date getDateupdated() {
		return dateupdated;
	}

	public void setDateupdated(Date dateupdated) {
		this.dateupdated = dateupdated;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
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
