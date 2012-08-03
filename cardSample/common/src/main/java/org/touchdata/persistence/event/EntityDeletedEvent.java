package org.touchdata.persistence.event;

import org.springframework.context.ApplicationEvent;
import org.touchdata.common.IEntity;

import com.google.common.base.Preconditions;

/**
 * This event should be fired when entity is updated.
 */
public final class EntityDeletedEvent<T extends IEntity> extends ApplicationEvent {

    private final Class<T> clazz;
    private final T entity;

    public EntityDeletedEvent(final Object sourceToSet, final Class<T> clazzToSet, final T entityToSet) {
	super(sourceToSet);

	Preconditions.checkNotNull(clazzToSet);
	clazz = clazzToSet;

	Preconditions.checkNotNull(entityToSet);
	entity = entityToSet;
    }

    // API

    public final Class<T> getClazz() {
	return clazz;
    }

    public final T getEntity() {
	return Preconditions.checkNotNull(entity);
    }
}
