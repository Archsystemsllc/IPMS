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

import org.hibernate.validator.constraints.NotEmpty;

import com.archsystemsinc.ipms.common.INameableEntity;
import com.thoughtworks.xstream.annotations.XStreamAlias;

@Entity
@XmlRootElement
@XStreamAlias( "privilege" )
@Table(name = "privilege")
public class Privilege implements INameableEntity{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "priv_id")
	private Long id;
	@Column(unique = true, nullable = false)
	@NotEmpty
	private String name;
	
	@Column
	private String description;

	public Privilege(){
		super();
	}

	public Privilege( final String nameToSet ){
		super();
		name = nameToSet;
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

	public String getFormattedName() {
		if(name != null)
			return name.replace(",", "<br>");
		
		return name;
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
		final Privilege other = (Privilege) obj;
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
		return null;
	}

	@Override
	public Set<Principal> getStakeHolders() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	
}
