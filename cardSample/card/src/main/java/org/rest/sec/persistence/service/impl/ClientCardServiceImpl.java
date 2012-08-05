package org.rest.sec.persistence.service.impl;

import org.apache.commons.lang3.tuple.Triple;
import org.rest.common.persistence.service.AbstractService;
import org.rest.common.search.ClientOperation;
import org.rest.sec.model.ClientCard;
import org.rest.sec.persistence.dao.IClientCardJpaDAO;
import org.rest.sec.persistence.service.IClientCardService;
import org.rest.sec.util.SearchUtilSec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ClientCardServiceImpl extends AbstractService<ClientCard> implements IClientCardService {

    @Autowired
    IClientCardJpaDAO dao;

    public ClientCardServiceImpl() {
        super(ClientCard.class);
    }

    // API

    // find

    @Override
    public ClientCard findByName(final String name) {
        return getDao().findByName(name);
    }

    // Spring

    @Override
    protected final IClientCardJpaDAO getDao() {
        return dao;
    }

    @Override
    public Specification<ClientCard> resolveConstraint(final Triple<String, ClientOperation, String> constraint) {
        return SearchUtilSec.resolveConstraint(constraint, ClientCard.class);
    }

    @Override
    protected JpaSpecificationExecutor<ClientCard> getSpecificationExecutor() {
        return dao;
    }

}
