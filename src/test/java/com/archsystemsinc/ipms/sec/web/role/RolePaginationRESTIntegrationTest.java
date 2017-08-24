package com.archsystemsinc.ipms.sec.web.role;

import org.springframework.beans.factory.annotation.Autowired;

import com.archsystemsinc.ipms.sec.client.template.RoleRESTTemplateImpl;
import com.archsystemsinc.ipms.sec.model.Role;
import com.archsystemsinc.ipms.sec.test.SecPaginationRESTIntegrationTest;

public class RolePaginationRESTIntegrationTest extends SecPaginationRESTIntegrationTest< Role >{
	
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
	
}
