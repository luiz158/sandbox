package org.rest.sec.model;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;

import org.rest.common.client.IEntityOperations;
import org.springframework.stereotype.Component;

@Component
public final class ClientCardEntityOpsImpl implements IEntityOperations<ClientCard> {

    public ClientCardEntityOpsImpl() {
        super();
    }

    // template method

    @Override
    public final ClientCard createNewEntity() {
        return new ClientCard(randomAlphabetic(8));
    }

    @Override
    public final void invalidate(final ClientCard entity) {
        entity.setName(null);
    }

    @Override
    public void change(final ClientCard resource) {
        resource.setName(randomAlphabetic(8));
    }

}
