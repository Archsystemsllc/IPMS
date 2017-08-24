package com.archsystemsinc.ipms.sec.model;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang.builder.ToStringBuilder;


import com.archsystemsinc.ipms.common.INameableEntity;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

@Entity
@XmlRootElement
@XStreamAlias("evm")
@Table(name = "project_evm")
public class ProjectEvm implements INameableEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "evm_id")
	@XStreamAsAttribute
	private Long id;

	@Column(name="cvp", nullable = true)
	private float cvPercent;
	
	@Column(name="svp", nullable = true)
	private float svPercent;
	
	@Column(nullable = true)
	private String name;
	
	@ManyToOne
	@JoinColumn(name = "project_id")
	private Project project;
	
	@Column(nullable = true)
	@OrderBy("date asc")
	private Date date;

	@Column(name = "bcws",nullable=true)
    private float bcws;
    
	@Column(name = "cpi",nullable=true)
    private float cpi;
    
	@Column(name = "bcwp", nullable=true)
    private float bcwp;
    
	@Column(name = "acwp", nullable=true)
    private float acwp;
    
	@Column(name = "sv", nullable=true)
    private float sv;
    
	@Column(name = "cv", nullable=true)
    private float cv;
    
	@Column(name = "eac", nullable=true)
    private float eac;
    
	@Column(name = "vac", nullable=true)
    private float vac;
    
	@Column(name = "spi", nullable=true)
    private float spi;
	
	public float getCvPercent() {
		if(bcwp != 0)
		      return (cv / bcwp)*100;
		else
			return cvPercent;
	}

	public void setCvPercent(float cvPercent) {
		this.cvPercent = cvPercent;
	}

	public float getSvPercent() {
		if(bcwp != 0)
		   return (sv / bcwp)*100;
		else
		   return svPercent;
	}

	public void setSvPercent(float svPercent) {
		this.svPercent = svPercent;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
     
	public float getBcws() {
		return bcws;
	}

	public void setBcws(float bcws) {
		this.bcws = bcws;
	}

	public float getCpi() {
		return cpi;
	}

	public void setCpi(float cpi) {
		this.cpi = cpi;
	}

	public float getBcwp() {
		return bcwp;
	}

	public void setBcwp(float bcwp) {
		this.bcwp = bcwp;
	}

	public float getAcwp() {
		return acwp;
	}

	public void setAcwp(float acwp) {
		this.acwp = acwp;
	}

	public float getSv() {
		return sv;
	}

	public void setSv(float sv) {
		this.sv = sv;
	}

	public float getCv() {
		return cv;
	}

	public void setCv(float cv) {
		this.cv = cv;
	}

	public float getEac() {
		return eac;
	}

	public void setEac(float eac) {
		this.eac = eac;
	}

	public float getVac() {
		return vac;
	}

	public void setVac(float vac) {
		this.vac = vac;
	}

	public float getSpi() {
		return spi;
	}

	public void setSpi(float spi) {
		this.spi = spi;
	}

	@Override
	public String getName() {
		return name;
	}

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
		final ProjectEvm other = (ProjectEvm) obj;
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
	public Long getId() {
		// TODO Auto-generated method stub
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;	
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
