package com.archsystemsinc.ipms.sec.model;

import java.util.Date;
import java.util.HashSet;
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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;
import org.hibernate.validator.constraints.NotEmpty;
import com.archsystemsinc.ipms.common.INameableEntity;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;


@Entity
@XmlRootElement
@XStreamAlias("Task")
@Table(name = "task")
public class Task implements INameableEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "task_id")
	private Long id;

    @Column(name = "baseline_cost", nullable = true)
	private Float baselineCost;
    
    @Column(name = "percent_complete", nullable = true)
	private Float percentComplete;
	
    @Column(name = "standard_rate", nullable = true)
    private Float standardRate;
    
    @Column(name = "total_hours_spent", nullable = true)
	private Float totalHoursSpent;
    
    @Column(name = "total_hours_planned", nullable = true)
    private Float totalHoursPlanned;
    
    @Column(name = "msproject_task_id", nullable = true)
    private Integer msProjectTaskId;
    
    @Column(name = "msproject_parent_task_id", nullable = true)
    private Integer msProjectParentTaskId;
    
    @Column(name = "msproject_task_mode", nullable = true)
    private String msProjectTaskMode;
    
    @Column(name = "baselined_effort_hours", nullable = true)
	private Float baselinedEffortHours; 
	
	@Column(nullable = true)
	@NotEmpty
	private String description;

	//@formatter:off
	@ManyToMany( /*cascade = { CascadeType.REMOVE },*/fetch = FetchType.LAZY )
	@JoinTable(joinColumns = { @JoinColumn(name = "task_id", referencedColumnName = "task_id") }, inverseJoinColumns = { @JoinColumn(name = "principal_id", referencedColumnName = "principal_id") })
	@XStreamImplicit
	private Set< Principal > principals;
	
	//@OneToMany( mappedBy = "task")
			
	@Column(nullable = true)
	private String status = TaskStatus.Open.toString();

	@Column(name = "date_created", nullable = true)
	private Date dateCreated;

	@Column(name = "due_date", nullable = true)
	private Date dueDate;
    
	@Column(nullable = true)
	@NotEmpty
	private String name;

	@ManyToOne
	@JoinColumn(name = "assigned_to")
	private Principal assignedTo;

	@ManyToOne
	@JoinColumn(name = "program_id")
	private Program program;

	@ManyToOne
	@JoinColumn(name = "project_id")
	private Project project;

	@ManyToOne
	@JoinColumn(name = "created_by")
	private Principal createdBy;
	
	@OneToMany( mappedBy = "task")
	private Set<RevisionHistory> revisions = new HashSet<RevisionHistory>();

	@Transient
	private Long assignedToId;

	@Transient
	//@NotNull
	private Long programId;

	@Transient
	private Long projectId;

	@Transient
	private Long createdById;
	
	public Float getBaselineCost() {
		return baselineCost;
	}

	public void setBaselineCost(Float baselineCost) {
		this.baselineCost = baselineCost;
	}

	public Float getPercentComplete() {
		return percentComplete;
	}

	public void setPercentComplete(Float percentComplete) {
		this.percentComplete = percentComplete;
	}

	public Float getStandardRate() {
		return standardRate;
	}

	public void setStandardRate(Float standardRate) {
		this.standardRate = standardRate;
	}

	public Float getTotalHoursSpent() {
		return totalHoursSpent;
	}

	public void setTotalHoursSpent(Float totalHoursSpent) {
		this.totalHoursSpent = totalHoursSpent;
	}

	public Float getTotalHoursPlanned() {
		return totalHoursPlanned;
	}

	public void setTotalHoursPlanned(Float totalHoursPlanned) {
		this.totalHoursPlanned = totalHoursPlanned;
	}

	public Float getBaselinedEffortHours() {
		return baselinedEffortHours;
	}

	public void setBaselinedEffortHours(Float baselinedEffortHours) {
		this.baselinedEffortHours = baselinedEffortHours;
	}

	public String getStatus() {
		return status;
	}	

	public void setStatus(final String status1) {
		status = status1;
	}

	public Principal getAssignedTo() {
		return assignedTo;
	}

	public void setAssignedTo(final Principal assignedToSet) {
		assignedTo = assignedToSet;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(final Date dateCreated1) {
		dateCreated = dateCreated1;
	}

	public Principal getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(final Principal createdBy1) {
		createdBy = createdBy1;
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

	public void setDueDate(final Date dueDateToSet) {
		dueDate = dueDateToSet;
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

	public Program getProgram() {
		return program;
	}

	public void setProgram(final Program program1) {
		program = program1;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(final Project project1) {
		project = project1;
	}

	public Long getCreatedById() {
		return createdById;
	}

	public void setCreatedById(final Long createdById1) {
		createdById = createdById1;
	}

	public Long getAssignedToId() {
		return assignedToId;
	}

	public void setAssignedToId(final Long assignedToIdToSet) {
		assignedToId = assignedToIdToSet;
	}

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(final Long id1) {
		id = id1;
	}

	@Override
	public String getName() {
		return name;
	}

	public void setName(final String name1) {
		name = name1;
	}

	@Override
	public Principal getOwner() {
		// TODO Auto-generated method stub
		return assignedTo;
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

	public Integer getMsProjectTaskId() {
		return msProjectTaskId;
	}

	public void setMsProjectTaskId(Integer msProjectTaskId) {
		this.msProjectTaskId = msProjectTaskId;
	}

	public Integer getMsProjectParentTaskId() {
		return msProjectParentTaskId;
	}

	public void setMsProjectParentTaskId(Integer msProjectParentTaskId) {
		this.msProjectParentTaskId = msProjectParentTaskId;
	}

	public String getMsProjectTaskMode() {
		return msProjectTaskMode;
	}

	public void setMsProjectTaskMode(String msProjectTaskMode) {
		this.msProjectTaskMode = msProjectTaskMode;
	}
}


