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
@XStreamAlias("FinancialHours")
@Table(name = "financial_hours")
@CheckDateRange(ObjectTypeEnum.Issue)
public class FinancialHours implements
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
	
	@Column(name = "total_hours_planned")
	@NotNull
	private Long totalHoursPlanned;
	
	@Column(name = "current_hours")
	@NotNull
	private Long currentHours;
	
	@Column(name = "cumulative_total_actual_hours")
	@NotNull
	private Long cumulativeTotalActualHours;
	
	@Column(name = "total_hours_remaining")
	@NotNull
	private Long totalHoursRemaining;
	
	@Column(name = "current_used_percent")
	@NotNull
	private Float currentUsedPercent;
	
	@Column(name = "est_hours_completion")
	@NotNull
	private Long estHoursCompletion;

	@Column(name = "variance_from_baseline")
	@NotNull
	private Long varianceFromBaseline;

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
