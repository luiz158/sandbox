package org.rest.sec.web.privilege;

import org.rest.sec.client.template.PrivilegeRESTTemplateImpl;
import org.rest.sec.model.BusinessCard;
import org.rest.sec.test.SecMimeRESTIntegrationTest;
import org.springframework.beans.factory.annotation.Autowired;

public class PrivilegeMimeRESTIntegrationTest extends SecMimeRESTIntegrationTest<BusinessCard> {

    @Autowired
    private PrivilegeRESTTemplateImpl restTemplate;

    public PrivilegeMimeRESTIntegrationTest() {
        super(BusinessCard.class);
    }

    // tests

    // template method

    @Override
    protected final PrivilegeRESTTemplateImpl getAPI() {
        return restTemplate;
    }

}
