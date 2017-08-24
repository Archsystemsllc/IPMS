package com.archsystemsinc.ipms.sec.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
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
@XStreamAlias("program")
@Table(name = "program")
@CheckDateRange(ObjectTypeEnum.Program)
public class Program implements INameableEntity,Comparable<Program> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7233891501800577062L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "program_id")
	private Long id;

	@Column(name = "active", columnDefinition = "BIT", length = 1)
	private boolean active = true;

	@Column(unique = true, nullable = false)
	@NotEmpty
	private String name;

	@Column(nullable = true)
	@NotEmpty
	private String description;

	@Column(name = "start_date", nullable = true)
	@NotNull
	private Date startDate;

	@Column(name = "end_date", nullable = true)
	@NotNull
	private Date endDate;
	
	@Transient
	private String activity;

	@ManyToOne
	@JoinColumn(name = "manager_id")
	private Principal manager;
	
	@ManyToOne
	@JoinColumn(name = "organizationgroup_id")
	private OrganizationGroup organizationGroup;

	public String getActivity() {
		return activity;
	}

	public void setActivity(String activity) {
		this.activity = activity;
	}

	@OneToMany( mappedBy = "program")
	@OrderBy("date desc")
	private Set<Meeting> meetings = new HashSet<Meeting>();

	@OneToMany( mappedBy = "program")
	private Set<Project> projects = new HashSet<Project>();

	@OneToMany( mappedBy = "program")
	private Set<Task> tasks = new HashSet<Task>();

	@Transient
	private Set<Risk> risks = new HashSet<Risk>();

	@Transient
	private Set<ActionItem> actionItems = new HashSet<ActionItem>();

	@Transient
	private Set<Issue> issues = new HashSet<Issue>();

	@Transient
	private Long managerId;

	@Transient
	private Set<LessonsLearned> lessonsLearned;

	public Program() {
		super();
	}

	public Program(final String nameToSet) {
		super();
		name = nameToSet;
	}

	// API

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(final Date endDate1) {
		endDate = endDate1;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(final boolean active1) {
		active = active1;
	}
	public Set<Task> getTasks() {
		return tasks;
	}

	public void setTasks(final Set<Task> tasks1) {
		tasks = tasks1;
	}



	public void setActionItems(final Set<ActionItem> actionItems1) {
		actionItems = actionItems1;
	}

	public void setLessonsLearned(final Set<LessonsLearned> lessonsLearned1) {
		lessonsLearned = lessonsLearned1;
	}

	@Override
	public Long getId(){
		return id;
	}

	@Override
	public void setId( final Long idToSet ){
		id = idToSet;
	}

	public Set<ActionItem> getActionItems() {
		final Iterator<Project> itr = projects.iterator();
		actionItems = new HashSet();
		while (itr.hasNext()) {
			final Project project = itr.next();
			actionItems.addAll(project.getActionItems());
		}
		return actionItems;
	}

	public Set<LessonsLearned> getLessonsLearned() {
		try {
			final Iterator<Project> itr = projects.iterator();
			while (itr.hasNext()) {
				final Project project = itr.next();
				lessonsLearned.addAll(project.getLessonsLearned());
			}
		}catch(final Exception e) {
		}
		return lessonsLearned;
	}

	public Set<Meeting> getMeetings() {
		return meetings;
	}

	public void setMeetings(final Set<Meeting> meetingsToSet) {
		meetings = meetingsToSet;
	}

	public Set<Issue> getIssues() {
		final Iterator<Project> itr = projects.iterator();
		issues = new HashSet();
		while (itr.hasNext()) {
			final Project project = itr.next();
			issues.addAll(project.getIssues());
		}
		return issues;
	}


	public void setIssues(final Set<Issue> issuesToSet) {
		issues = issuesToSet;
	}

	public Set<Risk> getRisks() {
		final Iterator<Project> itr = projects.iterator();
		risks = new HashSet();
		while (itr.hasNext()) {
			final Project project = itr.next();
			risks.addAll(project.getRisks());
		}
		return risks;
	}

	public void setRisks(final Set<Risk> risksToSet) {
		risks = risksToSet;
	}

	@Override
	public String getName(){
		return name;
	}

	public void setName( final String nameToSet ){
		name = nameToSet;
	}

	public Set<Project> getProjects() {
		return projects;
	}

	public void setProjects(final Set<Project> projects1) {
		projects = projects1;
	}

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

	public Principal getManager() {
		return manager;
	}

	public void setManager(final Principal manager1) {
		manager = manager1;
	}

	public Long getManagerId() {
		return managerId;
	}

	public void setManagerId(final Long managerIdToSet) {
		managerId = managerIdToSet;
	}

	//
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
		{
			return true;
		}
		if( obj == null )
		{
			return false;
		}
		if( getClass() != obj.getClass() )
			return false;
		final Program other = (Program) obj;
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
		return manager;
	}

	@Override
	public Set<Principal> getStakeHolders() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int compareTo(Program o) {
		
		// ProvidedItems items1, items2  
		boolean b1 = o.isActive();  
		boolean b2 = this.isActive();  
		if (b1 == b2)  
		{  
		    return 1;  
		}  
		// either b1 is true or b2  
		// if true goes after false switch the -1 and 1  
		return (b1 ? -1 : 1);  
	
	}

	public OrganizationGroup getOrganizationGroup() {
		return organizationGroup;
	}

	public void setOrganizationGroup(OrganizationGroup organizationGroup) {
		this.organizationGroup = organizationGroup;
	}
}
