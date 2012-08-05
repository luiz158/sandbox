package org.rest.sec.web.role;

import org.rest.sec.client.template.BusinessToClientRESTTemplateImpl;
import org.rest.sec.model.BusinessToClient;
import org.rest.sec.test.SecMimeRESTIntegrationTest;
import org.springframework.beans.factory.annotation.Autowired;

public class BusinessToClientMimeRESTIntegrationTest extends SecMimeRESTIntegrationTest<BusinessToClient> {

    @Autowired
    private BusinessToClientRESTTemplateImpl restTemplate;

    public BusinessToClientMimeRESTIntegrationTest() {
        super(BusinessToClient.class);
    }

    // tests

    // template method

    @Override
    protected final BusinessToClientRESTTemplateImpl getAPI() {
        return restTemplate;
    }

}
