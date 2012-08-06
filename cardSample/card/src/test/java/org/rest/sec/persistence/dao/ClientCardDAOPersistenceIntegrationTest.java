package org.rest.sec.persistence.dao;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.rest.sec.model.ClientCard;
import org.rest.sec.test.SecDAOPersistenceIntegrationTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

@TransactionConfiguration(defaultRollback = true)
@Transactional
public class ClientCardDAOPersistenceIntegrationTest extends SecDAOPersistenceIntegrationTest<ClientCard> {

    @Autowired
    private IClientCardJpaDAO dao;

    // save

    @Test
    public void whenSaveIsPerformed_thenNoException() {
        getAPI().save(createNewEntity());
    }

    // find by

    @Test
    public void givenEntityDoesNotExist_whenFindingEntityByName_thenEntityNotFound() {
        // Given
        final String name = randomAlphabetic(8);

        // When
        final ClientCard entityByName = getDAOCasted().findByName(name);

        // Then
        assertNull(entityByName);
    }

    // template method

    @Override
    protected final JpaRepository<ClientCard, Long> getAPI() {
        return dao;
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

    //

    protected final IClientCardJpaDAO getDAOCasted() {
        return dao;
    }

}
