package com.archsystemsinc.ipms.sec.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.validator.constraints.NotEmpty;

import com.archsystemsinc.ipms.common.INameableEntity;

@Entity
@XmlRootElement
@Table(name = "requirement")
public class Requirement implements
INameableEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "requirement_id")
	private Long id;

	@Transient
	private String priorityString;

	@Transient
	private String requirementTypeString;

	@Transient
	private String riskLevelString;

	@Column(unique = true, nullable = true)
	private String name;

	@Column(unique = true, nullable = true)
	private String summary;

	@Column(nullable = false)
	private String description;

	@Column(nullable = false)
	private int priority;

	@Column(nullable = false)
	private int type;

	@Column(nullable = false)
	private int riskLevel;

	@Column(nullable = false)
	@NotEmpty
	private String riskDescription;

	@OneToMany( mappedBy = "requirement")
	private Set<Comment> comments = new HashSet<Comment>();

	@OneToMany( mappedBy = "requirement")
	private Set<RevisionHistory> revisions = new HashSet<RevisionHistory>();

	public Requirement() {
		super();
	}

	public Requirement(final String summaryToSet,
			final Set<Comment> commentsToSet) {
		super();
		summary = summaryToSet;
	}
	
	public Long getId(){
		return id;
	}

	public Set<RevisionHistory> getRevisions() {
		return revisions;
	}

	public void setRevisions(final Set<RevisionHistory> revisionsToSet) {
		revisions = revisionsToSet;
	}

	public Set<Comment> getComments() {
		return comments;
	}

	public void setComments(final Set<Comment> commentsToSet) {
		comments = commentsToSet;
	}
	
	

	/*public String getPriorityString() {
		if (priority == 0)
		{
			return RequirementPriority.High.name();
		}
		else if (priority == 1){
			return RequirementPriority.Medium.name();
		}
		else{
			return RequirementPriority.Low.name();
		}

	}

	public String getRequirementTypeString() {
		if (type == 0)
		{
			return RequirementType.Business.name();
		}
		else if (type == 1)
		{
			return RequirementType.Functional.name();
		}
		else
		{
			return RequirementType.User.name();
		}
	}

	public String getRiskLevelString() {
		if (riskLevel == 0)
		{
			return RiskLevelType.High.name();
		}
		else if (riskLevel == 1)
		{
			return RiskLevelType.Medium.name();
		}
		else
		{
			return RiskLevelType.Low.name();
		}
	}

	@Override
	public void setId( final Long idToSet ){
		id = idToSet;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(final int priorityToSet) {
		priority = priorityToSet;
	}

	public int getType() {
		return type;
	}

	public void setType(final int typeToSet) {
		type = typeToSet;
	}

	public int getRiskLevel() {
		return riskLevel;
	}

	public void setRisk(final int riskToSet) {
		riskLevel = riskToSet;
	}

	public String getRiskDescription() {
		return riskDescription;
	}

	public void setRiskDescription(final String riskDescriptionToSet) {
		riskDescription = riskDescriptionToSet;
	}

	@Override
	public String getName() {
		return summary.replace(" ", "");
	}

	public void setName(final String nameToSet) {
		name = nameToSet;
	}

	public void setRiskLevel(final int riskLevelToSet) {
		riskLevel = riskLevelToSet;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(final String summaryToSet) {
		summary = summaryToSet;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(final String descriptionToSet) {
		description = descriptionToSet;
	}*/

	
	public String getPriorityString() {
		return priorityString;
	}

	public void setPriorityString(String priorityString) {
		this.priorityString = priorityString;
	}

	public String getRequirementTypeString() {
		return requirementTypeString;
	}

	public void setRequirementTypeString(String requirementTypeString) {
		this.requirementTypeString = requirementTypeString;
	}

	public String getRiskLevelString() {
		return riskLevelString;
	}

	public void setRiskLevelString(String riskLevelString) {
		this.riskLevelString = riskLevelString;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getRiskLevel() {
		return riskLevel;
	}

	public void setRiskLevel(int riskLevel) {
		this.riskLevel = riskLevel;
	}

	public String getRiskDescription() {
		return riskDescription;
	}

	public void setRiskDescription(String riskDescription) {
		this.riskDescription = riskDescription;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public boolean equals( final Object obj ){
		if( this == obj )
		{
			return true;
		}
		if( obj == null )
		{
			return false;
		}
		if( getClass() != obj.getClass() )
		{
			return false;
		}
		final Requirement other = (Requirement) obj;
		if (getName() == null) {
			if (other.getName() != null)
			{
				return false;
			}
		}
		else if (!getName().equals(other.getName()))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Requirement [priorityString=" + priorityString
				+ ", requirementTypeString=" + requirementTypeString
				+ ", riskLevelString=" + riskLevelString + ", name=" + name
				+ ", summary=" + summary + ", description=" + description
				+ ", priority=" + priority + ", type=" + type + ", riskLevel="
				+ riskLevel + ", riskDescription=" + riskDescription
				+ ", comments=" + comments + ", revisions=" + revisions + "]";
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

enum RequirementPriority {
	High, Medium, Low
}

enum RiskLevelType {
	High, Medium, Low
}

enum RequirementType {
	Business, Functional, User
}