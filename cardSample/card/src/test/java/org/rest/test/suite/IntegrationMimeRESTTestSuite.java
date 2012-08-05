package org.rest.test.suite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import org.rest.sec.web.privilege.PrivilegeMimeRESTIntegrationTest;
import org.rest.sec.web.role.RoleMimeRESTIntegrationTest;

@RunWith(Suite.class)
@SuiteClasses({ PrivilegeMimeRESTIntegrationTest.class, RoleMimeRESTIntegrationTest.class })
public final class IntegrationMimeRESTTestSuite {
    //
}
