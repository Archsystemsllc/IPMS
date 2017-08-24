package com.archsystemsinc.ipms.sec.web.role;

import org.springframework.beans.factory.annotation.Autowired;

import com.archsystemsinc.ipms.sec.client.template.RoleRESTTemplateImpl;
import com.archsystemsinc.ipms.sec.model.Role;
import com.archsystemsinc.ipms.sec.test.SecMimeRESTIntegrationTest;

public class RoleMimeRESTIntegrationTest extends SecMimeRESTIntegrationTest< Role >{
	
	@Autowired private RoleRESTTemplateImpl restTemplate;
	
	public RoleMimeRESTIntegrationTest(){
		super( Role.class );
	}
	
	// tests
	
	// template method
	
	@Override
	protected final RoleRESTTemplateImpl getAPI(){
		return restTemplate;
	}
	
}
