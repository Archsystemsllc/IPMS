package com.archsystemsinc.ipms.sec.model;

import java.util.Date;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated( value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor" )
@StaticMetamodel(OrganizationCategory.class)
public class OrganizationCategory_ {
	
	public static volatile SingularAttribute<OrganizationCategory, Long> id;
	public static volatile SingularAttribute<OrganizationCategory, Project> project;
	public static volatile SingularAttribute<OrganizationCategory, Principal> principal;
	public static volatile SingularAttribute<OrganizationCategory, String> category;
	public static volatile SingularAttribute<OrganizationCategory, Float> rate;
	public static volatile SingularAttribute<OrganizationCategory, Principal> createdBy;
	public static volatile SingularAttribute<OrganizationCategory, Principal> updatedBy;
	public static volatile SingularAttribute<OrganizationCategory, Date> datecreated;
	public static volatile SingularAttribute<OrganizationCategory, Date> dateupdated;


}
