package com.archsystemsinc.ipms.test.scenario;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.List;

import org.hamcrest.Matchers;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.archsystemsinc.ipms.sec.client.ExamplePaths;
import com.archsystemsinc.ipms.sec.test.SecGeneralRESTIntegrationTest;
import com.archsystemsinc.ipms.testing.security.AuthenticationUtil;
import com.archsystemsinc.ipms.web.http.HTTPLinkHeaderUtils;
import com.google.common.net.HttpHeaders;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.specification.RequestSpecification;

public class ScenarioRESTIntegrationTest extends SecGeneralRESTIntegrationTest{
	
	@Autowired private ExamplePaths paths;
	
	// tests
	
	// GET
	
	@Test
	public final void whenGetIsDoneOnRoot_then(){
		// When
		final Response getOnRootResponse = givenAuthenticated().get( paths.getRootUri() );
		
		// Then
		final List< String > allURIsDiscoverableFromRoot = HTTPLinkHeaderUtils.extractAllURIs( getOnRootResponse.getHeader( HttpHeaders.LINK ) );
		
		assertThat( allURIsDiscoverableFromRoot, not( Matchers.<String> empty() ) );
	}
	
	// util
	
	protected final RequestSpecification givenAuthenticated(){
		return AuthenticationUtil.givenBasicAuthenticated();
	}
	
}
