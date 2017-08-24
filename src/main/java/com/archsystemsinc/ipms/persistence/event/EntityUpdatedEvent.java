package com.archsystemsinc.ipms.persistence.event;

import java.util.Date;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
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
public final class EntityUpdatedEvent< T extends INameableEntity > extends ApplicationEvent implements ApplicationContextAware{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5854431432483341823L;
	private final Class< INameableEntity > clazz;
	private final INameableEntity entity;
	private Emailer emailer;
	ApplicationContext applicationContext;
	
	private IUserActivityJpaDAO iUserActivityJpaDAO;
	
	@SuppressWarnings("unchecked")
	public EntityUpdatedEvent( final Object sourceToSet, final Class< T > clazzToSet, final T entityToSet, final Emailer emailer2 ){
		super( sourceToSet );
	    emailer = emailer2;	
		Preconditions.checkNotNull( clazzToSet );
		clazz = (Class<INameableEntity>) clazzToSet;
		Preconditions.checkNotNull( entityToSet );
		entity = (INameableEntity) entityToSet;
		
		emailer.sendEmail(entity, "Updated");
	}
	
	// API
	
	@SuppressWarnings("unchecked")
	public EntityUpdatedEvent(final Object sourceToSet, final Class< T > clazzToSet, final T entityToSet, final Emailer emailer2, IUserActivityJpaDAO iUserActivityJpaDAO , String user) {
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
		userActivity.setActivityType(UserActivityType.UPDATE.name());
		userActivity.setUser(user);
		iUserActivityJpaDAO.save(userActivity);
		emailer.sendEmail(entity, "Updated");
	}

	public final Class< INameableEntity > getClazz(){
		return clazz;
	}
	
	public final INameableEntity getEntity(){
		return Preconditions.checkNotNull( entity );
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.applicationContext = applicationContext;
	}
	
}
