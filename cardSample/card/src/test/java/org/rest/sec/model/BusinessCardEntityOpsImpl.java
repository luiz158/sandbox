package org.rest.sec.model;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;

import org.rest.common.client.IEntityOperations;
import org.rest.sec.model.BusinessCard;
import org.springframework.stereotype.Component;

@Component
public final class BusinessCardEntityOpsImpl implements IEntityOperations<BusinessCard> {

    public BusinessCardEntityOpsImpl() {
        super();
    }

    // template method

    @Override
    public final BusinessCard createNewEntity() {
        return new BusinessCard(randomAlphabetic(8));
    }

    @Override
    public final void invalidate(final BusinessCard entity) {
        entity.setName(null);
    }

    @Override
    public void change(final BusinessCard resource) {
        resource.setName(randomAlphabetic(8));
    }

}
