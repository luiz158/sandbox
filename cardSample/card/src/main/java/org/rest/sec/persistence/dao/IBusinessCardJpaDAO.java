package org.rest.sec.persistence.dao;

import org.rest.sec.model.BusinessCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface IBusinessCardJpaDAO extends JpaRepository<BusinessCard, Long>, JpaSpecificationExecutor<BusinessCard> {

    BusinessCard findByName(final String name);

}
