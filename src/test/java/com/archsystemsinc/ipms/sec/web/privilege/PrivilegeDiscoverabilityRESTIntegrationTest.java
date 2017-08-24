package com.archsystemsinc.ipms.sec.web.privilege;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;

import org.springframework.beans.factory.annotation.Autowired;

import com.archsystemsinc.ipms.sec.client.template.PrivilegeRESTTemplateImpl;
import com.archsystemsinc.ipms.sec.model.Privilege;
import com.archsystemsinc.ipms.sec.test.SecDiscoverabilityRESTIntegrationTest;
import com.jayway.restassured.specification.RequestSpecification;

public class PrivilegeDiscoverabilityRESTIntegrationTest extends SecDiscoverabilityRESTIntegrationTest< Privilege >{
	
	@Autowired private PrivilegeRESTTemplateImpl restTemplate;
	
	public PrivilegeDiscoverabilityRESTIntegrationTest(){
		super( Privilege.class );
	}
	
	// tests
	
	// template method
	
	@Override
	protected final Privilege createNewEntity(){
		return restTemplate.createNewEntity();
	}
	
	@Override
	protected final String getURI(){
		return getAPI().getURI();
	}
	
	@Override
	protected void change( final Privilege resource ){
		resource.setName( randomAlphabetic( 6 ) );
	}
	
	@Override
	protected RequestSpecification givenAuthenticated(){
		return getAPI().givenAuthenticated();
	}
	
	@Override
	protected final PrivilegeRESTTemplateImpl getAPI(){
		return restTemplate;
	}
	
}
