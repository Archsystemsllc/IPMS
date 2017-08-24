package com.archsystemsinc.ipms.sec.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.validator.constraints.NotEmpty;

import com.archsystemsinc.ipms.common.IEntity;
import com.archsystemsinc.ipms.common.INameableEntity;
import com.thoughtworks.xstream.annotations.XStreamAlias;


@Entity
@XmlRootElement
@XStreamAlias("UserActivity")
@Table(name = "useractivity")
public class UserActivity implements INameableEntity, Comparable<UserActivity> {

	private static final long serialVersionUID = 1L;

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "useractivity_id")
	private Long id;

	@Override
	public String toString() {
		return "UserActivity [id=" + id + "]";
	}

	@Column(name="activity_type", nullable = true)
	@NotEmpty
	private String activityType;

	@Column(name="entity_id", nullable = true)
	private Long entityId;
	
	@Column(name="entity_type", nullable = true)
	private String entityType;

	@Column(name = "date_created", nullable = true)
	@OrderBy("dateCreated asc")
	private Date dateCreated;

	@Column(name="entity_name", nullable = true)
	private String entityName;

	@Column(name = "user")
	private String user;
	
	@Override
	public int compareTo(final UserActivity actionItem) {

		int result = 0;
		try {
			final Date thisActionItemDate = getDateCreated();
			final Date actionItemDate = actionItem.getDateCreated();
			result = actionItemDate.compareTo(thisActionItemDate);
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return result;

	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(final Date dateCreated1) {
		dateCreated = dateCreated1;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getEntityId() {
		return entityId;
	}

	public void setEntityId(Long entityId) {
		this.entityId = entityId;
	}

	public String getActivityType() {
		return activityType;
	}

	public void setActivityType(String activityType) {
		this.activityType = activityType;
	}

	public String getEntityType() {
		return entityType;
	}

	public void setEntityType(String entityType) {
		this.entityType = entityType;
	}

	public String getEntityName() {
		return entityName;
	}

	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return entityName;
	}

	@Override
	public Principal getOwner() {
		return null;
	}

	@Override
	public Set<Principal> getStakeHolders() {
		// TODO Auto-generated method stub
		return null;
	}
}
