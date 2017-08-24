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
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.validator.constraints.NotEmpty;

import com.archsystemsinc.ipms.common.INameableEntity;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

@Entity
@XmlRootElement
@XStreamAlias("group")
@Table(name = "group")
public class Group implements INameableEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "group_id")
	@XStreamAsAttribute
	private Long id;
	@Column(unique = true, nullable = false)
	@NotEmpty
	private String name;

	//@formatter:off
	@ManyToMany( /*cascade = { CascadeType.REMOVE },*/fetch = FetchType.LAZY )
	@JoinTable(joinColumns = { @JoinColumn(name = "group_id", referencedColumnName = "group_id") }, inverseJoinColumns = { @JoinColumn(name = "principal_id", referencedColumnName = "principal_id") })
	@XStreamImplicit
	private Set<Principal> principals;
	//@formatter:on

	public Group() {
		super();
	}

	public Group(final String nameToSet) {
		super();
		name = nameToSet;
	}

	public Group(final String nameToSet, final Set<Principal> principalsToSet) {
		super();
		name = nameToSet;
		principals = principalsToSet;
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

	public Set<Principal> getPrrincipals() {
		return principals;
	}

	public void setPrincipals(final Set<Principal> principalsToSet) {
		principals = principalsToSet;
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
		final Group other = (Group) obj;
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
		return null;
	}

}
