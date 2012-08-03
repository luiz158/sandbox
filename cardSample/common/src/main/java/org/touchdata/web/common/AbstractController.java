package org.touchdata.web.common;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.tuple.ImmutableTriple;
import org.apache.http.HttpHeaders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UriComponentsBuilder;
import org.touchdata.common.ClientOperation;
import org.touchdata.common.IEntity;
import org.touchdata.common.event.PaginatedResultsRetrievedEvent;
import org.touchdata.common.event.ResourceCreatedEvent;
import org.touchdata.common.event.SingleResourceRetrievedEvent;
import org.touchdata.common.exceptions.BadRequestException;
import org.touchdata.common.exceptions.ConflictException;
import org.touchdata.common.exceptions.ResourceNotFoundException;
import org.touchdata.common.web.RestPreconditions;
import org.touchdata.persistence.service.IService;
import org.touchdata.util.SearchCommonUtil;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;

public abstract class AbstractController<T extends IEntity> {
    protected final Logger logger = LoggerFactory.getLogger(getClass());

    private Class<T> clazz;

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    public AbstractController(final Class<T> clazzToSet) {
	super();

	Preconditions.checkNotNull(clazzToSet);
	clazz = clazzToSet;
    }

    // search

    @SuppressWarnings("unchecked")
    public List<T> searchInternal(@RequestParam(SearchCommonUtil.Q_PARAM) final String queryString) {
	try {
	    List<ImmutableTriple<String, ClientOperation, String>> parsedQuery = null;
	    try {
		parsedQuery = SearchCommonUtil.parseQueryString(queryString);
	    } catch (final IllegalStateException illState) {
		logger.error("IllegalStateException on find operation");
		logger.warn("IllegalStateException on find operation", illState);
		throw new ConflictException(illState);
	    }

	    final List<T> results = getService().search(parsedQuery.toArray(new ImmutableTriple[parsedQuery.size()]));
	    return results;
	} catch (final IllegalStateException illEx) {
	    logger.error("IllegalStateException on search operation");
	    logger.warn("IllegalStateException on search operation", illEx);
	    throw new BadRequestException(illEx);
	} catch (final UnsupportedOperationException unsupEx) {
	    logger.error("UnsupportedOperationException on search operation");
	    logger.warn("UnsupportedOperationException on search operation", unsupEx);
	    throw new BadRequestException(unsupEx);
	}
    }

    @SuppressWarnings("unchecked")
    public List<T> searchInternalPaged(@RequestParam(SearchCommonUtil.Q_PARAM) final String queryString, final int page, final int size) {
	try {
	    List<ImmutableTriple<String, ClientOperation, String>> parsedQuery = null;
	    try {
		parsedQuery = SearchCommonUtil.parseQueryString(queryString);
	    } catch (final IllegalStateException illState) {
		logger.error("IllegalStateException on find operation");
		logger.warn("IllegalStateException on find operation", illState);
		throw new ConflictException(illState);
	    }

	    final Page<T> resultPage = getService().searchPaged(page, size, null, parsedQuery.toArray(new ImmutableTriple[parsedQuery.size()]));
	    return Lists.newArrayList(resultPage.getContent());
	} catch (final IllegalStateException illEx) {
	    logger.error("IllegalStateException on search operation");
	    logger.warn("IllegalStateException on search operation", illEx);
	    throw new BadRequestException(illEx);
	} catch (final UnsupportedOperationException unsupEx) {
	    logger.error("UnsupportedOperationException on search operation");
	    logger.warn("UnsupportedOperationException on search operation", unsupEx);
	    throw new BadRequestException(unsupEx);
	}
    }

    // find/get

    protected final T findOneInternal(final Long id, final UriComponentsBuilder uriBuilder, final HttpServletResponse response) {
	T resource = null;
	try {
	    resource = RestPreconditions.checkNotNull(getService().findOne(id));
	} catch (final InvalidDataAccessApiUsageException ex) {
	    logger.error("InvalidDataAccessApiUsageException on find operation");
	    logger.warn("InvalidDataAccessApiUsageException on find operation", ex);
	    throw new ConflictException(ex);
	}

	eventPublisher.publishEvent(new SingleResourceRetrievedEvent<T>(clazz, uriBuilder, response));

	return resource;
    }

    protected final List<T> findAllInternal() {
	return getService().findAll();
    }

    protected final void findAllRedirectToPagination(final UriComponentsBuilder uriBuilder, final HttpServletResponse response) {
	final String resourceName = clazz.getSimpleName().toString().toLowerCase();
	final String locationValue = uriBuilder.path("/" + resourceName).build().encode().toUriString() + "?page=0&size=10";

	response.setHeader(HttpHeaders.LOCATION, locationValue);
    }

