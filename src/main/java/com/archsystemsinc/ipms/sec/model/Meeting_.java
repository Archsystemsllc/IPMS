package com.archsystemsinc.ipms.sec.model;

import java.util.Date;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated( value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor" )
@StaticMetamodel(Meeting.class)
public abstract class Meeting_ {

	public static volatile SingularAttribute<Meeting, Long> id;
	public static volatile SingularAttribute<Meeting, String> summary;
	public static volatile SingularAttribute<Meeting, String> name;
	public static volatile SingularAttribute<Meeting, Project> project;
	public static volatile SingularAttribute<Meeting, Program> program;
	public static volatile SingularAttribute<Meeting, Date> date;
	public static volatile SingularAttribute<Meeting, Principal> organizer;
	public static volatile SetAttribute<Meeting, Principal> attendees;

}
