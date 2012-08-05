package org.rest.sec.client.template.newer;

import org.rest.common.client.template.AbstractClientRESTTemplate;
import org.rest.sec.client.SecBusinessPaths;
import org.rest.sec.model.ClientCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("client")
public class ClientCardClientRESTTemplate extends AbstractClientRESTTemplate<ClientCard> {

    @Autowired
    private SecBusinessPaths paths;

    public ClientCardClientRESTTemplate() {
        super(ClientCard.class);
    }

    // operations

    // template method

    @Override
    public final String getURI() {
        return paths.getClientCardUri();
    }

    @Override
    protected void basicAuth() {
        //
    }

}
