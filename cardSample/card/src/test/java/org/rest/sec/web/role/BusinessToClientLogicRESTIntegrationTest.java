package org.rest.sec.web.role;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import org.hamcrest.Matchers;
import org.junit.Test;
import org.rest.common.web.WebConstants;
import org.rest.sec.client.template.BusinessCardRESTTemplateImpl;
import org.rest.sec.client.template.BusinessToClientRESTTemplateImpl;
import org.rest.sec.model.BusinessCard;
import org.rest.sec.model.BusinessToClient;
import org.rest.sec.test.SecLogicRESTIntegrationTest;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.collect.Sets;
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

    // find all

    @Test
    public final void whenResourceIsRetrieved_thenAssociationsAreAlsoRetrieved() {
        final BusinessToClient existingResource = getAPI().create(getAPI().createNewEntity());
        assertThat(existingResource.getPrivileges(), not(Matchers.<BusinessCard> empty()));
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

    @Test
    public final void whenCreatingNewResourceWithExistingAssociations_thenNewResourceIsCorrectlyCreated() {
        final BusinessCard existingAssociation = getAssociationAPI().create(getAssociationAPI().createNewEntity());
        final BusinessToClient newResource = getAPI().createNewEntity();
        newResource.getPrivileges().add(existingAssociation);
        getAPI().create(newResource);

        final BusinessToClient newResource2 = getAPI().createNewEntity();
        newResource2.getPrivileges().add(existingAssociation);
        getAPI().create(newResource2);
    }

    /**
     * - note: this test ensures that a new User cannot automatically create new Privileges <br>
     * - note: the standard way to do this is: first create the BusinessCard resource(s), then associate them with the new User resource and then create the User resource
     */
    @Test
    public final void whenResourceIsCreatedWithNewAssociation_then409IsReceived() {
        final BusinessToClient newResource = getAPI().createNewEntity();
        newResource.getPrivileges().add(getAssociationAPI().createNewEntity());

        // When
        final Response response = getAPI().createAsResponse(newResource);

        // Then
        assertThat(response.getStatusCode(), is(409));
    }

    @Test
    public final void whenResourceIsCreatedWithInvalidAssociation_then409IsReceived() {
        final BusinessCard invalidAssociation = getAssociationAPI().createNewEntity();
        getAssociationAPI().invalidate(invalidAssociation);
        final BusinessToClient newResource = getAPI().createNewEntity();
        newResource.getPrivileges().add(invalidAssociation);

        // When
        final Response response = getAPI().createAsResponse(newResource);

        // Then
        assertThat(response.getStatusCode(), is(409));
    }

    @Test
    public final void whenResourceIsCreatedWithExistingAssociation_then201IsReceived() {
        final BusinessCard existingAssociation = getAssociationAPI().create(getAssociationAPI().createNewEntity());
        final BusinessToClient newResource = getAPI().createNewEntity();
        newResource.getPrivileges().add(existingAssociation);

        // When
        final Response response = getAPI().createAsResponse(newResource);

        // Then
        assertThat(response.getStatusCode(), is(201));
    }

    @Test
    public final void whenResourceIsCreatedWithExistingAssociation_thenAssociationIsLinkedToTheResource() {
        final BusinessCard existingAssociation = getAssociationAPI().create(getAssociationAPI().createNewEntity());
        final BusinessToClient resourceToCreate = getAPI().createNewEntity();

        // When
        resourceToCreate.getPrivileges().add(existingAssociation);
        final BusinessToClient existingResource = getAPI().create(resourceToCreate);

        // Then
        assertThat(existingResource.getPrivileges(), hasItem(existingAssociation));
    }

    // update

    @Test
    public final void givenResourceExists_whenResourceIsUpdatedWithExistingAsscoaition_thenAssociationIsCorrectlyUpdated() {
        // Given
        final BusinessToClient existingResource = getAPI().create(getAPI().createNewEntity());
        final BusinessCard existingAssociation = getAssociationAPI().create(getAssociationAPI().createNewEntity());
        existingResource.setPrivileges(Sets.newHashSet(existingAssociation));

        // When
        getAPI().update(existingResource);

        // Given
        final BusinessToClient updatedResource = getAPI().findOne(existingResource.getId());
        assertThat(updatedResource.getPrivileges(), hasItem(existingAssociation));
    }

    @Test
    public final void givenExistingResourceAndExistingAssociation_whenUpdatingResourceWithTheAssociation_thanAssociationCorrectlyDone() {
        final BusinessToClient existingResource = getAPI().create(getAPI().createNewEntity());
        final BusinessCard existingAssociation = getAssociationAPI().create(getAssociationAPI().createNewEntity());
        existingResource.setPrivileges(Sets.newHashSet(existingAssociation));

        getAPI().update(existingResource);
        final BusinessToClient updatedResource = getAPI().findOne(existingResource.getId());
        assertThat(updatedResource.getPrivileges(), hasItem(existingAssociation));
    }

    // scenarios

    @Test
    public final void whenScenarioOfWorkingWithAssociations_thenTheChangesAreCorrectlyPersisted() {
        final BusinessCard existingAssociation = getAssociationAPI().create(getAssociationAPI().createNewEntity());
        final BusinessToClient resource1 = new BusinessToClient(randomAlphabetic(6), Sets.newHashSet(existingAssociation));

        final BusinessToClient resource1ViewOfServerBefore = getAPI().create(resource1);
        assertThat(resource1ViewOfServerBefore.getPrivileges(), hasItem(existingAssociation));

        final BusinessToClient resource2 = new BusinessToClient(randomAlphabetic(6), Sets.newHashSet(existingAssociation));
        getAPI().create(resource2);

        final BusinessToClient resource1ViewOfServerAfter = getAPI().findOne(resource1ViewOfServerBefore.getId());
        assertThat(resource1ViewOfServerAfter.getPrivileges(), hasItem(existingAssociation));
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
