package org.rest.sec.persistence.util;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;

import org.rest.sec.model.BusinessCard;
import org.rest.sec.model.ClientCard;

public class FixtureFactory {

    private FixtureFactory() {
        throw new AssertionError();
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
