package org.rest.sec.persistence.dao;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.rest.sec.model.BusinessToClient;
import org.rest.sec.test.SecPersistenceDAOIntegrationTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

@TransactionConfiguration(defaultRollback = true)
@Transactional
public class BusinessToClientDAOPersistenceIntegrationTest extends SecPersistenceDAOIntegrationTest<BusinessToClient> {

    @Autowired
    IBusinessCardJpaDAO associationDao;
    @Autowired
    private IBusinessToClientJpaDAO dao;

    // find by

    @Test
    public void givenEntityDoesNotExist_whenFindingEntityByName_thenEntityNotFound() {
        // Given
        final String name = randomAlphabetic(8);

        // When
        final BusinessToClient entityByName = getDAOCasted().findByName(name);

        // Then
        assertNull(entityByName);
    }

    // template method

    @Override
    protected final JpaRepository<BusinessToClient, Long> getAPI() {
        return dao;
    }

    @Override
    protected final BusinessToClient createNewEntity() {
        final BusinessToClient entity = new BusinessToClient(randomAlphabetic(8));
        return entity;
    }

    @Override
    protected final void invalidate(final BusinessToClient entity) {
        entity.setName(null);
    }

    @Override
    protected final void changeEntity(final BusinessToClient entity) {
        entity.setName(randomAlphabetic(6));
    }

    //

    protected final IBusinessToClientJpaDAO getDAOCasted() {
        return dao;
    }

}
