/**
* Copyright (c) 2017, Archsystems Inc and/or its affiliates. All rights reserved.
*/
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
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import com.archsystemsinc.ipms.common.INameableEntity;
import com.archsystemsinc.ipms.sec.model.constraint.CheckDateRange;
import com.archsystemsinc.ipms.sec.model.constraint.ObjectTypeEnum;
import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
* This is the Financial Hours Model class. Mapped to financial_hours table
* @author Benigna
* @version 0.2.1
*/
@Entity
@XmlRootElement
@XStreamAlias("FinancialHours")
@Table(name = "financial_hours")
@CheckDateRange(ObjectTypeEnum.Issue)
public class FinancialHours implements
INameableEntity {

	
	private static final long serialVersionUID = 1L;

	/**
	 * The reporting ID for this financial hours
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "reporting_id")
	private Long id;

	/**
	 * The Reporting Date
	 */
	@Column(name = "reporting_dt")
	@NotNull
	private Date reportingDate;
	
	/** 
	 * The description about the hours
	 */
	@Column(name = "description")
	@NotNull
	private String description;
	
	/**
	 * The financial header for this financial hours
	 */
	@ManyToOne
	@JoinColumn(name = "header_id")
	private FinancialHeader financialHeader;
	
	/**
	 * The total hours planned
	 */
	@Column(name = "total_hours_planned")
	@NotNull
	private Long totalHoursPlanned;
	
	/**
	 * The Current Hours
	 */
	@Column(name = "current_hours")
	@NotNull
	private Long currentHours;
	
	/**
	 * The Cumulative total hours
	 */
	@Column(name = "cumulative_total_actual_hours")
	@NotNull
	private Long cumulativeTotalActualHours;
	
	/**
	 * The total hours remaining
	 */
	@Column(name = "total_hours_remaining")
	@NotNull
	private Long totalHoursRemaining;
	
	/**
	 * The Curretn used Percent
	 */
	@Column(name = "current_used_percent")
	@NotNull
	private Float currentUsedPercent;
	
	/**
	 * Estimated Hours of Completion
	 */
	@Column(name = "est_hours_completion")
	@NotNull
	private Long estHoursCompletion;

	/**
	 * Variance From Baseline
	 */
	@Column(name = "variance_from_baseline")
	@NotNull
	private Long varianceFromBaseline;

	/**
	 * The Projected Spent
	 */
	@Column(name = "prj_spent")
	@NotNull
	private Float projectedSpent;

	/**
	 * Returns the Id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Sets the Financial Hours Id
	 * the id to be set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	public Date getReportingDate() {
		return reportingDate;
	}

	public void setReportingDate(Date reportingDate) {
		this.reportingDate = reportingDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public FinancialHeader getFinancialHeader() {
		return financialHeader;
	}

	public void setFinancialHeader(FinancialHeader financialHeader) {
		this.financialHeader = financialHeader;
	}

	public Long getTotalHoursPlanned() {
		return totalHoursPlanned;
	}

	public void setTotalHoursPlanned(Long totalHoursPlanned) {
		this.totalHoursPlanned = totalHoursPlanned;
	}

	public Long getCurrentHours() {
		return currentHours;
	}

	public void setCurrentHours(Long currentHours) {
		this.currentHours = currentHours;
	}

	public Long getCumulativeTotalActualHours() {
		return cumulativeTotalActualHours;
	}

	public void setCumulativeTotalActualHours(Long cumulativeTotalActualHours) {
		this.cumulativeTotalActualHours = cumulativeTotalActualHours;
	}

	public Long getTotalHoursRemaining() {
		return totalHoursRemaining;
	}

	public void setTotalHoursRemaining(Long totalHoursRemaining) {
		this.totalHoursRemaining = totalHoursRemaining;
	}

	public Float getCurrentUsedPercent() {
		return currentUsedPercent;
	}

	public void setCurrentUsedPercent(Float currentUsedPercent) {
		this.currentUsedPercent = currentUsedPercent;
	}

	public Long getEstHoursCompletion() {
		return estHoursCompletion;
	}

	public void setEstHoursCompletion(Long estHoursCompletion) {
		this.estHoursCompletion = estHoursCompletion;
	}

	public Long getVarianceFromBaseline() {
		return varianceFromBaseline;
	}

	public void setVarianceFromBaseline(Long varianceFromBaseline) {
		this.varianceFromBaseline = varianceFromBaseline;
	}

	public Float getProjectedSpent() {
		return projectedSpent;
	}

	public void setProjectedSpent(Float projectedSpent) {
		this.projectedSpent = projectedSpent;
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
