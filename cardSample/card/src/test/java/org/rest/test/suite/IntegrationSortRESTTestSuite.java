package org.rest.test.suite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import org.rest.sec.web.privilege.PrivilegeSortRESTIntegrationTest;
import org.rest.sec.web.role.RoleSortRESTIntegrationTest;

@RunWith(Suite.class)
@SuiteClasses({ PrivilegeSortRESTIntegrationTest.class, RoleSortRESTIntegrationTest.class })
public final class IntegrationSortRESTTestSuite {
    //
}
