package com.archsystemsinc.ipms.sec.model;

import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import com.archsystemsinc.ipms.common.INameableEntity;
import com.thoughtworks.xstream.annotations.XStreamAlias;


@Entity
@XmlRootElement
@XStreamAlias("Attendee")
@Table(name = "attendee")
public class Attendee implements INameableEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "attendee_id")
	private Long id;

	@Column(nullable = true)
	private String name;

	@Column(nullable = true)
	private String email;

	@Column(nullable = true)
	private String telephone;

	@Column(nullable = true)
	private String company;

	public String getEmail() {
		return email;
	}

	public void setEmail(final String email1) {
		email = email1;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(final String telephone1) {
		telephone = telephone1;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(final String company1) {
		company = company1;
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
		return null;
	}

	@Override
	public Set<Principal> getStakeHolders() {
		// TODO Auto-generated method stub
		return null;
	}

}
