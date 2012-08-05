package org.rest.sec.web.businesscard;

import org.rest.sec.client.template.BusinessCardRESTTemplateImpl;
import org.rest.sec.model.BusinessCard;
import org.rest.sec.test.SecSortRESTIntegrationTest;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.collect.Ordering;

public class BusinessCardSortRESTIntegrationTest extends SecSortRESTIntegrationTest<BusinessCard> {

    @Autowired
    private BusinessCardRESTTemplateImpl template;

    // tests

    // template method (shortcuts)

    @Override
    protected final BusinessCard createNewEntity() {
        return template.createNewEntity();
    }

    @Override
    protected final String getURI() {
        return template.getURI();
    }

    @Override
    protected final BusinessCardRESTTemplateImpl getAPI() {
        return template;
    }

    @Override
    protected final Ordering<BusinessCard> getOrdering() {
        return new Ordering<BusinessCard>() {
            @Override
            public final int compare(final BusinessCard left, final BusinessCard right) {
                return left.getName().compareToIgnoreCase(right.getName());
            }
        };
    }

}
