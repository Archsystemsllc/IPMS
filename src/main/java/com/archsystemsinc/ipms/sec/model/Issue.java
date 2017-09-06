package com.archsystemsinc.ipms.sec.model;

import java.util.Date;
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
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.validator.constraints.NotEmpty;

import com.archsystemsinc.ipms.common.INameableEntity;
import com.archsystemsinc.ipms.sec.model.constraint.CheckDateRange;
import com.archsystemsinc.ipms.sec.model.constraint.ObjectTypeEnum;
import com.thoughtworks.xstream.annotations.XStreamAlias;

@Entity
@XmlRootElement
@XStreamAlias("issue")
@Table(name = "issue")
@CheckDateRange(ObjectTypeEnum.Issue)
public class Issue implements
INameableEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "issue_id")
	private Long id;

	@Column(unique = true, nullable = false)
	@NotEmpty
	private String summary;

	@Column(nullable = true)
	@NotEmpty
	private String status = IssueStatus.Pending.toString();

	@Column(nullable = true)
	private String description;

	@Column(nullable = true)
	private String name;

	@Column(nullable = true)
	@NotEmpty
	private String priority;

	@Column(name = "date_reported", nullable = true)
	@NotNull
	private Date dateReported;

	@Column(name = "due_date", nullable = true)
	@NotNull
	private Date dueDate;

	@ManyToOne
	@JoinColumn(name = "project_id")
	private Project project;

	@ManyToOne
	@JoinColumn(name = "program_id")
	private Program program;

	@ManyToOne
	@JoinColumn(name = "assigned_id")
	private Principal assigned;

	@ManyToOne
	@JoinColumn(name = "assignee_id")
	private Principal assignee;

	@OneToMany( mappedBy = "issue")
	private Set<LessonsLearned> lessonsLearneds = new HashSet<LessonsLearned>();

	@OneToMany( mappedBy = "issue")
	private Set<ActionItem> actionItems = new HashSet<ActionItem>();
	
	@OneToMany( mappedBy = "issue")
	private Set<RevisionHistory> revisions = new HashSet<RevisionHistory>();

	@Transient
	private String projectName;

	@Transient
	private Long assigneeId;

	@Transient
	private Long assignedId;

	@Transient
	private Long projectId;
	
	@Transient 
	private String jiraId;
	
	@Transient 
	private String source ="IPMS";

	public Issue() {
		super();
	}

	public Issue(final String summaryToSet) {
		super();
		summary = summaryToSet;
	}

	// API

	@Override
	public Long getId(){
		return id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(final String status1) {
		status = status1;
	}

	public Program getProgram() {
		return program;
	}

	public void setProgram(final Program program1) {
		program = program1;
	}

	@Override
	public void setId( final Long idToSet ){
		id = idToSet;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(final Project project1) {
		project = project1;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(final String projectName1) {
		projectName = projectName1;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(final String summary1) {
		summary = summary1;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(final String description1) {
		description = description1;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(final Date date1) {
		dueDate = date1;
	}

	public Date getDateReported() {
		return dateReported;
	}

	public void setDateReported(final Date date1) {
		dateReported = date1;
	}

	public Principal getAssigned() {
		return assigned;
	}

	public void setAssigned(final Principal assigned1) {
		assigned = assigned1;
	}

	public Principal getAssignee() {
		return assignee;
	}

	public void setAssignee(final Principal assignee1) {
		assignee = assignee1;
	}

	public Set<LessonsLearned> getLessonsLearneds() {
		return lessonsLearneds;
	}

	public void setLessonsLearneds(final Set<LessonsLearned> lessonsLearneds1) {
		lessonsLearneds = lessonsLearneds1;
	}

	public Set<ActionItem> getActionItems() {
		return actionItems;
	}

	public void setActionItems(final Set<ActionItem> actionItems1) {
		actionItems = actionItems1;
	}

	public Long getAssigneeId() {
		return assigneeId;
	}

	public void setAssigneeId(final Long assigneeIdToSet) {
		assigneeId = assigneeIdToSet;
	}

	public Long getAssignedId() {
		return assignedId;
	}

	public void setAssignedId(final Long assignedIdToSet) {
		assignedId = assignedIdToSet;
	}

	public void setName(final String name1) {
		name = name1;
	}

	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(final Long projectId1) {
		projectId = projectId1;
	}

	//
	@Override
	public int hashCode(){
		final int prime = 31;
		int result = 1;
		result = prime * result + ((summary == null) ? 0 : summary.hashCode());
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
		final Issue other = (Issue) obj;
		if (summary == null) {
			if (other.summary != null)
				return false;
		}
		else if (!summary.equals(other.summary))
		{
			return false;
		}
		return true;
	}


	@Override
	public String toString() {
		return "Issue [summary=" + summary + ", description=" + description + ", name=" + name + ", dateReported="
				+ dateReported + ", dueDate=" + dueDate + "]";
	}

	@Override
	public String getName() {
		return summary;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(final String priority1) {
		priority = priority1;
	}

	@Override
	public Principal getOwner() {
		// TODO Auto-generated method stub
		return assigned;
	}

	@Override
	public Set<Principal> getStakeHolders() {
		Set<Principal> list = new HashSet<Principal>();
		list.add(assignee);
		return null;
	}

	public Set<RevisionHistory> getRevisions() {
		return revisions;
	}

	public void setRevisions(Set<RevisionHistory> revisions) {
		this.revisions = revisions;
	}

	public String getJiraId() {
		return jiraId;
	}

	public void setJiraId(String jiraId) {
		this.jiraId = jiraId;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}
	
	

}
