package com.archsystemsinc.ipms.sec.web.privilege;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import com.archsystemsinc.ipms.client.template.IEntityOperations;
import com.archsystemsinc.ipms.sec.client.template.PrivilegeRESTTemplateImpl;
import com.archsystemsinc.ipms.sec.model.Privilege;
import com.archsystemsinc.ipms.spring.client.ClientTestConfig;
import com.archsystemsinc.ipms.spring.context.ContextTestConfig;
import com.archsystemsinc.ipms.spring.testing.TestingConfig;
import com.archsystemsinc.ipms.web.common.AbstractSearchRESTIntegrationTest;

@RunWith( SpringJUnit4ClassRunner.class )
@ContextConfiguration( classes = { ClientTestConfig.class, TestingConfig.class, ContextTestConfig.class },loader = AnnotationConfigContextLoader.class )
public class PrivilegeSearchRESTIntegrationTest extends AbstractSearchRESTIntegrationTest< Privilege >{
	
	@Autowired private PrivilegeRESTTemplateImpl restTemplate;
	
	public PrivilegeSearchRESTIntegrationTest(){
		super();
	}
	
	// tests
	
	// template
	
	@Override
	protected final PrivilegeRESTTemplateImpl getAPI(){
		return restTemplate;
	}
	
	@Override
	protected final IEntityOperations< Privilege > getEntityOperations(){
		return restTemplate;
	}
	
}
