/**
* Copyright (c) 2017, Archsystems Inc and/or its affiliates. All rights reserved.
*/
package com.archsystemsinc.ipms.sec.model;

import java.sql.Date;
import java.util.HashSet;
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
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import com.archsystemsinc.ipms.common.INameableEntity;
import com.archsystemsinc.ipms.sec.model.constraint.CheckDateRange;
import com.archsystemsinc.ipms.sec.model.constraint.ObjectTypeEnum;
import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
* This is the Financial Header Model class. Mapped to financial_header table
* @author Benigna
* @version 0.2.1
*/
@Entity
@XmlRootElement
@XStreamAlias("FinancialHeader")
@Table(name = "financial_header")
@CheckDateRange(ObjectTypeEnum.Issue)
public class FinancialHeader implements
INameableEntity {

	
	private static final long serialVersionUID = 1L;
	/**
	 * The header id
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "header_id")
	private Long id;

	/**
	 * The Project Id
	 */
	@ManyToOne
	@JoinColumn(name = "project_id")
	private Project project;
	
	/**
	 * The Start Date
	 */
	@Column(name = "start_dt", nullable = true)
	@NotNull
	private Date startDate;
	
	/**
	 * The End Date
	 */
	@Column(name = "end_dt", nullable = true)
	@NotNull
	private Date endDate;

	/**
	 * The FinancialExpenses for the given header
	 */
	@OneToMany( mappedBy = "financialHeader")
	private Set<FinancialExpenses> financialExpenses = new HashSet<FinancialExpenses>();

	/**
	 * Returns the header Id
	 * @return the header Id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Sets the header Id
	 * @param id the id to be set
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * Returns the Project associated with this <code>FinancialHeader</code>
	 * @return The <code>Project</code>
	 */
	public Project getProject() {
		return project;
	}

	/**
	 * Set the Project
	 * @param project the project to be set
	 */
	public void setProject(Project project) {
		this.project = project;
	}

	/**
	 * Returns the start Date
	 * @return the start Date
	 */
	public Date getStartDate() {
		return startDate;
	}

	/** Sets the Start Date
	 * 
	 * @param startDate The StartDate to be set
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	/**
	 * Returns the End Date
	 * @return The End Date
	 */
	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((endDate == null) ? 0 : endDate.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((project == null) ? 0 : project.hashCode());
		result = prime * result + ((startDate == null) ? 0 : startDate.hashCode());
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
		FinancialHeader other = (FinancialHeader) obj;
		if (endDate == null) {
			if (other.endDate != null)
				return false;
		} else if (!endDate.equals(other.endDate))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (project == null) {
			if (other.project != null)
				return false;
		} else if (!project.equals(other.project))
			return false;
		if (startDate == null) {
			if (other.startDate != null)
				return false;
		} else if (!startDate.equals(other.startDate))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "FinancialHeader [id=" + id + ", project=" + project + ", startDate=" + startDate + ", endDate="
				+ endDate + "]";
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
