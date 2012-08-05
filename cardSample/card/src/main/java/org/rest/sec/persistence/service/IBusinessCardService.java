package org.rest.sec.persistence.service;

import java.util.List;

import org.rest.common.persistence.service.IService;
import org.rest.sec.model.BusinessCard;

public interface IBusinessCardService extends IService<BusinessCard> {

    BusinessCard findByName(final String name);

    List<BusinessCard> findAllByAssociation(Long idOfClientCard);

}
