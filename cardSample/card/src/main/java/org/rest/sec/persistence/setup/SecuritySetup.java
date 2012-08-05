package org.rest.sec.persistence.setup;

import org.rest.common.event.BeforeSetupEvent;
import org.rest.sec.model.BusinessCard;
import org.rest.sec.model.ClientCard;
import org.rest.sec.persistence.service.IBusinessCardService;
import org.rest.sec.persistence.service.IClientCardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
@Profile("production")
public class SecuritySetup implements ApplicationListener<ContextRefreshedEvent> {
    static final Logger logger = LoggerFactory.getLogger(SecuritySetup.class);

    private boolean setupDone;

    @Autowired
    private IBusinessCardService businessCardService;
    @Autowired
    private IClientCardService clientCardService;

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

            createBusinessCards();
            createClientCards();

            setupDone = true;
            logger.info("Setup Done");
        }
    }

    // BusinessCard

    private void createBusinessCards() {
        createBusinessCardIfNotExisting("BusinessCard1");
        createBusinessCardIfNotExisting("BusinessCard2");
    }

    final void createBusinessCardIfNotExisting(final String name) {
        final BusinessCard entityByName = businessCardService.findByName(name);
        if (entityByName == null) {
            final BusinessCard entity = new BusinessCard(name);
            businessCardService.create(entity);
        }
    }

    // ClientCard

    private void createClientCards() {
        createClientCardsIfNotExisting("ClientCard1");
        createClientCardsIfNotExisting("ClientCard2");
    }

    final void createClientCardsIfNotExisting(final String name) {
        final ClientCard entityByName = clientCardService.findByName(name);
        if (entityByName == null) {
            final ClientCard entity = new ClientCard(name);
            clientCardService.create(entity);
        }
    }

}
