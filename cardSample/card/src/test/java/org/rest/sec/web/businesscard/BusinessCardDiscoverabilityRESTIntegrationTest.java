package org.rest.sec.web.businesscard;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;

import org.rest.sec.client.template.BusinessCardRESTTemplateImpl;
import org.rest.sec.model.BusinessCard;
import org.rest.sec.test.SecDiscoverabilityRESTIntegrationTest;
import org.springframework.beans.factory.annotation.Autowired;

import com.jayway.restassured.specification.RequestSpecification;

public class BusinessCardDiscoverabilityRESTIntegrationTest extends SecDiscoverabilityRESTIntegrationTest<BusinessCard> {

    @Autowired
    private BusinessCardRESTTemplateImpl restTemplate;

    public BusinessCardDiscoverabilityRESTIntegrationTest() {
        super(BusinessCard.class);
    }

    // tests

    // template method

    @Override
    protected final BusinessCard createNewEntity() {
        return restTemplate.createNewEntity();
    }

    @Override
    protected final String getURI() {
        return getAPI().getURI();
    }

    @Override
    protected void change(final BusinessCard resource) {
        resource.setName(randomAlphabetic(6));
    }

    @Override
    protected RequestSpecification givenAuthenticated() {
        return getAPI().givenAuthenticated();
    }

    @Override
    protected final BusinessCardRESTTemplateImpl getAPI() {
        return restTemplate;
    }

}
