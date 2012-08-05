package org.rest.sec.web.businesscard;

import org.rest.sec.client.template.BusinessCardRESTTemplateImpl;
import org.rest.sec.model.BusinessCard;
import org.rest.sec.test.SecPaginationRESTIntegrationTest;
import org.springframework.beans.factory.annotation.Autowired;

public class BusinessCardPaginationRESTIntegrationTest extends SecPaginationRESTIntegrationTest<BusinessCard> {

    @Autowired
    private BusinessCardRESTTemplateImpl template;

    // tests

    // template method (shortcuts)

    @Override
    protected final BusinessCard createNewEntity() {
        return template.createNewEntity();
    }

    @Override
    protected final String getURI() {
        return template.getURI();
    }

    @Override
    protected final BusinessCardRESTTemplateImpl getAPI() {
        return template;
    }

}
