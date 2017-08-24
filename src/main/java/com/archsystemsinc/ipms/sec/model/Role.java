package com.archsystemsinc.ipms.sec.model;

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
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.validator.constraints.NotEmpty;

import com.archsystemsinc.ipms.common.INameableEntity;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

@Entity
@XmlRootElement
@XStreamAlias( "role" )
@Table(name = "role")
public class Role implements INameableEntity{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "role_id")
	@XStreamAsAttribute
	private Long id;
	@Column(unique = true, nullable = false)
	@NotEmpty
	private String name;

	//@formatter:off
	@ManyToMany( /*cascade = { CascadeType.REMOVE },*/fetch = FetchType.LAZY )
	@JoinTable(joinColumns = { @JoinColumn(name = "role_id", referencedColumnName = "role_id") }, inverseJoinColumns = { @JoinColumn(name = "priv_id", referencedColumnName = "priv_id") })
	@XStreamImplicit
	private Set< Privilege > privileges;
	//@formatter:on
	
	@Transient
	private List<Long> privilegesIds;

	public Role(){
		super();
	}

	public Role( final String nameToSet ){
		super();
		name = nameToSet;
	}

	public Role( final String nameToSet, final Set< Privilege > privilegesToSet ){
		super();
		name = nameToSet;
		privileges = privilegesToSet;
	}

	// API

	@Override
	public Long getId(){
		return id;
	}

	@Override
	public void setId( final Long idToSet ){
		id = idToSet;
	}

	@Override
	public String getName(){
		return name;
	}

	public void setName( final String nameToSet ){
		name = nameToSet;
	}

	public Set< Privilege > getPrivileges(){
		return privileges;
	}

	public void setPrivileges( final Set< Privilege > privilegesToSet ){
		privileges = privilegesToSet;
	}	
	
	public List<Long> getPrivilegesIds() {
		return privilegesIds;
	}

	public void setPrivilegesIds(List<Long> privilegesIds) {
		this.privilegesIds = privilegesIds;
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
			return true;
		if( obj == null )
			return false;
		if( getClass() != obj.getClass() )
			return false;
		final Role other = (Role) obj;
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

}
