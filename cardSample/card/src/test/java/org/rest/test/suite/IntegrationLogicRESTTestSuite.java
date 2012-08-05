package org.rest.test.suite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import org.rest.sec.web.businesscard.BusinessCardLogicRESTIntegrationTest;
import org.rest.sec.web.businesscard.BusinessCardSearchRESTIntegrationTest;
import org.rest.sec.web.clientcard.ClientCardLogicRESTIntegrationTest;
import org.rest.sec.web.clientcard.ClientCardSearchRESTIntegrationTest;
import org.rest.sec.web.role.BusinessToClientLogicRESTIntegrationTest;
import org.rest.sec.web.role.BusinessToClientSearchRESTIntegrationTest;

@RunWith(Suite.class)
@SuiteClasses({// @formatter:off
    BusinessToClientLogicRESTIntegrationTest.class, 
    BusinessToClientSearchRESTIntegrationTest.class, 

    ClientCardLogicRESTIntegrationTest.class,
    ClientCardSearchRESTIntegrationTest.class, 
    
    BusinessCardLogicRESTIntegrationTest.class,
    BusinessCardSearchRESTIntegrationTest.class 
 })
// @formatter:off
public final class IntegrationLogicRESTTestSuite {
    //
}
