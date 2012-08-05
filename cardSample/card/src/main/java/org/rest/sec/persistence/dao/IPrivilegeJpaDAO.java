package org.rest.sec.persistence.dao;

import org.rest.sec.model.Privilege;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface IPrivilegeJpaDAO extends JpaRepository<Privilege, Long>, JpaSpecificationExecutor<Privilege> {

    Privilege findByName(final String name);

}
