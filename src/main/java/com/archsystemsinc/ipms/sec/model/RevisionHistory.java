package com.archsystemsinc.ipms.sec.model;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.archsystemsinc.ipms.common.INameableEntity;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

@Entity
@XmlRootElement
@XStreamAlias("revisionHistory")
@Table(name = "revision_history")
public class RevisionHistory implements INameableEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "revision_history_id")
	@XStreamAsAttribute
	private Long id;

	@ManyToOne
	@JoinColumn(name = "requirement_id", insertable = false, updatable = false)
	private Requirement requirement;
	
	@ManyToOne
	@JoinColumn(name = "actionitem_id", insertable = false, updatable = false)
	private ActionItem actionItem;
	
	@ManyToOne
	@JoinColumn(name = "issue_id", insertable = false, updatable = false)
	private Issue issue;
	
	@ManyToOne
	@JoinColumn(name = "lessons_learned_id", insertable = false, updatable = false)
	private LessonsLearned lessonsLearned;
	
	@ManyToOne
	@JoinColumn(name = "risk_id", insertable = false, updatable = false)
	private Risk risk;
	
	@ManyToOne
	@JoinColumn(name = "revision_history_type_id", insertable = false, updatable = false)
	private RevisionHistoryType revisionHistoryType;
	
	@ManyToOne
	@JoinColumn(name = "task_id", insertable = false, updatable = false)
	private Task task;

	@ManyToOne
	@JoinColumn(name = "principal_id", insertable = false, updatable = false)
	private Principal principal;

	@Column(unique = true, nullable = false)
	private String text;

	@Column(unique = true, nullable = true)
	private String name;

	@Column(name = "updated_at", nullable = true)
	private Timestamp updatedAt;
	
	@Column(name = "principal_id")
	private Long principalId;

	@Column(name = "requirement_id")
	private Long requirementId;
	
	@Column(name = "actionitem_id")
	private Long actionItemId;
	
	@Column(name = "issue_id")
	private Long issueId;
	
	@Column(name = "lessons_learned_id")
	private Long lessonsLearnedId;
	
	@Column(name = "risk_id")
	private Long riskId;
	
	@Column(name = "task_id")
	private Long taskId;
	
	@Column(name = "revision_history_type_id")
	private Long revisionHistoryTypeId;

	public RevisionHistory() {
		super();
	}

	public RevisionHistory(final String textToSet) {
		super();
		text = textToSet;
	}

	public Principal getPrincipal() {
		return principal;
	}

	public void setPrincipal(final Principal principalToSet) {
		principal = principalToSet;
	}

	public Requirement getRequirement() {
		return requirement;
	}

	public void setRequirement(final Requirement requirementToSet) {
		requirement = requirementToSet;
	}

	@Override
	public Long getId(){
		return id;
	}

	@Override
	public void setId( final Long idToSet ){
		id = idToSet;
	}	

	public Timestamp getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}

	public String getText() {
		return text;
	}

	public void setText(final String textToSet) {
		text = textToSet;
	}

	@Override
	public String getName() {
		return text;
	}
	
	


	//

	public Long getPrincipalId() {
		return principalId;
	}

	public void setPrincipalId(Long principalId) {
		this.principalId = principalId;
	}

	public Long getRequirementId() {
		return requirementId;
	}

	public void setRequirementId(Long requirementId) {
		this.requirementId = requirementId;
	}

	public void setName(final String nameToSet) {
		name = nameToSet;
	}

	@Override
	public int hashCode(){
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((getName() == null) ? 0 : getName().hashCode());
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
		final RevisionHistory other = (RevisionHistory) obj;
		if (getName() == null) {
			if (other.getName() != null)
				return false;
		}
		else if (!getName().equals(other.getName()))
			return false;
		return true;
	}

	@Override
	public String toString(){
		return new ToStringBuilder(this).append("id", id)
				.append("name", getName()).toString();
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

	public ActionItem getActionItem() {
		return actionItem;
	}

	public void setActionItem(ActionItem actionItem) {
		this.actionItem = actionItem;
	}

	public Issue getIssue() {
		return issue;
	}

	public void setIssue(Issue issue) {
		this.issue = issue;
	}

	public LessonsLearned getLessonsLearned() {
		return lessonsLearned;
	}

	public void setLessonsLearned(LessonsLearned lessonsLearned) {
		this.lessonsLearned = lessonsLearned;
	}

	public Risk getRisk() {
		return risk;
	}

	public void setRisk(Risk risk) {
		this.risk = risk;
	}

	public RevisionHistoryType getRevisionHistoryType() {
		return revisionHistoryType;
	}

	public void setRevisionHistoryType(RevisionHistoryType revisionHistoryType) {
		this.revisionHistoryType = revisionHistoryType;
	}

	public Task getTask() {
		return task;
	}

	public void setTask(Task task) {
		this.task = task;
	}

	public Long getActionItemId() {
		return actionItemId;
	}

	public void setActionItemId(Long actionItemId) {
		this.actionItemId = actionItemId;
	}

	public Long getIssueId() {
		return issueId;
	}

	public void setIssueId(Long issueId) {
		this.issueId = issueId;
	}

	public Long getLessonsLearnedId() {
		return lessonsLearnedId;
	}

	public void setLessonsLearnedId(Long lessonsLearnedId) {
		this.lessonsLearnedId = lessonsLearnedId;
	}

	public Long getRiskId() {
		return riskId;
	}

	public void setRiskId(Long riskId) {
		this.riskId = riskId;
	}

	public Long getTaskId() {
		return taskId;
	}

	public void setTaskId(Long taskId) {
		this.taskId = taskId;
	}

	public Long getRevisionHistoryTypeId() {
		return revisionHistoryTypeId;
	}

	public void setRevisionHistoryTypeId(Long revisionHistoryTypeId) {
		this.revisionHistoryTypeId = revisionHistoryTypeId;
	}
}
