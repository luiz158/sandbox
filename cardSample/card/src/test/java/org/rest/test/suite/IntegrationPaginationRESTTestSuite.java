package org.rest.test.suite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import org.rest.sec.web.privilege.BusinessCardPaginationRESTIntegrationTest;
import org.rest.sec.web.role.RolePaginationRESTIntegrationTest;

@RunWith(Suite.class)
@SuiteClasses({ BusinessCardPaginationRESTIntegrationTest.class, RolePaginationRESTIntegrationTest.class })
public final class IntegrationPaginationRESTTestSuite {
    //
}
