package org.rest.test.suite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import org.rest.sec.persistence.dao.BusinessCardDAOPersistenceIntegrationTest;
import org.rest.sec.persistence.dao.BusinessToClientDAOPersistenceIntegrationTest;
import org.rest.sec.persistence.dao.ClientCardDAOPersistenceIntegrationTest;
import org.rest.sec.persistence.service.BusinessCardSearchPersistenceIntegrationTest;
import org.rest.sec.persistence.service.BusinessCardServicePersistenceIntegrationTest;
import org.rest.sec.persistence.service.BusinessToClientSearchPersistenceIntegrationTest;
import org.rest.sec.persistence.service.BusinessToClientServicePersistenceIntegrationTest;
import org.rest.sec.persistence.service.ClientCardSearchPersistenceIntegrationTest;
import org.rest.sec.persistence.service.ClientCardServicePersistenceIntegrationTest;

@RunWith(Suite.class)
@SuiteClasses({// @formatter:off
    BusinessCardSearchPersistenceIntegrationTest.class, 
    BusinessCardServicePersistenceIntegrationTest.class, 
    BusinessCardDAOPersistenceIntegrationTest.class,
    
    ClientCardSearchPersistenceIntegrationTest.class, 
    ClientCardServicePersistenceIntegrationTest.class, 
    ClientCardDAOPersistenceIntegrationTest.class,
    
    BusinessToClientSearchPersistenceIntegrationTest.class, 
    BusinessToClientServicePersistenceIntegrationTest.class, 
    BusinessToClientDAOPersistenceIntegrationTest.class })
// @formatter:on
public final class IntegrationPersistenceTestSuite {
    //
}
