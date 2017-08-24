package com.archsystemsinc.ipms.sec.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.validator.constraints.NotEmpty;

import com.archsystemsinc.ipms.common.INameableEntity;
import com.thoughtworks.xstream.annotations.XStreamAlias;

@Entity
@XmlRootElement
@XStreamAlias("projectreport")
@Table(name = "project_statusreport")
public class ProjectStatusReport implements INameableEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "project_statusreport_id")
	private Long id;

	@Column(unique = true, nullable = false)
	private String name;
	
	@Column(name="reporting_period", unique = true, nullable = false)
	private String reportingPeriod;
	
	@Column(name = "report_date", nullable = true)
	private Date reportDate;

	@Column(name = "budget_status", nullable = true)
	private String budgetStatus = ProjectOverallStatus.Empty.toString();
	
	@Column(name = "scope_status", nullable = true)
	private String scopeStatus = ProjectOverallStatus.Empty.toString();

	@Column(name = "quality_status", nullable = true)
	private String qualityStatus = ProjectOverallStatus.Empty.toString();
	
	@Column(name = "issue_status", nullable = true)
	private String issueStatus;
	
	@Column(name = "change_status", nullable = true)
	private String changeStatus;
	
	@Column(name = "project_definition", nullable = true)
	private String projectDefinition;

	@Column(name = "deliverables_produced", nullable = true)
	private String deliverablesProduced;
	
	@ManyToOne
	@JoinColumn(name = "project_id")
	private Project project;	
	
	@ManyToOne
	@JoinColumn(name = "prepared_by")
	private Principal preparedBy;
	
	@OneToMany( mappedBy = "projectStatusReport")
	private Set<StatusReportSignatory> statusReportSignatorys = new HashSet<StatusReportSignatory>();
	
	@Override
	public Long getId(){
		return id;
	}

	@Override
	public void setId(final Long idToSet) {
		id = idToSet;
	}

	@Override
	public String getName(){
		return name;
	}

	public void setName( final String nameToSet ){
		name = nameToSet;
	}

	public String getReportingPeriod() {
		return reportingPeriod;
	}

	public void setReportingPeriod(String reportingPeriod) {
		this.reportingPeriod = reportingPeriod;
	}

	public Principal getPreparedBy() {
		return preparedBy;
	}

	public void setPreparedBy(Principal preparedBy) {
		this.preparedBy = preparedBy;
	}

	public Date getReportDate() {
		return reportDate;
	}

	public void setReportDate(Date reportDate) {
		this.reportDate = reportDate;
	}

	public String getBudgetStatus() {
		return budgetStatus;
	}

	public void setBudgetStatus(String budgetStatus) {
		this.budgetStatus = budgetStatus;
	}

	public String getScopeStatus() {
		return scopeStatus;
	}

	public void setScopeStatus(String scopeStatus) {
		this.scopeStatus = scopeStatus;
	}

	public String getQualityStatus() {
		return qualityStatus;
	}

	public void setQualityStatus(String qualityStatus) {
		this.qualityStatus = qualityStatus;
	}

	public String getIssueStatus() {
		return issueStatus;
	}

	public void setIssueStatus(String issueStatus) {
		this.issueStatus = issueStatus;
	}

	public String getChangeStatus() {
		return changeStatus;
	}

	public void setChangeStatus(String changeStatus) {
		this.changeStatus = changeStatus;
	}

	public String getProjectDefinition() {
		return projectDefinition;
	}

	public void setProjectDefinition(String projectDefinition) {
		this.projectDefinition = projectDefinition;
	}

	public String getDeliverablesProduced() {
		return deliverablesProduced;
	}

	public void setDeliverablesProduced(String deliverablesProduced) {
		this.deliverablesProduced = deliverablesProduced;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public Set<StatusReportSignatory> getStatusReportSignatorys() {
		return statusReportSignatorys;
	}

	public void setStatusReportSignatorys(
			Set<StatusReportSignatory> statusReportSignatorys) {
		this.statusReportSignatorys = statusReportSignatorys;
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
