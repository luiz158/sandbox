package org.rest.sec.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.rest.common.exceptions.ConflictException;
import org.rest.common.util.QueryConstants;
import org.rest.common.util.SearchCommonUtil;
import org.rest.common.web.RestPreconditions;
import org.rest.common.web.controller.AbstractController;
import org.rest.common.web.controller.ISortingController;
import org.rest.sec.model.ClientCard;
import org.rest.sec.persistence.service.IClientCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.util.UriComponentsBuilder;

@Controller
@RequestMapping(value = "clientcard")
public class ClientCardController extends AbstractController<ClientCard> implements ISortingController<ClientCard> {

    @Autowired
    private IClientCardService service;

    public ClientCardController() {
        super(ClientCard.class);
    }

    // API

    // search

    @RequestMapping(params = { SearchCommonUtil.Q_PARAM }, method = RequestMethod.GET)
    @ResponseBody
    public List<ClientCard> search(@RequestParam(SearchCommonUtil.Q_PARAM) final String queryString) {
        return searchInternal(queryString);
    }

    // find - all/paginated

    @Override
    @RequestMapping(params = { QueryConstants.PAGE, QueryConstants.SIZE, QueryConstants.SORT_BY }, method = RequestMethod.GET)
    @ResponseBody
    public List<ClientCard> findAllPaginatedAndSorted(@RequestParam(value = QueryConstants.PAGE) final int page, @RequestParam(value = QueryConstants.SIZE) final int size,
            @RequestParam(value = QueryConstants.SORT_BY) final String sortBy, @RequestParam(value = QueryConstants.SORT_ORDER) final String sortOrder, final UriComponentsBuilder uriBuilder,
            final HttpServletResponse response) {
        return findPaginatedAndSortedInternal(page, size, sortBy, sortOrder, uriBuilder, response);
    }

    @Override
    @RequestMapping(params = { QueryConstants.PAGE, QueryConstants.SIZE }, method = RequestMethod.GET)
    @ResponseBody
    public List<ClientCard> findAllPaginated(@RequestParam(value = QueryConstants.PAGE) final int page, @RequestParam(value = QueryConstants.SIZE) final int size, final UriComponentsBuilder uriBuilder,
            final HttpServletResponse response) {
        return findPaginatedAndSortedInternal(page, size, null, null, uriBuilder, response);
    }

    @Override
    @RequestMapping(params = { QueryConstants.SORT_BY }, method = RequestMethod.GET)
    @ResponseBody
    public List<ClientCard> findAllSorted(@RequestParam(value = QueryConstants.SORT_BY) final String sortBy, @RequestParam(value = QueryConstants.SORT_ORDER) final String sortOrder) {
        return findAllSortedInternal(sortBy, sortOrder);
    }

    @Override
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public List<ClientCard> findAll(final HttpServletRequest request) {
        return findAllInternal(request);
    }

    @RequestMapping(value = "/businesscard/{id}", method = RequestMethod.GET)
    @ResponseBody
    public List<ClientCard> findAllByAssociation(@PathVariable("id") final Long idOfAssociation) {
        return service.findAllByAssociation(idOfAssociation);
    }

    // find - one

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ClientCard findOne(@PathVariable("id") final Long id, final UriComponentsBuilder uriBuilder, final HttpServletResponse response) {
        return findOneInternal(id, uriBuilder, response);
    }

    @RequestMapping(params = "name", method = RequestMethod.GET)
    @ResponseBody
    public ClientCard findOneByName(@RequestParam("name") final String name) {
        ClientCard resource = null;
        try {
            resource = RestPreconditions.checkNotNull(getService().findByName(name));
        } catch (final InvalidDataAccessApiUsageException ex) {
            logger.error("InvalidDataAccessApiUsageException on find operation");
            logger.warn("InvalidDataAccessApiUsageException on find operation", ex);
            throw new ConflictException(ex);
        }

        return resource;
    }

    // create

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody final ClientCard resource, final UriComponentsBuilder uriBuilder, final HttpServletResponse response) {
        createInternal(resource, uriBuilder, response);
    }

    // update

    @RequestMapping(method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void update(@RequestBody final ClientCard resource) {
        updateInternal(resource);
    }

    // delete

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") final Long id) {
        deleteByIdInternal(id);
    }

    // Spring

    @Override
    protected final IClientCardService getService() {
        return service;
    }

}
