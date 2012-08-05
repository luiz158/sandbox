package org.rest.test.suite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import org.rest.sec.web.businesscard.BusinessCardDiscoverabilityRESTIntegrationTest;
import org.rest.sec.web.clientcard.ClientCardDiscoverabilityRESTIntegrationTest;

@RunWith(Suite.class)
@SuiteClasses({ BusinessCardDiscoverabilityRESTIntegrationTest.class, ClientCardDiscoverabilityRESTIntegrationTest.class })
public final class IntegrationDiscoverabilityRESTTestSuite {
    //
}
