package com.archsystemsinc.ipms.sec.model;

import java.util.Date;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated( value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor" )
@StaticMetamodel(OrganizationGroup.class)
public abstract class OrganizationGroup_ {

	public static volatile SingularAttribute<OrganizationGroup, Long> id;
	public static volatile SingularAttribute<OrganizationGroup, String> name;
	public static volatile SingularAttribute<OrganizationGroup, String> director;
	public static volatile SingularAttribute<OrganizationGroup, String> firstDeputy;
	public static volatile SingularAttribute<OrganizationGroup, String> secondDeputy;
	public static volatile SingularAttribute<OrganizationGroup, String> recordStatus;
	public static volatile SingularAttribute<OrganizationGroup, Date> dateCreated;
	public static volatile SingularAttribute<OrganizationGroup, Date> startDate;
	public static volatile SingularAttribute<OrganizationGroup, Date> endDate;
	public static volatile SetAttribute<OrganizationGroup, Program> programs;
		

}
