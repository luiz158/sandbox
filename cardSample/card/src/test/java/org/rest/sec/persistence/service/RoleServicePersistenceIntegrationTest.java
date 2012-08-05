package org.rest.sec.persistence.service;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.hamcrest.Matchers.hasItem;
import static org.junit.Assert.assertThat;

import org.junit.Ignore;
import org.junit.Test;
import org.rest.common.persistence.service.IService;
import org.rest.sec.model.BusinessCard;
import org.rest.sec.model.Role;
import org.rest.sec.test.SecPersistenceServiceIntegrationTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import com.google.common.collect.Sets;

public class RoleServicePersistenceIntegrationTest extends SecPersistenceServiceIntegrationTest<Role> {

    @Autowired
    private IPrivilegeService privilegeService;
    @Autowired
    private IRoleService roleService;

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
        final Role newResource = createNewEntity();
        newResource.getPrivileges().add(existingAssociation);
        getAPI().create(newResource);

        roleService.deleteAll();
        // privilegeService.deleteAll();
    }

    @Test
    public final void whenCreatingNewResourceWithExistingAssociations_thenNewResourceIsCorrectlyCreated() {
        final BusinessCard existingAssociation = getAssociationService().create(new BusinessCard(randomAlphabetic(6)));
        final Role newResource = createNewEntity();
        newResource.getPrivileges().add(existingAssociation);
        getAPI().create(newResource);

        final Role newResource2 = createNewEntity();
        newResource2.getPrivileges().add(existingAssociation);
        getAPI().create(newResource2);
    }

    @Test
    public final void whenScenarioOfWorkingWithAssociations_thenTheChangesAreCorrectlyPersisted() {
        final BusinessCard existingAssociation = getAssociationService().create(new BusinessCard(randomAlphabetic(6)));
        final Role resource1 = new Role(randomAlphabetic(6), Sets.newHashSet(existingAssociation));

        final Role resource1ViewOfServerBefore = getAPI().create(resource1);
        assertThat(resource1ViewOfServerBefore.getPrivileges(), hasItem(existingAssociation));

        final Role resource2 = new Role(randomAlphabetic(6), Sets.newHashSet(existingAssociation));
        getAPI().create(resource2);

        final Role resource1ViewOfServerAfter = getAPI().findOne(resource1ViewOfServerBefore.getId());
        assertThat(resource1ViewOfServerAfter.getPrivileges(), hasItem(existingAssociation));
    }

    // template method

    @Override
    protected final IService<Role> getAPI() {
        return roleService;
    }

    @Override
    protected final Role createNewEntity() {
        return createNewEntity(randomAlphabetic(8));
    }

    @Override
    protected final void invalidate(final Role entity) {
        entity.setName(null);
    }

    @Override
    protected final void changeEntity(final Role entity) {
        entity.setName(randomAlphabetic(6));
    }

    // util

    protected final Role createNewEntity(final String name) {
        return new Role(name, Sets.<BusinessCard> newHashSet());
    }

    final IPrivilegeService getAssociationService() {
        return privilegeService;
    }

}
