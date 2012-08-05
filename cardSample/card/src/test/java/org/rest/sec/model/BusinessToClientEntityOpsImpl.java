package org.rest.sec.model;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;

import org.rest.common.client.IEntityOperations;
import org.springframework.stereotype.Component;

@Component
public final class BusinessToClientEntityOpsImpl implements IEntityOperations<BusinessToClient> {

    public BusinessToClientEntityOpsImpl() {
        super();
    }

    // API

    @Override
    public final BusinessToClient createNewEntity() {
        return new BusinessToClient(randomAlphabetic(8), null);
    }

    @Override
    public final void invalidate(final BusinessToClient entity) {
        entity.setName(null);
    }

    @Override
    public final void change(final BusinessToClient resource) {
        resource.setName(randomAlphabetic(8));
    }

}
