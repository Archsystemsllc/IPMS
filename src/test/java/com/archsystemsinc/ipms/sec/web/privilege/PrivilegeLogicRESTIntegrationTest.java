package com.archsystemsinc.ipms.sec.web.privilege;

import org.springframework.beans.factory.annotation.Autowired;

import com.archsystemsinc.ipms.sec.client.template.PrivilegeRESTTemplateImpl;
import com.archsystemsinc.ipms.sec.model.Privilege;
import com.archsystemsinc.ipms.sec.test.SecLogicRESTIntegrationTest;
import com.jayway.restassured.specification.RequestSpecification;

public class PrivilegeLogicRESTIntegrationTest extends SecLogicRESTIntegrationTest< Privilege >{
	
	@Autowired private PrivilegeRESTTemplateImpl restTemplate;
	
	public PrivilegeLogicRESTIntegrationTest(){
		super( Privilege.class );
	}
	
	// tests
	
	// template
	
	@Override
	protected final PrivilegeRESTTemplateImpl getAPI(){
		return restTemplate;
	}
	
	@Override
	protected final String getURI(){
		return getAPI().getURI() + "/";
	}
	
	@Override
	protected final RequestSpecification givenAuthenticated(){
		return getAPI().givenAuthenticated();
	}
	
}
