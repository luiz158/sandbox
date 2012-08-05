package org.rest.sec.web.role;

import org.rest.common.util.order.OrderByName;
import org.rest.sec.client.template.BusinessToClientRESTTemplateImpl;
import org.rest.sec.model.BusinessToClient;
import org.rest.sec.test.SecSortRESTIntegrationTest;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.collect.Ordering;

public class BusinessToClientSortRESTIntegrationTest extends SecSortRESTIntegrationTest<BusinessToClient> {

    @Autowired
    private BusinessToClientRESTTemplateImpl template;

    // tests

    // template method (shortcuts)

    @Override
    protected final BusinessToClient createNewEntity() {
        return template.createNewEntity();
    }

    @Override
    protected final String getURI() {
        return template.getURI();
    }

    @Override
    protected final BusinessToClientRESTTemplateImpl getAPI() {
        return template;
    }

    @Override
    protected final Ordering<BusinessToClient> getOrdering() {
        return new OrderByName<BusinessToClient>();
    }

}
