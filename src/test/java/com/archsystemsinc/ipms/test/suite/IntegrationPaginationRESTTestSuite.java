package com.archsystemsinc.ipms.test.suite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.archsystemsinc.ipms.sec.web.privilege.PrivilegePaginationRESTIntegrationTest;
import com.archsystemsinc.ipms.sec.web.role.RolePaginationRESTIntegrationTest;
import com.archsystemsinc.ipms.sec.web.user.UserPaginationRESTIntegrationTest;

@RunWith( Suite.class )
@SuiteClasses( { PrivilegePaginationRESTIntegrationTest.class, RolePaginationRESTIntegrationTest.class, UserPaginationRESTIntegrationTest.class } )
public final class IntegrationPaginationRESTTestSuite{
	//
}
