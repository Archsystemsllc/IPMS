package com.archsystemsinc.ipms.test.suite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.archsystemsinc.ipms.sec.web.privilege.PrivilegeLogicRESTIntegrationTest;
import com.archsystemsinc.ipms.sec.web.privilege.PrivilegeSearchRESTIntegrationTest;
import com.archsystemsinc.ipms.sec.web.role.RoleLogicRESTIntegrationTest;
import com.archsystemsinc.ipms.sec.web.role.RoleSearchRESTIntegrationTest;
import com.archsystemsinc.ipms.sec.web.user.UserLogicRESTIntegrationTest;
import com.archsystemsinc.ipms.security.AuthenticationRESTIntegrationTest;

@RunWith( Suite.class )
@SuiteClasses( {// @formatter:off
    UserLogicRESTIntegrationTest.class,
    RoleLogicRESTIntegrationTest.class,
    PrivilegeLogicRESTIntegrationTest.class,

    RoleSearchRESTIntegrationTest.class,
    PrivilegeSearchRESTIntegrationTest.class,

    AuthenticationRESTIntegrationTest.class
}) //@formatter:off
public final class IntegrationLogicRESTTestSuite {
    //
}
