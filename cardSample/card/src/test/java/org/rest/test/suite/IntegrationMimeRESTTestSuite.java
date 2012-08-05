package org.rest.test.suite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import org.rest.sec.web.businesscard.BusinessCardMimeRESTIntegrationTest;
import org.rest.sec.web.role.RoleMimeRESTIntegrationTest;

@RunWith(Suite.class)
@SuiteClasses({ BusinessCardMimeRESTIntegrationTest.class, RoleMimeRESTIntegrationTest.class })
public final class IntegrationMimeRESTTestSuite {
    //
}
