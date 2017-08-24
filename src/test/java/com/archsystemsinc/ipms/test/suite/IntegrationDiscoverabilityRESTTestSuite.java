package com.archsystemsinc.ipms.test.suite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.archsystemsinc.ipms.sec.web.privilege.PrivilegeDiscoverabilityRESTIntegrationTest;
import com.archsystemsinc.ipms.sec.web.role.RoleDiscoverabilityRESTIntegrationTest;
import com.archsystemsinc.ipms.sec.web.user.UserDiscoverabilityRESTIntegrationTest;
import com.archsystemsinc.ipms.web.root.RootDiscoverabilityRESTIntegrationTest;

@RunWith( Suite.class )
@SuiteClasses( { UserDiscoverabilityRESTIntegrationTest.class, RoleDiscoverabilityRESTIntegrationTest.class, PrivilegeDiscoverabilityRESTIntegrationTest.class, RootDiscoverabilityRESTIntegrationTest.class } )
public final class IntegrationDiscoverabilityRESTTestSuite{
	//
}
