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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import com.archsystemsinc.ipms.common.INameableEntity;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

@Entity
@XmlRootElement
@Table(name = "principal")
public class Principal implements INameableEntity{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "principal_id")
	private Long id;

	@Column(unique = true, nullable = false)
	@NotEmpty
	private String name;

	@Column(nullable = false)
	@Size(min = 4, max = 10)
	private String password;

	@Column(nullable = false)
	private String username;

	@Column(nullable = true)
	@Email
	private String email;
	
	@Column(name = "jira_username", nullable = true)
	private String jiraUsername;

	@Transient
	@Size(min = 4, max = 10)
	private String confirmPassword;

	@Transient
	private Set<Privilege> privileges;
	
	@Transient
	private List<Long> rolesList;
	
	@Transient
	private List<Long> privilegesList;
	
		//@formatter:off
		@ManyToMany( /*cascade = { CascadeType.REMOVE },*/fetch = FetchType.LAZY )
		@JoinTable(joinColumns = { @JoinColumn(name = "principal_id", referencedColumnName = "principal_id") }, inverseJoinColumns = { @JoinColumn(name = "role_id", referencedColumnName = "role_id") })
		@XStreamImplicit
		private Set< Role > roles;
		
	@OrderBy("dueDate DESC")
	@OneToMany( mappedBy = "assignedTo")
	private Set<ActionItem> actionItems = new HashSet<ActionItem>();

	@OneToMany( mappedBy = "meetingAgendaOwner")
	private Set<MeetingAgendaItem> meetingAgendaItems = new HashSet<MeetingAgendaItem>();

	@OneToMany( mappedBy = "manager")
	private Set<Project> projects = new HashSet<Project>();

	@OneToMany( mappedBy = "manager")
	private Set<Program> programs = new HashSet<Program>();

	@OrderBy("dueDate DESC")
	@OneToMany( mappedBy = "assignedTo")
	private Set<Task> tasks = new HashSet<Task>();

	@OneToMany( mappedBy = "principal")
	private Set<Comment> comments = new HashSet<Comment>();

	@OneToMany( mappedBy = "principal")
	private Set<RevisionHistory> revisions = new HashSet<RevisionHistory>();

	@OneToMany( mappedBy = "responsiblePerson")
	private Set<Risk> risks = new HashSet<Risk>();

	public Principal(){
		super();
	}

	public Principal( final String nameToSet, final String passwordToSet, final Set< Role > rolesToSet ){
		super();

		name = nameToSet;
		password = passwordToSet;
		roles = rolesToSet;
	}

	// API


	public Set<ActionItem> getActionItems() {
		return actionItems;
	}

	public Set<Project> getProjects() {
		return projects;
	}

	public void setProjects(final Set<Project> projects1) {
		projects = projects1;
	}

	public Set<MeetingAgendaItem> getMeetingAgendaItems() {
		return meetingAgendaItems;
	}

	public void setMeetingAgendaItems(
			final Set<MeetingAgendaItem> meetingAgendaItems1) {
		meetingAgendaItems = meetingAgendaItems1;
	}

	public Set<Program> getPrograms() {
		return programs;
	}

	public void setPrograms(final Set<Program> programs1) {
		programs = programs1;
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

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(final Long idToSet) {
		id = idToSet;
	}

	public Set<Risk> getRisks() {
		return risks;
	}

	public void setRisks(final Set<Risk> risksToSet) {
		risks = risksToSet;
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

	@Override
	public String getName(){
		return name;
	}

	public void setName( final String nameToSet ){
		name = nameToSet;
	}

	public String getPassword(){
		return password;
	}

	public void setPassword( final String passwordToSet ){
		password = passwordToSet;
	}

	public Set< Role > getRoles(){
		return roles;
	}

	public void setRoles( final Set< Role > rolesToSet ){
		roles = rolesToSet;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(final String username1) {
		username = username1;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(final String email1) {
		email = email1;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(final String confirmPassword1) {
		confirmPassword = confirmPassword1;
	}

	public Set<Privilege> getPrivileges() {
		return privileges;
	}

	public void setPrivileges(final Set<Privilege> privileges1) {
		privileges = privileges1;
	}

	public List<Long> getRolesList() {
		return rolesList;
	}

	public void setRolesList(List<Long> rolesList) {
		this.rolesList = rolesList;
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
		final Principal other = (Principal) obj;
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
		return new ToStringBuilder( this ).append( "id", id ).append( "name", name ).toString();
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

	public List<Long> getPrivilegesList() {
		return privilegesList;
	}

	public void setPrivilegesList(List<Long> privilegesList) {
		this.privilegesList = privilegesList;
	}

	public String getJiraUsername() {
		return jiraUsername;
	}

	public void setJiraUsername(String jiraUsername) {
		this.jiraUsername = jiraUsername;
	}

	
}
