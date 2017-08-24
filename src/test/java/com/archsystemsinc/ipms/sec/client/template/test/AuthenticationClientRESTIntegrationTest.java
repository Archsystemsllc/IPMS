package com.archsystemsinc.ipms.sec.client.template.test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import com.archsystemsinc.ipms.sec.client.template.newer.AuthenticationRESTTemplate;
import com.archsystemsinc.ipms.sec.model.dto.User;
import com.archsystemsinc.ipms.sec.util.SecurityConstants;
import com.archsystemsinc.ipms.spring.client.ClientTestConfig;
import com.archsystemsinc.ipms.spring.context.ContextTestConfig;
import com.archsystemsinc.ipms.spring.testing.TestingConfig;
import com.archsystemsinc.ipms.test.AbstractRESTIntegrationTest;

@RunWith( SpringJUnit4ClassRunner.class )
@ContextConfiguration( classes = { TestingConfig.class, ClientTestConfig.class, ContextTestConfig.class },loader = AnnotationConfigContextLoader.class )
public class AuthenticationClientRESTIntegrationTest extends AbstractRESTIntegrationTest{
	
	@Autowired private AuthenticationRESTTemplate authenticationRestTemplate;
	
	// tests
	
	// GET
	
	@Test
	public final void whenAuthenticating_then201IsReceived(){
		// When
		final ResponseEntity< User > response = authenticationRestTemplate.authenticate( SecurityConstants.ADMIN_USERNAME, SecurityConstants.ADMIN_PASSWORD );
		
		// Then
		assertThat( response.getStatusCode().value(), is( 201 ) );
	}
	
}
