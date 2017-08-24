package com.archsystemsinc.ipms.sec.model;

import java.util.Date;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated( value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor" )
@StaticMetamodel(Program.class)
public abstract class Program_ {

	public static volatile SingularAttribute<Program, Long> id;
	public static volatile SetAttribute<Program, Project> projects;
	public static volatile SingularAttribute<Program, String> name;
	public static volatile SingularAttribute<Program, String> description;
	public static volatile SingularAttribute<Program, Principal> manager;
	public static volatile SingularAttribute<Program, Date> startDate;
	public static volatile SingularAttribute<Program, Boolean> active;
	public static volatile SingularAttribute<Program, OrganizationGroup> organizationGroup;
}
