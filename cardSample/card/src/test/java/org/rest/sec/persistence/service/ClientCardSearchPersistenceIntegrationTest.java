package org.rest.sec.persistence.service;

import org.rest.common.client.IEntityOperations;
import org.rest.sec.model.ClientCard;
import org.rest.sec.model.ClientCardEntityOpsImpl;
import org.rest.sec.persistence.util.FixtureFactory;
import org.rest.sec.test.SecSearchPersistenceIntegrationTest;
import org.springframework.beans.factory.annotation.Autowired;

public class ClientCardSearchPersistenceIntegrationTest extends SecSearchPersistenceIntegrationTest<ClientCard> {

    @Autowired
    private IClientCardService service;
    @Autowired
    private ClientCardEntityOpsImpl entityOps;

    // tests

    // template method

    @Override
    protected final IClientCardService getAPI() {
        return service;
    }

    @Override
    protected final ClientCard createNewEntity() {
        return FixtureFactory.createNewClientCard();
    }

    @Override
    protected final IEntityOperations<ClientCard> getEntityOperations() {
        return entityOps;
    }

}
