package com.archsystemsinc.ipms.client.template;

import com.archsystemsinc.ipms.common.IEntity;

public interface IEntityOperations< T extends IEntity >{
	
	T createNewEntity();
	
	void invalidate( final T entity );
	
	void change( final T resource );
	
}
