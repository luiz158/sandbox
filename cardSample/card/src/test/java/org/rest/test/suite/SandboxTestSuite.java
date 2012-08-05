package org.rest.test.suite;

import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import org.rest.sec.web.role.BusinessToClientLogicRESTIntegrationTest;

@RunWith(Suite.class)
@SuiteClasses({ BusinessToClientLogicRESTIntegrationTest.class })
@Ignore
public final class SandboxTestSuite {
    //
}
