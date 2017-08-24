package com.archsystemsinc.ipms.sec.model;

import java.util.Date;
import java.util.HashSet;
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
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import com.archsystemsinc.ipms.common.INameableEntity;
import com.thoughtworks.xstream.annotations.XStreamAlias;

@Entity
@XmlRootElement
@XStreamAlias("parkinglot")
@Table(name = "parking_lot")
public class ParkingLot implements INameableEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "parkinglot_id")
	private Long id;

	@Column(nullable = true)
	@NotNull
	private Date dateadded;
	
	@Column
	private String issue;
	
	@Column
	private String owner;
	
	@Column
	private String status;
	
	@Column
	private String biweeklyupdate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDateadded() {
		return dateadded;
	}

	public void setDateadded(Date dateadded) {
		this.dateadded = dateadded;
	}

	public String getIssue() {
		return issue;
	}

	public void setIssue(String issue) {
		this.issue = issue;
	}


	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getBiweeklyupdate() {
		return biweeklyupdate;
	}

	public void setBiweeklyupdate(String biweeklyupdate) {
		this.biweeklyupdate = biweeklyupdate;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Principal> getStakeHolders() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Principal getOwner() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
