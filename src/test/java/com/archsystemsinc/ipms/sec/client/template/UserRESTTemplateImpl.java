package com.archsystemsinc.ipms.sec.client.template;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.archsystemsinc.ipms.client.template.AbstractRESTTemplate;
import com.archsystemsinc.ipms.sec.client.ExamplePaths;
import com.archsystemsinc.ipms.sec.model.Role;
import com.archsystemsinc.ipms.sec.model.dto.User;
import com.archsystemsinc.ipms.testing.security.AuthenticationUtil;
import com.google.common.collect.Sets;
import com.jayway.restassured.specification.RequestSpecification;

@Component
public final class UserRESTTemplateImpl extends AbstractRESTTemplate< User >{
	
	@Autowired protected ExamplePaths paths;
	
	public UserRESTTemplateImpl(){
		super( User.class );
	}
	
	// API
	
	// template method
	
	@Override
	public final String getURI(){
		return paths.getUserUri();
	}
	
	@Override
	public final RequestSpecification givenAuthenticated(){
		return AuthenticationUtil.givenBasicAuthenticated();
	}
	
	@Override
	public final User createNewEntity(){
		return new User( randomAlphabetic( 8 ), randomAlphabetic( 8 ), Sets.<Role> newHashSet() );
	}
	
	@Override
	public final void invalidate( final User entity ){
		entity.setName( null );
	}
	
	@Override
	public final void change( final User resource ){
		resource.setName( randomAlphabetic( 8 ) );
	}
	
}
