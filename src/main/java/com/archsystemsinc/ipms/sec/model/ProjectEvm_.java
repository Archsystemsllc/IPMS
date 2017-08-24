package com.archsystemsinc.ipms.sec.model;

import java.util.Date;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated( value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor" )
@StaticMetamodel(ProjectEvm.class)
public abstract class ProjectEvm_ {

	public static volatile SingularAttribute<ProjectEvm, Long> id;
	public static volatile SingularAttribute<ProjectEvm, String> name;
	public static volatile SingularAttribute<ProjectEvm, Date> date;
	public static volatile SingularAttribute<ProjectEvm, Float> bcws;
	public static volatile SingularAttribute<ProjectEvm, Float> cpi;
	public static volatile SingularAttribute<ProjectEvm, Float> acwp;
	public static volatile SingularAttribute<ProjectEvm, Float> sv;
	public static volatile SingularAttribute<ProjectEvm, Float> cv;
	public static volatile SingularAttribute<ProjectEvm, Float> eac;
	public static volatile SingularAttribute<ProjectEvm, Float> vac;
	public static volatile SingularAttribute<ProjectEvm, Float> spi;
}
