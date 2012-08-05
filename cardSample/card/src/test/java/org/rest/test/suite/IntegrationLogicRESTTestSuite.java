package org.rest.test.suite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import org.rest.sec.client.template.test.BusinessCardClientRESTIntegrationTest;
import org.rest.sec.client.template.test.ClientCardClientRESTIntegrationTest;
import org.rest.sec.web.businesscard.BusinessCardLogicRESTIntegrationTest;
import org.rest.sec.web.businesscard.BusinessCardSearchRESTIntegrationTest;
import org.rest.sec.web.clientcard.ClientCardLogicRESTIntegrationTest;
import org.rest.sec.web.clientcard.ClientCardSearchRESTIntegrationTest;

@RunWith(Suite.class)
@SuiteClasses({// @formatter:off
    ClientCardLogicRESTIntegrationTest.class,
    ClientCardClientRESTIntegrationTest.class, 
    ClientCardSearchRESTIntegrationTest.class, 
    
    BusinessCardLogicRESTIntegrationTest.class,
    BusinessCardClientRESTIntegrationTest.class, 
    BusinessCardSearchRESTIntegrationTest.class 
 })
// @formatter:off
public final class IntegrationLogicRESTTestSuite {
    //
}
