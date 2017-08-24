package com.archsystemsinc.ipms.test.suite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.archsystemsinc.ipms.sec.web.privilege.PrivilegeMimeRESTIntegrationTest;
import com.archsystemsinc.ipms.sec.web.role.RoleMimeRESTIntegrationTest;
import com.archsystemsinc.ipms.sec.web.user.UserMimeRESTIntegrationTest;

@RunWith( Suite.class )
@SuiteClasses( { PrivilegeMimeRESTIntegrationTest.class, RoleMimeRESTIntegrationTest.class, UserMimeRESTIntegrationTest.class } )
public final class IntegrationMimeRESTTestSuite{
	//
}
