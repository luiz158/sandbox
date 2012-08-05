package org.rest.sec.persistence.service;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Ignore;
import org.junit.Test;
import org.rest.common.persistence.service.IService;
import org.rest.sec.model.BusinessCard;
import org.rest.sec.model.BusinessToClient;
import org.rest.sec.test.SecPersistenceServiceIntegrationTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

public class BusinessToClientServicePersistenceIntegrationTest extends SecPersistenceServiceIntegrationTest<BusinessToClient> {

    @Autowired
    private IBusinessCardService privilegeService;
    @Autowired
    private IBusinessToClientService roleService;

    // create

    @Test
    public void whenSaveIsPerformed_thenNoException() {
        roleService.create(createNewEntity());
    }

    @Test(expected = DataAccessException.class)
    public void whenAUniqueConstraintIsBroken_thenSpringSpecificExceptionIsThrown() {
        final String name = randomAlphabetic(8);

        roleService.create(createNewEntity(name));
        roleService.create(createNewEntity(name));
    }

    // scenario

    /** - known issue: this fails on a H2 database */
    @Test
    @Ignore
    public final void givenEntityExistsWithAssociationScenarios_whenDeletingEverything_thenNoException() {
        final BusinessCard existingAssociation = getAssociationService().create(new BusinessCard(randomAlphabetic(6)));
        final BusinessToClient newResource = createNewEntity();
        newResource.setBusinessCard(existingAssociation);
        getAPI().create(newResource);

        roleService.deleteAll();
        // privilegeService.deleteAll();
    }

    @Test
    public final void whenCreatingNewResourceWithExistingAssociations_thenNewResourceIsCorrectlyCreated() {
        final BusinessCard existingAssociation = getAssociationService().create(new BusinessCard(randomAlphabetic(6)));
        final BusinessToClient newResource = createNewEntity();
        newResource.setBusinessCard(existingAssociation);
        getAPI().create(newResource);

        final BusinessToClient newResource2 = createNewEntity();
        newResource2.setBusinessCard(existingAssociation);
        getAPI().create(newResource2);
    }

    @Test
    public final void whenScenarioOfWorkingWithAssociations_thenTheChangesAreCorrectlyPersisted() {
        final BusinessCard existingAssociation = getAssociationService().create(new BusinessCard(randomAlphabetic(6)));
        final BusinessToClient resource1 = new BusinessToClient(randomAlphabetic(6), existingAssociation);

        final BusinessToClient resource1ViewOfServerBefore = getAPI().create(resource1);
        assertThat(resource1ViewOfServerBefore.getBusinessCard(), equalTo(existingAssociation));

        final BusinessToClient resource2 = new BusinessToClient(randomAlphabetic(6), existingAssociation);
        getAPI().create(resource2);

        final BusinessToClient resource1ViewOfServerAfter = getAPI().findOne(resource1ViewOfServerBefore.getId());
        assertThat(resource1ViewOfServerAfter.getBusinessCard(), equalTo(existingAssociation));
    }

    // template method

    @Override
    protected final IService<BusinessToClient> getAPI() {
        return roleService;
    }

    @Override
    protected final BusinessToClient createNewEntity() {
        return createNewEntity(randomAlphabetic(8));
    }

    @Override
    protected final void invalidate(final BusinessToClient entity) {
        entity.setName(null);
    }

    @Override
    protected final void changeEntity(final BusinessToClient entity) {
        entity.setName(randomAlphabetic(6));
    }

    // util

    protected final BusinessToClient createNewEntity(final String name) {
        return new BusinessToClient(name, null);
    }

    final IBusinessCardService getAssociationService() {
        return privilegeService;
    }

}
