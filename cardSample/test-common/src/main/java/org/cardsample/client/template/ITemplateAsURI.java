package org.cardsample.client.template;

import org.cardsample.common.IEntity;

public interface ITemplateAsURI<T extends IEntity> {

    // create

    String createResourceAsURI(final T resource); // 8

}
