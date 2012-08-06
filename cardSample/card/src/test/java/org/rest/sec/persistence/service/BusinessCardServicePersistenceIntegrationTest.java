package org.rest.sec.persistence.service;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.junit.Assert.assertFalse;

import org.junit.Test;
import org.rest.common.persistence.service.IService;
import org.rest.sec.model.BusinessCard;
import org.rest.sec.model.ClientCard;
import org.rest.sec.test.SecPersistenceServiceIntegrationTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import com.google.common.collect.Sets;

public class BusinessCardServicePersistenceIntegrationTest extends SecPersistenceServiceIntegrationTest<BusinessCard> {

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

    // scenarios (in progress)

    @Test
    public void when_thenNoExceptions() {
        final BusinessCard businessCard = bService.create(createNewEntity(randomAlphabetic(8)));
        final ClientCard clientCard = cService.create(new ClientCard(randomAlphabetic(8)));

        businessCard.setClientCards(Sets.newHashSet(clientCard));
        bService.update(businessCard);

        final BusinessCard updatedBusinessCard = bService.findOne(businessCard.getId());
        final ClientCard updatedClientCard = cService.findOne(clientCard.getId());

        assertFalse(updatedBusinessCard.getClientCards().isEmpty());
        assertFalse(updatedClientCard.getBusinessCards().isEmpty());
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
