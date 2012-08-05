package org.rest.sec.client.template.newer;

import org.rest.common.client.template.AbstractClientRESTTemplate;
import org.rest.sec.client.SecBusinessPaths;
import org.rest.sec.model.BusinessCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("client")
public class BusinessCardClientRESTTemplate extends AbstractClientRESTTemplate<BusinessCard> {

    @Autowired
    private SecBusinessPaths paths;

    public BusinessCardClientRESTTemplate() {
        super(BusinessCard.class);
    }

    // operations

    // template method

    @Override
    public final String getURI() {
        return paths.getBusinessCardUri();
    }

    @Override
    protected void basicAuth() {
        //
    }

}
