package com.archsystemsinc.ipms.sec.web.role;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import com.archsystemsinc.ipms.client.template.IEntityOperations;
import com.archsystemsinc.ipms.sec.client.template.RoleRESTTemplateImpl;
import com.archsystemsinc.ipms.sec.model.Role;
import com.archsystemsinc.ipms.spring.client.ClientTestConfig;
import com.archsystemsinc.ipms.spring.context.ContextTestConfig;
import com.archsystemsinc.ipms.spring.testing.TestingConfig;
import com.archsystemsinc.ipms.web.common.AbstractSearchRESTIntegrationTest;

@RunWith( SpringJUnit4ClassRunner.class )
@ContextConfiguration( classes = { ClientTestConfig.class, TestingConfig.class, ContextTestConfig.class },loader = AnnotationConfigContextLoader.class )
public class RoleSearchRESTIntegrationTest extends AbstractSearchRESTIntegrationTest< Role >{
	
	@Autowired private RoleRESTTemplateImpl restTemplate;
	
	public RoleSearchRESTIntegrationTest(){
		super();
	}
	
	// tests
	
	// template
	
	@Override
	protected final RoleRESTTemplateImpl getAPI(){
		return restTemplate;
	}
	
	@Override
	protected final IEntityOperations< Role > getEntityOperations(){
		return restTemplate;
	}
	
}
