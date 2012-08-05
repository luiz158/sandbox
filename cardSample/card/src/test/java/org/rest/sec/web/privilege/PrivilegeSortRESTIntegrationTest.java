package org.rest.sec.web.privilege;

import org.rest.sec.client.template.PrivilegeRESTTemplateImpl;
import org.rest.sec.model.BusinessCard;
import org.rest.sec.test.SecSortRESTIntegrationTest;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.collect.Ordering;

public class PrivilegeSortRESTIntegrationTest extends SecSortRESTIntegrationTest<BusinessCard> {

    @Autowired
    private PrivilegeRESTTemplateImpl template;

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
    protected final PrivilegeRESTTemplateImpl getAPI() {
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
