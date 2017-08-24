package com.archsystemsinc.ipms.security;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import com.archsystemsinc.ipms.sec.client.template.UserRESTTemplateImpl;
import com.archsystemsinc.ipms.spring.client.ClientTestConfig;
import com.archsystemsinc.ipms.spring.context.ContextTestConfig;
import com.archsystemsinc.ipms.spring.testing.TestingConfig;
import com.jayway.restassured.response.Response;

@RunWith( SpringJUnit4ClassRunner.class )
@ContextConfiguration( classes = { ClientTestConfig.class, TestingConfig.class, ContextTestConfig.class },loader = AnnotationConfigContextLoader.class )
public class SecurityRESTIntegrationTest{
	
	@Autowired private UserRESTTemplateImpl userTemplate;
	
	// tests
	
	// Unauthenticated
	
	@Test
	public final void givenUnauthenticated_whenAResourceIsDeleted_then401IsReceived(){
		// Given
		final String uriOfExistingResource = userTemplate.createResourceAsURI( userTemplate.createNewEntity() );
		
		// When
		final Response response = given().delete( uriOfExistingResource );
		
		// Then
		assertThat( response.getStatusCode(), is( 401 ) );
	}
	
	// Authenticated
	
	@Test
	public final void givenAuthenticatedByBasicAuth_whenAResourceIsCreated_then201IsReceived(){
		// Given
		// When
		final Response response = userTemplate.givenAuthenticated().contentType( userTemplate.getMime() ).body( userTemplate.createNewEntity() ).post( userTemplate.getURI() );
		
		// Then7
		assertThat( response.getStatusCode(), is( 201 ) );
	}
	
	@Test
	public final void givenAuthenticatedByDigestAuth_whenAResourceIsCreated_then201IsReceived(){
		// Given
		// When
		final Response response = userTemplate.givenAuthenticated().contentType( userTemplate.getMime() ).body( userTemplate.createNewEntity() ).post( userTemplate.getURI() );
		
		// Then
		assertThat( response.getStatusCode(), is( 201 ) );
	}
	
}
