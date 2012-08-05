package org.rest.sec.web.role;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.rest.common.web.WebConstants;
import org.rest.sec.client.template.BusinessCardRESTTemplateImpl;
import org.rest.sec.client.template.BusinessToClientRESTTemplateImpl;
import org.rest.sec.model.BusinessToClient;
import org.rest.sec.test.SecLogicRESTIntegrationTest;
import org.springframework.beans.factory.annotation.Autowired;

import com.jayway.restassured.response.Response;
import com.jayway.restassured.specification.RequestSpecification;

public class BusinessToClientLogicRESTIntegrationTest extends SecLogicRESTIntegrationTest<BusinessToClient> {

    @Autowired
    private BusinessToClientRESTTemplateImpl restTemplate;
    @Autowired
    private BusinessCardRESTTemplateImpl associationRestTemplate;

    public BusinessToClientLogicRESTIntegrationTest() {
        super(BusinessToClient.class);
    }

    // tests

    // escaping characters

    @Test
    public final void givenWorkingWithSpecialCharacters_whtnResourcesIfRetrievedByName_thenResourceIsCorrectlyRetrieved() {
        final BusinessToClient newResource = getAPI().createNewEntity();
        newResource.setName("Macy's,Dell, Inc.");
        getAPI().createAsResponse(newResource);

        // When
        final BusinessToClient retrievedResource = getAPI().findByName(newResource.getName());
        assertEquals(newResource, retrievedResource);
    }

    // find one

    @Test
    public final void givenResourceExists_whenResourceIsRetrievedByName_thenResourceIsCorrectlyRetrieved() {
        final BusinessToClient newResource = getAPI().createNewEntity();
        getAPI().create(newResource);
        final BusinessToClient existingResourceByName = getAPI().findByName(newResource.getName());
        assertEquals(newResource, existingResourceByName);
    }

    // create

    @Test
    public final void givenResourceHasNameWithSpace_whenResourceIsCreated_then201IsReceived() {
        final BusinessToClient newResource = getAPI().createNewEntity();
        newResource.setName(randomAlphabetic(4) + " " + randomAlphabetic(4));

        // When
        final Response createAsResponse = getAPI().createAsResponse(newResource);

        // Then
        assertThat(createAsResponse.getStatusCode(), is(201));
    }

    @Test
    public final void givenExistingResourceHasNameWithSpace_whtnResourcesIfRetrievedByName_thenResourceIsCorrectlyRetrieved() {
        final BusinessToClient newResource = getAPI().createNewEntity();
        newResource.setName(randomAlphabetic(4) + " " + randomAlphabetic(4));
        getAPI().createAsResponse(newResource);

        // When
        final BusinessToClient retrievedResource = getAPI().findByName(newResource.getName());
        assertEquals(newResource, retrievedResource);
    }

    // template

    @Override
    protected final BusinessToClientRESTTemplateImpl getAPI() {
        return restTemplate;
    }

    @Override
    protected final String getURI() {
        return getAPI().getURI() + WebConstants.PATH_SEP;
    }

    @Override
    protected final RequestSpecification givenAuthenticated() {
        return getAPI().givenAuthenticated();
    }

    // util

    final BusinessCardRESTTemplateImpl getAssociationAPI() {
        return associationRestTemplate;
    }

}
