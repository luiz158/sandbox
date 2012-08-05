package org.rest.sec.persistence.service.impl;

import org.apache.commons.lang3.tuple.Triple;
import org.rest.common.persistence.service.AbstractService;
import org.rest.common.search.ClientOperation;
import org.rest.sec.model.BusinessToClient;
import org.rest.sec.persistence.dao.IBusinessToClientJpaDAO;
import org.rest.sec.persistence.service.IBusinessToClientService;
import org.rest.sec.util.SearchUtilSec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BusinessToClientServiceImpl extends AbstractService<BusinessToClient> implements IBusinessToClientService {

    @Autowired
    IBusinessToClientJpaDAO dao;

    public BusinessToClientServiceImpl() {
        super(BusinessToClient.class);
    }

    // API

    // get/find

    @Override
    public BusinessToClient findByName(final String name) {
        return getDao().findByName(name);
    }

    // create

    @Override
    public BusinessToClient create(final BusinessToClient entity) {
        /*
         * final long id = IdUtil.randomPositiveLong(); entity.setId( id );
         */

        /*
         * final List< BusinessCard > associationsTemp = Lists.newArrayList( entity.getPrivileges() ); entity.getPrivileges().clear(); for( final BusinessCard privilege : associationsTemp ){ entity.getPrivileges().add(
         * associationDao.findByName( privilege.getName() ) ); }
         */

        return super.create(entity);
    }

    // Spring

    @Override
    public Specification<BusinessToClient> resolveConstraint(final Triple<String, ClientOperation, String> constraint) {
        return SearchUtilSec.resolveConstraint(constraint, BusinessToClient.class);
    }

    @Override
    protected final IBusinessToClientJpaDAO getDao() {
        return dao;
    }

    @Override
    protected JpaSpecificationExecutor<BusinessToClient> getSpecificationExecutor() {
        return dao;
    }

}
