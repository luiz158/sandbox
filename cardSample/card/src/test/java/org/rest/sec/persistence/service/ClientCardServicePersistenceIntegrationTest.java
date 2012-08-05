package org.rest.sec.persistence.service;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;

import org.junit.Test;
import org.rest.common.persistence.service.IService;
import org.rest.sec.model.ClientCard;
import org.rest.sec.test.SecPersistenceServiceIntegrationTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

public class ClientCardServicePersistenceIntegrationTest extends SecPersistenceServiceIntegrationTest<ClientCard> {

    @Autowired
    private IClientCardService service;
    @Autowired
    IBusinessToClientService roleService;

    // create

    @Test
    public void whenSaveIsPerformed_thenNoException() {
        service.create(createNewEntity());
    }

    @Test(expected = DataAccessException.class)
    public void whenAUniqueConstraintIsBroken_thenSpringSpecificExceptionIsThrown() {
        final String name = randomAlphabetic(8);

        service.create(createNewEntity(name));
        service.create(createNewEntity(name));
    }

    // template method

    @Override
    protected final IService<ClientCard> getAPI() {
        return service;
    }

    @Override
    protected final ClientCard createNewEntity() {
        return new ClientCard(randomAlphabetic(8));
    }

    @Override
    protected final void invalidate(final ClientCard entity) {
        entity.setName(null);
    }

    @Override
    protected final void changeEntity(final ClientCard entity) {
        entity.setName(randomAlphabetic(6));
    }

    // util

    protected final ClientCard createNewEntity(final String name) {
        return new ClientCard(name);
    }

}
