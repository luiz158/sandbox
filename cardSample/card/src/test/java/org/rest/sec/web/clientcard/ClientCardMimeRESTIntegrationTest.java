package org.rest.sec.web.clientcard;

import org.rest.sec.client.template.ClientCardRESTTemplateImpl;
import org.rest.sec.model.ClientCard;
import org.rest.sec.test.SecMimeRESTIntegrationTest;
import org.springframework.beans.factory.annotation.Autowired;

public class ClientCardMimeRESTIntegrationTest extends SecMimeRESTIntegrationTest<ClientCard> {

    @Autowired
    private ClientCardRESTTemplateImpl restTemplate;

    public ClientCardMimeRESTIntegrationTest() {
        super(ClientCard.class);
    }

    // tests

    // template method

    @Override
    protected final ClientCardRESTTemplateImpl getAPI() {
        return restTemplate;
    }

}
