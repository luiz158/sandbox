package org.rest.sec.client.template.test;

import org.junit.runner.RunWith;
import org.rest.common.client.AbstractClientRESTIntegrationTest;
import org.rest.common.client.IEntityOperations;
import org.rest.sec.client.template.ClientCardRESTTemplateImpl;
import org.rest.sec.client.template.newer.ClientCardClientRESTTemplate;
import org.rest.sec.model.ClientCard;
import org.rest.spring.client.ClientTestConfig;
import org.rest.spring.context.ContextTestConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { ContextTestConfig.class, ClientTestConfig.class }, loader = AnnotationConfigContextLoader.class)
public class ClientCardClientRESTIntegrationTest extends AbstractClientRESTIntegrationTest<ClientCard> {

    @Autowired
    private ClientCardClientRESTTemplate clientTemplate;
    @Autowired
    private ClientCardRESTTemplateImpl entityOps;

    public ClientCardClientRESTIntegrationTest() {
        super();
    }

    // tests

    // template method

    @Override
    protected final ClientCardClientRESTTemplate getAPI() {
        return clientTemplate;
    }

    @Override
    protected final IEntityOperations<ClientCard> getEntityOps() {
        return entityOps;
    }

}
