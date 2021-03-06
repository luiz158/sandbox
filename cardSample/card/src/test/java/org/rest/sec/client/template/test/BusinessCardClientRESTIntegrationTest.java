package org.rest.sec.client.template.test;

import org.junit.runner.RunWith;
import org.rest.common.client.AbstractClientRESTIntegrationTest;
import org.rest.common.client.IEntityOperations;
import org.rest.sec.client.template.BusinessCardRESTTemplateImpl;
import org.rest.sec.client.template.newer.BusinessCardClientRESTTemplate;
import org.rest.sec.model.BusinessCard;
import org.rest.spring.client.ClientTestConfig;
import org.rest.spring.context.ContextTestConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { ContextTestConfig.class, ClientTestConfig.class }, loader = AnnotationConfigContextLoader.class)
public class BusinessCardClientRESTIntegrationTest extends AbstractClientRESTIntegrationTest<BusinessCard> {

    @Autowired
    private BusinessCardClientRESTTemplate clientTemplate;
    @Autowired
    private BusinessCardRESTTemplateImpl entityOps;

    public BusinessCardClientRESTIntegrationTest() {
        super();
    }

    // tests

    // template method

    @Override
    protected final BusinessCardClientRESTTemplate getAPI() {
        return clientTemplate;
    }

    @Override
    protected final IEntityOperations<BusinessCard> getEntityOps() {
        return entityOps;
    }

}
