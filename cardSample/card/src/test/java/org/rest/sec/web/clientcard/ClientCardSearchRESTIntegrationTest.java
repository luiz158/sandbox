package org.rest.sec.web.clientcard;

import org.junit.runner.RunWith;
import org.rest.common.client.IEntityOperations;
import org.rest.common.web.base.AbstractSearchRESTIntegrationTest;
import org.rest.sec.client.template.ClientCardRESTTemplateImpl;
import org.rest.sec.model.ClientCard;
import org.rest.spring.client.ClientTestConfig;
import org.rest.spring.context.ContextTestConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { ClientTestConfig.class, ContextTestConfig.class }, loader = AnnotationConfigContextLoader.class)
public class ClientCardSearchRESTIntegrationTest extends AbstractSearchRESTIntegrationTest<ClientCard> {

    @Autowired
    private ClientCardRESTTemplateImpl restTemplate;

    public ClientCardSearchRESTIntegrationTest() {
        super();
    }

    // tests

    // template

    @Override
    protected final ClientCardRESTTemplateImpl getAPI() {
        return restTemplate;
    }

    @Override
    protected final IEntityOperations<ClientCard> getEntityOperations() {
        return restTemplate;
    }

}
