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
import com.archsystemsinc.ipms.sec.model.constraint.CheckDateRange;
import com.archsystemsinc.ipms.sec.model.constraint.ObjectTypeEnum;
import com.thoughtworks.xstream.annotations.XStreamAlias;

@Entity
@XmlRootElement
@XStreamAlias("project")
@Table(name = "project")
@CheckDateRange(ObjectTypeEnum.Project)
public class Project implements INameableEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "project_id")
	private Long id;

	@Column
	private String status;

	@Column(unique = true, nullable = false)
	@NotEmpty String name;
	
	@Column(name = "manager_name",nullable = true)
	String managerName;

	@Column(nullable = true)
	@NotEmpty
	private String description;

	@Column(name = "start_date", nullable = true)
	private Date startDate;

	@Column(name = "active", columnDefinition = "BIT", length = 1)
	private boolean active = true;

	@Column(name = "end_date", nullable = true)
	private Date endDate;

	@ManyToOne
	@JoinColumn(name = "manager_id")
	private Principal manager;

	@OneToMany( mappedBy = "project")
	private Set<Task> tasks = new HashSet<Task>();
	
	@OneToMany( mappedBy = "project")
	private Set<ProjectEvm> projectEvms = new HashSet<ProjectEvm>();
	
	@OneToMany( mappedBy = "project")
	private Set<ProjectMileStone> projectMileStones = new HashSet<ProjectMileStone>();

	@OneToMany( mappedBy = "project")
	private Set<ProjectStatusReport> projectStatusReports = new HashSet<ProjectStatusReport>();
	
	@OneToMany( mappedBy = "project")
	private Set<Risk> risks = new HashSet<Risk>();

	@OneToMany( mappedBy = "project")
	private Set<Meeting> meetings = new HashSet<Meeting>();

	@OneToMany( mappedBy = "project")
	private Set<Issue> issues = new HashSet<Issue>();

	@ManyToOne
	@JoinColumn(name = "program_id")
	private Program program;

	@Transient
	private Long programId;

	@Transient
	private Long managerId;

	@Transient
	private Set<LessonsLearned> lessonsLearned;

	@Transient
	private Set<ActionItem> actionItems;

	public Project() {
		super();
	}

	public Project(final String nameToSet) {
		super();
		name = nameToSet;
	}

	// API	

	public boolean isActive() {
		return active;
	}

	public String getManagerName() {
		return managerName;
	}

	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}

	public Set<ProjectMileStone> getProjectMileStones() {
		return projectMileStones;
	}

	public void setProjectMileStones(Set<ProjectMileStone> projectMileStones) {
		this.projectMileStones = projectMileStones;
	}

	public Set<ProjectStatusReport> getProjectStatusReports() {
		return projectStatusReports;
	}

	public void setProjectStatusReports(Set<ProjectStatusReport> projectStatusReports) {
		this.projectStatusReports = projectStatusReports;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Set<ProjectEvm> getProjectEvms() {
		return projectEvms;
	}

	public void setProjectEvms(Set<ProjectEvm> projectEvms) {
		this.projectEvms = projectEvms;
	}

	public void setActive(final boolean active1) {
		active = active1;
	}

	@Override
	public Long getId(){
		return id;
	}

	@Override
	public void setId(final Long idToSet) {
		id = idToSet;
	}

	public Set<Task> getTasks() {
		return tasks;
	}

	public void setTasks(final Set<Task> tasks1) {
		tasks = tasks1;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(final Date endDate1) {
		endDate = endDate1;
	}

	public void setActionItems(final Set<ActionItem> actionItems1) {
		actionItems = actionItems1;
	}

	public Set<ActionItem> getActionItems() {
		final Iterator<Issue> itr = issues.iterator();
		if (actionItems == null)
			actionItems = new HashSet<ActionItem>();
		while (itr.hasNext()) {
			final Issue issue = itr.next();
			actionItems.addAll(issue.getActionItems());
		}

		final Iterator<Meeting> itr1 = meetings.iterator();
		while (itr1.hasNext()) {
			final Meeting meeting = itr1.next();
			actionItems.addAll(meeting.getActionItems());
		}
		return actionItems;
	}

	public Set<LessonsLearned> getLessonsLearned() {
		final Iterator<Issue> itr = issues.iterator();
		if (lessonsLearned == null)
			lessonsLearned = new HashSet<LessonsLearned>();
		while (itr.hasNext()) {
			final Issue issue = itr.next();
			lessonsLearned.addAll(issue.getLessonsLearneds());
		}

		final Iterator<Meeting> itr1 = meetings.iterator();
		while (itr1.hasNext()) {
			final Meeting meeting = itr1.next();
			lessonsLearned.addAll(meeting.getLessonsLearneds());
		}

		return lessonsLearned;
	}

	public void setLessonsLearned(final Set<LessonsLearned> lessonsLearnedToSet) {
		lessonsLearned = lessonsLearnedToSet;
	}

	public Set<Meeting> getMeetings() {
		return meetings;
	}

	public void setMeetings(final Set<Meeting> meetingsToSet) {
		meetings = meetingsToSet;
	}

	public Set<Risk> getRisks() {
		return risks;
	}

	public void setRisks(final Set<Risk> risksToSet) {
		risks = risksToSet;
	}

	public Principal getManager() {
		return manager;
	}

	public void setManager(final Principal manager1) {
		manager = manager1;
	}


	@Override
	public String getName(){
		return name;
	}

	public void setName( final String nameToSet ){
		name = nameToSet;
	}


	//

	public String getDescription() {
		return description;
	}

	public void setDescription(final String description1) {
		description = description1;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(final Date startDate1) {
		startDate = startDate1;
	}

	public Program getProgram() {
		return program;
	}

	public void setProgram(final Program program1) {
		program = program1;
	}

	public Long getManagerId() {
		return managerId;
	}

	public void setManagerId(final Long managerIdToSet) {
		managerId = managerIdToSet;
	}

	public Long getProgramId() {
		return programId;
	}

	public void setProgramId(final Long programIdToSet) {
		programId = programIdToSet;
	}

	public Set<Issue> getIssues() {
		return issues;
	}

	public void setIssues(final Set<Issue> issues1) {
		issues = issues1;
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
		if( id == null ){
			if( other.id != null )
				return false;
		}
		else if( !id.equals( other.id ) )
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
		return manager;
	}

	@Override
	public Set<Principal> getStakeHolders() {
		// TODO Auto-generated method stub
		return null;
	}

}
