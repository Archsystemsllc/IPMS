package com.archsystemsinc.ipms.sec.model;

import java.sql.Timestamp;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated( value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor" )
@StaticMetamodel(Comment.class)
public abstract class Comment_ {

	public static volatile SingularAttribute<Comment, Long> id;
	public static volatile SingularAttribute<Comment, String> text;
	public static volatile SingularAttribute<Comment, String> name;
	public static volatile SingularAttribute<Comment, Timestamp> madeAt;
	public static volatile SingularAttribute<Comment, Requirement> requirement;
	public static volatile SingularAttribute<Comment, Principal> principal;
}
