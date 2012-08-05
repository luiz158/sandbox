package org.rest.sec.persistence.service;

import org.rest.common.persistence.service.IService;
import org.rest.sec.model.BusinessCard;

public interface IBusinessCardService extends IService<BusinessCard> {

    BusinessCard findByName(final String name);

}
