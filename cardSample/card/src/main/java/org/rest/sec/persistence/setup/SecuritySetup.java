package org.rest.sec.persistence.setup;

import java.util.Set;

import org.rest.common.event.BeforeSetupEvent;
import org.rest.sec.model.BusinessCard;
import org.rest.sec.model.BusinessToClient;
import org.rest.sec.persistence.service.IBusinessCardService;
import org.rest.sec.persistence.service.IBusinessToClientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.google.common.collect.Sets;

@Component
@Profile("production")
public class SecuritySetup implements ApplicationListener<ContextRefreshedEvent> {
    static final Logger logger = LoggerFactory.getLogger(SecuritySetup.class);

    private boolean setupDone;

    @Autowired
    private IBusinessToClientService roleService;

    @Autowired
    private IBusinessCardService privilegeService;

    @Autowired
    private ApplicationContext eventPublisher;

    public SecuritySetup() {
        super();
    }

    //

    /**
     * - note that this is a compromise - the flag makes this bean statefull which can (and will) be avoided in the future by a more advanced mechanism <br>
     * - the reason for this is that the context is refreshed more than once throughout the lifecycle of the deployable <br>
     * - alternatives: proper persisted versioning
     */
    @Override
    public final void onApplicationEvent(final ContextRefreshedEvent event) {
        if (!setupDone) {
            logger.info("Executing Setup");
            eventPublisher.publishEvent(new BeforeSetupEvent(this));

            createPrivileges();
            createRoles();

            setupDone = true;
            logger.info("Setup Done");
        }
    }

    // BusinessCard

    private void createPrivileges() {
        createPrivilegeIfNotExisting("CAN_USER_WRITE");
        createPrivilegeIfNotExisting("CAN_ROLE_WRITE");
    }

    final void createPrivilegeIfNotExisting(final String name) {
        final BusinessCard entityByName = privilegeService.findByName(name);
        if (entityByName == null) {
            final BusinessCard entity = new BusinessCard(name);
            privilegeService.create(entity);
        }
    }

    // BusinessToClient

    private void createRoles() {
        final BusinessCard privilegeUserWrite = privilegeService.findByName("CAN_USER_WRITE");
        final BusinessCard privilegeRoleWrite = privilegeService.findByName("CAN_ROLE_WRITE");

        createRoleIfNotExisting("ROLE_ADMIN", Sets.<BusinessCard> newHashSet(privilegeUserWrite, privilegeRoleWrite));
    }

    final void createRoleIfNotExisting(final String name, final Set<BusinessCard> privileges) {
        final BusinessToClient entityByName = roleService.findByName(name);
        if (entityByName == null) {
            final BusinessToClient entity = new BusinessToClient(name);
            entity.setPrivileges(privileges);
            roleService.create(entity);
        }
    }

}
