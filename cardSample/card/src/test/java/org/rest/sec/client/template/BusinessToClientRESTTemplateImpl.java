package org.rest.sec.client.template;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;

import org.rest.common.client.template.AbstractRESTTemplate;
import org.rest.sec.client.SecBusinessPaths;
import org.rest.sec.model.BusinessCard;
import org.rest.sec.model.BusinessToClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.google.common.collect.Sets;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.specification.RequestSpecification;

@Component
@Profile("client")
public final class BusinessToClientRESTTemplateImpl extends AbstractRESTTemplate<BusinessToClient> {

    @Autowired
    protected SecBusinessPaths paths;

    public BusinessToClientRESTTemplateImpl() {
        super(BusinessToClient.class);
    }

    // API

    public final BusinessToClient findByName(final String name) {
        final String resourceAsXML = findOneAsMime(getURI() + "?name=" + name);
        return marshaller.decode(resourceAsXML, clazz);
    }

    // template method

    @Override
    public final String getURI() {
        return paths.getBusinessToClientStringUri();
    }

    @Override
    public final RequestSpecification givenAuthenticated() {
        return RestAssured.given();
    }

    @Override
    public final BusinessToClient createNewEntity() {
        return new BusinessToClient(randomAlphabetic(8), Sets.<BusinessCard> newHashSet());
    }

    @Override
    public final void invalidate(final BusinessToClient entity) {
        entity.setName(null);
    }

    @Override
    public final void change(final BusinessToClient resource) {
        resource.setName(randomAlphabetic(8));
    }

}
