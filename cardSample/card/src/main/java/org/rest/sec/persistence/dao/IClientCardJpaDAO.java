package org.rest.sec.persistence.dao;

import org.rest.sec.model.ClientCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface IClientCardJpaDAO extends JpaRepository<ClientCard, Long>, JpaSpecificationExecutor<ClientCard> {

    ClientCard findByName(final String name);

}
