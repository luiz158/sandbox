package org.rest.test.suite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import org.rest.sec.web.businesscard.BusinessCardSortRESTIntegrationTest;
import org.rest.sec.web.role.BusinessToClientSortRESTIntegrationTest;

@RunWith(Suite.class)
@SuiteClasses({ BusinessCardSortRESTIntegrationTest.class, BusinessToClientSortRESTIntegrationTest.class })
public final class IntegrationSortRESTTestSuite {
    //
}
