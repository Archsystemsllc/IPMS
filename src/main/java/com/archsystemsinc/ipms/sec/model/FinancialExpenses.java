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
* This is the Financial Expenses Model class. Mapped to financial_expenses table
* @author Benigna
* @version 0.2.1
*/
@Entity
@XmlRootElement
@XStreamAlias("FinancialExpenses")
@Table(name = "financial_expenses")
@CheckDateRange(ObjectTypeEnum.Issue)
public class FinancialExpenses implements
INameableEntity {

	
	private static final long serialVersionUID = 1L;

	/**
	 * The Reporting Id
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
	 * The Reporting Description
	 */
	@Column(name = "description")
	@NotNull
	private String description;
	
	/**
	 * The parent FinancialHeader 
	 * @see FinancialHeader
	 */
	@ManyToOne
	@JoinColumn(name = "header_id")
	private FinancialHeader financialHeader;
	
	/**
	 * The Total Funding
	 */
	@Column(name = "total_funding")
	@NotNull
	private Long totalFunding;
	
	/**
	 * The Current Invoice
	 */
	@Column(name = "current_invoice")
	@NotNull
	private Long currentInvoice;
	
	/**
	 * The Total Invoiced
	 */
	@Column(name = "total_invoiced")
	@NotNull
	private Long totalInvoiced;
	
	/**
	 * The Funds Remaining
	 */
	@Column(name = "funds_remaining")
	@NotNull
	private Long fundsRemaining;
	
	/**
	 * The current amount spent
	 */
	@Column(name = "current_spent")
	@NotNull
	private Float currentSpent;
	
	/**
	 * The Projected Expenses through End of Contract
	 */
	@Column(name = "prj_exp_eoc")
	@NotNull
	private Long prjExpnsThruEndOfContract;

	/**
	 * The Variance
	 */
	@Column(name = "variance")
	@NotNull
	private Long variance;

	/**
	 * The Projected Amount Spent
	 */
	@Column(name = "prj_spent")
	@NotNull
	private Float projectedSpent;

	/**
	 * Returns the Expenses Id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Sets the Expense Id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Returns the Reporting Date
	 * @return The Reporting Date
	 */
	public Date getReportingDate() {
		return reportingDate;
	}

	/** Sets the Reporting Date
	 * @param reportingDate the Reporting Date to be set
	 */
	public void setReportingDate(Date reportingDate) {
		this.reportingDate = reportingDate;
	}
	/**
	 * Returns the description for this expense
	 * @return The description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets the description for this expenese
	 * @param description The Description to be set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * The Financial Header for this expense
	 * @return The Financial Header for this expnse 
	 */
	public FinancialHeader getFinancialHeader() {
		return financialHeader;
	}

	/**
	 * Sets the Financial Header
	 * @param financialHeader The Financial Header
	 */
	public void setFinancialHeader(FinancialHeader financialHeader) {
		this.financialHeader = financialHeader;
	}

	/**
	 * Returns the Total Funding
	 * @return The Total Funding
	 */
	public Long getTotalFunding() {
		return totalFunding;
	}

	/**
	 * Sets the Total Funding
	 * @param totalFunding the Total Funding to be set
	 */
	public void setTotalFunding(Long totalFunding) {
		this.totalFunding = totalFunding;
	}

	/**
	 * Returns the current Invoice
	 * @return The current invoice
	 */
	public Long getCurrentInvoice() {
		return currentInvoice;
	}

	/**
	 * Sets the current Invoice
	 * @param currentInvoice The current invoice to be set
	 */
	public void setCurrentInvoice(Long currentInvoice) {
		this.currentInvoice = currentInvoice;
	}

	/**
	 * Returns the total invoiced
	 * @return The total invoiced
	 */
	public Long getTotalInvoiced() {
		return totalInvoiced;
	}

	/**
	 * Set the Total Invoiced
	 * @param totalInvoiced the total invoiced to be set
	 */
	public void setTotalInvoiced(Long totalInvoiced) {
		this.totalInvoiced = totalInvoiced;
	}

	/**
	 * The Funds Remaining
	 * @return The Funds Remaining
	 */
	public Long getFundsRemaining() {
		return fundsRemaining;
	}

	/**
	 * Set the funds remaining
	 * @param fundsRemaining the remaining funds to be set
	 */
	public void setFundsRemaining(Long fundsRemaining) {
		this.fundsRemaining = fundsRemaining;
	}

	/** 
	 * The Current Amount Spent
	 * @return the Current Spent
	 */
	public Float getCurrentSpent() {
		return currentSpent;
	}

	/**
	 * Sets the Current Spent Amount
	 * @param currentSpent the Current Spent Amount to set
	 */
	public void setCurrentSpent(Float currentSpent) {
		this.currentSpent = currentSpent;
	}

	/**
	 * Returns the Projected Expenses through End Of Contract
	 * @return the Projected Expenses through End Of Contract
	 */
	public Long getPrjExpnsThruEndOfContract() {
		return prjExpnsThruEndOfContract;
	}

	/**
	 * Sets the Projected Expenses through End Of Contract
	 * @param prjExpnsThruEndOfContract The Projected Expenses through End Of Contract to be set
	 */
	public void setPrjExpnsThruEndOfContract(Long prjExpnsThruEndOfContract) {
		this.prjExpnsThruEndOfContract = prjExpnsThruEndOfContract;
	}

	/**
	 * Returns the Variance
	 * @return the varianace
	 */
	public Long getVariance() {
		return variance;
	}

	/**
	 * Sets the Variance
	 * @param variance
	 */
	public void setVariance(Long variance) {
		this.variance = variance;
	}

	/**
	 * Returns the Projected Spent
	 * @return the Projected Spent
	 */
	public Float getProjectedSpent() {
		return projectedSpent;
	}

	/**
	 * Sets the Projected Spent
	 * @param projectedSpent the Projected Spent to spent
	 */
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
