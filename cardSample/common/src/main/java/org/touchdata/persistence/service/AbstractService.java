package org.touchdata.persistence.service;

import java.util.List;

import org.apache.commons.lang3.tuple.ImmutableTriple;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;
import org.touchdata.common.ClientOperation;
import org.touchdata.common.IEntity;
import org.touchdata.persistence.event.BeforeEntityCreatedEvent;
import org.touchdata.persistence.event.EntitiesDeletedEvent;
import org.touchdata.persistence.event.EntityCreatedEvent;
import org.touchdata.persistence.event.EntityDeletedEvent;
import org.touchdata.persistence.event.EntityUpdatedEvent;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;

@Transactional
public abstract class AbstractService<T extends IEntity> implements IService<T> {
    protected final Logger logger = LoggerFactory.getLogger(getClass());

    private Class<T> clazz;

    @Autowired
    protected ApplicationEventPublisher eventPublisher;

    public AbstractService(final Class<T> clazzToSet) {
	super();

	clazz = clazzToSet;
    }

    // API

    // search

    @Override
    public List<T> search(final ImmutableTriple<String, ClientOperation, String>... constraints) {
	final Specification<T> firstSpec = resolveConstraint(constraints[0]);
	Specifications<T> specifications = Specifications.where(firstSpec);
	for (int i = 1; i < constraints.length; i++) {
	    specifications = specifications.and(resolveConstraint(constraints[i]));
	}
	if (firstSpec == null) {
	    return Lists.newArrayList();
	}

	return getSpecificationExecutor().findAll(specifications);
    }

    @Override
    public Page<T> searchPaged(final int page, final int size, final String sortBy, final ImmutableTriple<String, ClientOperation, String>... constraints) {
	Sort sortInfo = null;
	if (sortBy != null) {
	    sortInfo = new Sort(sortBy);
	}

	final Specification<T> firstSpec = resolveConstraint(constraints[0]);
	Preconditions.checkState(firstSpec != null);
	Specifications<T> specifications = Specifications.where(firstSpec);
	for (int i = 1; i < constraints.length; i++) {
	    specifications = specifications.and(resolveConstraint(constraints[i]));
	}

	return getSpecificationExecutor().findAll(specifications, new PageRequest(page, size, sortInfo));
    }

    // find

    @Override
    @Transactional(readOnly = true)
    public T findOne(final long id) {
	return getDao().findOne(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<T> findAll() {
	return Lists.newArrayList(getDao().findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public Page<T> findPaginated(final int page, final int size, final String sortBy) {
	Sort sortInfo = null;
	if (sortBy != null) {
	    sortInfo = new Sort(sortBy);
	}

	return getDao().findAll(new PageRequest(page, size, sortInfo));
    }

    // save/create/persist

    @Override
    public T create(final T entity) {
	Preconditions.checkNotNull(entity);
	eventPublisher.publishEvent(new BeforeEntityCreatedEvent<T>(this, clazz, entity));

	final T persistedEntity = getDao().save(entity);

	eventPublisher.publishEvent(new EntityCreatedEvent<T>(this, clazz, persistedEntity));
	return persistedEntity;
    }

    // update/merge

    @Override
    public void update(final T entity) {
	Preconditions.checkNotNull(entity);

	getDao().save(entity);
	eventPublisher.publishEvent(new EntityUpdatedEvent<T>(this, clazz, entity));
    }

    // delete

    @Override
    public void deleteAll() {
	getDao().deleteAll();
	eventPublisher.publishEvent(new EntitiesDeletedEvent<T>(this, clazz));
    }

    @Override
    public void delete(final long id) {
	final T entity = getDao().findOne(id);
	getDao().delete(entity);
	eventPublisher.publishEvent(new EntityDeletedEvent<T>(this, clazz, entity));
    }

    // template method

    protected abstract PagingAndSortingRepository<T, Long> getDao();

    protected abstract JpaSpecificationExecutor<T> getSpecificationExecutor();

    @SuppressWarnings("static-method")
    public Specification<T> resolveConstraint(final ImmutableTriple<String, ClientOperation, String> constraint) {
	throw new UnsupportedOperationException();
    }

}
