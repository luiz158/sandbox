package org.touchdata.client.template;

import org.touchdata.common.IEntity;

public interface ITemplateAsURI<T extends IEntity> {

    // create

    String createResourceAsURI(final T resource); // 8

}
