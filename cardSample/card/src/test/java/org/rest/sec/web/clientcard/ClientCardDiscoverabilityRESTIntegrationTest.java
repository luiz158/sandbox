package org.rest.sec.web.clientcard;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;

import org.rest.sec.client.template.ClientCardRESTTemplateImpl;
import org.rest.sec.model.ClientCard;
import org.rest.sec.test.SecDiscoverabilityRESTIntegrationTest;
import org.springframework.beans.factory.annotation.Autowired;

import com.jayway.restassured.specification.RequestSpecification;

public class ClientCardDiscoverabilityRESTIntegrationTest extends SecDiscoverabilityRESTIntegrationTest<ClientCard> {

    @Autowired
    private ClientCardRESTTemplateImpl restTemplate;

    public ClientCardDiscoverabilityRESTIntegrationTest() {
        super(ClientCard.class);
    }

    // tests

    // template method

    @Override
    protected final ClientCard createNewEntity() {
        return restTemplate.createNewEntity();
    }

    @Override
    protected final String getURI() {
        return getAPI().getURI();
    }

    @Override
    protected void change(final ClientCard resource) {
        resource.setName(randomAlphabetic(6));
    }

    @Override
    protected RequestSpecification givenAuthenticated() {
        return getAPI().givenAuthenticated();
    }

    @Override
    protected final ClientCardRESTTemplateImpl getAPI() {
        return restTemplate;
    }

}
