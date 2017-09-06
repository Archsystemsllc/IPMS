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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

import com.archsystemsinc.ipms.common.INameableEntity;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

@Entity
@XmlRootElement
@XStreamAlias("wbsBudget")
@Table(name = "wbs_budget")
public class WBSBudget implements INameableEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "wbs_id")
	@XStreamAsAttribute
	private Long id;

	@ManyToOne
	@JoinColumn(name="task_id")
	private Task task;
	
	@ManyToOne
	@JoinColumn(name = "created_by", nullable=true)
	private Principal createdBy;
	
	@ManyToOne
	@JoinColumn(name = "updated_by", nullable=true)
	private Principal updatedBy;
	
	@Column(name="date_created",nullable=true)
	private Date datecreated;
	
	@Column(name="date_updated",nullable=true)
	private Date dateupdated;
	
	@Transient
	private long taskId;
	
	
	
	public long getTaskId() {
		return taskId;
	}

	public void setTaskId(long taskId) {
		this.taskId = taskId;
	}

	public Task getTask() {
		return task;
	}

	public void setTask(Task task) {
		this.task = task;
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
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
