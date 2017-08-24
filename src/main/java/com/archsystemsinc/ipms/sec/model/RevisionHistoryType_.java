package com.archsystemsinc.ipms.sec.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated( value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor" )
@StaticMetamodel(RevisionHistoryType.class)
public abstract class RevisionHistoryType_ {

	public static volatile SingularAttribute<RevisionHistoryType, Long> id;
	public static volatile SingularAttribute<RevisionHistoryType, String> name;
	public static volatile SingularAttribute<RevisionHistoryType, String> description;
	public static volatile SetAttribute<RevisionHistory, RevisionHistoryType> revisions;
}
