package org.rest.test.suite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import org.rest.sec.persistence.dao.BusinessCardDAOPersistenceIntegrationTest;
import org.rest.sec.persistence.dao.RoleDAOPersistenceIntegrationTest;
import org.rest.sec.persistence.service.BusinessCardSearchPersistenceIntegrationTest;
import org.rest.sec.persistence.service.BusinessCardServicePersistenceIntegrationTest;
import org.rest.sec.persistence.service.RoleSearchPersistenceIntegrationTest;
import org.rest.sec.persistence.service.RoleServicePersistenceIntegrationTest;

@RunWith(Suite.class)
@SuiteClasses({// @formatter:off
BusinessCardSearchPersistenceIntegrationTest.class, BusinessCardServicePersistenceIntegrationTest.class, BusinessCardDAOPersistenceIntegrationTest.class,

RoleSearchPersistenceIntegrationTest.class, RoleServicePersistenceIntegrationTest.class, RoleDAOPersistenceIntegrationTest.class })
// @formatter:on
public final class IntegrationPersistenceTestSuite {
    //
}
