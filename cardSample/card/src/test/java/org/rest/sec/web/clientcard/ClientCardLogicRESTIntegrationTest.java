package org.rest.sec.web.clientcard;

import org.rest.common.web.WebConstants;
import org.rest.sec.client.template.ClientCardRESTTemplateImpl;
import org.rest.sec.model.ClientCard;
import org.rest.sec.test.SecLogicRESTIntegrationTest;
import org.springframework.beans.factory.annotation.Autowired;

import com.jayway.restassured.specification.RequestSpecification;

public class ClientCardLogicRESTIntegrationTest extends SecLogicRESTIntegrationTest<ClientCard> {

    @Autowired
    private ClientCardRESTTemplateImpl restTemplate;

    public ClientCardLogicRESTIntegrationTest() {
        super(ClientCard.class);
    }

    // tests

    // template

    @Override
    protected final ClientCardRESTTemplateImpl getAPI() {
        return restTemplate;
    }

    @Override
    protected final String getURI() {
        return getAPI().getURI() + WebConstants.PATH_SEP;
    }

    @Override
    protected final RequestSpecification givenAuthenticated() {
        return getAPI().givenAuthenticated();
    }

}
