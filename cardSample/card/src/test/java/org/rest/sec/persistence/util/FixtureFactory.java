package org.rest.sec.persistence.util;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;

import org.rest.sec.model.BusinessCard;
import org.rest.sec.model.ClientCard;
import org.rest.sec.model.BusinessToClient;

import com.google.common.collect.Sets;

public class FixtureFactory {

    private FixtureFactory() {
        throw new AssertionError();
    }

    // role

    public static BusinessToClient createNewRole() {
        return createNewRole(randomAlphabetic(8));
    }

    public static BusinessToClient createNewRole(final String name) {
        return new BusinessToClient(name, Sets.<BusinessCard> newHashSet());
    }

    // business card

    public static BusinessCard createNewBusinessCard() {
        return createNewBusinessCard(randomAlphabetic(8));
    }

    public static BusinessCard createNewBusinessCard(final String name) {
        return new BusinessCard(name);
    }

    // client card

    public static ClientCard createNewClientCard() {
        return createNewClientCard(randomAlphabetic(8));
    }

    public static ClientCard createNewClientCard(final String name) {
        return new ClientCard(name);
    }

}
