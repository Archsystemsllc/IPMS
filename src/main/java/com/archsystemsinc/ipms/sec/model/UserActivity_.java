package com.archsystemsinc.ipms.sec.model;

import java.util.Date;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated( value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor" )
@StaticMetamodel(UserActivity.class)
public abstract class UserActivity_ {

	public static volatile SingularAttribute<UserActivity, Long> id;
	public static volatile SingularAttribute<UserActivity, String> entityName;
	public static volatile SingularAttribute<UserActivity, String> entityType;
	public static volatile SingularAttribute<UserActivity, String> activityType;
	public static volatile SingularAttribute<UserActivity, Long> entityId;
	public static volatile SingularAttribute<UserActivity, Date> dateCreated;
	public static volatile SingularAttribute<UserActivity, String> user;
}
