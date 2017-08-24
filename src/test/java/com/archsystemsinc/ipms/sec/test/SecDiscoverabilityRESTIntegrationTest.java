package com.archsystemsinc.ipms.sec.test;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import com.archsystemsinc.ipms.common.IEntity;
import com.archsystemsinc.ipms.spring.client.ClientTestConfig;
import com.archsystemsinc.ipms.spring.context.ContextTestConfig;
import com.archsystemsinc.ipms.spring.testing.TestingConfig;
import com.archsystemsinc.ipms.web.common.AbstractDiscoverabilityRESTIntegrationTest;

@RunWith( SpringJUnit4ClassRunner.class )
@ContextConfiguration( classes = { ClientTestConfig.class, TestingConfig.class, ContextTestConfig.class },loader = AnnotationConfigContextLoader.class )
public abstract class SecDiscoverabilityRESTIntegrationTest< T extends IEntity > extends AbstractDiscoverabilityRESTIntegrationTest< T >{
	
	public SecDiscoverabilityRESTIntegrationTest( final Class< T > clazzToSet ){
		super( clazzToSet );
	}
	
}
