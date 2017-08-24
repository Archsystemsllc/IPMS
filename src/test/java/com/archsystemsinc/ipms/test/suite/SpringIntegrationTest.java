package com.archsystemsinc.ipms.test.suite;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import com.archsystemsinc.ipms.spring.context.ContextTestConfig;
import com.archsystemsinc.ipms.spring.persistence.jpa.PersistenceJPAConfig;
import com.archsystemsinc.ipms.spring.security.SecurityConfig;
import com.archsystemsinc.ipms.spring.testing.TestingConfig;

@RunWith( SpringJUnit4ClassRunner.class )
@ContextConfiguration( classes = { PersistenceJPAConfig.class, SecurityConfig.class, TestingConfig.class, ContextTestConfig.class },loader = AnnotationConfigContextLoader.class )
public final class SpringIntegrationTest{
	
	@Test
	public final void whenSpringContextIsInstantiated_thenNoExceptions(){
		//
	}
	
}
