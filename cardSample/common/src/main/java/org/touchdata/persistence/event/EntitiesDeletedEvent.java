package org.touchdata.persistence.event;

import org.springframework.context.ApplicationEvent;
import org.touchdata.common.IEntity;

import com.google.common.base.Preconditions;

/**
 * This event is fired after all entities are deleted.
 */
public final class EntitiesDeletedEvent<T extends IEntity> extends ApplicationEvent {

    private final Class<T> clazz;

    public EntitiesDeletedEvent(final Object sourceToSet, final Class<T> clazzToSet) {
	super(sourceToSet);

	Preconditions.checkNotNull(clazzToSet);
	clazz = clazzToSet;
    }

    // API

    public final Class<T> getClazz() {
	return clazz;
    }

}
