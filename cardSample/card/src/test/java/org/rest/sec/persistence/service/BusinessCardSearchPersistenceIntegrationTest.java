package org.rest.sec.persistence.service;

import org.rest.common.client.IEntityOperations;
import org.rest.sec.model.BusinessCard;
import org.rest.sec.model.BusinessCardEntityOpsImpl;
import org.rest.sec.persistence.util.FixtureFactory;
import org.rest.sec.test.SecSearchPersistenceIntegrationTest;
import org.springframework.beans.factory.annotation.Autowired;

public class BusinessCardSearchPersistenceIntegrationTest extends SecSearchPersistenceIntegrationTest<BusinessCard> {

    @Autowired
    private IBusinessCardService service;
    @Autowired
    private BusinessCardEntityOpsImpl entityOps;

    // tests

    // template method

    @Override
    protected final IBusinessCardService getAPI() {
        return service;
    }

    @Override
    protected final BusinessCard createNewEntity() {
        return FixtureFactory.createNewBusinessCard();
    }

    @Override
    protected final IEntityOperations<BusinessCard> getEntityOperations() {
        return entityOps;
    }

}
