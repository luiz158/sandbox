package org.rest.sec.persistence.service.impl;

import org.apache.commons.lang3.tuple.Triple;
import org.rest.common.persistence.service.AbstractService;
import org.rest.common.search.ClientOperation;
import org.rest.sec.model.BusinessCard;
import org.rest.sec.persistence.dao.IPrivilegeJpaDAO;
import org.rest.sec.persistence.service.IPrivilegeService;
import org.rest.sec.util.SearchUtilSec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PrivilegeServiceImpl extends AbstractService<BusinessCard> implements IPrivilegeService {

    @Autowired
    IPrivilegeJpaDAO dao;

    public PrivilegeServiceImpl() {
        super(BusinessCard.class);
    }

    // API

    // find

    @Override
    public BusinessCard findByName(final String name) {
        return getDao().findByName(name);
    }

    // Spring

    @Override
    protected final IPrivilegeJpaDAO getDao() {
        return dao;
    }

    @Override
    public Specification<BusinessCard> resolveConstraint(final Triple<String, ClientOperation, String> constraint) {
        return SearchUtilSec.resolveConstraint(constraint, BusinessCard.class);
    }

    @Override
    protected JpaSpecificationExecutor<BusinessCard> getSpecificationExecutor() {
        return dao;
    }

}
