package org.rest.sec.web.role;

import org.rest.sec.client.template.BusinessToClientRESTTemplateImpl;
import org.rest.sec.model.BusinessToClient;
import org.rest.sec.test.SecPaginationRESTIntegrationTest;
import org.springframework.beans.factory.annotation.Autowired;

public class BusinessToClientPaginationRESTIntegrationTest extends SecPaginationRESTIntegrationTest<BusinessToClient> {

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

}
