package com.archsystemsinc.ipms.sec.model;

import java.util.Date;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated( value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor" )
@StaticMetamodel(Issue.class)
public abstract class Issue_ {

	public static volatile SingularAttribute<Issue, Long> id;
	public static volatile SetAttribute<Issue, LessonsLearned> lessonsLearneds;
	public static volatile SingularAttribute<Issue, String> summary;
	public static volatile SingularAttribute<Issue, String> name;
	public static volatile SingularAttribute<Issue, String> description;
	public static volatile SingularAttribute<Issue, Principal> assignee;
	public static volatile SingularAttribute<Issue, Principal> assigned;
	public static volatile SingularAttribute<Issue, Date> dueDate;
	public static volatile SingularAttribute<Issue, Date> dateReported;
	public static volatile SingularAttribute<Issue, String> status;
	public static volatile SetAttribute<Issue, RevisionHistory> revisions;
}
