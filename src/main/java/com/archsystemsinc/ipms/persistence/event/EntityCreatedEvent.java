package com.archsystemsinc.ipms.persistence.event;

import java.util.Date;

import org.springframework.context.ApplicationEvent;

import com.archsystemsinc.ipms.common.IEntity;
import com.archsystemsinc.ipms.common.INameableEntity;
import com.archsystemsinc.ipms.sec.model.UserActivity;
import com.archsystemsinc.ipms.sec.model.UserActivityType;
import com.archsystemsinc.ipms.sec.persistence.dao.IUserActivityJpaDAO;
import com.archsystemsinc.ipms.sec.util.email.Emailer;
import com.google.common.base.Preconditions;

public final class EntityCreatedEvent< T extends INameableEntity > extends ApplicationEvent{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7696717858408917815L;
	private final Class< INameableEntity > clazz;
	private final INameableEntity entity;
	
	private Emailer emailer = null;

	public EntityCreatedEvent( final Object sourceToSet, final Class<T> clazz2, final T persistedEntity, final Emailer emailer2 ){
		super( sourceToSet );
		emailer = emailer2;
		Preconditions.checkNotNull( clazz2 );
		clazz = (Class<INameableEntity>) clazz2;  
		Preconditions.checkNotNull( persistedEntity );
		entity = (INameableEntity) persistedEntity;
		emailer.sendEmail(entity, "Created");	
	}
	
	public EntityCreatedEvent( final Object sourceToSet, final Class<T> clazz2, final T persistedEntity, final Emailer emailer2, IUserActivityJpaDAO iUserActivityJpaDAO, String user){
		super( sourceToSet );
		emailer = emailer2;
		Preconditions.checkNotNull( clazz2 );
		clazz = (Class<INameableEntity>) clazz2; 
		Preconditions.checkNotNull( persistedEntity );
		entity = (INameableEntity) persistedEntity;
		emailer.sendEmail(entity, "Created");
		UserActivity userActivity = new UserActivity();
		userActivity.setDateCreated(new Date());
		userActivity.setEntityId(persistedEntity.getId());
		userActivity.setEntityType(clazz.getSimpleName());
		userActivity.setEntityName(persistedEntity.getName());
		userActivity.setActivityType(UserActivityType.CREATE.name());
		userActivity.setUser(user);
		iUserActivityJpaDAO.save(userActivity);
	}
	
	// API

	public final Class< INameableEntity > getClazz(){
		return clazz;
	}
	
	public final INameableEntity getEntity(){
		return Preconditions.checkNotNull( entity );
	}
	
}
