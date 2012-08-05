package org.rest.test.suite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import org.rest.sec.web.businesscard.BusinessCardLogicRESTIntegrationTest;
import org.rest.sec.web.businesscard.BusinessCardSearchRESTIntegrationTest;
import org.rest.sec.web.role.BusinessToClientLogicRESTIntegrationTest;
import org.rest.sec.web.role.BusinessToClientSearchRESTIntegrationTest;

@RunWith(Suite.class)
@SuiteClasses({// @formatter:off
    BusinessToClientLogicRESTIntegrationTest.class, 
    BusinessCardLogicRESTIntegrationTest.class,
    BusinessToClientSearchRESTIntegrationTest.class, 
    BusinessCardSearchRESTIntegrationTest.class 
 })
// @formatter:off
public final class IntegrationLogicRESTTestSuite {
    //
}
