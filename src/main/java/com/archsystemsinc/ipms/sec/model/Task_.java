package com.archsystemsinc.ipms.sec.model;

import java.util.Date;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated( value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor" )
@StaticMetamodel(Task.class)
public abstract class Task_ {

	public static volatile SingularAttribute<Task, Long> id;
	public static volatile SingularAttribute<Task, String> name;
	public static volatile SingularAttribute<Task, String> description;
	public static volatile SingularAttribute<Task, String> status;
	public static volatile SingularAttribute<Task, Date> dateCreated;
	public static volatile SingularAttribute<Task, Date> dueDate;
	public static volatile SingularAttribute<Task, Principal> assignedTo;
	public static volatile SingularAttribute<Task, Principal> createdBy;
	public static volatile SetAttribute<Task, RevisionHistory> revisions;

}
