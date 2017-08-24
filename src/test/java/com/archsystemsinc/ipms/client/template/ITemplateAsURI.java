package com.archsystemsinc.ipms.client.template;

import com.archsystemsinc.ipms.common.IEntity;

public interface ITemplateAsURI< T extends IEntity >{
	
	// create
	
	String createResourceAsURI( final T resource ); // 8
	
}
