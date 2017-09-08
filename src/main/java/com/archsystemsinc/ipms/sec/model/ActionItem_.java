package com.archsystemsinc.ipms.sec.model;
/**
* This class consists declarations for ActionItems model*   
 * @author Nikhil Dasari
* @version 1.1
*/

import java.util.Date;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated( value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor" )
@StaticMetamodel(ActionItem.class)
public abstract class ActionItem_ {

	public static volatile SingularAttribute<ActionItem, Long> id;
	public static volatile SingularAttribute<ActionItem, String> summary;
	//public static volatile SingularAttribute<ActionItem, String> name;
	public static volatile SingularAttribute<ActionItem, String> notes;
	public static volatile SingularAttribute<ActionItem, String> description;
	public static volatile SingularAttribute<ActionItem, String> responsibleParty;
	public static volatile SingularAttribute<ActionItem, String> aiNumberFromExcel;
	public static volatile SingularAttribute<ActionItem, Date> dateCompleted;
	public static volatile SingularAttribute<ActionItem, Date> dateCreated;
	public static volatile SingularAttribute<ActionItem, Issue> issue;
	public static volatile SingularAttribute<ActionItem, Date> dueDate;
	public static volatile SingularAttribute<ActionItem, Principal> assignedTo;
	public static volatile SingularAttribute<ActionItem, String> status;
	public static volatile SetAttribute<ActionItem, RevisionHistory> revisions;
}
