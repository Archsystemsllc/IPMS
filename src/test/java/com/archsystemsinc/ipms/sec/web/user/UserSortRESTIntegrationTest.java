package com.archsystemsinc.ipms.sec.web.user;

import org.springframework.beans.factory.annotation.Autowired;

import com.archsystemsinc.ipms.sec.client.template.UserRESTTemplateImpl;
import com.archsystemsinc.ipms.sec.model.dto.User;
import com.archsystemsinc.ipms.sec.test.SecSortRESTIntegrationTest;
import com.google.common.collect.Ordering;

public class UserSortRESTIntegrationTest extends SecSortRESTIntegrationTest< User >{
	
	@Autowired private UserRESTTemplateImpl template;
	
	// tests
	
	// template method (shortcuts)
	
	@Override
	protected final User createNewEntity(){
		return template.createNewEntity();
	}
	
	@Override
	protected final String getURI(){
		return template.getURI();
	}
	
	@Override
	protected final UserRESTTemplateImpl getAPI(){
		return template;
	}
	
	@Override
	protected final Ordering< User > getOrdering(){
		return new Ordering< User >(){
			@Override
			public final int compare( final User left, final User right ){
				return left.getName().compareToIgnoreCase( right.getName() );
			}
		};
	}
	
}
