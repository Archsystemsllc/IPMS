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

@Entity
@XmlRootElement
@XStreamAlias("FinancialExpenses")
@Table(name = "financial_expenses")
@CheckDateRange(ObjectTypeEnum.Issue)
public class FinancialExpenses implements
INameableEntity {

	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "reporting_id")
	private Long id;

	@Column(name = "reporting_dt")
	@NotNull
	private Date reportingDate;
	
	@Column(name = "description")
	@NotNull
	private String description;
	
	@ManyToOne
	@JoinColumn(name = "header_id")
	private FinancialHeader financialHeader;
	
	@Column(name = "total_funding")
	@NotNull
	private Long totalFunding;
	
	@Column(name = "current_invoice")
	@NotNull
	private Long currentInvoice;
	
	@Column(name = "total_invoiced")
	@NotNull
	private Long totalInvoiced;
	
	@Column(name = "funds_remaining")
	@NotNull
	private Long fundsRemaining;
	
	@Column(name = "current_spent")
	@NotNull
	private Float currentSpent;
	
	@Column(name = "prj_exp_eoc")
	@NotNull
	private Long prjExpnsThruEndOfContract;

	@Column(name = "variance")
	@NotNull
	private Long variance;

	@Column(name = "prj_spent")
	@NotNull
	private Float projectedSpent;

	public Long getId() {
		return id;
	}

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

	public Long getTotalFunding() {
		return totalFunding;
	}

	public void setTotalFunding(Long totalFunding) {
		this.totalFunding = totalFunding;
	}

	public Long getCurrentInvoice() {
		return currentInvoice;
	}

	public void setCurrentInvoice(Long currentInvoice) {
		this.currentInvoice = currentInvoice;
	}

	public Long getTotalInvoiced() {
		return totalInvoiced;
	}

	public void setTotalInvoiced(Long totalInvoiced) {
		this.totalInvoiced = totalInvoiced;
	}

	public Long getFundsRemaining() {
		return fundsRemaining;
	}

	public void setFundsRemaining(Long fundsRemaining) {
		this.fundsRemaining = fundsRemaining;
	}

	public Float getCurrentSpent() {
		return currentSpent;
	}

	public void setCurrentSpent(Float currentSpent) {
		this.currentSpent = currentSpent;
	}

	public Long getPrjExpnsThruEndOfContract() {
		return prjExpnsThruEndOfContract;
	}

	public void setPrjExpnsThruEndOfContract(Long prjExpnsThruEndOfContract) {
		this.prjExpnsThruEndOfContract = prjExpnsThruEndOfContract;
	}

	public Long getVariance() {
		return variance;
	}

	public void setVariance(Long variance) {
		this.variance = variance;
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
