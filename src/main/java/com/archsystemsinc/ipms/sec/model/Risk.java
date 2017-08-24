package com.archsystemsinc.ipms.sec.model;

import java.util.HashSet;
import java.util.List;
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
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.validator.constraints.NotEmpty;

import com.archsystemsinc.ipms.common.INameableEntity;
import com.thoughtworks.xstream.annotations.XStreamAlias;

@Entity
@XmlRootElement
@XStreamAlias("Risk")
@Table(name = "risk")
public class Risk implements INameableEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "risk_id")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "project_id")
	private Project project;

	@ManyToOne
	@JoinColumn(name = "program_id")
	private Program program;
	
	@Column(nullable = true)
	@NotEmpty
	private String status = RiskStatus.Open.toString();

	@Column(name = "risk_summary", nullable = true)
	@NotEmpty
	private String riskSummary;

	@Column(nullable = true)
	private String name;

	@Column(nullable = true)
	@NotEmpty
	private String likelihood;

	@Column(nullable = true)
	@NotEmpty
	private String impact;

	@Column(name = "mitigating_factors", nullable = true)
	private String mitigatingFactors;

	@ManyToOne
	@JoinColumn(name = "principal_id", nullable = true)
	private Principal responsiblePerson;
	
	@OneToMany( mappedBy = "risk")
	private Set<RevisionHistory> revisions = new HashSet<RevisionHistory>();

	@Transient
	private Long programId;

	@Transient
	private Long projectId;

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(final Long id1) {
		id = id1;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(final Project projectToSet) {
		project = projectToSet;
	}

	public Long getProgramId() {
		return programId;
	}

	public void setProgramId(final Long programId1) {
		programId = programId1;
	}

	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(final Long projectId1) {
		projectId = projectId1;
	}

	@Override
	public String getName() {
		return name;
	}

	public void setName(final String nameToSet) {
		name = nameToSet;
	}

	public Program getProgram() {
		return program;
	}

	public void setProgram(final Program programToSet) {
		program = programToSet;
	}

	public String getRiskSummary() {
		return riskSummary;
	}

	public void setRiskSummary(final String riskSummaryToSet) {
		riskSummary = riskSummaryToSet;
	}

	public String getLikelihood() {
		return likelihood;
	}

	public void setLikelihood(final String likelihoodToSet) {
		likelihood = likelihoodToSet;
	}

	public String getImpact() {
		return impact;
	}

	public void setImpact(final String impactToSet) {
		impact = impactToSet;
	}

	public String getMitigatingFactors() {
		return mitigatingFactors;
	}

	public void setMitigatingFactors(final String mitigatingFactorsToSet) {
		mitigatingFactors = mitigatingFactorsToSet;
	}

	public Principal getResponsiblePerson() {
		return responsiblePerson;
	}

	public void setResponsiblePerson(final Principal responsiblePersonToSet) {
		responsiblePerson = responsiblePersonToSet;
	}	

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public Principal getOwner() {
		// TODO Auto-generated method stub
		return responsiblePerson;
	}

	@Override
	public Set<Principal> getStakeHolders() {
		// TODO Auto-generated method stub
		return null;
	}

	public Set<RevisionHistory> getRevisions() {
		return revisions;
	}

	public void setRevisions(Set<RevisionHistory> revisions) {
		this.revisions = revisions;
	}


}
