package org.rest.test.suite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import org.rest.common.search.ConstructQueryStringUnitTest;
import org.rest.sec.persistence.service.BusinessCardSearchPersistenceIntegrationTest;
import org.rest.sec.persistence.service.RoleSearchPersistenceIntegrationTest;
import org.rest.sec.web.businesscard.BusinessCardSearchRESTIntegrationTest;
import org.rest.sec.web.role.RoleSearchRESTIntegrationTest;
import org.rest.util.ParseQueryStringUnitTest;

@RunWith(Suite.class)
@SuiteClasses({// @formatter:off
ParseQueryStringUnitTest.class, ConstructQueryStringUnitTest.class,

RoleSearchPersistenceIntegrationTest.class, BusinessCardSearchPersistenceIntegrationTest.class,

RoleSearchRESTIntegrationTest.class, BusinessCardSearchRESTIntegrationTest.class })
// @formatter:on
public final class SearchTestSuite {
    //
}
