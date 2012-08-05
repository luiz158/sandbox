package org.rest.sec.persistence.service;

import org.rest.common.client.IEntityOperations;
import org.rest.sec.model.BusinessToClient;
import org.rest.sec.model.BusinessToClientEntityOpsImpl;
import org.rest.sec.persistence.util.FixtureFactory;
import org.rest.sec.test.SecSearchPersistenceIntegrationTest;
import org.springframework.beans.factory.annotation.Autowired;

public class BusinessToClientSearchPersistenceIntegrationTest extends SecSearchPersistenceIntegrationTest<BusinessToClient> {

    @Autowired
    private IBusinessToClientService roleService;
    @Autowired
    private BusinessToClientEntityOpsImpl entityOps;

    // tests

    // template method

    @Override
    protected final IBusinessToClientService getAPI() {
        return roleService;
    }

    @Override
    protected final BusinessToClient createNewEntity() {
        return FixtureFactory.createNewRole();
    }

    @Override
    protected final IEntityOperations<BusinessToClient> getEntityOperations() {
        return entityOps;
    }

}
