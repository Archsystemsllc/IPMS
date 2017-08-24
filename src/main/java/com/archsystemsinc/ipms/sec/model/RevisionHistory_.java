package com.archsystemsinc.ipms.sec.model;

import java.sql.Timestamp;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated( value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor" )
@StaticMetamodel(RevisionHistory.class)
public abstract class RevisionHistory_ {

	public static volatile SingularAttribute<RevisionHistory, Long> id;
	public static volatile SingularAttribute<RevisionHistory, String> text;
	public static volatile SingularAttribute<RevisionHistory, String> name;
	public static volatile SingularAttribute<RevisionHistory, Timestamp> updatedAt;
	public static volatile SingularAttribute<RevisionHistory, Requirement> requirement;
	public static volatile SingularAttribute<RevisionHistory, Principal> principal;
	public static volatile SingularAttribute<RevisionHistory, RevisionHistoryType> revisionHistoryType;
	public static volatile SingularAttribute<RevisionHistory, ActionItem> actionItem;
	public static volatile SingularAttribute<RevisionHistory, Issue> issue;
	public static volatile SingularAttribute<RevisionHistory, LessonsLearned> lessonsLearned;
	public static volatile SingularAttribute<RevisionHistory, Risk> risk;
	public static volatile SingularAttribute<RevisionHistory, Task> task;
}
