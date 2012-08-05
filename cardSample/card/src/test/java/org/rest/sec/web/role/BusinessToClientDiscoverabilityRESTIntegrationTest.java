package org.rest.sec.web.role;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;

import org.rest.sec.client.template.BusinessToClientRESTTemplateImpl;
import org.rest.sec.model.BusinessToClient;
import org.rest.sec.test.SecDiscoverabilityRESTIntegrationTest;
import org.springframework.beans.factory.annotation.Autowired;

import com.jayway.restassured.specification.RequestSpecification;

public class BusinessToClientDiscoverabilityRESTIntegrationTest extends SecDiscoverabilityRESTIntegrationTest<BusinessToClient> {

    @Autowired
    private BusinessToClientRESTTemplateImpl restTemplate;

    public BusinessToClientDiscoverabilityRESTIntegrationTest() {
        super(BusinessToClient.class);
    }

    // tests

    // template method

    @Override
    protected final BusinessToClient createNewEntity() {
        return restTemplate.createNewEntity();
    }

    @Override
    protected final String getURI() {
        return getAPI().getURI();
    }

    @Override
    protected void change(final BusinessToClient resource) {
        resource.setName(randomAlphabetic(6));
    }

    @Override
    protected RequestSpecification givenAuthenticated() {
        return getAPI().givenAuthenticated();
    }

    @Override
    protected final BusinessToClientRESTTemplateImpl getAPI() {
        return restTemplate;
    }

}
