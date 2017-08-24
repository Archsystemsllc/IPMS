package com.archsystemsinc.ipms.sec.model;

import java.util.Date;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated( value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor" )
@StaticMetamodel(Project.class)
public abstract class Project_ {

	public static volatile SingularAttribute<Project, Long> id;
	public static volatile SetAttribute<Project, Issue> issues;
	public static volatile SingularAttribute<Project, String> name;
	public static volatile SingularAttribute<Project, String> description;
	public static volatile SingularAttribute<Project, Principal> manager;
	public static volatile SingularAttribute<Project, Date> startDate;
	public static volatile SingularAttribute<Project, Program> program;
	public static volatile SingularAttribute<Project, Date> endDate;
	public static volatile SingularAttribute<Project, Boolean> active;
    public static volatile SetAttribute<Project, ProjectEvm> projectEvms;
    
}
