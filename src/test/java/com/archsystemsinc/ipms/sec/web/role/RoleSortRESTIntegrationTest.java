package com.archsystemsinc.ipms.sec.web.role;

import org.springframework.beans.factory.annotation.Autowired;

import com.archsystemsinc.ipms.sec.client.template.RoleRESTTemplateImpl;
import com.archsystemsinc.ipms.sec.model.Role;
import com.archsystemsinc.ipms.sec.test.SecSortRESTIntegrationTest;
import com.google.common.collect.Ordering;

public class RoleSortRESTIntegrationTest extends SecSortRESTIntegrationTest< Role >{
	
	@Autowired private RoleRESTTemplateImpl template;
	
	// tests
	
	// template method (shortcuts)
	
	@Override
	protected final Role createNewEntity(){
		return template.createNewEntity();
	}
	
	@Override
	protected final String getURI(){
		return template.getURI();
	}
	
	@Override
	protected final RoleRESTTemplateImpl getAPI(){
		return template;
	}
	
	@Override
	protected final Ordering< Role > getOrdering(){
		return new Ordering< Role >(){
			@Override
			public final int compare( final Role left, final Role right ){
				return left.getName().compareToIgnoreCase( right.getName() );
			}
		};
	}
	
}
