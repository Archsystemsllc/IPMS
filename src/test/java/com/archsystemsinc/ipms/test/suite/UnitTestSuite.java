package com.archsystemsinc.ipms.test.suite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.archsystemsinc.ipms.sec.persistence.service.impl.PrincipalServiceUnitTest;
import com.archsystemsinc.ipms.sec.persistence.service.impl.PrivilegeServiceUnitTest;
import com.archsystemsinc.ipms.sec.persistence.service.impl.RoleServiceUnitTest;
import com.archsystemsinc.ipms.sec.util.ConstructQueryStringUnitTest;
import com.archsystemsinc.ipms.util.ParseQueryStringUnitTest;

@RunWith( Suite.class )
@SuiteClasses( { PrincipalServiceUnitTest.class, RoleServiceUnitTest.class, PrivilegeServiceUnitTest.class, ParseQueryStringUnitTest.class, ConstructQueryStringUnitTest.class } )
public final class UnitTestSuite{
	//
}
