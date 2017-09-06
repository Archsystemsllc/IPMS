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
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import com.archsystemsinc.ipms.common.INameableEntity;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

@Entity
@XmlRootElement
@XStreamAlias("forecast")
@Table(name = "forecast")
public class Forecast implements INameableEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "forecast_id")
	@XStreamAsAttribute
	private Long id;

	@Column(name="total_amount", nullable = true)
	private double totalAmount;
	
	@Column(name="start_amount", nullable = true)
	private double startAmount;
	
	@Column(name="spent_amount" ,nullable = true)
	private double spentAmount;
	
	@Column(name="left_amount",nullable=true)
	private double leftAmount;
	
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
	
	@Column(nullable = true)
	@OrderBy("date asc")
	private Date date;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public double getStartAmount() {
		return startAmount;
	}

	public void setStartAmount(double startAmount) {
		this.startAmount = startAmount;
	}

	public double getSpentAmount() {
		return spentAmount;
	}

	public void setSpentAmount(double spentAmount) {
		this.spentAmount = spentAmount;
	}
	public double getLeftAmount() {
		return leftAmount;
	}

	public void setLeftAmount(double leftAmount) {
		this.leftAmount = leftAmount;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	public Principal getCreatedBy() {
		return createdBy;
	}

	public void setCreatedby(Principal createdBy) {
		this.createdBy = createdBy;
	}

	public Principal getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedby(Principal updatedBy) {
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
