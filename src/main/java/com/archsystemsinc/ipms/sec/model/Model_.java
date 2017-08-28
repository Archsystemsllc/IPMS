package com.archsystemsinc.ipms.sec.model;

import java.util.Date;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated( value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor" )
@StaticMetamodel(ModelIPMS.class)
public abstract class Model_ {
	
	public static volatile SingularAttribute<ModelIPMS, Long> id;
	public static volatile SetAttribute<ModelIPMS, Project> projects;
	public static volatile SingularAttribute<ModelIPMS, String> name;
	public static volatile SingularAttribute<ModelIPMS, String> description;
	public static volatile SingularAttribute<ModelIPMS, Principal> manager;
	public static volatile SingularAttribute<ModelIPMS, Date> startDate;
	public static volatile SingularAttribute<ModelIPMS, Boolean> active;
	public static volatile SingularAttribute<ModelIPMS, Program> organizationGroup;


}