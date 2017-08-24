package com.archsystemsinc.ipms.sec.model;

import java.sql.Timestamp;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;
import org.apache.commons.lang.builder.ToStringBuilder;
import com.archsystemsinc.ipms.common.INameableEntity;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

@Entity
@XmlRootElement
@XStreamAlias("comment")
@Table(name = "comment")
public class Comment implements INameableEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "comment_id")
	@XStreamAsAttribute
	private Long id;

	@ManyToOne
	@JoinColumn(name = "requirement_id")
	private Requirement requirement;

	@ManyToOne
	@JoinColumn(name = "principal_id")
	private Principal principal;

	@Column(unique = true, nullable = false)
	private String text;

	@Column(unique = true, nullable = true)
	private String name;

	@Transient
	private Long requirementId;

	@Transient
	private String timeElapsed;

	@Column(nullable = false)
	private Timestamp madeAt;

	public Comment() {
		super();
	}

	public Comment(final String textToSet) {
		super();
		text = textToSet;
	}
	// API

	public String getTimeElapsed() {
		// Get elapsed time in milliseconds
		final long elapsedTimeMillis = System.currentTimeMillis()
				- madeAt.getTime();

		// Get elapsed time in seconds
		final float elapsedTimeSec = elapsedTimeMillis / 1000F;

		// Get elapsed time in minutes
		final float elapsedTimeMin = elapsedTimeMillis / (60 * 1000F);

		// Get elapsed time in hours
		final float elapsedTimeHour = elapsedTimeMillis / (60 * 60 * 1000F);

		// Get elapsed time in days
		final float elapsedTimeDay = elapsedTimeMillis / (24 * 60 * 60 * 1000F);

		String ret = "";

		if (elapsedTimeDay > 1)
			ret += Math.round(elapsedTimeDay) + " day(s) ago";

		else if (elapsedTimeHour > 1)
			ret += Math.round(elapsedTimeHour) + " hour(s) ago";

		else if (elapsedTimeMin > 1)
			ret += Math.round(elapsedTimeMin) + " min(s) ago";

		else if (elapsedTimeSec > 0)
			ret += Math.round(elapsedTimeHour) + " sec(s) ago";

		return ret;
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

	public Long getRequirementId() {
		return requirementId;
	}

	public void setRequirementId(final Long requirementIdToSet) {
		requirementId = requirementIdToSet;
	}

	@Override
	public void setId( final Long idToSet ){
		id = idToSet;
	}

	public Timestamp getMadeAt() {
		return madeAt;
	}

	public void setMadeAt(final Timestamp madeAtToSet) {
		madeAt = madeAtToSet;
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
		final Comment other = (Comment) obj;
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

}