    protected final List<T> findPaginatedInternal(final int page, final int size, final String sortBy, final UriComponentsBuilder uriBuilder, final HttpServletResponse response) {
	Page<T> resultPage = null;
	try {
	    resultPage = getService().findPaginated(page, size, sortBy);
	} catch (final InvalidDataAccessApiUsageException apiEx) {
	    logger.error("InvalidDataAccessApiUsageException on find operation");
	    logger.warn("InvalidDataAccessApiUsageException on find operation", apiEx);
	    throw new BadRequestException(apiEx);
	}

	if (page > resultPage.getTotalPages()) {
	    throw new ResourceNotFoundException();
	}
	eventPublisher.publishEvent(new PaginatedResultsRetrievedEvent<T>(clazz, uriBuilder, response, page, resultPage.getTotalPages(), size));

	return Lists.newArrayList(resultPage.getContent());
    }

    // save/create/persist

    protected final void createInternal(final T resource, final UriComponentsBuilder uriBuilder, final HttpServletResponse response) {
	RestPreconditions.checkRequestElementNotNull(resource);
	RestPreconditions.checkRequestState(resource.getId() == null);
	try {
	    getService().create(resource);
	}
	// this is so that the service layer can MANUALLY throw exceptions that get handled by the exception translation mechanism
	catch (final IllegalStateException illegalState) {
	    logger.error("IllegalArgumentException on create operation for: " + resource.getClass().getSimpleName());
	    logger.warn("IllegalArgumentException on create operation for: " + resource.getClass().getSimpleName(), illegalState);
	    throw new ConflictException(illegalState);
	} catch (final DataIntegrityViolationException ex) { // on unique constraint
	    logger.error("DataIntegrityViolationException on create operation for: " + resource.getClass().getSimpleName());
	    logger.warn("DataIntegrityViolationException on create operation for: " + resource.getClass().getSimpleName(), ex);
	    throw new ConflictException(ex);
	} catch (final InvalidDataAccessApiUsageException dataEx) { // on saving a new Resource that also contains new/unsaved entities
	    logger.error("InvalidDataAccessApiUsageException on create operation for: " + resource.getClass().getSimpleName());
	    logger.warn("InvalidDataAccessApiUsageException on create operation for: " + resource.getClass().getSimpleName(), dataEx);
	    throw new ConflictException(dataEx);
	} catch (final DataAccessException dataEx) {
	    logger.error("Generic DataAccessException on create operation for: " + resource.getClass().getSimpleName());
	    logger.warn("Generic DataAccessException on create operation for: " + resource.getClass().getSimpleName(), dataEx);
	    throw new ConflictException(dataEx);
	}

	// - note: mind the autoboxing and potential NPE when the resource has null id at this point (likely when working with DTOs)
	eventPublisher.publishEvent(new ResourceCreatedEvent<T>(clazz, uriBuilder, response, resource.getId()));
    }

    // update

    protected final void updateInternal(final T resource) {
	RestPreconditions.checkRequestElementNotNull(resource);
	RestPreconditions.checkRequestElementNotNull(resource.getId());
	RestPreconditions.checkNotNull(getService().findOne(resource.getId()));

	try {
	    getService().update(resource);
	}
	// this is so that the service layer can MANUALLY throw exceptions that get handled by the exception translation mechanism
	catch (final IllegalStateException illegalState) {
	    logger.error("IllegalArgumentException on create operation for: " + resource.getClass().getSimpleName());
	    logger.warn("IllegalArgumentException on create operation for: " + resource.getClass().getSimpleName(), illegalState);
	    throw new ConflictException(illegalState);
	} catch (final InvalidDataAccessApiUsageException dataEx) {
	    logger.error("InvalidDataAccessApiUsageException on update operation for: " + resource.getClass().getSimpleName());
	    logger.warn("InvalidDataAccessApiUsageException on update operation for: " + resource.getClass().getSimpleName(), dataEx);
	    throw new ConflictException(dataEx);
	} catch (final DataIntegrityViolationException dataEx) { // on unique constraint
	    logger.error("DataIntegrityViolationException on update operation for: " + resource.getClass().getSimpleName());
	    logger.warn("DataIntegrityViolationException on update operation for: " + resource.getClass().getSimpleName(), dataEx);
	    throw new ConflictException(dataEx);
	}
    }

    // delete/remove

    protected final void deleteByIdInternal(final long id) {
	try {
	    getService().delete(id);
	} catch (final InvalidDataAccessApiUsageException dataEx) {
	    logger.error("InvalidDataAccessApiUsageException on delete operation");
	    logger.warn("InvalidDataAccessApiUsageException on delete operation", dataEx);
	    throw new ResourceNotFoundException(dataEx);
	} catch (final DataAccessException dataEx) {
	    logger.error("DataAccessException on delete operation");
	    logger.warn("DataAccessException on delete operation", dataEx);
	}
    }

    // template method

    protected abstract IService<T> getService();

}
