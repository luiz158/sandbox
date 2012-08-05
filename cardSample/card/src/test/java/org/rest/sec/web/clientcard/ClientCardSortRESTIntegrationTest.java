package org.rest.sec.web.clientcard;

import org.rest.sec.client.template.ClientCardRESTTemplateImpl;
import org.rest.sec.model.ClientCard;
import org.rest.sec.test.SecSortRESTIntegrationTest;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.collect.Ordering;

public class ClientCardSortRESTIntegrationTest extends SecSortRESTIntegrationTest<ClientCard> {

    @Autowired
    private ClientCardRESTTemplateImpl template;

    // tests

    // template method (shortcuts)

    @Override
    protected final ClientCard createNewEntity() {
        return template.createNewEntity();
    }

    @Override
    protected final String getURI() {
        return template.getURI();
    }

    @Override
    protected final ClientCardRESTTemplateImpl getAPI() {
        return template;
    }

    @Override
    protected final Ordering<ClientCard> getOrdering() {
        return new Ordering<ClientCard>() {
            @Override
            public final int compare(final ClientCard left, final ClientCard right) {
                return left.getName().compareToIgnoreCase(right.getName());
            }
        };
    }

}
