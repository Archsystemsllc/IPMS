package com.archsystemsinc.ipms.sec.web.privilege;

import org.springframework.beans.factory.annotation.Autowired;

import com.archsystemsinc.ipms.sec.client.template.PrivilegeRESTTemplateImpl;
import com.archsystemsinc.ipms.sec.model.Privilege;
import com.archsystemsinc.ipms.sec.test.SecMimeRESTIntegrationTest;

public class PrivilegeMimeRESTIntegrationTest extends SecMimeRESTIntegrationTest< Privilege >{
	
	@Autowired private PrivilegeRESTTemplateImpl restTemplate;
	
	public PrivilegeMimeRESTIntegrationTest(){
		super( Privilege.class );
	}
	
	// tests
	
	// template method
	
	@Override
	protected final PrivilegeRESTTemplateImpl getAPI(){
		return restTemplate;
	}
	
}
