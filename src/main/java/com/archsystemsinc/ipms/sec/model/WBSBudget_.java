package com.archsystemsinc.ipms.sec.model;

import java.util.Date;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated( value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor" )
@StaticMetamodel(WBSBudget.class)
public abstract class WBSBudget_ {

	public static volatile SingularAttribute<WBSBudget, Long> id;
	//public static volatile SingularAttribute<WBSBudget, Long> taskId;
	public static volatile SingularAttribute<WBSBudget, Principal> createdBy;
	public static volatile SingularAttribute<WBSBudget, Principal> updatedBy;
	public static volatile SingularAttribute<WBSBudget, Date> datecreated;
	public static volatile SingularAttribute<WBSBudget, Date> dateupdated;

}
