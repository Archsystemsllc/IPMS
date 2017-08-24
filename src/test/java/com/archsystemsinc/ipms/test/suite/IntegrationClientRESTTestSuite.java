package com.archsystemsinc.ipms.test.suite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.archsystemsinc.ipms.sec.client.template.test.AuthenticationClientRESTIntegrationTest;
import com.archsystemsinc.ipms.sec.client.template.test.UserClientRESTIntegrationTest;

@RunWith( Suite.class )
@SuiteClasses( { UserClientRESTIntegrationTest.class, AuthenticationClientRESTIntegrationTest.class } )
public final class IntegrationClientRESTTestSuite{
	//
}
