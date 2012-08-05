package org.rest.sec.persistence.dao;

import org.rest.sec.model.BusinessToClient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface IBusinessToClientJpaDAO extends JpaRepository<BusinessToClient, Long>, JpaSpecificationExecutor<BusinessToClient> {

    BusinessToClient findByName(final String name);

}
