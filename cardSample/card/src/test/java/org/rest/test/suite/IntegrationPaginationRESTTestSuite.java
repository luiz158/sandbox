package org.rest.test.suite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import org.rest.sec.web.businesscard.BusinessCardPaginationRESTIntegrationTest;
import org.rest.sec.web.clientcard.ClientCardPaginationRESTIntegrationTest;

@RunWith(Suite.class)
@SuiteClasses({ BusinessCardPaginationRESTIntegrationTest.class, ClientCardPaginationRESTIntegrationTest.class })
public final class IntegrationPaginationRESTTestSuite {
    //
}
