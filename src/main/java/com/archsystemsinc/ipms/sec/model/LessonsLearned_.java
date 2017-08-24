package com.archsystemsinc.ipms.sec.model;

import java.util.Date;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated( value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor" )
@StaticMetamodel(LessonsLearned.class)
public abstract class LessonsLearned_ {

	public static volatile SingularAttribute<LessonsLearned, Long> id;
	public static volatile SingularAttribute<LessonsLearned, String> summary;
	public static volatile SingularAttribute<LessonsLearned, String> name;
	public static volatile SingularAttribute<LessonsLearned, String> impact;
	public static volatile SingularAttribute<LessonsLearned, String> recommendation;
	public static volatile SingularAttribute<LessonsLearned, Issue> issue;
	public static volatile SingularAttribute<Program, Date> date;
	public static volatile SetAttribute<LessonsLearned, RevisionHistory> revisions;

}
