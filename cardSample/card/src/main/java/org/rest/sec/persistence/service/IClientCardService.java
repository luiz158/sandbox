package org.rest.sec.persistence.service;

import java.util.List;

import org.rest.common.persistence.service.IService;
import org.rest.sec.model.ClientCard;

public interface IClientCardService extends IService<ClientCard> {

    ClientCard findByName(final String name);

    List<ClientCard> findAllByAssociation(Long idOfAssociation);

}
