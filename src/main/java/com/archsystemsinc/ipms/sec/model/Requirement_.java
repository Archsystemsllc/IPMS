package com.archsystemsinc.ipms.sec.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated( value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor" )
@StaticMetamodel(Requirement.class)
public abstract class Requirement_ {

	public static volatile SingularAttribute<Requirement, Long> id;
	public static volatile SingularAttribute<Requirement, Long> requirementId;
	public static volatile SetAttribute<Requirement, Comment> comments;
	public static volatile SetAttribute<Requirement, RevisionHistory> revisions;
	public static volatile SingularAttribute<Requirement, String> summary;
	public static volatile SingularAttribute<Requirement, String> name;
	public static volatile SingularAttribute<Requirement, String> description;
	public static volatile SingularAttribute<Requirement, Integer> priority;
	public static volatile SingularAttribute<Requirement, Integer> type;
	public static volatile SingularAttribute<Requirement, Integer> riskLevel;
	public static volatile SingularAttribute<Requirement, String> riskDescription;
}
