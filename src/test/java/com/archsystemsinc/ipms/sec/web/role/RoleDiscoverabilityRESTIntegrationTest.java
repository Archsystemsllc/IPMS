package com.archsystemsinc.ipms.sec.web.role;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;

import org.springframework.beans.factory.annotation.Autowired;

import com.archsystemsinc.ipms.sec.client.template.RoleRESTTemplateImpl;
import com.archsystemsinc.ipms.sec.model.Role;
import com.archsystemsinc.ipms.sec.test.SecDiscoverabilityRESTIntegrationTest;
import com.jayway.restassured.specification.RequestSpecification;

public class RoleDiscoverabilityRESTIntegrationTest extends SecDiscoverabilityRESTIntegrationTest< Role >{
	
	@Autowired private RoleRESTTemplateImpl restTemplate;
	
	public RoleDiscoverabilityRESTIntegrationTest(){
		super( Role.class );
	}
	
	// tests
	
	// template method
	
	@Override
	protected final Role createNewEntity(){
		return restTemplate.createNewEntity();
	}
	
	@Override
	protected final String getURI(){
		return getAPI().getURI();
	}
	
	@Override
	protected void change( final Role resource ){
		resource.setName( randomAlphabetic( 6 ) );
	}
	
	@Override
	protected RequestSpecification givenAuthenticated(){
		return getAPI().givenAuthenticated();
	}
	
	@Override
	protected final RoleRESTTemplateImpl getAPI(){
		return restTemplate;
	}
	
}
