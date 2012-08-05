package org.rest.sec.client.template;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;

import org.rest.common.client.template.AbstractRESTTemplate;
import org.rest.sec.client.SecBusinessPaths;
import org.rest.sec.model.BusinessCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.specification.RequestSpecification;

@Component
@Profile("client")
public final class BusinessCardRESTTemplateImpl extends AbstractRESTTemplate<BusinessCard> {

    @Autowired
    protected SecBusinessPaths paths;

    public BusinessCardRESTTemplateImpl() {
        super(BusinessCard.class);
    }

    // template method

    @Override
    public final String getURI() {
        return paths.getPrivilegeUri();
    }

    @Override
    public final RequestSpecification givenAuthenticated() {
        return RestAssured.given();
    }

    @Override
    public final BusinessCard createNewEntity() {
        return new BusinessCard(randomAlphabetic(8));
    }

    @Override
    public final void invalidate(final BusinessCard entity) {
        entity.setName(null);
    }

    @Override
    public void change(final BusinessCard resource) {
        resource.setName(randomAlphabetic(8));
    }

}
