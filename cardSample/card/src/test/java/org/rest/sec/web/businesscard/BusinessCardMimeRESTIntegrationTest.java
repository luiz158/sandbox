package org.rest.sec.web.businesscard;

import org.rest.sec.client.template.BusinessCardRESTTemplateImpl;
import org.rest.sec.model.BusinessCard;
import org.rest.sec.test.SecMimeRESTIntegrationTest;
import org.springframework.beans.factory.annotation.Autowired;

public class BusinessCardMimeRESTIntegrationTest extends SecMimeRESTIntegrationTest<BusinessCard> {

    @Autowired
    private BusinessCardRESTTemplateImpl restTemplate;

    public BusinessCardMimeRESTIntegrationTest() {
        super(BusinessCard.class);
    }

    // tests

    // template method

    @Override
    protected final BusinessCardRESTTemplateImpl getAPI() {
        return restTemplate;
    }

}
