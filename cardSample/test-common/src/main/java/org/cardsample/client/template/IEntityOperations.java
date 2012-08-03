package org.cardsample.client.template;

import org.cardsample.common.IEntity;

public interface IEntityOperations<T extends IEntity> {

    T createNewEntity();

    void invalidate(final T entity);

    void change(final T resource);

}
