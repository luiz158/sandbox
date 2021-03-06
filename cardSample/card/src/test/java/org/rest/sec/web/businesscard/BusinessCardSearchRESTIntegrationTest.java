package org.rest.sec.web.businesscard;

import org.junit.runner.RunWith;
import org.rest.common.client.IEntityOperations;
import org.rest.common.web.base.AbstractSearchRESTIntegrationTest;
import org.rest.sec.client.template.BusinessCardRESTTemplateImpl;
import org.rest.sec.model.BusinessCard;
import org.rest.spring.client.ClientTestConfig;
import org.rest.spring.context.ContextTestConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { ClientTestConfig.class, ContextTestConfig.class }, loader = AnnotationConfigContextLoader.class)
public class BusinessCardSearchRESTIntegrationTest extends AbstractSearchRESTIntegrationTest<BusinessCard> {

    @Autowired
    private BusinessCardRESTTemplateImpl restTemplate;

    public BusinessCardSearchRESTIntegrationTest() {
        super();
    }

    // tests

    // template

    @Override
    protected final BusinessCardRESTTemplateImpl getAPI() {
        return restTemplate;
    }

    @Override
    protected final IEntityOperations<BusinessCard> getEntityOperations() {
        return restTemplate;
    }

}
