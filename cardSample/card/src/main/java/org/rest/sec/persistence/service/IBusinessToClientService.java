package org.rest.sec.persistence.service;

import org.rest.common.persistence.service.IService;
import org.rest.sec.model.BusinessToClient;

public interface IBusinessToClientService extends IService<BusinessToClient> {

    BusinessToClient findByName(final String name);

}
