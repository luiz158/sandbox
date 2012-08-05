package org.rest.test.suite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import org.rest.common.search.ConstructQueryStringUnitTest;
import org.rest.sec.persistence.service.BusinessCardSearchPersistenceIntegrationTest;
import org.rest.sec.persistence.service.ClientCardSearchPersistenceIntegrationTest;
import org.rest.sec.web.businesscard.BusinessCardSearchRESTIntegrationTest;
import org.rest.sec.web.clientcard.ClientCardSearchRESTIntegrationTest;
import org.rest.util.ParseQueryStringUnitTest;

@RunWith(Suite.class)
@SuiteClasses({// @formatter:off
    ParseQueryStringUnitTest.class, 
    ConstructQueryStringUnitTest.class,
    BusinessCardSearchPersistenceIntegrationTest.class,
    ClientCardSearchPersistenceIntegrationTest.class,
    BusinessCardSearchRESTIntegrationTest.class, 
    ClientCardSearchRESTIntegrationTest.class
})
// @formatter:on
public final class SearchTestSuite {
    //
}
