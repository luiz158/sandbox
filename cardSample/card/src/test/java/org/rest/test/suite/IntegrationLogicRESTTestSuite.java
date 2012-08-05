package org.rest.test.suite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import org.rest.sec.web.privilege.PrivilegeLogicRESTIntegrationTest;
import org.rest.sec.web.privilege.PrivilegeSearchRESTIntegrationTest;
import org.rest.sec.web.role.RoleLogicRESTIntegrationTest;
import org.rest.sec.web.role.RoleSearchRESTIntegrationTest;

@RunWith(Suite.class)
@SuiteClasses({// @formatter:off
    RoleLogicRESTIntegrationTest.class, 
    PrivilegeLogicRESTIntegrationTest.class,
    RoleSearchRESTIntegrationTest.class, 
    PrivilegeSearchRESTIntegrationTest.class 
 })
// @formatter:off
public final class IntegrationLogicRESTTestSuite {
    //
}
