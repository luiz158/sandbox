package org.touchdata.client.template;

import org.touchdata.common.IEntity;

public interface IEntityOperations<T extends IEntity> {

    T createNewEntity();

    void invalidate(final T entity);

    void change(final T resource);

}
