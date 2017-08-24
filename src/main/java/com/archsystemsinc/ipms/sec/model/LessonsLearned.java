package com.archsystemsinc.ipms.sec.model;

import java.util.Date;
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
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.validator.constraints.NotEmpty;

import com.archsystemsinc.ipms.common.INameableEntity;
import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * @author ARCHSYSTEMS
 *
 */
@Entity
@XmlRootElement
@XStreamAlias("LessonsLearned")
@Table(name = "lessons_learned")
public class LessonsLearned implements INameableEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "lessons_learned_id")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "issue_id")
	private Issue issue;

	@ManyToOne
	@JoinColumn(name = "meeting_id")
	private Meeting meeting;

	@Column(nullable = true)
	private String recommendation;

	@Column(nullable = true)
	private int authorization;

	@Column(name = "success_factors", nullable = true)
	private String successFactors;

	@Column(name = "areas_of_improvement", nullable = true)
	private String areasOfImprovement;

	@Column(name = "date", nullable = true)
	private Date date;

	@Column(nullable = true)
	@NotEmpty
	private String impact;

	@Column(nullable = true)
	@NotEmpty
	private String summary;

	@Column(nullable = true)
	private String name;
	
	@OneToMany( mappedBy = "lessonsLearned")
	private Set<RevisionHistory> revisions = new HashSet<RevisionHistory>();

	@Transient
	private Long issueId;
	
	@Transient
	private Long meetingId;

	public Long getMeetingId() {
		return meetingId;
	}

	public void setMeetingId(Long meetingId) {
		this.meetingId = meetingId;
	}



	@Transient
	private String issueSummary;

	public Meeting getMeeting() {
		return meeting;
	}

	public void setMeeting(final Meeting meeting1) {
		meeting = meeting1;
	}

	public Issue getIssue() {
		return issue;
	}

	public void setIssue(final Issue issue1) {
		issue = issue1;
	}

	public String getRecommendation() {
		return recommendation;
	}

	public void setRecommendation(final String recommendation1) {
		recommendation = recommendation1;
	}

	public String getImpact() {
		return impact;
	}

	public void setImpact(final String impact1) {
		impact = impact1;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(final String summary1) {
		summary = summary1;
	}

	public String getIssueSummary() {
		return issueSummary;
	}

	public void setIssueSummary(final String issueSummary1) {
		issueSummary = issueSummary1;
	}

	public int getAuthorization() {
		return authorization;
	}

	public void setAuthorization(final int authorization1) {
		authorization = authorization1;
	}

	public String getSuccessFactors() {
		return successFactors;
	}

	public void setSuccessFactors(final String successFactors1) {
		successFactors = successFactors1;
	}

	public String getAreasOfImprovement() {
		return areasOfImprovement;
	}

	public void setAreasOfImprovement(final String areasOfImprovement1) {
		areasOfImprovement = areasOfImprovement1;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(final Date date1) {
		date = date1;
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
		return summary;
	}

	public Long getIssueId() {
		return issueId;
	}

	public void setIssueId(final Long issueId1) {
		issueId = issueId1;
	}
	
	public void setName(final String name1) {
		name = name1;
	}

	@Override
	public Principal getOwner() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Principal> getStakeHolders() {
		Set<Principal> list = new HashSet<Principal>();
		list.addAll(issue.getStakeHolders());
		list.addAll(meeting.getStakeHolders());
		return null;
	}

	public Set<RevisionHistory> getRevisions() {
		return revisions;
	}

	public void setRevisions(Set<RevisionHistory> revisions) {
		this.revisions = revisions;
	}

}
