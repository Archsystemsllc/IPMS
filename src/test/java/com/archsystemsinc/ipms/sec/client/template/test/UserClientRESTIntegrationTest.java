package com.archsystemsinc.ipms.sec.client.template.test;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import com.archsystemsinc.ipms.client.AbstractClientRESTIntegrationTest;
import com.archsystemsinc.ipms.client.template.IEntityOperations;
import com.archsystemsinc.ipms.sec.client.template.UserRESTTemplateImpl;
import com.archsystemsinc.ipms.sec.client.template.newer.UserClientRESTTemplate;
import com.archsystemsinc.ipms.sec.model.dto.User;
import com.archsystemsinc.ipms.spring.client.ClientTestConfig;
import com.archsystemsinc.ipms.spring.context.ContextTestConfig;
import com.archsystemsinc.ipms.spring.testing.TestingConfig;

@RunWith( SpringJUnit4ClassRunner.class )
@ContextConfiguration( classes = { ContextTestConfig.class, TestingConfig.class, ClientTestConfig.class },loader = AnnotationConfigContextLoader.class )
public class UserClientRESTIntegrationTest extends AbstractClientRESTIntegrationTest< User >{
	
	@Autowired private UserClientRESTTemplate userClientTemplate;
	@Autowired private UserRESTTemplateImpl userEntityOps;
	
	public UserClientRESTIntegrationTest(){
		super();
	}
	
	// tests
	
	// template method
	
	@Override
	protected final UserClientRESTTemplate getAPI(){
		return userClientTemplate;
	}
	
	@Override
	protected final IEntityOperations< User > getEntityOps(){
		return userEntityOps;
	}
	
}
