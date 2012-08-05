package org.rest.sec.persistence.service.impl;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.rest.common.persistence.service.AbstractServiceUnitTest;
import org.rest.sec.model.BusinessToClient;
import org.rest.sec.persistence.dao.IBusinessToClientJpaDAO;
import org.rest.sec.persistence.util.FixtureFactory;
import org.springframework.data.jpa.repository.JpaRepository;

import com.google.common.collect.Lists;

public class BusinessToClientServiceUnitTest extends AbstractServiceUnitTest<BusinessToClient> {

    private BusinessToClientServiceImpl instance;

    private IBusinessToClientJpaDAO daoMock;

    // fixtures

    @Override
    @Before
    public final void before() {
        instance = new BusinessToClientServiceImpl();

        daoMock = mock(IBusinessToClientJpaDAO.class);
        when(daoMock.save(any(BusinessToClient.class))).thenReturn(new BusinessToClient());
        when(daoMock.findAll()).thenReturn(Lists.<BusinessToClient> newArrayList());
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

    final BusinessToClient configureGet(final long id) {
        final BusinessToClient entity = createNewEntity();
        entity.setId(id);
        when(daoMock.findOne(id)).thenReturn(entity);
        return entity;
    }

    // template method

    @Override
    protected final BusinessToClientServiceImpl getAPI() {
        return instance;
    }

    @Override
    protected final JpaRepository<BusinessToClient, Long> getDAO() {
        return daoMock;
    }

    @Override
    protected final BusinessToClient createNewEntity() {
        return FixtureFactory.createNewRole();
    }

    @Override
    protected void changeEntity(final BusinessToClient entity) {
        entity.setName(randomAlphabetic(6));
    }

}
