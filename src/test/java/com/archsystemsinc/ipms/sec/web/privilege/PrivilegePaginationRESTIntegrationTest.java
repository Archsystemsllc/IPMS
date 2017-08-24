package com.archsystemsinc.ipms.sec.web.privilege;

import org.springframework.beans.factory.annotation.Autowired;

import com.archsystemsinc.ipms.sec.client.template.PrivilegeRESTTemplateImpl;
import com.archsystemsinc.ipms.sec.model.Privilege;
import com.archsystemsinc.ipms.sec.test.SecPaginationRESTIntegrationTest;

public class PrivilegePaginationRESTIntegrationTest extends SecPaginationRESTIntegrationTest< Privilege >{
	
	@Autowired private PrivilegeRESTTemplateImpl template;
	
	// tests
	
	// template method (shortcuts)
	
	@Override
	protected final Privilege createNewEntity(){
		return template.createNewEntity();
	}
	
	@Override
	protected final String getURI(){
		return template.getURI();
	}
	
	@Override
	protected final PrivilegeRESTTemplateImpl getAPI(){
		return template;
	}
	
}
