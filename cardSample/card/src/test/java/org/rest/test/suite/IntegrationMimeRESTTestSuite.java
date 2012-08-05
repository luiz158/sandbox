package org.rest.test.suite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import org.rest.sec.web.businesscard.BusinessCardMimeRESTIntegrationTest;
import org.rest.sec.web.role.BusinessToClientMimeRESTIntegrationTest;

@RunWith(Suite.class)
@SuiteClasses({ BusinessCardMimeRESTIntegrationTest.class, BusinessToClientMimeRESTIntegrationTest.class })
public final class IntegrationMimeRESTTestSuite {
    //
}
