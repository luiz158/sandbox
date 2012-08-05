package org.rest.test.suite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import org.rest.sec.web.privilege.PrivilegePaginationRESTIntegrationTest;
import org.rest.sec.web.role.RolePaginationRESTIntegrationTest;

@RunWith(Suite.class)
@SuiteClasses({ PrivilegePaginationRESTIntegrationTest.class, RolePaginationRESTIntegrationTest.class })
public final class IntegrationPaginationRESTTestSuite {
    //
}
