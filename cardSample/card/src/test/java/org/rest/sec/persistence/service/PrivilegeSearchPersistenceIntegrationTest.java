package org.rest.sec.persistence.service;

import org.rest.common.client.IEntityOperations;
import org.rest.sec.model.BusinessCard;
import org.rest.sec.model.PrivilegeEntityOpsImpl;
import org.rest.sec.persistence.util.FixtureFactory;
import org.rest.sec.test.SecSearchPersistenceIntegrationTest;
import org.springframework.beans.factory.annotation.Autowired;

public class PrivilegeSearchPersistenceIntegrationTest extends SecSearchPersistenceIntegrationTest<BusinessCard> {

    @Autowired
    private IPrivilegeService privilegeService;
    @Autowired
    private PrivilegeEntityOpsImpl entityOps;

    // tests

    // template method

    @Override
    protected final IPrivilegeService getAPI() {
        return privilegeService;
    }

    @Override
    protected final BusinessCard createNewEntity() {
        return FixtureFactory.createNewPrivilege();
    }

    @Override
    protected final IEntityOperations<BusinessCard> getEntityOperations() {
        return entityOps;
    }

}
