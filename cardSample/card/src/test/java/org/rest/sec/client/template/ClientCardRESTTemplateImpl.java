package org.rest.sec.client.template;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;

import org.rest.common.client.template.AbstractRESTTemplate;
import org.rest.sec.client.SecBusinessPaths;
import org.rest.sec.model.ClientCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.specification.RequestSpecification;

@Component
@Profile("client")
public final class ClientCardRESTTemplateImpl extends AbstractRESTTemplate<ClientCard> {

    @Autowired
    protected SecBusinessPaths paths;

    public ClientCardRESTTemplateImpl() {
        super(ClientCard.class);
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
    public final ClientCard createNewEntity() {
        return new ClientCard(randomAlphabetic(8));
    }

    @Override
    public final void invalidate(final ClientCard entity) {
        entity.setName(null);
    }

    @Override
    public void change(final ClientCard resource) {
        resource.setName(randomAlphabetic(8));
    }

}
