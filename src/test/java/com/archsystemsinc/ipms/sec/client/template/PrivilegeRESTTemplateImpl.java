package com.archsystemsinc.ipms.sec.client.template;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.archsystemsinc.ipms.client.template.AbstractRESTTemplate;
import com.archsystemsinc.ipms.sec.client.ExamplePaths;
import com.archsystemsinc.ipms.sec.model.Privilege;
import com.archsystemsinc.ipms.testing.security.AuthenticationUtil;
import com.jayway.restassured.specification.RequestSpecification;

@Component
public final class PrivilegeRESTTemplateImpl extends AbstractRESTTemplate< Privilege >{
	
	@Autowired protected ExamplePaths paths;
	
	public PrivilegeRESTTemplateImpl(){
		super( Privilege.class );
	}
	
	// template method
	
	@Override
	public final String getURI(){
		return paths.getPrivilegeUri();
	}
	
	@Override
	public final RequestSpecification givenAuthenticated(){
		return AuthenticationUtil.givenBasicAuthenticated();
	}
	
	@Override
	public final Privilege createNewEntity(){
		return new Privilege( randomAlphabetic( 8 ) );
	}
	
	@Override
	public final void invalidate( final Privilege entity ){
		entity.setName( null );
	}
	
	@Override
	public void change( final Privilege resource ){
		resource.setName( randomAlphabetic( 8 ) );
	}
	
}
