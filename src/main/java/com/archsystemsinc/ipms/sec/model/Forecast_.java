package com.archsystemsinc.ipms.sec.model;

import java.util.Date;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated( value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor" )
@StaticMetamodel(Forecast.class)
public abstract class Forecast_ {
	public static volatile SingularAttribute<Forecast, Long> id;
	public static volatile SingularAttribute<Forecast, Long> project_id;
	public static volatile SingularAttribute<Forecast, Float> totalAmount;
	public static volatile SingularAttribute<Forecast, Float> startAmount;
	public static volatile SingularAttribute<Forecast, Float> spentAmount;
	public static volatile SingularAttribute<Forecast, Float> leftAmount;
	public static volatile SingularAttribute<Forecast, Date> date;
	public static volatile SingularAttribute<Forecast, Principal> createdBy;
	public static volatile SingularAttribute<Forecast, Principal> updatedBy;
	public static volatile SingularAttribute<Forecast, Date> datecreated;
	public static volatile SingularAttribute<Forecast, Date> dateupdated;

}
