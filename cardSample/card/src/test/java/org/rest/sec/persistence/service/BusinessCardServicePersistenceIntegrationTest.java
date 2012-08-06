package org.rest.sec.persistence.service;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;

import org.junit.Test;
import org.rest.common.persistence.service.IService;
import org.rest.sec.model.BusinessCard;
import org.rest.sec.test.SecServicePersistenceIntegrationTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

public class BusinessCardServicePersistenceIntegrationTest extends SecServicePersistenceIntegrationTest<BusinessCard> {

    @Autowired
    private IBusinessCardService bService;

    @Autowired
    private IClientCardService cService;

    // create

    @Test
    public void whenSaveIsPerformed_thenNoException() {
        bService.create(createNewEntity());
    }

    @Test(expected = DataAccessException.class)
    public void whenAUniqueConstraintIsBroken_thenSpringSpecificExceptionIsThrown() {
        final String name = randomAlphabetic(8);

        bService.create(createNewEntity(name));
        bService.create(createNewEntity(name));
    }

    // template method

    @Override
    protected final IService<BusinessCard> getAPI() {
        return bService;
    }

    @Override
    protected final BusinessCard createNewEntity() {
        return new BusinessCard(randomAlphabetic(8));
    }

    @Override
    protected final void invalidate(final BusinessCard entity) {
        entity.setName(null);
    }

    @Override
    protected final void changeEntity(final BusinessCard entity) {
        entity.setName(randomAlphabetic(6));
    }

    // util

    protected final BusinessCard createNewEntity(final String name) {
        return new BusinessCard(name);
    }

}
