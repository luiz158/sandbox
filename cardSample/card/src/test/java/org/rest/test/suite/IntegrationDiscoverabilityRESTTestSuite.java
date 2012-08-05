package org.rest.test.suite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import org.rest.sec.web.privilege.BusinessCardDiscoverabilityRESTIntegrationTest;
import org.rest.sec.web.role.RoleDiscoverabilityRESTIntegrationTest;

@RunWith(Suite.class)
@SuiteClasses({ RoleDiscoverabilityRESTIntegrationTest.class, BusinessCardDiscoverabilityRESTIntegrationTest.class })
public final class IntegrationDiscoverabilityRESTTestSuite {
    //
}
