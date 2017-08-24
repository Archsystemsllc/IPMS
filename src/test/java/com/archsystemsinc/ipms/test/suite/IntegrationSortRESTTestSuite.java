package com.archsystemsinc.ipms.test.suite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.archsystemsinc.ipms.sec.web.privilege.PrivilegeSortRESTIntegrationTest;
import com.archsystemsinc.ipms.sec.web.role.RoleSortRESTIntegrationTest;
import com.archsystemsinc.ipms.sec.web.user.UserSortRESTIntegrationTest;

@RunWith( Suite.class )
@SuiteClasses( { PrivilegeSortRESTIntegrationTest.class, RoleSortRESTIntegrationTest.class, UserSortRESTIntegrationTest.class } )
public final class IntegrationSortRESTTestSuite{
	//
}
