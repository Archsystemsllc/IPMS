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

/**
 * This event should be fired when entity is updated.
 */
public final class EntityDeletedEvent< T extends INameableEntity > extends ApplicationEvent{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 841754405150965445L;
	private final Class< INameableEntity > clazz;
	private final INameableEntity entity;
	private Emailer emailer;
	
	public EntityDeletedEvent( final Object sourceToSet, final Class< T > clazzToSet, final T entityToSet,final Emailer emailer2 ){
		super( sourceToSet );
		emailer = emailer2;
		Preconditions.checkNotNull( clazzToSet );
		clazz = (Class<INameableEntity>) clazzToSet;
		
		Preconditions.checkNotNull( entityToSet );
		entity = (INameableEntity) entityToSet;
		
		emailer.sendEmail(entity, "Deleted");
	}
	
	@SuppressWarnings("unchecked")
	public EntityDeletedEvent( final Object sourceToSet, final Class< T > clazzToSet, final T entityToSet,final Emailer emailer2, IUserActivityJpaDAO iUserActivityJpaDAO , String user){
		super( sourceToSet );
		emailer = emailer2;
		Preconditions.checkNotNull( clazzToSet );
		clazz = (Class<INameableEntity>) clazzToSet;
		
		Preconditions.checkNotNull( entityToSet );
		entity = (INameableEntity) entityToSet;
		UserActivity userActivity = new UserActivity();
		userActivity.setDateCreated(new Date());
		userActivity.setEntityId(entityToSet.getId());
		userActivity.setEntityType(clazzToSet.getSimpleName());
		userActivity.setEntityName(entityToSet.getName());
		userActivity.setActivityType(UserActivityType.DELETE.name());
		userActivity.setUser(user);
		iUserActivityJpaDAO.save(userActivity);
		emailer.sendEmail(entity, "Deleted");
	}
	
	// API
	
	public final Class< INameableEntity > getClazz(){
		return clazz;
	}
	
	public final INameableEntity getEntity(){
		return Preconditions.checkNotNull( entity );
	}
	
}
