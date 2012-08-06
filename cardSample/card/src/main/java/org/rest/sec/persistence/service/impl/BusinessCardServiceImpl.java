package org.rest.sec.persistence.service.impl;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;

import java.util.List;

import org.apache.commons.lang3.tuple.Triple;
import org.rest.common.persistence.service.AbstractService;
import org.rest.common.search.ClientOperation;
import org.rest.sec.model.BusinessCard;
import org.rest.sec.model.ClientCard;
import org.rest.sec.persistence.dao.IBusinessCardJpaDAO;
import org.rest.sec.persistence.service.IBusinessCardService;
import org.rest.sec.persistence.service.IClientCardService;
import org.rest.sec.util.SearchUtilSec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;

@Service
@Transactional
public class BusinessCardServiceImpl extends AbstractService<BusinessCard> implements IBusinessCardService {

    @Autowired
    IBusinessCardJpaDAO dao;

    @Autowired
    IClientCardService clientCardService;

    public BusinessCardServiceImpl() {
        super(BusinessCard.class);
    }

    // API

    @Override
    public void notification() {
        final ClientCard existingClientCard = clientCardService.create(new ClientCard(randomAlphabetic(6)));
        BusinessCard existingBusinessCard = dao.findByName("BusinessCard1");
        existingBusinessCard.getClientCards().add(existingClientCard);
        dao.save(existingBusinessCard);
    }

    // find

    @Override
    public BusinessCard findByName(final String name) {
        return getDao().findByName(name);
    }

    @Override
    public List<BusinessCard> findAllByAssociation(final Long idOfClientCard) {
        final ClientCard clientCard = clientCardService.findOne(idOfClientCard);
        return Lists.newArrayList(clientCard.getBusinessCards());
    }

    // Spring

    @Override
    protected final IBusinessCardJpaDAO getDao() {
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
