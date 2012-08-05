package org.rest.sec.persistence.service.impl;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.rest.common.persistence.service.AbstractServiceUnitTest;
import org.rest.common.util.IDUtils;
import org.rest.sec.model.ClientCard;
import org.rest.sec.persistence.dao.IClientCardJpaDAO;
import org.rest.sec.persistence.util.FixtureFactory;
import org.springframework.data.jpa.repository.JpaRepository;

import com.google.common.collect.Lists;

public class ClientCardServiceUnitTest extends AbstractServiceUnitTest<ClientCard> {

    private ClientCardServiceImpl instance;

    private IClientCardJpaDAO daoMock;

    // fixtures

    @Override
    @Before
    public final void before() {
        instance = new ClientCardServiceImpl();

        daoMock = mock(IClientCardJpaDAO.class);
        when(daoMock.save(any(ClientCard.class))).thenReturn(new ClientCard());
        when(daoMock.findAll()).thenReturn(Lists.<ClientCard> newArrayList());
        instance.dao = daoMock;
        super.before();
    }

    // get

    @Test
    public final void whenGetIsTriggered_thenNoException() {
        configureGet(1l);

        // When
        instance.findOne(1l);

        // Then
    }

    @Test
    public final void whenGetIsTriggered_thenEntityIsRetrieved() {
        configureGet(1l);
        // When
        instance.findOne(1l);

        // Then
        verify(daoMock).findOne(1l);
    }

    // mocking behavior

    final ClientCard configureGet(final long id) {
        final ClientCard entity = new ClientCard();
        entity.setId(id);
        when(daoMock.findOne(id)).thenReturn(entity);
        return entity;
    }

    // template method

    @Override
    protected final ClientCardServiceImpl getAPI() {
        return instance;
    }

    @Override
    protected final JpaRepository<ClientCard, Long> getDAO() {
        return daoMock;
    }

    @Override
    protected ClientCard createNewEntity() {
        final ClientCard newEntity = FixtureFactory.createNewClientCard();
        newEntity.setId(IDUtils.randomPositiveLong());
        return newEntity;
    }

    @Override
    protected void changeEntity(final ClientCard entity) {
        entity.setName(randomAlphabetic(8));
    }

}
